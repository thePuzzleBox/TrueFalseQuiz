package com.example.helen.truefalsequiz;

import java.util.List;

public class Quiz {

    public List<Question> questions;
    public int score;
    public int currentQuestion;


    public Quiz(List questions, int score, int currentQuestion) {
        this.questions = questions;
        this.score = score;
        this.currentQuestion = currentQuestion;
    }
    public List getQuestions() {
        return questions;
    }


    public int getScore() {
        return score;
    }

    public void incrScore() {

        score = score + 100;
        Quiz quiz = new Quiz(questions, score, currentQuestion);

        if( quiz.isThereAnotherQuestion() == true){
            currentQuestion++;
                }
    }

    public void decrScore() {

        if(score == 0){
            score = 0;
        }

        else {
            score -= 100;
        }

        Quiz quiz = new Quiz(questions, score, currentQuestion);

        if( quiz.isThereAnotherQuestion() == true){
            currentQuestion++;
        }
    }



    public int getCurrentQuestion() {
        return currentQuestion;
    }



    public boolean isThereAnotherQuestion(){
        if(currentQuestion < 11){
            return true;
        }

        else{
            return false;
        }


    }

}
