<%-- 
    Document   : index
    Created on : 01/03/2020, 11:36:05 AM
    Author     : jorac
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>LOGIN</title>
        <%@include file="/head.jsp"%>
       
    </head>
    <body>
        <nav id="brand" class="navbar navbar-expand-lg navbar-dark bg-dark margin-bottom-3">
            <a class="navbar-brand" href="#">Sistema de matricula</a>
        </nav>

        <div id="login" class="container">

            <div class="row">
                <div class="col-3"></div>
                <div class="col-6"> <form class="text-center" action="#!">

                        <h4 class="h4 mb-4">Ingresar</h4>

                        <!-- Email -->
                        <input type="text" id="cedula_login" class="form-control mb-4" placeholder="Cedula">

                        <!-- Password -->
                        <input type="password" id="clave_login" class="form-control mb-4" placeholder="Clave">

                        <!-- Sign in button -->
                        <button id="entrar" class="btn btn-info btn-block my-4" type="submit">Ingresar</button>


                    </form></div>
                <div class="col-3"></div>

            </div>


        </div>
    </body>
</html>
