package com.example.datastructure;

import org.springframework.util.FileCopyUtils;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SqlDemo {

    public static void main(String[] args) throws IOException {

        String sqlPath = "C:/Users/CCC/Desktop/HuaWei/adb/sql/test.txt";

        String sql = FileCopyUtils.copyToString(new FileReader(sqlPath));
        String params = "SHJ(String), 2023-04-01(String), 2023-04-30(String), SHJ(String), 2023-04-01(String), 2023-04-30(String), 2023-04-30(String), 2023-04-01(String), 2023-04-30(String), SHJ(String), SHJ(String), 2023-04-01(String), 2023-04-30(String), 2023-04-01(String), 2023-04-30(String), 2023-04-01(String), SHJ(String), 2023-04-01(String), 2023-04-30(String), SHJ(String), 2023-04-30(String), 2023-04-01(String), 2023-04-01(String), SHJ(String), 2023-04-01(String), 2023-04-30(String), 2023-04-30(String), 225(String), 225(String), SHJ(String), 2023-04-01(String), 2023-04-30(String)";

        List<String> paramList = Arrays.stream(params.split(",")).map(param -> {
            if ("null".equalsIgnoreCase(param.trim())) {
                return "null";
            }
            int index = param.indexOf("(");
            return param.substring(0, index).trim();
        }).collect(Collectors.toList());

        StringBuilder builder = new StringBuilder();
        int paramIndex = 0;
        for (int i = 0; i < sql.length(); i++) {
            char c = sql.charAt(i);
            if (c == '?') {
                builder.append("'").append(paramList.get(paramIndex++)).append("'");
            } else {
                builder.append(c);
            }
        }

        FileCopyUtils.copy(builder.toString(), new FileWriter(sqlPath));
    }

}
