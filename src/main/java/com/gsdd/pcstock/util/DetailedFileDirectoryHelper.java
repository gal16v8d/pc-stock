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
    directories.forEach(
        directory ->
            FileUtils.listFiles(directory, filters, true).parallelStream()
                .forEach(
                    file -> fileList.add(fromFile(file))));
    Collections.sort(fileList);
    return fileList;
  }

  private DetailedFile fromFile(File file) {
    log.debug("current file > {}", file.getAbsolutePath());
    return DetailedFile.builder()
        .name(file.getName())
        .size(file.length())
        .resolution(extractResolution(file.getAbsolutePath()))
        .build();
  }

  private String extractResolution(String path) {
    String resolucion = "0x0";
    if (withResolution) {
      try {
        resolucion = new VideoPropertiesUtil().getFileResolutionXuggler(path);
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
