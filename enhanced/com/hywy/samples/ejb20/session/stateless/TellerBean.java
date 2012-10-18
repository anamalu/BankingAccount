/**
 * The following example illustrates
 * a Session Facade to a JDO object
 *
 * author: Gopalan Suresh Raj
 * Copyright (c), 2002. All Rights Reserved.
 * URL: http://gsraj.tripod.com/
 * email: gopalan@gmx.net
 */

package com.hywy.samples.ejb20.session.stateless;

import java.util.ArrayList;
import java.util.Iterator;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.hywy.samples.bank.Teller;

/**
 * TellerBean is a stateless Session Bean. This bean illustrates:
 * <ul>
 * <li> No persistence of state between calls to the Session Bean
 * <li> Looking up values from the Environment
 * </ul>
 *
 * @author Gopalan Suresh Raj
 */
public class TellerBean implements SessionBean {

  private static final boolean VERBOSE_ = true;
  private SessionContext context_;
  private int transactionLimit_;
  /** The Teller reference */
  private Teller teller_;

  // You might also consider using WebLogic's log service
  private void log(String string) {
    if (VERBOSE_) System.out.println(string);
  }

  /**
   * This method is required by the EJB Specification,
   * but is not used by this example.
   *
   */
  public void ejbActivate() {
    log("ejbActivate called");
  }

  /**
   * This method is required by the EJB Specification,
   * but is not used by this example.
   *
   */
  public void ejbRemove() {
    log("ejbRemove called");
  }

  /**
   * This method is required by the EJB Specification,
   * but is not used by this example.
   *
   */
  public void ejbPassivate() {
    log("ejbPassivate called");
  }

  /**
   * Sets the session context_.
   *
   * @param context_ SessionContext Context for session
   */
  public void setSessionContext(SessionContext context_) {
    log("setSessionContext called");
    this.context_ = context_;
  }

  /**
   * This method corresponds to the create method in the home interface
   * "TellerHome.java".
   * The parameter sets of the two methods are identical. When the client calls
   * <code>TellerHome.create()</code>, the containamingExceptionr allocates an instance of
   * the EJBean and calls <code>ejbCreate()</code>.
   *
   * @exception               javax.ejb.CreateException if there is
   *                          a communications or systems failure
   * @see                     com.hywy.samples.ejb20.session.stateless.Teller
   */
  public void ejbCreate () throws CreateException {
    log("ejbCreate called");

    try {
      InitialContext initialContext = new InitialContext();

      Integer limit = (Integer) initialContext.lookup("java:/comp/env/transactionLimit_");

      transactionLimit_ = limit.intValue();
    } catch (NamingException namingException) {
      throw new CreateException("Failed to find environment value "+namingException);
    }
    this.teller_ = new com.hywy.samples.bank.Teller();
  }

  /**
   * Create Checking or Savings Account
   */
  public void createAccount(String customerNames,
                            int startingBalance,
                            boolean isCheckingAccount) {
    final String THIS = "TellerBean::createAccount(customerNames, startingBalance, isCheckingAccount) :";
    try {
      this.teller_.createAccount(customerNames, startingBalance, isCheckingAccount);
    }
    catch(Exception exception) {
      log(THIS+exception.toString());
    }
  }

  /**
   * Delete and close the account
   */
  public void deleteAccount(long accountNumber) {
    final String THIS = "TellerBean::deleteAccount(accountNumber) :";
    try {
      this.teller_.deleteAccount(accountNumber);
    }
    catch(Exception exception) {
      log(THIS+exception.toString());
    }
  }

  /**
   * Transfer Money
   */
  public boolean transferMoney(long fromAccountNumber,
                               long toAccountNumber,
                               int amount) {
    final String THIS = "TellerBean::transferMoney(fromAccountNumber,toAccountNumber,amount) :";
    boolean result = false;
    if (amount > transactionLimit_) {
      log(THIS+"Attempt to transer "+amount+" is greater than the limit of "+transactionLimit_);
      amount = transactionLimit_;
    }
    log(THIS+"Attempting to Transfer "+amount+" from Account No: "+fromAccountNumber+ " to Account No: "+toAccountNumber);
    try {
      result = this.teller_.transferMoney(fromAccountNumber, toAccountNumber, amount);
    }
    catch(Exception exception) {
      log(THIS+exception.toString());
    }
    return result;
  }

  /**
   * List out all Checking accounts found
   */
  public ArrayList listAllCheckingAccounts() {
    final String THIS = "TellerBean::listAllCheckingAccounts() :";
    ArrayList list = null;
    Iterator iterator = null;
    try {
      list = this.teller_.listAllCheckingAccounts();
    }
    catch(Exception exception) {
      log(THIS+exception.toString());
    }
    if(list != null) {
      iterator = list.iterator();
      while(iterator.hasNext()) {
        log(THIS+iterator.next().toString());
      }
    }
    return list;
  }

  /**
   * List out all Savings accounts found
   */
  public ArrayList listAllSavingsAccounts() {
    final String THIS = "TellerBean::listAllSavingsAccounts() :";
    ArrayList list = null;
    Iterator iterator = null;
    try {
      list = this.teller_.listAllSavingsAccounts();
    }
    catch(Exception exception) {
      log(THIS+exception.toString());
    }
    if(list != null) {
      iterator = list.iterator();
      while(iterator.hasNext()) {
        log(THIS+iterator.next().toString());
      }
    }
    return list;
  }

}
