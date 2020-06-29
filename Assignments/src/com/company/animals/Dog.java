/**
 * Name: Anthony Eitan Fleysher , ID: 203192331.
 * Dog Class Def.
 */
package com.company.animals;

public class Dog extends TerrestrialAnimals {
    /** Class Dog extends TerrestrialAnimals
     * */
    private String breed;
    private final String uniqueSound = "Woof Woof";
    public Dog(String breed,int noLegs){
        /** Constructor Dog
         * @param breed (string)
         * @param noLegs (int)
         * */
        super(noLegs);
        this.setBreed(breed);
        this.setUniqueSound(uniqueSound);
    }
    public String getBreed() {
        /** getter breed
         * */
        return breed;
    }

    public boolean setBreed(String breed) {
        /** setter breed
         * @param breed (string)
         * */
        if(!breed.equals("")){
            this.breed=breed;
            return true;
        }
        return false;
    }
    public String toString() {
        /** @return dog details as a string
         * */
        return super.toString()+"Breed: "+this.getBreed()+"\n"+this.getClass().getSimpleName()+" uniqueSound: "+this.getUniqueSound()+"\n";
    }
}