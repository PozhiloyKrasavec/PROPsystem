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

public class RegistrationController {
    ArrayList<User> users;
    ArrayList<Agent> agents;
    @FXML
    TextField name;
    @FXML
    TextField login;
    @FXML
    TextField password;
    @FXML
    TextField passwordCheck;
    @FXML
    ChoiceBox<String> userType;

    public void init(ArrayList<User> users, ArrayList<Agent> agents){
        this.users = users;
        this.agents = agents;
        userType.getItems().addAll(
                "Клиент",
                "Агент"
        );
    }
    public void regBtnOn(ActionEvent event) throws IOException {
        String nameTxt = name.getText();
        String loginTxt = login.getText();
        String passwordTxt = password.getText();
        String passwordChkTxt = passwordCheck.getText();
        if (userType.getValue().equals("Клиент") && passwordTxt.equals(passwordChkTxt)){
            users.add(new User(nameTxt,loginTxt,passwordTxt,100000,new ArrayList<Estate>()));
            WriteUsersTask writeUsersTask = new WriteUsersTask(users,agents);
            new Thread(writeUsersTask).start();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Пользователь зарегистрирован");
            if(alert.showAndWait().get()== ButtonType.OK) alert.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("User.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("PROPsystem");
            stage.getIcons().add(new Image(String.valueOf(getClass().getResource("home.png"))));
            UserController userController = loader.getController();
            userController.init(users,agents,users.get(users.size()-1));
            stage.show();
            ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
        }else if (userType.getValue().equals("Агент")&& passwordTxt.equals(passwordChkTxt)){
            agents.add(new Agent(nameTxt,loginTxt,passwordTxt,100000,new ArrayList<Estate>(),new ArrayList<Application>()));
            WriteAgentsTask writeAgentsTask = new WriteAgentsTask(agents,users);
            new Thread(writeAgentsTask).start();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Агент зарегистрирован");
            if(alert.showAndWait().get()== ButtonType.OK) alert.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Agent.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("PROPsystem");
            stage.getIcons().add(new Image(String.valueOf(getClass().getResource("home.png"))));
            stage.setScene(scene);
            AgentController agentController = loader.getController();
            agentController.init(users,agents,agents.get(agents.size()-1));
            stage.show();
            ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
        }
    }
}
