package addressbook;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class Controller {
    private ObservableList<Address> addresses;

    @FXML
    public TableView tblAddresses;
    @FXML
    public TextField txtFirstName;
    @FXML
    public TextField txtLastName;
    @FXML
    public TextField txtEmail;
    @FXML
    public TextField txtPhoneNumber;

    @FXML
    public void addAddress(){
        Address add = new Address(
                "",
                txtFirstName.getText(),
                txtLastName.getText(),
                txtEmail.getText(),
                txtPhoneNumber.getText()
        );
        try {
            add.setAddressID(AddressRepository.getInstance().add(add));
            this.tblAddresses.setItems(addresses);
        }
        catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }
    @FXML
    public void deleteAddress(){

        try {
            Address add = (Address)tblAddresses.getSelectionModel().getSelectedItem();
            AddressRepository.getInstance().delete(add.getAddressID());
            this.addresses.remove(add);

        }
        catch(SQLException throwables){
            throwables.printStackTrace();
        }
    }

    public void initialize() {
        try {
            this.addresses = AddressRepository.getInstance().getAll();
            this.tblAddresses.setItems(addresses);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
