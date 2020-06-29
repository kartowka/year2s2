/**
 * Name: Anthony Eitan Fleysher , ID: 203192331.
 * Alligator Class Def.
 */
package com.company.animals;

public class Alligator extends WaterAnimal implements IReptile{
    /** Class Aligator extends WatarAnimal
     * create Alligator Object */
    private String areaOfLiving;
    private TerrestrialAnimals noLegs = new TerrestrialAnimals(4);
    private final String uniqueSound="Roar";
    public Alligator(String areaOfLiving,double diveDept){
        /** constructor Alligator
         * @param areaOfLiving (string)
         * @param diveDept (double)
         * */
        super(diveDept);
        this.setAreaOfLiving(areaOfLiving);
        this.setUniqueSound(uniqueSound);
    }
    public int getNumberOfLegs(){return noLegs.getNoLegs();}
    public String getAreaOfLiving() {
        /** getter areaOfLiving
         * */
        return areaOfLiving;
    }

    public boolean setAreaOfLiving(String areaOfLiving) {
        /** Setter areaOfLiving
         * @param areaOfLiving (string)
         * */
        if(!areaOfLiving.equals("")){
            this.areaOfLiving=areaOfLiving;
            return true;
        }
        return false;
    }

    public String toString() {
        /** @return Alligator fields as a string
         * */
        return super.toString()+"Area of living: "+this.getAreaOfLiving()+"\n"+this.getClass().getSimpleName()+" uniqueSound: "+this.getUniqueSound()+"\n";
    }
    public boolean speedUp(int speed) {
        if(Math.abs(speed)<=MAX_SPEED){
            this.setSpeed(speed);
            return true;
        }
        return false;
    }
}
