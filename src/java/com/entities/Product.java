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
@Table(name = "product")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p"),
    @NamedQuery(name = "Product.findByProductId", query = "SELECT p FROM Product p WHERE p.productId = :productId"),
    @NamedQuery(name = "Product.findByProductName", query = "SELECT p FROM Product p WHERE p.productName = :productName"),
    @NamedQuery(name = "Product.findByKeyword", query = "SELECT p FROM Product p WHERE p.keyword = :keyword"),
    @NamedQuery(name = "Product.findByNoOfSubscription", query = "SELECT p FROM Product p WHERE p.noOfSubscription = :noOfSubscription"),
    @NamedQuery(name = "Product.findByProductDescription", query = "SELECT p FROM Product p WHERE p.productDescription = :productDescription"),
    @NamedQuery(name = "Product.findByExpiredResponse", query = "SELECT p FROM Product p WHERE p.expiredResponse = :expiredResponse"),
    @NamedQuery(name = "Product.findByStartResponse", query = "SELECT p FROM Product p WHERE p.startResponse = :startResponse")})
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "productId")
    private Integer productId;
    @Column(name = "productName")
    private String productName;
    @Column(name = "keyword")
    private String keyword;
    @Column(name = "noOfSubscription")
    private Integer noOfSubscription;
    @Column(name = "productDescription")
    private String productDescription;
    @Column(name = "expiredResponse")
    private String expiredResponse;
    @Column(name = "startResponse")
    private String startResponse;
    @OneToMany(mappedBy = "productId")
    private Collection<Messages> messagesCollection;

    public Product() {
    }

    public Product(Integer productId) {
        this.productId = productId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getNoOfSubscription() {
        return noOfSubscription;
    }

    public void setNoOfSubscription(Integer noOfSubscription) {
        this.noOfSubscription = noOfSubscription;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getExpiredResponse() {
        return expiredResponse;
    }

    public void setExpiredResponse(String expiredResponse) {
        this.expiredResponse = expiredResponse;
    }

    public String getStartResponse() {
        return startResponse;
    }

    public void setStartResponse(String startResponse) {
        this.startResponse = startResponse;
    }

    @XmlTransient
    public Collection<Messages> getMessagesCollection() {
        return messagesCollection;
    }

    public void setMessagesCollection(Collection<Messages> messagesCollection) {
        this.messagesCollection = messagesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productId != null ? productId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.productId == null && other.productId != null) || (this.productId != null && !this.productId.equals(other.productId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.Product[ productId=" + productId + " ]";
    }
    
}
