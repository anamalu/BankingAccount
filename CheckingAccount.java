/*
#Apollo Namalu
#OOP, Checking account, an extension of the Account class
#Is trigered by running BankingAccount.java
*/
import java.util.*;
public class CheckingAccount  extends Account {
	private java.lang.String vendor_;
	private double amount_;
	private static Scanner input = new Scanner(System.in);
  /** Constructor receives and initializes account holder information*/ 
	public CheckingAccount(java.lang.String accountHolderNames, int id,  java.lang.String ssn, 
				java.lang.String address, double initialBalance) {
                                
		super();
		this.accountType_ = CHECKING_ACCOUNT_;
	     	this.create(id, accountHolderNames, ssn, address, initialBalance);
  	}//End of CheckingAccount constructor
  
	public void payBill(){
		String vendor = "No bills paid yet"; 
		double bill_amount = 0.0;
  	 	System.out.println("Enter name of vendor: ");
	 	vendor = input.nextLine();
	 	System.out.println("Enter Bill amount: ");
	 	bill_amount = input.nextDouble();
  	 	this.vendor_ = vendor;
  	 	this.amount_ = bill_amount;
 	 	balance_ -= this.amount_;
 	 //System.out.println("Nvwaalaaaah!");
  	}//End of payBill 

	/** Default toString() method */
	public java.lang.String toString() {
		StringBuffer strBuff = new StringBuffer();
		strBuff.append("\n \t END-OF-THE-MONTH STATEMENT: \n");
		strBuff.append("Account Type: CHECKING \n");
		strBuff.append(super.toString());
		strBuff.append("Bill Payments:" +"  Vendor: " + vendor_ +" Amount: $" + amount_+ "\n");
		strBuff.append("---------------------END-OF-STATEMENT------------------------\n");	
    		return strBuff.toString();
  	}//End of toString()

}//End of Class CheckingAccount
