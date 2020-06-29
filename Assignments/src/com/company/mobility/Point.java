/**
 * Name: Anthony Eitan Fleysher , ID: 203192331.
 * Point Class Def.
 */
package com.company.mobility;


/* Creates a point(x,y)  */
public class Point {
    private int X;
    private int Y;

    public Point(){
        /** default ctor **/
        this.setX(0);
        this.setY(0);
    }
    public Point(int x, int y){
        /** ctor with param
         * @param x (int)
         * @param y (int)
         * **/
        if(!this.setX(x)){this.X=0;}
        if(!this.setY(y)){this.Y=0;}
    }
    public boolean setX(int x){
        /** setter x **/
        this.X=x;
        return x >= 0;
    }
    public boolean setY(int y){
        /** setter y **/
        this.Y=y;
        return y >= 0;
    }
    public int getX() {
        /** getter x **/
        return X;
    }
    public int getY() {
        /** getter y **/
        return Y;
    }

    @Override
    public String toString() {
        /** returns point details as string **/
        return "("+this.getX()+","+this.getY()+")";
    }
}
