/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ahmed
 */
@Entity
@Table(name = "transaction")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transaction.findAll", query = "SELECT t FROM Transaction t"),
    //SELECT * FROM subscription.transaction where status=0 and subscriberId NOT IN (SELECT subscriberId FROM subscription.Subscribers  WHERE productId = : productId and mobileNo = : mobileNo
   // @NamedQuery(name = "Transaction.findByStatusZ", query = "SELECT t FROM Transaction t WHERE t.status =0 and t.subscriberId NOT IN"
  //          + " (SELECT subscriberId FROM subscription.Subscribers s WHERE s.productId = : productId and mobileNo = : mobileNo)"),
    @NamedQuery(name = "Transaction.findByTransactionId", query = "SELECT t FROM Transaction t WHERE t.transactionId = :transactionId"),
    @NamedQuery(name = "Transaction.findNotPending", query = "SELECT t FROM Transaction t WHERE t.status <>0 ORDER BY t.transDate DESC"),
    @NamedQuery(name = "Transaction.findByTransDate", query = "SELECT t FROM Transaction t WHERE t.transDate = :transDate"),
    @NamedQuery(name = "Transaction.findByMobileNo", query = "SELECT t FROM Transaction t WHERE t.mobileNo = :mobileNo"),
    @NamedQuery(name = "Transaction.findByMessageId", query = "SELECT t FROM Transaction t WHERE t.messageId = :messageId"),
    @NamedQuery(name = "Transaction.findByStatus", query = "SELECT t FROM Transaction t WHERE t.status = :status"),
    @NamedQuery(name = "Transaction.findBySendDate", query = "SELECT t FROM Transaction t WHERE t.sendDate = :sendDate")})
public class Transaction implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "transactionId")
    private Integer transactionId;
    @Basic(optional = false)
    @Column(name = "transDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date transDate;
    @Column(name = "mobileNo")
    private String mobileNo;
    @Column(name = "messageId")
    private Integer messageId;
    @Column(name = "status")
    private Integer status;
    @Column(name = "sendDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sendDate;
    @JoinColumn(name = "subscriberId", referencedColumnName = "subscriberId")
    @ManyToOne
    private Subscribers subscriberId;

    public Transaction() {
    }

    public Transaction(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Transaction(Integer transactionId, Date transDate) {
        this.transactionId = transactionId;
        this.transDate = transDate;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Date getTransDate() {
        return transDate;
    }

    public void setTransDate(Date transDate) {
        this.transDate = transDate;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public Subscribers getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(Subscribers subscriberId) {
        this.subscriberId = subscriberId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (transactionId != null ? transactionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transaction)) {
            return false;
        }
        Transaction other = (Transaction) object;
        if ((this.transactionId == null && other.transactionId != null) || (this.transactionId != null && !this.transactionId.equals(other.transactionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.Transaction[ transactionId=" + transactionId + " ]";
    }
    
}
