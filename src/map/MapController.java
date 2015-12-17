/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package map;

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.CoinPack;

/**
 *
 * @author Buddhi
 */
public class MapController {

    static int mapMax = 10;
    private static int coinTotal = 0;
    private static int bricks;
    private static int stones;
    private static int waters;
    private static String map[][] = new String[mapMax][mapMax];
    static int x = 0, y = 0;
    static ArrayList<String> P0;
    static ArrayList<String> P1;
    static ArrayList<String> P2;
    static ArrayList<String> P3;
    static ArrayList<String> P4;

    public static void createMap(String address) {

        String I;
        String playerNum;
        String brick;
        String stone;
        String water;
        ArrayList<String> brick_pos = new ArrayList<String>();
        ArrayList<String> stone_pos = new ArrayList<String>();
        ArrayList<String> water_pos = new ArrayList<String>();
        ArrayList<CoinPack> coin_pos = new ArrayList<CoinPack>();
        //String dtail="I:P0:5,7;1,3;6,8;8,7;0,4;2,6:4,8;3,2;7,1;9,3;7,4;4,2;8,1;3,6;5,8:6,2;3,1;1,7;1,2;4,7;9,2;5,4;7,2;8,6;2,4#";
        String new_add = address.substring(0, address.length() - 1);// to remove last # mark
        StringTokenizer str = new StringTokenizer(new_add, ":");
        I = str.nextToken();//check first value I,G, S etc
        playerNum = str.nextToken();//get player names
        brick = str.nextToken();//get bricks pattern
        stone = str.nextToken();//get stones pattern
        water = str.nextToken();//get water patterns
        System.out.println("I = " + I + " brick = " + brick + " stone = " + stone + " water = " + water);

        StringTokenizer bri = new StringTokenizer(brick, ";");
        for (int i = 0; bri.hasMoreTokens(); i++) {
            brick_pos.add(i, bri.nextToken());
        }
        StringTokenizer sto = new StringTokenizer(stone, ";");
        for (int i = 0; sto.hasMoreTokens(); i++) {
            stone_pos.add(i, sto.nextToken());
        }
        StringTokenizer wat = new StringTokenizer(water, ";");
        for (int i = 0; wat.hasMoreTokens(); i++) {
            water_pos.add(i, wat.nextToken());
        }
        for (int i = 0; i < mapMax; i++) {
            for (int j = 0; j < mapMax; j++) {
                getMap()[i][j] = "0";
            }
        }
        for (int i = 0; i < brick_pos.size(); i++) {
            String positions[] = brick_pos.get(i).split(",");
            x = Integer.parseInt(positions[0]);
            y = Integer.parseInt(positions[1]);
            getMap()[y][x] = "1";//1 for brick
            setBricks(getBricks() + 1);
        }
        for (int i = 0; i < stone_pos.size(); i++) {
            String positions[] = stone_pos.get(i).split(",");
            x = Integer.parseInt(positions[0]);
            y = Integer.parseInt(positions[1]);
            getMap()[y][x] = "2";//2 for stone
            setStones(getStones() + 1);
        }
        for (int i = 0; i < water_pos.size(); i++) {
            String positions[] = water_pos.get(i).split(",");
            x = Integer.parseInt(positions[0]);
            y = Integer.parseInt(positions[1]);
            getMap()[y][x] = "3";//3 for water
            setWaters(getWaters() + 1);
        }
        printMap();

    }

    private static void printMap() {
        for (int i = 0; i < mapMax; i++) {
            for (int j = 0; j < mapMax; j++) {
                System.out.print(getMap()[i][j] + "  ");
            }
            System.out.println();
        }
    }

