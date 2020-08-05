package co.com.gsdd.pcstock.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;

import co.com.gsdd.pcstock.constants.PCStockGralConstants;
import co.com.gsdd.pcstock.enums.NameExclusion;
import co.com.gsdd.pcstock.model.Exclusion;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RenameUtil {

    /**
     *
     * @param dir
     * @param filters
     * @param newName
     * @return true if all can be renamed.
     */
    public static boolean renameAllFilesOnDir(File dir, String[] filters, String newName) {
        try {
            Integer failedCount = 0;
            Collection<File> files = FileUtils.listFiles(dir, filters, true);
            List<Exclusion> exclusions = getExclusion(files);
            newName = dir + File.separator + newName.trim();
            for (File file : files) {
                String currentName = file.getName();
                String tempEN = NameExclusion.findByName(currentName);
                String fileExt = extractFileExtension(currentName);
                String episodeNumber = extractFileName(currentName).replaceAll(PCStockGralConstants.REGEX_NUMBER,
                        PCStockGralConstants.EMPTY);
                for (Exclusion exclusion : exclusions) {
                    episodeNumber = episodeNumber.replaceFirst(exclusion.getCode(), PCStockGralConstants.EMPTY);
                }
                episodeNumber = completeEpisode(episodeNumber, files.size());
                File f1 = new File(dir + File.separator + currentName);
                String ren = getRenameValue(tempEN, episodeNumber, fileExt, newName);
                boolean r = f1.renameTo(new File(ren));
                if (!r) {
                    failedCount++;
                }
            }
            return (failedCount == 0);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }

    /**
     * complete with 0's when necessary.
     * 
     * @param ep
     *            episode name.
     * @param quantity
     *            files that match the episode name.
     * @return episode with number.
     */
    public static String completeEpisode(String ep, Integer quantity) {
        if (ep != null && !PCStockGralConstants.EMPTY.equals(ep.trim())) {
            return String.format("%0" + String.valueOf(quantity).length() + "d", Integer.parseInt(ep));
        }
        return ep;
    }

    /**
     * Rename the file and preserve its special parts.
     * 
     * @param tempEN
     *            specific part of the name to be appended.
     * @param episode
     *            number of episode.
     * @param fileExt
     *            extension for file.
     * @param newName
     *            new preffix for file.
     * @return new name for the file.
     */
    public static String getRenameValue(String tempEN, String episode, String fileExt, String newName) {
        if (tempEN == null) {
            return newName + PCStockGralConstants.SPACE + episode + fileExt;
        } else {
            if (PCStockGralConstants.EMPTY.equals(episode)) {
                return newName + tempEN + fileExt;
            } else {
                return newName + tempEN + PCStockGralConstants.SPACE + episode + fileExt;
            }
        }
    }

    /**
     * Allows to exclude from the episode numeric values than are part of the episode name.
     * 
     * @param files
     *            list of files to analyze.
     * @return list of exclusion for avoid to take numeric value as a part of episode number.
     */
    public static List<Exclusion> getExclusion(Collection<File> files) {
        List<Exclusion> tmp = new ArrayList<>();
        List<String> numberList = new ArrayList<>();
        try {
            for (File file : files) {
                String currentName = file.getName();
                String episode = extractFileName(currentName).replaceAll(PCStockGralConstants.REGEX_NUMBER,
                        PCStockGralConstants.SPACE);
                episode = episode.replaceAll(PCStockGralConstants.REGEX_SPACE, PCStockGralConstants.SPACE);
                Set<String> hs = new HashSet<>();
                hs.addAll(Arrays.asList(episode.split(PCStockGralConstants.SPACE)));
                numberList.addAll(hs);
                for (String s : numberList) {
                    Exclusion e = new Exclusion();
                    e.setCode(s);
                    e.setFrequency(1L);
                    if (tmp.contains(e)) {
                        Integer pos = tmp.indexOf(e);
                        tmp.get(pos).setFrequency(tmp.get(pos).getFrequency() + 1);
                    } else {
                        tmp.add(e);
                    }
                }
                numberList.clear();
            }
            Comparator<Exclusion> excComparador = (Exclusion p1, Exclusion p2) -> p1.getFrequency()
                    .compareTo(p2.getFrequency());
            Collections.sort(tmp, excComparador);
            int filesSize = files.size();
            return tmp.stream().filter(exclusionDto -> exclusionDto.getFrequency() == filesSize)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    private static String extractFileName(String episodeName) {
        return episodeName.substring(0, episodeName.lastIndexOf(PCStockGralConstants.DOT));
    }

    private static String extractFileExtension(String episodeName) {
        return episodeName.substring(episodeName.lastIndexOf(PCStockGralConstants.DOT), episodeName.length());
    }

}
