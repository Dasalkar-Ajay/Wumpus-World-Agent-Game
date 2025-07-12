package com.aigame.PlayAsGuest;

import com.aigame.Controller.AppController;
import com.aigame.Maps.Maps;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class PlayAsGuest extends Play{
    private Maps maps;
    private String[][] finalmap;
    private String[][] map = new String[SIZE][SIZE];
    private BorderPane view;
    private AppController appController;
    private GridPane board = new GridPane();

    public PlayAsGuest(AppController appController) {
        this.appController = appController;
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

        VBox controlVBox=new VBox();
        controlVBox.getChildren().add(directionBox);
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
                newRow--;
                break;
            case "DOWN":
                newRow++;
                break;
            case "LEFT":
                newCol--;
                break;
            case "RIGHT":
                newCol++;
                break;
            default:
                return;
        }

        if (isInBounds(newRow, newCol)) {
            String destination = map[newRow][newCol];

            if (destination.equals("P")) {
                showMessage("You fell into a pit! Game Over.", Alert.AlertType.ERROR);
                resetGame();
            } else if (destination.equals("W")) {
                showMessage("Wumpus ate you! Game Over.", Alert.AlertType.ERROR);
                resetGame();
            } else if (destination.equals("G")) {
                showMessage("You found the GOLD! You Win!", Alert.AlertType.INFORMATION);
                resetGame();
            } else {
                map[agentRow][agentCol] = ".";
                agentRow = newRow;
                agentCol = newCol;
                map[agentRow][agentCol] = "A";
                drawBoard(board, map, finalmap, "/Images/Avtar/Avtarno0.jpg");
            }
        }else{
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
        drawBoard(board, map, finalmap, "/Images/Avtar/Avtarno0.jpg");
    }

    public BorderPane getView() {
        return view;
    }

}
