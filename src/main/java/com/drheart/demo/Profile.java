/* Evan, Serenna, Lily, Gia, Barbora
12/6 most recent - 1/16
Stores the instances of the profiles/people
Commented and edited by Barbora
 */
package com.drheart.demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class Profile{
    private final String name;
    private final String email;
    private final String pronouns;
    private final int grade;
    private final String bio;
    private final String pickUpLine;
    private final ArrayList<Question> questions; //This is what stores the answers to the questions
    private Set<Match> matches; //This is what stores the matches
    private PrintStream newProfile;

    /**
     * Used by servlets to create profiles
     * @param name String name of person
     * @param email String email, used as identifier
     * @param pronouns String pronouns
     * @param grade Int grade
     * @param bio String bio
     * @param pickUpLine String pickup line
     */
    public Profile(String name, String email, String pronouns, int grade, String bio,
                   String pickUpLine) {
        this.name = name;
        this.email = email;
        this.pronouns = pronouns;
        this.grade = grade;
        this.bio = bio;
        this.pickUpLine = pickUpLine;
        this.questions = new ArrayList<Question>();
        this.matches = new TreeSet<Match>();
    }

    /**
     * Initializes Profile, adds email to mainFile, creates new file called email and containing
     * the necessary information
     * @param name String name of person
     * @param email String email, used as identifier
     * @param pronouns String pronouns
     * @param grade Int grade
     * @param bio String bio
     * @param pickUpLine String pickup line
     * @param questions String Arraylist with the name of all questions
     * @param mainFile Main File which stores all emails (ids)
     */
    public Profile(String name, String email, String pronouns, int grade, String bio,
                   String pickUpLine, ArrayList<Question> questions, PrintStream mainFile)
            throws FileNotFoundException {
        //Initialization
        this.name = name;
        this.email = email;
        this.pronouns = pronouns;
        this.grade = grade;
        this.bio = bio;
        this.pickUpLine = pickUpLine;
        this.questions = questions;
        this.matches = new TreeSet<Match>();

        //Add email/identifier to central file
        mainFile.print(this.email + ",");

        File newProfileFile = new File("/Users/babu/IdeaProjects/demo/src/main/resources/" + this.email + ".csv");
        newProfile = new PrintStream(newProfileFile);
        populateNewFile();
    }

    /**
     * creates and populates a new file
     */
    private void populateNewFile() {
        newProfile.print(name + ",");
        newProfile.print(pronouns + ",");
        newProfile.print(grade + ",");
        newProfile.print(bio + ",");
        newProfile.print(pickUpLine + ",");

        for (int i = 0; i < questions.size(); i++) {
            newProfile.print(questions.get(i).toString());
        }
    }

    /**
     * Used by the algorithm, adds a match to the profile and adds match to the profile's file
     * @param match The match to be added (Match initialized by (Profile profile, Double MatchPercentage)
     *              - the profile is the other profile to which the match is
     */
    public void addMatch (Match match) {
        matches.add(match);
        newProfile.print(match.getOtherProfile().getEmail() + "," + match.getMatchPercentageFormated() + ",");
    }

    /**
     * Used by servlets, adds match without adding to the file
     */
    public void addMatchWithoutPrint (Match match) {
        matches.add(match);
    }

    /**
     * @return Set of all Matches
     */
    public Set<Match> getMatches() {
        return matches;
    }

    /**
     * @return String email of profile/person (which is used as an identifier)
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return String name of profile/person
     */
    public String getName() {
        return name;
    }

    /**
     * @return Int grade of profile/person
     */
    public int getGrade() {
        return grade;
    }

    /**
     * @return String pronouns of profile/person
     */
    public String getPronouns() {
        return pronouns;
    }

    /**
     * @return String bio of profile/person
     */
    public String getBio() {
        return bio;
    }

    /**
     * @return String pickup line of profile/person
     */
    public String getPickUpLine() {
        return pickUpLine;
    }

    /**
     * @return ArrayList of the Questions which should be filled out
     */
    public ArrayList<Question> getQuestions() {
        return questions;
    }

    //This isn't ever going to be useful
//    public void addQuestion(Question newQuestion) {
//        questions.add(newQuestion);
//    }

    /**
     * @return Name: this.name, email: this.email, pronouns: this.pronouns,
     * grade: this.grade, bio: this.bio, pick up line: this.pickUpLine
     */
    @Override
    public String toString() {
        return "Name: " + name + ", email: " + email + ", pronouns: " + pronouns +
                ", grade: " + grade + ", bio: " + bio + ", pick up line: " + pickUpLine;
    }


//    //old toString
//    public String toString() {
//        return "Name: " + name + ", Grade: " + grade + ", Question Details: " + questions.get(0).getFullQuestionDetails();
//    }
}