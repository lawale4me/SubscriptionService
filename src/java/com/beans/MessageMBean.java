/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import com.core.AdminRepositoryImpl;
import com.core.AppManager;
import com.entities.Messages;
import com.entities.Product;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Ahmed
 */
@ManagedBean
@RequestScoped
public class MessageMBean {

    /**
     * Creates a new instance of ProductMBean
     */
    public MessageMBean() {
    }

    private String message;
    private Integer messageId;
    private Product product;
    Messages msg;
    private List<Messages> msgs;
    private List<Product> productList;
    private UploadedFile file;

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    AppManager appManager = new AppManager(new AdminRepositoryImpl());

    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        HttpSession httpSession = request.getSession(false);
        //username=(String) httpSession.getAttribute("username");  
        //user=appManager.getUser(username);                        
        msgs = appManager.getMsgs();
        productList = appManager.getProductList();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Messages getMsg() {
        return msg;
    }

    public void setMsg(Messages msg) {
        this.msg = msg;
    }

    public List<Messages> getMsgs() {
        return msgs;
    }

    public void setMsgs(List<Messages> msgs) {
        this.msgs = msgs;
    }

    public String addAction() {
        Messages msg = new Messages();
        msg.setMessage(message);
        msg.setMessageId(messageId);
        msg.setProductId(product);
        msg = appManager.addMessage(msg);
        msgs.add(msg);

        message = "";
        messageId = 0;
        messageId = null;
        return null;
    }

    public String addAction2() {

        if (file != null) {
            ArrayList<String> Strs = new ArrayList<String>();
            String line = "";
            BufferedReader br;
            try {
                br = new BufferedReader(new InputStreamReader(file.getInputstream()));
            
            while ((line = br.readLine()) != null) {
                System.out.println("Message:" + line);
                Strs.add(line);
            }
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            for (String str : Strs) {
                Messages msg=new Messages();
                msg.setMessage(str);
                msg.setMessageId(messageId);
                msg.setProductId(product);
                msg = appManager.addMessage(msg);
                msgs.add(msg);
            }
            message = "";
            messageId = 0;
            messageId = null;

            FacesMessage message = new FacesMessage("Successful", file.getFileName() + " was uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            FacesMessage message = new FacesMessage("Error", " File not uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

        return null;
    }

    public void onEdit(RowEditEvent event) {
    }

    public void onCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Product Cancelled");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        Messages pr = (Messages) event.getObject();
        pr = appManager.getMsg(pr.getMessageId());
        appManager.removeMsg(pr);
        msgs.remove(pr);
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

}
