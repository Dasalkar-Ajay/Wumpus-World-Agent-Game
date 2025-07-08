package com.aigame.HomePage;

import com.aigame.Controller.AppController;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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
                aboutUsButton.setStyle(
                                "-fx-background-color: darkred;" +
                                                "-fx-text-fill: white;" +
                                                "-fx-font-size: 14px;");

                aboutUsButton.setOnAction(new EventHandler<ActionEvent>() {

                        @Override
                        public void handle(ActionEvent event) {
                                appController.navigateToAboutUsPage();
                        }

                });

                Button helpButton = new Button("Help");
                helpButton.setStyle(
                                "-fx-background-color: darkred;" +
                                                "-fx-text-fill: white;" +
                                                "-fx-font-size: 14px;");
                helpButton.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                                appController.navigateToHelpPage();
                        }
                });

                Button signUpButton = new Button("Sign Up");
                signUpButton.setStyle(
                                "-fx-background-color: darkred;" +
                                                "-fx-text-fill: white;" +
                                                "-fx-font-size: 14px;");
                signUpButton.setOnAction(new EventHandler<ActionEvent>() {

                        @Override
                        public void handle(ActionEvent event) {
                              appController.navigateToSignUpPage();
                        }
                        
                });



                Button loginButton = new Button("Login");
                loginButton.setStyle(
                                "-fx-background-color: darkred;" +
                                                "-fx-text-fill: white;" +
                                                "-fx-font-size: 14px;");
                loginButton.setOnAction(new EventHandler<ActionEvent>() {

                        @Override
                        public void handle(ActionEvent event) {
                             appController.navigateToLoginPage();
                        }
                        
                });

                Button playAsGuestButton = new Button("Play as Guest");
                playAsGuestButton.setStyle(
                                "-fx-background-color: darkred;" +
                                                "-fx-text-fill: white;" +
                                                "-fx-font-size: 14px;");

                // Left VBox with About Us and Help
                VBox leftVBox = new VBox(50, aboutUsButton, helpButton);
                leftVBox.setPadding(new Insets(40));
                
                // Right VBox with Sign Up, Login, Play as Guest
                VBox rightVBox = new VBox(50, signUpButton, loginButton, playAsGuestButton);
                rightVBox.setPadding(new Insets(40));
                
                VBox name = new VBox(title, welcome);
                name.setAlignment(Pos.CENTER);
                
                HBox hBox = new HBox(leftVBox, rightVBox);
                leftVBox.setMaxWidth(Double.MAX_VALUE);
                leftVBox.setMaxHeight(Double.MAX_VALUE);
                leftVBox.prefWidthProperty().bind(hBox.widthProperty());
                leftVBox.prefHeightProperty().bind(hBox.heightProperty());

                rightVBox.setMaxWidth(Double.MAX_VALUE);
                rightVBox.setMaxHeight(Double.MAX_VALUE);
                rightVBox.prefWidthProperty().bind(hBox.widthProperty());
                rightVBox.prefHeightProperty().bind(hBox.heightProperty());
                rightVBox.setAlignment(Pos.BOTTOM_RIGHT);

             

                // Add everything
                stackPane.getChildren().addAll(imageView, name, hBox);
                viewPage.getChildren().add(stackPane);     
        }

        public GridPane getView() {
                return viewPage;
        }
}
