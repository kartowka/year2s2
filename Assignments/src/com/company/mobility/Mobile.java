/**
 * Name: Anthony Eitan Fleysher , ID: 203192331.
 * Mobile abstract Class Def.
 */
package com.company.mobility;


public abstract class Mobile implements ILocatable{
    /** abstract class Mobile **/
    private Point location;
    private double totalDistance;
    public Mobile(){
        /** constructor default **/
        Point p = new Point(0,0);
        this.setLocation(p);
        this.totalDistance=0;
    }
    public Mobile(Point p){
        /** constructor with param
         * @param p (point)
         * **/
        this.setLocation(p);
        this.totalDistance=0;
    }
    public void addTotalDistance(double distance){
        /** add to distance **/
        this.totalDistance+=distance;
    }
    public double calcDistance(Point p){
        /** calculate the distance **/
        return Math.sqrt(Math.pow((p.getX()-this.location.getX()),2)+Math.pow((p.getY()-this.location.getY()),2));
    }
    public double move(Point p){
        /** make a move **/
        double traveled=this.calcDistance(p);
        this.addTotalDistance(traveled);
        //this.setLocation(p);
        return traveled;

    }
    public Point getLocation() {
        /** getter location **/
        return location;
    }
    public boolean setLocation(Point p) {
        /** setter location **/
        if(p.getX() >=0 && p.getY() >= 0){
            this.location=p;
            return true;
        }
        return false;
    }

    public double getTotalDistance() {
        /** getter distance **/
        return totalDistance;
    }

    @Override
    public String toString() {
        /** return Mobile details as string **/
        return "Current location: "+this.getLocation()+"\n"+"Total distance traveled: "+this.getTotalDistance()+"\n";
    }
}
