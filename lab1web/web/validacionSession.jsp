<%-- 
    Document   : validacionSession
    Created on : 01/03/2020, 08:26:47 PM
    Author     : jorac
--%>

   <%
            if (((String) session.getAttribute("usuario")) == null) {
                request.getSession().invalidate();
                response.sendRedirect("index.jsp");
            }
    %>