/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jogo.lib;

/**
 *
 * @author matheus
 */
public class JogoVelhaClientMessage {
    private boolean wantsReset;
    private String request;

    public JogoVelhaClientMessage(boolean wantsReset, String request) {
        this.wantsReset = wantsReset;
        this.request = request;
    }
    
    public boolean getWantsReset() {
        return wantsReset;
    }

    public String getRequest() {
        return request;
    }    
}
