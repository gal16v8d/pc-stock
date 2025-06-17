package com.gsdd.pcstock.util;

import com.gsdd.pcstock.model.GeneralFile;
import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import org.apache.commons.io.FileUtils;

public class GeneralFileDirectoryHelper extends AbstractDirectoryHelper<GeneralFile> {

  @Override
  public List<GeneralFile> traverseDirectories(String[] filters, List<File> directories) {
    List<GeneralFile> fileList = new CopyOnWriteArrayList<>();
    try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
      directories.forEach(
          directory ->
              executor.submit(
                  () -> {
                    Collection<File> filteredFiles = FileUtils.listFiles(directory, filters, true);
                    if (!filteredFiles.isEmpty()) {
                      fileList.add(
                          GeneralFile.builder()
                              .name(directory.getName())
                              .quantity((long) filteredFiles.size())
                              .build());
                    }
                  }));
    }
    return fileList;
  }

  @Override
  public Long extractAccountField(GeneralFile pcStockFile) {
    return pcStockFile.getQuantity();
  }
}
