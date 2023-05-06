package com.example.snakeandladder;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class SnakeAndLadder extends Application {
    public static final int tileSize = 70, width = 10, height = 10;
    public static final int butonLine = height  * tileSize +50,  infoline = butonLine-30;
    private static Dice dice= new Dice();
    private Player Player1, Player2;
    private boolean playerOneTurn = false;

    private final Button PlayerOneButton = new Button("Player 1");
    private final Button PlayerTwoButton = new Button("Player 2");
    private final Button StartGame = new Button("Start");

    private final Label PlayerOneLabel = new Label("");
    private Label PlayerTwoLabel = new Label("");
    private final Label diceLabel = new Label("");
    Board board = new Board();
    private Pane createContent(){
        Pane root = new Pane();
        root.setPrefSize(width*tileSize,  height*tileSize + 150);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Tile tile = new Tile(tileSize);
                root.getChildren().add(tile);
                tile.setTranslateX(j*tileSize);
                tile.setTranslateY(i*tileSize);
            }
        }
        Tile Dice = new Tile(60);
        root.getChildren().add(Dice);
        Dice.setTranslateY(butonLine-10);
        Dice.setTranslateX(320);

        Image img = new Image("D:\\Projects\\Acciojob\\SnakeAndLadder\\src\\main\\img.png");
        ImageView board = new ImageView();
        board.setImage(img);
        board.setFitHeight(height*tileSize);
        board.setFitWidth(width*tileSize);


        //Buttons
        PlayerOneButton.setDisable(true);
        PlayerTwoButton.setDisable(true);
        PlayerOneButton.setTranslateY(butonLine);
        PlayerOneButton.setTranslateX(100);
        PlayerTwoButton.setTranslateY(butonLine);
        PlayerTwoButton.setTranslateX(500);
        StartGame.setTranslateY(butonLine-10);
        StartGame.setTranslateX(320);

        PlayerOneButton.setPrefSize(100,20);
        PlayerOneButton.setStyle("-fx-font-size : 12pt");
        PlayerTwoButton.setPrefSize(100,20);
        PlayerTwoButton.setStyle("-fx-font-size : 12pt");
        StartGame.setPrefSize(60,60);

        // Players
        Player1 = new Player(20, Color.BLACK,  "Pushp");
        Player2 = new Player(18, Color.WHITE,  "Ratan");

        // Info


        PlayerOneLabel.setTranslateY(infoline + 5);
        PlayerOneLabel.setTranslateX(107);
        PlayerOneLabel.setStyle("-fx-font-size : 12pt");
        PlayerTwoLabel.setTranslateY(infoline + 5);
        PlayerTwoLabel.setTranslateX(507);
        PlayerTwoLabel.setStyle("-fx-font-size : 12pt");
        diceLabel.setTranslateY(butonLine-2);
        diceLabel.setTranslateX(332);
        diceLabel.setStyle("-fx-font-weight : bold; -fx-font-size : 20pt;");

        // Player Action
        PlayerOneButton.setOnAction(actionEvent -> movingPlayers(Player1));
        PlayerTwoButton.setOnAction(actionEvent -> movingPlayers(Player2));
        StartGame.setOnAction(actionEvent -> startGame());

        root.getChildren().addAll(board,
                PlayerOneButton, PlayerTwoButton, StartGame,
                PlayerOneLabel, PlayerTwoLabel, diceLabel,
                Player1.getCoin(), Player2.getCoin());

        return root;
    }

    // Moving the Players
    private void movingPlayers(Player player){
        if(playerOneTurn){
            PlayerOneButton.setDisable(true);
            PlayerTwoButton.setDisable(false);
            PlayerTwoLabel.setText(Player2.getName() + "'s Turn");
            PlayerOneLabel.setText("");
        }else{
            PlayerTwoButton.setDisable(true);
            PlayerOneButton.setDisable(false);
            PlayerOneLabel.setText(Player1.getName() + "'s Turn");
            PlayerTwoLabel.setText("");
        }
        playerOneTurn = !playerOneTurn;
        int roll = dice.getDiceValue();
        for (int i = 0; i <7; i++) {
            diceLabel.setText("" + dice.getDiceValue());
        }
        diceLabel.setText("" + roll);
        diceLabel.setTranslateX(342);
        player.movePlayer(roll);
        if(player.getCurrentPos() == 100){
            win(player);
        }
    }

    private void win(Player player){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Winner");
        alert.setContentText("Congratulations ! " + player.getName() + " is winner.");
        alert.setHeaderText("");
        alert.showAndWait();
        PlayerTwoButton.setDisable(true);
        PlayerOneButton.setDisable(true);
        StartGame.setVisible(true);
        StartGame.setDisable(false);
        StartGame.setText("Restart");
        diceLabel.setTranslateX(332);
        diceLabel.setText("");

    }
    private void startGame(){
        Player1.restart();
        Player2.restart();
        StartGame.setVisible(false);
        diceLabel.setText("Go");
        StartGame.setDisable(true);
        PlayerOneButton.setDisable(false);
        PlayerOneLabel.setText(Player1.getName() + "'s Turn");
        playerOneTurn = true;
    }



    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createContent());
        stage.setTitle("Snake & Ladder");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}