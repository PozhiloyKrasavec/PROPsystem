package com.example.nastya.Modal;

import java.util.ArrayList;

public class User {
    String name;
    String login;
    String password;
    int balance;
    ArrayList<Estate> listOfProperty;

    public User(String name, String login, String password, int balance, ArrayList<Estate> listOfProperty) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.balance = balance;
        this.listOfProperty = listOfProperty;
    }
    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public int getBalance() {
        return balance;
    }

    public ArrayList<Estate> getListOfProperty() {
        return listOfProperty;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setListOfProperty(ArrayList<Estate> listOfProperty) {
        this.listOfProperty = listOfProperty;
    }
    public void addProperty(Estate estate){
        listOfProperty.add(estate);
    }
}
