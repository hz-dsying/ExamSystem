package com.zzxx.exam.bean;

import java.util.List;

public class Question {
    private String information;
    private String tigan;
    private String answer0;
    private String answer1;
    private String answer2;
    private String answer3;
    private List trueAnswer;
    private int level;
    private int score;

    public Question(){

    }

    public List getTrueAnswer() {
        return trueAnswer;
    }

    public void setTrueAnswer(List trueAnswer) {
        this.trueAnswer = trueAnswer;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getTigan() {
        return tigan;
    }

    public void setTigan(String tigan) {
        this.tigan = tigan;
    }

    public String getAnswer0() {
        return answer0;
    }

    public void setAnswer0(String answer0) {
        this.answer0 = answer0;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    @Override
    public String toString() {
        return tigan + "\n" + "A. " + answer0 + "\n" + "B. "+ answer1 + "\n" + "B. "+ answer2 + "\n" + "D. "+ answer3 + "\n";
    }
}
