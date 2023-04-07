package com.example.datastructure.tree.huffman;

import java.io.*;
import java.util.Map;

/**
 * className FileZipTest
 * packageName com.example.datastructure.tree.huffman
 * description
 *
 * @author CCC
 * @date 2020/12/14 23:11
 */
public class FileZipTest {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        testZip();

        testUnzip();

    }

    public static void testZip() throws IOException {

        String path = "D:/111111.jpg";

        String dest = "D:/111111.zip";

        try (InputStream is = new FileInputStream(new File(path));
             OutputStream os = new FileOutputStream(new File(dest));
             ObjectOutputStream oos = new ObjectOutputStream(os)) {
            byte[] bytes = new byte[is.available()];
            is.read(bytes);
            HuffmanCoding.HuffmanCodingEncoder encoder = new HuffmanCoding.HuffmanCodingEncoder(bytes);
            byte[] encodeBytes = encoder.encode();
            oos.writeObject(encoder.getHuffmanCodesTable());
            oos.writeInt(encoder.getLength());
            oos.writeObject(encodeBytes);
            oos.flush();
        }
    }

    public static void testUnzip() throws IOException, ClassNotFoundException {

        String path = "D:/111111.zip";

        String dest = "D:/1111112.jpg";

        try (InputStream is = new FileInputStream(new File(path));
             ObjectInputStream ois = new ObjectInputStream(is);
             OutputStream os = new FileOutputStream(new File(dest))) {

            Map<Byte, String> huffmanCodesTable = (Map<Byte, String>) ois.readObject();
            int length = ois.readInt();
            byte[] codingBytes = (byte[]) ois.readObject();

            byte[] decodeBytes = new HuffmanCoding.HuffmanCodingDecoder(codingBytes, huffmanCodesTable, length).decode();

            os.write(decodeBytes);
            os.flush();
        }

    }

}
