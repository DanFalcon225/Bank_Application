/**
 * The main of this class is to enable user to process, store and manipulate accounts stored in
 * bank
 */
package bankApplication;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;


/**
 * @author Daniils Sokolovs
 * @version 04/01/2021
 */

public class Account {
    
    private int AccountNo;
    private String Name;
    private String Address;
    private Date openDate;
    private double AccBalance;
    private Queue <Transaction> TransList;
    
    //Creating auto date and changing it is format
    SimpleDateFormat newDateFormat = new SimpleDateFormat("hh:mm:ss a dd/MM/yyyy");
   

    /**
     * Constructor of Account class
     * @param theAccountNo
     * @param theName
     * @param theAddress 
     */
    public Account(int theAccountNo, String theName, String theAddress){
        
        AccountNo = theAccountNo;
        openDate = new Date();
        AccBalance = 0.0;//initial account balance
        TransList = new LinkedList <>();
        Name = theName;
        Address = theAddress;
        
    }
    
    public int getAccountNo(){
        
        return AccountNo;
    }
    
    public String getName(){
        
        return Name;
    }
    
    
    public String getAddress(){
        
        return Address;
    }
    
    
    public Date getDate(){
        
        return openDate;
    }
    
    
    public double getBalance(){
        
        return AccBalance;
    }
    

    /**
     * Processing new transaction
     * @param newTransation
     */
    public void addTransaction(Transaction newTransation){
        
        //For deposit
        if (newTransation.getTransType() == true){
             
            AccBalance = AccBalance + newTransation.getTransAmount();

        } 
        
        //For withdrawal
        else if (newTransation.getTransType() == false) {
            
            AccBalance = AccBalance - newTransation.getTransAmount();
            
            }

        TransList.add(newTransation);
    }
    
    /**
     * Sort transaction list by transaction amount
     * @return 
     */
    public ArrayList<Transaction> SortedTransactions(){
        
        ArrayList listSorted = getAccountTransaction();

        Collections.sort(listSorted);//Applying to compareTo() method from Transaction class
 
        return listSorted;
    }
   
    /**
     * Display of transactions
     * @return 
     */
    public ArrayList<Transaction> getAccountTransaction(){
          
        ArrayList newList = new ArrayList(TransList);//convert queue to arraylist
        
        Collections.reverse(newList);//revers arraylist
        
        //Loop to instantly remove last element in arraylist size which exceed 6 elements
        while(newList.size() > 6){
            int index = newList.size() - 1;
            newList.remove(index);
        }
 
        return newList;
        
    }
    
    
    /**
     * toString method to display Account class object in particular layout
     * @return 
     */
    @Override
    public String toString(){
        
        return "Account Number: " + AccountNo + "\n" + "Account holder name: " + Name + "\n" + "Account holder address: " + Address + "\n" + 
       "Date of registering account: " + newDateFormat.format(getDate())  + "\n" + "Account Balance: " + AccBalance + " £" + "\n" + "\n";
    }
    

}//end Account class