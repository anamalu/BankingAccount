/*
Apollo Namalu
#Savings Account class is an extension of the Account class
#Trigured by running BankingAccount.java
*/

public class SavingsAccount extends Account{
	private double interestRate_;
		
	public SavingsAccount(java.lang.String accountHolderNames, int id,  java.lang.String ssn, 
			      java.lang.String address, double initialBalance ){
                                
		super();
		this.accountType_ = SAVINGS_ACCOUNT_;
	     	this.create(id, accountHolderNames, ssn, address, initialBalance);
     	}
      
	/** computes the interest rate */
	public double calculateInterest(){
    
    		if(this.accountType_ != SAVINGS_ACCOUNT_) {
      		System.out.println("Invalid Account Type :"+this.accountType_);
    		}
    		return this.balance_*this.interestRate_/100;
  	}

	/** Setter Method for interestRate_*/
	public void  setInterestRate( double newInterestRate) {
		interestRate_ = newInterestRate;
  	}

	/** Accessor Method for interestRate_ */
  	public double  getInterestRate_() {
    		return interestRate_;
  	}

  	/** Overridden toString() method */
  	public java.lang.String  toString() {
    		StringBuffer strBuff = new StringBuffer();
    		double interest = calculateInterest()/30;
    		balance_ += interest;	
    		strBuff.append("\n \t END-OF-THE-MONTH STATEMENT: \n\n");
    		strBuff.append("Account Type: Savings \n");
    		strBuff.append("Interest Rate_= " + interestRate_+ "%" + "\n");
    		strBuff.append(super.toString());
     		strBuff.append("Interest Earned = " + interest + "%" + "\n");
    		strBuff.append("------------------END-OF-STATEMENT-------------------\n");	
    		return strBuff.toString();
  	}//End of toString()

}//End of Savings class

