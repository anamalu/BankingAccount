/*
 * JUnit Test class AccountTest.java for com.hywy.samples.bank.Account.java
 *
 */
package com.hywy.samples.bank;


import com.hywy.pej.adapter.JDOAdapter;

// Standard JDO Class Library Imports
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;

import junit.framework.TestCase;

import java.util.Date;
import java.text.SimpleDateFormat;
import com.hywy.samples.bank.Account;

/**
 * Unit test associated with com.hywy.samples.bank.Account Class' JDO field's get/set method Implementation
 * JDO identity type: datastore
 *
 * Author: Administrator
 * Company: HYWY Software Inc.
 *
 * Created Date: May 23, 2002
 *
 * Maintain History:
 *
 */
public class AccountTest extends junit.framework.TestCase {

	/** Project Name that was configured using the Console */
	public static final String PROJECT_NAME_ = "bank";

	/** Datastore Name that was configured using the Console */
	public static final String DATASTORE_NAME_ = "db"; 

	/** PMF Object pool initial capacity */
	public static final int INITIAL_POOL_CAPACITY_ = 5;

	/** PMF Object pool maximum capacity */
	public static final int MAXIMUM_POOL_CAPACITY_ = 10;

	/** PMF Object pool capacity increment */
	public static final int POOL_CAPACITY_INCREMENT_ = 1;

	/** PersistenceManagerFactory Object */
	PersistenceManagerFactory pmFactory_ = null;

	/** A JDOAdapter instance */
	JDOAdapter adapter_;

	/**
	* Retrieve the JDOAdapter that holds the PersistenceManagerFactory Object Pool
	*
	* @return a JDOAdapter instance that holds the PMF object pool
	*/
	public JDOAdapter getAdapter() {
		return this.adapter_;
	}

	/**
	* Releases the configured PMF and returns it back to the Object Pool,
	* so that it can be reused by some other process
	*
	* @param datastoreName - string denoting the name of the pre-configured datastore
	* @param pmFactory - PersistenceManagerFactory object that can be released back to the pool
	*/
	public void releasePMF(String datastoreName, PersistenceManagerFactory pmFactory) {
		this.adapter_.close(datastoreName, pmFactory);
	}

	/**
	*/
	public AccountTest (String name) {
		super (name);
	}

	/**
	* Create  a new JDOAdapter
	*
	*/
	protected void setUp() {
		final String THIS = "setUp(..)";
		Throwable th = null;
		adapter_ = new JDOAdapter(AccountTest.PROJECT_NAME_,
				      	  AccountTest.INITIAL_POOL_CAPACITY_,
				          AccountTest.MAXIMUM_POOL_CAPACITY_,
				          AccountTest.POOL_CAPACITY_INCREMENT_);
		
		try{
	        	pmFactory_ = this.getAdapter().obtainPMF(AccountTest.PROJECT_NAME_,
                                                 	AccountTest.DATASTORE_NAME_);				          
		}catch(Exception exception){
			th = exception;
		}finally{
			if(th != null){
				System.err.println(THIS + " Error when getting the PMF.");
				th.printStackTrace();
			}
		}
	}

   
	/**
	*
	*/
	public static junit.framework.Test suite() {
		return new junit.framework.TestSuite(com.hywy.samples.bank.AccountTest.class);
	}

	/**
	* Main method which executes the Unit Test
	* associated with this class
	*/
	public static void main(String args[]) {
		///////////////////////////////////////////////////////////////////////////
		//  Uncomment out the follwoing code if you want to run JUnit Test console
		//  junit.awtui.TestRunner.run(com.hywy.samples.bank.AccountTest.class);
		///////////////////////////////////////////////////////////////////////////
			
		// Run the JUnit test GUI
		// junit.awtui.TestRunner.run(com.hywy.samples.bank.AccountTest.class);
		
		////////////////////////////////////////////////////////////////////////////		
		// The AccountTest class is run in the command line by default.	
		////////////////////////////////////////////////////////////////////////////
		
		// Run the test harness
		junit.textui.TestRunner.run(com.hywy.samples.bank.AccountTest.class);

	}
      	  	
