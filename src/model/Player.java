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
public class Player extends CellObject {

    private int x;
    private int y;
    private String name;
    private int direction;
    private int whetherShot;
    private int coins;
    private int points;
    private int health;

    public Player() {
    }

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Player(int x, int y, String name, int direction, int whetherShot, int coins, int points, int health) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.direction = direction;
        this.whetherShot = whetherShot;
        this.coins = coins;
        this.points = points;
        this.health = health;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the points
     */
    public int getPoints() {
        return points;
    }

    /**
     * @param points the points to set
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * @return the direction
     */
    public int getDirection() {
        return direction;
    }

    /**
     * @param direction the direction to set
     */
    public void setDirection(int direction) {
        this.direction = direction;
    }

    /**
     * @return the coins
     */
    public int getCoins() {
        return coins;
    }

    /**
     * @param coins the coins to set
     */
    public void setCoins(int coins) {
        this.coins = coins;
    }

    /**
     * @return the health
     */
    public int getHealth() {
        return health;
    }

    /**
     * @param health the health to set
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * @return the whetherShot
     */
    public int getWhetherShot() {
        return whetherShot;
    }

    /**
     * @param whetherShot the whetherShot to set
     */
    public void setWhetherShot(int whetherShot) {
        this.whetherShot = whetherShot;
    }

    @Override
    public String toString() {
        return getName();
    }
}
