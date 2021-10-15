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
public class OwnerBooksController implements Initializable {

    @FXML
    private TableColumn<Books, String> bookName;
    @FXML
    private TableColumn<Books, String> bookPrice;
    @FXML
    private Button btnOwnerAdd;
    @FXML
    private Button btnOwnerDelete;
    @FXML
    private Button btnOwnerBack;
    @FXML
    private TextField txtbookName;
    @FXML
    private TextField txtbookprice;
    @FXML
    private TableView<Books> ownerBookTable;
    private Label statlab;
    @FXML
    private Label satlab;
    
    
    public void file() throws IOException
    {
        Books b = Books.getInstance();
        String filename = b.filename();
  if (filename.length() == 0) { statlab.setText("file empty"); } 
  else {


        try {
            // gets the column name for the table and stores them
            BufferedReader br = new BufferedReader(new FileReader(new File(filename)));
            //String FirstLine = br.readLine().trim();
            //String[] columnsName = FirstLine.split(",");
            String line;
            String[] array;
            
          while ((line = br.readLine()) != null){
            array = line.split("/");
            ownerBookTable.getItems().add(new Books(array[0], array[1]));
        }

        br.close();
                 
       
        } catch (FileNotFoundException ex) {
            Logger.getLogger(OwnerBooksController.class.getName()).log(Level.SEVERE, null, ex);
      ex.printStackTrace();
    }
    
  }
    }
        
    
    
    
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
      bookName.setCellValueFactory(new PropertyValueFactory<Books, String>("bookName"));
       bookPrice.setCellValueFactory(new PropertyValueFactory<Books, String>("price"));
        try {
            file();
        } catch (IOException ex) {
            Logger.getLogger(OwnerBooksController.class.getName()).log(Level.SEVERE, null, ex);
        }
      ownerBookTable.getColumns().addAll(bookName,bookPrice );
        
    //file();
   // ownerBookTable.getColumns().addAll(bookName, bookPrice);
        
        
        

        
        
    }    

    @FXML
    private void btnOwnerAdd(ActionEvent event) {
       String inpBookName = this.txtbookName.getText();
       String inpBookprice = this.txtbookprice.getText();
       Books b = Books.getInstance();
       b.write(inpBookName, inpBookprice);
       
       ownerBookTable.getItems().add(new Books(inpBookName, "$" + inpBookprice));
       
       
       
        this.txtbookprice.clear();
        this.txtbookName.clear();
    }

    @FXML
    private void btnOwnerDelete(ActionEvent event) throws FileNotFoundException {
      ObservableList<Books> allBooks,singleBook;
       allBooks = ownerBookTable.getItems();
       singleBook=ownerBookTable.getSelectionModel().getSelectedItems();
       Books.removeBooks(ownerBookTable.getSelectionModel().getSelectedItem());
       singleBook.forEach(allBooks::remove);
      
    }

    @FXML
    private void btnOwnerBack(ActionEvent event) throws IOException {
        Stage ownerbackStage = new Stage();
           FXMLLoader ownerbackLoader = new FXMLLoader(getClass().getResource("/ownerScreens/ownerStartScreen.fxml"));
           Parent ownerbackRoot = ownerbackLoader.load();
           Scene ownerback  = new Scene(ownerbackRoot );
           ownerbackStage.setTitle("Bookstore App (Owner)");
           ownerbackStage.setScene(ownerback);
           
           
           //adds the function to change window to new screen.
           Stage Current = (Stage) ((Node) event.getSource()).getScene().getWindow();
           ownerbackStage.setScene(ownerback);
           
           Current.setScene(ownerback);
           Current.setTitle("Bookstore App (Owner Login Screen)");
           Current.show();
    }

    @FXML
    private void txtBookName(ActionEvent event) {
    }

    @FXML
    private void txtBookPrice(ActionEvent event) {
    }
    
}
