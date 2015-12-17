/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import map.MapControl;

/**
 *
 * @author Buddhi
 */
public class ServerConnector extends Thread {

    private ServerSocket serverSocket;
    private Socket socket;
    private TankClient client;
    private MapControl mapControl;

    public ServerConnector(TankClient cli) throws IOException {
        serverSocket = new ServerSocket(7000);
        mapControl = new MapControl();
        this.client = cli;
    }

    @Override
    public void run() {
        //send the joining command to the server
        client.run("JOIN#");

        //start the GUI after the connection established
        while (true) {
            try {
                socket = serverSocket.accept();
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String string = br.readLine();

                processString(string);

                mapControl.printMap();
            } catch (IOException ex) {
                Logger.getLogger(ServerConnector.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void main(String[] args) {
        TankClient client = new TankClient();

        try {
            ServerConnector connector = new ServerConnector(client);
            connector.start();
        } catch (IOException ex) {
            Logger.getLogger(ServerConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void processString(String string) {
        //check the pattern of the string
        System.out.println(string);
        if (string.startsWith("I")) {
            mapControl.initializeMap(string);
        } else if (string.startsWith("S")) {
            mapControl.setPlayer(string);
        } else if (string.startsWith("G")) {
            mapControl.updateMap(string);
        } else if (string.startsWith("L")) {

        } else if (string.startsWith("C")) {

        }
    }
}
