/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).on("DOMContentLoaded", pageLoad);
let profesores = 1;

function pageLoad() {
    $("#salir").on("click", salir);
    $("#cursos").hide();
    $("#entrar").on("click", autenticar);
    $("#li_profes").on("click", cambiarAProfe);
    $("#li_cursos").on("click", cambiarACurso);
}


function autenticar(){

    let cedula = $("#cedula_login").val();
    let clave =  $("#clave_login").val();
    
      $.ajax({
        type: "GET",
        url: "matricula/usuario?cedula=" + cedula + "&clave=" + clave,
        success: function(data){        
            let validacion = parseInt(data);
            if(validacion === 1){
                window.location.replace("cruds.jsp");
            }
            else
               swal("Error", "Datos invalidos", "error");
       
        },
        error: function () {
             swal("Error", "Datos invalidos", "error");
        }
    });
}



function salir(){
    $.ajax({
        type: "GET",
        url: "matricula/usuario/salir",
        success: function(d){
            location.reload();
        },
        error: function () {
             swal("Error", "Error inesperado", "error");
        }
    });
}

function cambiarAProfe(){
     $("#cursos").hide();
     $("#profesores").show();
}

function cambiarACurso(){
    $("#profesores").hide();
    $("#cursos").show();
}