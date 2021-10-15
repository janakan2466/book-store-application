package BookAppMain;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



/**
 *
 * @author Vithushan Jeyendiran
 */
public class FXMain extends Application {
   
    // Creates the Login screen window
    /**
     * @param args the command line arguments
     */
    @Override
    public void start(Stage stage) throws Exception 
    {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/loginscreen/loginScreen.fxml"));  
      //loader.setController(this);
      
      
      Parent root = loader.load();//FXMLLoader.load(getClass().getResource("/loginscreen/FXML.fmxl"));  ;
      Scene loginScreen = new Scene(root);
      stage.setTitle("Login Screen");
      stage.setScene(loginScreen);
      stage.show();
      
    }

   
    public static void main(String[] args) {
       
        
      // if( (Books.isEmpty()) == true) 
       //{
           // if File does not exits creates ones and writes the colomn title
           Books b = Books.getInstance();
          // b.write("BOOKS  ,", "PRICE" );
           
       //}
        
       launch(args);
        
        
        
      //  if(((Books.getInstance()).readLine()) == 0)
        

    }
    
}
