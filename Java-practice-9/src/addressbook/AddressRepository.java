package addressbook;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class AddressRepository {
    private static AddressRepository instance;

    private final String DATABASE_URL = "jdbc:derby:./db/addressbook";
    private final String GET_ALL_QUERY = "SELECT * FROM Addresses";
    private final String GET_QUERY = "SELECT * FROM Addresses WHERE AddressID=?";
    private final String ADD_QUERY = "INSERT INTO Addresses(FirstName,LastName,Email,PhoneNumber) VALUES(?,?,?,?)";
    private final String DELETE_QUERY = "DELETE FROM Addresses WHERE AddressID=?";
    private final String GET_LAST_ID = "SELECT MAX(AddressID) FROM Address";
    // PreparedStatement implementation depend on database management system, and it is interface as well
    private PreparedStatement getAllStmt;
    private PreparedStatement getStmt;
    private PreparedStatement addStmt;
    private PreparedStatement deleteStmt;
    private PreparedStatement getLastidStmt;



    // Just an interface getting connection from date-base
    private Connection con;

    AddressRepository() throws SQLException {
        this.con = DriverManager.getConnection(DATABASE_URL);
        this.getAllStmt = this.con.prepareStatement(GET_ALL_QUERY);
        this.getStmt = this.con.prepareStatement(GET_QUERY);
        this.addStmt = this.con.prepareStatement(ADD_QUERY);
        this.deleteStmt = this.con.prepareStatement(DELETE_QUERY);
        this.getLastidStmt = this.con.prepareStatement(GET_LAST_ID);
    }

    // called lazy instantiation or loading
    public static AddressRepository getInstance() throws SQLException{
        if(instance == null){
            instance=new AddressRepository();
        }
        return instance;
    }

    public Address get(String addressID) throws SQLException{
        ResultSet result = null;
        Address address = null;

        // sql column index start with 1 not from 0 as usual
        this.getStmt.setString(1, addressID);
        result = this.getStmt.executeQuery();
        if(result.next()){
            address = new Address(
                    result.getString("AddressID"),
                    result.getString("FirstName"),
                    result.getString("LastName"),
                    result.getString("Email"),
                    result.getString("PhoneNumber")
            );
        }
        return address;

    }

    public ObservableList<Address> getAll() throws SQLException {
        ResultSet result = null;
        ObservableList<Address> list = FXCollections.observableArrayList();

        result = this.getAllStmt.executeQuery();

        while(result.next()){
            list.add(new Address(
               result.getString("AddressID"),
               result.getString("FirstName"),
               result.getString("LastName"),
               result.getString("Email"),
               result.getString("PhoneNumber")
            ));
        }
        return list;

    }
    // returns the addressID of the new row
    public String add(Address address) throws SQLException{
        this.addStmt.setString(1, address.getFirstName());
        this.addStmt.setString(2, address.getLastName());
        this.addStmt.setString(3, address.getEmail());
        this.addStmt.setString(4, address.getPhoneNUmber());

        if(this.addStmt.executeUpdate() > 0){
            ResultSet lastResult = this.getLastidStmt.executeQuery();
            if(lastResult.next()){
                return lastResult.getString(1);
            }
        };

        return null;
    }


    public void delete(String addressID){

    }
}
