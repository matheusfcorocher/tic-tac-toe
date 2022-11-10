/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jogo.server;

import jogo.lib.JogoVelhaServerMessage;

/**
 *
 * @author matheus
 */
public class JogoVelha {
    
    protected int turn;
    protected int q1, q2, q3, q4, q5, q6, q7, q8, q9;
    protected boolean isGameOver;
    
    public JogoVelha() {
        this.turn = 1;
        this.q1 = 0;
        this.q2 = 0;
        this.q3 = 0;
        this.q4 = 0;
        this.q5 = 0;
        this.q6 = 0;
        this.q7 = 0;
        this.q8 = 0;
        this.q9 = 0;
        this.isGameOver = false;
    }
    
    public JogoVelhaServerMessage execute(int player, int square) {
        if (this.isYourTurn(player) && !this.isGameOver && !this.hasPlayerColor(square)) {
            this.setSquareColor(player, square);
            int winner = getWinner();
            
            if (this.isThereAnyWinner(winner)) {
                this.isGameOver = true;
            }
            
            this.goToNextTurn();
            if (this.shouldResetTurn()) {
                this.resetTurn();
            }
        }
        
        return this.getGameStatus();
    }
    
    private boolean isYourTurn(int player) {
        return this.turn == player;
    }
    
    private void goToNextTurn() {
        this.turn += 1;
    }
    
    private void resetTurn() {
        this.turn = 1;
    }
    
    private boolean shouldResetTurn() {
        return this.turn == 3;
    }
    
    private boolean hasPlayerColor(int square) {
        int s = 0;
        switch (square) {
            case 1:
                s = this.q1;
                break;
            case 2:
                s = this.q2;
                break;
            case 3:
                s = this.q3;
                break;
            case 4:
                s = this.q4;
                break;
            case 5:
                s = this.q5;
                break;
            case 6:
                s = this.q6;
                break;
            case 7:
                s = this.q7;
                break;
            case 8:
                s = this.q8;
                break;
            case 9:
                s = this.q9;
                break;
        }
        return s != 0;
    }
    
    private void setSquareColor(int player, int square) {
        switch (square) {
            case 1:
                this.q1 = player;
                break;
            case 2:
                this.q2 = player;
                break;
            case 3:
                this.q3 = player;
                break;
            case 4:
                this.q4 = player;
                break;
            case 5:
                this.q5 = player;
                break;
            case 6:
                this.q6 = player;
                break;
            case 7:
                this.q7 = player;
                break;
            case 8:
                this.q8 = player;
                break;
            case 9:
                this.q9 = player;
                break;
        }
    }
    
    private int getWinner() {
        int winner = 0;
        
        if (this.q1 != 0 && this.q1 == this.q2 && this.q2 == this.q3) {
            winner = this.q1;
        } else if (this.q4 != 0 && this.q4 == this.q5 && this.q5 == this.q6) {
            winner = this.q4;
        } else if (this.q7 != 0 && this.q7 == this.q8 && this.q8 == this.q9) {
            winner = this.q7;
        } else if (this.q1 != 0 && this.q1 == this.q4 && this.q4 == this.q7) {
            winner = this.q1;
        } else if (this.q2 != 0 && this.q2 == this.q5 && this.q5 == this.q8) {
            winner = this.q2;
        } else if (this.q3 != 0 && this.q3 == this.q6 && this.q6 == this.q9) {
            winner = this.q3;
        } else if (this.q1 != 0 && this.q1 == this.q5 && this.q5 == this.q9) {
            winner = this.q1;
        } else if (this.q3 != 0 && this.q3 == this.q5 && this.q5 == this.q7) {
            winner = this.q3;
        }
        return winner;
    }
    
    private boolean isThereAnyWinner(int winner) {
        return winner != 0;
    }
    
    protected JogoVelhaServerMessage getGameStatus() {
        return new JogoVelhaServerMessage(this.turn, this.q1, this.q2, this.q3,
                this.q4, this.q5, this.q6,
                this.q7, this.q8, this.q9);
    }
    
}
