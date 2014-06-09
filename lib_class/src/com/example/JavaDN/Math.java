package com.example.JavaDN;

/**
 * Sum: number object.
 */
public class Math implements IMath {

    public Math() {
    }

    public static void main(String[] args) {
        System.out.println("com.example.JavaDN.Math");
    }

    public static boolean gte(int a, int b) {
        return a > b;
    }

    @Override
    public int sum(int a, int b) {
        return a + b;
    }

    @Override
    public int diff(int a, int b) {
        return a - b;
    }
}
