package com.gsdd.pcstock.util;

import com.gsdd.pcstock.constants.PcStockGeneralConstants;
import com.gsdd.pcstock.enums.NameExclusionEnum;
import com.gsdd.pcstock.model.Exclusion;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

@Slf4j
@UtilityClass
public final class RenameUtil {

  /**
   * @param dir
   * @param filters
   * @param newName
   * @return true if all can be renamed.
   */
  public static boolean renameAllFilesOnDir(File dir, String[] filters, String newName) {
    try {
      AtomicInteger failedCount = new AtomicInteger(0);
      Collection<File> files = FileUtils.listFiles(dir, filters, true);
      List<Exclusion> exclusions = getExclusion(files);
      String newFullName = dir + File.separator + newName.trim();
      files.parallelStream()
          .forEach(
              (File file) -> {
                String currentName = file.getName();
                String tempEN = NameExclusionEnum.findByName(currentName);
                String fileExt = extractFileExtension(currentName);
                String episodeNumber =
                    extractFileName(currentName)
                        .replaceAll(
                            PcStockGeneralConstants.REGEX_NUMBER, PcStockGeneralConstants.EMPTY);
                for (Exclusion exclusion : exclusions) {
                  episodeNumber =
                      episodeNumber.replaceFirst(
                          exclusion.getCode(), PcStockGeneralConstants.EMPTY);
                }
                episodeNumber = completeEpisode(episodeNumber, files.size());
                File f1 = new File(dir + File.separator + currentName);
                String ren = getRenameValue(tempEN, episodeNumber, fileExt, newFullName);
                boolean r = f1.renameTo(new File(ren));
                if (!r) {
                  failedCount.addAndGet(1);
                }
              });
      return (failedCount.get() == 0);
    } catch (Exception e) {
      log.error("Error renaming files: {}", e.getMessage(), e);
      return false;
    }
  }

  /**
   * complete with 0's when necessary.
   *
   * @param ep episode name.
   * @param quantity files that match the episode name.
   * @return episode with number.
   */
  public static String completeEpisode(String ep, Integer quantity) {
    if (ep != null && !PcStockGeneralConstants.EMPTY.equals(ep.trim())) {
      return String.format("%0" + String.valueOf(quantity).length() + "d", Integer.parseInt(ep));
    }
    return ep;
  }

  /**
   * Rename the file and preserve its special parts.
   *
   * @param tempEn specific part of the name to be appended.
   * @param episode number of episode.
   * @param fileExt extension for file.
   * @param newName new prefix for file.
   * @return new name for the file.
   */
  public static String getRenameValue(
      String tempEn, String episode, String fileExt, String newName) {
    if (tempEn == null) {
      return newName + PcStockGeneralConstants.SPACE + episode + fileExt;
    } else {
      if (PcStockGeneralConstants.EMPTY.equals(episode)) {
        return newName + tempEn + fileExt;
      } else {
        return newName + tempEn + PcStockGeneralConstants.SPACE + episode + fileExt;
      }
    }
  }

  /**
   * Allows to exclude from the episode numeric values than are part of the episode name.
   *
   * @param files list of files to analyze.
   * @return list of exclusion for avoid to take numeric value as a part of episode number.
   */
  public static List<Exclusion> getExclusion(Collection<File> files) {
    List<Exclusion> tmp = new ArrayList<>();
    List<String> numberList = new ArrayList<>();
    try {
      for (File file : files) {
        String currentName = file.getName();
        String episode =
            extractFileName(currentName)
                .replaceAll(PcStockGeneralConstants.REGEX_NUMBER, PcStockGeneralConstants.SPACE);
        episode =
            episode.replaceAll(PcStockGeneralConstants.REGEX_SPACE, PcStockGeneralConstants.SPACE);
        Set<String> hs = new HashSet<>(Arrays.asList(episode.split(PcStockGeneralConstants.SPACE)));
        numberList.addAll(hs);
        for (String s : numberList) {
          Exclusion e = new Exclusion();
          e.setCode(s);
          e.setFrequency(1L);
          if (tmp.contains(e)) {
            int pos = tmp.indexOf(e);
            tmp.get(pos).setFrequency(tmp.get(pos).getFrequency() + 1);
          } else {
            tmp.add(e);
          }
        }
        numberList.clear();
      }
      Comparator<Exclusion> excComparator = Comparator.comparing(Exclusion::getFrequency);
      tmp.sort(excComparator);
      int filesSize = files.size();
      return tmp.stream()
          .filter(exclusionDto -> exclusionDto.getFrequency() == filesSize)
          .collect(Collectors.toList());
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return Collections.emptyList();
    }
  }

  private static String extractFileName(String episodeName) {
    return episodeName.substring(0, episodeName.lastIndexOf(PcStockGeneralConstants.DOT));
  }

  private static String extractFileExtension(String episodeName) {
    return episodeName.substring(episodeName.lastIndexOf(PcStockGeneralConstants.DOT));
  }
}
