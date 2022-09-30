package com.gsdd.pcstock.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@Slf4j
class RenameUtilTest {

  @TempDir public Path path;

  @BeforeEach
  void setUp() {
    try {
      initDir();
    } catch (Exception e) {
      log.error("Error on setUp", e);
    }
  }

  private void initDir() throws IOException {
    File file = path.toFile();
    for (int i = 0; i < 13; i++) {
      File f = new File(file.getAbsolutePath(), "test " + i + ".txt");
      f.createNewFile();
    }
  }

  @ParameterizedTest
  @CsvSource({"txt,renamed", "mp4,renamed"})
  void renameAllFilesOnDirTest(String filter, String newName) {
    Assertions.assertTrue(
        RenameUtil.renameAllFilesOnDir(path.toFile(), new String[] {filter}, newName));
  }
}
