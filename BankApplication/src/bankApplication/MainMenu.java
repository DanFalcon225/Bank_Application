/**
 * The main aim of this class is to provide a sufficient GUI for user to work with application
 */
package bankApplication;

import javax.swing.JOptionPane;

/**
 * @author Daniils Sokolovs
 * @version 04/01/2021
 */

public class MainMenu extends javax.swing.JFrame {
    
    //Implement LinkedList class which use methods from Java Collection Framework
    static BankInterface MyList = new Bank();
    
    //Implement custom LinkedList class
    //static BankInterface MyList = new MyBank();
    

    /**
     * Creates new form Interface
     */
    public MainMenu() {
        initComponents();
        jPanel1.setVisible(false);
        jPanel2.setVisible(false);
    }
    
    /**
     * Validation for Account number
     * @return 
     */
    public int getAccountNoGUI() {
    
      int theAccountNo = -1;
        
      //if text field for the account is empty, stop process and display error
        if (jTextFieldAccountNo.getText().isEmpty()){
                
                JOptionPane.showMessageDialog(this,
                "The input is not valid. Please enter account number.", 
                "Something whent wrong!", JOptionPane.ERROR_MESSAGE);
                
                return theAccountNo;
            }
        
        else {

            try{
                theAccountNo = Integer.parseInt(this.jTextFieldAccountNo.getText());
                
                //Account number must be a positive
                if (theAccountNo < 0){
                    JOptionPane.showMessageDialog(this,
                    "The input is not valid. The account number must be a positive number.", 
                    "Something whent wrong!", JOptionPane.ERROR_MESSAGE);
                }   

            }//end try
            
            //Catch any other exceptions
            catch(NumberFormatException exception){
                JOptionPane.showMessageDialog(this,
                "The input is not valid - wrong data type, input must be integer! Please enter a valid number of stored account.", "Something went wrong!",
                 JOptionPane.ERROR_MESSAGE); 
            }//end catch
            
        }

      return theAccountNo;
      
    }//end getAccountNoGUI() method
    
    
    /**
     * Validation for Account Holder Name
     * @return 
     */
    public String getHolderName(){
        
         String theName = null;
        
        try {
            if(jTextFieldAccHolder.getText().isEmpty()){
                throw new Exception();
            }
            
            theName = jTextFieldAccHolder.getText();
        }//end try
        
        catch(Exception e){
            JOptionPane.showMessageDialog(this, "Please enter name of the account holder.","Something whent wrong!",
                    JOptionPane.ERROR_MESSAGE);
            
        }//end catch
        
        return theName;
        
    }//end getHolderName() method
    
    
    /**
     * Validation for Account Holder Address
     * @return 
     */
    public String getAddress(){
        
          String theAddress = null;
        
        try {
            if(jTextFieldAddress.getText().isEmpty()){
                throw new Exception();
            }
            
            theAddress = jTextFieldAddress.getText();
        }//end try
        
        catch(Exception e){
            JOptionPane.showMessageDialog(this, "Please enter address of the account holder.","Something whent wrong!",
                    JOptionPane.ERROR_MESSAGE);
            
        }//end catch
        
        return theAddress;
    }//end getAddress method
    
  
    
    /**
     * Adding new account to the bank terminal
     */
    
    public void addAccount(){
  
        if(getAccountNoGUI() == -1 || getHolderName() == null || getAddress() == null){
            JOptionPane.showMessageDialog(this, "Account cannot be added as some values are missing.","Something whent wrong!",
                    JOptionPane.ERROR_MESSAGE);
        }
        
        //Search for the similar account number. Disable possibility multiple account being registered under the same number.
        else if (MyList.search(getAccountNoGUI()) != null){
        
            JOptionPane.showMessageDialog(this, "Account cannot be added as an account already existing in a terminal under the entered number.","Something whent wrong!",
            JOptionPane.ERROR_MESSAGE);
            
        }
        else{
            
            Account newAccount = new Account(getAccountNoGUI(), getHolderName(), getAddress()); //creat object of Account class
            
            MyList.addAccount(newAccount);//Using method from class Bank to add account
           
            JOptionPane.showMessageDialog(this, "New Account has been added!");
            
        
        }
        setClear();
        setClearAccNo();
        
    }//end addAccount() menthod
    
