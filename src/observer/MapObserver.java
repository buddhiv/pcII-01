/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package observer;

import model.Cell;

/**
 *
 * @author Buddhi
 */
public interface MapObserver {

    void updateInterface(Cell map[][]);
}
