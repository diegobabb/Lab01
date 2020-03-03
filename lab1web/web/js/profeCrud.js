$(document).on("DOMContentLoaded", pageLoad);

function pageLoad() {
    listarProfesor();
    $("#agregarProfesor").on("click", agregarProfesor);
    $("#modificarProfesor").hide();
    $("#agregarProfesorModal").on("click", escoba);
    $("#modificarProfesor").on("click", editarProfesor);
}

function listarProfesor() {
    $.ajax({
        type: "GET",
        url: "matricula/profesor",
        success: listar,
        error: function () {
            console.log("NEIN!");
        }
    });
}


function listar(profesores) {
    var tbody = $("#contenido_profesor");
    tbody.html("");
    profesores.forEach((p) => {
        row(tbody, p);
    });
}

function row(tbody, p) {
    var tr = $("<tr />");
    tr.html(
            "<td>" + p.cedula + " </td>" +
            "<td>" + p.nombre + "</td>" +
            "<td>" + p.telefono + "</td>" +
            "<td>" + p.email + "</td>" +
            "<td> <button class='btn btn-info btn-sm'" +
            "onclick='prepareEditarProfe(\"" + p.cedula + "\",\"" + p.nombre + "\",\"" + p.telefono + "\",\"" + p.email + "\");'>Editar</button> </td>" +
            "<td> <button class='btn btn-danger btn-sm' onclick='eliminarProfe(\"" + p.cedula + "\");''>Eliminar</button> </td>"
            );
    tbody.append(tr);
}

function agregarProfesor() {
    
    let profesor = {
        cedula: $("#add_cedula").val(),
        nombre: $("#add_nombre").val(),
        telefono: $("#add_telefono").val(),
        email: $("#add_email").val()
    };

    $.ajax(
            {type: "POST",
                url: "matricula/profesor",
                data: JSON.stringify(profesor),
                contentType: "application/json",
                success: function (data) {
                    if (data == -1) {
                        swal("Error", "Datos invalidos", "error");
                    }
                    if (data == 1) {
                        swal("Exito", "Profesor agregado correctamente", "success");
                       actualizar();
                    }
                },
                error: function () {
                    swal("Error", "Ocurrio un error inesperado", "error");
                }
            });

}

function eliminarProfe(c){
     let cedula = String(c).trim();

     $.ajax({
        type: "DELETE",
        url: "matricula/profesor?cedula=" + cedula,
        success: function (data) {
            if(data === 1)
                swal("Error", "Ocurrio un error inesperado", "error");
            else{
                swal("Exito", "Profesor eliminado correctamente", "success");
                actualizar();
            }
        },
        error: function () {
            swal("Error", "Ocurrio un error inesperado", "error");
        }
    });
}

function prepareEditarProfe(cedula, nombre, telefono, email){

    $("#add_cedula").val(cedula);
    $("#add_nombre").val(nombre);
    $("#add_telefono").val(telefono);
    $("#add_email").val(email);
    $("#agregarProfesor").hide();
    $("#modificarProfesor").show();
    $('#modalAgregarProfesor').modal('show');
    $("#add_cedula").prop( "disabled", true );
    
}

function editarProfesor(){
    let nuevo_profe = {
        ID: 0,
        cedula: $("#add_cedula").val().trim(),
        nombre: $("#add_nombre").val().trim(),
        telefono: $("#add_telefono").val().trim(),
        email: $("#add_email").val().trim()
    };
    
        $.ajax(
            {   type: "PUT",
                url: "matricula/profesor",
                data: JSON.stringify(nuevo_profe),
                contentType: "application/json",
                success: function (resul) {
                    let r = parseInt(resul);
                    if(r)
                        actualizar();
                    else
                        swal("Error", "Ocurrio un error inesperado", "error");
                },
                error: function () {
                    console.log("No se pudo.");
                }
            });
}


function escoba() {
    $('#modalAgregarProfesor').modal('hide');
    $("#add_cedula").val("");
    $("#add_nombre").val("");
    $("#add_telefono").val("");
    $("#add_email").val("");
    $("#agregarProfesor").show();
    $("#modificarProfesor").hide();
    $("#add_cedula").prop( "disabled", false );
}

function actualizar(){
    var tbody = $("#contenido_profesor");
    tbody.html("");
    escoba();
    listarProfesor();
}

