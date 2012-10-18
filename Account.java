/* 
Apollo Namalu
OOP H4 Parent class, is trigered by running BankingAccount.java
Creating an account to be extended by Savings and Checking accounts.
Provides functionality to Savings and Checking accounts
*/
import java.util.*;
import java.util.Date;
import java.util.Calendar;
import java.util.TimeZone;

public class Account{

	protected int id_;
	protected double balance_;
	protected double annualInterestRate_;
	protected java.util.Date dateCreated_;
	protected int accountType_;
	protected java.lang.String accountHolderNames_;
	protected static int INVALID_ACCOUNT_ = -1;
	protected static int CHECKING_ACCOUNT_ = 100; //user will enter 100 to choose checking account
	protected static int SAVINGS_ACCOUNT_ = 200;  //user will enter 200 to choose savings account
	protected java.lang.String address_;
	protected java.lang.String ssn_;
	protected double depositsSoFar = 0.0;
	protected double withdrawsSoFar = 0.0;
	
	//creates the default account
	public Account(){
		//super();
		this.id_ = 0;		
		this.balance_ = 0.0;
		//annualInterestRate_ = 0.0;
		this.accountType_ = INVALID_ACCOUNT_;
		this.address_ = "";
		this.ssn_ = "";
	}

	//creates an account with specified id, initial balance, Name, SSN, and address
	public void create(int id,java.lang.String accountHolderNames,java.lang.String ssn, 
				    java.lang.String address, double initialBalance){
	   
	    if(this.accountType_ == INVALID_ACCOUNT_) {
		 System.out.println("Invalid Account Type :"+this.accountType_);
	    }
	    if(initialBalance < 0) {
		 System.out.println("Invalid Initial Balance (in cents):" + initialBalance);
	    }

	    this.id_ = id;
	    this.accountHolderNames_ = accountHolderNames;
	    this.balance_ = initialBalance;
	    this.dateCreated_ = Calendar.getInstance(TimeZone.getTimeZone("GMT")).getTime();
	    this.address_ = address;
	    this.ssn_ = ssn;
	    //return this.balance_;
	    //id = this.id;		
	}
	
	//sets the balance for a specified account type
	void setBalance(int amount){
		if(this.accountType_ == INVALID_ACCOUNT_) {
			 System.out.println("Invalid Account Type :"+this.accountType_);
	    	}	
	    	if(amount < 0) {
			 System.out.println("Invalid Amount (in cents) :"+amount);
	    	}
	    	this.balance_ = amount;
	    	//return this.balance_;
		//balance_ = amount;
	}//End of setBalance()
	
	/** Carry's out deposits from the specified account */
	public double deposit(double amount) {
		if(this.accountType_ == INVALID_ACCOUNT_) {
      			System.out.println("Invalid Account Type :"+this.accountType_);
    		}
    		if(amount < 0) {
      			System.out.println("Invalid Amount (in cents) :"+amount);
   		 }
    		
		this.depositsSoFar += amount;
    		this.balance_ += amount;
    		return this.balance_;
  	}//End of deposit()	

	/** Carry's out withdraws from the specified account */
	public double withdraw(double amount) {
    
		if(this.accountType_ == INVALID_ACCOUNT_) {
			System.out.println("Invalid Account Type :"+this.accountType_);
		}
   		if(amount < 0) {
      			System.out.println("Invalid Amount (in cents) :"+amount);
    		}
    
		this.withdrawsSoFar += amount; 
    		this.balance_ -= amount;
    		return this.balance_;
  	}
	
	/* Setter Method for accountType_*/
	public void  setAccountType_(   int newAccountType_   ) {
		accountType_ = newAccountType_;
  	}	

	/* Accessor Method for accountType_ */
	public int  getAccountType_(  ) {
		return accountType_;
	}

	//Setter Method for id-
  	public void setId(int id){
		id_ = id;
  	}
  
	//Accessor Method for 
	public int getId(){
		return id_;
	}

  	/* Setter Method for customerNames_*/
  	public void  setAccountHolderNames_(java.lang.String newaccountHolderNames) {
    		accountHolderNames_ = newaccountHolderNames;
  	}

  	/* Accessor Method for customerNames_ */
  	public java.lang.String  getCustomerNames_(  ) {
    		return accountHolderNames_;
  	}
	
	// Setter Method for  balance_
  	public void  setBalance_(int newBalance) {
    		balance_ = newBalance;
  	}
	
	//Accessor method for balance_ 
	double getBalance(){
		return balance_;
	}
	
  	/* Setter Method for dateCreated_*/
  	public void  setdateCreated_(   java.util.Date newDateCreated_   ) {
    		dateCreated_ = newDateCreated_;
  	}

  	/* Accessor Method for dateCreated_ */
	public java.util.Date  getDateCreated_(  ) {
		return dateCreated_;
  	}

	/* Default toString() method */
  	public java.lang.String  toString(  ) {
    		StringBuffer strBuff = new StringBuffer();
    		strBuff.append("\tCustomer Name: " + accountHolderNames_ + "\n");
    		strBuff.append("\tCustomer SSN: " + ssn_ + "\n");
    		strBuff.append("\tCustomer Address: " + address_ + "\n");
    		strBuff.append("\tDate Created = " + dateCreated_ + "\n");
   		strBuff.append("\tBalance = $" + balance_ + "\n");
    		strBuff.append("\tDeposits = $" + depositsSoFar + "\n");
    		strBuff.append("\tWithdraws = $" + withdrawsSoFar + "\n");
    		return strBuff.toString();
  	}//End of toString()
	
}//End of Account Class		
