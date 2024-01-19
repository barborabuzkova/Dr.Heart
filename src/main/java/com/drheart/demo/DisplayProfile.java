package com.drheart.demo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@WebServlet(name = "DisplayProfile", value = "/DisplayProfile")
public class DisplayProfile extends HttpServlet {
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
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String emailProvided = request.getParameter("emailProvided");
        System.out.println("Email :: " + emailProvided);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        for (Profile p : profiles.values()) {
            if (emailProvided.equals(p.getEmail())) {
                Profile toDisplay = p;
                out.println("<html> <body style=\"background-color: lavender\"> <font size=5>" +
                        "<div style=\"text-align: center;\">");
                out.print("Name: " + toDisplay.getName());
                out.print("<br> Email: " + toDisplay.getEmail());
                out.print("<br> Grade: " + toDisplay.getGrade());
                out.print("<br> Pronouns: " + toDisplay.getPronouns());
                out.print("<br> Bio: " + toDisplay.getBio());
                out.print("<br> Pickup Line: " + toDisplay.getPickUpLine());
                out.print("</div> </font> </body> </html>");
            }
        }
    }
}
