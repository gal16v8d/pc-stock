package com.gsdd.pcstock.util;

import com.gsdd.pcstock.model.GralFile;
import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.apache.commons.io.FileUtils;

public class GralFileDirectoryHelper extends AbstractDirectoryHelper<GralFile> {

  @Override
  public List<GralFile> traverseDirectories(String[] filters, List<File> directories) {
    List<GralFile> fileList = new CopyOnWriteArrayList<>();
    directories.parallelStream()
        .forEach(
            (File directory) -> {
              Collection<File> filteredFiles = FileUtils.listFiles(directory, filters, true);
              if (!filteredFiles.isEmpty()) {
                fileList.add(
                    GralFile.builder()
                        .name(directory.getName())
                        .quantity((long) filteredFiles.size())
                        .build());
              }
            });
    return fileList;
  }

  @Override
  public Long extractAccountField(GralFile pcStockFile) {
    return pcStockFile.getQuantity();
  }
}