    /**
     * Removing Account from the storage
     */
    
    public void removeAccount(){
        
        //Account number must be entered
         if (jTextFieldAccountNo.getText().isEmpty()){
                
                JOptionPane.showMessageDialog(this,
                "The input is not valid. Please enter account number.", 
                "Something whent wrong!", JOptionPane.ERROR_MESSAGE);
        }
        
         //Search for account number.
        else if(MyList.search(getAccountNoGUI()) == null){
            
            JOptionPane.showMessageDialog(this, "Account cannot be removed. There are no any accounts stored under the entered number.","Something whent wrong!",
                    JOptionPane.ERROR_MESSAGE);
            
        }
        
        //Remove account
        else{
 
            MyList.removeAccount(getAccountNoGUI());
            JOptionPane.showMessageDialog(this, "Account has been removed.");
            
        }
        setClearAccNo();//clesr Account number text field
         
    }//end removeAccount() method
    
    /**
     * Display all stored accounts
     */
    public void getDisplay(){
        
        try{
            
            if(MyList.listAccounts().isEmpty()){
              throw new Exception();  
            }
            jTextAreaForDisplay.append(MyList.listAccounts() + "\n"); //empty line between stored elements
            jTextAreaForDisplay.append("\n");
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(this,
            "Unable to display data. There are no accounts in a terminal.", 
            "Something whent wrong!", JOptionPane.ERROR_MESSAGE);
            
        }
       
    }//end getDisplay() method 
    
    
    /**
     * Search for particular account in a storage
     */
    public void searchAccount(){
        
        //Account number must be entered
        if (jTextFieldAccountNo.getText().isEmpty()){
                
                JOptionPane.showMessageDialog(this,
                "The input is not valid. Please enter account number.", 
                "Something whent wrong!", JOptionPane.ERROR_MESSAGE);
        }
        
        //Account must exist in a storage
        else if(MyList.search(getAccountNoGUI()) == null){
            
            JOptionPane.showMessageDialog(this, "Account cannot be found. There are no any accounts stored under the entered number.","Something whent wrong!",
                    JOptionPane.ERROR_MESSAGE);
            
        }
        
        else{
            
            //Search for Account class object
            Account foundAccount = MyList.search(getAccountNoGUI());
            
            //Display found object
            jTextAreaForDisplay.append(foundAccount.toString() + "\n");
            jTextAreaForDisplay.append("\n");
            
            JOptionPane.showMessageDialog(this, "Account has been found.");    
            
            
        }
        setClearAccNo();//clesr Account number text field
        
    }//end searchAccount() method
    

     /**
     * Validation for transaction amount
     * @return 
     */
    public int getAmountForTrans(){
        
        int theAmountForTrans = -1;
        
        try{
            theAmountForTrans = Integer.parseInt(this.jTextFieldTransAmount.getText());
            if(theAmountForTrans < 0){
                JOptionPane.showMessageDialog(this,
                "The input is not valid. The transation amount must be a positive number.", 
                "Something whent wrong!", JOptionPane.ERROR_MESSAGE);
            }

        }//end try
        
        catch(NumberFormatException exception){
            JOptionPane.showMessageDialog(this,
            "The input is not valid - wrong data type, input must be integer! Please enter a valid amount for transaction.", "Something went wrong!",
             JOptionPane.ERROR_MESSAGE); 
        }//end catch
        
      return theAmountForTrans;
      
    }//getAmountForTrans() method
    
    
    /**
     * Validation for transaction type
     * @return 
     */
    
