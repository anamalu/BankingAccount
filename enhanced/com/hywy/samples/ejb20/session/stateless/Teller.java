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

import java.rmi.RemoteException;
import javax.ejb.EJBObject;

import java.util.ArrayList;

/**
 * The methods in this interface are the public face of TellerBean.
 * The signatures of the methods are identical to those of the EJBean, except
 * that these methods throw a java.rmi.RemoteException.
 * Note that the EJBean does not implement this interface. The corresponding
 * code-generated EJBObject, TellerBeanE, implements this interface and
 * delegates to the bean.
 *
 * @author Gopalan Suresh Raj.
 */
public interface Teller extends EJBObject {

  /**
   * Create Checking or Savings Account
   */
  public void createAccount(String customerNames,
                            int startingBalance,
                            boolean isCheckingAccount) throws RemoteException;

  /**
   * Delete and close the account
   */
  public void deleteAccount(long accountNumber) throws RemoteException;

  /**
   * Transfer Money
   */
  public boolean transferMoney(long fromAccountNumber,
                               long toAccountNumber,
                               int amount) throws RemoteException;
  /**
   * List out all Checking accounts found
   */
  public ArrayList listAllCheckingAccounts() throws RemoteException;

  /**
   * List out all Savings accounts found
   */
  public ArrayList listAllSavingsAccounts() throws RemoteException;

}
