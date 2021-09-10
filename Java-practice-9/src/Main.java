import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
     books.DisplayAuthors.main(args);
    books.JdbcRowSetTest.main(args);
   books.DisplayQueryResults.main(args);
    addressbook.AddressBookDisplay.main(args);



    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        stage.setTitle("Address Book");
        stage.setScene(new Scene(root, 600, 275));
        stage.show();
    }
}
