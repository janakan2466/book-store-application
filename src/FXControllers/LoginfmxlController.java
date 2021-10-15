/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXControllers;

import BookAppMain.Customers;
import static BookAppMain.Customers.currentCustomer;
import static BookAppMain.Customers.getCurrentCustomer;
import static BookAppMain.Customers.setCurrentUser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Vithushan Jeyendiran
 */

public class LoginfmxlController implements Initializable {

    
    private static Scanner x;
    
    @FXML
    private PasswordField password;
    @FXML
    private TextField username;
    @FXML
    private Label labelStat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            fileCheck();
        } catch (IOException ex) {
            Logger.getLogger(LoginfmxlController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    
    
     public boolean fileCheck() throws IOException
    {
        
        Customers c = Customers.getInstance();
        String filename = c.filename();
        try{
            currentCustomer();
        }catch (NullPointerException e){
            System.out.println("The Customer is invalid\n");
        }
         boolean valid = false;
         String user = "";
         String passWord = "" ;
         String points = "";
         
         
         
             x = new Scanner(new File(filename));
             x.useDelimiter("[/\n]");
             
             while(x.hasNext() && !valid)
             {
                 user = x.next();
                 passWord = x.next();
                 points = x.next();
                 
                  if(user.trim().equals(username.getText().trim()) && passWord.trim().equals(password.getText().trim()))
                  {
                      valid = true;
                      
                      //Customers customer = new Customers(user, passWord, points);
                      Customers.storeCustomer(user.trim(),passWord.trim(),points.trim());
                      
                      return true;
                      
                  }
                  

             } 
             x.close();
             return false;
             
         
          
    }
    
    
    
    
    
    @FXML
    private void btnLogin(ActionEvent event) throws Exception{
        String Pass = this.password.getText();
        String user = this.username.getText();
        Stage stage;
        
        if(Pass.contains("admin") && user.contains("admin"))
        {
           labelStat.setText("Login Credentials are Correct");
           Stage ownerStage = new Stage();
           FXMLLoader ownerLoader = new FXMLLoader(getClass().getResource("/ownerScreens/ownerStartScreen.fxml"));
           Parent ownerRoot = ownerLoader.load();
           Scene ownerStartScreen = new Scene(ownerRoot);
           ownerStage.setTitle("Bookstore App (Owner)");
           
           
           //adds the function to change window to new screen.
           Stage Current = (Stage) ((Node) event.getSource()).getScene().getWindow();
           ownerStage.setScene(ownerStartScreen);
           
           Current.setScene(ownerStartScreen);
           Current.setTitle("Bookstore App (Owner)");
           Current.show();
           
           
        }
        
        
        
    
        else if( fileCheck() == true)
        {
           
           setCurrentUser(user);
           getCurrentCustomer();
           System.out.println("logged in");
           labelStat.setText("Login Credentials are Correct");
           Stage customerStage = new Stage();
           FXMLLoader customerLoader = new FXMLLoader(getClass().getResource("/customerScreens/customerLoginScreen.fxml"));
           Parent customerRoot = customerLoader.load();
           Scene customerLoginScreen = new Scene(customerRoot);
           customerStage.setTitle("Bookstore App (customer)");
           customerStage.setScene(customerLoginScreen);
           
           
           //adds the function to change window to new screen.
           Stage Current = (Stage) ((Node) event.getSource()).getScene().getWindow();
           customerStage.setScene(customerLoginScreen);
           
           Current.setScene(customerLoginScreen);
           Current.setTitle("Bookstore App (customer)");
           Current.show();
           
        }
      
         
        
        
        /*
        else if(Pass.contains("customer1") && user.contains("customer1"))
        {
           //******IMPORTANT
            // Customers.storeCustomer
            //^stores the customer static call only once here 
            // whenever you buy or do anything with this customer refer to retrive customer
            // find the customer in txt file and change the values
            
           String customerName = user;
           labelStat.setText("Login Credentials are Correct");
           Stage customerStage = new Stage();
           FXMLLoader customerLoader = new FXMLLoader(getClass().getResource("/customerScreens/customerLoginScreen.fxml"));
           Parent customerRoot = customerLoader.load();
           Scene customerLoginScreen = new Scene(customerRoot);
           customerStage.setTitle("Bookstore App (customer)");
           customerStage.setScene(customerLoginScreen);
           
           
           //adds the function to change window to new screen.
           Stage Current = (Stage) ((Node) event.getSource()).getScene().getWindow();
           customerStage.setScene(customerLoginScreen);
           
           Current.setScene(customerLoginScreen);
           Current.setTitle("Bookstore App (customer)");
           Current.show();
           
       */    
           
           
            
     else
        {
            labelStat.setText("Login Credentials are Wrong");
        }
    }

    @FXML
    private void passwordField(ActionEvent event) {
    }

    @FXML
    private void userName(ActionEvent event) {
    }
    
}
