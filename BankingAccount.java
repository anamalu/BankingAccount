/* 
Apollo Namalu
#OOP,
#Apollo Namalu
#BankingAccount: A test class for the Accounts 
*/  
import java.util.*;

public class BankingAccount{
	private static Scanner input = new Scanner(System.in);

	public static void main(String[] args){
           	int userChoice, type, menu, id_num;
            	String name, address = "", ssn = "", vendor = "";
          	double init_bal, with_amount, dep_amount, trans_amount, bill_amount;
            	boolean quit = false;
            	Account account = null;

		//Setting up a new account. Gathering required information
            	System.out.println("\nWelcome to Terminal Bank!\n\n ");
            	System.out.println("Enter your Name: ");
		name = input.nextLine();
            	System.out.println("Enter your SSN: ");
            	ssn = input.nextLine();
           	System.out.println("Enter your address: ");
            	address = input.nextLine();
	 	System.out.println("Enter Identification Number: ");
                id_num = input.nextInt();
                System.out.println("Enter Initial Balance: ");
                init_bal = input.nextDouble();

                //choosing an account type to work with
               	System.out.println("Enter Account Type (100=checking, 200=savings): ");
                type = input.nextInt();
                
               	if(type == 100){
                  	account = new CheckingAccount(name, id_num,ssn,address, init_bal);
                }
                else{
                	System.out.println("Enter interest rate: ");
                	double interest = input.nextDouble();
                	account = new SavingsAccount(name, id_num,ssn,address, init_bal);//savings
                	((SavingsAccount)account).setInterestRate(interest);
              	}
                
                do {
                
                	System.out.println("\t MENU");
			System.out.println("1. Deposit Amount");
			System.out.println("2. Withdraw Amount");
			System.out.println("3. Transfer Amount [Savings to Checking]");
			System.out.println("4. Pay Bills");			         
			System.out.println("5. Display Information");
			System.out.println("6. Exit");
		        System.out.print("Please enter your choice: ");
		        menu = input.nextInt();
		              
		        switch (menu) {
		        	case 1:
		        		System.out.println("Hi "+name+", \n");
					System.out.print("Enter amount to deposit: ");
		      			dep_amount = input.nextDouble();
	                      		account.deposit(dep_amount);
		                 	break;
		          	case 2:
		        		System.out.println("Hi "+name+", \n");
					System.out.print("Enter amount to deposit: ");
					System.out.println("Your Balance = $" + account.balance_);
               				System.out.print("Enter amount to withdraw:");
               			  	with_amount = input.nextDouble();
		                      	account.withdraw(with_amount);
		                      	break;
		           	case 3:
		        		System.out.println("Hi "+name+", \n");
					System.out.print("Enter amount to deposit: ");
					System.out.print("Enter amount to transfer: ");
			      		trans_amount = input.nextDouble();
		                	//account.transfer(trans_amount);
		                      	break;
		              	case 4:
		        		System.out.println("Hi "+name+", \n");
					System.out.print("Enter amount to deposit: ");
					((CheckingAccount)account).payBill(); 
		                      	break;
		              	case 5:
		                      	System.out.println(account);
		                      	break;
		              	case 6:
		                      	quit = true;
		                      	break;
		              }
                } while (!quit);

            System.out.println("Thank you for banking with Terminal bank");
      }//End of main() 

}//End of class BankingAccount
