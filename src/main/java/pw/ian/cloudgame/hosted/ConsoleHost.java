/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pw.ian.cloudgame.hosted;

/**
 * Represents a {@link Host} of a game from the console.
 *
 * @see Host
 */
public class ConsoleHost implements Host {
    /**
     * There is only one console, so there is only a single instance of
     * ConsoleHost
     */
    public static final ConsoleHost INSTANCE = new ConsoleHost();

    /**
     * Do not call
     */
    private ConsoleHost() {
    }
}
