package com.zeta.innerclass.statik;

public class StaticInnerClassDemo {

    public static void main(String[] args) {
        double[] d = new double[20];
        for (int i = 0; i < d.length; i++)
            d[i] = 100 * Math.random();
        ArrayAlg.Pair p = ArrayAlg.minmax(d);
        System.out.println("min = " + p.getFirst());
        System.out.println("max = " + p.getSecond());
    }
}

class ArrayAlg {

    static class Pair {
        private double first;
        private double second;

        Pair(double f, double s) {
            first = f;
            second = s;
        }

        double getFirst() {
            return first;
        }

        double getSecond() {
            return second;
        }
    }

    static Pair minmax(double[] values) {
        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;
        for (double v : values) {
            if (min > v) min = v;
            if (max < v) max = v;
        }
        return new Pair(min, max);
    }
}
