/* Evan, Serenna, Barbora
Originally Titled "File Reader"
1/11 most recent - 1/16
Evan and Serenna made algorithm, createProfile, matchScore, and importanceVal
Barbora edited main and added MainFile printing
 */
package com.drheart.demo;

import java.io.*; //files
import java.util.*; //scan & math
//import org.json.simple.*;

public class DataProcessor { //Can we get this to run when the excel file updates?

    public static void main(String[] args) throws FileNotFoundException {
        //Create scanner, split
        Scanner scannerGeorg = new Scanner(new File("/Users/babu/IdeaProjects/demo/src/main/resources/SampleFormData.csv"));

        //Read first line and read in questions
        String bob = scannerGeorg.nextLine();
        ArrayList<String> questions = new ArrayList<>();
        String[] lineOne = bob.split("\\s*,\\s*");

        //Create Main File with all profiles
        File MainFileForIDS = new File("/Users/babu/IdeaProjects/demo/src/main/FileStorage/MainFileForIDS.csv");
        PrintStream mainFile = new PrintStream(MainFileForIDS);

        //System.out.println(lineOne[0] + " " + lineOne[1]); //test line
        int x = 7; // how many questions are just for profile information + age which we don't use
        //System.out.println(lineOne.length); //test line
        while (x < lineOne.length) {
            //System.out.println(lineOne[x]); //test line
            questions.add(lineOne[x]);
            x += 3; // by 1, and then skip 2 for 'desired outcome' and 'importance'
        }
        //System.out.println(questions.toString()); //test line

        //create profiles
        ArrayList<Profile> profiles = new ArrayList<>();
        while (scannerGeorg.hasNextLine()) {
            profiles.add(createProfile(scannerGeorg.nextLine(), questions, mainFile));
        }
        //System.out.println(profiles.size());
        //System.out.println(profiles.toString()); //test tostring

        //run the algorithm
        for (int profileNumber = 0; profileNumber < profiles.size(); profileNumber++) {
            algorithm(profiles, profileNumber);
        }
    }

    public static Profile createProfile(String line, ArrayList<String> questions, PrintStream mainFile) throws NumberFormatException, FileNotFoundException {

        String[] sally = line.split("\\s*,\\s*"); //regex again
        String name = sally[0];
        String email = sally[1];
        String pronouns = sally[2];
        int grade = Integer.parseInt(sally[3]); //parse string into int
        String bio = sally[4];
        String pickUpLine = sally[5];
        ArrayList<Question> pfQuestions = new ArrayList<Question>();
        for (int i = 0; i < questions.size(); i++) { //creating the questions for the arraylist
            pfQuestions.add(new Question(questions.get(i), Integer.parseInt(sally[7 + (i * 3)]), Integer.parseInt(sally[8 + (i * 3)]), Integer.parseInt(sally[9 + (i * 3)])));
        }
        Profile profilebob = new Profile(name, email, pronouns, grade, bio, pickUpLine, pfQuestions, mainFile);
        return profilebob;
    }

    public static void algorithm(ArrayList<Profile> profiles, int profileNumber) {
        for (int j = 0; j < profiles.size(); j++) {
            ArrayList<Question> q1 = profiles.get(profileNumber).getQuestions();
            ArrayList<Question> q2 = profiles.get(j).getQuestions();
            int selfSum1 = 0;
            int selfSum2 = 0;
            int desSum1 = 0;
            int desSum2 = 0;
            int impSum1 = 0;
            int impSum2 = 0;
            int score1 = 0;
            int score2 = 0;
            for (int k = 0; k < profiles.get(profileNumber).getQuestions().size(); k++) { //cumulative sum
                int self1 = q1.get(k).getScoreSelf();
                int self2 = q2.get(k).getScoreSelf();
                int des1 = q1.get(k).getScoreDesired();
                int des2 = q2.get(k).getScoreDesired();
                int imp1 = importanceVal(q1.get(k).getScoreDesiredImportance());
                int imp2 = importanceVal(q2.get(k).getScoreDesiredImportance());
                selfSum1 += self1;
                selfSum2 += self2;
                desSum1 += des1;
                desSum2 += des2;
                impSum1 += imp1;
                impSum2 += imp2;
                score1 += matchScore(des1, self2, imp1);
                score2 += matchScore(des2, self1, imp2);
            }
            double matchPercentage1 = score1 / impSum1; //match percentage of j profile with i
            double matchPercentage2 = score2 / impSum2; // ^^ vice versa
            profiles.get(profileNumber).addMatch(new Match(profiles.get(j), matchPercentage1));
            profiles.get(j).addMatch(new Match(profiles.get(profileNumber), matchPercentage2));
        }
    }

    public static int matchScore(int desired, int other, int importance) {
        if (desired == other) {
            return importance;
        } else if (Math.abs(desired - other) == 1) {
            return importance / 2;
        } else {
            return 0;
        }
    }

    // giving the levels of importance a mathematical value
    public static int importanceVal(int v) {
        if (v == 5) {
            return 50;
        } else if (v == 2) {
            return 2;
        } else if (v == 3) {
            return 10;
        } else if (v == 4) {
            return 20;
        } else { //in the incredibly unlikely chance of a number other than 1-5 being here, i want it to be categorized as unimportant (lumped with values of 1)
            return 0;
        }
    }

    // public static Profile createProfile(Scanner s) { //make sure we create form responses so that we have the right format from the excel file/whatever type of file we use
//       String name = s.next();
//       int age = s.nextInt();
//       Profile newProfile = new Profile(name, age);
//       while (s.hasNext()) {
//          String qName = s.next();
//          System.out.println(qName);
//          int sScore = s.nextInt();
//          System.out.println(sScore);
//          int dScore = s.nextInt();
//          System.out.println(dScore);
//          int imp = s.nextInt();
//          System.out.println(imp);
//          // Question newQuestion = new Question(qName, sScore, dScore, imp);
//          // newProfile.addQuestion(newQuestion);
//
//         //adding the results and details of each question to the profiles
//          newProfile.addQuestion(new Question(qName, sScore, dScore, imp));
//       }
//       return newProfile;
//    }

}