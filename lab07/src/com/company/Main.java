package com.company;

import java.io.File;
import java.util.Scanner;

public class Main {
    final static String dir = System.getProperty("user.dir");
    final static String file_name = dir+"/src/com/company/data.dat";

    public static void main(String [] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Do you want to delete the old ranking (y/n)?");
        String input = in.next();
        if(input.equals("y") || input.equals("Y"))
            new File(file_name).delete();
        in.close();

        String [] names = {"Australian Open", "French Open", "Wimbledon", "USA Open", "ATP final"};
        for(int i=0; i<100; i++)
        {
            for(int j=0; j<names.length; j++)
            {
                Tournament t = new Tournament(names[j],file_name);
                t.simulateTournament();
                t.printPlayers();
            }
        }
    }
}