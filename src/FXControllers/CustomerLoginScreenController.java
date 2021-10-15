/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXControllers;

import BookAppMain.Books;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Vithushan Jeyendiran
 */
public class CustomerLoginScreenController implements Initializable {

    @FXML
    private Label customerLoginScreenTop;
    @FXML
    private TableView<Books> customerStartScreenTable;
    @FXML
    private Button btnCustomerBuy;
    @FXML
    private Button btnCustomerRedeemPoints;
    @FXML
    private Button btnCustomerLogOut;
    @FXML
    private Label customerLoginScreenStatus;
    @FXML
    private TableColumn<Books, String> bookName;
    @FXML
    private TableColumn<Books, String> bookPrice;
    @FXML
    private TableColumn<Books, String> selectOption;
    
    
    
    
    
    // checkbox stuff
public void checkbox()
{
         ObservableList<Books> allBooks = customerStartScreenTable.getItems();
        
         ObservableList<Books> Selected = FXCollections.observableArrayList();
         double cost= 0; 
          
           for (Books book : allBooks )
           {
               if(book.getSelect().isSelected())
               {
                   Selected.add(book);
                   Books.addQueue(book);
                   cost += Double.parseDouble(book.getPrice().trim().substring(1,book.getPrice().trim().length()));
               } 
           }
           Books.setCost(cost);
           allBooks.forEach(Selected::remove);
     
}
    

   
    
    
    
    
    /**
     * Initializes the controller class.
     */
      
    
       public void file() throws IOException
    {
        Books b = Books.getInstance();
        String filename = b.filename();
 


        try {
            // gets the column name for the table and stores them
            BufferedReader br = new BufferedReader(new FileReader(new File(filename)));
            //String FirstLine = br.readLine().trim();
            //String[] columnsName = FirstLine.split(",");
            String line;
            String[] array;
            
          while ((line = br.readLine()) != null){
            array = line.split("/");
            customerStartScreenTable.getItems().add(new Books(array[0], array[1]));
        }

        br.close();
                 
       
        } catch (FileNotFoundException ex) {
            Logger.getLogger(OwnerBooksController.class.getName()).log(Level.SEVERE, null, ex);
      ex.printStackTrace();
    }
    
  }
    
    
    
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        customerLoginScreenTop.setText("welcome " );
        
      
      bookName.setCellValueFactory(new PropertyValueFactory<Books, String>("bookName"));
      bookPrice.setCellValueFactory(new PropertyValueFactory<Books, String>("price"));
      selectOption.setCellValueFactory(new PropertyValueFactory <Books, String>("select"));
       
        try {
            file();
        } catch (IOException ex) {
            Logger.getLogger(OwnerBooksController.class.getName()).log(Level.SEVERE, null, ex);
        }
      customerStartScreenTable.getColumns().addAll(bookName,bookPrice,selectOption );
      
    }    

    @FXML
    private void btnCustomerBuy(ActionEvent event) throws Exception {
        customerLoginScreenStatus.setText("Plese select a book to purchase.");
          
        
         // checkbox stuff
            checkbox();
            
          
        
           Stage CustomerBuyStage = new Stage();
           FXMLLoader CustomerBuyLoader = new FXMLLoader(getClass().getResource("/customerScreens/customer-cost-screen.fxml"));
           Parent CustomerBuyRoot = CustomerBuyLoader.load();
           Scene CustomerBuyScreen = new Scene(CustomerBuyRoot);
           CustomerBuyStage.setTitle("Bookstore App (Customer Buy Screen)");
           
           
           //adds the function to change window to new screen.
           Stage Current = (Stage) ((Node) event.getSource()).getScene().getWindow();
           CustomerBuyStage.setScene(CustomerBuyScreen);
           
           Current.setScene(CustomerBuyScreen);
           Current.setTitle("Bookstore App (Customer Buy Screen)");
           Current.show();
        

    }

    @FXML
    private void btnCustomerRedeemPoints(ActionEvent event) throws Exception {
        customerLoginScreenStatus.setText("Plese select a book to purchase.");
        
        
            checkbox();
       
           Stage CustomerRedeemStage = new Stage();
           FXMLLoader CustomerRedeemLoader = new FXMLLoader(getClass().getResource("/customerScreens/CustomerCostScreenRedeem.fxml"));
           Parent CustomerRedeemRoot = CustomerRedeemLoader.load();
           Scene CustomerRedeemScreen = new Scene(CustomerRedeemRoot);
           CustomerRedeemStage.setTitle("Bookstore App (Customer Redeem Screen)");
           
           
           //adds the function to change window to new screen.
           Stage Current = (Stage) ((Node) event.getSource()).getScene().getWindow();
           CustomerRedeemStage.setScene(CustomerRedeemScreen);
           
           
           Current.setScene(CustomerRedeemScreen);
           Current.setTitle("Bookstore App (Customer Redeem Screen)");
           Current.show();
           
           
    }

    @FXML
    private void btnCustomerLogout(ActionEvent event) throws Exception {
        customerLoginScreenStatus.setText("Logged Out");
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
    
}
