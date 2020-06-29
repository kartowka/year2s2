/**
 * Name: Anthony Eitan Fleysher , ID: 203192331.
 * AirAnimal Class Def.
 */
package com.company.animals;
import com.company.mobility.Point;

public class AirAnimal extends Animal {
    /** AirAnimal Class extend Animal Abstract class
     * */
    private double wingspan;
    private Point defaultPosition=new Point(0,100);
    static final double MIN_WINGSPAN=0;
    public AirAnimal(double wingspan){
        /** Constructor AirAnimal
         * @param wingspan (double)
         * */
        super();
        this.setWingspan(wingspan);
        super.setCategory("Air Animal");
        this.setLocation(defaultPosition);
        this.setPosition(defaultPosition);
    }
    public double getWingspan() {
        /** Getter wingspan
         *  */
        return wingspan;
    }

    public boolean setWingspan(double wingspan) {
        /** Setter wingspan
         * @return true/false operation complete
         * */
        if(wingspan<MIN_WINGSPAN){return false;}
        this.wingspan=wingspan;
        return true;
    }

    @Override
    public String toString() {
        /** @return all fields as a string
         * */
        return super.toString()+"Wingspan: " + this.getWingspan()+"\n"+this.getClass().getSimpleName()+" default position: "+this.defaultPosition+"\n";
    }
}
