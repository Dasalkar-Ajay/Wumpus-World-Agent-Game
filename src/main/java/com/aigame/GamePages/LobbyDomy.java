package com.aigame.GamePages;

import com.aigame.Controller.AppController;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

public class LobbyDomy {

    private AppController appController;
    private GridPane view;

    public LobbyDomy(AppController appController) {
        this.appController = appController;
        initialize();
    }

    private void initialize() {
        view = new GridPane();
        VBox profileBox = getProfileBox();
        VBox avtarBox = getAvtarBox();

        view.add(avtarBox, 0, 0);
        view.add(profileBox, 1, 0);

        avtarBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        avtarBox.prefWidthProperty().bind(view.widthProperty());
        avtarBox.prefHeightProperty().bind(view.heightProperty());

        profileBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        profileBox.prefHeightProperty().bind(view.widthProperty());
        profileBox.prefWidthProperty().bind(view.heightProperty());

        view.setStyle("-fx-background-color: rgb(43, 42, 46);");
    }

    public GridPane getView() {
        return view;
    }

    private VBox getAvtarBox() {
        Label avtarLable = new Label("AVTARS");
        avtarLable.setStyle(" -fx-background-color:rgb(182, 128, 113);" +
                "-fx-font-size: 30px;" +
                "-fx-padding: 10 20 10 20;" +
                "-fx-background-radius: 10;" +
                "-fx-border-color: white;" +
                "-fx-border-radius: 10;" +
                "-fx-border-width: 2px;" +
                "-fx-text-fill: golden;");

        Image avtarImage = new Image("/Images/Avtar/Avtarno0.jpg");
        ImageView imageView = new ImageView(avtarImage);
        imageView.setFitHeight(400);
        imageView.setFitWidth(400);

        // Clip image to rounded corners
        Rectangle clip = new Rectangle(400, 400);
        clip.setArcWidth(60);
        clip.setArcHeight(60);
        imageView.setClip(clip);

        VBox imageContainer = new VBox(imageView);
        imageContainer.setPadding(new Insets(3)); // Space to show border

        Button leftArrow = new Button("➤");
        Button rightArrow = new Button("➤");

        // Flip the left arrow using CSS transform
        leftArrow.setStyle(
                " -fx-font-size: 36px;" +
                        "-fx-background-color: #444;" +
                        "-fx-text-fill: white;" +
                        "-fx-background-radius: 50;" +
                        "-fx-cursor: hand;" +
                        "-fx-rotate: 180;");

        rightArrow.setStyle(
                " -fx-font-size: 36px;" +
                        "-fx-background-color: #444;" +
                        "-fx-text-fill: white;" +
                        "-fx-cursor: hand;" +
                        "-fx-background-radius: 50;");

        HBox avtarImageBox = new HBox(30, leftArrow, imageView, rightArrow);
        avtarImageBox.setAlignment(Pos.CENTER);

        Button buyButton = new Button("Buy");
        buyButton.setStyle(
                "-fx-font-size: 16px;" +
                        "-fx-font-size: 30px;" +
                        "-fx-background-color: rgb(56, 146, 33);" +
                        "-fx-text-fill: white;" +
                        "-fx-background-radius: 15;" +
                        "-fx-padding: 5 15 5 15;" +
                        "-fx-content-display: LEFT;" +
                        "-fx-alignment: CENTER;");

        Button equipButton = new Button("Equap");
        equipButton.setStyle(
                "-fx-font-size: 16px;" +
                        "-fx-font-size: 30px;" +
                        "-fx-background-color: rgb(214, 214, 59);" +
                        "-fx-text-fill: white;" +
                        "-fx-background-radius: 15;" +
                        "-fx-padding: 5 15 5 15;" +
                        "-fx-content-display: LEFT;" +
                        "-fx-alignment: CENTER;");

        HBox buttonBox = new HBox(30);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(buyButton, equipButton);

        VBox avtarBox = new VBox(30, avtarLable, avtarImageBox, buttonBox);
        avtarBox.setAlignment(Pos.CENTER);
        avtarBox.setPadding(new Insets(20));

        return avtarBox;
    }

    private VBox getProfileBox() {

        Image profileImage = new Image("/Images/Avtar/ProfileImage.png");
        Image settingImage = new Image("/Images/Avtar/SettingImage.png");

        ImageView profileImageView = new ImageView(profileImage);
        ImageView settinImageView = new ImageView(settingImage);
        settinImageView.setFitHeight(50);
        settinImageView.setFitWidth(50);

        profileImageView.setFitHeight(50);
        profileImageView.setFitWidth(50);

        Button profileButton = new Button("Profile", profileImageView);
        profileButton.setPrefSize(180, 60);
        profileButton.setStyle(
                "-fx-font-size: 16px;" +
                        "-fx-background-color: rgb(161, 148, 133);" +
                        "-fx-text-fill: black;" +
                        "-fx-background-radius: 30;" +
                        "-fx-padding: 5 15 5 15;" +
                        "-fx-content-display: LEFT;" +
                        "-fx-alignment: CENTER_LEFT;");

        Button settingButton = new Button();
        settingButton.setGraphic(settinImageView);

        Button playButton = new Button("Play");
        playButton.setPrefSize(150, 50);
        playButton.setStyle(
                "-fx-font-size: 16px;" +
                        "-fx-font-size: 30px;" +
                        "-fx-background-color: rgb(156, 22, 22);" +
                        "-fx-text-fill: white;" +
                        "-fx-background-radius: 15;" +
                        "-fx-padding: 5 15 5 15;" +
                        "-fx-alignment: CENTER;");

        VBox profilesettingBox = new VBox(30, profileButton, settingButton);
        profilesettingBox.setAlignment(Pos.TOP_RIGHT);

        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        VBox vBox = new VBox(30, profilesettingBox, spacer, playButton);
        vBox.setAlignment(Pos.TOP_RIGHT);
        vBox.setPadding(new Insets(30, 20, 50, 30));

        return vBox;
    }
}
