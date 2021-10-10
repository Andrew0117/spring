package com.parsing.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@Entity
@Table(name = "transaction")
@XmlRootElement(name = "transaction")
@XmlAccessorType(XmlAccessType.FIELD)
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @XmlElement(name = "place")
    private String place;

    @XmlElement(name = "amount")
    private Double amount;

    @XmlElement(name = "currency")
    private String currency;

    @XmlElement(name = "card")
    private String card;

    @XmlElement(name = "client")
    @ManyToOne
    private Client client;

    public Transaction() {
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(id, that.id) && Objects.equals(place, that.place) && Objects.equals(amount, that.amount) && Objects.equals(currency, that.currency) && Objects.equals(card, that.card) && Objects.equals(client, that.client);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, place, amount, currency, card, client);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "place='" + place + '\'' +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", card='" + card + '\'' +
                ", client=" + client +
                '}';
    }
}
