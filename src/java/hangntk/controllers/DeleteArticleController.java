/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangntk.controllers;

import hangntk.blos.ArticleBLO;
import hangntk.blos.NotificationBLO;
import hangntk.entity.TblUser;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ASUS
 */
public class DeleteArticleController extends HttpServlet {

    private static final String SUCCESS = "ViewArticleController";
    private static final String ERROR = "error.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String articleID = request.getParameter("articleID");
            String notiUserID = request.getParameter("notiUserID");
            ArticleBLO blo = new ArticleBLO();
            if (blo.updateStatusDelete(articleID)) {
                request.setAttribute("SUCCESS", "Delete success!");
                HttpSession session = request.getSession();
                TblUser user = (TblUser) session.getAttribute("INFO");
                if (user.getUserRole().equals("Admin")) {
                    Date date = Calendar.getInstance().getTime();
                    NotificationBLO bloNotification = new NotificationBLO();
                    bloNotification.insert(articleID, user.getUserEmail(), date, "Delete Article", notiUserID);
                }
                url = SUCCESS;
            }
        } catch (Exception e) {
            request.setAttribute("ERROR", "Can't delete");
            log("ERROR at DeleteArticleController:" + e.getMessage());
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
