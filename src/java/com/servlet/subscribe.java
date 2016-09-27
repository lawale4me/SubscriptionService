/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servlet;

import com.core.AdminRepositoryImpl;
import com.core.AppManager;
import com.dto.RequestStatus;
import com.dto.TransactionStatus;
import com.entities.Product;
import com.entities.Requests;
import com.entities.Subscribers;
import com.entities.Transaction;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ahmed
 */
public class subscribe extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String responseMessage;
        try {

            String SOURCEADDR = request.getParameter("SOURCEADDR");
            String MESSAGE = request.getParameter("MESSAGE");
            String DESTADDR = request.getParameter("DESTADDR");

            System.out.println("the phone recieved is " + SOURCEADDR);
            AppManager manager = new AppManager(new AdminRepositoryImpl());

            Requests req = new Requests();
            req.setDateInserted(new Date());
            req.setDestAddress(DESTADDR);
            req.setMessageContent(MESSAGE);
            req.setSourceAddress(SOURCEADDR);
            req.setStatusID(RequestStatus.PENDING.ordinal());

            Requests req1 = manager.insertRequest(req);

            //***********************************
            System.out.println("actual from shorcode: " + MESSAGE + " SOURCEADDR " + SOURCEADDR);

            StringBuilder dResult = new StringBuilder();
            String reqArray[];
            if (MESSAGE.contains("*")) {
                String ray[] = MESSAGE.split("\\*");
                reqArray = ray;
            } else {
                String ray[] = MESSAGE.split(" ");
                reqArray = ray;
            }
            String keyword = reqArray[0];

            Product product = manager.findProduct(keyword);

            if (product != null && !keyword.equalsIgnoreCase("STOP")) {

                Subscribers subscriber = new Subscribers();
                subscriber.setMobileNo(SOURCEADDR);
                subscriber.setProductId(product.getProductId());
                subscriber.setRemainingAlerts(product.getNoOfSubscription());
                subscriber.setRequestId(req1);
                subscriber = manager.insertSubscriber(subscriber);
                responseMessage = manager.subscribeUser(product, subscriber, req1);

            } else if (keyword.equalsIgnoreCase("STOP")) {
                Product prod = manager.findProduct(reqArray[1]);               
               
                if (prod != null) {
                    List<Transaction> tranxs =  manager.getActiveTransactions(SOURCEADDR, prod);
                    
                    if(tranxs!=null){
                        System.out.println("No of transactions:"+tranxs.size());
                       for(Transaction t:tranxs){
                           t.setStatus(TransactionStatus.CANCELED.ordinal());
                          // manager.updateTranx(t);
                           manager.deleteTranx(t);
                       }
                    responseMessage = "You have successfully opted out of the "
                            + prod.getProductName() +" service service. To "
                            + "subscribe again, text "+ prod.getKeyword()+
                            " to 33100";}
                    else{
                        responseMessage = "no pending subscription";    
                    }
                } else {
                    responseMessage = "Invalid request";
                }
            } else {

                responseMessage = "Invalid request";

            }

            //***********************************
            //manager.logTransaction("CreditWallet", "Not Applicable", "Cellulant", phone,tpResponse.toString());
            out.println(responseMessage);
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
