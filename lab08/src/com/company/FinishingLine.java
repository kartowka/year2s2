package com.company;

public class FinishingLine {
    private int count;
    public FinishingLine(){
        count=1;
    }
    public void arrive(String name){
        if (count == 1){
            System.out.println("====> " + name + " finished first!");
            count++;
        }
        else{
            System.out.println("====> " + name + " finished on " + count++ + "-th place");
        }
    }
}
