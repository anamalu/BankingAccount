/**
 * The following example illustrates an RMI Server
 *
 * author: Gopalan Suresh Raj
 * Copyright (c), 2002. All Rights Reserved.
 * URL: http://gsraj.tripod.com/
 * email: gopalan@gmx.net
 */

package com.hywy.samples.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

import java.util.ArrayList;

/**
 * This is an interface to a component that
 * functions as a Teller to a Bank
 *
 * @author Gopalan Suresh Raj
 */
public interface TellerRemote extends Remote {

  /**
   * Create Checking or Savings Account
   */
  void createAccount(String customerNames,
                     int startingBalance,
                     boolean isCheckingAccount) throws RemoteException;

  /**
   * Delete and close the account
   */
  void deleteAccount(long accountNumber) throws RemoteException;

  /**
   * Transfer Money
   */
  boolean transferMoney(long fromAccountNumber,
                        long toAccountNumber,
                        int amount) throws RemoteException;
  /**
   * List out all Checking accounts found
   */
  ArrayList listAllCheckingAccounts() throws RemoteException;

  /**
   * List out all Savings accounts found
   */
  ArrayList listAllSavingsAccounts() throws RemoteException;

}