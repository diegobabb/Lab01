
<div class="modal fade" id="modalAgregarProfesor" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header text-center">
                <h4 class="modal-title w-100">Agregar Profesor</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

            <div class="modal-body">

                <div class="md-form mb-4">
                    <label for="cedula">Cedula</label>
                    <input type="text" id="add_cedula" class="form-control" onkeypress="return onlyNumberKey(event)" autocomplete="false">
                </div>

                <div class="md-form  mb-4">
                    <label for="nombre">Nombre</label>
                    <input type="text" id="add_nombre" class="form-control" autocomplete="false">
                </div>
                
                <div class="md-form  mb-4">
                    <label for="telefono">Telefono</label>
                    <input type="text" id="add_telefono" class="form-control" autocomplete="false">
                </div>
                
                <div class="md-form  mb-4">
                    <label for="email">Email</label>
                    <input type="text" id="add_email" class="form-control" autocomplete="false">
                </div>
                
                <div class="md-form  mb-4">
                    <label for="curso">Curso</label>
                    <select class="form-control" id="add_curso">
                        
                    </select>
                </div>

            </div>

            <div class="modal-footer d-flex justify-content-center">
                <button id="agregarProfesor" style="background-color: lightseagreen;" class="btn btn-indigo">Agregar<i class="fas fa-paper-plane-o ml-1"></i></button>
                <button id="modificarProfesor" style="background-color: lightseagreen;" class="btn btn-indigo">Editar<i class="fas fa-paper-plane-o ml-1"></i></button>
            </div>

        </div>
    </div>
</div>