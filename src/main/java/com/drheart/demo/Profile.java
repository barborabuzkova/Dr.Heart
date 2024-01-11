/* Evan, Serenna, Gia, Barbora
12/6
Stores the instances of the profiles/people
 */
package com.drheart.demo;

import java.util.*;

public class Profile{
    private final String name;
    private final String email;
    private final String pronouns;
    private final int grade;
    private final String bio;
    private final String pickUpLine;
    private final ArrayList<Question> questions;
    private Set<Match> matches;

    public Profile(String name, String email, String pronouns, int grade, String bio,
                   String pickUpLine, ArrayList<Question> questions) {
        this.name = name;
        this.email = email;
        this.pronouns = pronouns;
        this.grade = grade;
        this.bio = bio;
        this.pickUpLine = pickUpLine;
        this.questions = questions;
        this.matches = new TreeSet<Match>();
    }

    public void addMatch (Match match) {
        matches.add(match);
    }

    public Set<Match> getMatches() {
        return matches;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public int getGrade() {
        return grade;
    }

    //This isn't ever going to be useful
//    public void addQuestion(Question newQuestion) {
//        questions.add(newQuestion);
//    }

    @Override
    public String toString() {
        return "Name: " + name + ", email: " + email + ", pronouns: " + pronouns +
                ", grade " + grade + ", bio" + bio + ", pick up line: " + pickUpLine;
    }


//    //old toString
//    public String toString() {
//        return "Name: " + name + ", Grade: " + grade + ", Question Details: " + questions.get(0).getFullQuestionDetails();
//    }
}