    public Boolean getTransTypeGUI(){
        
        Boolean theTransTypeGUI = null;
            
            //In case two radio buttons were selected at the same time
            if (jRadioButtonDeposit.isSelected() && jRadioButtonWithdrawal.isSelected()){
                
                JOptionPane.showMessageDialog(this, "Please select only one transaction type at once.","Something whent wrong!",
                JOptionPane.ERROR_MESSAGE);
                
                return theTransTypeGUI;
                
            }
            
            //For deposit
            else if (jRadioButtonDeposit.isSelected()){

            return true;

            }
            
            //For withdrawal
            else if (jRadioButtonWithdrawal.isSelected()){

            return false;
            
            }

            else {
                
                JOptionPane.showMessageDialog(this, "Please select transaction type.","Something whent wrong!",
                    JOptionPane.ERROR_MESSAGE);
            }

     
       return  theTransTypeGUI;
       
    }//end getTransTypeGUI() method
    
    
    /**
     * Processing transaction for account
     */
    public void addTransaction(){

        if(getAmountForTrans() == -1 || getAccountNoGUI() == -1 || getTransTypeGUI() == null){
            JOptionPane.showMessageDialog(this, "Transaction cannot be made as some values are missing.","Something whent wrong!",
                    JOptionPane.ERROR_MESSAGE);
        }
        
     
        else if (MyList.search(getAccountNoGUI()) == null){
            JOptionPane.showMessageDialog(this,
            "Transaction cannot be made. There are no any accounts stored under the entered number.", 
            "Something whent wrong!", JOptionPane.ERROR_MESSAGE);
            
        }
        
        
        else {
            
            Transaction newTransation = new Transaction(getTransTypeGUI(), getAmountForTrans()); // new object of Transaction class
            
            Account transaction = MyList.search(getAccountNoGUI()); // search for account by account number
                
            //In case if current balance is less than withdrawal amount
            if(transaction.getBalance() < getAmountForTrans() && getTransTypeGUI() == false){

                JOptionPane.showMessageDialog(this, "Withdrawal cannot be done as insufficient account balance.","Something whent wrong!",
                JOptionPane.ERROR_MESSAGE);
            }

            else {

                transaction.addTransaction(newTransation);

                JOptionPane.showMessageDialog(this, "Transaction has been made.");

            }
     
        }
        setClearAccNo();//clear Account no text field
        setClearTrans();//clear Transaction text fields
        
    }//end addTransaction() method
    
    
    /**
     * Display all transactions within the account
     */
    public void displayTransactions(){
        
        if(getAccountNoGUI() == -1){
            JOptionPane.showMessageDialog(this, "Please enter account number for which transactions are needs to be displayed.","Something whent wrong!",
            JOptionPane.ERROR_MESSAGE);
            
        }//end if
        
        
        else if (MyList.search(getAccountNoGUI()) == null){
            JOptionPane.showMessageDialog(this,
            "Transactions cannot be displayed. There are no any accounts stored under the entered number.", 
            "Something whent wrong!", JOptionPane.ERROR_MESSAGE);
            
        }//end else if

        else {
            
            Account transList = MyList.search(getAccountNoGUI());
            
            //if no transaction made for the account
            if(transList.getAccountTransaction().isEmpty()){
                
                JOptionPane.showMessageDialog(this, "There are no transactions made for chosen account.","Something whent wrong!",
                JOptionPane.ERROR_MESSAGE);
                
            }//end if
            
            else {
                
                //creating Transaction class object and using relevant method from Account class
                for(Transaction transDisplay : transList.getAccountTransaction()){
                jTextAreaForDisplay.append(transDisplay.toString() + "\n"); //empty line between stored elements
                jTextAreaForDisplay.append("\n");
                
                }//end for loop
                    
            }//end else
      
        }//end else
        setClearAccNo();//clesr Account number text field
        
    }//end displayTransactions() method
    
    
    /**
     * Sort list of transactions
     */
    public void printSortedTrans(){
        
        if(getAccountNoGUI() == -1){
            JOptionPane.showMessageDialog(this, "Please enter account number for which transactions are needs to be sorted.","Something whent wrong!",
            JOptionPane.ERROR_MESSAGE);
            
        }//end if
        
        else if (MyList.search(getAccountNoGUI()) == null){
            JOptionPane.showMessageDialog(this,
                    "Transactions cannot be sorted. There are no any accounts stored under the entered number.",
                    "Something whent wrong!", JOptionPane.ERROR_MESSAGE);
            
        }//end else if
        
        else {
            
            Account transListSort = MyList.search(getAccountNoGUI());
            
            //if no transaction made for the account
            if(transListSort.getAccountTransaction().isEmpty()){
                
                JOptionPane.showMessageDialog(this, "There are no transactions made for chosen account.","Something whent wrong!",
                        JOptionPane.ERROR_MESSAGE);
                
            }//end if
            
            else{
                
                for(Transaction transSorted : transListSort.SortedTransactions()){
                    jTextAreaForDisplay.append(transSorted.toString() + "\n"); //empty line between stored elements
                    jTextAreaForDisplay.append("\n");
                    
                }// end for loop
                
            }//end else
            
        }//end else
        setClearAccNo();//clesr Account number text field
        
    }//end printSortedTrans() method
    
