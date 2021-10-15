/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BookAppMain;

/**
 *
 * @author Vithushan Jeyendiran
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Customers {
    String  name;
    String password;
    String points;
    State state;
    private Context instance;
        
   
   
    
    public Customers(String name, String password, String points) {
        this.name = name;
        this.password = password;
        this.points = points;
        instance= new Context(this);
        state= instance.getState();
       
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPoints(String points) {
        this.points = points;
        double checkPoints= Double.parseDouble(this.points);
        instance.setState(checkPoints);
    }

    public String getPassword() {
        return password;
    }

    public String getPoints() {
        return (this.points);
    }
    //Returns the membership as a String after checking its internal state
    public String getMembership(){
        return state.toString();
    }
    //Set the current user to a variable that can be easily checked through a file
    public static void setCurrentUser(String username){
        currentUser= username;
    }
    //returns the current customer that is loaded from the customer file
    public static Customers getCurrentCustomer(){
        return currentCust;
    }
    //Method to return the amount of points the current user has (used for transaction calculations)
    //Returns points to Dollar format
    public double pointsConverter(){
        double conversion= ((Double.parseDouble(this.points))/100);
        return conversion;
    }
    
 
    
    private static Customers cInstance = null;
    private static String currentUser= "";
    //a static declaration for any customer that logs on
    private static Customers currentCust= null;
    
    //name of the associated file
    private String filename;
    private Customers(String txt){
        filename = txt;
    }
    
    public static boolean isEmpty(){
        if(cInstance == null)
            {   
               return true;
            }
            else 
            {
                return false;
            }    
        }
    
    public static Customers getInstance(){
        if (cInstance == null){
            cInstance = new Customers("Customers.txt");
        }
        return cInstance;
    }
    
    public void write(String c1,String c2,String c3){
        try{
            FileWriter fw2 = new FileWriter(filename,true);
            fw2.write(String.format("%s/%s/%s\r\n", c1,c2,c3));
            fw2.close();
        }
        catch(IOException e){
            System.out.println("An error occoured.");
            e.printStackTrace();
        }
            
    }
    
    public void read(){
        try{
            Scanner scan = new Scanner(new File(filename));
        }
        catch(IOException e){
            System.out.println("An error has occoured.");
            e.printStackTrace();
        }
    }
    
    public  String filename() {
      return this.filename;
    }
    
    public static Boolean removeCustomer(Customers c) {
		Scanner sc = null;
		File file = null;
		FileOutputStream fileOut = null;
		try {
			// takes the text file and converts to string
			file = new File("./Customers.txt");
			sc = new Scanner(file);
			String text = "";
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				// skips adding the customer to be removed to text
				if (line.contains(c.getName())) {
					continue;
				}
				text += line + "\n";
			}
			
			// rewrites the text while skipping the removed customer
			fileOut = new FileOutputStream("./Customers.txt");
			fileOut.write(text.getBytes());
			fileOut.close();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			sc.close();
		}
	}
    
    public static Customers retriveCustomer() throws FileNotFoundException{
        
        Scanner x = new Scanner(new File("Temp.txt"));
        String user = x.nextLine();
        String passWord = x.nextLine();
        String points = x.nextLine();
        Customers c = new Customers("","","");
        
        c.setName(user);
        c.setPassword(passWord);
        c.setPoints(points);
        
        return c;
    }
    
    public static void storeCustomer(String user, String pass,String points){
        try{
            try (FileWriter fw2 = new FileWriter("Temp.txt",true)) {
                fw2.write(String.format("%s\n%s\n%s\n", user,pass, points));
            }
        }
        catch(IOException e){
            System.out.println("An error occoured.");
        }
            
    }
    
    //Retrives the current customer that is evoked by the username
    //When the username is entered, it searches through the file and retrives that customer and its values
    public static void currentCustomer(){
        File file= null;
        Scanner check= null;

        try{
            file= new File("Customers.txt");
            check= new Scanner(file);
            while(check.hasNextLine()){
                String id= check.nextLine();
                if(id.contains(currentUser)){
                        String[] parser= id.split("/");

                    currentCust= new Customers(parser[0], parser[1], parser[2]);
                    //return currentCust;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            if(check != null){
                check.close();
            }
        }
        //return null;
    }
    
    //total cost after deduction with points
    public double redeemTC(){
        double price= 0.00;
        if(Books.getCost() < getCurrentCustomer().pointsConverter()){
            //double diff= pointsConverter()-Books.totalCost();
            //this.setPoints(""+(int)diff);
            return price;
        }
        if(Books.getCost() > getCurrentCustomer().pointsConverter()){
            price= Books.getCost()- getCurrentCustomer().pointsConverter();
            //this.setPoints("0");
            return price;
        }
        else{
            //this.setPoints("0");
            return price;
        }
    }
    
    //This method presents a dummy reciept amount for the total amount of points left after a
    //reciept transaction
    public double redeemPoints(){
        double totPoints= 0.00;
        if(Books.getCost() < getCurrentCustomer().pointsConverter()){
            totPoints= (getCurrentCustomer().pointsConverter()-(Books.getCost()));
            //this.setPoints(""+(int)diff);
            System.out.println(Books.getCost());
            return totPoints*100;
        }
        else if(Books.getCost() > getCurrentCustomer().pointsConverter()){
            return totPoints*100;
        }
        
        else{
            return totPoints*100;
        }
    }
    
    //Not included in the state pattern, implemented to showcase a dummy reciept
    //This method presents the predicted state when purchasing with POINTS
    public String redeemState(){
        double totPoints= 0.00;
        if(Books.getCost() < getCurrentCustomer().pointsConverter()){
            totPoints= (getCurrentCustomer().pointsConverter()-Books.getCost());
            //this.setPoints(""+(int)diff);
            if(totPoints*100 < 1000){
                return "Silver";
            }
            else if(totPoints*100 >= 1000){
                return "Gold";
            }
        }
        else if(Books.getCost() > getCurrentCustomer().pointsConverter()){
            //totPoints= Books.totalCost()-pointsConverter();
            //this.setPoints("0");
            return "Silver";
        }
            //this.setPoints("0");
            return "Silver";
    }
    
    //This method presents the amount of points that can be accumulated with a CASH purchase of a book
    public String pointsAccumulated(){
        double pointsAccumulated= Books.getCost()*100 + Double.parseDouble(getCurrentCustomer().getPoints());
        String formatter= String.format(("%.1f"), pointsAccumulated);
        return formatter;
    }
    
    //This method overwrites the previous customer text file whenever a customer makes a transaciton
    public static boolean save(){
        Scanner sc = null;
        File file = null;
        FileOutputStream fileOut = null;
        try {
                // takes the text file and converts to string
                file = new File("./Customers.txt");
                sc = new Scanner(file);
                String text = "";
                while (sc.hasNextLine()) {
                        String line = sc.nextLine();
                        // skips adding the customer to be removed to text
                        if (line.contains(getCurrentCustomer().getName())) {
                                text += String.format("%s/%s/%s\r\n", getCurrentCustomer().getName(), 
                                        getCurrentCustomer().getPassword(), getCurrentCustomer().getPoints());
                                System.out.println(getCurrentCustomer().getPoints());
                                continue;
                        }
                        text += line + "\n";
                }

                // rewrites the text while skipping the removed customer
                fileOut = new FileOutputStream("./Customers.txt");
                fileOut.write(text.getBytes());
                fileOut.close();
                return true;

        } catch (Exception e) {
                e.printStackTrace();
                return false;
        } finally {
                sc.close();
        }
    }
    //Method to retrive a dummy state prediction for the CASH purchase reciept
    //This method does not change the customer's state (only predicts)
    //The method is evoked to present the customer balance after cash purchase
    public String cashState(){
        if(Books.getCost()*100 + getCurrentCustomer().pointsConverter()*100 < 1000){
            return "Silver";
        }
        else if(Books.getCost()*100 + getCurrentCustomer().pointsConverter()*100 >= 1000){
            return "Gold";
        }
        else 
            return "Silver";
    }
    //03/04/21 6:14 PM
}
