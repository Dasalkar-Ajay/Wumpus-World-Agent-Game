package com.aigame.Controller;

import java.net.URL;

import com.aigame.AboutUs.AboutUsPage;
import com.aigame.GamePages.LobbyPage;
import com.aigame.HomePage.HelpPage;
import com.aigame.HomePage.HomePage;
import com.aigame.Login_SignUp.LoginPage;
import com.aigame.Login_SignUp.SignUpPage;
import com.aigame.PlayAsGuest.PlayAsGuest;
import com.aigame.SqlHandling.SqlQueryPerformer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;


public class AppController extends Application {
    // These are the Instances of the classses
    private Stage primaryStage;
    private HomePage homePage;
    private AboutUsPage aboutUsPage;
    public MediaPlayer mediaPlayer;
    private HelpPage helpPage;
    private LoginPage loginPage;
    private SignUpPage signUpPage;
    private LobbyPage lobbyPage;
    private PlayAsGuest playAsGuest;

    //These are the Scenes of classes
    private Scene homePageScene, aboutUsPageScene,helpPageScene,loginPageScene,signUpPageScene,lobbyPageScene,playAsGuestScene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        homePage = new HomePage(this);
        aboutUsPage = new AboutUsPage(this);
        helpPage=new HelpPage(this);
        loginPage=new LoginPage(this);
        signUpPage=new SignUpPage(this);
        lobbyPage=new LobbyPage(this);
        playAsGuest=new PlayAsGuest(this);
        
        URL resource = getClass().getResource("/Audio/scary(chosic.com).mp3");
        if (resource == null) {
            System.err.println("Audio file not found!");
            return;
        }
        
        Media media = new Media(resource.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); 
        mediaPlayer.play();

        //---------------------------------------------------------------
        // ADDing Audio in Game
        // ----------------------------------------------------------------

        homePageScene=new Scene(homePage.getView(),1300,750);
        aboutUsPageScene = new Scene(aboutUsPage.getView(),1300,750);
        helpPageScene=new Scene(helpPage.getView(),1300,750);
        loginPageScene=new Scene(loginPage.getView(),1300,750);
        signUpPageScene=new Scene(signUpPage.getView(),1300,750);
        playAsGuestScene=new Scene(playAsGuest.getView(),1300,750);

        primaryStage.setTitle("Wumpus World Agent Game");
        primaryStage.setScene(homePageScene);
        primaryStage.show();
    }

    public void navigateToHomePage(){
        primaryStage.setScene(homePageScene);
    }
    
    public void navigateToAboutUsPage(){
        primaryStage.setScene(aboutUsPageScene);
    }

    public void navigateToHelpPage(){
        primaryStage.setScene(helpPageScene);
    }

    public void navigateToLoginPage(){
        primaryStage.setScene(loginPageScene);
    }

    public void navigateToSignUpPage(){
        primaryStage.setScene(signUpPageScene);
    }
    
    public void navigateToLobbyPage(String id, SqlQueryPerformer sqlQueryPerformer) {
         lobbyPageScene=new Scene(lobbyPage.getView(id,sqlQueryPerformer),1300,750);
        primaryStage.setScene(lobbyPageScene);
    }

    public void navigateToPlayAsGuest(){
        primaryStage.setScene(playAsGuestScene);
    }

    public void navigateToPlayPage() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'navigateToPlayPage'");
    }
}
