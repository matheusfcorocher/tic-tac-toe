/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jogo.server;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author matheus
 */
public class JogoVelhaServerResetVoting {

    private ArrayList<Integer> resetVotes;

    // 0 - means there is no vote
    // 1 - means user votes no
    // 2 - means user votes yes
    public JogoVelhaServerResetVoting() {
        this.resetVotes = new ArrayList<>();
    }

    public void initVotes(int playersQuantity) {
        for (int i = 0; i < playersQuantity - this.resetVotes.size(); i++) {
            this.resetVotes.add(0);
        }
    }

    public void resetVotingSystem() {
        for (int i = 0; i < this.resetVotes.size(); i++) {
            this.resetVotes.set(i,0);
        }
        System.out.println(this.resetVotes);
    }

    public ArrayList<Integer> getResetVotes() {
        return resetVotes;
    }

    public void addVote(boolean wantsReset, int p) {
        int vote = 1;
        if (wantsReset) {
            vote = 2;
        }
        this.resetVotes.set(p-1, vote);
    }

    public boolean isReadyToCallElection() {
        boolean isReady = true;
        for (int resetVote : this.resetVotes) {
            if (resetVote == 0) {
                isReady = false;
            }
        }
        return isReady;
    }

    public boolean callResetElection() {
        System.out.println("Starting reset election");
        boolean shouldReset = true;
        for (int resetVote : this.resetVotes) {
            if (resetVote == 1) {
                shouldReset = false;
            }
        }
        return shouldReset;
    }

}
