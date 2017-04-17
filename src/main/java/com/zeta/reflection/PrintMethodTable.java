package com.zeta.reflection;

import java.lang.reflect.Method;

/**
 * Created by Zeta on 2017/4/17.
 */
public class PrintMethodTable {

    public static void main(String[] args) throws Exception {
        Method square = PrintMethodTable.class.getMethod("square", double.class);
        Method sqrt = Math.class.getMethod("sqrt", double.class);
        printTable(1, 10, 10, square);
        printTable(1, 10, 10, sqrt);
    }

    public static double square(double x) {
        return x * x;
    }

    private static void printTable(double from, double to, int n, Method f) {
        //以对象描述作为表头
        System.out.println(f);
        double dx = (to - from) / (n - 1);
        for (double x = from; x <= to; x += dx) {
            try {
                double y = (Double) f.invoke(null, x);
                System.out.printf("%10.4f | %10.4f%n", x, y);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
