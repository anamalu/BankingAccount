/**
 * To Run the Application
 * java -classpath
 * .;C:\PEJ\lib\jdo.jar;C:\PEJ\lib\j2ee.jar;C:\PEJ\lib\pej.jar;C:\PEJ\lib\tools.jar
 * com.hywy.samples.bank.Teller
 */
 // java -classpath .;C:\PEJ\lib\jdo.jar;C:\PEJ\lib\j2ee.jar;C:\PEJ\lib\pej.jar;C:\PEJ\lib\tools.jar com.hywy.samples.bank.Teller


package com.hywy.samples.bank;

import com.hywy.pej.adapter.JDOAdapter;
import com.hywy.pej.persistence.oidserver.Oid;

// Standard JDO Class Library Imports
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;
import javax.jdo.Extent;
import javax.jdo.spi.PersistenceCapable;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.text.SimpleDateFormat;

import com.hywy.samples.bank.Account;
import com.hywy.samples.bank.Checking;
import com.hywy.samples.bank.Savings;

public class Teller {
  /** Project Name that was configured using the Console */
  public static final String PROJECT_NAME_ = "bank";

  /** Datastore Name that was configured using the Console */
  public static final String DATASTORE_NAME_ = "bank_datastore";

  /** PMF Object pool initial capacity */
  public static final int INITIAL_POOL_CAPACITY_ = 1;

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
   * Create  a new JDOAdapter
   *
   */
  public Teller() {
    adapter_ = new JDOAdapter(Teller.PROJECT_NAME_,
                              Teller.INITIAL_POOL_CAPACITY_,
                              Teller.MAXIMUM_POOL_CAPACITY_,
                              Teller.POOL_CAPACITY_INCREMENT_);

    try{
      pmFactory_ = this.getAdapter().obtainPMF(Teller.PROJECT_NAME_,
                                               Teller.DATASTORE_NAME_);
    }catch(Exception exception){
      exception.printStackTrace();
    }
  }

