package addressbook;

public class Address {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNUmber;
    private String addressID;

    public Address(String addressID, String firstName, String lastName, String email, String phoneNumber){
        this.addressID = addressID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNUmber = phoneNumber;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getEmail(){
        return email;
    }

    public String getPhoneNUmber(){
        return phoneNUmber;
    }

    public String getAddressID(){
        return addressID;
    }

    public void setFirstName(){
        this.firstName = firstName;
    }

    public void setLastName(){
        this.lastName = lastName;
    }

    public void setEmail(){
        this.email = email;
    }

    public void setPhoneNUmber(){
        this.phoneNUmber = phoneNUmber;
    }

    public void setAddressID(String add){
        this.addressID = addressID;
    }
}
