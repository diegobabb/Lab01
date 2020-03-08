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
    $(document).on("keyup","#filtroCurso", filtroCurso);
}


function obtenerCarrera(id) {
    switch (id) {
        case 1:
            return "Ingenieria En Sistemas";
        case 2:
            return "Administracion de Empresas";
        case 3:
            return "Matematicas";
        case 4:
            return "Ingles";
        case 5:
            return "Educacion";
    }
}

function listarCurso() {
    $.ajax({
        type: "GET",
        url: "matricula/curso",
        success: listarC,
        error: function () {
             swal("Error", "Error inesperado", "error");
        }
    });
}


function listarC(cursos) {
    var tbody = $("#contenido_curso");
    tbody.html("");
    cursos.forEach((p) => {
        rowC(tbody, p);
    });
}

function rowC(tbody, p) {

    var tr = $("<tr class='itemC' />");
    tr.html(
            "<td>" + p.codigo + " </td>" +
            "<td>" + p.nombre + "</td>" +
            "<td>" + p.credito + "</td>" +
            "<td>" + p.horas + "</td>" +
            "<td>" + obtenerCarrera(p.carrera) + "</td>" +
            "<td> <button class='btn btn-info btn-sm'" +
            "onclick='prepareEditarCurso(\"" + p.codigo + "\",\"" + p.nombre + "\",\"" + p.credito + "\",\"" + p.horas + "\",\"" + p.carrera + "\");'>Editar</button> </td>" +
            "<td> <button class='btn btn-danger btn-sm' onclick='eliminarCurso(\"" + p.codigo + "\");''>Eliminar</button> </td>"
            );
    tbody.append(tr);
}

function agregarCurso() {
    if (validaCurso() === true) {
        let curso = {
            codigo: $("#add_codigo").val().trim(),
            nombre: $("#add_nombrec").val().trim(),
            credito: parseInt($("#add_credito").val().trim()),
            horas: parseInt($("#add_horas").val().trim()),
            carrera: parseInt($("#add_carrera").children("option:selected").val())
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
}

function eliminarCurso(c) {
    let codigo = String(c).trim();

    $.ajax({
        type: "DELETE",
        url: "matricula/curso?codigo=" + codigo,
        success: function (data) {
            if (data === 1)
                swal("Error", "Ocurrio un error inesperado", "error");
            else {
                swal("Exito", "Curso eliminado correctamente", "success");
                actualizarPagCursos();
            }
        },
        error: function () {
            swal("Error", "Ocurrio un error inesperado", "error");
        }
    });
}

function prepareEditarCurso(codigo, nombre, credito, horas, carrera) {

    $("#add_codigo").val(codigo);
    $("#add_nombrec").val(nombre);
    $("#add_credito").val(credito);
    $("#add_horas").val(horas);
    $("#agregarCurso").hide();
    $("#modificarCurso").show();
    $('#modalAgregarCurso').modal('show');
    $("#add_codigo").prop("disabled", true);
    $("#add_carrera").val(carrera);

}

function editarCurso() {
    let nuevo_curso = {
        codigo: $("#add_codigo").val().trim(),
        nombre: $("#add_nombrec").val().trim(),
        credito: parseInt($("#add_credito").val().trim()),
        horas: parseInt($("#add_horas").val().trim()),
        carrera: parseInt($("#add_carrera").children("option:selected").val())
    };

    $.ajax(
            {type: "PUT",
                url: "matricula/curso",
                data: JSON.stringify(nuevo_curso),
                contentType: "application/json",
                success: function (resul) {
                    let r = parseInt(resul);
                    if (r) {
                        swal("Exito", "Curso actualizado correctamente", "success");
                        actualizarPagCursos();
                    } else
                        swal("Error", "Ocurrio un error inesperado", "error");
                },
                error: function () {
                    swal("Error", "Error inesperado", "error");
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
    $("#add_codigo").prop("disabled", false);
    $("#filtroCurso").val("");
}

function actualizarPagCursos() {
    var tbody = $("#contenido_curso");
    tbody.html("");
    escobaCurso();
    listarCurso();
    actualizar();
}


function validaCurso() {
    let curso = {
        codigo: $("#add_codigo").val().trim(),
        nombre: $("#add_nombrec").val().trim(),
        credito: parseInt($("#add_credito").val().trim()),
        horas: parseInt($("#add_horas").val().trim()),
        carrera: parseInt($("#add_carrera").children("option:selected").val())
    };

    if (curso.codigo.length > 5) {
        swal("Error", "El codigo no debe ser mayor a 5 caracteres", "warning");
        return false;
    }
    
    if(curso.nombre.length < 3 || curso.nombre.length > 50){
        swal("Error", "El nombre tiene una extension invalida", "warning");
        return false;
    }
    
   if(curso.credito > 5 || curso.credito < 2){
       swal("Error", "Credito invalido", "warning");
        return false;
   }
   
    if(curso.horas < 2 || curso.horas > 20){
       swal("Error", "Horas semanales invalidas", "warning");
        return false;
   }

    return true;
}

 function onlyNumberKey(evt) { 

    // Only ASCII charactar in that range allowed 
        var ASCIICode = (evt.which) ? evt.which : evt.keyCode 
        if (ASCIICode > 31 && (ASCIICode < 48 || ASCIICode > 57)) 
            return false; 
        return true; 
} 

function filtroCurso(){
   let tdata =  $("#filtroCurso_por option:selected" ).val();
   let busqueda = $("#filtroCurso").val().trim().toUpperCase();

    $("tr.itemC").each(function () {
        let dato = $(this).find(`td:eq(${tdata})`).text().toUpperCase();
        (dato.startsWith(busqueda)) ? $(this).show() : $(this).hide();
    });
}