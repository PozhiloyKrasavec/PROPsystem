package com.example.nastya;

import com.example.nastya.Modal.Agent;
import com.example.nastya.Modal.Application;
import com.example.nastya.Modal.Estate;
import com.example.nastya.Modal.User;
import com.example.nastya.Modal.tasks.WriteAgentsTask;
import com.example.nastya.Modal.tasks.WriteUsersTask;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ApplicationController {
    ArrayList<User> users;
    User user;
    Agent agent;
    Estate estate;
    ArrayList<Agent> agents;
    @FXML
    MenuButton agentsItem;
    @FXML
    MenuButton estatesItem;
    public void init(User user, ArrayList<User> users, ArrayList<Agent> agents){
        ArrayList<MenuItem> menuItemArrayList = new ArrayList<>();
        ArrayList<MenuItem> menuItems = new ArrayList<>();
        this.agents  = agents;
        this.users = users;
        this.user = user;
        agents.forEach(agent1 -> {
            MenuItem item = new MenuItem(agent1.toString());
            item.setOnAction(event -> {
                agent = agent1;
                agent1.getListOfProperty().forEach(estate1 -> {
                    String temp = estate1.toString();
                    MenuItem item1 = new MenuItem(temp);
                    item1.setOnAction(event1->{
                        estate = estate1;
                        estatesItem.setText(estate.toString());
                    });
                    menuItems.add(item1);
                });
                agentsItem.setText(agent.toString());
                estatesItem.getItems().addAll(menuItems);
            });
            menuItemArrayList.add(item);
        });
        agentsItem.getItems().addAll(menuItemArrayList);
    }
    public void makeDeal(ActionEvent event) throws IOException {
        agent.getListOfApplication().add(new Application(user.getLogin(), agent.getLogin(),estate.getId()));
        agent.getListOfProperty().remove(estate);
        user.getListOfProperty().add(estate);
        user.setBalance(user.getBalance() - estate.getPrice());
        agent.setBalance(agent.getBalance()+estate.getPrice());
        for (Agent agent1 : agents){
            if (agent1.getLogin().equals(agent.getLogin())) agent1 = agent;
        }
        for (User user1 : users){
            if (user1.getLogin().equals(user.getLogin())) user1 = user;
        }
        WriteAgentsTask writeAgentsTask = new WriteAgentsTask(agents,users);
        WriteUsersTask writeUsersTask = new WriteUsersTask(users,agents);
        new Thread(writeAgentsTask).start();
        new Thread(writeUsersTask).start();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Покупка совершена");
        if(alert.showAndWait().get()== ButtonType.OK) alert.close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("User.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("PROPsystem");
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("home.png"))));
        UserController userController = loader.getController();
        userController.init(users,agents,user);
        stage.show();
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
    }
}
