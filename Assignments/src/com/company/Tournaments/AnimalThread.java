package com.company.Tournaments;

import com.company.animals.Animal;

import java.util.Random;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicBoolean;

public class AnimalThread implements Runnable {
    private Animal participant;
    private double neededDistance;
    private final AtomicBoolean startFlag;
    private final AtomicBoolean finishFlag;
    private static final int MAX_SLEEP_TIME = 1000;

    public AnimalThread(Animal animal, AtomicBoolean startFlag, AtomicBoolean finishFlag, Double neededDistance) {
        this.participant = animal;
        this.startFlag = startFlag;
        this.finishFlag = finishFlag;
        this.neededDistance = neededDistance;
    }
    public double getNeededDistance() {
        return neededDistance;
    }

    public boolean setNeededDistance(double neededDistance) {
        this.neededDistance = neededDistance;
        return true;
    }
    public AtomicBoolean getStartFlag() {
        return startFlag;
    }


    public AtomicBoolean getFinishFlag() {
        return finishFlag;
    }

    @Override
    public void run() {
        synchronized (this.startFlag) {
            while (!this.startFlag.get()) {
                try {
                    startFlag.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        synchronized (this.finishFlag) {
            while (participant.getTotalDistance() <= neededDistance) {
                if (participant.getOrientation().equals("EAST")) {
                    participant.toEAST();
                } else if (participant.getOrientation().equals("SOUTH")) {
                    participant.toSOUTH();
                } else if (participant.getOrientation().equals("WEST")) {
                    participant.toWEST();
                } else if (participant.getOrientation().equals("NORTH")) {
                    participant.toNORTH();
                }
                participant.setCurrentEnergy();
                participant.setEnergyConsumption();
                participant.getPan().repaint();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                while (participant.getCurrentEnergy() == 0) {
                    if (participant.getCurrentEnergy() >= 0) {
                        finishFlag.notify();
                    } else {
                        try {
                            finishFlag.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            finishFlag.compareAndSet(false, true);
            finishFlag.notifyAll();
        }
        if (Thread.interrupted()) {
            try {
                throw new InterruptedException();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
