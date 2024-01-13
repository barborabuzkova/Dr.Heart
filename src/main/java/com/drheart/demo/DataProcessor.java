package com.drheart.demo;

import java.io.*; //files
import java.util.*; //scan
//import org.json.simple.*;

public class DataProcessor { //can we get this to run when the excel file updates?

    public static void main(String[] args) throws FileNotFoundException{

//       String name = "";
//       int age = 0; //??
        Scanner scannerGeorg = new Scanner(new File("SampleFormData.csv"));

        String bob = scannerGeorg.nextLine();
        ArrayList<String> questions = new ArrayList<>();

        String[] lineOne = bob.split("\\s*,\\s*"); //regex
        //System.out.println(lineOne[0] + " " + lineOne[1]); //test line

        int x = 7;
        //System.out.println(lineOne.length); //test line
        while (x < lineOne.length){
            //System.out.println(lineOne[x]); //test line
            questions.add(lineOne[x]);
            x += 3;
        }
        //System.out.println(questions.toString()); //test line

        System.out.println(createProfile(scannerGeorg.nextLine(), questions)); //test tostring
    }


    public static Profile createProfile(String line, ArrayList<String> questions) throws NumberFormatException{

        String[] sally = line.split("\\s*,\\s*"); //regex again
        String name = sally[0];
        String email = sally[1];
        String pronouns = sally[2];
        int grade = Integer.parseInt(sally[3]); //parse string into int
        String bio = sally[4];
        String pickUpLine = sally[5];
        ArrayList<Question> pfQuestions = new ArrayList<Question>();
        for (int i = 0; i < questions.size(); i++){ //creating the questions for the arraylist
            pfQuestions.add(new Question(questions.get(i),Integer.parseInt(sally[7+(i*3)]),Integer.parseInt(sally[8+(i*3)]),Integer.parseInt(sally[9+(i*3)])));
        }
        Profile profilebob = new Profile(name,email,pronouns,grade,bio,pickUpLine,pfQuestions);
        return profilebob;
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