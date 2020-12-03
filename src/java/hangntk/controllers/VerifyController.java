/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangntk.controllers;

import hangntk.blos.UserBLO;
import hangntk.entity.TblUser;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.mail.internet.InternetAddress;

/**
 *
 * @author ASUS
 */
public class VerifyController extends HttpServlet {

    private static final String SUCCESS = "verify.jsp";
    private static final String FAIL = "register.jsp";
    private static final String EMAIL = "hangnguyenkute3@gmail.com";
    private static final String PASSWORD = "ufqzbllsocplczpq";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = FAIL;
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String fullname = request.getParameter("fullname");
            request.setAttribute("FULLNAME", fullname);

            UserBLO blo = new UserBLO();
            TblUser u = new TblUser(email, "Member", fullname, password, "New");
            String encrypPass = u.encryptPass(password);
            u.setUserPass(encrypPass);
            TblUser check = blo.findByID(email);
            
            if (check == null) {
                HttpSession session = request.getSession();
                session.setAttribute("ENTITY", u);
                Properties p = new Properties();
                p.put("mail.smtp.auth", "true");
                p.put("mail.smtp.starttls.enable", "true");
                p.put("mail.smtp.host", "smtp.gmail.com");
                p.put("mail.smtp.port", 587);
                Session s = Session.getInstance(p,
                        new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(EMAIL, PASSWORD);
                    }
                });
                Message msg = new MimeMessage(s);
                msg.setFrom(new InternetAddress(EMAIL));
                msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
                msg.setSubject("Mail Verify");
                int code = (new Random().nextInt((9999 - 1000) + 1) + 1000);
                session.setAttribute("CODE", code);
                msg.setText(code + "");
                Transport.send(msg);
                url = SUCCESS;
            } else {
                request.setAttribute("ERROR", "Email already exists!");
            }
        } catch (Exception e) {
            log("ERROR at RegistController:" + e.getMessage());
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
