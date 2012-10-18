/**
 * The following example illustrates an RMI-IIOP Server
 *
 * author: Gopalan Suresh Raj
 * Copyright (c), 2002. All Rights Reserved.
 * URL: http://gsraj.tripod.com/
 * email: gopalan@gmx.net
 */
 ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 // To Run
 // start tnameserv -ORBInitialPort 1000
 // start java -classpath .;C:\PEJ\lib\jdo.jar;C:\PEJ\lib\j2ee.jar;C:\PEJ\lib\pej.jar; com.hywy.samples.remote.iiop.TellerServer
 ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

package com.hywy.samples.remote.iiop;

import java.util.Properties;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

import com.hywy.samples.remote.TellerRemoteImpl;

/**
 * Creates a Server and binds the RMI Servant with the IIOP Registry
 *
 * Pre-requisites: You will need to have the COS naming server
 * running for the registration code to work.
 *
 * @author Gopalan Suresh Raj
 */
public class TellerServer {

  public static final String CONTEXT_NAME = "java.naming.factory.initial";
  public static final String IIOP_STRING  = "com.sun.jndi.cosnaming.CNCtxFactory";

  public static final String URL_NAME = "java.naming.provider.url";
  public static final String IIOP_URL_STRING  = "iiop://localhost:1000";

  /**
   * Entry Point to this application
   */
  public static void main(String[] args) {
    try {
      // Create the Object
      TellerRemoteImpl myObject = new TellerRemoteImpl();

      // Create the IIOP Initial Context
      Properties iiopProperties = new Properties();
      iiopProperties.put( TellerServer.CONTEXT_NAME,
                          TellerServer.IIOP_STRING );
      iiopProperties.put( TellerServer.URL_NAME,
                          TellerServer.IIOP_URL_STRING );
      InitialContext iiopContext = new InitialContext( iiopProperties );

      // Bind the object to the IIOP registry
      iiopContext.rebind( "TELLER", myObject );

      System.out.println( "TellerServer bound in the IIOP Registry as TELLER and is up and ready for eCommerce..." );
    }
    catch ( Exception exception ) {
      exception.printStackTrace ();
    }
  }
}
