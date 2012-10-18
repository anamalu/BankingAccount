/*
 * Enhanced JDO class Account.java
 *
 */
package com.hywy.samples.bank;

import java.util.Date;
import java.util.Calendar;
import java.util.TimeZone;
import java.io.Serializable;
import javax.jdo.JDOUnsupportedOptionException;
import javax.jdo.JDOFatalInternalException;
import javax.jdo.PersistenceManager;
import javax.jdo.spi.JDOImplHelper;
import javax.jdo.spi.PersistenceCapable;
import javax.jdo.spi.StateManager;
import javax.jdo.InstanceCallbacks;

/**
*  Enhanced Class Description for Account.java
*
*  @author: Administrator
*  @created date: May 23, 2002
*  @last modified date:
*  @version: 0.1
*
*/
public class Account
            implements Serializable, PersistenceCapable, InstanceCallbacks
{
    /////////////////////////////////////////////////////////////
    // The Original Account User's Class Contents
    /////////////////////////////////////////////////////////////



    /** Attribute   accountType_ */
    protected int accountType_  ;


    /** Attribute   customerNames_ */
    protected String customerNames_  ;


    /** Attribute   balance_ */
    protected int balance_  ;


    /** Attribute   openedOn_ */
    protected java.util.Date openedOn_  ;


    /** Attribute   INVALID_ACCOUNT_ */
    protected static int INVALID_ACCOUNT_  = -1  ;


    /** Attribute   CHECKING_ACCOUNT_ */
    protected static int CHECKING_ACCOUNT_  = 100  ;


    /** Attribute   SAVINGS_ACCOUNT_ */
    protected static int SAVINGS_ACCOUNT_  = 200  ;



    /** Constructor */
    public Account()
    {
        super();
    ///////////////////////////////////////////
    //// Start of User's Business Logic    ////
    ///////////////////////////////////////////

    this._setAccountType_(INVALID_ACCOUNT_);
    ///////////////////////////////////////////
    ////   End of User's Business Logic    ////
    ///////////////////////////////////////////
    }



    /** Methods Description */
    public int  create(   String customerNames  ,  int startingBalance   )  throws   Exception
    {
        //return 0;
    ///////////////////////////////////////////
    //// Start of User's Business Logic    ////
    ///////////////////////////////////////////

    if(this._getAccountType_() == INVALID_ACCOUNT_) { throw new Exception("Invalid Account Type :"+this._getAccountType_());
    }
    if(startingBalance < 0) {
      throw new Exception("Invalid Starting Balance (in cents):" + startingBalance);
    }

    this._setCustomerNames_(customerNames);

    this._setBalance_(startingBalance);

    this._setOpenedOn_(Calendar.getInstance(TimeZone.getTimeZone("GMT")).getTime());

    return this._getBalance_();
    ///////////////////////////////////////////
    ////   End of User's Business Logic    ////
    ///////////////////////////////////////////
    }

    /** Methods Description */
    public int  credit(   int amount   )  throws   Exception
    {
        //return 0;
    ///////////////////////////////////////////
    //// Start of User's Business Logic    ////
    ///////////////////////////////////////////

    if(this._getAccountType_() == INVALID_ACCOUNT_) { throw new Exception("Invalid Account Type :"+this._getAccountType_());
    }
    if(amount < 0) {
      throw new Exception("Invalid Amount (in cents) :"+amount);
    }

    this._setBalance_(this._getBalance_()+amount);

    return this._getBalance_();
    ///////////////////////////////////////////
    ////   End of User's Business Logic    ////
    ///////////////////////////////////////////
    }

    /** Methods Description */
    public int  debit(   int amount   )  throws   Exception
    {
        //return 0;
    ///////////////////////////////////////////
    //// Start of User's Business Logic    ////
    ///////////////////////////////////////////

    if(this._getAccountType_() == INVALID_ACCOUNT_) { throw new Exception("Invalid Account Type :"+this._getAccountType_());
    }
    if(amount < 0) {
      throw new Exception("Invalid Amount (in cents) :"+amount);
    }

    this._setBalance_(this._getBalance_()-amount);

    return this._getBalance_();
    ///////////////////////////////////////////
    ////   End of User's Business Logic    ////
    ///////////////////////////////////////////
    }

    /** Setter Method for field --> accountType_*/
    public void  setAccountType_(   int newAccountType_   )
    {
        Account.jdoSetCom_Hywy_Samples_Bank_Account_accountType_(this, newAccountType_ );
    }

    /** Accessor Method for field --> accountType_ */
    public int  getAccountType_(  )
    {
        return Account.jdoGetCom_Hywy_Samples_Bank_Account_accountType_(this);
    }

    /** Setter Method for field --> customerNames_*/
    public void  setCustomerNames_(   String newCustomerNames_   )
    {
        Account.jdoSetCom_Hywy_Samples_Bank_Account_customerNames_(this, newCustomerNames_ );
    }

    /** Accessor Method for field --> customerNames_ */
    public String  getCustomerNames_(  )
    {
        return Account.jdoGetCom_Hywy_Samples_Bank_Account_customerNames_(this);
    }

    /** Setter Method for field --> balance_*/
    public void  setBalance_(   int newBalance_   )
    {
        Account.jdoSetCom_Hywy_Samples_Bank_Account_balance_(this, newBalance_ );
    }

    /** Accessor Method for field --> balance_ */
    public int  getBalance_(  )
    {
        return Account.jdoGetCom_Hywy_Samples_Bank_Account_balance_(this);
    }

    /** Setter Method for field --> openedOn_*/
    public void  setOpenedOn_(   java.util.Date newOpenedOn_   )
    {
        Account.jdoSetCom_Hywy_Samples_Bank_Account_openedOn_(this, newOpenedOn_ );
    }

    /** Accessor Method for field --> openedOn_ */
    public java.util.Date  getOpenedOn_(  )
    {
        return Account.jdoGetCom_Hywy_Samples_Bank_Account_openedOn_(this);
    }

    /** Default toString() method */
    public String  toString(  )
    {
        StringBuffer strBuff = new StringBuffer();

    strBuff.append(" Account: ");

    strBuff.append(" accountType_= " + this._getAccountType_());

    strBuff.append(" customerNames_= " + this._getCustomerNames_());

    strBuff.append(" balance_= " + this._getBalance_());

    strBuff.append(" openedOn_= " + this._getOpenedOn_());


    return strBuff.toString();
    }



    /////////////////////////////////////////////////////////////
    //  HYWY Modifications for JDO Compatible Enhancements
    //  to make this class Persistence Capable
    /////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////
    //  JDO Generated Fields
    //  (Conforms to section 20.18.1 of the JDO specification)
    /////////////////////////////////////////////////////////////
    /**
     *
     */
    protected transient StateManager jdoStateManager = null;

    /**
     *
     */
    protected transient byte jdoFlags = READ_WRITE_OK;

    /**
     *
     */
    private static int jdoInheritedFieldCount = 0;

    /////////////////////////////////////////////////////////////////
    //  JDO Generated jdoGetManagedFieldCount
    //  (Conforms to Section 20.18.7 of the JDO Specification)
    //  The generated method returns the number of managed
    //  fields in this class plus the number of inherited managed
    //  fields. This method is expected to be executed only during
    //  class loading of the subclasses.
    /////////////////////////////////////////////////////////////////
    /**
     * Account have 4 persistentcapable fields
     */
    protected static int jdoGetManagedFieldCount()
    {
        return jdoInheritedFieldCount + 4;
    }

    /////////////////////////////////////////////////////////////////
    //  7.12 JDO identity handling
    //  This method creates a new instance of the class used
    //  for JDO identity. It is only intended for application identity.
    //  If the class has been enhanced for datastore identity,
    //  then null is returned. If the class is abstract,
    //  null is returned.
    /////////////////////////////////////////////////////////////////
    /**
     * Account's JDO identity is datastore
     *  and  is not  an abstract class
     *
     * @return Object - JDO ObjectId instance
     */
    public Object jdoNewObjectIdInstance()
    {
        return null;

    }

    /**
     * Account's JDO identity is datastore
     *  and  is not  abstract class
     *
     * @return Object jdo ObjectId instance
     */
    public Object jdoNewObjectIdInstance(String stringObject)
    {
            return null;
        }

    /** See JDO specification 20.18.3
     *  Get the PersistenceManager
     *
     * @return PersistenceManager
     */
     public PersistenceManager jdoGetPersistenceManager()
     {
        return jdoStateManager==null?null:jdoStateManager.getPersistenceManager(this);
     }

    /////////////////////////////////////////////////////////////////
    //  List out the field names in an array
    /////////////////////////////////////////////////////////////////
    /**
     * 0 persistentcapable fields are defined in the super class: .class
     * 4 persistentcapable fields are defined in the class: Account.class
     */
     private static String[] jdoFieldNames = new String[]  { "accountType_","balance_","customerNames_","openedOn_" };

    /**
     *  4   persistentcapable field type
     */
     private static Class[] jdoFieldTypes = new Class[] {   int.class,  int.class,  java.lang.String.class,  java.util.Date.class };

    /**
     *  4   persistentcapable fields are defined in the class: Account.class
     */
     private static byte[] jdoFieldFlags = new byte[] {  CHECK_READ+CHECK_WRITE, CHECK_READ+CHECK_WRITE, CHECK_READ+CHECK_WRITE, CHECK_READ+CHECK_WRITE };

    ////////////////////////////////////////////////////////////////////
    // JDO Generated static initializer
    // (Conforms to Section 20.18.2 of the JDO Specification)
    //
    // The generated static initializer constructs the values for
    // jdoFieldNames and jdoFieldTypes and calls the static method
    // in JDOImplHelper to register itself.
    //
    // Register the Impl class with the JDOImplHelper
    ///////////////////////////////////////////////////////////////////
    static
    {
                JDOImplHelper.registerClass (
                         Account.class,
                         jdoFieldNames,
                         jdoFieldTypes,
                         jdoFieldFlags,
                         null,
                         new Account());
    }

    ///////////////////////////////////////////////////////////////////
    // JDO Generated jdoReplaceStateManager
    // (Conforms to Section 20.18.4 of the JDO Specification)
    //
    // The generated method asks the current StateManager to approve the
    // change or validates the caller's authority to set the state.
    ////////////////////////////////////////////////////////////////////////////
    public synchronized void jdoReplaceStateManager(StateManager stateManager)
    {
        if (null != this.jdoStateManager)
        {
            this.jdoStateManager = jdoStateManager.replacingStateManager(this, stateManager);
        }
        else
        {
            SecurityManager securityManager = System.getSecurityManager();
            if (null != securityManager)
            {
                securityManager.checkPermission (new javax.jdo.spi.JDOPermission ("setStateManager"));
            }
            this.jdoStateManager = stateManager;
            this.jdoFlags = LOAD_REQUIRED;
        }
    }

    /*
     *
     */
    public void jdoReplaceFlags()
    {
        jdoFlags = jdoStateManager.replacingFlags(this);
    }

    /*
     *
     */
    public void jdoMakeDirty(String fieldName)
    {
        if (jdoStateManager != null) jdoStateManager.makeDirty(this, fieldName);
    }

    ////////////////////////////////////////////////////////////////////////////
    // JDO Generated jdoGetObjectId and jdoGetTransactionalObjectId
    // (Conforms to Section 20.18.3 of the JDO Specification)
    //
    // These generated methods delegate to the StateManager.
    ////////////////////////////////////////////////////////////////////////////
    /*
     *
     */
    public Object jdoGetObjectId()
    {
        return jdoStateManager==null?null:jdoStateManager.getObjectId(this);
    }

    /*
     *
     */
    public Object jdoGetTransactionalObjectId()
    {
        return jdoStateManager==null?null:jdoStateManager.getTransactionalObjectId(this);
    }

    ///////////////////////////////////////////////////////////////////////////
    // JDO Generated interrogatives
    // (Conforms to Section 20.18.3 of the JDO Specification)
    ///////////////////////////////////////////////////////////////////////////

    /*
     *
     */
    public boolean jdoIsDirty()
    {
        return jdoStateManager==null?false:jdoStateManager.isDirty(this);
    }

    /*
     *
     */
    public boolean jdoIsTransactional()
    {
        return jdoStateManager==null?false:jdoStateManager.isTransactional(this);
    }

    /*
     *
     */
    public boolean jdoIsPersistent()
    {
        return jdoStateManager==null?false:jdoStateManager.isPersistent(this);
    }

    /*
     *
     */
    public boolean jdoIsNew()
    {
        return jdoStateManager==null?false:jdoStateManager.isNew(this);
    }

    /*
     *
     */
    public boolean jdoIsDeleted()
    {
        return jdoStateManager==null?false:jdoStateManager.isDeleted(this);
    }

    ///////////////////////////////////////////////////////////////////////////
    // JDO Generated jdoNewInstance helpers
    // (Conforms to Section 20.18.6 of the JDO Specification)
    //
    // The first generated helper assigns the value of the passed parameter
    // to the jdoStateManager field.
    ///////////////////////////////////////////////////////////////////////////
    /*
     *
     */
    public PersistenceCapable jdoNewInstance(StateManager stateManager)
    {
        /////////////////////////////////////////////////////////////////
        // Invoke an instance of the Impl class and return its instance
        /////////////////////////////////////////////////////////////////
        Account _jdoInstance_ = new Account();
        _jdoInstance_.jdoStateManager = stateManager;
        _jdoInstance_.jdoFlags = LOAD_REQUIRED;
        return _jdoInstance_;
    }

    /*
     *
     */
    public PersistenceCapable jdoNewInstance(StateManager stateManager, Object oid)
    {
        throw new JDOFatalInternalException ("Not Application Identity");
    }

    ///////////////////////////////////////////////////////////////////////////
    // JDO Generated jdoCopyKeyFieldsToObjectId
    // (Conforms to Section 20.20 of the JDO Specification)
    //
    // The generated methods copy key field values from the PersistenceCapable
    // instance or from the ObjectIdFieldHandler.
    ///////////////////////////////////////////////////////////////////////////
    /*
     *
     */
    public void jdoCopyKeyFieldsToObjectId(Object oid)
    {
        throw new JDOFatalInternalException ("Not Application Identity");
    }

    /*
     *
     */
    public void jdoCopyKeyFieldsToObjectId(PersistenceCapable persistenceCapable, Object oid)
    {
        throw new JDOFatalInternalException ("Not Application Identity");
    }

    /*
     *
     */
    public void jdoCopyKeyFieldsToObjectId(PersistenceCapable.ObjectIdFieldManager fieldManager, Object oid)
    {
        throw new JDOFatalInternalException ("Not Application Identity");
    }

    /*
     *
     */
    public void jdoCopyKeyFieldsToObjectId(PersistenceCapable.ObjectIdFieldSupplier supplier, Object oid)
    {
        throw new JDOFatalInternalException ("Not Application Identity");
    }

    /*
     *
     */
    public void jdoCopyKeyFieldsFromObjectId(PersistenceCapable.ObjectIdFieldManager fieldManager, Object oid)
    {
        throw new JDOFatalInternalException ("Not Application Identity");
    }

    /*
     *
     */
    public void jdoCopyKeyFieldsFromObjectId(PersistenceCapable.ObjectIdFieldConsumer consumer, Object oid)
    {
        throw new JDOFatalInternalException ("Not Application Identity");
    }

    ///////////////////////////////////////////////////////////////////////////
    // JDO Generated jdoProvideField and jdoProvideFields
    // (Conforms to Section 20.18.11 of the JDO Specification)
    //
    // The generated jdoProvideField gives the current value of one field
    // to the StateManager. This method is called by the StateManager whenever
    // it wants to get the value of a field in the instance, for example to store
    // the field in the data store. The enhancer by default will generate the
    // jdoProvideField and jdoProvideFields
    ///////////////////////////////////////////////////////////////////////////
    /*
     *
     */
    public void jdoProvideField(int fieldNumber)
    {
        final String THIS = "jdoProvideField(fieldNumber): ";
        switch (fieldNumber)
        {
            case 0:
                jdoStateManager.providedIntField(this, fieldNumber, _getAccountType_2());
                break;

            case 1:
                jdoStateManager.providedIntField(this, fieldNumber, _getBalance_2());
                break;

            case 2:
                jdoStateManager.providedStringField(this, fieldNumber, _getCustomerNames_2());
                break;

            case 3:
                jdoStateManager.providedObjectField(this, fieldNumber, _getOpenedOn_2());
                break;

            default:
                throw new IllegalArgumentException(THIS+"invalid field number: " + fieldNumber);
        }
    }

    /*
     *
     */
    public void jdoProvideFields(int fields[])
    {
        int numberOfFields = fields.length;
        for (int index = 0; index < numberOfFields; index++)
        {
            jdoProvideField(fields[index]);
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // JDO Generated jdoReplaceField and jdoReplaceFields
    // (Conforms to Section 20.18.10 of the JDO Specification)
    //
    // The generated jdoReplaceField retrieves a new value from the StateManager for
    // one specific field based on field number. This method is called by the StateManager
    // whenever it wants to update the value of a field in the instance, for example to
    // store values in the instance from the data store.
    //
    // This may be used by the StateManager to clear fields and handle cleanup of
    // the objects currently referred to by the fields (e. g., embedded objects).
    // The enhancer by default will generate the jdoReplaceField and jdoReplaceFields
    ///////////////////////////////////////////////////////////////////////////
    /*
     *
     */
    public void jdoReplaceField(int fieldNumber)
    {
        final String THIS = "jdoReplaceField(fieldNumber): ";
        switch (fieldNumber)
        {
            case 0:
                _setAccountType_2 ( jdoStateManager.replacingIntField(this, fieldNumber) );
                break;

            case 1:
                _setBalance_2 ( jdoStateManager.replacingIntField(this, fieldNumber) );
                break;

            case 2:
                _setCustomerNames_2 ( jdoStateManager.replacingStringField(this, fieldNumber) );
                break;

            case 3:
                _setOpenedOn_2 ( (java.util.Date)jdoStateManager.replacingObjectField(this, fieldNumber) );

                break;

            default:
                throw new IllegalArgumentException(THIS+"invalid field number: " + fieldNumber);
        }

    }

    /*
     *
     */
    public void jdoReplaceFields(int fields[])
    {
        int numberOfFields = fields.length;
        for (int index = 0; index < numberOfFields; index++)
        {
            jdoReplaceField(fields[index]);
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // Generated jdoCopyField and jdoCopyFields methods
    // (Conforms to Section 20.18.12 of the JDO Specification)
    //
    // The generated jdoCopyFields copies fields from another instance to
    // this instance. This method might be used by the StateManager to create
    // before images of instances for rollback. This method copies values for
    // fields declared in this class, and then calls the superclass (if there
    // is a persistence-capable superclass) to handle the other values. This is
    // done instead of calling another method (such as jdoCopyField) due to the
    // overhead of checking and casting the parameter. To avoid security exposure,
    // jdoCopyFields can only be invoked when both instances are owned by the same
    // StateManager. Thus, a malicious user cannot use this method to copy fields
    // from a managed instance to a non-managed instance, or to an instance managed
    // by a stealth StateManager.
    ///////////////////////////////////////////////////////////////////////////
    /**
     *
     */
    public void jdoCopyFields (Object persistenceCapable, int[] fieldNumbers)
    {
        final String THIS = "jdoCopyFields(persistenceCapable,fieldNumbers): ";
        if (persistenceCapable instanceof Account)
        {
            Account other = (Account)persistenceCapable;
            // the other instance must be owned by the same StateManager
            if (other.jdoStateManager != this.jdoStateManager ||  (this.jdoStateManager == null))
            {
                throw new javax.jdo.JDOFatalInternalException("Invalid PersistenceCapable instance passed in as a parameter");
            }
            for (int index = 0; index < fieldNumbers.length; ++index)
            {
                jdoCopyField (other, fieldNumbers [index]);
            }
        }
        else
        {
            throw new ClassCastException (THIS + "Invalid PersistenceCapable instance passed in as a parameter");
        }
    }

    /**
     *
     */
    public void jdoCopyField (Account other, int fieldNumber)
    {
        final String THIS = "jdoCopyField(other,fieldNumber): ";
            switch (fieldNumber)
            {
                case 0:
                    _setAccountType_2 (other._getAccountType_());
                    break;

                case 1:
                    _setBalance_2 (other._getBalance_());
                    break;

                case 2:
                    _setCustomerNames_2 (other._getCustomerNames_());
                    break;

                case 3:
                    _setOpenedOn_2 (other._getOpenedOn_());
                    break;

                default:
                    //////////////////////////////////////////////////////////////////////////////
                    // There is no superclass, so if execution reached here, throw an exception
                    //////////////////////////////////////////////////////////////////////////////
                    throw new IllegalArgumentException("Invalid Field Number passed in as a parameter");
            } // switch
    }


    ///////////////////////////////////////////////////////////////////////////
    // JDO Generated jdoGetXXX methods (one per persistent field)
    // (Conforms to Section 20.18.8 of the JDO Specification)
    //
    // The generated jdoGetXXX methods have exactly the same semantics
    // as the byte code getfield. They return the value of one
    // specific field. The field returned was either cached in
    // the instance or retrieved from the StateManager. The name of
    // the generated method is constructed from the fully qualified
    // class name plus field name. This allows for hidden fields to be
    // supported explicitly, and for classes to be enhanced independently.
    //
    // The modifier (MMM in the examples) is the same modifier as the
    // corresponding field definition.
    //
    // Therefore, access to the method is controlled by the same policy
    // as for the corresponding field.
    ///////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    // JDO Generated jdoGetXXX methods with one underscoll just before field name
    //  (one per persistent field)
    // (This PEJ's implementation for JDO class enhancement)
    //
    ///////////////////////////////////////////////////////////////////////////
    /**
     *
     */
    public static int jdoGetCom_Hywy_Samples_Bank_Account_accountType_(Account _jdoInstance_)
    {
        return jdoGetCom_Hywy_Samples_Bank_Account__accountType_( _jdoInstance_ );
    }

    ///////////////////////////////////////////////////////////////////////////
    // JDO Generated jdoSetXXX methods (one per persistent field)
    // (Conforms to Section 20.18.9 of the JDO Specification)
    //
    // The generated jdoSetXXX methods have exactly the same semantics
    // as the byte code setField. They modify the value of one specific
    // field. The field modified was either cached in the instance or
    // notified the StateManager.
    //
    // If the field is in the default fetch group, and the jdoFlags are
    // set to READ_WRITE_OK, then this method stores the new value in the
    // field and returns. If the jdoFlags is not set to READ_WRITE_OK,
    // then this method calls the StateManager with the old value of the
    // field and the new value. The StateManager will return the value to
    // be stored in the instance.
    //
    // The name of the generated method is constructed from the fully
    // qualified class name plus field name. This allows for hidden fields
    // to be supported explicitly, and for classes to be enhanced independently.
    ///////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    // JDO Generated jdoSetXXX methods with one underscoll just before field name
    //  (one per persistent field)
    // (This PEJ's implementation for JDO class enhancement)
    //
    ///////////////////////////////////////////////////////////////////////////
    /**
     *
     */
    public static void jdoSetCom_Hywy_Samples_Bank_Account_accountType_(Account _jdoInstance_, int newValue)
    {
        jdoSetCom_Hywy_Samples_Bank_Account__accountType_( _jdoInstance_, newValue);
    }

    ///////////////////////////////////////////////////////////////////////////
    // JDO Generated jdoGetXXX methods with two underscoll just before field name
    //  (one per persistent field)
    // (This PEJ's implementation for JDO class enhancement)
    //
    ///////////////////////////////////////////////////////////////////////////
    /**
     *
     */
    public static int jdoGetCom_Hywy_Samples_Bank_Account__accountType_(Account _jdoInstance_)
    {
        // default fetch group field
        if( _jdoInstance_.jdoFlags <= READ_WRITE_OK )
        {
            return _jdoInstance_._getAccountType_2();
        }
        StateManager stateManager = _jdoInstance_.jdoStateManager;
        if (stateManager != null)
        {
            int fieldNumber = _jdoInstance_.jdoInheritedFieldCount + 0;
            if (stateManager.isLoaded (_jdoInstance_, fieldNumber))
            {
                return _jdoInstance_._getAccountType_2();
            }
            return stateManager.getIntField (_jdoInstance_, fieldNumber, _jdoInstance_._getAccountType_2());
        }
        else
        {
            return ((Account)_jdoInstance_)._getAccountType_2();
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // JDO Generated jdoSetXXX methods with two underscoll just before field name
    //  (one per persistent field)
    // (This PEJ's implementation for JDO class enhancement)
    //
    ///////////////////////////////////////////////////////////////////////////
    /**
     *
     */
    public static void jdoSetCom_Hywy_Samples_Bank_Account__accountType_(Account _jdoInstance_, int newValue)
    {
        // default fetch group field
        if( _jdoInstance_.jdoFlags == READ_WRITE_OK )
        {
            _jdoInstance_._setAccountType_2 (newValue);
            return;
        }
        StateManager stateManager = _jdoInstance_.jdoStateManager;
        if (stateManager == null)
        {
            _jdoInstance_._setAccountType_2 (newValue);
        }
        else
        {
            int fieldNumber = _jdoInstance_.jdoInheritedFieldCount + 0;
            stateManager.setIntField (_jdoInstance_, fieldNumber, _jdoInstance_._getAccountType_(), newValue);
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // JDO Generated _getXxx() methods with one underscoll just before "get"
    //  (one per persistent field)
    // (This PEJ's implementation for JDO class enhancement)
    //
    ///////////////////////////////////////////////////////////////////////////
    /**
     *
     */
    public int _getAccountType_()
    {
            return Account.jdoGetCom_Hywy_Samples_Bank_Account__accountType_(this);
    }

    ///////////////////////////////////////////////////////////////////////////
    // JDO Generated _setXxx() methods with one underscoll just before "set"
    //  (one per persistent field)
    // (This PEJ's implementation for JDO class enhancement)
    //
    ///////////////////////////////////////////////////////////////////////////
    /**
     *
     */
    public void _setAccountType_ (int  newValue)
    {
        Account.jdoSetCom_Hywy_Samples_Bank_Account__accountType_(this, newValue);
    }


    ///////////////////////////////////////////////////////////////////////////
    // JDO Generated _getXxx2() methods with one underscoll just before "get"
    //  (one per persistent field) and with "2" at the end of the method name.
    // The purposes of the method is that PEJ's JDO run time are guaranteed
    // to retrieve the value of the non visible private / non package level
    // variable values in the super class.
    // (This PEJ's implementation for JDO class enhancement)
    //
    ///////////////////////////////////////////////////////////////////////////
    /**
     *
     */
    public int _getAccountType_2()
    {
        return accountType_;
    }


    ///////////////////////////////////////////////////////////////////////////
    // JDO Generated _setXxx2() methods with one underscoll just before "set"
    //  (one per persistent field) and with "2" at the end of the method name.
    // The purposes of the method is that PEJ's JDO run time are guaranteed
    // to set the value of the non visible (private / non package level) variable
    // values in the super class.
    // (This PEJ's implementation for JDO class enhancement)
    //
    ///////////////////////////////////////////////////////////////////////////
    /**
     *
     */
    public void _setAccountType_2 (int  newValue)
    {
        accountType_ = newValue;
    }

    ///////////////////////////////////////////////////////////////////////////
    // JDO Generated jdoGetXXX methods (one per persistent field)
    // (Conforms to Section 20.18.8 of the JDO Specification)
    //
    // The generated jdoGetXXX methods have exactly the same semantics
    // as the byte code getfield. They return the value of one
    // specific field. The field returned was either cached in
    // the instance or retrieved from the StateManager. The name of
    // the generated method is constructed from the fully qualified
    // class name plus field name. This allows for hidden fields to be
    // supported explicitly, and for classes to be enhanced independently.
    //
    // The modifier (MMM in the examples) is the same modifier as the
    // corresponding field definition.
    //
    // Therefore, access to the method is controlled by the same policy
    // as for the corresponding field.
    ///////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    // JDO Generated jdoGetXXX methods with one underscoll just before field name
    //  (one per persistent field)
    // (This PEJ's implementation for JDO class enhancement)
    //
    ///////////////////////////////////////////////////////////////////////////
    /**
     *
     */
    public static int jdoGetCom_Hywy_Samples_Bank_Account_balance_(Account _jdoInstance_)
    {
        return jdoGetCom_Hywy_Samples_Bank_Account__balance_( _jdoInstance_ );
    }

    ///////////////////////////////////////////////////////////////////////////
    // JDO Generated jdoSetXXX methods (one per persistent field)
    // (Conforms to Section 20.18.9 of the JDO Specification)
    //
    // The generated jdoSetXXX methods have exactly the same semantics
    // as the byte code setField. They modify the value of one specific
    // field. The field modified was either cached in the instance or
    // notified the StateManager.
    //
    // If the field is in the default fetch group, and the jdoFlags are
    // set to READ_WRITE_OK, then this method stores the new value in the
    // field and returns. If the jdoFlags is not set to READ_WRITE_OK,
    // then this method calls the StateManager with the old value of the
    // field and the new value. The StateManager will return the value to
    // be stored in the instance.
    //
    // The name of the generated method is constructed from the fully
    // qualified class name plus field name. This allows for hidden fields
    // to be supported explicitly, and for classes to be enhanced independently.
    ///////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    // JDO Generated jdoSetXXX methods with one underscoll just before field name
    //  (one per persistent field)
    // (This PEJ's implementation for JDO class enhancement)
    //
    ///////////////////////////////////////////////////////////////////////////
    /**
     *
     */
    public static void jdoSetCom_Hywy_Samples_Bank_Account_balance_(Account _jdoInstance_, int newValue)
    {
        jdoSetCom_Hywy_Samples_Bank_Account__balance_( _jdoInstance_, newValue);
    }

    ///////////////////////////////////////////////////////////////////////////
    // JDO Generated jdoGetXXX methods with two underscoll just before field name
    //  (one per persistent field)
    // (This PEJ's implementation for JDO class enhancement)
    //
    ///////////////////////////////////////////////////////////////////////////
    /**
     *
     */
    public static int jdoGetCom_Hywy_Samples_Bank_Account__balance_(Account _jdoInstance_)
    {
        // default fetch group field
        if( _jdoInstance_.jdoFlags <= READ_WRITE_OK )
        {
            return _jdoInstance_._getBalance_2();
        }
        StateManager stateManager = _jdoInstance_.jdoStateManager;
        if (stateManager != null)
        {
            int fieldNumber = _jdoInstance_.jdoInheritedFieldCount + 1;
            if (stateManager.isLoaded (_jdoInstance_, fieldNumber))
            {
                return _jdoInstance_._getBalance_2();
            }
            return stateManager.getIntField (_jdoInstance_, fieldNumber, _jdoInstance_._getBalance_2());
        }
        else
        {
            return ((Account)_jdoInstance_)._getBalance_2();
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // JDO Generated jdoSetXXX methods with two underscoll just before field name
    //  (one per persistent field)
    // (This PEJ's implementation for JDO class enhancement)
    //
    ///////////////////////////////////////////////////////////////////////////
    /**
     *
     */
    public static void jdoSetCom_Hywy_Samples_Bank_Account__balance_(Account _jdoInstance_, int newValue)
    {
        // default fetch group field
        if( _jdoInstance_.jdoFlags == READ_WRITE_OK )
        {
            _jdoInstance_._setBalance_2 (newValue);
            return;
        }
        StateManager stateManager = _jdoInstance_.jdoStateManager;
        if (stateManager == null)
        {
            _jdoInstance_._setBalance_2 (newValue);
        }
        else
        {
            int fieldNumber = _jdoInstance_.jdoInheritedFieldCount + 1;
            stateManager.setIntField (_jdoInstance_, fieldNumber, _jdoInstance_._getBalance_(), newValue);
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // JDO Generated _getXxx() methods with one underscoll just before "get"
    //  (one per persistent field)
    // (This PEJ's implementation for JDO class enhancement)
    //
    ///////////////////////////////////////////////////////////////////////////
    /**
     *
     */
    public int _getBalance_()
    {
            return Account.jdoGetCom_Hywy_Samples_Bank_Account__balance_(this);
    }

    ///////////////////////////////////////////////////////////////////////////
    // JDO Generated _setXxx() methods with one underscoll just before "set"
    //  (one per persistent field)
    // (This PEJ's implementation for JDO class enhancement)
    //
    ///////////////////////////////////////////////////////////////////////////
    /**
     *
     */
    public void _setBalance_ (int  newValue)
    {
        Account.jdoSetCom_Hywy_Samples_Bank_Account__balance_(this, newValue);
    }


    ///////////////////////////////////////////////////////////////////////////
    // JDO Generated _getXxx2() methods with one underscoll just before "get"
    //  (one per persistent field) and with "2" at the end of the method name.
    // The purposes of the method is that PEJ's JDO run time are guaranteed
    // to retrieve the value of the non visible private / non package level
    // variable values in the super class.
    // (This PEJ's implementation for JDO class enhancement)
    //
    ///////////////////////////////////////////////////////////////////////////
    /**
     *
     */
    public int _getBalance_2()
    {
        return balance_;
    }


    ///////////////////////////////////////////////////////////////////////////
    // JDO Generated _setXxx2() methods with one underscoll just before "set"
    //  (one per persistent field) and with "2" at the end of the method name.
    // The purposes of the method is that PEJ's JDO run time are guaranteed
    // to set the value of the non visible (private / non package level) variable
    // values in the super class.
    // (This PEJ's implementation for JDO class enhancement)
    //
    ///////////////////////////////////////////////////////////////////////////
    /**
     *
     */
    public void _setBalance_2 (int  newValue)
    {
        balance_ = newValue;
    }

    ///////////////////////////////////////////////////////////////////////////
    // JDO Generated jdoGetXXX methods (one per persistent field)
    // (Conforms to Section 20.18.8 of the JDO Specification)
    //
    // The generated jdoGetXXX methods have exactly the same semantics
    // as the byte code getfield. They return the value of one
    // specific field. The field returned was either cached in
    // the instance or retrieved from the StateManager. The name of
    // the generated method is constructed from the fully qualified
    // class name plus field name. This allows for hidden fields to be
    // supported explicitly, and for classes to be enhanced independently.
    //
    // The modifier (MMM in the examples) is the same modifier as the
    // corresponding field definition.
    //
    // Therefore, access to the method is controlled by the same policy
    // as for the corresponding field.
    ///////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    // JDO Generated jdoGetXXX methods with one underscoll just before field name
    //  (one per persistent field)
    // (This PEJ's implementation for JDO class enhancement)
    //
    ///////////////////////////////////////////////////////////////////////////
    /**
     *
     */
    public static java.lang.String jdoGetCom_Hywy_Samples_Bank_Account_customerNames_(Account _jdoInstance_)
    {
        return jdoGetCom_Hywy_Samples_Bank_Account__customerNames_( _jdoInstance_ );
    }

    ///////////////////////////////////////////////////////////////////////////
    // JDO Generated jdoSetXXX methods (one per persistent field)
    // (Conforms to Section 20.18.9 of the JDO Specification)
    //
    // The generated jdoSetXXX methods have exactly the same semantics
    // as the byte code setField. They modify the value of one specific
    // field. The field modified was either cached in the instance or
    // notified the StateManager.
    //
    // If the field is in the default fetch group, and the jdoFlags are
    // set to READ_WRITE_OK, then this method stores the new value in the
    // field and returns. If the jdoFlags is not set to READ_WRITE_OK,
    // then this method calls the StateManager with the old value of the
    // field and the new value. The StateManager will return the value to
    // be stored in the instance.
    //
    // The name of the generated method is constructed from the fully
    // qualified class name plus field name. This allows for hidden fields
    // to be supported explicitly, and for classes to be enhanced independently.
    ///////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    // JDO Generated jdoSetXXX methods with one underscoll just before field name
    //  (one per persistent field)
    // (This PEJ's implementation for JDO class enhancement)
    //
    ///////////////////////////////////////////////////////////////////////////
    /**
     *
     */
    public static void jdoSetCom_Hywy_Samples_Bank_Account_customerNames_(Account _jdoInstance_, java.lang.String newValue)
    {
        jdoSetCom_Hywy_Samples_Bank_Account__customerNames_( _jdoInstance_, newValue);
    }

    ///////////////////////////////////////////////////////////////////////////
    // JDO Generated jdoGetXXX methods with two underscoll just before field name
    //  (one per persistent field)
    // (This PEJ's implementation for JDO class enhancement)
    //
    ///////////////////////////////////////////////////////////////////////////
    /**
     *
     */
    public static java.lang.String jdoGetCom_Hywy_Samples_Bank_Account__customerNames_(Account _jdoInstance_)
    {
        // default fetch group field
        if( _jdoInstance_.jdoFlags <= READ_WRITE_OK )
        {
            return _jdoInstance_._getCustomerNames_2();
        }
        StateManager stateManager = _jdoInstance_.jdoStateManager;
        if (stateManager != null)
        {
            int fieldNumber = _jdoInstance_.jdoInheritedFieldCount + 2;
            if (stateManager.isLoaded (_jdoInstance_, fieldNumber))
            {
                return _jdoInstance_._getCustomerNames_2();
            }
            return stateManager.getStringField (_jdoInstance_, fieldNumber, _jdoInstance_._getCustomerNames_2());
        }
        else
        {
            return ((Account)_jdoInstance_)._getCustomerNames_2();
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // JDO Generated jdoSetXXX methods with two underscoll just before field name
    //  (one per persistent field)
    // (This PEJ's implementation for JDO class enhancement)
    //
    ///////////////////////////////////////////////////////////////////////////
    /**
     *
     */
    public static void jdoSetCom_Hywy_Samples_Bank_Account__customerNames_(Account _jdoInstance_, java.lang.String newValue)
    {
        // default fetch group field
        if( _jdoInstance_.jdoFlags == READ_WRITE_OK )
        {
            _jdoInstance_._setCustomerNames_2 (newValue);
            return;
        }
        StateManager stateManager = _jdoInstance_.jdoStateManager;
        if (stateManager == null)
        {
            _jdoInstance_._setCustomerNames_2 (newValue);
        }
        else
        {
            int fieldNumber = _jdoInstance_.jdoInheritedFieldCount + 2;
            stateManager.setStringField (_jdoInstance_, fieldNumber, _jdoInstance_._getCustomerNames_(), newValue);
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // JDO Generated _getXxx() methods with one underscoll just before "get"
    //  (one per persistent field)
    // (This PEJ's implementation for JDO class enhancement)
    //
    ///////////////////////////////////////////////////////////////////////////
    /**
     *
     */
    public java.lang.String _getCustomerNames_()
    {
            return Account.jdoGetCom_Hywy_Samples_Bank_Account__customerNames_(this);
    }

    ///////////////////////////////////////////////////////////////////////////
    // JDO Generated _setXxx() methods with one underscoll just before "set"
    //  (one per persistent field)
    // (This PEJ's implementation for JDO class enhancement)
    //
    ///////////////////////////////////////////////////////////////////////////
    /**
     *
     */
    public void _setCustomerNames_ (java.lang.String  newValue)
    {
        Account.jdoSetCom_Hywy_Samples_Bank_Account__customerNames_(this, newValue);
    }


    ///////////////////////////////////////////////////////////////////////////
    // JDO Generated _getXxx2() methods with one underscoll just before "get"
    //  (one per persistent field) and with "2" at the end of the method name.
    // The purposes of the method is that PEJ's JDO run time are guaranteed
    // to retrieve the value of the non visible private / non package level
    // variable values in the super class.
    // (This PEJ's implementation for JDO class enhancement)
    //
    ///////////////////////////////////////////////////////////////////////////
    /**
     *
     */
    public java.lang.String _getCustomerNames_2()
    {
        return customerNames_;
    }


    ///////////////////////////////////////////////////////////////////////////
    // JDO Generated _setXxx2() methods with one underscoll just before "set"
    //  (one per persistent field) and with "2" at the end of the method name.
    // The purposes of the method is that PEJ's JDO run time are guaranteed
    // to set the value of the non visible (private / non package level) variable
    // values in the super class.
    // (This PEJ's implementation for JDO class enhancement)
    //
    ///////////////////////////////////////////////////////////////////////////
    /**
     *
     */
    public void _setCustomerNames_2 (java.lang.String  newValue)
    {
        customerNames_ = newValue;
    }

    ///////////////////////////////////////////////////////////////////////////
    // JDO Generated jdoGetXXX methods (one per persistent field)
    // (Conforms to Section 20.18.8 of the JDO Specification)
    //
    // The generated jdoGetXXX methods have exactly the same semantics
    // as the byte code getfield. They return the value of one
    // specific field. The field returned was either cached in
    // the instance or retrieved from the StateManager. The name of
    // the generated method is constructed from the fully qualified
    // class name plus field name. This allows for hidden fields to be
    // supported explicitly, and for classes to be enhanced independently.
    //
    // The modifier (MMM in the examples) is the same modifier as the
    // corresponding field definition.
    //
    // Therefore, access to the method is controlled by the same policy
    // as for the corresponding field.
    ///////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    // JDO Generated jdoGetXXX methods with one underscoll just before field name
    //  (one per persistent field)
    // (This PEJ's implementation for JDO class enhancement)
    //
    ///////////////////////////////////////////////////////////////////////////
    /**
     *
     */
    public static java.util.Date jdoGetCom_Hywy_Samples_Bank_Account_openedOn_(Account _jdoInstance_)
    {
        return jdoGetCom_Hywy_Samples_Bank_Account__openedOn_( _jdoInstance_ );
    }

    ///////////////////////////////////////////////////////////////////////////
    // JDO Generated jdoSetXXX methods (one per persistent field)
    // (Conforms to Section 20.18.9 of the JDO Specification)
    //
    // The generated jdoSetXXX methods have exactly the same semantics
    // as the byte code setField. They modify the value of one specific
    // field. The field modified was either cached in the instance or
    // notified the StateManager.
    //
    // If the field is in the default fetch group, and the jdoFlags are
    // set to READ_WRITE_OK, then this method stores the new value in the
    // field and returns. If the jdoFlags is not set to READ_WRITE_OK,
    // then this method calls the StateManager with the old value of the
    // field and the new value. The StateManager will return the value to
    // be stored in the instance.
    //
    // The name of the generated method is constructed from the fully
    // qualified class name plus field name. This allows for hidden fields
    // to be supported explicitly, and for classes to be enhanced independently.
    ///////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    // JDO Generated jdoSetXXX methods with one underscoll just before field name
    //  (one per persistent field)
    // (This PEJ's implementation for JDO class enhancement)
    //
    ///////////////////////////////////////////////////////////////////////////
    /**
     *
     */
    public static void jdoSetCom_Hywy_Samples_Bank_Account_openedOn_(Account _jdoInstance_, java.util.Date newValue)
    {
        jdoSetCom_Hywy_Samples_Bank_Account__openedOn_( _jdoInstance_, newValue);
    }

    ///////////////////////////////////////////////////////////////////////////
    // JDO Generated jdoGetXXX methods with two underscoll just before field name
    //  (one per persistent field)
    // (This PEJ's implementation for JDO class enhancement)
    //
    ///////////////////////////////////////////////////////////////////////////
    /**
     *
     */
    public static java.util.Date jdoGetCom_Hywy_Samples_Bank_Account__openedOn_(Account _jdoInstance_)
    {
        // default fetch group field
        if( _jdoInstance_.jdoFlags <= READ_WRITE_OK )
        {
            return _jdoInstance_._getOpenedOn_2();
        }
        StateManager stateManager = _jdoInstance_.jdoStateManager;
        if (stateManager != null)
        {
            int fieldNumber = _jdoInstance_.jdoInheritedFieldCount + 3;
            if (stateManager.isLoaded (_jdoInstance_, fieldNumber))
            {
                return _jdoInstance_._getOpenedOn_2();
            }
            return (java.util.Date)stateManager.getObjectField (_jdoInstance_, fieldNumber, _jdoInstance_._getOpenedOn_2());
        }
        else
        {
            return ((Account)_jdoInstance_)._getOpenedOn_2();
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // JDO Generated jdoSetXXX methods with two underscoll just before field name
    //  (one per persistent field)
    // (This PEJ's implementation for JDO class enhancement)
    //
    ///////////////////////////////////////////////////////////////////////////
    /**
     *
     */
    public static void jdoSetCom_Hywy_Samples_Bank_Account__openedOn_(Account _jdoInstance_, java.util.Date newValue)
    {
        // default fetch group field
        if( _jdoInstance_.jdoFlags == READ_WRITE_OK )
        {
            _jdoInstance_._setOpenedOn_2 (newValue);
            return;
        }
        StateManager stateManager = _jdoInstance_.jdoStateManager;
        if (stateManager == null)
        {
            _jdoInstance_._setOpenedOn_2 (newValue);
        }
        else
        {
            int fieldNumber = _jdoInstance_.jdoInheritedFieldCount + 3;
            stateManager.setObjectField (_jdoInstance_, fieldNumber, _jdoInstance_._getOpenedOn_(), newValue);
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // JDO Generated _getXxx() methods with one underscoll just before "get"
    //  (one per persistent field)
    // (This PEJ's implementation for JDO class enhancement)
    //
    ///////////////////////////////////////////////////////////////////////////
    /**
     *
     */
    public java.util.Date _getOpenedOn_()
    {
            return Account.jdoGetCom_Hywy_Samples_Bank_Account__openedOn_(this);
    }

    ///////////////////////////////////////////////////////////////////////////
    // JDO Generated _setXxx() methods with one underscoll just before "set"
    //  (one per persistent field)
    // (This PEJ's implementation for JDO class enhancement)
    //
    ///////////////////////////////////////////////////////////////////////////
    /**
     *
     */
    public void _setOpenedOn_ (java.util.Date  newValue)
    {
        Account.jdoSetCom_Hywy_Samples_Bank_Account__openedOn_(this, newValue);
    }


    ///////////////////////////////////////////////////////////////////////////
    // JDO Generated _getXxx2() methods with one underscoll just before "get"
    //  (one per persistent field) and with "2" at the end of the method name.
    // The purposes of the method is that PEJ's JDO run time are guaranteed
    // to retrieve the value of the non visible private / non package level
    // variable values in the super class.
    // (This PEJ's implementation for JDO class enhancement)
    //
    ///////////////////////////////////////////////////////////////////////////
    /**
     *
     */
    public java.util.Date _getOpenedOn_2()
    {
        return openedOn_;
    }


    ///////////////////////////////////////////////////////////////////////////
    // JDO Generated _setXxx2() methods with one underscoll just before "set"
    //  (one per persistent field) and with "2" at the end of the method name.
    // The purposes of the method is that PEJ's JDO run time are guaranteed
    // to set the value of the non visible (private / non package level) variable
    // values in the super class.
    // (This PEJ's implementation for JDO class enhancement)
    //
    ///////////////////////////////////////////////////////////////////////////
    /**
     *
     */
    public void _setOpenedOn_2 (java.util.Date  newValue)
    {
        openedOn_ = newValue;
    }


    ///////////////////////////////////////////////////////////////////////////
    // JDO Generated writeObject method
    // (Conforms to Section 20.16.18 of the JDO Specification)
    //
    // If no user-written method writeObject exists, then one will be generated.
    // The generated writeObject makes sure that all persistent and transactional
    // serializable fields are loaded into the instance, and then the default
    // output behavior is invoked on the output stream. If the class is
    // serializable (either by explicit declaration or by inheritance) then
    // this code will guarantee that the fields are loaded prior to standard
    // serialization. If the class is not serializable, then this code will
    // never be executed.
    // Note that there is no modification of a user?s readObject.
    // During the execution of readObject, a new transient instance
    // is created. This instance might be made persistent later, but
    // while it is being constructed by serialization, it remains transient.
    ///////////////////////////////////////////////////////////////////////////
    /**
     *
     */
    private void writeObject (java.io.ObjectOutputStream out) throws java.io.IOException
    {
        jdoPreSerialize ();
        out.defaultWriteObject ();
    }

    ///////////////////////////////////////////////////////////////////////////
    // JDO Generated jdoPreSerialize method
    // (Conforms to Section 20.19 of the JDO Specification)
    // The generated jdoPreSerialize method makes sure that all persistent
    // and transactional serializable fields are loaded into the instance
    // by delegating to the corresponding method in StateManager.
    ///////////////////////////////////////////////////////////////////////////
    /**
     *
     */
    private void jdoPreSerialize()
    {
        if (jdoStateManager != null)
        {
            jdoStateManager.preSerialize(this);
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    // This method is called after the default fetch group values have been loaded
    // from the StateManager into the instance. Non-persistent fields whose value
    // depends on values of default fetch group fields should be initialized in this method.
    // should be accessed by this This method is not modified by the enhancer.
    // Only fields that are in the default fetch group  method,
    // as other fields are not guaranteed to be initialized.  This method might register the
    // instance with other objects in the runtime environment.
    // The context in which this call is made does not allow access
    // to other persistent JDO in-stances.
    /////////////////////////////////////////////////////////////////////////////////////////
    /**
     *
     */
    public void jdoPostLoad()
    {
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    // This method is called before the values are stored from the instance to the StateManager.
    // Data store fields that might have been affected by modified non-persistent fields should
    // be updated in this method. This method is modified by the enhancer so that changes to
    // persistent fields will be reflected in the data store.
    // The context in which this call is made allows access to the PersistenceManager and
    // other persistent JDO instances.
    /////////////////////////////////////////////////////////////////////////////////////////////
    /**
     *
     */
    public void jdoPreStore()
    {
    }

    ///////////////////////////////////////////////////////////////////////////
    //  This method is called before the values in the instance are cleared.
    //  This happens during the state transition to hollow. Non-persistent,
    //  non-transactional fields should be cleared in this method.
    //  Associations between this instance and others in the runtime environment
    //  should be cleared. This method is not modified by the enhancer.
    ///////////////////////////////////////////////////////////////////////////
    /**
     *
     */
    public void jdoPreClear()
    {
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    // This method is called during the execution of deletePersistent
    // before the state tran-sition to persistent-deleted or
    // persistent-new-deleted. Access to field values within this
    // call are valid. Access to field values after this call are disallowed.
    // This method is modified by the enhancer so that fields referenced
    // can be used in the business logic of the method.
    // To implement a containment aggregate, the user could implement this method to delete
    // contained persistent instances.
    ////////////////////////////////////////////////////////////////////////////////////////
    /**
     *
     */
    public void jdoPreDelete()
    {
    }

}  // end of enhanced JDO class -> Account.java