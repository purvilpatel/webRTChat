
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Dead Astronauts
 */
public class HelperServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        //if (request.getParameter("new_value").toString().equals("DOWNLOAD")) {
        System.out.println("download called");
        download(response, ActionServlet.valueList);
        response.getWriter().write("the-file-name.txt");
        ServletContext application = getServletConfig().getServletContext();
        application.removeAttribute("user1");
        application.removeAttribute("user2");
        return;
        //}
        
    }

    private static void download(HttpServletResponse response, ArrayList<String> arrays) {
        response.setContentType("text/html");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException ex) {
            Logger.getLogger(HelperServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        String filename = "chat.txt";
        response.setContentType("APPLICATION/OCTET-STREAM");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
        System.out.println("#%@#$@#%@#%@      " + arrays.size());
        for (String str : arrays) {
            str = str.trim();
            writer.println(str + "\n");
            System.out.println(str);
        }
        writer.close();
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
