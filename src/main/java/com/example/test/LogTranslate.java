package com.example.test;

import java.io.*;

/**
 * className LogTranslate
 * packageName com.example.test
 * description
 *
 * @author CCC
 * @date 2020/11/30 21:49
 */
public class LogTranslate {

    public static void main(String[] args) throws IOException {

        String path = "C:\\Users\\CCC\\Desktop\\application.2020-11-28.1.log.parse2";

        String dest = "C:\\Users\\CCC\\Desktop\\application.2020-11-28.1.log.parse2.parse";

        BufferedReader reader = new BufferedReader(new FileReader(path));

        BufferedWriter writer = new BufferedWriter(new FileWriter(dest));

        String line;

        while ((line = reader.readLine()) != null) {

            String time = line.substring(0, "2020-11-28 13:36:13.188".length());

            int index = line.indexOf("nodeSpecType\":\"");
            String type = line.substring(index + 15, index + 15 + 8);
            int index1 = line.indexOf("nodeIp\":\"");
            String nodeIp = line.substring(index1 + 9, index1 + 9 + 15);
            nodeIp = nodeIp.substring(0, nodeIp.indexOf("\""));

            writer.write(time + "\t" + nodeIp + "\t" + type + "\t节点离线");
            writer.write("\r\n");
        }

        writer.flush();

        reader.close();
        writer.close();
    }

}
