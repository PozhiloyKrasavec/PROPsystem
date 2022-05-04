package com.example.nastya.Modal.tasks;

import com.example.nastya.Modal.Agent;
import com.example.nastya.Modal.Application;
import com.example.nastya.Modal.Estate;
import javafx.concurrent.Task;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class ReadAgentsTask extends Task<ArrayList<Agent>> {
    String path;
    String pathToEstates;
    String pathToApplication;

    public ReadAgentsTask(String path, String pathToEstates, String pathToApplication) {
        this.path = path;
        this.pathToEstates = pathToEstates;
        this.pathToApplication = pathToApplication;
    }

    @Override
    protected ArrayList<Agent> call() throws Exception {
        ArrayList<Agent> agents_temp = new ArrayList<>();
        ArrayList<Estate> estates_temp = new ArrayList<>();
        ArrayList<Application> applications_temp = new ArrayList<>();
        String lineUser = "";
        String lineEstate = "";
        String lineApplication = "";
        String separator = ",";
        BufferedReader brAgent = new BufferedReader(new FileReader(path));
        BufferedReader brEstates = new BufferedReader(new FileReader(pathToEstates));
        BufferedReader brApplication = new BufferedReader(new FileReader(pathToApplication));
        int i = 0;
        while ((lineApplication = brApplication.readLine()) != null) {
            String[] application = lineApplication.split(separator);
            applications_temp.add(
                    new Application(application[0],application[1],Integer.parseInt(application[2])));
            i++;
        }
        i=0;
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
        while ((lineUser = brAgent.readLine()) != null) {
            String[] agent = lineUser.split(separator);
            agents_temp.add(new Agent(agent[0],
                    agent[1],
                    agent[2],
                    Integer.parseInt(agent[3]),
                    new ArrayList<Estate>(),
                    new ArrayList<Application>()));
            for (Estate estate : estates_temp) {
                if (estate.getOwnerName().equals(agents_temp.get(i).getLogin())) {
                    agents_temp.get(i).addProperty(estate);
                }
            }
            for (Application application : applications_temp) {
                if (application.getAgentName().equals(agents_temp.get(i).getLogin())) {
                    agents_temp.get(i).addApplication(application);
                }
            }
            i++;
        }
        return agents_temp;
    }
}
