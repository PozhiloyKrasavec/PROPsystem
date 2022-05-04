package com.example.nastya.Modal.tasks;

import com.example.nastya.Modal.Agent;
import com.example.nastya.Modal.Application;
import com.example.nastya.Modal.Estate;
import com.example.nastya.Modal.User;
import javafx.concurrent.Task;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class WriteAgentsTask extends Task {
    ArrayList<User> users;
    ArrayList<Agent> agents;

    public WriteAgentsTask(ArrayList<Agent> agents,ArrayList<User> users) {
        this.agents = agents;
        this.users = users;
    }

    @Override
    protected Object call() throws Exception {
        File fileAgents = new File("src/agents.csv");
        File fileEstates = new File("src/estates.csv");
        File fileApplications = new File("src/applications.csv");
        fileEstates.delete();
        fileEstates.createNewFile();
        fileApplications.delete();
        fileApplications.createNewFile();
        Appendable printWriterAgents = new PrintWriter(fileAgents,"cp1251");
        Appendable printWriterEstates = new FileWriter(fileEstates, Charset.forName("cp1251"));
        Appendable printWriterApplications = new FileWriter(fileApplications, Charset.forName("cp1251"));
        CSVPrinter csvPrinterAgents = CSVFormat.EXCEL.print(printWriterAgents);
        CSVPrinter csvPrinterEstates = CSVFormat.EXCEL.print(printWriterEstates);
        CSVPrinter csvPrinterApplications = CSVFormat.EXCEL.print(printWriterApplications);
        for (Agent agent:agents){
            csvPrinterAgents.printRecord(
               agent.getName(),agent.getLogin(),agent.getPassword(),agent.getBalance()
            );
            try {
                for (Application application: agent.getListOfApplication()){
                    csvPrinterApplications.printRecord(
                            application.getClientName(),
                            application.getAgentName(),
                            application.getEstateId()
                    );
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        writeEstate(csvPrinterEstates,users,agents);
        csvPrinterAgents.flush();
        csvPrinterAgents.close();
        csvPrinterApplications.flush();
        csvPrinterApplications.close();
        csvPrinterEstates.flush();
        csvPrinterEstates.close();
        return null;
    }
    public void writeEstate(CSVPrinter csvPrinterEstates, ArrayList<User> users, ArrayList<Agent> agents) throws IOException {
        for (User user:users) {
            if (user.getListOfProperty().size()!=0){
                for (Estate estate: user.getListOfProperty()){
                    csvPrinterEstates.printRecord(
                            estate.getOwnerName(),
                            estate.getSquare(),
                            estate.getRoomQnt(),
                            estate.getAddress(),
                            estate.getPrice()
                    );
                }
            }
        }
        for (Agent agent :agents) {
            if (agent.getListOfProperty().size()!=0){
                for (Estate estate: agent.getListOfProperty()){
                    csvPrinterEstates.printRecord(
                            estate.getOwnerName(),
                            estate.getSquare(),
                            estate.getRoomQnt(),
                            estate.getAddress(),
                            estate.getPrice()
                    );
                }
            }
        }
    }
}
