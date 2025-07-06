package com.aigame.Controller;

import java.net.URL;

import com.aigame.AboutUs.AboutUsPage;
import com.aigame.HomePage.HomePage;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;


public class AppController extends Application {

    private Stage primaryStage;
    private Scene homePageScene, aboutUsPageScene;

    private HomePage homePage;
    private AboutUsPage aboutUsPage;

    // ADD THIS FIELD TO KEEP A STRONG REFERENCE
    private MediaPlayer mediaPlayer;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        homePage = new HomePage(this);
        aboutUsPage = new AboutUsPage(this);

        URL resource = getClass().getResource("/Audio/scary(chosic.com).mp3");
        if (resource == null) {
            System.err.println("Audio file not found!");
            return;
        }
        Media media = new Media(resource.toURI().toString());


        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); 
        mediaPlayer.play();

        aboutUsPageScene = new Scene(aboutUsPage.getView(), 1000, 730, true);
        primaryStage.setTitle("AboutUs Page");
        primaryStage.setScene(aboutUsPageScene);
        primaryStage.show();
    }
}
