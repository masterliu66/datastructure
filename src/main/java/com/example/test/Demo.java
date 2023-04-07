package com.example.test;

import io.vavr.*;
import io.vavr.control.Option;
import static io.vavr.API.*;

import java.util.*;
import java.util.function.Predicate;

/**
 * className Demo
 * packageName com.example.test
 * description
 *
 * @author CCC
 * @date 2020/12/14 22:22
 */
public class Demo {

    public static void main(String[] args){

        String msg = """
                H053.0000017200226CQWHHCN 3CN6270 200226XTA   15619XTA   014000006                                                             \s
                Q20A6CNXTA   XTA0684         CRCVConfirmation  of received claim                                                               \s
                Q21A6CNXTA   XTA0684         01CRCVConfirmation  of received claim                                                             \s
                Q22A6CNXTA   XTA0684         0100136004 CRCVConfirmation  of received claim                                                    \s
                Q23A6CNXTA   XTA0684         0100100000000000000000000000+00000000000+                                                         \s
                Q23A6CNXTA   XTA0685         0100100000000000000000000000+00000000000+                                                         \s
                Q23A6CNXTA   XTA0685         0100100000000000000000000000+00000000000+                                                         \s
                Q23A6CNXTA   XTA0685         0100100000000000000000000000+00000000000+                                                         \s
                Q23A6CNXTA   XTA0685         0100100000000000000000000000+00000000000+                                                         \s
                Q24A6CNXTA   XTA0684         01001000000031676056CRCVConfirmation  of received claim                                           \s
                Q25A6CNXTA   XTA0684         01001000000000000000000000+00000000000+                                                           \s
                H993.00000615619
                """;

//        System.out.println(msg);

        var tuple = Tuple.of(1, "s", true);
        System.out.println(tuple._1);
        System.out.println(tuple._2);
        System.out.println(tuple._3);

        var function = Function2.of(Integer::sum);

        System.out.println(function.apply(1).apply(2));
        System.out.println(function.apply(1, 2));

        var option = Option.of(null);
        int i = 1;
        String a = Match(i).of(
                Case($(is(1)), "one"),
                Case($(is(2)), "two"),
                Case($(), "?")
        );

        System.out.println(a);
    }

    private static Predicate<Integer> is(int i) {
        return t -> t == i;
    }
}

