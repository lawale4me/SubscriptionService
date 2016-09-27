/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ahmed
 */
@Entity
@Table(name = "requests")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Requests.findAll", query = "SELECT r FROM Requests r ORDER BY r.requestID desc"),
    @NamedQuery(name = "Requests.findByRequestID", query = "SELECT r FROM Requests r WHERE r.requestID = :requestID"),
    @NamedQuery(name = "Requests.findByMessageContent", query = "SELECT r FROM Requests r WHERE r.messageContent = :messageContent"),
    @NamedQuery(name = "Requests.findByDestAddress", query = "SELECT r FROM Requests r WHERE r.destAddress = :destAddress"),
    @NamedQuery(name = "Requests.findBySourceAddress", query = "SELECT r FROM Requests r WHERE r.sourceAddress = :sourceAddress"),
    @NamedQuery(name = "Requests.findByStatusID", query = "SELECT r FROM Requests r WHERE r.statusID = :statusID"),
    @NamedQuery(name = "Requests.findByDateInserted", query = "SELECT r FROM Requests r WHERE r.dateInserted = :dateInserted")})
public class Requests implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "requestID")
    private Integer requestID;
    @Column(name = "messageContent")
    private String messageContent;
    @Column(name = "destAddress")
    private String destAddress;
    @Column(name = "sourceAddress")
    private String sourceAddress;
    @Column(name = "statusID")
    private Integer statusID;
    @Column(name = "dateInserted")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateInserted;
    @OneToMany(mappedBy = "requestId")
    private Collection<Subscribers> subscribersCollection;

    public Requests() {
    }

    public Requests(Integer requestID) {
        this.requestID = requestID;
    }

    public Integer getRequestID() {
        return requestID;
    }

    public void setRequestID(Integer requestID) {
        this.requestID = requestID;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public String getDestAddress() {
        return destAddress;
    }

    public void setDestAddress(String destAddress) {
        this.destAddress = destAddress;
    }

    public String getSourceAddress() {
        return sourceAddress;
    }

    public void setSourceAddress(String sourceAddress) {
        this.sourceAddress = sourceAddress;
    }

    public Integer getStatusID() {
        return statusID;
    }

    public void setStatusID(Integer statusID) {
        this.statusID = statusID;
    }

    public Date getDateInserted() {
        return dateInserted;
    }

    public void setDateInserted(Date dateInserted) {
        this.dateInserted = dateInserted;
    }

    @XmlTransient
    public Collection<Subscribers> getSubscribersCollection() {
        return subscribersCollection;
    }

    public void setSubscribersCollection(Collection<Subscribers> subscribersCollection) {
        this.subscribersCollection = subscribersCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (requestID != null ? requestID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Requests)) {
            return false;
        }
        Requests other = (Requests) object;
        if ((this.requestID == null && other.requestID != null) || (this.requestID != null && !this.requestID.equals(other.requestID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.Requests[ requestID=" + requestID + " ]";
    }
    
}
