/**
 * Name: Anthony Eitan Fleysher , ID: 203192331.
 * Dolphin Class Def.
 */
package com.company.animals;

public class Dolphin extends WaterAnimal {
    /** Class Dolphin extends waterAnimal
     * */
    enum WaterTypes {Sea, Sweet};
    private WaterTypes waterType;
    private final String uniqueSound = "Click-Click";

    public Dolphin(String waterType,double diveDept) {
        /** Constructor Dolphin
         * @param waterType (string)
         * @param diveDept (double)
         * */
        super(diveDept);
        this.setWaterType(waterType);
        this.setUniqueSound(uniqueSound);
    }

    public WaterTypes getWaterType() {
        /** getter waterType
         * */
        return waterType;
    }

    public boolean setWaterType(String waterType) {
        /** setter waterType
         * @param waterType (string)
         * */
        if(waterType.equals("sea")) {
            this.waterType= WaterTypes.Sea;
            return true;
        }
        if(waterType.equals("sweet")){
            this.waterType=WaterTypes.Sweet;
            return true;
        }
        return false;
    }
    public String toString() {
        /** @return dolphin details as a string
         * */
        return super.toString()+"WaterType: "+this.getWaterType()+"\n"+this.getClass().getSimpleName()+" uniqueSound: "+this.getUniqueSound()+"\n";
    }
}