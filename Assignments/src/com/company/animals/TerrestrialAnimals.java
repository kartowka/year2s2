/**
 * Name: Anthony Eitan Fleysher , ID: 203192331.
 * TerrestrialAnimals Class Def.
 */
package com.company.animals;

import com.company.mobility.Point;

public class TerrestrialAnimals extends Animal {
    /**    class TerrestrialAnimals extends Animal
     *  **/
    private int noLegs;
    Point defaultPosition= new Point(0,20);
    static final int MIN_LEGS=0;
    public TerrestrialAnimals(int noLegs){
        /** Constructor TerrestrialAnimal
         * @param noLegs (int)
         */
        super();
        super.setCategory("Terrestrial Animal");
        this.setNoLegs(noLegs);
        this.setPosition(defaultPosition);
        this.setLocation(defaultPosition);
    }

    public int getNoLegs() {
        /** getter noLegs
         *  **/
        return noLegs;
    }

    public boolean setNoLegs(int noLegs) {
        /** setter noLegs
         * **/
        if(noLegs<MIN_LEGS){return false;}
        this.noLegs=noLegs;
        return true;
    }

    @Override
    public String toString() {
        /** returns TerrestrialAnimal fields as string
         * **/
        return super.toString()+"Number of legs: "+this.getNoLegs()+"\n"+this.getClass().getSimpleName()+" default position: "+this.defaultPosition+"\n";
    }
}
