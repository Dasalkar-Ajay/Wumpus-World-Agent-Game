package com.aigame.HomePage;

import com.aigame.Controller.AppController;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class HelpPage {
    private AppController appController;
    private GridPane view;

    public HelpPage(AppController appController) {
        this.appController = appController;
        initialize();
    }

    private void initialize() {
        view = new GridPane();

        // Background image
        Image image = new Image("/Images/HomePageImage/HelpPage.png");
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(false);
        imageView.fitHeightProperty().bind(view.heightProperty());
        imageView.fitWidthProperty().bind(view.widthProperty());

        // Back button
        Button backButton = new Button("Back");
        backButton.setStyle(
              
                "-fx-text-fill:rgb(54, 114, 173);" +
                "-fx-font-size: 16px;");

        backButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                appController.navigateToHomePage();
            }
            
        });

        // Set exact position
        backButton.setLayoutX(30);
        backButton.setLayoutY(50);

        // Use Pane for absolute positioning
        Pane buttonPane = new Pane(backButton);

        // StackPane to keep background image behind
        StackPane root = new StackPane(imageView, buttonPane);

        view.getChildren().add(root);
    }

    public GridPane getView() {
        return view;
    }
}
