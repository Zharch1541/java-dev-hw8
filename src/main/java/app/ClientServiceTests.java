package app;

import db.Database;
import services.ClientService;

import java.sql.SQLException;

public class ClientServiceTests {
    public static void main(String[] args) throws SQLException {
        ClientService clientService = new ClientService(Database.getConn());

        System.out.println(clientService.listAll());

        //Create new Client
        System.out.println(clientService.create("Pavlo"));
        System.out.println(clientService.listAll());

        //Delete client
        clientService.deleteById(3);
        System.out.println(clientService.listAll());

        //Get client by Id
        System.out.println("clientService.getById(4) = " + clientService.getById(4));

        //Set name client by id
        clientService.setName(5, "Petro");
        System.out.println(clientService.listAll());



    }
}
