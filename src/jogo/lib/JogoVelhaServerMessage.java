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
public class JogoVelhaServerMessage implements Serializable {
    private int player;
    private int turn;
    private int winner;
    private boolean isGameOver;
    private int q1, q2, q3, q4, q5, q6, q7, q8, q9;

    public JogoVelhaServerMessage(int turn, int winner, boolean isGameOver, int q1, int q2, int q3, int q4, int q5, int q6, int q7, int q8, int q9) {
        this.turn = turn;
        this.winner = winner;
        this.isGameOver = isGameOver;
        this.q1 = q1;
        this.q2 = q2;
        this.q3 = q3;
        this.q4 = q4;
        this.q5 = q5;
        this.q6 = q6;
        this.q7 = q7;
        this.q8 = q8;
        this.q9 = q9;
    }
    
    public int getWinner() {
        return winner;
    }

    public boolean getIsGameOver() {
        return isGameOver;
    }
    
    public int getQ1() {
        return q1;
    }

    public int getQ2() {
        return q2;
    }

    public int getQ3() {
        return q3;
    }

    public int getQ4() {
        return q4;
    }

    public int getQ5() {
        return q5;
    }

    public int getQ6() {
        return q6;
    }

    public int getQ7() {
        return q7;
    }

    public int getQ8() {
        return q8;
    }

    public int getQ9() {
        return q9;
    }
    
    public int getPlayer() {
        return this.player;
    }
    
    public void setPlayer(int player) {
        this.player = player;
    }
    
    public int getTurn() {
        return this.turn;
    }
}
