/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).on("DOMContentLoaded", pageLoad);

function pageLoad() {
    listarCurso();
    $("#agregarCurso").on("click", agregarCurso);
    $("#modificarCurso").hide();
    $("#agregarCursoModal").on("click", escobaCurso);
    $("#modificarCurso").on("click", editarCurso);
}

function listarCurso() {
    $.ajax({
        type: "GET",
        url: "matricula/curso",
        success: listarC,
        error: function () {
            console.log("NEIN!");
        }
    });
}


function listarC(cursos) {
    console.log("llego un", cursos);
    var tbody = $("#contenido_curso");
    tbody.html("");
    cursos.forEach((p) => {
        rowC(tbody, p);
    });
}

function rowC(tbody, p) {
    var tr = $("<tr />");
    tr.html(
            "<td>" + p.codigo + " </td>" +
            "<td>" + p.nombre + "</td>" +
            "<td>" + p.credito + "</td>" +
            "<td>" + p.horas + "</td>" +
            "<td> <button class='btn btn-info btn-sm'" +
            "onclick='prepareEditarCurso(\"" + p.codigo + "\",\"" + p.nombre + "\",\"" + p.credito + "\",\"" + p.horas + "\");'>Editar</button> </td>" +
            "<td> <button class='btn btn-danger btn-sm' onclick='eliminarCurso(\"" + p.codigo + "\");''>Eliminar</button> </td>"
            );
    tbody.append(tr);
}

function agregarCurso() {
    
    let curso = {
        codigo: $("#add_codigo").val().trim(),
        nombre: $("#add_nombrec").val().trim(),
        credito: parseInt($("#add_credito").val().trim()),
        horas: parseInt($("#add_horas").val().trim()) 
    };

    $.ajax(
            {type: "POST",
                url: "matricula/curso",
                data: JSON.stringify(curso),
                contentType: "application/json",
                success: function (data) {
                    if (data == 0) {
                        swal("Error", "Datos invalidos", "error");
                    }
                    if (data == 1) {
                        swal("Exito", "Curso agregado correctamente", "success");
                        actualizarPagCursos();
                    }
                },
                error: function () {
                    swal("Error", "Ocurrio un error inesperado", "error");
                }
            });

}

function eliminarCurso(c){
     let codigo = String(c).trim();

     $.ajax({
        type: "DELETE",
        url: "matricula/curso?codigo=" + codigo,
        success: function (data) {
            if(data === 1)
                swal("Error", "Ocurrio un error inesperado", "error");
            else{
                swal("Exito", "Curso eliminado correctamente", "success");
                actualizarPagCursos();
            }
        },
        error: function () {
            swal("Error", "Ocurrio un error inesperado", "error");
        }
    });
}

function prepareEditarCurso(codigo, nombre, credito, horas){

    $("#add_codigo").val(codigo);
    $("#add_nombrec").val(nombre);
    $("#add_credito").val(credito);
    $("#add_horas").val(horas);
    $("#agregarCurso").hide();
    $("#modificarCurso").show();
    $('#modalAgregarCurso').modal('show');
    $("#add_codigo").prop( "disabled", true );
    
}

function editarCurso(){
    let nuevo_curso = {
        codigo: $("#add_codigo").val().trim(),
        nombre: $("#add_nombrec").val().trim(),
        credito: parseInt($("#add_credito").val().trim()),
        horas: parseInt($("#add_horas").val().trim())
    };
    
        $.ajax(
            {   type: "PUT",
                url: "matricula/curso",
                data: JSON.stringify(nuevo_curso),
                contentType: "application/json",
                success: function (resul) {
                    let r = parseInt(resul);
                    if(r)
                        actualizarPagCursos();
                    else
                        swal("Error", "Ocurrio un error inesperado", "error");
                },
                error: function () {
                    console.log("No se pudo.");
                }
            });
}


function escobaCurso() {
    $('#modalAgregarCurso').modal('hide');
    $("#add_codigo").val("");
    $("#add_nombrec").val("");
    $("#add_credito").val("");
    $("#add_horas").val("");
    $("#agregarCurso").show();
    $("#modificarCurso").hide();
    $("#add_codigo").prop( "disabled", false );
}

function actualizarPagCursos(){
    var tbody = $("#contenido_curso");
    tbody.html("");
    escobaCurso();
    listarCurso();
}

