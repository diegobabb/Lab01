/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Observable;

/**
 *
 * @author Diego Babb
 */
public class Model extends Observable {

    public Model() {
    }

    public void update() {

        this.setChanged();
        this.notifyObservers(null);
    }

    @Override
    public void addObserver(java.util.Observer o) {
        super.addObserver(o);
        this.setChanged();
        this.notifyObservers(null);
    }
}
