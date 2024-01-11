package com.drheart.demo;

import java.io.*; //files
import java.util.*; //scan

public class FileReader{ //can we get this to run when the excel file updates?

    public static void main(String[] args) throws FileNotFoundException{

        String name = "";
        int age = 0;
        Scanner scanner = new Scanner(new File("meowmoew.json"));

        // while (scanner.hasNext()){
//          scanner.next();
//          age = scanner.nextInt();
//          scanner.next();
//          name = scanner.next();
//       }

        // System.out.print(age + ", " + name);

        //these were also throwing errors and I commented them out
        //Profile kai = createProfile(scanner);
        //System.out.println(kai.toString());
    }

    //this was throwing errors cause I changed some stuff so I just commented it out.
//    public static Profile createProfile(Scanner s) { //make sure we create form responses so that we have the right format from the excel file/whatever type of file we use
//        String name = s.next();
//        int age = s.nextInt();
//        Profile newProfile = new Profile(name, age);
//        while (s.hasNext()) {
//            String qName = s.next();
//            int sScore = s.nextInt();
//            int dScore = s.nextInt();
//            int imp = s.nextInt();
//            // Question newQuestion = new Question(qName, sScore, dScore, imp);
//            // newProfile.addQuestion(newQuestion);
//            //adding the results and details of each question to the profiles
//            newProfile.addQuestion(new Question(qName, sScore, dScore, imp));
//        }
//        return newProfile;
//    }

}