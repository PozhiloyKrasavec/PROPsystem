package com.example.nastya.Modal;

import java.util.Date;

public class Application {
    String clientName;
    String agentName;
    int estateId;

    public Application(String clientName, String agentName, int estateId) {
        this.clientName = clientName;
        this.agentName = agentName;
        this.estateId = estateId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public int getEstateId() {
        return estateId;
    }

    public void setEstateId(int estateId) {
        this.estateId = estateId;
    }

    @Override
    public String toString() {
        return "Клиент: " + clientName + " Агент: " + agentName + " ID собственности: " + estateId;
    }
}
