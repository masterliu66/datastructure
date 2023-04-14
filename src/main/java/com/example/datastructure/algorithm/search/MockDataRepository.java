package com.example.datastructure.algorithm.search;

import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class MockDataRepository {

    public static final String FOLDER_PATH = "D:/tmp/data/sort/";

    public static final String FILE_PATH_PREFIX = FOLDER_PATH + "file";

    public static final String FILE_PATH_SUFFIX = ".csv";

    public static final String INDEX_PATH_PREFIX = FOLDER_PATH + "index/";

    public static final String INDEX_PATH_SUFFIX = ".txt";

    public String findById(int id) {
        String[] rows = findByPartition(id / DataGenerator.DATA_SIZE);
        if (rows.length <= id % DataGenerator.DATA_SIZE) {
            return null;
        }
        return rows[id % DataGenerator.DATA_SIZE];
    }

    public List<String> listByPartition(int partition) {
        return Arrays.stream(findByPartition(partition)).collect(Collectors.toList());
    }

    private String[] findByPartition(int partition) {
        String[] rows = null;
        try (FileReader reader = new FileReader(FILE_PATH_PREFIX + partition + FILE_PATH_SUFFIX)) {
            String result = FileCopyUtils.copyToString(reader);
            rows = result.split("\r\n");
        } catch (IOException e) {
            return new String[0];
        }
        return rows;
    }

    public void writeToPartition(String result, int partition) {
        try {
            FileCopyUtils.copy(result, new FileWriter(FILE_PATH_PREFIX + partition + FILE_PATH_SUFFIX));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String findByIndexName(String indexName) {
        String result = null;
        try {
            result = FileCopyUtils.copyToString(new FileReader(INDEX_PATH_PREFIX + indexName + INDEX_PATH_SUFFIX));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void writeIndex(Supplier<String> supplier, String indexName) {
        String path = INDEX_PATH_PREFIX + indexName + INDEX_PATH_SUFFIX;
        File file = new File(path);
        if (file.exists()) {
            return;
        }
        try {
            FileCopyUtils.copy(supplier.get(), new FileWriter(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
