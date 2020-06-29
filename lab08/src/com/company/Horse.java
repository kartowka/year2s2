package com.company;

public class Horse implements Runnable{
    private String id;
    private FinishingLine f;
    private int distance;
    public Horse(String id,FinishingLine f,int distance){
        this.id=id;
        this.f=f;
        this.distance=distance;
    }
    public void run(){
        while(distance>0) {
            System.out.println(id + ":\t " + distance + " meters to finish");
            distance -= 20;
            try {
                Thread.sleep((int)(Math.random() * 1000));
            }
            catch (InterruptedException e){}
        }
        f.arrive(id);
    }
    public String getId(){return id;}
}
