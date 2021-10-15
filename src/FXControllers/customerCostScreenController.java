/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXControllers;

import BookAppMain.Books;
import BookAppMain.Customers;
import static BookAppMain.Customers.getCurrentCustomer;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Vithushan Jeyendiran
 */
public class customerCostScreenController implements Initializable {

    @FXML
    private Label labelTC;
    @FXML
    private Label labelPoints;
    @FXML
    private Label labelStatus;
    @FXML
    private Button btnLogout;
    @FXML
    private Button btnBuy;
    @FXML
    private Button btnBack;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //It provides the current book costs, current state state of the user after purchase, and total amount of points 
        //That will be accumulated on top of the current customers balance. The total balance of the customer will be assumed
        //for its state prediction.
        labelTC.setText("$ "+String.format("%.2f", Books.getCost()));
        labelStatus.setText(getCurrentCustomer().cashState());
        labelPoints.setText(getCurrentCustomer().pointsAccumulated());
    }

    @FXML
    private void btnBuy(ActionEvent event) throws Exception{
        //**********************************************
        //This method evokes the state pattern as confirming a transaction and setting the points 
        //to the current customer.
        
        
           getCurrentCustomer().setPoints(getCurrentCustomer().pointsAccumulated());
        //If the file were to not load, it returns an error to stdout
           if(!(Customers.save())){
               System.out.println("Transaction Error\n");
           }
           
           for(Books searcher : Books.getQueue()){
               Books.removeBooks(searcher);
           }
           Books.getQueue().clear();
        
        Stage thankYouStage = new Stage();
           FXMLLoader thankYouLoader = new FXMLLoader(getClass().getResource("/customerScreens/CustomerThankYou.fxml"));
           Parent thankYouLogoutRoot = thankYouLoader.load();
           Scene thankYouLogout  = new Scene(thankYouLogoutRoot );
           thankYouStage.setTitle("Bookstore App (thankYou)");
           thankYouStage.setScene(thankYouLogout);

            thankYouStage.show();;
        
        
    }

    private void btnLogout(ActionEvent event) throws Exception {
          
    }

    @FXML
    private void Logout(ActionEvent event) throws Exception { 
        Stage customerLogoutStage = new Stage();
           FXMLLoader customerLogoutLoader = new FXMLLoader(getClass().getResource("/loginscreen/loginScreen.fxml"));
           Parent customerLogoutRoot = customerLogoutLoader.load();
           Scene customerLogout  = new Scene(customerLogoutRoot );
           customerLogoutStage.setTitle("Bookstore App (Login)");
           customerLogoutStage.setScene(customerLogout);
           
           
           //adds the function to change window to new screen.
           Stage Current = (Stage) ((Node) event.getSource()).getScene().getWindow();
           customerLogoutStage.setScene(customerLogout);
           
           Current.setScene(customerLogout);
           Current.setTitle("Bookstore App (Login Screen)");
           Current.show();;
        
    }

    @FXML
    private void btnBack(ActionEvent event) throws Exception {
           Stage CustomerBackStage = new Stage();
           FXMLLoader BackLoader = new FXMLLoader(getClass().getResource("/customerScreens/customerLoginScreen.fxml"));
           Parent CustomerBackRoot = BackLoader.load();
           Scene CustomerBackScene = new Scene(CustomerBackRoot);
           CustomerBackStage.setTitle("Bookstore App (Customer Screen)");
           CustomerBackStage.setScene(CustomerBackScene);
          
           //adds the function to change window to new screen.
           Stage Current = (Stage) ((Node) event.getSource()).getScene().getWindow();
            CustomerBackStage.setScene(CustomerBackScene);
           
           Current.setScene(CustomerBackScene);
           Current.setTitle("Bookstore App (Customer Screen)");
           Current.show();
    }
    
}
