/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangntk.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author ASUS
 */
public class MainController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String VIEW="ViewArticleController";
    private static final String LOGIN = "LoginController";
    private static final String LOGOUT = "LogoutController";
    private static final String REGIST = "RegistController";
    private static final String SEARCH = "SearchController";
    private static final String POST_ARTICLE = "PostArticleController";
    private static final String DETAIL_ARTICLE = "DetailArticleController";
    private static final String COMMENT = "CommentController";
    private static final String EMOTION = "AddEmotionController";
    private static final String DELETE_ARTICLE = "DeleteArticleController";
    private static final String DELETE_COMMENT = "DeleteCommentController";
    private static final String NOTI="ViewNotificationController";
    private static final String VERIFY="VerifyController";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            boolean isMultiPart = ServletFileUpload.isMultipartContent(request);

            if (isMultiPart) {
                url = POST_ARTICLE;
            } else {
                String action = request.getParameter("action");
                if (action.equals("Login")) {
                    url = LOGIN;
                } else if (action.equals("View")) {
                    url = VIEW;
                }else if (action.equals("Verify")) {
                    url = VERIFY;
                }else if (action.equals("Notification")) {
                    url = NOTI;
                }else if (action.equals("Regist")) {
                    url = REGIST;
                } else if (action.equals("Logout")) {
                    url = LOGOUT;
                } else if (action.equals("Search")) {
                    url = SEARCH;
                } else if (action.equals("Detail")) {
                    url = DETAIL_ARTICLE;
                } else if (action.equals("Comment")) {
                    url = COMMENT;
                } else if (action.equals("AddEmotion")) {
                    url = EMOTION;
                } else if (action.equals("DeleteArticle")) {
                    url = DELETE_ARTICLE;
                } else if (action.equals("DeleteComment")) {
                    url = DELETE_COMMENT;
                } else {
                    request.setAttribute("ERROR", "Invalid action");
                }
            }
        } catch (Exception e) {
            log("ERROR at MainController:" + e.getMessage());
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