    /**
     * Clear all text fields in Account panel
     */
    public void setClear(){
        
        jTextFieldAccHolder.setText(null);
        jTextFieldAddress.setText(null);  
    }
   
    /**
     * Clear all text fields in Transaction panel
     */
    public void setClearTrans(){
        
        jTextFieldTransAmount.setText(null);
       
    }
    
    /**
     * Reset Account number input
     */
    public void setClearAccNo(){
        
        jTextFieldAccountNo.setText(null);
    }
    
    /**
     * Reset Text Area
     */
    
    public void setClearTextArea(){
        
        jTextAreaForDisplay.setText(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaForDisplay = new javax.swing.JTextArea();
        jButtonClearDisplay = new javax.swing.JButton();
        jButtonResetScreen = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldAccHolder = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldAddress = new javax.swing.JTextField();
        jButtonResetAccInput = new javax.swing.JButton();
        jButtonAddAccount = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldTransAmount = new javax.swing.JTextField();
        jRadioButtonDeposit = new javax.swing.JRadioButton();
        jRadioButtonWithdrawal = new javax.swing.JRadioButton();
        jButtonAddTrans = new javax.swing.JButton();
        jButtonRestTransInput = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldAccountNo = new javax.swing.JTextField();
        jButtonRestAccNoInput = new javax.swing.JButton();
        jButtonSort = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jRadioButtonMenuItem2 = new javax.swing.JRadioButtonMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("jRadioButtonMenuItem1");

        jMenuItem5.setText("jMenuItem5");

        jMenu3.setText("File");
        jMenuBar2.add(jMenu3);

        jMenu4.setText("Edit");
        jMenuBar2.add(jMenu4);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextAreaForDisplay.setColumns(20);
        jTextAreaForDisplay.setRows(5);
        jTextAreaForDisplay.setBorder(new javax.swing.border.SoftBevelBorder(0));
        jScrollPane1.setViewportView(jTextAreaForDisplay);

        jButtonClearDisplay.setText("Clear the Display Area");
        jButtonClearDisplay.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButtonClearDisplay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClearDisplayActionPerformed(evt);
            }
        });

        jButtonResetScreen.setText("Reset Screen");
        jButtonResetScreen.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButtonResetScreen.setPreferredSize(new java.awt.Dimension(159, 25));
        jButtonResetScreen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonResetScreenActionPerformed(evt);
            }
        });

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(0));
        jPanel1.setToolTipText("");
        jPanel1.setPreferredSize(new java.awt.Dimension(430, 334));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel1.setText("Account Details");

        jLabel3.setText("Account holder's name");

        jTextFieldAccHolder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldAccHolderActionPerformed(evt);
            }
        });

        jLabel4.setText("Address");

        jTextFieldAddress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldAddressActionPerformed(evt);
            }
        });

        jButtonResetAccInput.setText("Reset Input");
        jButtonResetAccInput.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButtonResetAccInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonResetAccInputActionPerformed(evt);
            }
        });

        jButtonAddAccount.setText("Add Account");
        jButtonAddAccount.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButtonAddAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddAccountActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4)
                            .addComponent(jButtonAddAccount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldAccHolder, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonResetAccInput, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldAccHolder, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonResetAccInput, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(jButtonAddAccount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(0));
        jPanel2.setPreferredSize(new java.awt.Dimension(430, 334));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel5.setText("Transaction Details");
        jLabel5.setPreferredSize(new java.awt.Dimension(102, 16));

        jLabel7.setText("Amount");

        jTextFieldTransAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTransAmountActionPerformed(evt);
            }
        });

        jRadioButtonDeposit.setSelected(true);
        jRadioButtonDeposit.setText("Deposit");
        jRadioButtonDeposit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonDepositActionPerformed(evt);
            }
        });

        jRadioButtonWithdrawal.setText("Withdrawal");
        jRadioButtonWithdrawal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonWithdrawalActionPerformed(evt);
            }
        });

        jButtonAddTrans.setText("Add Transaction");
        jButtonAddTrans.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButtonAddTrans.setPreferredSize(new java.awt.Dimension(103, 25));
        jButtonAddTrans.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddTransActionPerformed(evt);
            }
        });

        jButtonRestTransInput.setText("Reset Input");
        jButtonRestTransInput.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButtonRestTransInput.setPreferredSize(new java.awt.Dimension(103, 25));
        jButtonRestTransInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRestTransInputActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonAddTrans, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jRadioButtonDeposit)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(138, 138, 138)
                                .addComponent(jButtonRestTransInput, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jTextFieldTransAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jRadioButtonWithdrawal))
                                .addGap(22, 22, 22))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldTransAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 113, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButtonDeposit)
                    .addComponent(jRadioButtonWithdrawal))
                .addGap(97, 97, 97)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAddTrans, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(jButtonRestTransInput, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Bank application");
        jLabel8.setToolTipText("");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel2.setText("Account No.");

        jTextFieldAccountNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldAccountNoActionPerformed(evt);
            }
        });

        jButtonRestAccNoInput.setText("Reset Input");
        jButtonRestAccNoInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRestAccNoInputActionPerformed(evt);
            }
        });

        jButtonSort.setText("Sort Transactions");
        jButtonSort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSortActionPerformed(evt);
            }
        });

        jButton1.setText("Search");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jMenuBar1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jMenu1.setText("File");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jRadioButtonMenuItem2.setSelected(true);
        jRadioButtonMenuItem2.setText("Exit");
        jRadioButtonMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jRadioButtonMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenu2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jMenuItem2.setText("Add new Account");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuItem3.setText("Delete Account");
        jMenuItem3.setToolTipText("");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuItem4.setText("Add new Transaction");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuBar1.add(jMenu2);

        jMenu5.setText("View");
        jMenu5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jMenuItem6.setText("Display All  Accounts");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem6);

        jMenuItem7.setText("Display Transactions");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem7);

        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(129, 129, 129)
                .addComponent(jButtonClearDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonResetScreen, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(118, 118, 118))
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldAccountNo, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonRestAccNoInput, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addComponent(jButtonSort, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25))
            .addGroup(layout.createSequentialGroup()
                .addGap(249, 249, 249)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel8)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldAccountNo, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonRestAccNoInput, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSort, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonClearDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonResetScreen, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );

        jPanel1.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents
    //Exit from application
    private void jRadioButtonMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMenuItem2ActionPerformed

        System.exit(0);
    }//GEN-LAST:event_jRadioButtonMenuItem2ActionPerformed
    
    //Panel for transacition
    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed

        jPanel2.setVisible(true);
        jPanel1.setVisible(false);
        
    }//GEN-LAST:event_jMenuItem4ActionPerformed
    //Panel for account
    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed

        jPanel2.setVisible(false);
        jPanel1.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    //Account number validation
    private void jTextFieldAccountNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldAccountNoActionPerformed

        getAccountNoGUI();
    }//GEN-LAST:event_jTextFieldAccountNoActionPerformed
    
    //Account holder validation
    private void jTextFieldAccHolderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldAccHolderActionPerformed
        
        getHolderName();
    }//GEN-LAST:event_jTextFieldAccHolderActionPerformed
    
    //Validation of account holder's address
    private void jTextFieldAddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldAddressActionPerformed
        
        getAddress();
    }//GEN-LAST:event_jTextFieldAddressActionPerformed
    
    //Clear all text fields in Account panel
    private void jButtonResetAccInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonResetAccInputActionPerformed

        setClear();
    }//GEN-LAST:event_jButtonResetAccInputActionPerformed
    
    //Adding new Account
    private void jButtonAddAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddAccountActionPerformed
        
        addAccount();
    }//GEN-LAST:event_jButtonAddAccountActionPerformed
    
    //Validation for transaction amount
    private void jTextFieldTransAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTransAmountActionPerformed
        
        getAmountForTrans();
    }//GEN-LAST:event_jTextFieldTransAmountActionPerformed
    
   //Clear all text fields in Transaction panel
    private void jButtonRestTransInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRestTransInputActionPerformed
        
        setClearTrans();
    }//GEN-LAST:event_jButtonRestTransInputActionPerformed
    
    //Reset Account number input
    private void jButtonRestAccNoInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRestAccNoInputActionPerformed
        
        setClearAccNo();
    }//GEN-LAST:event_jButtonRestAccNoInputActionPerformed
    
    //Displays all stored Accounts
    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        
        getDisplay();
    }//GEN-LAST:event_jMenuItem6ActionPerformed
    
    //Reset Text Area
    private void jButtonClearDisplayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClearDisplayActionPerformed
       
        setClearTextArea();
    }//GEN-LAST:event_jButtonClearDisplayActionPerformed
    //Removing account for the storage
    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        
        removeAccount();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jButtonAddTransActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddTransActionPerformed
        
        addTransaction();
    }//GEN-LAST:event_jButtonAddTransActionPerformed

    private void jRadioButtonDepositActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonDepositActionPerformed
        
        getTransTypeGUI();
    }//GEN-LAST:event_jRadioButtonDepositActionPerformed

    private void jRadioButtonWithdrawalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonWithdrawalActionPerformed
        
        getTransTypeGUI();
    }//GEN-LAST:event_jRadioButtonWithdrawalActionPerformed

    //Print sorted transactions
    private void jButtonSortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSortActionPerformed
       printSortedTrans();
    }//GEN-LAST:event_jButtonSortActionPerformed

    //List of transactions
    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        
        displayTransactions();
    }//GEN-LAST:event_jMenuItem7ActionPerformed
    
    //Reset whole screen
    private void jButtonResetScreenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonResetScreenActionPerformed
        setClearTrans();
        setClearAccNo();
        setClearTextArea();
        setClear();
    }//GEN-LAST:event_jButtonResetScreenActionPerformed

    //Search of account
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        searchAccount();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonAddAccount;
    private javax.swing.JButton jButtonAddTrans;
    private javax.swing.JButton jButtonClearDisplay;
    private javax.swing.JButton jButtonResetAccInput;
    private javax.swing.JButton jButtonResetScreen;
    private javax.swing.JButton jButtonRestAccNoInput;
    private javax.swing.JButton jButtonRestTransInput;
    private javax.swing.JButton jButtonSort;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jRadioButtonDeposit;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem2;
    private javax.swing.JRadioButton jRadioButtonWithdrawal;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaForDisplay;
    private javax.swing.JTextField jTextFieldAccHolder;
    private javax.swing.JTextField jTextFieldAccountNo;
    private javax.swing.JTextField jTextFieldAddress;
    private javax.swing.JTextField jTextFieldTransAmount;
    // End of variables declaration//GEN-END:variables
}//end MainMenu class
