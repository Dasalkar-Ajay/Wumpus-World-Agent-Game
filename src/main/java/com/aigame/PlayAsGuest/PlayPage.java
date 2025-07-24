package com.aigame.PlayAsGuest;

import com.aigame.Controller.AppController;
import com.aigame.Maps.Maps;
import com.aigame.SqlHandling.SqlQueryPerformer;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PlayPage extends Play {
    private Maps maps;
    private int leveltoPlay;
    private SqlQueryPerformer sqlQueryPerformer;
    private String playerId, avtar_inUse;
    private String[][] finalmap;
    private String[][] map = new String[SIZE][SIZE];
    private BorderPane view;
    private int health = 300;
    private GridPane board = new GridPane();
    Rectangle healthBar;

    public PlayPage(AppController appController) {
        super.appController = appController;
        super.mediaPlayer = appController.mediaPlayer;
        maps = new Maps();
    }

    private void initialize() {
        finalmap = maps.getmap(leveltoPlay);
        for (int r = 0; r < SIZE; r++) {
            System.arraycopy(finalmap[r], 0, map[r], 0, SIZE);
        }

        view = new BorderPane();
        drawBoard(board, map, finalmap, avtar_inUse);
        view.setLeft(board);

        HBox directionBox = getDirection();
        Button shooButton = shootButton();
        VBox controlVBox = new VBox();

        HBox healthBox = getHealth();
        HBox backBox = backButton();

        controlVBox.getChildren().addAll(backBox, healthBox, directionBox, shooButton);
        controlVBox.setAlignment(Pos.CENTER);
        directionBox.setPadding(new Insets(50));
        view.setRight(controlVBox);
    }

    private HBox getDirection() {
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

        HBox directionBox = new HBox(15, leftBtn, downBtn, rightBtn, upBtn);
        return directionBox;
    }

    private void handleMove(String string) {
        int newRow = agentRow;
        int newCol = agentCol;

        switch (string) {
            case "UP": newRow--; break;
            case "DOWN": newRow++; break;
            case "LEFT": newCol--; break;
            case "RIGHT": newCol++; break;
            default: return;
        }

        health -= 10;
        takeDamage(healthBar, health);

        if (isInBounds(newRow, newCol)) {
            String destination = map[newRow][newCol];
            if (health <= 0) {
                showMessage("Agent get die by out of health!!", Alert.AlertType.ERROR);
                resetGame();
            } else if (destination.equals("P")) {
                sound("/Audio/dieingSound.mp3");
                showMessage("You fell into a pit! Game Over.", Alert.AlertType.ERROR);
                resetGame();
            } else if (destination.equals("W")) {
                sound("/Audio/dieingSound.mp3");
                showMessage("Wumpus ate you! Game Over.", Alert.AlertType.ERROR);
                resetGame();
            } else if (destination.equals("G")) {
                sqlQueryPerformer.won(playerId,health);
                if (playingWill()) {
                    resetGame();
                } else {
                    appController.navigateToHomePage();
                }
            } else {
                map[agentRow][agentCol] = ".";
                agentRow = newRow;
                agentCol = newCol;
                map[agentRow][agentCol] = "A";
                drawBoard(board, map, finalmap, avtar_inUse);
            }
        } else {
            sound("/Audio/wallbump.mp3");
            showMessage("You Bumped to the Wall !!", Alert.AlertType.INFORMATION);
            resetGame();
        }
    }

    private void resetGame() {
        finalmap = maps.getmap(leveltoPlay);
        for (int r = 0; r < SIZE; r++) {
            System.arraycopy(finalmap[r], 0, map[r], 0, SIZE);
        }
        agentRow = 4;
        agentCol = 0;
        health = 300;
        drawBoard(board, map, finalmap, avtar_inUse);
    }

    private Button shootButton() {
        Button shoot = new Button("Shoot->");
        shoot.setPrefSize(100, 60);
        shoot.setOnAction(e -> {
            if (health > 30) {
                int[] arr = shoot();
                if (arr != null) {
                    health -= 30;
                    takeDamage(healthBar, health);
                    int shootrow = arr[0], shootcol = arr[1];
                    if (isInBounds(shootrow, shootcol)) {
                        if (finalmap[shootrow][shootcol].equals("W")) {
                            map[shootrow][shootcol] = "";
                            killWumpus(finalmap, shootrow, shootcol);
                            sound("/Audio/WumpusDieingSound.mp3");
                            drawBoard(board, map, finalmap, avtar_inUse);
                        }
                    }
                }
            } else {
                showMessage("Your health is less than 30 Cannot shoot Arrow", AlertType.WARNING);
            }
        });
        return shoot;
    }

    public BorderPane getView(int leveltoPlay, int avtar_inUse, String playerId, SqlQueryPerformer sqlQueryPerformer) {
        this.leveltoPlay = leveltoPlay;
        this.sqlQueryPerformer = sqlQueryPerformer;
        this.avtar_inUse = "/Images/Avtar/Avtarno" + avtar_inUse + ".jpg";
        this.playerId = playerId;


        initialize();

        return view;
    }

    private HBox getHealth() {
        Rectangle healthBar = new Rectangle(health, 30, Color.RED);
        healthBar.setArcWidth(15);
        healthBar.setX(50);
        healthBar.setY(50);
        this.healthBar = healthBar;

        Label healthLabel = new Label("Agents health:");
        HBox healtHBox = new HBox(20, healthLabel, healthBar);
        healtHBox.setPadding(new Insets(0, 0, 60, 40));
        return healtHBox;
    }

    private HBox backButton() {
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            appController.navigateToHomePage();
            mediaPlayer.stop();
        });

        HBox backBox = new HBox(50, backButton);
        backBox.setPadding(new Insets(0, 50, 300, 100));
        backBox.setAlignment(Pos.TOP_RIGHT);
        return backBox;
    }
}
