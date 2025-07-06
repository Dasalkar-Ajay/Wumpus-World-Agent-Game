package com.aigame.HomePage;

import com.aigame.Controller.AppController;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class HomePage {
    private GridPane viewPage;
    private AppController appController;
    private StackPane stackPane;

    public HomePage(AppController appController) {
        this.appController = appController;
        pageInitialze();
    }

    private void pageInitialze() {
        stackPane = new StackPane();
        viewPage = new GridPane();

        Image image = new Image(getClass().getResource("/Images/HomePageImage/HomePage.png").toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.fitHeightProperty().bind(viewPage.heightProperty());
        imageView.fitWidthProperty().bind(viewPage.widthProperty());

        Label welcome = new Label("WELCOME TO HELL");
        welcome.setStyle("-fx-font-size: 30px; -fx-text-fill: gold;");

        Label title = new Label("Wumpus World Agent Game");
        title.setStyle("-fx-font-size: 45px ;");

        // Flicker text fill color between red and dark red
        Timeline flickerTitle = new Timeline(
                new KeyFrame(Duration.seconds(0), e -> title.setTextFill(Color.RED)),
                new KeyFrame(Duration.seconds(0.1), e -> title.setTextFill(Color.DARKRED)),
                new KeyFrame(Duration.seconds(0.2), e -> title.setTextFill(Color.RED)),
                new KeyFrame(Duration.seconds(0.3), e -> title.setTextFill(Color.DARKRED)));
        flickerTitle.setCycleCount(Animation.INDEFINITE);
        flickerTitle.setAutoReverse(false);
        flickerTitle.play();

        // Scale pulsing
        ScaleTransition scaleTitle = new ScaleTransition(Duration.seconds(1.5), title);
        scaleTitle.setFromX(1.0);
        scaleTitle.setFromY(1.0);
        scaleTitle.setToX(1.3);
        scaleTitle.setToY(1.3);
        scaleTitle.setCycleCount(Animation.INDEFINITE);
        scaleTitle.setAutoReverse(true);
        scaleTitle.play();

        Button aboutUsButton = new Button("About Us");
        Button helpButton = new Button("Help");
        Button signUpButton = new Button("Sign Up");
        Button loginButton = new Button("Login");
        Button playAsGuestButton = new Button("Play as Guest");



        aboutUsButton.setStyle(
                "-fx-background-color: darkred;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 14px;");

        helpButton.setStyle(
                "-fx-background-color: darkred;" +
                        "-fx-text-fill: white;" +
                       "-fx-font-size: 14px;");

        signUpButton.setStyle(
                "-fx-background-color: darkred;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 14px;");

        loginButton.setStyle(
                "-fx-background-color: darkred;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 14px;");

        playAsGuestButton.setStyle(
                "-fx-background-color: darkred;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 14px;");



        // Left VBox with About Us and Help
        VBox leftVBox = new VBox(50, aboutUsButton, helpButton);
        leftVBox.setPadding(new Insets(20));
        leftVBox.setAlignment(Pos.CENTER_LEFT);

        // Right VBox with Sign Up, Login, Play as Guest
        VBox rightVBox = new VBox(50, signUpButton, loginButton, playAsGuestButton);
        rightVBox.setPadding(new Insets(20));
        rightVBox.setAlignment(Pos.CENTER_RIGHT);


        // Set alignment of VBoxes in StackPane
        StackPane.setAlignment(leftVBox, Pos.CENTER_LEFT);
        StackPane.setAlignment(rightVBox, Pos.CENTER_RIGHT);
        
        VBox name=new VBox(title,welcome);
        name.setAlignment(Pos.CENTER);


        // Add everything
        stackPane.getChildren().addAll(imageView, name, leftVBox, rightVBox);
        viewPage.getChildren().add(stackPane);
    }

    public GridPane getView() {
        return viewPage;
    }
}
