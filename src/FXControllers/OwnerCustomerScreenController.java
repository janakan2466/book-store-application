/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXControllers;

import BookAppMain.Customers;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author Vithushan Jeyendiran
 */
public class OwnerCustomerScreenController implements Initializable {

    @FXML
    private TableColumn<Customers, String> clmnUsername;
    @FXML
    private TableColumn<Customers, String> clmnPassword;
    @FXML
    private TableColumn<Customers, String> clmnPoints;
    @FXML
    private TextField txtPassword;
    @FXML
    private TextField txtUsername;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnBack;
    @FXML
    private TableView<Customers> ownerCustomersTable;
    @FXML
    private Label satlabc;
    
    public void filec() throws IOException
    {
        Customers c = Customers.getInstance();
        String filename = c.filename();
         
        if (filename.length() == 0) { satlabc.setText("file empty"); } 

        else {
            try {
                // gets the column name for the table and stores them
                BufferedReader brc = new BufferedReader(new FileReader(new File(filename)));
                //String FirstLine = br.readLine().trim();
                //String[] columnsName = FirstLine.split(",");
                String line;
                String[] array;
                String a1;
                
                while ((line = brc.readLine()) != null){
                array = line.split("/");
                ownerCustomersTable.getItems().add(new Customers(array[0], array[1], array[2]));
                }

                brc.close();
                 
       
            } catch (FileNotFoundException ex) {
                Logger.getLogger(OwnerCustomerScreenController.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
    
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       clmnUsername.setCellValueFactory(new PropertyValueFactory<Customers, String>("Name"));
       clmnPassword.setCellValueFactory(new PropertyValueFactory<Customers, String>("Password"));
       clmnPoints.setCellValueFactory(new PropertyValueFactory<Customers, String>("Points"));
        
        try {
            filec();
        } catch (IOException ex) {
            Logger.getLogger(OwnerCustomerScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
      //ownerCustomersTable.getColumns().addAll(clmnUsername,clmnPassword,clmnPoints);
    }
    

    @FXML
    private void txtPassword(ActionEvent event) {
    }

    @FXML
    private void txtUsername(ActionEvent event) {
    }

    @FXML
    private void btnDelete(ActionEvent event) {
       
       ObservableList<Customers> allCustomers,singleCustomer;
       allCustomers = ownerCustomersTable.getItems();
       singleCustomer=ownerCustomersTable.getSelectionModel().getSelectedItems();
       Customers.removeCustomer(ownerCustomersTable.getSelectionModel().getSelectedItem());
       singleCustomer.forEach(allCustomers::remove);
      
    }

    @FXML
    private void btnAdd(ActionEvent event) {
        String inpUsername = this.txtUsername.getText();
        String inpPassword = this.txtPassword.getText();
        String inpPoints = "0";
        Customers c = Customers.getInstance();
        c.write(inpUsername,inpPassword,inpPoints);
        
        ownerCustomersTable.getItems().add(new Customers(inpUsername,inpPassword,inpPoints));
        
        this.txtPassword.clear();
        this.txtUsername.clear();
    }

    @FXML
    private void btnBack(ActionEvent event) throws Exception {
           Stage ownerCustomerBackStage = new Stage();
           FXMLLoader ownerBackLoader = new FXMLLoader(getClass().getResource("/ownerScreens/ownerStartScreen.fxml"));
           Parent ownerCustomerBackRoot = ownerBackLoader.load();
           Scene ownerCustomerBackScene = new Scene(ownerCustomerBackRoot);
           ownerCustomerBackStage.setTitle("Bookstore App (Owner Books)");
           ownerCustomerBackStage.setScene(ownerCustomerBackScene);
          
           //adds the function to change window to new screen.
           Stage Current = (Stage) ((Node) event.getSource()).getScene().getWindow();
            ownerCustomerBackStage.setScene(ownerCustomerBackScene);
           
           Current.setScene(ownerCustomerBackScene);
           Current.setTitle("Bookstore App (Owner Login Screen)");
           Current.show();
    }
    
}
