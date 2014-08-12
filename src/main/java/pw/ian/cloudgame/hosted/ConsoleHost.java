/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pw.ian.cloudgame.hosted;

/**
 * Represents a host from the console.
 */
public class ConsoleHost implements Host {

    public static final ConsoleHost INSTANCE = new ConsoleHost();

    private ConsoleHost() {

    }
}
