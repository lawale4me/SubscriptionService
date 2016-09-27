/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.beans;

import com.core.AdminRepositoryImpl;
import com.core.AppManager;
import com.entities.Product;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Ahmed
 */
@ManagedBean
@RequestScoped
public class ProductMBean {

    /**
     * Creates a new instance of ProductMBean
     */
    public ProductMBean() {
    }
    
    private String productName;
    private String keyword;
    private Integer noOfSubscription;
    private String productDescription;
    private String expiredResponse;
    private String startResponse;
    Product product;
    private List<Product> productList ;
    
    AppManager appManager = new AppManager(new AdminRepositoryImpl());

    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();  
        HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();  
        HttpSession httpSession = request.getSession(false);          
        //username=(String) httpSession.getAttribute("username");  
        //user=appManager.getUser(username);                        
        productList = appManager.getProductList();
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

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    } 
    
    
       
 
    public String addAction() {
        Product product = new Product();        
        product.setExpiredResponse(expiredResponse);
        product.setKeyword(keyword);
        product.setNoOfSubscription(noOfSubscription);
        product.setProductDescription(productDescription);
        product.setProductName(productName);
        product.setStartResponse(startResponse);
        product =appManager.addProduct(product);
        productList.add(product);
 
        expiredResponse = "";
        productDescription = "";
        productName = "";
        keyword = "";
        noOfSubscription=0;        
        return null;
    }
    public void onEdit(RowEditEvent event) {  
        Product pr=(Product) event.getObject();        
        System.out.println("Description:"+pr.getProductDescription());
        FacesMessage msg = new FacesMessage("Product Edited",pr.getProductName());  
         product=appManager.getProduct(pr.getProductId());
         product.setExpiredResponse(pr.getExpiredResponse());
         product.setKeyword(pr.getKeyword());
         product.setNoOfSubscription(pr.getNoOfSubscription());
         product.setProductDescription(pr.getProductDescription());
         product.setProductName(pr.getProductName());
        appManager.updateProduct(product);
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
       
    public void onCancel(RowEditEvent event) {  
        FacesMessage msg = new FacesMessage("Product Cancelled");   
        FacesContext.getCurrentInstance().addMessage(null, msg); 
        Product pr=(Product) event.getObject();
        pr=appManager.getProduct(pr.getProductId());
        appManager.removeProduct(pr);
        productList.remove(pr);
    }  

    public String getStartResponse() {
        return startResponse;
    }

    public void setStartResponse(String startResponse) {
        this.startResponse = startResponse;
    }
    
    
    
    
}
