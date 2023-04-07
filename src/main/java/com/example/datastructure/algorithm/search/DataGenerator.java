package com.example.datastructure.algorithm.search;

import net.datafaker.providers.base.Name;
import net.datafaker.transformations.Schema;
import org.springframework.util.FileCopyUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DataGenerator {

    public static void main(String[] args) {
        new DataGenerator().generateSorted();
    }

    public void generateSorted() {
        CsvTransformer<Name> transformer = CsvTransformer.<Name>builder().header(false).separator(",").build();
        // Schema for from scratch
        Schema<Name, String> fromScratch = new SchemaFactory().newSchema();
        for (int i = 0; i < 100; i++) {
            System.out.println("开始执行第" + i + "个任务");
            String result = transformer.generate(fromScratch, 10000);
            System.out.println("第" + i + "个任务数据准备完成");
            try {
                FileCopyUtils.copy(result, new FileWriter("D:/tmp/data/sort/file" + i + ".csv"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("第" + i + "个任务执行完成");
        }
    }

    public void generate() {
        CsvTransformer<Name> transformer = CsvTransformer.<Name>builder().header(false).separator(",").build();
        // Schema for from scratch
        Schema<Name, String> fromScratch = new SchemaFactory().newSchema();
        ExecutorService executorService = Executors.newFixedThreadPool(16);
        for (int i = 0; i < 100; i++) {
            final int index = i;
            executorService.execute(() -> {
                System.out.println("开始执行第" + index + "个任务");
                String result = transformer.generate(fromScratch, 10000);
                System.out.println("第" + index + "个任务数据准备完成");
                try {
                    FileCopyUtils.copy(result, new FileWriter("D:/tmp/data/file" + index + ".csv"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("第" + index + "个任务执行完成");
            });
        }
        executorService.shutdown();
    }

}
