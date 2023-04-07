package com.example.test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * className JsTest
 * packageName com.example.test
 * description
 *
 * @author CCC
 * @date 2020/11/23 23:17
 */
public class JsTest {

    private static final String util = "var ByteArrayHexer = {\n" +
            "\t/*\n" +
            "\t * Convert a hex to byte array.\n" +
            "\t */\n" +
            "\tparse : function(str) {\n" +
            "\t\tif (!(str && ((str.length) % 2) == 0)) {\n" +
            "\t\t\treturn null;\n" +
            "\t\t}\n" +
            "\t\tvar len = str.length / 2;\n" +
            "\t\tvar arr = new Array(len);\n" +
            "\t\ttry {\n" +
            "\t\t\tfor (var i = 0, pos = 0; i < len; i++, pos += 2) {\n" +
            "\t\t\t\tvar s = str.substr(pos, 2);\n" +
            "\t\t\t\tvar v = parseInt(s, 16);\n" +
            "\t\t\t\tarr[i] = v;\n" +
            "\t\t\t}\n" +
            "\t\t} catch (e) {\n" +
            "\t\t\treturn null;\n" +
            "\t\t}\n" +
            "\t\treturn arr;\n" +
            "\t},\n" +
            "\t/*\n" +
            "\t * Convert a byte array to hex.\n" +
            "\t */\n" +
            "\tformat : function(arr) {\n" +
            "\t\tvar str = \"\";\n" +
            "\t\ttry {\n" +
            "\t\t\tfor (var i = 0; i < arr.length; i++) {\n" +
            "\t\t\t\tvar tmp = (arr[i] & 0Xff).toString(16);\n" +
            "\t\t\t\tif (tmp.length == 1) {\n" +
            "\t\t\t\t\ttmp = \"0\" + tmp;\n" +
            "\t\t\t\t}\n" +
            "\t\t\t\tstr += tmp;\n" +
            "\t\t\t}\n" +
            "\t\t} catch (e) {\n" +
            "\t\t\treturn null;\n" +
            "\t\t}\n" +
            "\t\treturn str;\n" +
            "\t}\n" +
            "}";

    public static void main(String[] args) {

        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine nashorn = scriptEngineManager.getEngineByName("nashorn");

        try {
            nashorn.eval(util);
            nashorn.eval("print(ByteArrayHexer.parse('010204'))");

        } catch(ScriptException e){
            System.out.println("执行脚本错误: "+ e.getMessage());
        }
    }


}
