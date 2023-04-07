package com.example.datastructure;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.Statements;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectBody;

public class InterceptorTest {

    public static void main(String[] args) throws JSQLParserException {
        String sql = "WITH test AS (SELECT * FROM user) " +
                     "SELECT * FROM test " +
                     "LEFT JOIN emp ON test.id = emp.user_id " +
                     "WHERE id = 1";
        Statements statements = CCJSqlParserUtil.parseStatements(sql);
        for (Statement statement : statements.getStatements()) {
            System.out.println(statement);
            SelectBody selectBody = ((Select) statement).getSelectBody();
            System.out.println(selectBody);
        }
    }

}
