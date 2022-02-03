# Bank_Application

Current project is covering a basic data structure techniques such as: search, sort, select, add etc.	
The main data structure in used is LinkedList

"Bank" class - uses a LinkedList methods imported from Collection FrameWork	
"MyBank" class provides a manually coded data structure which imitates a LinkedList functionality. 

In order to swich between those two classes we need to go to the "MainMenu" and comment and uncomment relevant piece of code.

For "Bank" class 
static BankInterface MyList = new Bank();

For "MyBank" class
static BankInterface MyList = new MyBank();

Methods in each class has similar names and all of them inherited through the interface class - "BankInterface"
