/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ahmed
 */
@Entity
@Table(name = "subscribers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Subscribers.findAll", query = "SELECT s FROM Subscribers s"),
    @NamedQuery(name = "Subscribers.findBySubscriberId", query = "SELECT s FROM Subscribers s WHERE s.subscriberId = :subscriberId"),
    @NamedQuery(name = "Subscribers.findByProductId", query = "SELECT s FROM Subscribers s WHERE s.productId = :productId"),
    @NamedQuery(name = "Subscribers.findByRemainingAlerts", query = "SELECT s FROM Subscribers s WHERE s.remainingAlerts = :remainingAlerts"),
    @NamedQuery(name = "Subscribers.findByMobileNo", query = "SELECT s FROM Subscribers s WHERE s.mobileNo = :mobileNo")})
public class Subscribers implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "subscriberId")
    private Integer subscriberId;
    @Column(name = "productId")
    private Integer productId;
    @Column(name = "remainingAlerts")
    private Integer remainingAlerts;
    @Column(name = "mobileNo")
    private String mobileNo;
    @OneToMany(mappedBy = "subscriberId")
    private Collection<Transaction> transactionCollection;
    @JoinColumn(name = "requestId", referencedColumnName = "requestID")
    @ManyToOne
    private Requests requestId;

    public Subscribers() {
    }

    public Subscribers(Integer subscriberId) {
        this.subscriberId = subscriberId;
    }

    public Integer getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(Integer subscriberId) {
        this.subscriberId = subscriberId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getRemainingAlerts() {
        return remainingAlerts;
    }

    public void setRemainingAlerts(Integer remainingAlerts) {
        this.remainingAlerts = remainingAlerts;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    @XmlTransient
    public Collection<Transaction> getTransactionCollection() {
        return transactionCollection;
    }

    public void setTransactionCollection(Collection<Transaction> transactionCollection) {
        this.transactionCollection = transactionCollection;
    }

    public Requests getRequestId() {
        return requestId;
    }

    public void setRequestId(Requests requestId) {
        this.requestId = requestId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (subscriberId != null ? subscriberId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Subscribers)) {
            return false;
        }
        Subscribers other = (Subscribers) object;
        if ((this.subscriberId == null && other.subscriberId != null) || (this.subscriberId != null && !this.subscriberId.equals(other.subscriberId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.Subscribers[ subscriberId=" + subscriberId + " ]";
    }
    
}
