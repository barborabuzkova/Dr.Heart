package com.drheart.demo;

import java.io.*;
import java.util.ArrayList;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet(name = "MatchPage", value = "/MatchPage")
public class MatchPage extends HttpServlet {
   // private String message;
    private ArrayList<Profile> profiles;

    public void init() {
        profiles = new ArrayList<>();

        //temporary test code
        //this will be done by a file reader, has to be automated
        ArrayList<Question> exampleqs = new ArrayList<Question>();
        exampleqs.add(new Question("question0"));
        exampleqs.add(new Question("question1"));
        profiles.add(new Profile("Person0", "email1@seattleschools.org", "p1", 11, "bio1",
                "pul1", exampleqs));
        profiles.add(new Profile("Person1", "email2@seattleschools.org", "p2", 11, "bio2",
                "pul2", exampleqs));
        profiles.add(new Profile("Person3", "email3@seattleschools.org", "p3", 11, "bio3",
                "pul3", exampleqs));
        //this part will be done by the algorithm
        profiles.get(0).addMatch(new Match(profiles.get(1), .01));
        profiles.get(1).addMatch(new Match(profiles.get(0), .01));
        profiles.get(0).addMatch(new Match(profiles.get(2), .02));
        profiles.get(2).addMatch(new Match(profiles.get(0), .02));
        profiles.get(1).addMatch(new Match(profiles.get(2), .12));
        profiles.get(2).addMatch(new Match(profiles.get(1), .12));
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String emailProvided = request.getParameter("emailProvided");
        System.out.println("Email :: " + emailProvided);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        boolean emailFound = false;

        //table
        out.println("<html> <table border=1px> <tr> <th>Name</th> <th>Compatibility %</th> </tr>");
        for (int i = 0; i < profiles.size(); i++) {
            if (emailProvided.equals(profiles.get(i).getEmail())) {
                out.println(tableRow(profiles.get(i)));
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