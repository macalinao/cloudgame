/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplyian.cloudgame.model;

/**
 * Model
 *
 * @author ian
 */
public abstract class Model {

    private final String id;

    protected Model(String id) {
        this.id = id;
    }

    /**
     * Gets the id of the model
     *
     * @return
     */
    public String getId() {
        return id;
    }

}
