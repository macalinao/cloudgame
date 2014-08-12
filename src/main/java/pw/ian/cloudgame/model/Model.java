/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pw.ian.cloudgame.model;

/**
 * Represents a model in CloudGame. A model could be an Arena, a Region or
 * something else
 *
 * @author ian
 */
public abstract class Model {

    /**
     * The id of this Model
     */
    private final String id;

    protected Model(String id) {
        this.id = id;
    }

    /**
     * Gets the id of the Model
     *
     * @return This Model's ID
     */
    public String getId() {
        return id;
    }

}
