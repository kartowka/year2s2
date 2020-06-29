/**
 * Name: Anthony Eitan Fleysher , ID: 203192331.
 * Cat Class Def.
 */
package com.company.animals;

public class Cat extends TerrestrialAnimals{
    /** class Cat extends TerrestrialAnimal
     * */
    private boolean castrated;
    private final String uniqueSound="Meow";
    public Cat(boolean castrated,int noLegs){
        /** Constructor Cat
         * @param castrated (boolean)
         * @param noLegs (int)
         * */
        super(noLegs);
        this.setCastrated(castrated);
        this.setUniqueSound(uniqueSound);
    }
    public boolean isCastrated() {
        /** @return true/false cat is castrated
         * */
        return castrated;
    }

    public void setCastrated(boolean castrated) {
        /** setter isCastrated
         * */
        this.castrated = castrated;
    }
    public String toString() {
        /** @return fields of cat as a string
         *  */
        return super.toString()+"Castrated: "+this.isCastrated()+"\n"+this.getClass().getSimpleName()+" uniqueSound: "+this.getUniqueSound()+"\n";
    }
}
