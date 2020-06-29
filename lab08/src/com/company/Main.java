package com.company;

public class Main {
    public final static int dist = 500;

    public static void main(String[] args) {
        String[] names = {"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten"};
        FinishingLine f = new FinishingLine();
        Horse[] horses = new Horse[names.length];
        for (int i = 0; i < names.length; i++) {
            horses[i] = new Horse(names[i], f, dist);
            (new Thread(horses[i])).start();
        }
    }
}
