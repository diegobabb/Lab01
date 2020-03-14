/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import exceptions.GlobalException;
import exceptions.NoDataException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author diego
 */
public class App implements Observer {

    private Controller principal;
    private ControllerLogin login;

    public App() throws GlobalException, NoDataException {
        this.principal = null;
        this.login = new ControllerLogin(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("notifique a la app");
        if (this.principal == null) {
            try {
                this.principal = new Controller(this);
            } catch (GlobalException | NoDataException ex) {
                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.login = null;
        } else {
            this.login = new ControllerLogin(this);
            this.principal = null;
        }
    }
}
