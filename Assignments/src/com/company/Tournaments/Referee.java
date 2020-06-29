package com.company.Tournaments;

import javax.swing.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Referee implements Runnable {
    private String name;
    private Scores score;
    private final AtomicBoolean finishFlag;


    public Referee(String name, Scores score, AtomicBoolean flag) {
        this.name = name;
        this.score = score;
        this.finishFlag = flag;
    }
    @Override
    public void run() {
        synchronized (this.finishFlag) {
            while (!finishFlag.get()) {
                try {
                    finishFlag.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.score.add(name);
            finishFlag.notifyAll();
        }
    }
}
