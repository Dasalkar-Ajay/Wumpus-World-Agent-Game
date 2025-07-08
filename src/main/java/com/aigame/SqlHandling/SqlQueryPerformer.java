package com.aigame.SqlHandling;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlQueryPerformer {

    Connection connection;
    Statement statement;

    public SqlQueryPerformer() {
        connection = SqlConnector.makeConnection();
        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean isPresent(String id, String password) {
        String query = "SELECT * FROM LoginPersons WHERE email = '" + id + "' AND password = '" + password + "'";
        try {
            ResultSet result = statement.executeQuery(query);
            if (result.next())
                return true;
            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean isIdPresent(String id) {
        String query = "SELECT * FROM LoginPersons WHERE email = '" + id + "'";
        try {
            ResultSet result = statement.executeQuery(query);
            if (result.next())
                return true;
            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

   public void addPlayer(String id, String password) throws SQLException {
    try {

        String insertQuery = "INSERT INTO LoginPersons (EMAIL, PASSWORD) VALUES ('" + id + "', '" + password + "')";
        int insertedRows = statement.executeUpdate(insertQuery);
        if (insertedRows != 1) {
            System.out.println("Insert failed.");
            return;
        }

        String selectQuery = "SELECT * FROM LoginPersons WHERE email = '" + id + "' AND password = '" + password + "'";
        ResultSet result = statement.executeQuery(selectQuery);

        if (result.next()) {
            String personId = result.getString("Person_ID");
            String phone=result.getString("email");
            String updateQuery = "UPDATE LoginPersons SET name = 'Player" + personId + "' WHERE Person_ID = " + personId;
            int updatedRows = statement.executeUpdate(updateQuery);

            if (updatedRows != 1) {
                System.out.println("Update failed.");
            } else {
                System.out.println("All done: registered successfully.");
            }

            String insertQueryAvtar = "INSERT INTO Avtar (phone, name) VALUES ('" + phone + "', 'Player" + personId + "')";
            insertedRows = statement.executeUpdate(insertQueryAvtar);
        if (insertedRows != 1) {
            System.out.println("Insert failed.");
            return;
        }else
            System.out.println("there is creation of the Avtar table also");
            
        } else {
            System.out.println("Record not found after insert.");
        }
        result.close();
        connection.commit();
    } catch (SQLException e) {
        connection.rollback();
        System.out.println(e.getMessage());
        System.out.println("Problem at the time of Sign Up.");
    }
}

   public ResultSet getPlayerData(String phone) {
     String selectQuery = "SELECT * FROM Avtar WHERE  phone= '" +phone + "'";
        try {
           return statement.executeQuery(selectQuery);
        } catch (SQLException e) {
          System.out.println(e.getMessage()+"\nThe error in the getPlayerData SqlQueryPerformer");
          throw new RuntimeException("IN SQLQueryPerformer GetPlayerDAta Function");
        }
   }
}
