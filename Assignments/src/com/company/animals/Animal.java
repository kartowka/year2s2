/**
 * Name: Anthony Eitan Fleysher , ID: 203192331.
 * Animal Abstract Class Def.
 */
package com.company.animals;

import com.company.Olympics.Medal;
import com.company.Tournaments.AnimalThread;
import com.company.graphics.CompetitionPanel;
import com.company.graphics.IAnimal;
//import com.company.graphics.IClonable; (is it really needed?) -> using (JAVA Cloneable)
import com.company.graphics.IDrawable;
import com.company.mobility.ILocatable;
import com.company.mobility.Mobile;
import com.company.mobility.Point;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public abstract class Animal extends Mobile implements ILocatable, IDrawable,IAnimal,Cloneable {
    /** Abstart class Animal extends Mobile
     * */
    enum gender {Male, Female, Hermaphrodite};
    public final static int MAX_X = 725;
    public final static int MAX_Y = 445;
    public final static int MAX_X_WATERANIMAL = 660;
    private String name;
    private String category;
    private gender _gender;
    private double weight;
    private double speed;
    private ArrayList<Medal> medals;
    private Point position;
    private String uniqueSound;
    static final double MIN_WEIGHT=0;
    protected int size;
    enum Orientation{EAST,WEST,SOUTH,NORTH};
    protected Orientation orientation;
    protected int maxEnergy;
    protected int energyPerMeter;
    protected int currentEnergy;
    protected int energyConsumption;
    protected CompetitionPanel pan;
    protected BufferedImage img1, img2, img3, img4;
    private final static int PIXELSIZE = 65;
    public Animal(){
        /** Constructor Animal
         * */
        super();
        this.size=PIXELSIZE;
        this.energyConsumption=0;
        this.setOrientation("east");

    }
    public boolean setWeight(double weight) {
        /** Setter weight
         * @param weight (double)
         * */
        if (weight < MIN_WEIGHT) {
            return false;
        }
        this.weight = weight;
        return true;
    }
    public boolean setSpeed(double speed) {
        /** setter speed
         * @param speed (double)
         * */
        if (speed < MIN_WEIGHT) {
            return false;
        }
        this.speed = speed;
        return true;
    }
    public void setMedals(ArrayList<Medal> medals) {
        this.medals=medals;
    }
    public boolean setPanel(CompetitionPanel panel){
        if(panel==null){
            return false;
        }
        this.pan=panel;
        return true;
    }

    public CompetitionPanel getPan() {
        return pan;
    }

    public boolean setName(String name){
        /** setter name
         * @param name (string)
         * */
        if(!name.equals("")){
            this.name=name;
            return true;
        }
        return false;
    }
    public boolean setGender(String _gender) {
        /** setter gender
         * @param gender (string)
         * */
        if (_gender.equals("male")) {
            this._gender= gender.Male;
            return true;
        }
        if (_gender.equals("female")) {
            this._gender= gender.Female;
            return true;
        }
        if (_gender.equals("hermaphrodite")) {
            this._gender= gender.Hermaphrodite;
            return true;
        }
        return false;
    }

    public int getEnergyConsumption() {
        return energyConsumption;
    }

    public boolean setEnergyConsumption() {
        this.energyConsumption+=this.speed*this.energyPerMeter;
        return true;
    }

    public Point getPosition() {
        /** @return position as a Point
         * */
        return position;
    }

    public boolean setPosition(Point position) {
        /** setter position
         * @param position (Point)
         * */
        if(position!=null){
            this.position = position;
            return true;
        }
        return false;
    }
    public void makeSound(){
        /** prints specific animal sound
         * */
        System.out.println("Animal "+this.getClass().getSimpleName()+" said "+"\""+this.getUniqueSound()+"\"");
    }

    public String getUniqueSound() {
        /** @return uniqueSound as a string
         * */
        return uniqueSound;
    }

    protected boolean setUniqueSound(String uniqueSound) {
        /** setter uniqueSound
         * @param  uniqueSound (string)
         * */
        if (!uniqueSound.equals("")) {
            this.uniqueSound = uniqueSound;
            return true;
        }
        return false;
    }

    public String getName() {
        /** getter name
         * */
        return name;
    }

    public gender get_gender() {
        /** getter gender
         * */
        return _gender;
    }

    public double getWeight() {
        /** getter weight
         * */
        return weight;
    }

    public double getSpeed() {
        /** getter speed
         * */
        return speed;
    }

    public String getCategory() {
        return category;
    }

    public boolean setCategory(String category) {
        this.category = category;
        return true;
    }

    @Override
    public boolean eat(int energy) {
        if(this.currentEnergy==this.maxEnergy) {
            return false;
        }
        else if(this.currentEnergy<=this.maxEnergy){
            if(this.currentEnergy+energy>=this.maxEnergy){
                this.currentEnergy=this.maxEnergy;
            }
            else{
                this.currentEnergy+=energy;
            }
        }
        return true;
    }
    @Override
    public void loadImages(String nm) throws IOException {
        img1 = ImageIO.read(new File(IDrawable.PICTURE_PATH + nm.toLowerCase() + "1" + "E.png"));
        img2 = ImageIO.read(new File(IDrawable.PICTURE_PATH + nm.toLowerCase() + "1" + "S.png"));
        img3 = ImageIO.read(new File(IDrawable.PICTURE_PATH + nm.toLowerCase() + "1" + "W.png"));
        img4 = ImageIO.read(new File(IDrawable.PICTURE_PATH + nm.toLowerCase() + "1" + "N.png"));
    }

    @Override
    public void drawObject(Graphics g) {
        if(orientation==Orientation.EAST) // animal move to the east side
            if(this.category.equals("Water Animal")||this.category.equals("Air Animal") ){
                g.drawImage(img1, this.getLocation().getX(), this.getLocation().getY(), size, size-(size/5),pan);
            }
            else {
                g.drawImage(img1, this.getLocation().getX(), this.getLocation().getY(), size, size, pan);
            }
        else if(orientation==Orientation.SOUTH) // animal move to the south side
            g.drawImage(img2, this.getLocation().getX(), this.getLocation().getY(), size, size,pan);
        else if(orientation==Orientation.WEST)// animal move to the west side
            if(this.category.equals("Water Animal")||this.category.equals("Air Animal") ){
                g.drawImage(img3, this.getLocation().getX(), this.getLocation().getY(), size, size-(size/5),pan);
            }else {
                g.drawImage(img3, this.getLocation().getX(), this.getLocation().getY(), size, size, pan);
            }
        else if(orientation==Orientation.NORTH) // animal move to the north side
            g.drawImage(img4, this.getLocation().getX(), this.getLocation().getY(),size, size, pan);
    }

    public ArrayList<Medal> getMedals() {
        return medals;
    }

    public int getEnergyPerMeter() {
        return energyPerMeter;
    }

    public boolean setEnergyPerMeter(int energyPerMeter) {
        if(energyPerMeter<0){
            return false;
        }
        this.energyPerMeter=energyPerMeter;
        return true;
    }

    public String getOrientation() {
        return orientation.toString();
    }

    public boolean setOrientation(String orientation) {
        if(orientation.equals("west")){
            this.orientation=Orientation.WEST;
            return true;
        }
        if(orientation.equals("east")){
            this.orientation=Orientation.EAST;
            return true;
        }
        if(orientation.equals("south")){
            this.orientation=Orientation.SOUTH;
            return true;
        }
        if(orientation.equals("north")){
            this.orientation=Orientation.NORTH;
            return true;
        }
        return false;

    }

    public int getCurrentEnergy() {
        return currentEnergy;
    }

    public boolean setCurrentEnergy() {
        this.currentEnergy-=(this.speed*this.energyPerMeter);
        if(this.speed<=0){
            return false;
        }
        else if(this.currentEnergy<0){
            this.currentEnergy=0;
        }
        return true;
    }

    public int getMaxEnergy() {
        return maxEnergy;
    }

    public boolean setMaxEnergy(int maxEnergy) {
        if(maxEnergy<0){
            return false;
        }
        this.maxEnergy=maxEnergy;
        this.currentEnergy=maxEnergy;
        return true;
    }
    public Point nextLocationWater(int numberOfAnimals,int animalNumber){
        final  int animals2 = 365;
        final  int[] animals3 = {267,464};
        final  int[] animals4 = {218,366,514};
        if (numberOfAnimals == 2) {
            if (animalNumber == 1) {
                return new Point(animals2, this.getLocation().getY());
            }
        }else if (numberOfAnimals == 3) {
            if (animalNumber == 1) {
                return new Point(animals3[0], this.getLocation().getY());
            }
            return new Point(animals3[1], this.getLocation().getY());
        } else if (numberOfAnimals == 4) {
            if (animalNumber == 1) {
                return new Point(animals4[0], this.getLocation().getY());
            } else if (animalNumber == 2) {
                return new Point(animals4[1], this.getLocation().getY());
            }
            return new Point(animals4[2], this.getLocation().getY());
        }
        return new Point(0,0);
    }
    public Point nextLocationAir(int numberOfAnimals,int animalNumber){
        final  int animals2 = 363;
        final  int[] animals3 = {242,484};
        final  int[] animals4 = {182,364,546};
        if (numberOfAnimals == 2) {
            if (animalNumber == 1) {
                return new Point(animals2, this.getLocation().getY());
            }
        }else if (numberOfAnimals == 3) {
            if (animalNumber == 1) {
                return new Point(animals3[0], this.getLocation().getY());
            }
            return new Point(animals3[1], this.getLocation().getY());
        } else if (numberOfAnimals == 4) {
            if (animalNumber == 1) {
                return new Point(animals4[0], this.getLocation().getY());
            } else if (animalNumber == 2) {
                return new Point(animals4[1], this.getLocation().getY());
            }
            return new Point(animals4[2], this.getLocation().getY());
        }
        return new Point(0,0);
    }
    public Point nextLocationTerrestrial(int numberOfAnimals,int animalNumber){
        final int[] animals2 = {725,445};
        final int[] animals3 = {725,65,315,445};
        final int[] animals4 = {592,0,725,445,133,445};
        if (numberOfAnimals == 2) {
            if (animalNumber == 1) {
                return new Point(animals2[0], animals2[1]);
            }
        }else if (numberOfAnimals == 3) {
            if (animalNumber == 1) {
                return new Point(animals3[0], animals3[1]);
            }
            this.setOrientation("west");
            return new Point(animals3[2], animals3[3]);
        } else if (numberOfAnimals == 4) {
            if (animalNumber == 1) {
                return new Point(animals4[0], animals4[1]);
            } else if (animalNumber == 2) {
                this.setOrientation("west");
                return new Point(animals4[2], animals4[3]);
            }
            this.setOrientation("west");
            return new Point(animals4[4], animals4[5]);
        }
        return new Point(0,0);
    }
    public void toEAST(){
        this.move(new Point(this.getLocation().getX()+(int)this.getSpeed(),this.getLocation().getY()));
        this.setLocation(new Point(this.getLocation().getX()+(int)this.getSpeed(),this.getLocation().getY()));
        if(this.getCategory().equals("Water Animal")) {
            if (this.getLocation().getX() > MAX_X_WATERANIMAL) {
                this.setLocation(new Point(MAX_X_WATERANIMAL, this.getLocation().getY()));
                this.setOrientation("west");
            }
        }
        else if(this.getCategory().equals("Air Animal")){
            if (this.getLocation().getX() > MAX_X) {
                this.setLocation(new Point(MAX_X, this.getLocation().getY()));
                this.setOrientation("west");
            }
        }
        else if(this.getLocation().getX()>MAX_X) {
            this.setLocation(new Point(MAX_X, this.getLocation().getY()));
            this.setOrientation("south");
        }

    }
    public void toSOUTH(){
        this.move(new Point(this.getLocation().getX(),this.getLocation().getY()+(int)this.getSpeed()));
        this.setLocation(new Point(this.getLocation().getX(),this.getLocation().getY()+(int)this.getSpeed()));
        if(this.getLocation().getY()>MAX_Y) {
            this.setLocation(new Point(this.getLocation().getX(), MAX_Y));
            this.setOrientation("west");
        }
    }
    public void toWEST(){
        this.move(new Point(this.getLocation().getX()-(int)this.getSpeed(),this.getLocation().getY()));
        this.setLocation(new Point(this.getLocation().getX()-(int)this.getSpeed(),this.getLocation().getY()));
        if(this.getCategory().equals("Water Animal")) {
            if (this.getLocation().getX() <= 70) {
                this.setLocation(new Point(70, this.getLocation().getY()));
                this.setOrientation("east");
            }
        }
        else if(this.getCategory().equals("Air Animal")){
            if(this.getLocation().getX()<=0) {
                this.setLocation(new Point(0, this.getLocation().getY()));
                this.setOrientation("east");
            }
        }
        else if(this.getLocation().getX()<=0) {
            this.setLocation(new Point(0, this.getLocation().getY()));
            this.setOrientation("north");
        }
    }
    public void toNORTH(){
        this.move(new Point(this.getLocation().getX(),this.getLocation().getY()-(int)this.getSpeed()));
        this.setLocation(new Point(this.getLocation().getX(),this.getLocation().getY()-(int)this.getSpeed()));
        if(this.getLocation().getY()<=0) {
            this.setLocation(new Point(this.getLocation().getX(), 0));
            this.setOrientation("east");
        }
    }

    public String toString(){
        /** @return Animal details as a string
         * */
        return "Name: "+this.getName()+'\n'+"Gender: "+this.get_gender()+"\n"+"Weight: "+this.getWeight()+"\n"+"Speed: "+this.getSpeed()+"\n"+super.toString();
    }
}