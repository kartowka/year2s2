package com.company.Tournaments;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Scores {
    private Map<String, Date> scores;
    public Scores(){
        this.scores= Collections.synchronizedMap(new HashMap<>());
    }
    public void add(String name){
        this.scores.put(name,new Date());
    }
    public Map<String,Date> getAll(){
        return this.scores;
    }
}
