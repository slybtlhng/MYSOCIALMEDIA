/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangntk.filter;

import hangntk.entity.TblUser;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ASUS
 */
public class RoleFilter implements Filter {

    private static final boolean debug = true;
    private static final String LOGIN = "index.jsp";
    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;
    List<String> guest;
    List<String> member;
    List<String> admin;

    public RoleFilter() {
        guest = new ArrayList<>();
        guest.add("index.jsp");
        guest.add("error.jsp");
        guest.add("register.jsp");
        guest.add("verify.jsp");
        guest.add("Login");
        guest.add("Regist");
        guest.add("Verify");
        guest.add("LoginController");
        guest.add("MainController");
        guest.add("RegistController");
        guest.add("VerifyController");

        member = new ArrayList<>();
        member.add("detail.jsp");
        member.add("member.jsp");
        member.add("notification.jsp");
        member.add("error.jsp");
        member.add("Logout");
        member.add("View");
        member.add("Notification");
        member.add("Search");
        member.add("Detail");
        member.add("Comment");
        member.add("AddEmotion");
        member.add("DeleteArticle");
        member.add("DeleteComment");
        member.add("AddEmotionController");
        member.add("CommentController");
        member.add("DeleteArticleController");
        member.add("DeleteCommentController");
        member.add("DetailArticleController");
        member.add("LogoutController");
        member.add("MainController");
        member.add("PostArticleController");
        member.add("SearchController");
        member.add("ViewArticleController");
        member.add("ViewNotificationController");

        admin = new ArrayList<>();
        admin.add("detail.jsp");
        admin.add("member.jsp");
        admin.add("notification.jsp");
        admin.add("error.jsp");
        admin.add("View");
        admin.add("Notification");
        admin.add("Search");
        admin.add("Detail");
        admin.add("DeleteComment");
        admin.add("DeleteArticle");
        admin.add("Logout");
        admin.add("DeleteArticleController");
        admin.add("DeleteCommentController");
        admin.add("DetailArticleController");
        admin.add("LogoutController");
        admin.add("MainController");
        admin.add("SearchController");
        admin.add("ViewArticleController");
        admin.add("ViewNotificationController");
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("RoleFilter:DoBeforeProcessing");
        }

        // Write code here to process the request and/or response before
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log items on the request object,
        // such as the parameters.
        /*
	for (Enumeration en = request.getParameterNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    String values[] = request.getParameterValues(name);
	    int n = values.length;
	    StringBuffer buf = new StringBuffer();
	    buf.append(name);
	    buf.append("=");
	    for(int i=0; i < n; i++) {
	        buf.append(values[i]);
	        if (i < n-1)
	            buf.append(",");
	    }
	    log(buf.toString());
	}
         */
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("RoleFilter:DoAfterProcessing");
        }

        // Write code here to process the request and/or response after
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log the attributes on the
        // request object after the request has been processed. 
        /*
	for (Enumeration en = request.getAttributeNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    Object value = request.getAttribute(name);
	    log("attribute: " + name + "=" + value.toString());

	}
         */
        // For example, a filter might append something to the response.
        /*
	PrintWriter respOut = new PrintWriter(response.getWriter());
	respOut.println("<P><B>This has been appended by an intrusive filter.</B>");
         */
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String url = LOGIN;
        String uri = req.getRequestURI();
        int index = uri.lastIndexOf("/");
        String resource = uri.substring(index + 1);
        String action = req.getParameter("action");

        if (uri.contains("Controller")) {
            url = "//" + resource;
        }

        HttpSession session = req.getSession();
        TblUser user = (TblUser) session.getAttribute("INFO");
        String role = null;
        if (user != null) {
            role = user.getUserRole();
        }
        if (uri.endsWith("jpg") || uri.endsWith("js") || uri.contains("css") || uri.endsWith("png")) {
            chain.doFilter(request, response);
            return;
        }
        if (role == null) {
            if (action != null) {
                if (!guest.contains(resource) || !guest.contains(action)) {
                    url = LOGIN;
                }
            } else if (uri.endsWith("jsp") && guest.contains(resource)) {
                url = resource;
            }else{
                url=LOGIN;
            }
        } else {
            if (role.equals("Member")) {
                if (!member.contains(action) || !member.contains(resource)) {
                    url = "MainController?action=View";
                } else if (action == null) {
                    url = "MainController?action=View";
                }
            } else if (role.equals("Admin")) {
                if (!admin.contains(action) || !admin.contains(resource)) {
                    url = "MainController?action=View";
                } else if (action == null) {
                    url = "MainController?action=View";
                }
            }
        }
        if (url != null) {
            req.getRequestDispatcher(url).forward(request, response);
        } else {
            chain.doFilter(request, response); //ep chain k chay de k gui them request toi cho minh mong muon
        }
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("RoleFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("RoleFilter()");
        }
        StringBuffer sb = new StringBuffer("RoleFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

}
