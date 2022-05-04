package com.example.nastya;

import com.example.nastya.Modal.Agent;
import com.example.nastya.Modal.User;
import com.example.nastya.Modal.tasks.ReadAgentsTask;
import com.example.nastya.Modal.tasks.ReadUsersTask;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

public class HelloController implements Initializable {
    ArrayList<User> users;
    ArrayList<Agent> agents;
    String pahtToUsers = "src/users.csv";
    String pathToAgents = "src/agents.csv";
    String pathToEstates = "src/estates.csv";
    String pathToApplications = "src/applications.csv";
    @FXML
    PasswordField passwordField;
    @FXML
    TextField loginField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ReadAgentsTask readAgentsTask = new ReadAgentsTask(pathToAgents,pathToEstates,pathToApplications);
        ReadUsersTask readUsersTask = new ReadUsersTask(pahtToUsers,pathToEstates);
        new Thread(readAgentsTask).start();
        new Thread(readUsersTask).start();
        try {
            users = readUsersTask.get();
            agents = readAgentsTask.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
    public void loginBtnOn(ActionEvent event) throws IOException {
        String insertPassword = passwordField.getText();
        String insertLogin = loginField.getText();
        for (User savedUser:users){
            if (savedUser.getLogin().equals(insertLogin)){
                if (savedUser.getPassword().equals(insertPassword)){
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("User.fxml"));
                    Parent root = loader.load();
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    stage.setTitle("PROPsystem");
                    stage.getIcons().add(new Image(String.valueOf(getClass().getResource("home.png"))));
                    stage.setScene(scene);
                    UserController userController = loader.getController();
                    userController.init(users,agents,savedUser);
                    stage.show();
                    ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Ошибка");
                    alert.setHeaderText("Введен неверный пароль");
                    if (alert.showAndWait().get() == ButtonType.OK) alert.close();
                }
            }
        }
        for (Agent savedAgent:agents){
            if (savedAgent.getLogin().equals(insertLogin)){
                if (savedAgent.getPassword().equals(insertPassword)){
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Agent.fxml"));
                    Parent root = loader.load();
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    stage.setTitle("PROPsystem");
                    stage.getIcons().add(new Image(String.valueOf(getClass().getResource("home.png"))));
                    stage.setScene(scene);
                    AgentController agentController = loader.getController();
                    agentController.init(users,agents,savedAgent);
                    stage.show();
                    ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Ошибка");
                    alert.setHeaderText("Введен неверный пароль");
                    if (alert.showAndWait().get() == ButtonType.OK) alert.close();
                }
            }
        }
    }
    public void regBtnOn(ActionEvent event) throws IOException {
       FXMLLoader loader = new FXMLLoader(getClass().getResource("registration.fxml"));
       Parent root = loader.load();
       Stage stage = new Stage();
       Scene scene = new Scene(root);
        stage.setTitle("PROPsystem");
       stage.getIcons().add(new Image(String.valueOf(getClass().getResource("home.png"))));
       stage.setScene(scene);
       RegistrationController registrationController = loader.getController();
       registrationController.init(users,agents);
       stage.show();
       ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
    }
}