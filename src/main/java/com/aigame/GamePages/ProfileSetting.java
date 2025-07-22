package com.aigame.GamePages;

import com.aigame.Controller.AppController;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ProfileSetting {
   

    public static void showProfile(String name, int total_Play, int total_win, int total_loss, float money) {
        Stage primaryStage = new Stage();
        Label nameLabel = new Label(name);
        Label totalPlayLabel = new Label("Total_Play:: " + total_Play);
        Label totalwinLabel = new Label("Total_Win:: " + total_win);
        Label totalLossLabel = new Label("Total_Loss:: " + total_loss);
        Label totalMoney = new Label("Paisa Bohot Hai --> " + money);

        VBox profileBox = new VBox(20, nameLabel, totalPlayLabel, totalwinLabel, totalLossLabel, totalMoney);
        profileBox.setAlignment(Pos.CENTER);
        profileBox.setStyle("-fx-background-color: lightblue;"); 

        Scene scene = new Scene(profileBox, 300, 250);
        primaryStage.setTitle("Player Profile");
        primaryStage.setScene(scene);
        primaryStage.show();
        PauseTransition delay = new PauseTransition(Duration.seconds(5));
        delay.setOnFinished(event -> primaryStage.close());
        delay.play();
    }

    public static void  getSetting(AppController appController,LobbyPage lobbyPage){
        Stage primaryStage = new Stage();
        Label Music=new Label("Music");
        Button stopButton=new Button("Stop");
        stopButton.setOnAction(e->{appController.mediaPlayer.stop();});

        Button playButton=new Button("play");
        playButton.setOnAction(e->{appController.mediaPlayer.play();});


        HBox buttonBox=new HBox(30,stopButton,playButton);
        buttonBox.setAlignment(Pos.CENTER);

        Label labelLevel=new Label("Set the Level You Want to play::");
        Button easyButton=new Button("Easy");
        easyButton.setOnAction(e->{lobbyPage.setLevel(0);});

        Button mediumButton=new Button("Medium");
        mediumButton.setOnAction(e->{lobbyPage.setLevel(1);});

        Button hardButton=new Button("Hard");
        hardButton.setOnAction(e->{lobbyPage.setLevel(2);});

        HBox levelBox=new HBox(easyButton,mediumButton,hardButton);
        levelBox.setAlignment(Pos.CENTER);

        VBox settingBox = new VBox(20,Music,buttonBox ,labelLevel,levelBox);
        settingBox.setAlignment(Pos.CENTER);
        settingBox.setStyle("-fx-background-color: lightblue;"); 

        Scene scene = new Scene(settingBox, 300, 250);
        primaryStage.setTitle("Settings Profile");
        primaryStage.setScene(scene);
        primaryStage.show();

        PauseTransition delay = new PauseTransition(Duration.seconds(5));
        delay.setOnFinished(event -> primaryStage.close());
        delay.play();
    }

}
