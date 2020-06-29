package com.company;

public class Action {
    private String name;
    public Action(String name){
        this.name=name;
    }
    public String getName(){
        return this.name;
    }
    public boolean equals(Object other){
        if(other instanceof Action){
            return (name.compareTo(((Action)other).getName())==0);
        }
        return false;
    }
}
