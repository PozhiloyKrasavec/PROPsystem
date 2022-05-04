package com.example.nastya.Modal.tasks;

import com.example.nastya.Modal.Agent;
import com.example.nastya.Modal.Estate;
import com.example.nastya.Modal.User;
import javafx.concurrent.Task;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class WriteUsersTask extends Task {
    ArrayList<User> users;
    ArrayList<Agent> agents;

    public WriteUsersTask(ArrayList<User> users,ArrayList<Agent> agents) {
        this.users = users;
        this.agents = agents;
    }

    @Override
    protected Object call() throws Exception {
        File fileUsers = new File("src/users.csv");
        File fileEstates = new File("src/estates.csv");
        fileEstates.delete();
        fileEstates.createNewFile();
        Appendable printWriterUsers = new FileWriter(fileUsers, Charset.forName("cp1251"));
        Appendable printWriterEstates = new FileWriter(fileEstates,Charset.forName("cp1251"));
        CSVPrinter csvPrinterUsers = CSVFormat.EXCEL.print(printWriterUsers);
        CSVPrinter csvPrinterEstates = CSVFormat.EXCEL.print(printWriterEstates);
        for (User user : users){
            csvPrinterUsers.printRecord(
                    user.getName(),user.getLogin(),user.getPassword(),user.getBalance()
            );
        }
        writeEstate(csvPrinterEstates,users,agents);
        csvPrinterUsers.flush();
        csvPrinterUsers.close();
        csvPrinterEstates.flush();
        csvPrinterEstates.close();
        return null;
    }
    public void writeEstate(CSVPrinter csvPrinterEstates,ArrayList<User> users,ArrayList<Agent> agents) throws IOException {
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
