/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangntk.controllers;

import hangntk.blos.CommentBLO;
import hangntk.blos.NotificationBLO;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ASUS
 */
public class CommentController extends HttpServlet {

    private static final String SUCCESS = "DetailArticleController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String comment = request.getParameter("comment");
            String postBy = request.getParameter("postBy");
            String postOn = request.getParameter("articleID");
            String notiUserEmail = request.getParameter("notiUserEmail");
            Date date = Calendar.getInstance().getTime();

            CommentBLO bloComment = new CommentBLO();
            if (bloComment.insertComment(postBy, postOn, comment, date)) {
                if (!postBy.equals(notiUserEmail)) {
                    NotificationBLO bloNotification = new NotificationBLO();
                    bloNotification.insert(postOn, postBy, date, "Comment: " + comment, notiUserEmail);
                }
                request.setAttribute("SUCCESS", "Comment success!");
            }
        } catch (Exception e) {
            request.setAttribute("ERROR", "Can't post this comment");
            log("ERROR at PostArticleController:" + e.getMessage());
        } finally {
            request.getRequestDispatcher(SUCCESS).forward(request, response);
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
