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
public class Cell {

    private int x;
    private int y;
    private CellObject cellObject;

    public Cell() {
    }

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
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
     * @return the cellObject
     */
    public CellObject getCellObject() {
        return cellObject;
    }

    /**
     * @param cellObject the cellObject to set
     */
    public void setCellObject(CellObject cellObject) {
        this.cellObject = cellObject;
    }

}
