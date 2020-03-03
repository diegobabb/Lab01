/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import controllers.Controller;
import exceptions.GlobalException;
import exceptions.NoDataException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diego Babb
 */
public class Main {

    public static void main(String[] args) {
        
        try {
            Controller c = new Controller();
        } catch (GlobalException | NoDataException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
