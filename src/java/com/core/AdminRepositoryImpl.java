/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.core;

import com.dto.TransactionStatus;
import com.entities.Messages;
import com.entities.Product;
import com.entities.Requests;
import com.entities.Subscribers;
import com.entities.Transaction;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;

/**
 *
 * @author Ahmed
 */
public class AdminRepositoryImpl extends RepositoryBase implements AdminRepository {

    public AdminRepositoryImpl() {
    }

    @Override
    public Requests insertRequest(Requests req) {
        if (session != null && session.isActive()) {
            manager.persist(req);
        } else {
            EntityManager manager = RepositoryManager.getManager();
            manager.getTransaction().begin();
            manager.persist(req);
            manager.getTransaction().commit();
        }
        return req;
    }

    @Override
    public Product findProduct(String keyword) {
        if (session != null && session.isActive()) {
            List<Product> product = manager.createNamedQuery("Product.findByKeyword", Product.class).setParameter("keyword", keyword).getResultList();
            return product.isEmpty() ? null : product.get(0);
        } else {
            EntityManager manager = RepositoryManager.getManager();
            List<Product> product = manager.createNamedQuery("Product.findByKeyword", Product.class).setParameter("keyword", keyword).getResultList();
            manager.close();
            return product.isEmpty() ? null : product.get(0);
        }
    }

    @Override
    public Subscribers insertSubscriber(Subscribers subscriber) {
        if (session != null && session.isActive()) {
            manager.persist(subscriber);
        } else {
            EntityManager manager = RepositoryManager.getManager();
            manager.getTransaction().begin();
            manager.persist(subscriber);
            manager.getTransaction().commit();
        }
        return subscriber;
    }

    @Override
    public List<Subscribers> PendingSubscribers() {
        if (session != null && session.isActive()) {
            List<Subscribers> subscribers = manager.createNativeQuery("SELECT * FROM Subscribers s WHERE s.remainingAlerts > 0").getResultList();
            return subscribers.isEmpty() ? null : subscribers;
        } else {
            EntityManager manager = RepositoryManager.getManager();
            List<Subscribers> subscribers = manager.createNativeQuery("SELECT * FROM Subscribers s WHERE s.remainingAlerts > 0").getResultList();
            manager.close();
            return subscribers.isEmpty() ? null : subscribers;
        }
    }

    @Override
    public Messages getMessageByProductId(Integer productId) {
        if (session != null && session.isActive()) {
            List<Messages> messages = manager.createNativeQuery("SELECT * FROM subscription.messages where productId = :productId", Messages.class).setParameter("productId", productId).getResultList();
            return messages.isEmpty() ? null : messages.get(0);
        } else {
            EntityManager manager = RepositoryManager.getManager();
            List<Messages> messages = manager.createNativeQuery("SELECT * FROM subscription.messages where productId = :productId", Messages.class).setParameter("productId", productId).getResultList();
            manager.close();
            return messages.isEmpty() ? null : messages.get(0);
        }
    }

    @Override
    public Messages getMessageByProductIdAndMobileNo(Integer productId, String mobileNo) {
        if (session != null && session.isActive()) {
            List<Messages> messages = manager.createNativeQuery("SELECT * FROM subscription.messages where productId = : productId and messageId \n"
                    + "NOT IN (SELECT messageId FROM subscription.transaction where mobileNo = : mobileNo)", Messages.class).setParameter("productId", productId).setParameter("mobileNo", mobileNo).getResultList();
            return messages.isEmpty() ? null : messages.get(0);
        } else {
            EntityManager manager = RepositoryManager.getManager();
            List<Messages> messages = manager.createNativeQuery("SELECT * FROM subscription.messages where productId = : productId and messageId \n"
                    + "NOT IN (SELECT messageId FROM subscription.transaction where mobileNo = : mobileNo)", Messages.class).setParameter("productId", productId).setParameter("mobileNo", mobileNo).getResultList();
            manager.close();
            return messages.isEmpty() ? null : messages.get(0);
        }
    }

