/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dto;

import com.entities.Requests;

/**
 *
 * @author Ahmed
 */
public class SubscriberDTO {
    
    
    
    private Integer subscriberId;
    private Integer productId;
    private Integer remainingAlerts;
    private String mobileNo;
    private Requests requestId;

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

    public Requests getRequestId() {
        return requestId;
    }

    public void setRequestId(Requests requestId) {
        this.requestId = requestId;
    }
    
    
}
