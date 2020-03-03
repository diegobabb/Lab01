<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>CRUDS</title>
        <%@include file="/head.jsp"%>     
        <%@include file="/validacionSession.jsp"%>   
    </head>
    <body>
        <%@include file="/agregarProfesor.jsp"%>
        <%@include file="/agregarCurso.jsp"%>
        
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark margin-bottom-3">
            <a class="navbar-brand" href="#">Sistema de matricula</a>
            <div class="ml-auto">
                 <button id="salir" class="btn btn-outline-danger my-2 my-sm-0" type="submit">Salir</button>
            </div>
        </nav>

        <div class="container margin-bottom-3">
                <ul class="nav nav-pills nav-justified">
                    <li id="li_profes" class="nav-item">
                        <a id="nav_profes" class="nav-link active" href="#profesores" data-toggle="tab" role="tab">
                            Profesores
                        </a>
                    </li>
                    <li id="li_cursos" class="nav-item">
                        <a id="nav_cursos"  class="nav-link " href="#cursos" data-toggle="tab" role="tab">
                             Cursos
                        </a>
                    </li>
                </ul>
        </div>

        <div id="profesores" class="container">

            <div class="margin-bottom-1" class="row">
                <button class="btn-sm btn-success"  id="agregarProfesorModal" type="button" data-toggle="modal" data-target="#modalAgregarProfesor">Agregar Profesor</button>
            </div>

            <div class="row">
                <table class="table">
                    <thead>
                        <tr>
                            <th>Cedula</th>
                            <th>Nombre</th>
                            <th>Telefono</th>
                            <th>Email</th>
                            <th>Editar</th>
                            <th>Eliminar</th>
                        </tr>
                    </thead>
                    <tbody id="contenido_profesor">

                    </tbody>
                </table>
            </div>
        </div>
        
        <div id="cursos" class="container">
              <div class="margin-bottom-1" class="row">
                <button class="btn-sm btn-success"  id="agregarCursoModal" type="button" data-toggle="modal" data-target="#modalAgregarCurso">Agregar Curso</button>
            </div>
            <div class="row">
                <table class="table">
                    <thead>
                        <tr>
                            <th>Codigo</th>
                            <th>Nombre</th>
                            <th>Creditos</th>
                            <th>Horas semanales</th>
                            <th>Editar</th>
                            <th>Eliminar</th>
                        </tr>
                    </thead>
                    <tbody id="contenido_curso">

                    </tbody>
                </table>
            </div>
        </div>
        
        
    </body>
</html>
