/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.beans;

import com.core.AdminRepositoryImpl;
import com.core.AppManager;
import com.entities.Requests;
import com.entities.Transaction;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ahmed
 */
@ManagedBean
@RequestScoped
public class RequestMBean {

    AppManager appManager = new AppManager(new AdminRepositoryImpl());
    private List<Requests> reqs;
    private List<Requests> filteredReqs;
    /**
     * Creates a new instance of TransactionMBean
     */
    public RequestMBean() {
    }
    
    
      @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();  
        HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();  
        HttpSession httpSession = request.getSession(false);          
        //username=(String) httpSession.getAttribute("username");  
        //user=appManager.getUser(username);                        
        reqs = appManager.getRequests();
    }
        

    public List<Requests> getReqs() {
        return reqs;
    }

    public void setReqs(List<Requests> reqs) {
        this.reqs = reqs;
    }

    public List<Requests> getFilteredReqs() {
        return filteredReqs;
    }

    public void setFilteredReqs(List<Requests> filteredReqs) {
        this.filteredReqs = filteredReqs;
    }

  
    
    
    
}
