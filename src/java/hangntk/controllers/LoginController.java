/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangntk.controllers;

import hangntk.blos.UserBLO;
import hangntk.entity.TblUser;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ASUS
 */
public class LoginController extends HttpServlet {

    private static final String FAIL = "index.jsp";
    private static final String USER = "ViewArticleController";//sua lai thanh DisplayArticle

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = FAIL;
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            TblUser u = new TblUser();
            String encyptPass = u.encryptPass(password);

            UserBLO blo = new UserBLO();
            u = blo.checkLogin(email, encyptPass);

            if (u != null) {
                HttpSession session = request.getSession();
                session.setAttribute("INFO", u);
                if (u.getUserRole().equals("Member")||u.getUserRole().equals("Admin")) {
                    url = USER;
                } else {
                    request.setAttribute("ERROR", "Invalid Role");
                }
            }
        } catch (Exception e) {
            request.setAttribute("ERROR", "Email or Password wrong");
            log("ERROR at LoginController:" + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
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
