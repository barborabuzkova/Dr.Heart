package com.drheart.demo;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet(name = "MatchPage", value = "/MatchPage")
public class MatchPage extends HttpServlet {
   // private String message;
    private Map<String, Profile> profiles;

    private int numberOfQuestions;

    @Override
    public void init() {
        profiles = new HashMap<>();

        // main file: "/Users/babu/IdeaProjects/demo/src/main/resources/MainFileForIDS.csv"
        // other files: "/Users/babu/IdeaProjects/demo/src/main/resources/" + this.email + ".csv"
        // questions: "/Users/babu/IdeaProjects/demo/src/main/resources/QuestionsFile.csv"

        //read in profile ids
        String[] profilesFromIDS;
        InputStream mainFileStream = getClass().getResourceAsStream("/MainFileForIDS.csv");
        if (mainFileStream == null) {
            profilesFromIDS = null;
        } else {
            Scanner mainFileScanner = new Scanner(mainFileStream);
            profilesFromIDS = mainFileScanner.nextLine().split("\\s*,\\s*");
        }

        //get the number of questions
        InputStream questionsFileStream = getClass().getResourceAsStream("/QuestionsFile.csv");
        if (questionsFileStream == null) {
            numberOfQuestions = 0;
        } else {
            Scanner questionScanner = new Scanner(questionsFileStream);
            String[] temp = questionScanner.nextLine().split("\\s*,\\s*");
            numberOfQuestions = temp.length;
        }

        //loop through all profiles found and creates them
        // this might need to be array.length - 1
        for (int i = 0; i < profilesFromIDS.length; i++) {
            String[] profileInformation;
            InputStream profileInformationStream = getClass().getResourceAsStream("/" + profilesFromIDS[i] + ".csv");
            if (profileInformationStream == null) {
                profileInformation = null;
            } else {
                Scanner profileInformationScanner = new Scanner(profileInformationStream);
                profileInformation = profileInformationScanner.nextLine().split("\\s*,\\s*");
            }

            int currentLocInProfile = 0;
            //get information
            String name = profileInformation[currentLocInProfile ++]; //0
            String pronouns = profileInformation[currentLocInProfile ++]; //1
            int grade = Integer.parseInt(profileInformation[currentLocInProfile ++]); // 2 //parse string into int
            String bio = profileInformation[currentLocInProfile ++]; //3
            String pickUpLine = profileInformation[currentLocInProfile ++]; //4

            //create profile
            profiles.put(profilesFromIDS[i], new Profile(name, profilesFromIDS[i], pronouns, grade, bio, pickUpLine));
        }

        // adds all matches, once again it might have to be array.length - 1
        for (Profile p : profiles.values()) {
            String[] profileInformation;
            InputStream profileInformationStream = getClass().getResourceAsStream("/" + p.getEmail() + ".csv");
            if (profileInformationStream == null) {
                profileInformation = null;
            } else {
                Scanner profileInformationScanner = new Scanner(profileInformationStream);
                profileInformation = profileInformationScanner.nextLine().split("\\s*,\\s*");
            }


            //skip profile creation and question information
            int currentLocInProfile = 5 + 4 * numberOfQuestions;

            //add matches
            while (currentLocInProfile < profileInformation.length) {
                String email = profileInformation[currentLocInProfile];
                Double matchPercentage = Double.parseDouble(profileInformation[currentLocInProfile + 1]);
                p.addMatchWithoutPrint(new Match(profiles.get(email), matchPercentage));
                currentLocInProfile += 2;
            }
        }

//        //temporary test code
//        profiles.add(new Profile("Person0", "email1@seattleschools.org", "p1", 11, "bio1",
//                "pul1"));
//        profiles.add(new Profile("Person1", "email2@seattleschools.org", "p2", 11, "bio2",
//                "pul2"));
//        profiles.add(new Profile("Person3", "email3@seattleschools.org", "p3", 11, "bio3",
//                "pul3"));
//        //this part will be done by the algorithm
//        profiles.get(0).addMatch(new Match(profiles.get(1), .01));
//        profiles.get(1).addMatch(new Match(profiles.get(0), .01));
//        profiles.get(0).addMatch(new Match(profiles.get(2), .02));
//        profiles.get(2).addMatch(new Match(profiles.get(0), .02));
//        profiles.get(1).addMatch(new Match(profiles.get(2), .12));
//        profiles.get(2).addMatch(new Match(profiles.get(1), .12));
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String emailProvided = request.getParameter("emailProvided");
        System.out.println("Email :: " + emailProvided);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        boolean emailFound = false;

        //table
        out.println("<html> <table border=1px> <tr> <th>Name</th> <th>Compatibility %</th> </tr>");
        for (Profile p : profiles.values()) {
            if (emailProvided.equals(p.getEmail())) {
                out.println(tableRow(p));
                emailFound = true;
            }
        }
        out.println("</table> <br/>");
        //if the email isn't there it should send you back to index.jsp with a message
        if (!emailFound) {
            getServletContext().setAttribute("message", "email not found: " + emailProvided);
            response.sendRedirect(request.getContextPath());
        }
        out.println("<a href = "+request.getContextPath()+"> back </a>");
        out.println("</html>");
    }

    private String tableRow (Profile profile) {
        StringBuilder result = new StringBuilder();
        for (Match i : profile.getMatches()) {
            result.append("<tr> <td>" + i.getOtherProfile().getName() + "</td>" +
                    "<td>" + i.getMatchPercentage() + "</td> </tr>");
        }
        return result.toString();
    }

}