/**
 * Name: Anthony Eitan Fleysher , ID: 203192331.
 * Whale Class Def.
 */
package com.company.animals;

public class Whale extends WaterAnimal {
    /** class Whale extends WaterAnimal
     *
     */
    private String foodType;
    private final String uniqueSound="Splash";
    public Whale(String foodType,double diveDept){
        /** Constructor Whale
         * @param foodType (String)
         * @param diveDept (double)
         */
        super(diveDept);
        this.setFoodType(foodType);
        this.setUniqueSound(uniqueSound);
    }
    public String getFoodType() {
        /** getter foodType
         *
         */
        return foodType;
    }

    public boolean setFoodType(String foodType) {
        /** setter foodType
         *
         */
        if(!foodType.equals("")){
            this.foodType = foodType;
            return true;
        }
        return false;
    }
    public String toString() {
        /** returns Whale fields as string
         * **/
        return super.toString()+"FoodType: "+this.getFoodType()+"\n"+this.getClass().getSimpleName()+" uniqueSound: "+this.getUniqueSound()+"\n";
    }
}
