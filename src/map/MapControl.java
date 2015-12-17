/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package map;

import java.util.StringTokenizer;
import model.Brick;
import model.Cell;
import model.Player;
import model.Stone;
import model.Water;
import observer.MapObservable;
import view.ClientUI;

/**
 *
 * @author Buddhi
 */
public class MapControl {

    private Cell[][] map;
    private Player[] players;
    private int brickCount = 0, stoneCount = 0, waterCount = 0;

    public MapControl() {
        map = new Cell[10][10];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = new Cell(i, j);
            }
        }

        players = new Player[5];
    }

    public void initializeMap(String s) {
        String string = s.substring(2, s.length() - 1);

//        tokenize the string
        StringTokenizer tokenizer = new StringTokenizer(string, ":");
        String player = tokenizer.nextToken();
        String bricks = tokenizer.nextToken();
        String stones = tokenizer.nextToken();
        String water = tokenizer.nextToken();

        System.out.println("p: " + player + " bricks: " + bricks + " stones: " + stones + " water: " + water);

//        tokenize string for bricks
        StringTokenizer brickTokenizer = new StringTokenizer(bricks, ";");
        while (brickTokenizer.hasMoreTokens()) {
            String bt = brickTokenizer.nextToken();
            String[] split = bt.split(",");

            int x = Integer.parseInt(split[0]);
            int y = Integer.parseInt(split[1]);

//            i for bricks
            Brick brick = new Brick(x, y);
            map[y][x].setCellObject(brick);

            brickCount++;
        }

//        tokenize string for stones
        StringTokenizer stoneTokenizer = new StringTokenizer(stones, ";");
        while (stoneTokenizer.hasMoreTokens()) {
            String st = stoneTokenizer.nextToken();
            String[] split = st.split(",");

            int x = Integer.parseInt(split[0]);
            int y = Integer.parseInt(split[1]);

//            2 for stones
            Stone stone = new Stone(x, y);
            map[y][x].setCellObject(stone);

            stoneCount++;
        }

//        tokenize string for water
        StringTokenizer waterTokenizer = new StringTokenizer(water, ";");
        while (waterTokenizer.hasMoreTokens()) {
            String wt = waterTokenizer.nextToken();
            String[] split = wt.split(",");

            int x = Integer.parseInt(split[0]);
            int y = Integer.parseInt(split[1]);

//            3 for water
            Water w = new Water(x, y);
            map[y][x].setCellObject(w);

            waterCount++;
        }

//        printMap();
    }

    public void printMap() {
        System.out.println("");
        for (Cell[] cells : map) {
            for (Cell cell : cells) {
                if (cell.getCellObject() == null) {
                    System.out.print("0 ");
                } else {
                    System.out.print(cell.getCellObject() + " ");
                }
            }
            System.out.println("");
        }
        System.out.println("");
    }

    /**
     * @return the map
     */
    public Cell[][] getMap() {
        return map;
    }

    /**
     * @param map the map to set
     */
    public void setMap(Cell[][] map) {
        this.map = map;
    }

    public void setPlayer(String s) {
        String string = s.substring(2, s.length() - 1);
        StringTokenizer tokenizer = new StringTokenizer(string, ";");

        Player player = new Player();
        player.setName(tokenizer.nextToken());

        String location = tokenizer.nextToken();
        String[] split = location.split(",");
        player.setX(Integer.parseInt(split[0]));
        player.setY(Integer.parseInt(split[1]));

        player.setDirection(Integer.parseInt(tokenizer.nextToken()));
        player.setWhetherShot(0);
        player.setCoins(0);
        player.setPoints(0);
        player.setHealth(100);

        int playerNumber = Integer.parseInt(player.getName().substring(1, 2));

        players[playerNumber] = player;
        setPlayerOnMap(player);
    }

    private void setPlayerOnMap(Player player) {
        char playerNum = player.getName().charAt(1);

        int a = Integer.parseInt(String.valueOf(playerNum));
        players[a] = player;
        map[player.getY()][player.getX()].setCellObject(player);
    }

    public void updateMap(String string) {
        String s = string.substring(2, string.length() - 1);

        StringTokenizer tokenizer = new StringTokenizer(s, ":");

        int i = tokenizer.countTokens();

        for (int j = 1; j < i; j++) {
            String s1 = tokenizer.nextToken();
            StringTokenizer st = new StringTokenizer(s1, ";");

            String playerName = st.nextToken();

            String location = st.nextToken();
            String l[] = location.split(",");
            int x = Integer.parseInt(l[0]);
            int y = Integer.parseInt(l[1]);

            int direction = Integer.parseInt(st.nextToken());
            int whetherShot = Integer.parseInt(st.nextToken());
            int health = Integer.parseInt(st.nextToken());
            int coins = Integer.parseInt(st.nextToken());
            int points = Integer.parseInt(st.nextToken());

            Player player = new Player(x, y, playerName, direction, whetherShot, coins, points, health);

            clearPlayer(playerName);
            setPlayerOnMap(player);
        }

        String s1 = tokenizer.nextToken();
        StringTokenizer st = new StringTokenizer(s1, ";");
        while (st.hasMoreTokens()) {
            String[] ses = st.nextToken().split(",");

            int health = Integer.parseInt(ses[2]);
            if (health < 100) {
                int x = Integer.parseInt(ses[0]);
                int y = Integer.parseInt(ses[1]);

                Brick b = new Brick(x, y);
                b.setHealth(health);
                map[y][x].setCellObject(b);
            }
        }
    }

    private void clearPlayer(String playerName) {
        int playerNumber = Integer.parseInt(playerName.substring(1, 2));

        Player p = players[playerNumber];
        map[p.getY()][p.getX()] = new Cell(p.getX(), p.getY());

        players[playerNumber] = null;
    }
}
