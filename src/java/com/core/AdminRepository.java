/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.core;



import com.entities.Messages;
import com.entities.Product;
import com.entities.Requests;
import com.entities.Subscribers;
import com.entities.Transaction;
import java.util.List;


/**
 *
 * @author Ahmed
 */
public interface AdminRepository extends UnitOfWork
{   

    public Requests insertRequest(Requests req);

    public Product findProduct(String keyword);

    public Subscribers insertSubscriber(Subscribers subscriber);

    public List<Subscribers> PendingSubscribers();

    public Messages getMessageByProductId(Integer productId);

    public Messages getMessageByProductIdAndMobileNo(Integer productId, String mobileNo);

    public List<Product> getProductList();

    public Product addProduct(Product product);

    public void removeProduct(Product product);

    public void updateProduct(Product product);

    public Product getProduct(Integer productId);

    public List<Transaction> getTranxs();

    public List<Messages> getMsgs();

    public Messages addMessage(Messages msg);

    public Messages getMsg(Integer messageId);

    public void removeMsg(Messages msg);

    public List<Messages> getProductMessage(Integer productId);

    public void insertTranx(Transaction tranx);

    public void updateRequest(Requests request);

    public List<Requests> getRequests();

    public List<Transaction> getPendingTranx();

    public List<Transaction> getActiveTransactions(String mobileNo, Product prod);

    public void updateTranx(Transaction trxn);
    
    public void deleteTraxn(Transaction trxn);
    
}
