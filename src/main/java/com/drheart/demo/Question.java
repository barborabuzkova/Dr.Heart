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

    /**
     * initializes question object with all data about profile's response to the questions
     * @param name Name of question, ex: "assertiveness"
     * @param self score of person
     * @param desired desired score
     * @param desiredImportance importance of desired score
     */
    public Question(String name, int self, int desired, int desiredImportance) {
        questionName = name;
        scoreSelf = self;
        scoreDesired = desired;
        scoreDesiredImportance = desiredImportance;
    }

    /**
     * @return name of question (String)
     */
    public String getName() {
        return questionName;
    }

    /**
     * @return  Score (between 1 and 5 inclusive)
     */
    public int getScoreSelf() {
        return scoreSelf;
    }

    /**
     * @return Desired Score (between 1 and 5 inclusive)
     */
    public int getScoreDesired() {
        return scoreDesired;
    }

    /**
     * @return Importance of Desired Score (between 1 and 5 inclusive)
     */
    public int getScoreDesiredImportance() {
        return scoreDesiredImportance;
    }

    /**
     * @return toString with String identifiers, ex "Question: " + questionName
     */
    public String getFullQuestionDetails() {
        return "Question: " + questionName + " Self Score: " + scoreSelf + " Desired Partner Score: " +
                scoreDesired + " Partner's Score Importance: " + scoreDesiredImportance;
    }

    /**
     * @return questionName,scoreSelf,scoreDesired,scoreDesiredImportance,  - csv format
     */
    public String toString() {
        return questionName + "," + scoreSelf + "," + scoreDesired + "," + scoreDesiredImportance + ",";
    }

    //this was originally used for testing, shouldn't be necessary anymore
//    /**
//     * initializes question object using only questionName, only used for testing
//     * @param questionName Name of question, ex: "assertiveness"
//     */
//    public Question(String questionName) {
//        this.questionName = questionName;
//        scoreSelf = -1;
//        scoreDesired = -1;
//        scoreDesiredImportance = -1;
//
//    }
}