    @Override
    public List<Product> getProductList() {
        if (session != null && session.isActive()) {
            List<Product> product = manager.createNamedQuery("Product.findAll", Product.class).getResultList();
            return product.isEmpty() ? null : product;
        } else {
            EntityManager manager = RepositoryManager.getManager();
            List<Product> product = manager.createNamedQuery("Product.findAll", Product.class).getResultList();
            manager.close();
            return product.isEmpty() ? null : product;
        }
    }

    @Override
    public Product addProduct(Product product) {
        if (session != null && session.isActive()) {
            manager.persist(product);
        } else {
            EntityManager manager = RepositoryManager.getManager();
            manager.getTransaction().begin();
            manager.persist(product);
            manager.getTransaction().commit();
        }
        return product;
    }

    @Override
    public void removeProduct(Product product) {
        if (session != null && session.isActive()) {
            product = manager.merge(product);
            manager.remove(product);
        } else {
            Log.l.infoLog.info("no session");
            EntityManager manager = RepositoryManager.getManager();
            manager.getTransaction().begin();
            product = manager.merge(product);
            manager.remove(product);
            manager.getTransaction().commit();
        }
    }

    @Override
    public void updateProduct(Product product) {
        if (session != null && !session.isActive()) {
            EntityManager manager = RepositoryManager.getManager();
            manager.getTransaction().begin();
            manager.merge(product);
            manager.getTransaction().commit();
        } else {
            manager.merge(product);
        }
    }

    @Override
    public Product getProduct(Integer productId) {
        if (session != null && session.isActive()) {
            List<Product> product = manager.createNamedQuery("Product.findByProductId", Product.class).setParameter("productId", productId).getResultList();
            return product.isEmpty() ? null : product.get(0);
        } else {
            EntityManager manager = RepositoryManager.getManager();
            List<Product> product = manager.createNamedQuery("Product.findByProductId", Product.class).setParameter("productId", productId).getResultList();
            manager.close();
            return product.isEmpty() ? null : product.get(0);
        }
    }

    @Override
    public List<Transaction> getTranxs() {
        if (session != null && session.isActive()) {
            List<Transaction> trx = manager.createNamedQuery("Transaction.findNotPending", Transaction.class).getResultList();
            return trx.isEmpty() ? null : trx;
        } else {
            EntityManager manager = RepositoryManager.getManager();
            List<Transaction> trx = manager.createNamedQuery("Transaction.findNotPending", Transaction.class).getResultList();
            manager.close();
            return trx.isEmpty() ? null : trx;
        }
    }

    @Override
    public List<Messages> getMsgs() {
        if (session != null && session.isActive()) {
            List<Messages> msgs = manager.createNamedQuery("Messages.findAll", Messages.class).getResultList();
            return msgs.isEmpty() ? null : msgs;
        } else {
            EntityManager manager = RepositoryManager.getManager();
            List<Messages> msgs = manager.createNamedQuery("Messages.findAll", Messages.class).getResultList();
            manager.close();
            return msgs.isEmpty() ? null : msgs;
        }
    }

    @Override
    public Messages addMessage(Messages msg) {
        if (session != null && session.isActive()) {
            manager.persist(msg);
        } else {
            EntityManager manager = RepositoryManager.getManager();
            manager.getTransaction().begin();
            manager.persist(msg);
            manager.getTransaction().commit();
        }
        return msg;
    }

    @Override
    public Messages getMsg(Integer messageId) {
        if (session != null && session.isActive()) {
            List<Messages> msgs = manager.createNamedQuery("Messages.findByMessageId", Messages.class).setParameter("messageId", messageId).getResultList();
            return msgs.isEmpty() ? null : msgs.get(0);
        } else {
            EntityManager manager = RepositoryManager.getManager();
            List<Messages> msgs = manager.createNamedQuery("Messages.findByMessageId", Messages.class).setParameter("messageId", messageId).getResultList();
            manager.close();
            return msgs.isEmpty() ? null : msgs.get(0);
        }
    }

    @Override
    public void removeMsg(Messages msg) {
        if (session != null && session.isActive()) {
            msg = manager.merge(msg);
            manager.remove(msg);
        } else {
            EntityManager manager = RepositoryManager.getManager();
            manager.getTransaction().begin();
            msg = manager.merge(msg);
            manager.remove(msg);
            manager.getTransaction().commit();
        }
    }

