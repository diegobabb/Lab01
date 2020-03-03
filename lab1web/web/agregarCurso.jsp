

<div class="modal fade" id="modalAgregarCurso" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header text-center">
                <h4 class="modal-title w-100">Agregar Curso</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

            <div class="modal-body">

                <div class="md-form mb-4">
                    <label for="cedula">Codigo</label>
                    <input type="text" id="add_codigo" class="form-control" autocomplete="false">
                </div>

                <div class="md-form  mb-4">
                    <label for="nombre">Nombre</label>
                    <input type="text" id="add_nombrec" class="form-control" autocomplete="false">
                </div>
                
                <div class="md-form  mb-4">
                    <label for="telefono">Creditos</label>
                    <input type="text" id="add_credito" class="form-control" autocomplete="false">
                </div>
                
                <div class="md-form  mb-4">
                    <label for="email">Horas semanales</label>
                    <input type="text" id="add_horas" class="form-control" autocomplete="false">
                </div>

            </div>

            <div class="modal-footer d-flex justify-content-center">
                <button id="agregarCurso" style="background-color: lightseagreen;" class="btn btn-indigo">Agregar<i class="fas fa-paper-plane-o ml-1"></i></button>
                <button id="modificarCurso" style="background-color: lightseagreen;" class="btn btn-indigo">Editar<i class="fas fa-paper-plane-o ml-1"></i></button>
            </div>

        </div>
    </div>
</div>