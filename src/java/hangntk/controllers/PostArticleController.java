/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangntk.controllers;

import hangntk.blos.ArticleBLO;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author ASUS
 */
public class PostArticleController extends HttpServlet {

    private static final String SUCCESS = "ViewArticleController";
    private static final String FAIL = "error.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = FAIL;
        try {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            List items = null;

            items = upload.parseRequest(request);

            Iterator iter = items.iterator();
            Hashtable params = new Hashtable();
            String fileName = null;
            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();
                if (item.isFormField()) {
                    params.put(item.getFieldName(), item.getString("UTF-8"));
                } else {
                    String itemName = item.getName();
                    fileName = itemName.substring(itemName.lastIndexOf("\\") + 1);
                    if (!fileName.equals("")) {
                        fileName = System.currentTimeMillis() + fileName;
                        String path = getServletContext().getRealPath("/");
                        File desFile = new File(path+"image\\"+ fileName);
                        item.write(desFile);
                    }
                }
            }
            Date date = Calendar.getInstance().getTime();
            String titile = (String) params.get("title");
            String description = (String) params.get("description");

            String postBy = (String) params.get("postBy");
            ArticleBLO bloArticle = new ArticleBLO();
            if (bloArticle.insertArticle(titile, description, fileName, date, postBy)) {
                url = SUCCESS;
                request.setAttribute("SUCCESS", "Post Success!");
            }
        } catch (Exception e) {
            request.setAttribute("ERROR", "Can't post this article");
            log("ERROR at PostArticleController:" + e.getMessage());
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
