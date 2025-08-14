package com.aigame.PlayAsGuest;

import java.net.URL;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;
import com.aigame.Controller.AppController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;

public class Play {
    public static final int SIZE = 5;
    public static final int CELL_SIZE = 120;
    public int agentRow = 4, agentCol = 0;
    public AppController appController;
    public MediaPlayer mediaPlayer;

    private static final int[][] MOVES = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    // ========== Utility ==========
    public boolean isInBounds(int row, int col) {
        return row >= 0 && row < SIZE && col >= 0 && col < SIZE;
    }

    public void showMessage(String msg, Alert.AlertType type) {
        Alert alert = new Alert(type, msg);
        alert.setTitle("Wumpus Game");
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    // ========== Board Rendering ==========
    public void drawBoard(GridPane board, String[][] map, String[][] finalMap, String imageUrl) {
        board.getChildren().clear();
        board.setAlignment(Pos.CENTER);
        board.setGridLinesVisible(true);
        board.setPadding(new Insets(20));

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                StackPane cell = new StackPane();
                cell.setPrefSize(CELL_SIZE, CELL_SIZE);

                if ("A".equals(map[row][col])) {
                    ImageView agentImage = new ImageView(new Image(imageUrl));
                    agentImage.setFitHeight(80);
                    agentImage.setFitWidth(80);
                    cell.setStyle("-fx-border-color: black; -fx-background-color: " + getColor(finalMap[agentRow][agentCol]));
                    setAgentBackgroundSound(finalMap);
                    cell.getChildren().add(agentImage);
                } else {
                    cell.setStyle("-fx-border-color: black; -fx-background-color: #fefae0");
                }

                board.add(cell, col, row);
            }
        }
    }

    private String getColor(String cell) {
        switch (cell) {
            case "S": return "#0b6710ff";  // Stench
            case "B": return "#194591ff";  // Breeze
            case "BS": return "#1b797eff"; // Both
            case "G": return "#ecb532ff";  // Gold
            default: return "#a4cff2ff";   // Default
        }
    }

    private void setAgentBackgroundSound(String[][] map) {
        switch (map[agentRow][agentCol]) {
            case "S": case "BS": sound("/Audio/WumpusSound.mp3"); break;
            case "B": sound("/Audio/breeze.mp3"); break;
        }
    }

    // ========== Audio ==========
    public void sound(String url) {
        URL resource = getClass().getResource(url);
        if (resource == null) {
            System.err.println("Audio file not found!");
            return;
        }
        try {
            Media media = new Media(resource.toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();
        } catch (Exception e) {
            throw new RuntimeException("Sound error: " + e.getMessage());
        }
    }

    // ========== Game Events ==========
    public boolean playingWill() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Congratulations!");
        alert.setHeaderText("Do you want to play again?");
        alert.setContentText("Press OK to continue.");
        return alert.showAndWait().filter(btn -> btn == ButtonType.OK).isPresent();
    }

    public int[] shoot() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Choose Direction to Shoot:");
        ButtonType up = new ButtonType("↑");
        ButtonType down = new ButtonType("↓");
        ButtonType left = new ButtonType("←");
        ButtonType right = new ButtonType("→");
        ButtonType cancel = new ButtonType("Cancel");
        alert.getButtonTypes().setAll(up, down, left, right, cancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (!result.isPresent() || result.get() == cancel) return null;

        int[] pos = {agentRow, agentCol};
        if (result.get() == up) pos[0]--;
        else if (result.get() == down) pos[0]++;
        else if (result.get() == left) pos[1]--;
        else if (result.get() == right) pos[1]++;

        return pos;
    }

    public void killWumpus(String[][] map, int row, int col) {
        map[row][col] = "";
        for (int[] dir : MOVES) {
            int r = row + dir[0], c = col + dir[1];
            if (isInBounds(r, c)) {
                if ("S".equals(map[r][c])) map[r][c] = "";
                else if ("BS".equals(map[r][c])) map[r][c] = "B";
            }
        }
    }

    public void takeDamage(Rectangle healthBar, int currentHealth) {
        healthBar.setWidth(currentHealth);
    }

    // ========== Hint System ==========
    public void help(String[][] map) {
        int minSteps = Integer.MAX_VALUE;
        int bestDir = -1;

        for (int i = 0; i < MOVES.length; i++) {
            int newRow = agentRow + MOVES[i][0];
            int newCol = agentCol + MOVES[i][1];
            int steps = getTravel(newRow, newCol, map);
            if (steps < minSteps) {
                minSteps = steps;
                bestDir = i;
            }
        }

        getDirection(bestDir);
    }

    private int getTravel(int row, int col, String[][] map) {
        if (!isInBounds(row, col)) return Integer.MAX_VALUE;
        if(map[row][col].equals("W")|| map[row][col].equals("P"))return Integer.MAX_VALUE;

        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[SIZE][SIZE];
        queue.add(new int[]{row, col});
        visited[row][col] = true;
        int depth = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            depth++;
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                int r = curr[0], c = curr[1];

                if ("G".equals(map[r][c])) return depth;

                for (int[] dir : MOVES) {
                    int nr = r + dir[0], nc = c + dir[1];
                    if (isInBounds(nr, nc) && !visited[nr][nc] &&
                        !"P".equals(map[nr][nc]) && !"W".equals(map[nr][nc])) {
                        queue.add(new int[]{nr, nc});
                        visited[nr][nc] = true;
                    }
                }
            }
        }
        return Integer.MAX_VALUE;
    }

    private void getDirection(int dir) {
        String[] directions = {"Down ↓", "Up ↑", "Right →", "Left ←"};
        if (dir >= 0 && dir < directions.length) {
            showMessage(directions[dir], Alert.AlertType.INFORMATION);
        }
    }
}
