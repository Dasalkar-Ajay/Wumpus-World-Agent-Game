package com.aigame.PlayAsGuest;

import java.nio.file.OpenOption;
import java.util.Optional;

import com.aigame.Controller.AppController;
import com.aigame.Maps.Maps;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PlayAsGuest extends Play{
    private Maps maps;
    private String[][] finalmap;
    private String[][] map = new String[SIZE][SIZE];
    private BorderPane view;
    private int health=300;
    private GridPane board = new GridPane();

    public PlayAsGuest(AppController appController) {
        super.appController=appController;
        super.mediaPlayer=appController.mediaPlayer;
        maps = new Maps();
        finalmap = maps.getmap(3);
        for (int r = 0; r < SIZE; r++) {
            System.arraycopy(finalmap[r], 0, map[r], 0, SIZE);
        }
        initialize();
    }

    private void initialize() {
        view = new BorderPane();
        drawBoard(board, map, finalmap, "/Images/Avtar/Avtarno0.jpg");
        view.setLeft(board);

        HBox directionBox = getDirection();
        Button shooButton=shootButton();
        VBox controlVBox=new VBox();
        controlVBox.getChildren().addAll(directionBox,shooButton);



        controlVBox.setAlignment(Pos.CENTER);
        directionBox.setPadding(new Insets(50));
        view.setRight(controlVBox);
    }

    private HBox getDirection(){
        Button upBtn = new Button("↑");
        upBtn.setPrefSize(80, 50);
        Button downBtn = new Button("↓");
        downBtn.setPrefSize(80, 50);
        Button leftBtn = new Button("←");
       leftBtn.setPrefSize(80, 50);
        Button rightBtn = new Button("→");
        rightBtn.setPrefSize(80, 50);

         upBtn.setOnAction(e -> handleMove("UP"));
        downBtn.setOnAction(e -> handleMove("DOWN"));
        leftBtn.setOnAction(e -> handleMove("LEFT"));
        rightBtn.setOnAction(e -> handleMove("RIGHT"));

        HBox directionBox = new HBox(15, leftBtn, downBtn, rightBtn,upBtn);
        return directionBox;
    }

    private void handleMove(String string) {
       int newRow = agentRow;
        int newCol = agentCol;

         switch (string) {
            case "UP":
                newRow--;health-=10;
                break;
            case "DOWN":
                newRow++;health-=10;
                break;
            case "LEFT":
                newCol--;health-=10;
                break;
            case "RIGHT":
                newCol++;health-=10;
                break;
            default:
                return;
        }

        if (isInBounds(newRow, newCol)) {
            String destination = map[newRow][newCol];
            if(health<=0){
                showMessage("Agent get die by out of health!!", Alert.AlertType.ERROR);
                resetGame();
            }
            else if (destination.equals("P")) {
                sound("/Audio/dieingSound.mp3");
                showMessage("You fell into a pit! Game Over.", Alert.AlertType.ERROR);
                resetGame();
            } else if (destination.equals("W")) {
                sound("/Audio/dieingSound.mp3");
                showMessage("Wumpus ate you! Game Over.", Alert.AlertType.ERROR);
                resetGame();
            } else if (destination.equals("G")) {
                if(playingWill()){
                    resetGame();
                }
                else appController.navigateToHomePage();
            } else {
                map[agentRow][agentCol] = ".";
                agentRow = newRow;
                agentCol = newCol;
                map[agentRow][agentCol] = "A";
                drawBoard(board, map, finalmap, "/Images/Avtar/Avtarno0.jpg");
            }
        }else{
             sound("/Audio/wallbump.mp3");
            showMessage("You Bumped to the Wall !!", Alert.AlertType.INFORMATION);
                resetGame();
        }
    }

     private void resetGame() {
        for (int r = 0; r < SIZE; r++) {
            System.arraycopy(finalmap[r], 0, map[r], 0, SIZE);
        }
        agentRow = 4;
        agentCol = 0;
        health=300;
        drawBoard(board, map, finalmap, "/Images/Avtar/Avtarno0.jpg");
    }
    private Button shootButton(){
        Button shoot=new Button("Shoot->");
        shoot.setPrefSize(100, 60);
        shoot.setOnAction(e->{
           shoot();
        });
        return shoot;
    }
    public BorderPane getView() {
        return view;
    }

}
