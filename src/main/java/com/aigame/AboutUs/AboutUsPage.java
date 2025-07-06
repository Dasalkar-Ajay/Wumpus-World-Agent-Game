package com.aigame.AboutUs;

import com.aigame.Controller.AppController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class AboutUsPage {
    private GridPane view;
    private AppController appController;

    public AboutUsPage(AppController appController) {
        this.appController = appController;
        initialize();
    }

    private void initialize() {
        view = new GridPane();
        view.setPadding(new Insets(20));
        view.setStyle("-fx-background-color: #222;"); // Optional: dark background

        String text = "Hi, this is Ajay Dasalkar from Sinhgad College of Engineering, Pune.\n\n"
                + "I developed this project inspired by the AI problem 'Wumpus World Agent'.\n"
                + "As this is a solo project, I hope you enjoy it!\n\n"
                + "This project was created to help me better understand Java concepts.";

        Label description = new Label(text);
        description.setWrapText(true);
        description.setPadding(new Insets(20));
        description.setStyle("-fx-font-size: 20px; -fx-text-fill: gold;");

        Button backButton=new Button("Back");

        view.add(description, 0, 0);
        view.add(backButton, 1, 1);
    }

    public GridPane getView() {
        return view;
    }
}
