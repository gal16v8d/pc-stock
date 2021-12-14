package com.gsdd.pcstock.util;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import com.gsdd.pcstock.model.CompareFile;
import com.gsdd.pcstock.model.PCStockFile;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractDirectoryHelper<T extends PCStockFile> {

  public abstract List<T> traverseDirectories(String[] filters, List<File> directories);

  public abstract Long extractAccountField(T pcStockFile);

  public List<CompareFile> getCompareFileList(String[] filters, File mainDir, File secDir) {
    List<CompareFile> fileList = new ArrayList<>();
    try {
      List<T> filesOnMain = getFileList(filters, mainDir);
      List<T> filesOnSec = getFileList(filters, secDir);
      fileList = iterateCompareFileList(new ArrayList<>(filesOnMain), new ArrayList<>(filesOnSec));
    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }
    Collections.sort(fileList);
    return fileList;
  }

  public List<T> getFileList(String[] filters, File dir) {
    List<T> fileList = new ArrayList<>();
    if (dir != null) {
      List<File> directoryList = filterDirectories(dir);
      if (directoryList.isEmpty()) {
        fileList = traverseDirectories(filters, Arrays.asList(dir));
      } else {
        fileList = traverseDirectories(filters, directoryList);
      }
    }
    Collections.sort(fileList);
    return fileList;
  }

  private List<File> filterDirectories(File dir) {
    return Optional.ofNullable(dir)
        .map(directory -> directory.listFiles((FileFilter) DirectoryFileFilter.INSTANCE))
        .map(Stream::of).orElseGet(Stream::empty).collect(Collectors.toList());
  }

  private List<CompareFile> iterateCompareFileList(List<T> cL1, List<T> cL2) {
    List<CompareFile> compareList = new ArrayList<>();
    if (!cL1.isEmpty()) {
      Iterator<T> iter = cL1.iterator();
      while (iter.hasNext()) {
        T tmp = iter.next();
        int pos = cL2.indexOf(tmp);
        CompareFile a = new CompareFile();
        a.setName(tmp.getName());
        a.setOnMain(extractAccountField(tmp));
        if (pos != -1) {
          a.setOnSecondary(extractAccountField(cL2.get(pos)));
          cL2.remove(pos);
        } else {
          a.setOnSecondary(0L);
        }
        a.setDifferent((a.getOnMain() - a.getOnSecondary()) != 0);
        iter.remove();
        compareList.add(a);
      }
    }
    for (T psStockFile : cL2) {
      compareList
          .add(new CompareFile(psStockFile.getName(), 0L, extractAccountField(psStockFile), true));
    }
    return compareList;
  }

}
