/**
 * The main aim of this class is to enable system to produce same functionality 
 * by using custom methods of Linked List data structure. This class is not importing
 * any methods / classes from Java Collection FrameWork.
 */
package bankApplication;


/**
 * @author Daniils Sokolovs
 * @version 04/01/2021
 */
public class MyBank implements BankInterface{
    
    //Initialising first Node "head" value
    private AccountNode head;
    
    public MyBank(){
        
        this.head = null;
    }
    
    
     /**
     * Creating a class for Node object
     */
    public class AccountNode{
        
        protected Account data; // sored data type. In our case Account class object
        protected AccountNode next;//next node
        
        /**
         * Constructor of the AccountNode class
         * @param data
         * @param next 
         */
        protected AccountNode(Account data, AccountNode next){
            
            this.data = data;
            this.next = next;

        }
    }
    
    /**
     * Add new Node to the custom LinkedList
     * @param aAccount 
     */
    @Override
    public void addAccount(Account aAccount){
        
        AccountNode current;
        
        //Creating new Node
        AccountNode newest = new AccountNode(aAccount, null);
        
        //if first Node is empty assign created Node as a first Node
        if (this.head == null){
            newest.next = null;
            this.head = newest; 
        }
        
        //if first Node is not empty adding new Node at the back
        else{
            
            for(current = this.head; current.next != null; current = current.next){
                newest.next = null;
            }
            
            current.next = newest;
        }
        
        
    }//end addAccount() method
    

    /**
     * Search for the particular Node in a storage. Linear search is in use
     * @param AccountNo
     * @return 
     */
    public AccountNode searchNode(int AccountNo){
        
        AccountNode current;//new Node
        
        //if head is not empty process search
        if(this.head != null){
            current = this.head;
            while(true){
                //if searched account is equal to the stored one - search completed
                if(AccountNo == current.data.getAccountNo()){
                    
                    return current;
                }
                
                //if nexr Node is empty end search
                else {
                    if(current.next==null){
                        break;
                    }
                    //else checking for other Node
                    else{
                        current=current.next;
                    }
                }
            }
        }
        
        return null;
        
    }//end searchNode() method
    
    
    /**
     * Search for the particular Account in a storage.
     * @param AccountNo
     * @return 
     */
    @Override
    public Account search(int AccountNo){
        
        //Applying to the searchNode() method
        AccountNode found = searchNode(AccountNo);
        
        if(found!=null){
            return found.data;
        }
        else{
            return null;
        }
         
    }//end search() method
    
    
    /**
     * Removing particular account from the storage
     * @param AccountNo
     * @return 
     */
    @Override
    public int removeAccount(int AccountNo){
        
        //Searching for the particular Node in a LinkedList
        AccountNode deleteNode = searchNode(AccountNo);
        
        //If nothing located return 0 and end method
        if(deleteNode == null){
            
            return 0;
        }
        else{
            
            //if Node which is needs to be deleted is equal next Node
            AccountNode succ = deleteNode.next;
            if(deleteNode == head){
                head = succ;
            }
            
            else{
                
                //if Node which is needs to be deleted is equal to head
                AccountNode pred = head;
                while(pred.next!=deleteNode){
                    
                    pred = pred.next;
                    
                }
                pred.next = succ;
            }
            return 1;
            
        }
        
        
    }//end removeAccount() method
    
    
    /**
     * Sorting Linked List in ascending order
     * @param outString
     * @return 
     */
    
    public String sortList(String outString) {  
        
        String secString = "";
        AccountNode current = head, 
        index = null;
        
        Account tempVal;  
          
        if(head == null) {  
            return outString;  
        }  
        else {  
            while(current != null) {  
                
                index = current.next;  
                  
                while(index != null) { 
                    
                    //Swap data if current data is greater than index data 
                    if(current.data.getAccountNo()> index.data.getAccountNo()) {  
                        tempVal = current.data;  
                        current.data = index.data;  
                        index.data = tempVal;  
                    }
                    
                    index = index.next; 
                    
                }
                
                //Assigning processed objects to the string variable and incrementing it by 1
                secString += current.data.toString();
                current = current.next;
                
                
            }
            
            
        }
        
        return secString;
    }//end sortList() method
    
    
    /**
     * Display stored elements in a Linked List
     * @return 
     */
    @Override
    public String listAccounts(){
        
        String outString = "";
        String secString = "";
        AccountNode current;

        //Loop through the list until reaching the "null" linked value from the Node (last element)
        for(current = this.head; current.next != null; current = current.next){
  
                secString += current.data.toString();
                    
        }
        
        //Displaying all retrvied Nodes from the for loop and last Node (with linked value "null")
        outString = secString + current.data.toString();
        
        //Applying to the sort method created above to get display in ascending order
        return sortList(outString);
        
    }//end listAccounts() method
    
     
}//end MyBank class
