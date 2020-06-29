/**
 * Name: Anthony Eitan Fleysher , ID: 203192331.
 * Pigeon Class Def.
 */
package com.company.animals;

public class Pigeon extends AirAnimal{
    /** Class Pigeon extends AirAnimal
     * */
    private String family;
    private String uniqueSound= "Arr-rar-rar-rar-raah";
    public Pigeon(String family,double wingspan){
        /** Constructor Pigeon
         * @param family (String)
         * @param wingspan (double)
         * */
        super(wingspan);
        this.setFamily(family);
        this.setUniqueSound(uniqueSound);
    }

    public String getFamily() {
        /** getter family
         * **/
        return family;
    }

    public boolean setFamily(String family) {
        /** setter family
         * @param family (String)
         * **/
        if(!family.equals("")){
            this.family=family;
            return true;
        }
        return false;
    }
    public String toString() {
        /** returns Pigeon details as string.
         * **/
        return super.toString()+"Family: "+this.getFamily()+"\n"+this.getClass().getSimpleName()+" uniqueSound: "+this.getUniqueSound()+"\n";
    }
}
