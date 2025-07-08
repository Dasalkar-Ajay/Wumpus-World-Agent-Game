package com.aigame.GamePages;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.aigame.Controller.AppController;
import com.aigame.SqlHandling.SqlQueryPerformer;

import javafx.scene.layout.GridPane;

public class LobbyPage {

    GridPane view;
    AppController appController;
    SqlQueryPerformer sqlQueryPerformer;


    public LobbyPage(AppController appController){
        this.appController=appController;
    }

    private void initialize(String phone) {
        // this.sqlQueryPerformer=sqlQueryPerformer;
        // this.view=new GridPane();
        // ResultSet playerData=sqlQueryPerformer.getPlayerData(phone);
        // try {
            // playerData.next();
            // int totalPlay=playerData.getInt("total_play");
            // int total_win=playerData.getInt("total_win");
            // int total_loss=playerData.getInt("total_loss");
            // float money=playerData.getFloat("money");
            // int avtar_unlocked=playerData.getInt("avtar_unlock");
            // String name=playerData.getString("name");
            int totalPlay=10;
            int total_win=5;
            int total_loss=5;
            float money=10000;
            int avtar_unlocked=2;
            String name="ajay";

        // } catch (SQLException e) {
        //    System.out.println("Error in the initialization of the retriving data from table:");
        //    System.out.println(e.getMessage());
        // }


    }

    public GridPane getView(String id,SqlQueryPerformer sqlQueryPerformer) {
        this.sqlQueryPerformer=sqlQueryPerformer;
        initialize(id);
        return view;
    }  

}
