/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.beans;

import com.core.AdminRepositoryImpl;
import com.core.AppManager;
import com.entities.Messages;
import com.entities.Transaction;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ahmed
 */
@ManagedBean
@RequestScoped
public class TransactionMBean {

    List<Transaction> tranx;
    List<Transaction> pendingTranx;
    List<Transaction> filteredTranx;
    AppManager appManager = new AppManager(new AdminRepositoryImpl());
    /**
     * Creates a new instance of TransactionMBean
     */
    public TransactionMBean() {
    }
    
    
      @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();  
        HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();  
        HttpSession httpSession = request.getSession(false);                                       
        tranx = appManager.getTranxs();
        pendingTranx = appManager.getPendingTranx();
    }        

    public List<Transaction> getTranx() {
        return tranx;
    }

    public void setTranx(List<Transaction> tranx) {
        this.tranx = tranx;
    }

    public List<Transaction> getPendingTranx() {
        return pendingTranx;
    }

    public void setPendingTranx(List<Transaction> pendingTranx) {
        this.pendingTranx = pendingTranx;
    }
    
    public String getMsg(String msgId)
    {
        
        Messages m=appManager.getMsg(Integer.parseInt(msgId));
        if(m!=null){
            return m.getMessage();
        }
        else{
            return "Message not found";
        }
    }

    public List<Transaction> getFilteredTranx() {
        return filteredTranx;
    }

    public void setFilteredTranx(List<Transaction> filteredTranx) {
        this.filteredTranx = filteredTranx;
    }
            
    
    
    
}
