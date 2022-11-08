/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jogo.lib;

/**
 *
 * @author matheus
 */
public class ClientMaximumLimitException extends Exception {
    public ClientMaximumLimitException() {
        super("O número máximo de jogadores foi alcançado, tente outro servidor!");
    }
}