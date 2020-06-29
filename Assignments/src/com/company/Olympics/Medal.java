/**
 * Name: Anthony Eitan Fleysher , ID: 203192331.
 * Medal Class Def.
 */
package com.company.Olympics;


public class Medal {
    /** Medal Class **/
    enum types{bronze,silver,gold};
    private types type;
    private String tournament;
    private int year;
    private static final int MIN_YEAR=2020;
    public Medal(){
        /** empty ctor **/
    }
    public String getTournament() {
        /** getter tournament **/
        return tournament;
    }

    public boolean setTournament(String tournament) {
        /** setter tournament **/
        if(!tournament.equals("")){
            this.tournament=tournament;
            return true;
        }
        return false;
    }

    public int getYear() {
        /** getter year **/
        return year;
    }

    public boolean setYear(int year){
        /** setter year **/
        if(year>=MIN_YEAR){
            this.year=year;
            return true;
        }
        return false;
    }
    public boolean setType(String type){
        /** setter type **/
        if(type.equals("bronze")){
            this.type= types.bronze;
            return true;
        }
        if (type.equals("silver")) {
            this.type=types.silver;
            return true;
        }
        if (type.equals("gold")) {
            this.type=types.gold;
            return true;
        }
        return false;
    }
    public types getType(){
        /** getter type **/
        return this.type;
    }

    @Override
    public String toString() {
        /** returns Medal details as string **/
        return this.getType()+" Medal won at tournament: "+this.getTournament()+" at year: "+this.getYear()+"\n";
    }
}