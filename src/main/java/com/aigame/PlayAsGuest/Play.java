package com.aigame.PlayAsGuest;

import java.net.URL;
import java.util.Optional;

import com.aigame.Controller.AppController;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class Play {
    public final int SIZE = 5;
    public final int CELL_SIZE = 120;
    public int agentRow = 4;
    public int agentCol = 0;
    public AppController appController;
    public MediaPlayer mediaPlayer;

    public final String getColor(String cell) {
        switch (cell) {
            case "S":
                return "#0b6710ff";
            case "B":
                return "#194591ff";
            case "BS":
                return "#1b797eff";
            case "G":
                return "#ecb532ff";
            default:
                return "#a4cff2ff";
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

    public final void drawBoard(GridPane board, String[][] map, String[][] finalmap, String imageUrl) {
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
                    stackPane.setStyle(
                            "-fx-border-color: black; -fx-background-color: " + getColor(finalmap[agentRow][agentCol]));
                    setAgentbackgroundSound(finalmap);
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

    private void setAgentbackgroundSound(String[][] finalmap) {
        String back=finalmap[agentRow][agentCol];
        switch (back) {
            case "S":
                sound("/Audio/WumpusSound.mp3");
                break;
            case "B":
                 sound("/Audio/breeze.mp3");
                break;
            case "BS":
                 sound("/Audio/WumpusSound.mp3");
                break;
        }
    }

    public final void sound(String url) {
        URL resource = getClass().getResource(url);
        if (resource == null) {
            System.err.println("Audio file not found!");
            return;
        }
        try{
            Media media = new Media(resource.toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();
        }catch(Exception exception){
            throw new RuntimeException(exception.getMessage()+"In the Play.java sound function");
        }
    }
    public void win(){
       
    }
    public boolean playingWill(){
        Alert alert=new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Congratulatins!");
        alert.setHeaderText("Did you want to play agian");
        alert.setContentText("wanted to continue press Ok");
        if(alert.showAndWait().get()==ButtonType.OK){
            return true;
        }
        return false;
    }

    public int[] shoot(){
            Alert alert=new Alert(AlertType.CONFIRMATION);
            alert.setHeaderText("Choose Direction!");
            alert.setContentText("Conferm where have to shoot:");
            ButtonType up=new ButtonType("↑");
            ButtonType down=new ButtonType("↓");
            ButtonType left=new ButtonType("←");
            ButtonType right=new ButtonType("→");
            ButtonType cancle=new ButtonType("Cancle");
            alert.getButtonTypes().setAll(up,down,left,right,cancle);
            Optional<ButtonType> result=alert.showAndWait();
            int arr[]=new int[]{agentRow,agentCol};
            if(result.get()==up){
                arr[0]-=1;
            }else if(result.get()==down){
                arr[0]+=1;
            }else if(result.get()==left){
                arr[1]-=1;
            }else if(result.get()==right){
                arr[1]+=1;
            }
            return result.get()==cancle?null:arr;
    }
    public final void killWumpus(String[][] finalmap,int row,int col){
        finalmap[row][col]="";
        int [][] arr=new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
        for(int i=0;i<4;i++){
            int x=row+arr[i][0];int y=col+arr[i][1];
            if(isInBounds(x,y)){
                if(finalmap[x][y].equals("S"))finalmap[x][y]="";
                else if(finalmap[x][y].equals("BS"))finalmap[x][y]="B";
            }
        }
    }
    public final void healthBar(){
        
    }
}

