package com.example.nastya;

import com.example.nastya.Modal.Agent;
import com.example.nastya.Modal.Estate;
import com.example.nastya.Modal.User;
import com.example.nastya.Modal.tasks.WriteAgentsTask;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class NewEstateController {
    ArrayList<User> users;
    ArrayList<Agent> agents;
    Agent agent;
    @FXML
    TextField squareField;
    @FXML
    TextField roomQntField;
    @FXML
    TextField addressField;
    @FXML
    TextField priceField;
    public void init(Agent agent, ArrayList<User> users, ArrayList<Agent> agents){
        this.agents  = agents;
        this.users = users;
        this.agent = agent;
    }
    public void addEstate(ActionEvent event) throws IOException {
        Path path = Paths.get("src/estates.csv");
        Estate temp = new Estate(
                (int) (Files.lines(path).count()-1),
                Double.parseDouble(squareField.getText()),
                Integer.parseInt(roomQntField.getText()),
                addressField.getText(),
                Integer.parseInt(priceField.getText()),
                agent.getLogin()
                );
        agent.addProperty(temp);
        WriteAgentsTask writeAgentsTask = new WriteAgentsTask(agents,users);
        new Thread(writeAgentsTask).start();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Добавлена собственность");
        if (alert.showAndWait().get() == ButtonType.OK) alert.close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Agent.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("PROPsystem");
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("home.png"))));
        stage.setScene(scene);
        AgentController agentController = loader.getController();
        agentController.init(users,agents,agent);
        stage.show();
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
    }
}
