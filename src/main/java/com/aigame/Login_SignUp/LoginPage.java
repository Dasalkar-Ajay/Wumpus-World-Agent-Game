package com.aigame.Login_SignUp;

import com.aigame.Controller.AppController;
import com.aigame.SqlHandling.SqlQueryPerformer;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class LoginPage {
    private AppController appController;
    private GridPane view;
    private ApiRandomPhoto apiPhoto;
    private SqlQueryPerformer sqlQueryPerformer;

    public LoginPage(AppController appController){
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

        Label loginlabel=new Label("Login Here....");
        loginlabel.setStyle("-fx-font-size: 40px; -fx-text-fill: #1D1616;");

        TextField loginIdField=new TextField();
        loginIdField.setPromptText("enter the email or no please--->");

        Label passLabel=new Label("Enter Password Here--");
        PasswordField passwordField=new PasswordField();
        passwordField.setPromptText("Enter CareFully");

        Button loginButton=new Button("Login");
        loginButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
              String id=loginIdField.getText();
              String password=passwordField.getText();
                if(id.length()==0 || password.length()==0){
                    Alert alertnotFill=new Alert(AlertType.INFORMATION);
                    alertnotFill.setTitle("Incomplete Information:");
                    alertnotFill.setContentText("Please Fill The email-Phone no and password");
                    alertnotFill.show();
                    loginIdField.clear();
                    passwordField.clear();
                }else if(sqlQueryPerformer.isPresent(id,password)){

                    appController.navigateToLobbyPage(id,sqlQueryPerformer);

               }else{

                if(sqlQueryPerformer.isIdPresent(id)){
                    Alert alertnotFill=new Alert(AlertType.WARNING);
                    alertnotFill.setTitle("InCorrect Infromation");
                    alertnotFill.setContentText("incorrect Password!!!");
                    alertnotFill.show();
                    loginIdField.clear();
                    passwordField.clear();
                }else{
                 Alert alertnotFill=new Alert(AlertType.WARNING);
                    alertnotFill.setTitle("InCorrect Infromation");
                    alertnotFill.setContentText("Your email or Password is failed \n Or Your not Sign up here please SignUp");
                    alertnotFill.show();
                    loginIdField.clear();
                    passwordField.clear();
                }
               }

            }
        });
    
        Hyperlink signuplink=new Hyperlink("sign-up\nif not done");
        signuplink.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
             appController.navigateToSignUpPage();
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

        HBox entering=new HBox(50,loginButton,signuplink);
        VBox loginFormBox=new VBox(20,loginlabel,loginIdField,passLabel,passwordField,entering);
       loginFormBox.setAlignment(Pos.CENTER);
       loginFormBox.setMaxWidth(350);


        StackPane stackPane=new StackPane(imageView,backbuttonPane,loginFormBox);
        view.getChildren().add(stackPane);
    }
    public GridPane getView(){
        return view;
    }
}
