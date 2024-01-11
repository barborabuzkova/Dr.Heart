/* Evan, Serenna, Gia
12/6
Stores the instances of the questions
 */
package com.drheart.demo;

public class Question {

    //subject of question
    private String questionName;
    //variable for storing self score, desired score in
    //match, and importance of the match having the desired score
    private int scoreSelf;
    private int scoreDesired;
    private int scoreDesiredImportance;

    // I only made this initializiation in order to do some testing
    public Question(String questionName) {
        this.questionName = questionName;
        scoreSelf = -1;
        scoreDesired = -1;
        scoreDesiredImportance = -1;

    }

    //initializes question object with all data about profile's response
    //to the questions
    public Question(String name, int self, int desired, int desiredImportance) {
        questionName = name;
        scoreSelf = self;
        scoreDesired = desired;
        scoreDesiredImportance = desiredImportance;
    }

    public String getName() {
        return questionName;
    }

    public int getScoreSelf() {
        return scoreSelf;
    }

    public int getScoreDesired() {
        return scoreDesired;
    }

    public int getScoreDesiredImportance() {
        return scoreDesiredImportance;
    }

    public String getFullQuestionDetails() {
        return "Question: " + questionName + " Self Score: " + scoreSelf + " Desired Partner Score: " +
                scoreDesired + " Partner's Score Importance: " + scoreDesiredImportance;
    }
}