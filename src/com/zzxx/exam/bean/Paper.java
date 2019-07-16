package com.zzxx.exam.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Paper {
    private Map<Integer, List> userAnswers = new HashMap<Integer, List>();
    private Map<Integer, Question> questions = new HashMap<Integer, Question>();

    public Paper(){

    }

    public Paper(Map<Integer, List> userAnswers, Map<Integer, Question> questions) {
        this.userAnswers = userAnswers;
        this.questions = questions;
    }

    public Map<Integer, List> getUserAnswers() {
        return userAnswers;
    }

    public void setUserAnswers(Map<Integer, List> userAnswers) {
        this.userAnswers = userAnswers;
    }

    public Map<Integer, Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Map<Integer, Question> questions) {
        this.questions = questions;
    }
}
