/**
 * The main aim of this class is to store bank accounts by using LinkedList.
 * Additionally it is enable user to manipulate the stored elements by
 * removing, retrieving them form storage.
 */

package bankApplication;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Daniils Sokolovs
 * @version 04/01/2021
 */


public class Bank implements BankInterface{
    
    
    //LinkedList to store objects from class Account
    private LinkedList<Account> allAccounts;
    
    
    //Constructor of Bank class
    public Bank(){
        
        allAccounts = new LinkedList<>();
    }
    
    //Add a new Account to the LinkedList
    @Override
    public void addAccount(Account aAccount){
        
        
        allAccounts.add(aAccount);
        
    }
    
    //Remove Account from the LinkedList
    @Override
    public int removeAccount(int AccountNo){
        
        Account account = search(AccountNo);
        
        if(account == null){
            return 0;
        }
        
        else{
            
            allAccounts.remove(account);
        
            return 1;
        }
        
    }

    /**
     * Search for account in the storage
     * @param AccountNo
     * @return 
     */

    @Override
    public Account search(int AccountNo){
        
        Account foundAcc = null;
        
        //Sorting account storage
        Comparator<Account> accountComparator = new Comparator<Account>(){
            
            @Override
            public int compare(Account acc1, Account acc2){
                int returnVal = 0;
                if (acc1.getAccountNo() < acc2.getAccountNo()){
                    returnVal = -1;
                } else if(acc1.getAccountNo() == acc2.getAccountNo()){
                    returnVal = 0;
                } else if(acc1.getAccountNo() > acc2.getAccountNo()){
                    returnVal = 1;
                }

                return returnVal;

            }
            
        };
        

        //Creating a temporary list to store value from the current LinkedList
        List<Account> listOfValues = new ArrayList<>();
        listOfValues.addAll(allAccounts);
        
        //Applying to the sort method which was created above
        listOfValues.sort(accountComparator);
        
        //Create a new object of Account class with key value as an account number
        foundAcc = new Account(AccountNo, null, null);
        
        //Applying to binary search
        int index = Collections.binarySearch(listOfValues, foundAcc, accountComparator);
        
        if (index > -1){
            foundAcc = listOfValues.get(index);
            
        } else {
            foundAcc = null;
        }
        
        return foundAcc;
    }
    
    /**
     * Displaying sorted account list
     * @return 
     */
    @Override
    public String listAccounts(){
        
        //Sorting account storage
        Comparator<Account> accountComparator = new Comparator<Account>(){
            
            @Override
            public int compare(Account acc1, Account acc2){
                int returnVal = 0;
                if (acc1.getAccountNo() < acc2.getAccountNo()){
                    returnVal = -1;
                } else if(acc1.getAccountNo() == acc2.getAccountNo()){
                    returnVal = 0;
                } else if(acc1.getAccountNo() > acc2.getAccountNo()){
                    returnVal = 1;
                }

                return returnVal;

            }
              
        };
        
        List<Account> listOfValues = new ArrayList<>();
        listOfValues.addAll(allAccounts);
        
        //Applying to the sort method which was created above
        listOfValues.sort(accountComparator);
        
        
        return listOfValues.toString().replace("[","").replace("]","").replace(", ","");
    
    }

}// end Bank class
