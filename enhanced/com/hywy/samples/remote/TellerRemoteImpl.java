/**
 * The following example illustrates an RMI-IIOP Servant
 *
 * author: Gopalan Suresh Raj
 * Copyright (c), 2002. All Rights Reserved.
 * URL: http://gsraj.tripod.com/
 * email: gopalan@gmx.net
 */
 ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 // To Compile
 // cd C:\run\projects\bank\enhanced
 // C:\java\jdk1.3\bin\rmic -classpath . -iiop -idl com.hywy.samples.remote.TellerRemoteImpl
 // copy C:\run\projects\bank\enhanced\com\hywy\samples\remote\_TellerRemote_Stub.class C:\run\projects\bank\enhanced\com\hywy\samples\remote\client\
 // copy C:\run\projects\bank\enhanced\com\hywy\samples\remote\_TellerRemoteImpl_Tie.class C:\run\projects\bank\enhanced\com\hywy\samples\remote\client\
 ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
package com.hywy.samples.remote;

import java.util.ArrayList;
import java.util.Iterator;
import java.rmi.RemoteException;

import javax.rmi.PortableRemoteObject;

import com.hywy.samples.bank.Teller;

/**
 * This component looks up the price of a given Stock Symbol
 *
 * @author Gopalan Suresh Raj
 */
public class TellerRemoteImpl extends PortableRemoteObject
  implements TellerRemote {

  /** The Teller reference */
  private Teller teller_;

  /**
   * Public No argument constructor
   */
  public TellerRemoteImpl() throws RemoteException {
    // Invoke the superclass to export this object
    super();
    this.teller_ = new Teller();
  }

  /**
   * Create Checking or Savings Account
   */
  public void createAccount(String customerNames,
                            int startingBalance,
                            boolean isCheckingAccount) throws RemoteException {
    try {
      this.teller_.createAccount(customerNames, startingBalance, isCheckingAccount);
    }
    catch(Exception exception) {
      throw new RemoteException(exception.toString());
    }
  }

  /**
   * Delete and close the account
   */
  public void deleteAccount(long accountNumber) throws RemoteException {
    try {
      this.teller_.deleteAccount(accountNumber);
    }
    catch(Exception exception) {
      throw new RemoteException(exception.toString());
    }
  }

  /**
   * Transfer Money
   */
  public boolean transferMoney(long fromAccountNumber,
                               long toAccountNumber,
                               int amount) throws RemoteException {
    boolean result = false;
    try {
      result = this.teller_.transferMoney(fromAccountNumber, toAccountNumber, amount);
    }
    catch(Exception exception) {
      throw new RemoteException(exception.toString());
    }
    return result;
  }

  /**
   * List out all Checking accounts found
   */
  public ArrayList listAllCheckingAccounts() throws RemoteException {
    String THIS = "listAllCheckingAccounts() - ";
    ArrayList list = null;
    Iterator iterator = null;
    try {
      list = this.teller_.listAllCheckingAccounts();
    }
    catch(Exception exception) {
      throw new RemoteException(exception.toString());
    }
    if(list != null) {
      iterator = list.iterator();
      while(iterator.hasNext()) {
        System.out.println(THIS+iterator.next().toString());
      }
    }
    return list;
  }

  /**
   * List out all Savings accounts found
   */
  public ArrayList listAllSavingsAccounts() throws RemoteException {
    String THIS = "listAllSavingsAccounts() - ";
    ArrayList list = null;
    Iterator iterator = null;
    try {
      list = this.teller_.listAllSavingsAccounts();
    }
    catch(Exception exception) {
      throw new RemoteException(exception.toString());
    }
    if(list != null) {
      iterator = list.iterator();
      while(iterator.hasNext()) {
        System.out.println(THIS+iterator.next().toString());
      }
    }
    return list;
  }

}
