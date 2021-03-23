package com.streams.gironil.persistance;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2016-01-20T14:46:37")
@StaticMetamodel(Batches.class)
public class Batches_ { 

    public static volatile SingularAttribute<Batches, Date> creationDate;
    public static volatile SingularAttribute<Batches, Float> totalAmount;
    public static volatile SingularAttribute<Batches, Integer> numberOfTransactions;
    public static volatile SingularAttribute<Batches, String> batchId;
    public static volatile SingularAttribute<Batches, String> companyName;
    public static volatile SingularAttribute<Batches, String> companyCode;

}