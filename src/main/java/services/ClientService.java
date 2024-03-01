package services;

import entity.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientService {
    private PreparedStatement createSt;
    private PreparedStatement selectMaxIdSt;
    private PreparedStatement getBuIdSt;
    private PreparedStatement setNameSt;
    private PreparedStatement deleteByIdSt;
    private PreparedStatement listAllSt;


    public ClientService(Connection connection) throws SQLException {

        createSt = connection.prepareStatement(
                "INSERT INTO client (name) VALUES (?)"
        );

        selectMaxIdSt = connection.prepareStatement(
                "SELECT max(id) AS maxId FROM client"
        );

        getBuIdSt = connection.prepareStatement(
                "SELECT name FROM client WHERE id = ?"
        );

        setNameSt = connection.prepareStatement(
                "UPDATE client SET name = ? WHERE id = ?"
        );

        deleteByIdSt = connection.prepareStatement(
                "DELETE FROM client WHERE id = ?"
        );

        listAllSt = connection.prepareStatement(
                "SELECT id, name FROM client"
        );

    }

    public long create(String name) throws SQLException {
        if (name.length() < 2 || name.length() > 1000) {
            throw new IllegalArgumentException("Enter name from 2 to 1000 chars");
        }

        createSt.setString(1, name);
        createSt.executeUpdate();

        long id;
        try(ResultSet rs = selectMaxIdSt.executeQuery()) {
            rs.next();
            id = rs.getInt("maxId");
        }
        return id;
    }

    public String getById(long id) throws SQLException {
        getBuIdSt.setLong(1, id);
        String name = null;

        try (ResultSet rs = getBuIdSt.executeQuery()) {
            rs.next();
            name = rs.getString("name");
        }
        return name;
    }

    public void setName(long id, String name) throws SQLException {

        if (name.length() < 2 || name.length() > 1000) {
            throw new IllegalArgumentException("Enter name from 2 to 1000 chars");
        }

        setNameSt.setLong(2, id);
        setNameSt.setString(1, name);

        int rowsUpdated = setNameSt.executeUpdate();

        if (rowsUpdated == 0) {
            throw new SQLException("User with id " + id + " does not exist");
        }
    }

    public void deleteById(long id) throws SQLException {
        deleteByIdSt.setLong(1, id);

        int deletedRows = deleteByIdSt.executeUpdate();

        if (deletedRows == 0) {
            throw new SQLDataException("User with id " + id + " does not exist");
        }
    }

    public List<Client> listAll() throws SQLException {
        List<Client> clients = new ArrayList<>();
        try(ResultSet rs = listAllSt.executeQuery()) {
            while(rs.next()) {
            Client client = new Client(
                    rs.getLong("id"),
                    rs.getString("name"));
            clients.add(client);
        }
            return clients;
        }
    }
}
