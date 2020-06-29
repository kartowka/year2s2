/**
 * Name: Anthony Eitan Fleysher , ID: 203192331.
 * WaterAnimal Class Def.
 */
package com.company.animals;

import com.company.mobility.Point;

public class WaterAnimal extends Animal {
    /** class WaterAnimal extends Animal
     *
     */
    private static final int MAX_DIVE = -800;
    private static final int MIN_DIVE = 0;
    private double diveDept=MIN_DIVE;
    private Point defaultPosition= new Point(50,0);

    public WaterAnimal(double diveDept){
        /** Constructor WaterAnimal
         * @param diveDept (double)
          */
        super();
        this.Dive(diveDept);
        super.setCategory("Water Animal");
        this.setLocation(defaultPosition);
        this.setPosition(defaultPosition);
    }
    public boolean Dive(double dept){
        /** setter dive
         * **/
        if (dept> MAX_DIVE && dept <= MIN_DIVE){
            this.diveDept+=dept;
            return true;
        }
        return false;
    }
    @Override
    public String toString() {
        /** returns WaterAnimal fields as string
         * **/
        return super.toString()+"Max dive: " + MAX_DIVE+"\n"+"Current dive dept: "+this.diveDept+"\n"+this.getClass().getSimpleName()+" default position: "+this.defaultPosition+"\n";
    }
}
