/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Canvas;
import java.awt.Graphics;

/**
 *
 * @author Buddhi
 */
public class GroundCanvas extends Canvas {

    private int h;
    private int w;

    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.

        int i = 0, j = 0;

        while (i < w) {
            while (j < w) {
                
                
                j += 10;
            }
            i += 20;
        }
    }

    /**
     * @return the h
     */
    public int getH() {
        return h;
    }

    /**
     * @param h the h to set
     */
    public void setH(int h) {
        this.h = h;
    }

    /**
     * @return the w
     */
    public int getW() {
        return w;
    }

    /**
     * @param w the w to set
     */
    public void setW(int w) {
        this.w = w;
    }

}