  	//  Test the Non Relationship field
  		  					
	/**
	*  Test non relationship JDO field -> accountType_
	*/
	public void testSetGetAccountType_() {
		final String THIS = "testSetGetAccountType_(...)";
		Throwable th = null;

		Account account = new Account();				
		
  	        ////////////////////////////////////
  	        // TODO:
  	        // 	Give the real value 
  	        ////////////////////////////////////
  	        int  accountType_ = 0;

		account.setAccountType_(accountType_);				
		assertEquals(" 1 - Before make persistence while comparing accountType_ -> ", accountType_, account.getAccountType_());

		////////////////////////////////////
		// Persform JDO operations
		////////////////////////////////////
		
		PersistenceManager pManager = null;
		Transaction transaction = null;

		try {

			// Obtain a Persistence Manager from the Factory Object
			pManager = pmFactory_.getPersistenceManager();

			// Retrieve a Transaction object from the Persistence Manager
			transaction = pManager.currentTransaction();

			// Begin a Transaction
			transaction.begin();

			// Invoke the Make Persistent on the object
			pManager.makePersistent(account);

			// Commit the transaction
			transaction.commit();

			assertEquals(" 2 - After make persistence while comparing accountType_ -> ", accountType_, account.getAccountType_());

			/////////////////////////////////
			// Do something else .......
			////////////////////////////////

		}catch(Exception exception) {
			
			// Something bad happened.
			// If the transaction object was obtained
			if(transaction != null) {
				// Rollback the transaction
				transaction.rollback();
			}
			
			th = exception;
		}
		finally {
			
			// Close the Persistence Manager
			if(pManager != null) {
				pManager.close();
			}
			
			// Release the Persistence Manager Factory
			if(pmFactory_ != null) {
				releasePMF(DATASTORE_NAME_, pmFactory_);
			}

			if(th != null){
				th.printStackTrace();
				this.fail("Error occurs when doing " + THIS );
			}
		}
	}	  					
  		  					
	/**
	*  Test non relationship JDO field -> balance_
	*/
	public void testSetGetBalance_() {
		final String THIS = "testSetGetBalance_(...)";
		Throwable th = null;

		Account account = new Account();				
		
  	        ////////////////////////////////////
  	        // TODO:
  	        // 	Give the real value 
  	        ////////////////////////////////////
  	        int  balance_ = 0;

		account.setBalance_(balance_);				
		assertEquals(" 1 - Before make persistence while comparing balance_ -> ", balance_, account.getBalance_());

		////////////////////////////////////
		// Persform JDO operations
		////////////////////////////////////
		
		PersistenceManager pManager = null;
		Transaction transaction = null;

		try {

			// Obtain a Persistence Manager from the Factory Object
			pManager = pmFactory_.getPersistenceManager();

			// Retrieve a Transaction object from the Persistence Manager
			transaction = pManager.currentTransaction();

			// Begin a Transaction
			transaction.begin();

			// Invoke the Make Persistent on the object
			pManager.makePersistent(account);

			// Commit the transaction
			transaction.commit();

			assertEquals(" 2 - After make persistence while comparing balance_ -> ", balance_, account.getBalance_());

			/////////////////////////////////
			// Do something else .......
			////////////////////////////////

		}catch(Exception exception) {
			
			// Something bad happened.
			// If the transaction object was obtained
			if(transaction != null) {
				// Rollback the transaction
				transaction.rollback();
			}
			
			th = exception;
		}
		finally {
			
			// Close the Persistence Manager
			if(pManager != null) {
				pManager.close();
			}
			
			// Release the Persistence Manager Factory
			if(pmFactory_ != null) {
				releasePMF(DATASTORE_NAME_, pmFactory_);
			}

			if(th != null){
				th.printStackTrace();
				this.fail("Error occurs when doing " + THIS );
			}
		}
	}	  					
  		  					