    public static void updateMap(String G) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j].startsWith("P")) {
                    map[i][j] = "0";
                    break;
                }
            }
        }

        String raw_st = G.substring(2, G.length());

        StringTokenizer upmap = new StringTokenizer(raw_st, ":");

        while (upmap.hasMoreTokens()) {
            String k = upmap.nextToken();
            if (k.charAt(0) == 'P') {
                playerUpdateStatus(k);
            }
        }

    }

    private static void playerUpdateStatus(String P) {
        ArrayList<String> tokens = new ArrayList<String>();
        StringTokenizer player = new StringTokenizer(P, ";");
        while (player.hasMoreTokens()) {
            tokens.add(player.nextToken());
        }
        String positions[] = tokens.get(1).split(",");
        x = Integer.parseInt(positions[0]);
        y = Integer.parseInt(positions[1]);

        getMap()[y][x] = tokens.get(0);
        if ("P0".equals(tokens.get(0))) {
            P0 = tokens;
        }
        if ("P1".equals(tokens.get(0))) {
            P1 = tokens;
        }
        if ("P2".equals(tokens.get(0))) {
            P2 = tokens;
        }
        if ("P3".equals(tokens.get(0))) {
            P3 = tokens;
        }
        if ("P4".equals(tokens.get(0))) {
            P4 = tokens;
        }
        printMap();
        System.out.println(P0);
        System.out.println(P1);
    }

    public static void getLifePacks(String L) {

        String details[] = L.split(":");
        String[] positions = details[1].split(",");
        x = Integer.parseInt(positions[0]);
        y = Integer.parseInt(positions[1]);
        int time = Integer.parseInt(details[2].substring(0, (details[2]).length() - 1));

        Thread t = null;
        t = new Thread(new Runnable() {

            @Override
            public void run() {
                map[x][y] = "4";
                try {
                    Thread.sleep(time);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MapController.class.getName()).log(Level.SEVERE, null, ex);
                }
                map[x][y] = "0";
            }
        });
        t.start();

        System.out.println("LIFE PACK X  =" + y + " Y =" + x + " time " + time);

    }

    /**
     * @return the map
     */
    public static String[][] getMap() {
        return map;
    }

    /**
     * @param aMap the map to set
     */
    public static void setMap(String[][] aMap) {
        map = aMap;
    }

    public static void updateCoins(String s) {
        System.out.println("aaa " + s);

        String details[] = s.split(":");
        String[] positions = details[1].split(",");
        x = Integer.parseInt(positions[0]);
        y = Integer.parseInt(positions[1]);

        int amount = Integer.parseInt(details[2]);
        int time = Integer.parseInt(details[3].substring(0, (details[1]).length() - 1));

        System.out.println("amount " + amount);
        System.out.println("time " + time);
        Thread t = null;
        t = new Thread(new Runnable() {

            @Override
            public void run() {
                map[x][y] = "5";
                setCoinTotal(getCoinTotal() + amount);
                try {
                    Thread.sleep(time);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MapController.class.getName()).log(Level.SEVERE, null, ex);
                }
                map[x][y] = "0";
                setCoinTotal(getCoinTotal() - amount);
            }
        });
        t.start();

//        coins.add();
        System.out.println("COIN PACK X  =" + y + " Y =" + x + " time " + time);
    }

    /**
     * @return the coinTotal
     */
    public static int getCoinTotal() {
        return coinTotal;
    }

    /**
     * @param aCoinTotal the coinTotal to set
     */
    public static void setCoinTotal(int aCoinTotal) {
        coinTotal = aCoinTotal;
    }

    /**
     * @return the bricks
     */
    public static int getBricks() {
        return bricks;
    }

    /**
     * @param aBricks the bricks to set
     */
    public static void setBricks(int aBricks) {
        bricks = aBricks;
    }

    /**
     * @return the stones
     */
    public static int getStones() {
        return stones;
    }

    /**
     * @param aStones the stones to set
     */
    public static void setStones(int aStones) {
        stones = aStones;
    }

    /**
     * @return the waters
     */
    public static int getWaters() {
        return waters;
    }

    /**
     * @param aWaters the waters to set
     */
    public static void setWaters(int aWaters) {
        waters = aWaters;
    }

}
