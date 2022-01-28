/*
 *The main aim of this class is to provide a enable use to effectively plug in to
 *Bank or MyBank classes
 */
package bankApplication;


/**
 * @author Daniils Sokolovs
 * @version 04/01/2021
 */
public interface BankInterface {
    
     public void addAccount(Account aAccount);
     public int removeAccount(int AccountNo);
     public Account search(int AccountNo);
     public String listAccounts();

    
}//end BankInterface class