	/**
	*  Test non relationship JDO field -> customerNames_
	*/
	public void testSetGetCustomerNames_() {
		final String THIS = "testSetGetCustomerNames_(...)";
		Throwable th = null;

		Account account = new Account();				
		
  	        ////////////////////////////////////
  	        // TODO:
  	        // 	Give the real value 
  	        ////////////////////////////////////
  	        java.lang.String  customerNames_ = null;

		account.setCustomerNames_(customerNames_);				
		assertEquals(" 1 - Before make persistence while comparing customerNames_ -> ", customerNames_, account.getCustomerNames_());

		////////////////////////////////////
		// Persform JDO operations
		////////////////////////////////////
		
		PersistenceManager pManager = null;
		Transaction transaction = null;

		try {

			// Obtain a Persistence Manager from the Factory Object
			pManager = pmFactory_.getPersistenceManager();

			// Retrieve a Transaction object from the Persistence Manager
			transaction = pManager.currentTransaction();

			// Begin a Transaction
			transaction.begin();

			// Invoke the Make Persistent on the object
			pManager.makePersistent(account);

			// Commit the transaction
			transaction.commit();

			assertEquals(" 2 - After make persistence while comparing customerNames_ -> ", customerNames_, account.getCustomerNames_());

			/////////////////////////////////
			// Do something else .......
			////////////////////////////////

		}catch(Exception exception) {
			
			// Something bad happened.
			// If the transaction object was obtained
			if(transaction != null) {
				// Rollback the transaction
				transaction.rollback();
			}
			
			th = exception;
		}
		finally {
			
			// Close the Persistence Manager
			if(pManager != null) {
				pManager.close();
			}
			
			// Release the Persistence Manager Factory
			if(pmFactory_ != null) {
				releasePMF(DATASTORE_NAME_, pmFactory_);
			}

			if(th != null){
				th.printStackTrace();
				this.fail("Error occurs when doing " + THIS );
			}
		}
	}	  					
  		  					
	/**
	*  Test non relationship JDO field -> openedOn_
	*/
	public void testSetGetOpenedOn_() {
		final String THIS = "testSetGetOpenedOn_(...)";
		Throwable th = null;

		Account account = new Account();				
		
  	        ////////////////////////////////////
  	        // TODO:
  	        // 	Give the real value 
  	        ////////////////////////////////////
  	        java.util.Date  openedOn_ = null;

		account.setOpenedOn_(openedOn_);				
		assertEquals(" 1 - Before make persistence while comparing openedOn_ -> ", openedOn_, account.getOpenedOn_());

		////////////////////////////////////
		// Persform JDO operations
		////////////////////////////////////
		
		PersistenceManager pManager = null;
		Transaction transaction = null;

		try {

			// Obtain a Persistence Manager from the Factory Object
			pManager = pmFactory_.getPersistenceManager();

			// Retrieve a Transaction object from the Persistence Manager
			transaction = pManager.currentTransaction();

			// Begin a Transaction
			transaction.begin();

			// Invoke the Make Persistent on the object
			pManager.makePersistent(account);

			// Commit the transaction
			transaction.commit();

			assertEquals(" 2 - After make persistence while comparing openedOn_ -> ", openedOn_, account.getOpenedOn_());

			/////////////////////////////////
			// Do something else .......
			////////////////////////////////

		}catch(Exception exception) {
			
			// Something bad happened.
			// If the transaction object was obtained
			if(transaction != null) {
				// Rollback the transaction
				transaction.rollback();
			}
			
			th = exception;
		}
		finally {
			
			// Close the Persistence Manager
			if(pManager != null) {
				pManager.close();
			}
			
			// Release the Persistence Manager Factory
			if(pmFactory_ != null) {
				releasePMF(DATASTORE_NAME_, pmFactory_);
			}

			if(th != null){
				th.printStackTrace();
				this.fail("Error occurs when doing " + THIS );
			}
		}
	}	  					
  		  	  
                            
}
