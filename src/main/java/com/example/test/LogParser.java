package com.example.test;

import java.io.*;

/**
 * className LogParser
 * packageName com.example.test
 * description
 *
 * @author CCC
 * @date 2020/11/30 21:40
 */
public class LogParser {

    public static void main(String[] args) throws IOException {

        String path = "C:\\Users\\CCC\\Desktop\\application.2020-11-28.1.log";

        String dest = "C:\\Users\\CCC\\Desktop\\application.2020-11-28.1.log.parse3";

        BufferedReader reader = new BufferedReader(new FileReader(path));

        BufferedWriter writer = new BufferedWriter(new FileWriter(dest));

        String line;

        while ((line = reader.readLine()) != null) {

            if (line.contains("SignalUpdated")) {
                writer.write(line);
                writer.write("\r\n");
            }
        }

        writer.flush();

        reader.close();
        writer.close();
    }

}
