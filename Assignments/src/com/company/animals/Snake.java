/**
 * Name: Anthony Eitan Fleysher , ID: 203192331.
 * Snake Class Def.
 */
package com.company.animals;

public class Snake extends TerrestrialAnimals implements IReptile{
    /** class Snake extends TerrestrialAnimals implements IReptile
     * **/
    enum Poisonous{LOW, MEDIUM, HIGH};
    Poisonous _poisonous;
    private double length;
    private final String uniqueSound = "ssssssss";
    static final double MIN_LENGTH=0;
    public Snake(String poisonous,double length,int noLegs){
        /** Constructor Snake
         * @param poisonous (string)
         * @param length (double)
         * @param noLegs (int)
         * **/
        super(noLegs);
        this.setPoisonous(poisonous);
        this.setLength(length);
        this.setUniqueSound(uniqueSound);
    }
    public double getLength() {
        /** Getter length
         * **/
        return length;
    }

    public boolean setLength(double length) {
        /** Setter length
         * **/
        if(length<MIN_LENGTH){
            return false;
        }
        this.length=length;
        return true;
    }

    public Poisonous getPoisonous() {
        /** getter poisonous
         * **/
        return _poisonous;
    }

    public boolean setPoisonous(String poisonous) {
        /** Setter poisonous
         * **/
        if (poisonous.equals("low")) {
            this._poisonous= Poisonous.LOW ;
            return true;
        }
        if (poisonous.equals("medium")) {
            this._poisonous= Poisonous.MEDIUM;
            return true;
        }
        if (poisonous.equals("high")) {
            this._poisonous = Poisonous.HIGH;
            return true;
        }
        return false;
    }
    public boolean speedUp(int speed) {
        /** Setter speed
         * **/
        if(Math.abs(speed)<=MAX_SPEED){
            this.setSpeed(speed);
            return true;
        }
        return false;
    }

    public String toString() {
        /** returns Snake details as string
         * **/
        return super.toString()+"Length: "+this.getLength()+"\n"+"Number of legs: "+this.getNoLegs()+"\n"+"Poisonous: "+this.getPoisonous()+"\n"+this.getClass().getSimpleName()+" uniqueSound: "+this.getUniqueSound()+"\n";

    }
}