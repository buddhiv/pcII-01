/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package observer;

import java.util.ArrayList;
import model.Cell;

/**
 *
 * @author Buddhi
 */
public class MapObservable {

    private ArrayList<MapObserver> observers = new ArrayList<>();

    public void addObserver(MapObserver observer) {
        observers.add(observer);
    }

    public void update(Cell map[][]) {
        for (MapObserver mapObserver : observers) {
            mapObserver.updateInterface(map);
        }
    }

}
