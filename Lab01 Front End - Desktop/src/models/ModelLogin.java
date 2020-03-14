/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import exceptions.GlobalException;
import exceptions.NoDataException;
import java.util.Observable;
import services.ServiceUsuario;

/**
 *
 * @author diego
 */
public class ModelLogin extends Observable {

    public ModelLogin() {
    }
    
    public void login(String usuario, String contrasena) throws GlobalException, NoDataException {
        ServiceUsuario.getInstance().find(usuario, contrasena);
    }

    public void update() {
        this.setChanged();
        this.notifyObservers(null);
    }
}
