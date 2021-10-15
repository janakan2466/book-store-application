/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BookAppMain;


/**
 *
 * @author Janakan Sivaloganathan
 */

public class Context {
    private State state;
    //private double pointsCheck;
    
    //Not sure if this constructor is needed but included in case.
    public Context(Customers a){
    //Automatically initialize the new customer with the state of silver as per instructions
        state= new Silver();
        //pointsCheck= a.points;
    }
    //Method to determine customer status after every transaction
    public void setState(double points){
        
        if(points < 0.0){
            throw new IllegalArgumentException("Points cannot be less than 0\n") ;
        }
        if(points < 1000.0){
            this.state= new Silver();
        }
        else if(points >= 1000.0){
            this.state= new Gold();
        }
    }
    //Method to return the state (not necessary but if needed can be used).
    public State getState(){
        return state;
    }
}
