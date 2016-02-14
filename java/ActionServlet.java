/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Dead Astronauts
 */
public class ActionServlet extends HttpServlet {

    static boolean userOneSleeping = true;
    static boolean userTwoSleeping = true;
    static HttpServletResponse userOneResponse = null;
    static HttpServletResponse userTwoResponse = null;
    static String value = null;
    static ArrayList<String> valueList = new ArrayList<String>();
    static int userOne = 0;
    static int userTwo = 0;
    //static ArrayList<String> valueTwo = new ArrayList<String>();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        value = request.getParameter("new_value");
        String id = request.getParameter("ids");
        id = id.trim();
        System.out.println("#######################   " + id);
        if (id.equals("user1")) {
            userOne++;
            userOneResponse = response;
            valueList.add(value);
            if (userTwoSleeping && userTwoResponse != null) {
                if (userTwoSleeping && userTwoResponse == null) {
                    while (userTwoResponse == null);
                }
                write(userTwoResponse, valueList);
                userTwoSleeping = false;
            }
            userOneSleeping = true;
            while (userOneSleeping);
        } else {
            userTwo++;
            userTwoResponse = response;
            valueList.add(value);
            if (userOneSleeping && userOneResponse != null) {
                if (userOneSleeping && userOneResponse == null) {
                    while (userOneResponse == null);
                }
                write(userOneResponse, valueList);
                userOneSleeping = false;
            }
            userTwoSleeping = true;
            while (userTwoSleeping);
        }
    }

    private static void write(HttpServletResponse response, ArrayList<String> arrays)
            throws ServletException, IOException {
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        for (String str : arrays) {
            System.out.println(str);
            response.getWriter().write("<div class=\"appendData\">" + str + "<br/></div>");
        }
        //arrays.clear();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
