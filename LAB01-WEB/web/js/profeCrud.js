$(document).on("DOMContentLoaded", pageLoad);

function pageLoad() {
    listarProfesor();
    $("#agregarProfesor").on("click", agregarProfesor);
    $("#modificarProfesor").hide();
    $("#agregarProfesorModal").on("click", escoba);
    $("#modificarProfesor").on("click", editarProfesor);
    completarCursos();
    $(document).on("keyup","#filtroProfe", filtroProfe);
}

function completarCursos() {
    $.ajax({
        type: "GET",
        url: "matricula/curso",
        success: function (cursos) {
            var select = $("#add_curso");
            select.html("");
            select.append("<option value='0' selected>Ninguna</option>");
            cursos.forEach((c) => {
                select.append("<option value='" + c.codigo + "'>" + c.nombre + " </option>");
            });
        },
        error: function () {
            swal("Error", "Ocurrio un error inesperado al cargar el contenido de la pagina", "error");
        }
    });
}

function listarProfesor() {

    $.ajax({
        type: "GET",
        url: "matricula/profesor",
        async: false,
        success: listar,
        error: function () {
            swal("Error", "Ocurrio un error inesperado al cargar el contenido de la pagina", "error");
        }
    });
}

function obtenerCurso(id) {
    return $.ajax({
        type: "GET",
        url: "matricula/curso/" + id,
        async: false}).responseJSON;
}

function listar(profesores) {
    var tbody = $("#contenido_profesor");
    tbody.html("");
    profesores.forEach((p) => {
        row(tbody, p);
    });
}

function row(tbody, p) {
    let curso = (p.curso === undefined) ? "No asignado" : obtenerCurso(p.curso).nombre;

    var tr = $("<tr class='itemP' />");
    tr.html(
            "<td>" + p.cedula + " </td>" +
            "<td>" + p.nombre + "</td>" +
            "<td>" + p.telefono + "</td>" +
            "<td>" + p.email + "</td>" +
            "<td>" + curso + "</td>" +
            "<td> <button class='btn btn-info btn-sm'" +
            "onclick='prepareEditarProfe(\"" + p.cedula + "\",\"" + p.nombre + "\",\"" + p.telefono + "\",\"" + p.email + "\",\"" + p.curso + "\");'>Editar</button> </td>" +
            "<td> <button class='btn btn-danger btn-sm' onclick='eliminarProfe(\"" + p.cedula + "\");''>Eliminar</button> </td>"
            );
    tbody.append(tr);
}

function agregarProfesor() {
    if (validaProfe()) {
        let profesor = {
            cedula: $("#add_cedula").val(),
            nombre: $("#add_nombre").val(),
            telefono: $("#add_telefono").val(),
            email: $("#add_email").val(),
            curso: String($("#add_curso").children("option:selected").val()).trim()
        };

        if (profesor.curso == 0)
            profesor.curso = null;


        $.ajax(
                {type: "POST",
                    url: "matricula/profesor",
                    data: JSON.stringify(profesor),
                    contentType: "application/json",
                    success: function (data) {
                        if (data == -1) {
                            swal("Error", "El usuario con cedula " + profesor.cedula + " ya existe", "error");
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
}

function eliminarProfe(c) {
    let cedula = String(c).trim();

    $.ajax({
        type: "DELETE",
        url: "matricula/profesor?cedula=" + cedula,
        success: function (data) {
            if (data === 1)
                swal("Error", "Ocurrio un error inesperado", "error");
            else {
                swal("Exito", "Profesor eliminado correctamente", "success");
                actualizar();
            }
        },
        error: function () {
            swal("Error", "Ocurrio un error inesperado", "error");
        }
    });
}

function prepareEditarProfe(cedula, nombre, telefono, email, curso) {

    $("#add_cedula").val(cedula);
    $("#add_nombre").val(nombre);
    $("#add_telefono").val(telefono);
    $("#add_email").val(email);
    $("#agregarProfesor").hide();
    $("#modificarProfesor").show();
    $('#modalAgregarProfesor').modal('show');
    $("#add_cedula").prop("disabled", true);

    if (curso === "undefined")
        $("#add_curso").val("0");
    else
        $("#add_curso").val(curso);



}

function editarProfesor() {
    if (validaProfe()) {
        let nuevo_profe = {
            ID: 0,
            cedula: $("#add_cedula").val().trim(),
            nombre: $("#add_nombre").val().trim(),
            telefono: $("#add_telefono").val().trim(),
            email: $("#add_email").val().trim(),
            curso: String($("#add_curso").children("option:selected").val()).trim()
        };

        if (nuevo_profe.curso == 0)
            nuevo_profe.curso = null;

        $.ajax(
                {type: "PUT",
                    url: "matricula/profesor",
                    data: JSON.stringify(nuevo_profe),
                    contentType: "application/json",
                    success: function (resul) {
                        let r = parseInt(resul);
                        if (r)
                            actualizar();
                        else
                            swal("Error", "Ocurrio un error inesperado", "error");
                    },
                    error: function () {
                        swal("Error", "Ocurrio un error inesperado", "error");

                    }
                });
    }

}


function escoba() {
    $('#modalAgregarProfesor').modal('hide');
    $("#add_cedula").val("");
    $("#add_nombre").val("");
    $("#add_telefono").val("");
    $("#add_email").val("");
    $("#agregarProfesor").show();
    $("#modificarProfesor").hide();
    $("#filtroProfe").val("");
    $("#add_cedula").prop("disabled", false);
}

function actualizar() {
    var tbody = $("#contenido_profesor");
    tbody.html("");
    escoba();
    listarProfesor();
    completarCursos();
}

function validaProfe() {

    let profesor = {
        cedula: $("#add_cedula").val(),
        nombre: $("#add_nombre").val(),
        telefono: $("#add_telefono").val(),
        email: $("#add_email").val(),
        curso: String($("#add_curso").children("option:selected").val()).trim()
    };


    if (profesor.cedula.length < 8 || profesor.cedula.length > 15) {
        swal("Error", "Cedula invalida", "warning");
        return false;
    }

    if (profesor.nombre.length < 3 || profesor.nombre.length > 50) {
        swal("Error", "El nombre tiene una extension invalida", "warning");
        return false;
    }

    if (profesor.telefono.length > 15 || profesor.telefono.length < 8) {
        swal("Error", "Telefono invalido", "warning");
        return false;
    }

    if (profesor.email.length < 2 || profesor.email.length > 30) {
        swal("Error", "Email invalido", "warning");
        return false;
    }

    if (!isEmail(profesor.email)) {
        swal("Error", "Email invalido", "warning");
        return false;
    }

    return true;
}

function hasNumber(myString) {
    return /\d/.test(myString);
}

function isEmail(Email) {
    return /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(Email);
}

function filtroProfe() {
   let tdata =  $("#filtroProfe_por option:selected" ).val();
   let busqueda = $("#filtroProfe").val().trim().toUpperCase();

    $("tr.itemP").each(function () {
        let dato = $(this).find(`td:eq(${tdata})`).text().toUpperCase();
        (dato.startsWith(busqueda)) ? $(this).show() : $(this).hide();
    });
}

