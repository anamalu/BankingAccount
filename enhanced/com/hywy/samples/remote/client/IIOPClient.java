/**
 * The following example illustrates an RMI-IIOP Client
 *
 * author: Gopalan Suresh Raj
 * Copyright (c), 2002. All Rights Reserved.
 * URL: http://gsraj.tripod.com/
 * email: gopalan@gmx.net
 */
 /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 // To Run
 // C:\java\jdk1.3\bin\java -classpath .;C:\PEJ\lib\jdo.jar;C:\PEJ\lib\j2ee.jar;C:\PEJ\lib\pej.jar; com.hywy.samples.remote.client.IIOPClient
 /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
package com.hywy.samples.remote.client;

import java.util.Properties;
import java.util.ArrayList;
import java.util.Iterator;

import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

import com.hywy.pej.persistence.oidserver.Oid;

import com.hywy.samples.remote.TellerRemote;
import com.hywy.samples.remote.TellerRemoteImpl;
import com.hywy.samples.remote.iiop.TellerServer;

/**
 * This Client talks to the Bank Teller to get banking services
 *
 * Pre-requisites: You will need to have the COS naming server
 * and the TellerRemote Server up and running before you can
 * invoke them from this client.
 *
 * @author Gopalan Suresh Raj
 */
public class IIOPClient {

  static final String CONTEXT_NAME = "java.naming.factory.initial";
  static final String IIOP_STRING  = "com.sun.jndi.cosnaming.CNCtxFactory";

  static final String URL_NAME = "java.naming.provider.url";
  static final String IIOP_URL_STRING  = "iiop://localhost:1000";

  /**
   * Entry Point to this Application
   */
  public static void main( String[] args ) {

    try {
      // Create the IIOP Initial Context
      Properties iiopProperties = new Properties();
      iiopProperties.put( TellerServer.CONTEXT_NAME,
                          TellerServer.IIOP_STRING );
      iiopProperties.put( TellerServer.URL_NAME,
                          TellerServer.IIOP_URL_STRING );
      InitialContext iiopContext = new InitialContext( iiopProperties );

      // Obtain a reference to the Servant Object
      TellerRemote teller = (TellerRemote) PortableRemoteObject.narrow( iiopContext.lookup ( "TELLER" ),
                                                                        TellerRemote.class );

      /////////////////////////////
      // Invoke the Servant
      /////////////////////////////

      // try creating new accounts
      try {
        System.out.println("Create Checking Account for Gopalan Suresh Raj");
        teller.createAccount("Gopalan Suresh Raj", 10000000, true);
      }
      catch (Exception exception) {
        exception.printStackTrace();
      }
      try {
        System.out.println("Create Checking Account for Athul Suresh Raj");
        teller.createAccount("Athul Suresh Raj", 20000000, true);
      }
      catch (Exception exception) {
        exception.printStackTrace();
      }

      try {
        System.out.println("Create Savings Account for Gopalan Suresh Raj");
        teller.createAccount("Gopalan Suresh Raj", 910000000, false);
      }
      catch (Exception exception) {
        exception.printStackTrace();
      }

      try {
        System.out.println("Create Savings Account for Athul Suresh Raj");
        teller.createAccount("Athul Suresh Raj", 2000000000, false);
      }
      catch (Exception exception) {
        exception.printStackTrace();
      }

      Iterator iterator = null;
      Object oid = null;

      // List of Checking accounts
      ArrayList checkingAccountList = teller.listAllCheckingAccounts();
      System.out.println("The size of checkingAccountList is :"+checkingAccountList.size());
      iterator = checkingAccountList.iterator();
      System.out.println("_______________________________________");
      System.out.println("     List of Checking Accounts");
      System.out.println("_______________________________________");
      while(iterator.hasNext()) {
        oid = iterator.next();
        if(oid != null) {
          System.out.println("Checking Account Number :"+oid.toString());
        }
      }
      System.out.println("_______________________________________");

      // List of Savings accounts
      ArrayList savingsAccountList = teller.listAllSavingsAccounts();
      System.out.println("The size of savingsAccountList is :"+savingsAccountList.size());
      iterator = savingsAccountList.iterator();
      System.out.println("_______________________________________");
      System.out.println("     List of Savings Accounts");
      System.out.println("_______________________________________");
      while(iterator.hasNext()) {
        oid = iterator.next();
        if(oid != null) {
          System.out.println("Savings Account Number :"+oid.toString());
        }
      }
      System.out.println("_______________________________________");

      Long longValue = null;
      // Try deleting the first 2 accounts
      if((checkingAccountList.size() > 0)&&(savingsAccountList.size() > 0)) {
        try {
          Oid checkingOid = (Oid)checkingAccountList.get(0);
          if(checkingOid != null) {
            System.out.println("Delete Checking Account Number :"+checkingOid.toString());
            teller.deleteAccount(checkingOid.toLong());
            checkingAccountList.remove(checkingOid);
            System.out.println("The size of checkingAccountList is now :"+checkingAccountList.size());
          }
          Oid savingsOid = (Oid)savingsAccountList.get(0);
          if(savingsOid != null) {
            System.out.println("Delete Savings Account Number :"+savingsOid.toString());
            teller.deleteAccount(savingsOid.toLong());
            savingsAccountList.remove(savingsOid);
            System.out.println("The size of savingsAccountList is now :"+savingsAccountList.size());
          }
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
          if((fromOid != null) && (toOid != null)) {
            System.out.println("Transfer Money - 100 from Checking Account :"+
                               fromOid.toString()+
                               " to Savings Account :"+
                               toOid.toString());
            boolean result = teller.transferMoney(fromOid.toLong(),
                                                  toOid.toLong(),
                                                  100);
            if(result == true) {
              System.out.println("The Money Transfer was a SUCCESS");
            }
            else {
              System.out.println("The Money Transfer was a FAILURE");
            }
          }
        }
        catch (Exception exception) {
          exception.printStackTrace();
        }
      }
    }
    catch ( Exception exception ) {
      exception.printStackTrace ();
    }

  }
}
