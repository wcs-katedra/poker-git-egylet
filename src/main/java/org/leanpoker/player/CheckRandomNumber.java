/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.leanpoker.player;

/**
 *
 * @author poker10
 */
public class CheckRandomNumber {

    private double randNumber;
    private int bet;

    public int check() {
        randNumber = Math.random();

        if (randNumber > 0.1) {
            bet = 10;
        } else {
            bet = 0;
        }
        return bet;
    }

}
