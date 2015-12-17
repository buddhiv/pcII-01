/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Buddhi
 */
public class Brick extends CellObject {

    private double health;

    public Brick() {
    }

    public Brick(int x, int y) {
        this.setX(x);
        this.setY(y);
        this.health = 100;
    }

    /**
     * @return the health
     */
    public double getHealth() {
        return health;
//        this.
    }

    /**
     * @param health the health to set
     */
    public void setHealth(double health) {
        this.health = health;
    }

    @Override
    public String toString() {
        return "B";
    }

}
