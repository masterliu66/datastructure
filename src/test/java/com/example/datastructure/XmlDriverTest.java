package com.example.datastructure;

import org.apache.ibatis.builder.MapperBuilderAssistant;
import org.apache.ibatis.builder.xml.XMLIncludeTransformer;
import org.apache.ibatis.builder.xml.XMLMapperEntityResolver;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.parsing.XNode;
import org.apache.ibatis.parsing.XPathParser;
import org.apache.ibatis.scripting.LanguageDriver;
import org.apache.ibatis.scripting.xmltags.XMLLanguageDriver;
import org.apache.ibatis.session.Configuration;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XmlDriverTest {

    public static void main(String[] args) throws IOException {

        String xmlName = "personRepository.xml";
        String methodName = "selectOne";
        Map<String, Object> parameterObject = new HashMap<>();

        Configuration configuration = new Configuration();
        LanguageDriver langDriver = new XMLLanguageDriver();
        URL url = ResourceUtils.getURL("classpath:mapper/" + xmlName);
        InputStream inputStream = url.openStream();
        MapperBuilderAssistant builderAssistant = new MapperBuilderAssistant(configuration, url.getPath());;
        builderAssistant.setCurrentNamespace("");
        XPathParser parser = new XPathParser(inputStream, true, configuration.getVariables(), new XMLMapperEntityResolver());
        XNode context = parser.evalNode("/mapper");
        List<XNode> list = context.evalNodes("/mapper/sql");
        for (XNode node : list) {
            String id = node.getStringAttribute("id");
            id = builderAssistant.applyCurrentNamespace(id, false);
            if (!configuration.getSqlFragments().containsKey(id)) {
                configuration.getSqlFragments().put(id, node);
            }
        }
        XMLIncludeTransformer includeParser = new XMLIncludeTransformer(configuration, builderAssistant);
        includeParser.applyIncludes(context.getNode());
        List<XNode> nodes = context.evalNodes("select|insert|update|delete");
        for (XNode node : nodes) {
            if (!node.getStringAttribute("id").equals(methodName)) {
                continue;
            }
            Class<Object> parameterTypeClass = configuration.getTypeAliasRegistry().resolveAlias(node.getStringAttribute("parameterType"));
            SqlSource sqlSource = langDriver.createSqlSource(configuration, node, parameterTypeClass);
            System.out.println(sqlSource.getBoundSql(parameterObject).getSql());
        }

    }

}
