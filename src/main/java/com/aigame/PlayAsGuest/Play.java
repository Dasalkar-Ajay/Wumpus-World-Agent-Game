package com.aigame.PlayAsGuest;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class Play {
    public final int SIZE = 5;
    public final int CELL_SIZE = 120;
    public int agentRow = 4;
    public int agentCol = 0;

    public final String getColor(String cell) {
        switch (cell) {
            case "S" :return "#0b6710ff";
            case "B":return "#194591ff";
            case "BS":return "#1b797eff";
            case "G":return "#ecb532ff";
            default :return "#a4cff2ff";
        }
    }

     public final boolean isInBounds(int row, int col) {
        return row >= 0 && row < SIZE && col >= 0 && col < SIZE;
    }

    public final void showMessage(String msg, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle("Wumpus Game");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

       public final void drawBoard(GridPane board,String[][]map,String[][] finalmap,String imageUrl) {
        board.getChildren().clear();
        board.setAlignment(Pos.CENTER);
        board.setGridLinesVisible(true);

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (map[row][col].equals("A")) {
                    Image image = new Image(imageUrl);
                    ImageView imageView = new ImageView(image);
                    imageView.setFitHeight(80);
                    imageView.setFitWidth(80);
                    StackPane stackPane = new StackPane();
                    stackPane.setPrefSize(CELL_SIZE, CELL_SIZE);
                    stackPane.setStyle("-fx-border-color: black; -fx-background-color: " + getColor(finalmap[agentRow][agentCol]));
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
}
