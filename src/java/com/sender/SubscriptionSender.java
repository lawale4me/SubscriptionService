/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sender;

import com.core.AdminRepositoryImpl;
import com.core.AppManager;
import com.core.SubscriptionException;
import com.dto.SubscriberDTO;
import com.entities.Messages;
import com.entities.Product;
import com.entities.Subscribers;
import com.util.SendSMS;
import java.util.List;

/**
 *
 * @author Ahmed
 */
public class SubscriptionSender implements Runnable 
{

    @Override
    public void run()
    {
        
        
        
        try 
        {
            AppManager manager=new AppManager(new AdminRepositoryImpl());
            List<SubscriberDTO> pendingList = manager.PendingSubscribersDTO();
            List<Subscribers> pendingLs = manager.PendingSubscribers();
            System.out.println("Try run method");
            int i=0;
            
            for(SubscriberDTO subscriber : pendingList)
            {            
                System.out.println("=============== Started SubcriptionSender Task Runner ================");
                System.out.println("The subscriber Phone "+subscriber.getMobileNo());
                Messages msg = manager.prepareMessage(pendingLs.get(i));
                SendSMS sendSMS=new SendSMS();
                Product prod=manager.getProduct(pendingLs.get(i).getProductId());
                sendSMS.sendMessage(subscriber.getMobileNo(),msg.getMessage(), prod.getProductName());
                System.out.println("=============== Completed SubcriptionSender Task Runner ================");
              i++;
            }
            
        }catch (SubscriptionException ex) {
            ex.printStackTrace();            
            System.out.println("The error message is "+ex.getMessage());            
        } 
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("The error message is "+ex.getLocalizedMessage());
            System.out.println("The error message is "+ex.getMessage());            
        }
    }
    
}
