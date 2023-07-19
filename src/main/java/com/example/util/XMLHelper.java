package com.example.util;

import org.apache.ibatis.builder.BuilderException;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class XMLHelper {

    private XMLHelper() {}

    /** XML文件转XML文档对象 */
    public static Document xmlFileToDocument(File file) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setValidating(false);
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputStream inputStream = Files.newInputStream(file.toPath());
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            return db.parse(new InputSource(reader));
        } catch (Exception e) {
            throw new BuilderException("Error creating document instance.  Cause: " + e, e);
        }
    }

    /** XML文档转XML字符串 */
    public static String docToString(Document doc) {
        TransformerFactory ft = TransformerFactory.newInstance();
        try {
            Transformer ff = ft.newTransformer();
            ff.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            ff.setOutputProperty("encoding", "utf-8");
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ff.transform(new DOMSource(doc), new StreamResult(bos));
            return bos.toString();
        } catch (TransformerException e) {
            throw new BuilderException("Error transfer document.  Cause: " + e, e);
        }
    }

    public static void write(Document doc, File dest) {
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(new DOMSource(doc), new StreamResult(dest));
        } catch (Exception e) {
            throw new BuilderException("Error transfer document.  Cause: " + e, e);
        }
    }

}