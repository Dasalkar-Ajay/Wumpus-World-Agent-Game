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

public class PlayAsGuest {
    private Maps maps;
    private final int SIZE = 5;
    private String[][] finalmap;
    private String[][] map = new String[SIZE][SIZE];
    private BorderPane view;
    private final int CELL_SIZE = 120;
    private int agentRow = 4;
    private int agentCol = 0;
    private AppController appController;
    private GridPane board = new GridPane();

    public PlayAsGuest(AppController appController) {
        this.appController = appController;
        maps = new Maps();
        finalmap = maps.getmap("");
        for (int r = 0; r < SIZE; r++) {
            System.arraycopy(finalmap[r], 0, map[r], 0, SIZE);
        }
        initialize();
    }

    private void initialize() {
        view = new BorderPane();
        drawBoard();
        view.setLeft(board);
    }

    private void drawBoard() {
        board.getChildren().clear();
        board.setAlignment(Pos.CENTER);
        board.setGridLinesVisible(true);

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (map[row][col].equals("A")) {
                    Image image = new Image("/Images/Avtar/Avtarno0.jpg");
                    ImageView imageView = new ImageView(image);
                    imageView.setFitHeight(80);
                    imageView.setFitWidth(80);
                    StackPane stackPane = new StackPane();
                    stackPane.setPrefSize(CELL_SIZE, CELL_SIZE);
                    stackPane.setStyle("-fx-border-color: black; -fx-background-color: " + "#fefae0");
                    stackPane.getChildren().add(imageView);
                    board.add(stackPane, col, row);
                } else {
                    Label label = new Label("");
                    label.setPrefSize(CELL_SIZE, CELL_SIZE);
                    label.setAlignment(Pos.CENTER);
                    label.setStyle("-fx-border-color: black; -fx-background-color: " + "#fefae0");
                    board.add(label, col, row);
                }
                board.setPadding(new Insets(20));
            }
        }
    }

    public BorderPane getView() {
        return view;
    }
}
