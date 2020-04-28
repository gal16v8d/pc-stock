package co.com.gsdd.pcstock.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.FileUtils;

import co.com.gsdd.pcstock.model.DetailedFile;

public class DetailedFileDirectoryHelper extends AbstractDirectoryHelper<DetailedFile> {

    @Override
    public List<DetailedFile> traverseDirectories(String[] filters, List<File> directories) {
        List<DetailedFile> fileList = new ArrayList<>();
        for (File directory : directories) {
            Collection<File> filteredFiles = FileUtils.listFiles(directory, filters, true);
            for (File file : filteredFiles) {
                fileList.add(DetailedFile.builder().name(file.getName()).size(file.length()).build());
            }
        }
        Collections.sort(fileList);
        return fileList;
    }

    @Override
    public Long extractAccountField(DetailedFile pcStockFile) {
        return pcStockFile.getSize();
    }

}
