/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXControllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Vithushan Jeyendiran
 */
public class owenerStartScreenController implements Initializable {

      @FXML
    private Label startScreenStatus;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnOwnerBooks(ActionEvent event) throws Exception {
        startScreenStatus.setText("books");
        
           Stage ownerBooks = new Stage();
           FXMLLoader ownerLoader = new FXMLLoader(getClass().getResource("/ownerScreens/ownerBooks.fxml"));
           Parent ownerBookRoot = ownerLoader.load();
           Scene ownerBook = new Scene(ownerBookRoot);
           ownerBooks.setTitle("Bookstore App (Owner Books)");
           ownerBooks.setScene(ownerBook);
          
           //adds the function to change window to new screen.
           Stage Current = (Stage) ((Node) event.getSource()).getScene().getWindow();
           ownerBooks.setScene(ownerBook);
           
           Current.setScene(ownerBook);
           Current.setTitle("Bookstore App (Owner Books Screen)");
           Current.show();
    }

    @FXML
    private void btnOwnerCustomers(ActionEvent event) throws Exception {
        startScreenStatus.setText("Customers");
           Stage ownerCustomerStage = new Stage();
           FXMLLoader ownerCustomerLoader = new FXMLLoader(getClass().getResource("/ownerScreens/ownerCustomerScreen.fxml"));
           Parent ownerCustomerRoot = ownerCustomerLoader.load();
           Scene ownerCustomerScene = new Scene(ownerCustomerRoot);
           ownerCustomerStage.setTitle("Bookstore App (Owner Books)");
           ownerCustomerStage.setScene(ownerCustomerScene);
          
           //adds the function to change window to new screen.
           Stage Current = (Stage) ((Node) event.getSource()).getScene().getWindow();
           ownerCustomerStage.setScene(ownerCustomerScene);
           
           Current.setScene(ownerCustomerScene);
           Current.setTitle("Bookstore App (Owner Customer Screen)");
           Current.show();
    }

    @FXML
    private void btnOwnerLogout(ActionEvent event) throws Exception {
        startScreenStatus.setText("Logging out");
         Stage ownerLogoutStage = new Stage();
           FXMLLoader ownerLogoutLoader = new FXMLLoader(getClass().getResource("/loginscreen/loginScreen.fxml"));
           Parent ownerLogoutRoot = ownerLogoutLoader.load();
           Scene ownerLogout  = new Scene(ownerLogoutRoot );
           ownerLogoutStage.setTitle("Bookstore App (Login)");
           ownerLogoutStage.setScene(ownerLogout);
           
           
           //adds the function to change window to new screen.
           Stage Current = (Stage) ((Node) event.getSource()).getScene().getWindow();
           ownerLogoutStage.setScene(ownerLogout);
           
           Current.setScene(ownerLogout);
           Current.setTitle("Bookstore App (Login Screen)");
           Current.show();;
    }
    
   
}
