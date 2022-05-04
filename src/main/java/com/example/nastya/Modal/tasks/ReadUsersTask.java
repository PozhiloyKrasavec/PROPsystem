package com.example.nastya.Modal.tasks;

import com.example.nastya.Modal.Estate;
import com.example.nastya.Modal.User;
import javafx.concurrent.Task;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class ReadUsersTask extends Task<ArrayList<User>> {
    String path;
    String pathToEstates;

    public ReadUsersTask(String path, String pathToEstates) {
        this.path = path;
        this.pathToEstates = pathToEstates;
    }

    @Override
    protected ArrayList<User> call() throws Exception {
        ArrayList<User> users_temp = new ArrayList<>();
        ArrayList<Estate> estates_temp = new ArrayList<>();
        String lineUser = "";
        String lineEstate = "";
        String separator = ",";
        BufferedReader brUsers = new BufferedReader(new FileReader(path));
        BufferedReader brEstates = new BufferedReader(new FileReader(pathToEstates));
        int i = 0;
        while ((lineEstate = brEstates.readLine()) != null) {
            String[] estate = lineEstate.split(separator);
            estates_temp.add(
                    new Estate(i, Double.parseDouble(estate[1]),
                            Integer.parseInt(estate[2]),
                            estate[3],
                            Integer.parseInt(estate[4]),
                            estate[0]));
            i++;
        }
        i = 0;
        while ((lineUser = brUsers.readLine()) != null) {
            String[] user = lineUser.split(separator);
            users_temp.add(new User(user[0], user[1], user[2], Integer.parseInt(user[3]), new ArrayList<Estate>()));
            for (Estate estate : estates_temp) {
                if (estate.getOwnerName().equals(users_temp.get(i).getLogin())) {
                    users_temp.get(i).addProperty(estate);
                }
            }
            i++;
        }
        return users_temp;
    }
}
