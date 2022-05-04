package com.example.nastya;

import com.example.nastya.Modal.Agent;
import com.example.nastya.Modal.Application;
import com.example.nastya.Modal.Estate;
import com.example.nastya.Modal.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class UserController {
    ArrayList<User> users;
    User user;
    ArrayList<Agent> agents;
    @FXML
    Label nameValue;
    @FXML
    Label balanceValue;
    @FXML
    ListView<String> listView;

    public void makeApplication(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("application.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("PROPsystem");
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("home.png"))));
        stage.setScene(scene);
        ApplicationController applicationController = loader.getController();
        applicationController.init(user,users,agents);
        stage.show();
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
    }
    public void  showApplication(ActionEvent event){
        listView.getItems().clear();
        ArrayList<String> temp = new ArrayList<>();
        for (Agent agent: agents){
            for (Application application : agent.getListOfApplication()){
                if (application.getClientName().equals(user.getLogin())){
                    temp.add(application.toString());
                }
            }
        }
        listView.getItems().addAll(temp);
    }
    public void  showEstates(ActionEvent event){
        listView.getItems().clear();
      ArrayList<String> temp = new ArrayList<>();
      for (Estate estate : user.getListOfProperty()){
          temp.add(estate.toString());
      }
      listView.getItems().addAll(temp);
    }
    public void init(ArrayList<User> users,ArrayList<Agent> agents,User user){
        this.agents  = agents;
        this.users = users;
        this.user = user;
        nameValue.setText(user.getName());
        balanceValue.setText(String.valueOf(user.getBalance()));
    }
}
