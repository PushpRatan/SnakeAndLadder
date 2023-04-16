package com.example.snakeandladder;

import javafx.util.Pair;

import java.util.*;

public class Board {
     static List<Pair<Integer, Integer>> Cordinates;
     static HashMap<Integer, Integer> SnakeLadder;

    public Board(){
        Cordinates = new ArrayList<>();
        SnakeLadder = new HashMap<>();
        fill();
        mapp();
    }

    public void fill(){
        Cordinates.add(new Pair<>(0,700));
        for (int i = 0; i < SnakeAndLadder.height; i++) {
            for (int j = 0; j <SnakeAndLadder.width; j++) {
                int xCord =0;
                if(i%2==0){
                    xCord = j * SnakeAndLadder.tileSize + 35;
                }else{
                    xCord = SnakeAndLadder.width * SnakeAndLadder.tileSize - (j * SnakeAndLadder.tileSize) -35;
                }
                int yCord = SnakeAndLadder.height * SnakeAndLadder.tileSize - (i*SnakeAndLadder.tileSize) - 35;
                Cordinates.add(new Pair<>(xCord, yCord));
            }
        }
    }

    public static int getSnakeLadder(int pos) {
        if(SnakeLadder.containsKey(pos))return SnakeLadder.get(pos);
        return -1;
    }

    public void mapp(){
        SnakeLadder.put(5, 26);
        SnakeLadder.put(12, 71);
        SnakeLadder.put(19, 40);
        SnakeLadder.put(28, 54);
        SnakeLadder.put(36, 76);
        SnakeLadder.put(60, 79);
        SnakeLadder.put(66, 87);
        SnakeLadder.put(72, 91);
        SnakeLadder.put(98, 3);
        SnakeLadder.put(94, 48);
        SnakeLadder.put(88, 51);
        SnakeLadder.put(64, 22);
        SnakeLadder.put(55, 34);
        SnakeLadder.put(50, 11);
        SnakeLadder.put(43, 9);
        SnakeLadder.put(27, 6);
    }
    public int getX(int curr){
        if(curr<=100 && curr>=0){
            return Cordinates.get(curr).getKey();
        }
        return -1;
    }
    public int getY(int curr){
        if(curr<=100 && curr>=0){
            return Cordinates.get(curr).getValue();
        }
        return -1;
    }
}
