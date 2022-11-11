/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jogo.lib;

import java.io.Serializable;

/**
 *
 * @author matheus
 */
public class JogoVelhaClientMessage implements Serializable {
    private final boolean wantsReset;
    private int input = 0;

    public JogoVelhaClientMessage(boolean wantsReset, int input) {
        this.wantsReset = wantsReset;
        this.input = input;
    }
    
    public boolean getWantsReset() {
        return wantsReset;
    }

    public int getInput() {
        return input;
    }    
}
