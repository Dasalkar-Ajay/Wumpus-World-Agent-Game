package com.aigame.Login_SignUp;


import java.sql.SQLException;

import com.aigame.Controller.AppController;
import com.aigame.SqlHandling.SqlQueryPerformer;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class SignUpPage {
    private AppController appController;
    private GridPane view;
    private SqlQueryPerformer sqlQueryPerformer;
    private ApiRandomPhoto apiPhoto;

    public SignUpPage(AppController appController){
        this.appController=appController;
        initialize();
    }
    private void initialize(){
        view=new GridPane();
        sqlQueryPerformer=new SqlQueryPerformer();
        apiPhoto=new ApiRandomPhoto();
        Image image;
        try {
             image=new Image(apiPhoto.getResponseDAta());
        } catch (Exception e) {
            Label label=new Label();
            label.setText(e.getMessage()+"Error is occurs at the time of Processing\nd The API Please help and stay calm");
            label.setAlignment(Pos.CENTER);
            view.add(label, 0, 0);
             return;
        }
        ImageView imageView=new ImageView(image);
        imageView.fitHeightProperty().bind(view.heightProperty());
        imageView.fitWidthProperty().bind(view.widthProperty());

        Label signUplabel=new Label("SignUp Here....");
        signUplabel.setStyle("-fx-font-size: 40px; -fx-text-fill: #1D1616;");

        TextField signUpIdField=new TextField();
        signUpIdField.setPromptText("enter the email or no please--->");

        Label passLabel=new Label("Enter Password Here--");
        PasswordField passwordField=new PasswordField();
        passwordField.setPromptText("Enter CareFully");

        Button signUpButton=new Button("SignUP");
        signUpButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                String id=signUpIdField.getText();
                String password=passwordField.getText();

                if(id.length()==0 || password.length()==0){
                    Alert alertnotFill=new Alert(AlertType.INFORMATION);
                    alertnotFill.setTitle("Incomplete Information:");
                    alertnotFill.setContentText("Please Fill The email-Phone no and password");
                    alertnotFill.show();
                    signUpIdField.clear();
                    passwordField.clear();
                }

                if(sqlQueryPerformer.isIdPresent(id)){
                    Alert alertnotFill=new Alert(AlertType.INFORMATION);
                    alertnotFill.setTitle("Already person Present at these Id");
                    alertnotFill.setContentText("Please Try With Diff Phone No or Email");
                    alertnotFill.show();
                    signUpIdField.clear();
                    passwordField.clear();
                }else{
                    try {
                        sqlQueryPerformer.addPlayer(id,password);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    appController.navigateToLoginPage();
                }
            }
            
        });

        Hyperlink loginlink=new Hyperlink("Login\nif already SignUP");
        loginlink.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
             appController.navigateToLoginPage();
            }
        });

        Button backButton=new Button("Back");
        backButton.setLayoutX(30);
        backButton.setLayoutY(30);

        backButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
              appController.navigateToHomePage();
            }
            
        });


        Pane backbuttonPane=new Pane(backButton);

        HBox entering=new HBox(50,signUpButton,loginlink);
        VBox loginFormBox=new VBox(20,signUplabel,signUpIdField,passLabel,passwordField,entering);
       loginFormBox.setAlignment(Pos.CENTER);
       loginFormBox.setMaxWidth(350);


        StackPane stackPane=new StackPane(imageView,backbuttonPane,loginFormBox);
        view.getChildren().add(stackPane);
    }
    public GridPane getView(){
        return view;
    }
}
