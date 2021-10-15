/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BookAppMain;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javafx.scene.control.CheckBox;
import java.util.ArrayList;

/**
 *
 * @author Vithushan Jeyendiran
 */
public class Books {


    
    String price;
    String bookName;
    private CheckBox select;

    public Books(String bookName, String price) {
        this.price = price;
        this.bookName = bookName;
         this.select = new CheckBox();
    }

    
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookName() {
        return bookName;
    }
    
    public CheckBox getSelect()
    {
        return select;
    }
    
    public void setSelect(CheckBox c)
    {
        this.select = c;
    }    
    
    
    

    
 private static Books bInstance = null;
 private static double cost;
 private static ArrayList<Books> queue= new ArrayList<Books>();
 
 
  // Name of the associated file
 private String filename;
 private Books(String txt) 
 {
   filename = txt; 
 }

 public static boolean isEmpty()
 {
     if(bInstance == null)
     {   
        return true;
     }
     else 
     {
         return false;
     }
    
 }        
         
 public static Books getInstance()
{
   
    if( bInstance == null )
   {
     bInstance = new Books("Books.txt");
    
    }   
  
return bInstance;
}
 
 
  public void write(String colun1, String column2) {
 try {

 FileWriter fw =  new FileWriter(filename,true);
 
 fw.write(String.format("%s / $%s \r\n", colun1, column2));
 fw.close();
 

 } catch (IOException e) {
 System.out.println("An error occurred.");
 e.printStackTrace();
 }
 
 }
 
  public  String filename()
  {
      return filename;
  }
 
 
 public void read() {
 try{

 
 Scanner scan = new Scanner(new File(filename));
 
 
 /* while(scan.hasNextLine())
    {
         System.out.println(scan.nextLine());
    }  
 */   

 } catch (IOException e) {
 System.out.println("An error occurred.");
 e.printStackTrace();
 }
 }
 
public static Boolean removeBooks(Books b){
		Scanner sc = null;
		File file = null;
		FileOutputStream fileOut = null;
		try {
			// takes the text file and converts to string
			file = new File("./Books.txt");
			sc = new Scanner(file);
			String text = "";
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				// skips adding the customer to be removed to text
				if (line.contains(b.getBookName())) {
					continue;
				}
				text += line + "\n";
			}
			
			// rewrites the text while skipping the removed customer
			fileOut = new FileOutputStream("./Books.txt");
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

static Books a = new Books("","");

public static Books giveBook(){
    return a;
}
public static void storeBook(Books b){
        a = b;
}


public static void setCost(double input){
    cost= input;
}

public static double getCost(){
    return cost;
}
//Adds books to the Queue (shopping cart)
public static void addQueue(Books cart){
    queue.add(cart);
}
//Removes the books from the file
public static ArrayList<Books> getQueue(){
    return queue;
}




}


       


 

 

  
