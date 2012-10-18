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

import java.util.Properties;
import java.util.ArrayList;
import java.util.Iterator;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.RemoveException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import com.hywy.pej.persistence.oidserver.Oid;

/**
 * This class illustrates calling a stateless Session Bean and performing
 * the following exercises:
 * <ul>
 * <li> Create a Teller
 * <li> Create Accounts using the Teller
 * <li> Transfer Money using the Teller
 * <li> Remove the Teller
 * </ul>
 *
 * @author Gopalan Suresh Raj
 */

public class Client {

  private static final String JNDI_NAME_ = "TellerHome";

  private String url_;
  private TellerHome home_;

  public Client(String url) throws NamingException {

    this.url_ = url;
    home_ = lookupHome();
  }

  /**
   * Runs this example from the command linamingException. Example:
   * <p>
   * <tt>java com.hywy.samples.ejb20.session.stateless.Client "t3://localhost:7001"</tt>
   * <p>
   * The parameters are optional, but if any are supplied,
   * they are interpreted in this order:
   * <p>
   * @param url URL such as "t3://localhost:7001" of Server
   */
  public static void main(String[] args) throws Exception {

    log("\nBeginning stateless.Client...\n");

    String url = "t3://localhost:7001";

    // Parse the argument list
    if (args.length != 1) {
      System.out.println("Usage: java com.hywy.samples.ejb20.session.stateless.Client t3://hostname:port");
      return;
    } else {
      url = args[0];
    }

    Client client = null;
    try {
      client = new Client(url);
    } catch (NamingException namingException) {
      System.exit(1);
    }

    try {
      client.example();
    } catch (Exception exception) {
      log("There was an exception while creating and using the Teller.");
      log("This indicates that there was a problem communicating with the server: "+exception);
    }

    log("\nEnd stateless.Client...\n");
  }

  /**
   * Runs this example.
   */
  public void example() throws CreateException, RemoteException, RemoveException {

    // create a Teller
    log("Creating a Teller");
    Teller teller = (Teller) narrow(home_.create(), Teller.class);

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


    // remove the Teller
    log("Removing the teller");
    teller.remove();

  }

  /**
   * RMI/IIOP clients should use this narrow function
   */
  private Object narrow(Object reference, Class classReference) {
    return PortableRemoteObject.narrow(reference, classReference);
  }

  /**
   * Lookup the EJBs home_ in the JNDI tree
   */
  private TellerHome lookupHome() throws NamingException {
    // Lookup the beans home_ using JNDI
    Context context = getInitialContext();

    try {
      Object home = context.lookup(JNDI_NAME_);
      return (TellerHome) narrow(home, TellerHome.class);
    } catch (NamingException namingException) {
      log("The client was unable to lookup the EJBHome.  Please make sure ");
      log("that you have deployed the ejb with the JNDI name "+JNDI_NAME_+" on the WebLogic server at "+url_);
      throw namingException;
    }
  }

  /**
   * Using a Properties object will work on JDK 1.1.x and Java2
   * clients
   */
  private Context getInitialContext() throws NamingException {

    try {
      // Get an InitialContext
      Properties properties = new Properties();
      properties.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
      properties.put(Context.PROVIDER_URL, url_);
      return new InitialContext(properties);
    } catch (NamingException namingException) {
      log("We were unable to get a connection to the WebLogic server at "+url_);
      log("Please make sure that the server is running.");
      log(namingException.toString());
      throw namingException;
    }
  }

  /**
  * This is the Java2 version to get an InitialContext.
  * This version relies on the existence of a jndi.properties file in
  * the application's classpath.
  *
  */
  //    private static Context getInitialContext()
  //      throws NamingException
  //    {
  //      return new InitialContext();
  //    }

  private static void log(String string) {
    System.out.println(string);
  }

}