  /**
   * Create Checking or Savings Account
   */
  public void createAccount(String customerNames,
                            int startingBalance,
                            boolean isCheckingAccount)
    throws Exception {

    Account account = null;

    if(isCheckingAccount == true) {
      account = new Checking();
    }
    else {
      account = new Savings();
      ((Savings)account).setInterestRate_(2);
    }
    account.create(customerNames, startingBalance);

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

      // Invoke Make Persistent on the object
      pManager.makePersistent(account);

      // Commit the transaction
      transaction.commit();
    }catch(Exception exception) {

      // Something bad happened.
      // If the transaction object was obtained
      if(transaction != null) {
        // Rollback the transaction
        transaction.rollback();
      }

      exception.printStackTrace();
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
     }

  }

  /**
   * Delete and close the account
   */
  public void deleteAccount(long accountNumber)
    throws Exception {

    Account account = null;


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

      // Obtain the related PC object
      account = (Account)pManager.getObjectById(new Oid(accountNumber), true);

      if(account != null) {
        // Invoke Delete Persistent on the object
        pManager.deletePersistent(account);
      }

      // Commit the transaction
      transaction.commit();
    }catch(Exception exception) {

      // Something bad happened.
      // If the transaction object was obtained
      if(transaction != null) {
        // Rollback the transaction
        transaction.rollback();
      }

      exception.printStackTrace();
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
     }
  }

  /**
   * Transfer Money
   */
  public boolean transferMoney(long fromAccountNumber,
                               long toAccountNumber,
                               int amount) {
    boolean result = false;

    if(amount < 0) {
      return false;
    }

    ////////////////////////////////////
    // Perform JDO operations
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

      // Obtain the related PC object
      Account fromAccount = (Account)pManager.getObjectById(new Oid(fromAccountNumber), true);
      Account toAccount = (Account)pManager.getObjectById(new Oid(toAccountNumber), true);

      if (fromAccount == null) { 
        throw new Exception("Account Not Found: Account "
          + fromAccountNumber + " can not be found "
          + "and funds cannot be transfered from it.");
      }

      if (toAccount == null) { 
        throw new Exception("Account Not Found: Account "
          + toAccountNumber + " can not be found "
          + "and funds cannot be transfered to it.");
      }

      int balance = fromAccount.getBalance_();
      if( balance < amount) {
        throw new Exception("Insufficient Funds: Account "+
          fromAccountNumber+
          " has a balance of just $"+
          balance/100+
          ". Cannot transfer $"+
          amount/100);
      }

      fromAccount.debit(amount);
      toAccount.credit(amount);

      // Invoke Make Persistent on the object
      pManager.makePersistent(fromAccount);
      pManager.makePersistent(toAccount);

      // Commit the transaction
      transaction.commit();
      result = true;

    } catch(Exception exception) {

      // Something bad happened.
      // If the transaction object was obtained
      if(transaction != null) {
        // Rollback the transaction
        transaction.rollback();
      }
      result = false;
      exception.printStackTrace();
    } finally {

      // Close the Persistence Manager
      if(pManager != null) {
        pManager.close();
      }

      // Release the Persistence Manager Factory
      if(pmFactory_ != null) {
        releasePMF(DATASTORE_NAME_, pmFactory_);
      }
    }
    return result;
  }

  /**
   * List out all Checking accounts found
   */
  public ArrayList listAllCheckingAccounts() {

    // List of Checking accounts
    ArrayList checkingList = new ArrayList();

    ////////////////////////////////////
    // Perform JDO operations
    ////////////////////////////////////

    PersistenceManager pManager = null;
    Transaction transaction = null;

    try {

      // Obtain a Persistence Manager from the Factory Object
      pManager = pmFactory_.getPersistenceManager();


      Class pcClass = Class.forName("com.hywy.samples.bank.Checking");
      Extent extent = pManager.getExtent(pcClass, false) ;

      Iterator extentIterator = extent.iterator();
      while ( extentIterator.hasNext()) {

        PersistenceCapable checkingPC = (PersistenceCapable)extentIterator.next();


        Checking checking = (Checking)checkingPC;

        Oid currentCheckingOID = null;

        currentCheckingOID = (Oid)pManager.getTransactionalObjectId(checkingPC);
        if(currentCheckingOID != null) {
          System.out.println("_________________________________________________");
          System.out.println("Checking Account for :"+currentCheckingOID.toLong());
          System.out.println("Details are :"+checkingPC.toString());
          System.out.println("_________________________________________________");
          //          checkingList.add(new Long(currentCheckingOID.toLong()));
          checkingList.add(currentCheckingOID);
        }

      }
    } catch(Exception exception) {

      // Something bad happened.
      // If the transaction object was obtained
      if(transaction != null) {
        // Rollback the transaction
        transaction.rollback();
      }

      exception.printStackTrace();
    } finally {

      // Close the Persistence Manager
      if(pManager != null) {
        pManager.close();
      }

      // Release the Persistence Manager Factory
      if(pmFactory_ != null) {
        releasePMF(DATASTORE_NAME_, pmFactory_);
      }
    }
    return checkingList;
  }

  /**
   * List out all Savings accounts found
   */
  public ArrayList listAllSavingsAccounts() {

    // List of Savings accounts
    ArrayList savingsList = new ArrayList();

    ////////////////////////////////////
    // Persform JDO operations
    ////////////////////////////////////

    PersistenceManager pManager = null;
    Transaction transaction = null;

    try {

      // Obtain a Persistence Manager from the Factory Object
      pManager = pmFactory_.getPersistenceManager();


      Class pcClass = Class.forName("com.hywy.samples.bank.Savings");
      Extent extent = pManager.getExtent(pcClass, false) ;

      Iterator extentIterator = extent.iterator();
      while (extentIterator.hasNext()) {

        PersistenceCapable savingsPC = (PersistenceCapable)extentIterator.next();


        Savings savings = (Savings)savingsPC;

        Oid currentSavingsOID = null;

        currentSavingsOID = (Oid)pManager.getTransactionalObjectId(savingsPC);
        if(currentSavingsOID != null) {
          System.out.println("_________________________________________________");
          System.out.println("Savings Account for :"+currentSavingsOID.toLong());
          System.out.println("Details are :"+savingsPC.toString());
          System.out.println("_________________________________________________");
          //          savingsList.add(new Long(currentSavingsOID.toLong()));
          savingsList.add(currentSavingsOID);
        }

      }
    } catch(Exception exception) {

      // Something bad happened.
      // If the transaction object was obtained
      if(transaction != null) {
        // Rollback the transaction
        transaction.rollback();
      }

      exception.printStackTrace();
    } finally {

      // Close the Persistence Manager
      if(pManager != null) {
        pManager.close();
      }

      // Release the Persistence Manager Factory
      if(pmFactory_ != null) {
        releasePMF(DATASTORE_NAME_, pmFactory_);
      }
    }
    return savingsList;
  }

  public static void main(String [] args) {
    Teller teller = new Teller();

    // try creating new accounts
    try {
      teller.createAccount("Gopalan Suresh Raj", 10000000, true);
    }
    catch (Exception exception) {
      exception.printStackTrace();
    }
    try {
      teller.createAccount("Athul Suresh Raj", 20000000, true);
    }
    catch (Exception exception) {
      exception.printStackTrace();
    }

    try {
      teller.createAccount("Gopalan Suresh Raj", 910000000, false);
    }
    catch (Exception exception) {
      exception.printStackTrace();
    }

    try {
      teller.createAccount("Athul Suresh Raj", 2000000000, false);
    }
    catch (Exception exception) {
      exception.printStackTrace();
    }

    List checkingAccountList = teller.listAllCheckingAccounts();
    List savingsAccountList = teller.listAllSavingsAccounts();

    // Try deleting the first 2 accounts
    if((checkingAccountList.size() > 0)&&(savingsAccountList.size() > 0)) {
      try {
        Oid checkingOid = (Oid)checkingAccountList.get(0);
        Oid savingsOid = (Oid)savingsAccountList.get(0);
        teller.deleteAccount(checkingOid.toLong());
        teller.deleteAccount(savingsOid.toLong());
        checkingAccountList.remove(checkingOid);
        savingsAccountList.remove(savingsOid);
      }
      catch (Exception exception) {
        exception.printStackTrace();
      }

    }

    // Try transfering money from one account to the other
    if((checkingAccountList.size() > 0)&&(savingsAccountList.size() > 0)) {
      try {
        Oid fromOid = (Oid)checkingAccountList.get(0);
        Oid toOid = (Oid)savingsAccountList.get(0);
        teller.transferMoney(fromOid.toLong(),
                             toOid.toLong(),
                             100);
      }
      catch (Exception exception) {
        exception.printStackTrace();
      }

    }

  }

}