    @Override
    public List<Messages> getProductMessage(Integer productId) {
        if (session != null && session.isActive()) {
            List<Messages> msgs = manager.createNamedQuery("Messages.findByProduct", Messages.class).setParameter("productId", productId).getResultList();
            return msgs.isEmpty() ? null : msgs;
        } else {
            EntityManager manager = RepositoryManager.getManager();
            List<Messages> msgs = manager.createNamedQuery("Messages.findByProduct", Messages.class).setParameter("productId", productId).getResultList();
            manager.close();
            return msgs.isEmpty() ? null : msgs;
        }
    }

    @Override
    public void insertTranx(Transaction tranx) {
        if (session != null && session.isActive()) {
            manager.persist(tranx);
        } else {
            EntityManager manager = RepositoryManager.getManager();
            manager.getTransaction().begin();
            manager.persist(tranx);
            manager.getTransaction().commit();
        }
    }

    @Override
    public void updateRequest(Requests request) {
        if (session != null && !session.isActive()) {
            EntityManager manager = RepositoryManager.getManager();
            manager.getTransaction().begin();
            manager.merge(request);
            manager.getTransaction().commit();
        } else {
            manager.merge(request);
        }
    }

    @Override
    public List<Requests> getRequests() {
        if (session != null && session.isActive()) {
            List<Requests> trx = manager.createNamedQuery("Requests.findAll", Requests.class).getResultList();
            return trx.isEmpty() ? null : trx;
        } else {
            EntityManager manager = RepositoryManager.getManager();
            List<Requests> trx = manager.createNamedQuery("Requests.findAll", Requests.class).getResultList();
            manager.close();
            return trx.isEmpty() ? null : trx;
        }
    }

    @Override
    public List<Transaction> getPendingTranx() {
        if (session != null && session.isActive()) {
            List<Transaction> trx = manager.createNamedQuery("Transaction.findByStatus", Transaction.class).setParameter("status", TransactionStatus.PENDING.ordinal()).getResultList();
            return trx.isEmpty() ? null : trx;
        } else {
            EntityManager manager = RepositoryManager.getManager();
            List<Transaction> trx = manager.createNamedQuery("Transaction.findByStatus", Transaction.class).setParameter("status", TransactionStatus.PENDING.ordinal()).getResultList();
            manager.close();
            return trx.isEmpty() ? null : trx;
        }
    }

    @Override
    public List<Transaction> getActiveTransactions(String mobileNo, Product prod) {
        if (session != null && session.isActive()) {
            List<Transaction> tranx = manager.createNativeQuery("SELECT * FROM subscription.transaction where status=0 and subscriberId IN (SELECT subscriberId FROM subscription.subscribers  WHERE productId = "+prod.getProductId()+" and mobileNo = '"+mobileNo+"' )", Transaction.class)
                    .getResultList();
            return tranx.isEmpty() ? null : tranx;
        } else {
            EntityManager manager = RepositoryManager.getManager();
            List<Transaction> tranx = manager.createNativeQuery("SELECT * FROM subscription.transaction where status=0 and subscriberId IN (SELECT subscriberId FROM subscription.subscribers  WHERE productId = "+prod.getProductId()+" and mobileNo = '"+mobileNo+"' )", Transaction.class)
                    .getResultList();
            manager.close();
            return tranx.isEmpty() ? null : tranx;
        }
    }

    @Override
    public void updateTranx(Transaction trxn) {
        if (session != null && !session.isActive()) {
            EntityManager manager = RepositoryManager.getManager();
            manager.getTransaction().begin();
            manager.merge(trxn);
            manager.getTransaction().commit();
        } else {
            manager.merge(trxn);
        }
    }
    
    
     @Override
    public void deleteTraxn(Transaction trxn) {
      
      if (session!=null&&session.isActive()){   
          trxn=manager.merge(trxn);
          manager.remove(trxn);          
      }      
      else{
        EntityManager manager = RepositoryManager.getManager();
        manager.getTransaction().begin();
        trxn=manager.merge(trxn);
        manager.remove(trxn);
        manager.getTransaction().commit();
        } 
    }
    
    
    
    

}
