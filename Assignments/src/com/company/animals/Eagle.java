/**
 * Name: Anthony Eitan Fleysher , ID: 203192331.
 * Eagle Class Def.
 */
package com.company.animals;

public class Eagle extends AirAnimal{
    /** Class Eagle extends AirAnimal
     * */
    private double altitudeOfFlight;
    static final double MAX_ALTITUDE = 1000;
    static final double MIN_ALTITUDE = 0;
    private final String uniqueSound = "Clack-wack-chack";
    public Eagle(double altitudeOfFlight,double wingspan){
        /** Constructor Eagle
         * @param altitudeOfFilght (double)
         * @param wingspan (double)
         * */
        super(wingspan);
        this.setAltitudeOfFlight(altitudeOfFlight);
        this.setUniqueSound(uniqueSound);
    }

    public double getAltitudeOfFlight() {
        /** getter altitudeOfFlight
         * */
        return altitudeOfFlight;
    }

    public boolean setAltitudeOfFlight(double altitudeOfFlight) {
        /** setter altitudeOfFlight
         * @param altitudeOfFlight (double)
         * */
        if(altitudeOfFlight<MIN_ALTITUDE || altitudeOfFlight>MAX_ALTITUDE){return false;}
        this.altitudeOfFlight=altitudeOfFlight;
        return true;
    }
    public String toString() {
        /** @return details of Eagle as a string
         * */
        return super.toString()+"Altitude of flight: "+this.getAltitudeOfFlight()+"\n"+this.getClass().getSimpleName()+" uniqueSound: "+this.getUniqueSound()+"\n";
    }
}
