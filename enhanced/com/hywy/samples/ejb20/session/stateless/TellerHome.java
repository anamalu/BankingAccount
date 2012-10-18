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
import javax.ejb.CreateException;
import javax.ejb.EJBHome;

/**
 * This interface is the home interface for the TellerBean.java
 * 
 * A home interface may support one or more create
 * methods, which must correspond to methods named "ejbCreate" in the EJBean.
 *
 * @author Gopalan Suresh Raj.
 */
public interface TellerHome extends EJBHome {

  /**
   * This method corresponds to the ejbCreate method in the bean
   * "TellerBean.java".
   * The parameter sets of the two methods are identical. When the client calls
   * <code>TellerHome.create()</code>, the container
   * allocates an instance of the EJBean and calls <code>ejbCreate()</code>.
   *
   * @return                  Teller
   * @exception               RemoteException if there is
   *                          a communications or systems failure
   * @exception               CreateException
   *                          if there is a problem creating the bean
   * @see                     com.hywy.samples.ejb20.session.stateless.TellerBean
   */
  Teller create() throws CreateException, RemoteException;
}
