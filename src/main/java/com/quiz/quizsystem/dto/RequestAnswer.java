package com.quiz.quizsystem.dto;


import lombok.Getter;
import lombok.Setter;



@Setter
public class RequestAnswer {
    private String name;
    private String pair;
    private boolean is_correct;
    private int question_id;


    public String getName() {
        return name;
    }
    public String getPair() {
        return pair;
    }
    public boolean getIs_correct() {
        return is_correct;
    }
    public int getQuestion_id() {
        return question_id;
    }
   
}
