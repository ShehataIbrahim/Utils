package com.streams.gironil.persistance;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2016-01-20T14:46:37")
@StaticMetamodel(Transactions.class)
public class Transactions_ { 

    public static volatile SingularAttribute<Transactions, String> customerName;
    public static volatile SingularAttribute<Transactions, Float> amount;
    public static volatile SingularAttribute<Transactions, String> bicCode;
    public static volatile SingularAttribute<Transactions, Integer> transactionSerial;
    public static volatile SingularAttribute<Transactions, Date> creationDate;
    public static volatile SingularAttribute<Transactions, String> accountNumber;
    public static volatile SingularAttribute<Transactions, String> transactionId;
    public static volatile SingularAttribute<Transactions, String> batchId;
    public static volatile SingularAttribute<Transactions, String> customerReference;
    public static volatile SingularAttribute<Transactions, String> branchCode;
    public static volatile SingularAttribute<Transactions, String> companyCode;

}