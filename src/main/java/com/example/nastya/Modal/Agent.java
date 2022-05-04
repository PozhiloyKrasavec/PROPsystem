package com.example.nastya.Modal;

import java.util.ArrayList;

public class Agent extends User{
    ArrayList<Application> listOfApplication;
    public Agent(String name, String login, String password, int balance, ArrayList<Estate> listOfProperty,ArrayList<Application> listOfApplication) {
        super(name, login, password, balance, listOfProperty);
        this.listOfApplication = listOfApplication;
    }

    public ArrayList<Application> getListOfApplication() {
        return listOfApplication;
    }

    public void setListOfApplication(ArrayList<Application> listOfApplication) {
        this.listOfApplication = listOfApplication;
    }
    public void addApplication(Application application){
        listOfApplication.add(application);
    }

    @Override
    public String toString() {
        return "Имя Агента:" + name;
    }
}
