/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.core;

import com.dto.RequestStatus;
import com.dto.SubscriberDTO;
import com.dto.TransactionStatus;
import com.entities.Messages;
import com.entities.Product;
import com.entities.Requests;
import com.entities.Subscribers;
import com.entities.Transaction;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Ahmed
 */
public class AppManager {

    private final AdminRepository adminrepo;

    public AppManager(AdminRepositoryImpl adminRepositoryImpl) {
        this.adminrepo = adminRepositoryImpl;
    }

    public Requests insertRequest(Requests req) {
        UnitOfWorkSession ses = adminrepo.begin();
        Requests req1 = adminrepo.insertRequest(req);
        ses.commit();
        return req1;
    }

    public Product findProduct(String keyword) {
        UnitOfWorkSession ses = adminrepo.begin();
        Product prod = adminrepo.findProduct(keyword);
        ses.commit();
        return prod;
    }

    public String subscribeUser(Product product, Subscribers subscriber, Requests request) {
        try {
            UnitOfWorkSession ses = adminrepo.begin();
                                           
            List<Messages> msgs = getProductMessage(subscriber);
            Date sendDate = new Date();
            Calendar cal = Calendar.getInstance();

            if (msgs != null) {
                for (int i = 0; i < product.getNoOfSubscription(); i++) {
                    Transaction tranx = new Transaction();
                    tranx.setMobileNo(subscriber.getMobileNo());
                    tranx.setMessageId(msgs.get(i).getMessageId());
                    if(i==0){tranx.setSendDate(cal.getTime());}
                    else{
                        cal.set(Calendar.HOUR_OF_DAY, 10);
                        cal.set(Calendar.MINUTE, 0);
                        tranx.setSendDate(cal.getTime());
                    }
                    
                    cal.add(Calendar.DAY_OF_MONTH, 1);
                    tranx.setStatus(TransactionStatus.PENDING.ordinal());
                    tranx.setTransDate(new Date());
                    tranx.setSubscriberId(subscriber);
                    adminrepo.insertTranx(tranx);
                }

            } else {
                request.setStatusID(RequestStatus.NOMESSAGES.ordinal());
                adminrepo.updateRequest(request);
            }
            ses.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return product.getStartResponse();
    }

    public List<Subscribers> PendingSubscribers() {
        UnitOfWorkSession ses = adminrepo.begin();
        List<Subscribers> prod = adminrepo.PendingSubscribers();
        ses.commit();
        return prod;
    }

    public Messages prepareMessage(Subscribers subscriber) throws SubscriptionException {

        Messages msg = getNewMessage(subscriber);
        if (msg == null) {
            msg = getRandomMessage(subscriber);
        }
        if (msg == null) {
            throw new SubscriptionException("MESSAGE NOT POPULATED FOR THIS PRODUCT" + subscriber.getProductId());
        }
        return msg;
    }

    private Messages getNewMessage(Subscribers subscriber) {
        UnitOfWorkSession ses = adminrepo.begin();
        Messages msg = adminrepo.getMessageByProductIdAndMobileNo(subscriber.getProductId(), subscriber.getMobileNo());
        ses.commit();
        return msg;
    }

    private Messages getRandomMessage(Subscribers subscriber) {
        UnitOfWorkSession ses = adminrepo.begin();
        Messages msg = adminrepo.getMessageByProductId(subscriber.getProductId());
        ses.commit();
        return msg;
    }

    public List<Product> getProductList() {
        UnitOfWorkSession ses = adminrepo.begin();
        List<Product> prod = adminrepo.getProductList();
        ses.commit();
        return prod;
    }

    public Product addProduct(Product product) {
        UnitOfWorkSession ses = adminrepo.begin();
        Product prod = adminrepo.addProduct(product);
        ses.commit();
        return prod;
    }

    public void removeProduct(Product product) {
        UnitOfWorkSession ses = adminrepo.begin();
        adminrepo.removeProduct(product);
        ses.commit();
    }

    public void updateProduct(Product product) {
        UnitOfWorkSession ses = adminrepo.begin();
        adminrepo.updateProduct(product);
        ses.commit();
    }

    public List<SubscriberDTO> PendingSubscribersDTO() {
        List<Subscribers> subs = PendingSubscribers();
        ArrayList<SubscriberDTO> dtos = new ArrayList<SubscriberDTO>();
        for (Subscribers s : subs) {
            SubscriberDTO dto = new SubscriberDTO();
            dto.setMobileNo(s.getMobileNo());
            dto.setProductId(s.getProductId());
            dto.setRemainingAlerts(s.getRemainingAlerts());
            dto.setRequestId(s.getRequestId());
            dto.setSubscriberId(s.getSubscriberId());
            dtos.add(dto);
        }
        return dtos;
    }

    public Product getProduct(Integer productId) {
        UnitOfWorkSession ses = adminrepo.begin();
        Product prod = adminrepo.getProduct(productId);
        ses.commit();
        return prod;
    }

    public List<Transaction> getTranxs() {
        UnitOfWorkSession ses = adminrepo.begin();
        List<Transaction> tranx = adminrepo.getTranxs();
        ses.commit();
        return tranx;
    }

    public List<Messages> getMsgs() {
        UnitOfWorkSession ses = adminrepo.begin();
        List<Messages> msgs = adminrepo.getMsgs();
        ses.commit();
        return msgs;
    }

    public Messages addMessage(Messages msg) {
        UnitOfWorkSession ses = adminrepo.begin();
        Messages msg1 = adminrepo.addMessage(msg);
        ses.commit();
        return msg1;
    }

    public Messages getMsg(Integer messageId) {
        UnitOfWorkSession ses = adminrepo.begin();
        Messages msg = adminrepo.getMsg(messageId);
        ses.commit();
        return msg;
    }

    public void removeMsg(Messages msg) {
        UnitOfWorkSession ses = adminrepo.begin();
        adminrepo.removeMsg(msg);
        ses.commit();

    }

    private List<Messages> getProductMessage(Subscribers subscriber) {
        UnitOfWorkSession ses = adminrepo.begin();
        List<Messages> msg = adminrepo.getProductMessage(subscriber.getProductId());
        ses.commit();
        return msg;
    }

    public List<Requests> getRequests() {
        UnitOfWorkSession ses = adminrepo.begin();
        List<Requests> reqs = adminrepo.getRequests();
        ses.commit();
        return reqs;
    }

    public List<Transaction> getPendingTranx() {
        UnitOfWorkSession ses = adminrepo.begin();
        List<Transaction> tranx = adminrepo.getPendingTranx();
        ses.commit();
        return tranx;
    }

    public Subscribers insertSubscriber(Subscribers subscriber) {
       UnitOfWorkSession ses = adminrepo.begin();
        Subscribers sub = adminrepo.insertSubscriber(subscriber);
        ses.commit();
        return sub; 
    }

    public List<Transaction> getActiveTransactions(String SOURCEADDR, Product prod) {
       UnitOfWorkSession ses = adminrepo.begin();
        List<Transaction> tranx = adminrepo.getActiveTransactions(SOURCEADDR, prod);
        ses.commit();
        return tranx; 
    }

    public void updateTranx(Transaction trxn) {
        UnitOfWorkSession ses = adminrepo.begin();
        adminrepo.updateTranx(trxn);
        ses.commit();
    }

    public void deleteTranx(Transaction t) {
        UnitOfWorkSession ses = adminrepo.begin();
        adminrepo.deleteTraxn(t);
        ses.commit();
    }

}
