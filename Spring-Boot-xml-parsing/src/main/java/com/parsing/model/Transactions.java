package com.parsing.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name = "transactions")
@XmlAccessorType(XmlAccessType.FIELD)
public class Transactions {

    @XmlElement(name = "transaction")
    private Set<Transaction> transaction;

    public Transactions() {
    }

    public Set<Transaction> getTransaction() {
        return transaction;
    }

    public void setTransaction(Set<Transaction> transaction) {
        this.transaction = transaction;
    }

    @Override
    public String toString() {
        return "Transactions{" +
                "transaction=" + transaction +
                '}';
    }
}
