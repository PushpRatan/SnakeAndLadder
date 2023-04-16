package com.example.snakeandladder;

import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Player {
    private final Circle coin;
    private int currentPos;
    private String name;
    private static Board board = new Board();

    public  Player(int radii, Color coinColor, String playerName){
        coin = new Circle(radii);
        coin.setFill(coinColor);
        currentPos = 0;
        name = playerName;
        int x = board.getX(currentPos);
        int y = board.getY(currentPos);
        coin.setTranslateX(x);
        coin.setTranslateY(y);
        movePlayer(0);
    }

    public void restart(){
        currentPos=0;
        coin.setTranslateX(0);
        coin.setTranslateY(700);
    }
    public void movePlayer(int diceValue){
        if (currentPos + diceValue <= 100) {
            currentPos += diceValue;
        }
        translateAnimation(diceValue);
    }

    private void  translateAnimation(int diceValue){
        TranslateTransition animate = new TranslateTransition(Duration.millis(diceValue * 100), coin);
        animate.setToX(board.getX(currentPos));
        animate.setToY(board.getY(currentPos));
        animate.setAutoReverse(false);
        TranslateTransition first=animate, second = null;
        if(Board.getSnakeLadder(currentPos) != -1){
            second = snakeLadder(Board.getSnakeLadder(currentPos));
            SequentialTransition snakeOrLadder = new SequentialTransition(first, new PauseTransition(Duration.millis(200)), second);
            snakeOrLadder.play();
        }else{
            first.play();
        }
    }
    public TranslateTransition snakeLadder(int pos){
        TranslateTransition animate = new TranslateTransition(Duration.millis( 1000), coin);
        animate.setToX(board.getX(pos));
        animate.setToY(board.getY(pos));
        currentPos=pos;
        animate.setAutoReverse(false);
        return animate;
    }

    public Circle getCoin() {
        return coin;
    }

    public int getCurrentPos() {
        return currentPos;
    }

    public String getName() {
        return name;
    }



}
