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

            <div id="margin-fit-negative" class="row">
                <div class="col-2">
                    <div class="md-form  mb-4">
                        <label for="email">Filtro</label>
                    </div>
                </div>

                <div class="col-8">
                    <div class="md-form  mb-4">
                        <div class="md-form  mb-4">
                            <label for="email">Busqueda</label>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-2">
                    <div class="md-form  mb-4">
                        <select class="form-control" id="filtroProfe_por">
                            <option value="0">Cedula</option>
                            <option value="1">Nombre</option>
                            <option value="2">Telefono</option>
                            <option value="3">Email</option>
                            <option value="4">Curso</option>
                        </select>

                    </div>
                </div>

                <div class="col-8">
                    <div class="md-form  mb-4">
                        <div class="md-form  mb-4">
                            <input type="text" id="filtroProfe" class="form-control" autocomplete="false">
                        </div>
                    </div>
                </div>

                <div class="col-2">
                    <div class="margin-bottom-1" class="row">
                        <button class="btn-sm btn-success"  id="agregarProfesorModal" type="button" data-toggle="modal" data-target="#modalAgregarProfesor">Agregar Profesor</button>
                    </div>

                </div>
            </div>

            <div class="row">
                <table id="tabla_profes" class="table">
                    <thead>
                        <tr>
                            <th onclick="w3.sortHTML('#tabla_profes', '.itemP', 'td:nth-child(1)')">Cedula</th>
                            <th onclick="w3.sortHTML('#tabla_profes', '.itemP', 'td:nth-child(2)')">Nombre</th>
                            <th onclick="w3.sortHTML('#tabla_profes', '.itemP', 'td:nth-child(3)')">Telefono</th>
                            <th onclick="w3.sortHTML('#tabla_profes', '.itemP', 'td:nth-child(4)')">Email</th>
                            <th onclick="w3.sortHTML('#tabla_profes', '.itemP', 'td:nth-child(5)')">Curso</th>
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

            <div id="margin-fit-negative" class="row">
                <div class="col-2">
                    <div class="md-form  mb-4">
                        <label for="email">Filtro</label>
                    </div>
                </div>

                <div class="col-8">
                    <div class="md-form  mb-4">
                        <div class="md-form  mb-4">
                            <label for="email">Busqueda</label>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-2">
                    <div class="md-form  mb-4">
                        <select class="form-control" id="filtroCurso_por">
                            <option value="0">Codigo</option>
                            <option value="1">Nombre</option>
                            <option value="2">Creditos</option>
                            <option value="3">Horas Semanales</option>
                            <option value="4">Carrera</option>
                        </select>

                    </div>
                </div>

                <div class="col-8">
                    <div class="md-form  mb-4">
                        <div class="md-form  mb-4">
                            <input type="text" id="filtroCurso" class="form-control" autocomplete="false">
                        </div>
                    </div>
                </div>

                <div class="col-2">
                    <div class="margin-bottom-1" class="row">
                        <button class="btn-sm btn-success"  id="agregarCursoModal" type="button" data-toggle="modal" data-target="#modalAgregarCurso">Agregar Curso</button>
                    </div>
                </div>
            </div>


            <div class="row">
                <table id="tabla_cursos" class="table">
                    <thead>
                        <tr>
                            <th onclick="w3.sortHTML('#tabla_cursos', '.itemC', 'td:nth-child(1)')">Codigo</th>
                            <th onclick="w3.sortHTML('#tabla_cursos', '.itemC', 'td:nth-child(2)')">Nombre</th>
                            <th onclick="w3.sortHTML('#tabla_cursos', '.itemC', 'td:nth-child(3)')">Creditos</th>
                            <th onclick="w3.sortHTML('#tabla_cursos', '.itemC', 'td:nth-child(4)')">Horas semanales</th>
                            <th onclick="w3.sortHTML('#tabla_cursos', '.itemC', 'td:nth-child(5)')">Carrera</th>
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
