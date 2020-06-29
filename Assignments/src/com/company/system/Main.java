/**
 * Name: Anthony Eitan Fleysher , ID: 203192331.
 * Main class def.
 */
package com.company.system;

import com.company.animals.*;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        System.out.println("Hello and welcome to Zoo.");
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter number of animals you would like to add in to the Zoo: ");
        int numberOFAnimals=input.nextInt();
        Animal[] animals=new Animal[numberOFAnimals];
        for(int i=0;i<animals.length;++i){
            int choice;
            do{
                System.out.print("Which type of animal you would like to add? :\n1.WaterAnimal\n2.TerrestrialAnimal\n3.AirAnimal\nYour Choice: ");
                choice=input.nextInt();
            }while(choice<=0||choice>=4);
            switch (choice){
                case 1:
                    addWaterAnimal(animals,i);
                    break;
                case 2:
                    addTerrestrialAnimal(animals,i);
                    break;
                case 3:
                    addAirAnimal(animals,i);
                    break;
            }
        }
        int choice;
        do{
            System.out.print("1.Printing all animals in the Zoo.\n2.uniqueSound of each animal in the Zoo.\n3.exit.\nEnter your choice: ");
            choice = input.nextInt();
            switch (choice){
                case 1:
                    for(Animal animal: animals) {
                        System.out.println(animal.toString());
                    }
                    break;
                case 2:
                    for(Animal animal: animals) {
                        animal.makeSound();
                    }
                    break;
                case 3:
                    break;
            }
        }while(choice!=3);
        System.out.println("GoodBye");
        input.close();
    }
    public static void addAnimalField(Animal[] animals,int i) {
        //** help func to add animal fields **/
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Animal name: ");
        String name = input.next();
        animals[i].setName(name);
        System.out.print("Enter Animal Gender [Male,Female,Hermaphrodite]: ");
        String gender = input.next().toLowerCase();
        animals[i].setGender(gender);
        System.out.print("Enter Animal weight: ");
        double weight=input.nextDouble();
        animals[i].setWeight(weight);
        System.out.print("Enter Animal Speed: ");
        double speed=input.nextDouble();
        animals[i].setSpeed(speed);

    }
    public static void addTerrestrialAnimal(Animal[] animals, int i) {
        String[]  TerrestrialAnimal = {"1.Dog","2.Cat","3.Snake"};
        Scanner input = new Scanner(System.in);
        System.out.println("There is "+TerrestrialAnimal.length+" types of TerrestrialAnimal in the Zoo.\n"+ Arrays.toString(TerrestrialAnimal));
        int choice;
        do{
            System.out.print("which one would you like to pick?: ");
            choice = input.nextInt();
        }while(choice<=0||choice>TerrestrialAnimal.length);
        System.out.println("Enter number of Legs: ");
        int noLegs=input.nextInt();
        switch (choice) {
            case 1:
                System.out.print("Dog Breed: ");
                String breed=input.next();
                animals[i] = new Dog(breed,noLegs);
                break;
            case 2:
                System.out.print("Cat castrated [true,false]: ");
                boolean isCastrated=input.nextBoolean();
                animals[i] = new Cat(isCastrated,noLegs);
                break;
            case 3:
                System.out.print("Snake Poisonous [low,medium,high]: ");
                String poisonous = input.next();
                System.out.print("Snake length: ");
                double length = input.nextDouble();
                animals[i] = new Snake(poisonous,length,noLegs=0);
                break;
        }
        addAnimalField(animals,i);
    }

    public static void addAirAnimal(Animal[] animals, int i) {
        String[] AirAnimal = {"1.Eagle","2.Pigeon"};
        Scanner input = new Scanner(System.in);
        System.out.println("There is "+AirAnimal.length+" types of waterAnimal in the Zoo.\n"+ Arrays.toString(AirAnimal));
        int choice;
        do{
            System.out.print("which one would you like to pick?: ");
            choice = input.nextInt();
        }while(choice<=0||choice>AirAnimal.length);
        System.out.print("Enter wingspan: ");
        double wingspan=input.nextDouble();
        switch (choice) {
            case 1:
                System.out.print("Eagle AltitudeOfFlight: ");
                double altitude=input.nextDouble();
                animals[i] = new Eagle(altitude,wingspan);
                break;
            case 2:
                System.out.print("Pigeon Family: ");
                String family=input.next();
                animals[i] = new Pigeon(family,wingspan);
                break;
        }
        addAnimalField(animals,i);
    }

    public static void addWaterAnimal(Animal[] animals,int i){
        String[] waterAnimals = {"1.Alligator","2.Whale","3.Dolphin"};
        Scanner input = new Scanner(System.in);
        System.out.println("There is "+waterAnimals.length+" types of waterAnimal in the Zoo.\n"+ Arrays.toString(waterAnimals));
        int choice;
        do{
            System.out.print("which one would you like to pick?: ");
            choice = input.nextInt();
        }while(choice<=0||choice>waterAnimals.length);
        System.out.println("Enter DiveDept: ");
        double diveDept=input.nextDouble();
        switch (choice){
            case 1:
                System.out.print("Alligator AreaOfLiving: ");
                String live=input.next();
                animals[i]=new Alligator(live,diveDept);
                break;
            case 2:
                System.out.print("Whale foodType: ");
                String foodType=input.next();
                animals[i]=new Whale(foodType,diveDept);
                break;
            case 3:
                System.out.print("Dolphin WaterType [Sea,Sweet]: ");
                String WaterType=input.next().toLowerCase();
                animals[i]=new Dolphin(WaterType,diveDept);
                break;
        }
        addAnimalField(animals,i);
    }

}

