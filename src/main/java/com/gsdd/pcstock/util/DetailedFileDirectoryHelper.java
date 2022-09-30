package com.gsdd.pcstock.util;

import com.gsdd.pcstock.model.DetailedFile;
import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

@Setter
@Getter
@Slf4j
public class DetailedFileDirectoryHelper extends AbstractDirectoryHelper<DetailedFile> {

  private boolean withResolution;

  @Override
  public List<DetailedFile> traverseDirectories(String[] filters, List<File> directories) {
    List<DetailedFile> fileList = new CopyOnWriteArrayList<>();
    directories.parallelStream()
        .forEach(
            directory ->
                FileUtils.listFiles(directory, filters, true).stream()
                    .forEach(
                        file -> {
                          log.debug("fp > {}", file.getAbsolutePath());
                          fileList.add(
                              DetailedFile.builder()
                                  .name(file.getName())
                                  .size(file.length())
                                  .resolution(extractResolution(file.getAbsolutePath()))
                                  .build());
                        }));
    Collections.sort(fileList);
    return fileList;
  }

  private String extractResolution(String path) {
    String resolucion = "0x0";
    if (withResolution) {
      try {
        resolucion = VideoPropertiesUtil.getFileResolutionXuggler(path);
      } catch (Exception e) {
        log.warn("Error during extract resolution for: {}", path, e);
      }
    }
    return resolucion;
  }

  @Override
  public Long extractAccountField(DetailedFile pcStockFile) {
    return pcStockFile.getSize();
  }
}
