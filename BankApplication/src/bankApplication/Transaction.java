/**
 * The main of this class is to enable user to process, store and manipulate transactions made for 
 * bank account.
 */
package bankApplication;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *@author Daniils Sokolovs
 *@version 04/01/2021
 */



public class Transaction implements Comparable{
    
    private boolean transType;
    private double transAmount;
    private Date transDateTime;
    
    //Creating auto date and changing it is format
    SimpleDateFormat newDateFormatAcc = new SimpleDateFormat("hh:mm:ss a dd/MM/yyyy");

    /**
     * Constructor for object of Transactions class
     * @param thetransType
     * @param theTransAmount
     */
    
    public Transaction(boolean thetransType, double theTransAmount){
        
        transAmount = theTransAmount;
        transType = thetransType;
        transDateTime = new Date();
    }
    
    public double getTransAmount(){
        
        return transAmount;
    }
    
    public Date getTransDateTime(){
        
        return transDateTime;
    }
    
    public boolean getTransType(){
        
        return transType; 
    }
   
    /**
     * toString method to display Transaction class object in particular layout
     * @return 
     */
    @Override
    public String toString(){
        
        return "Transaction: " + newDateFormatAcc.format(getTransDateTime()) + " " + getTransAmount() + " £" + " " + (getTransType() == true ? " deposit" : "withdrawal");

    }
    
    /**
     * Method to sort Transaction class objects by transaction amount
     * @param anotherTransaction
     * @return 
     */
    
    @Override
    public int compareTo(Object anotherTransaction){
    
        Transaction other = (Transaction) anotherTransaction;
        
        if(getTransAmount() > other.getTransAmount()){
            
            return 1;
        }
        else if (getTransAmount() < other.getTransAmount()){
            
            return -1;
        }    
        else
            return 0;
    }
     
}//end Transaction clas
