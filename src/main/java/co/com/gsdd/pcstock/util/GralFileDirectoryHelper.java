package co.com.gsdd.pcstock.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;

import co.com.gsdd.pcstock.model.GralFile;

public class GralFileDirectoryHelper extends AbstractDirectoryHelper<GralFile> {

    @Override
    public List<GralFile> traverseDirectories(String[] filters, List<File> directories) {
        List<GralFile> fileList = new ArrayList<>();
        for (File directory : directories) {
            Collection<File> filteredFiles = FileUtils.listFiles(directory, filters, true);
            if (!filteredFiles.isEmpty()) {
                fileList.add(
                        GralFile.builder().name(directory.getName()).quantity((long) filteredFiles.size()).build());
            }
        }
        return fileList;
    }

    @Override
    public Long extractAccountField(GralFile pcStockFile) {
        return pcStockFile.getQuantity();
    }

}
