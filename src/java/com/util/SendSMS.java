/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 *
 * @author Ahmed
 */
public class SendSMS 
{
    
    private String gatewayHost="121.241.242.114";
    private String gatewayPort="8080";
    private String gatewayAppContext = "/bulksms/bulksms";
    
    //http://121.241.242.114:8080/bulksms/bulksms?
    //username=ruby-testuser&password=g0thb01&type=0&dlr=1
    //&destination=2348032601520&source=KayodeTest&message="This is a test @ 11:08am"
    

    public String urlComposer(String gatewayHost, String gatewayPort, 
            String sourceMsisdn, String destinationMsisdn, String text) throws UnsupportedEncodingException 
    {

        StringBuilder param = new StringBuilder();
        // Compose BaseUrl
        param.append("http://").append(this.gatewayHost).append(":");        
        param.append(this.gatewayPort).append(this.gatewayAppContext);
        param.append("?username=").append(URLEncoder.encode("ruby-testuser", "UTF-8"));    // an alphanumeric sender address
        param.append("&password=").append("g0thb01");
        param.append("&type=").append("0");
        param.append("&dlr=").append("1");
        param.append("&destination=").append(destinationMsisdn);
        param.append("&source=").append(sourceMsisdn);
        param.append("&message=").append(URLEncoder.encode(text, "UTF-8"));
        param.append("&dlr-mask=").append("31");
        return param.toString();
    }
    
    
    
     public String sendMessage(String phone,String message,String header) throws MalformedURLException, IOException {
        String sourceMsisdn = phone;
        String text = message;

            
            System.out.println(sourceMsisdn);
            System.out.println(text);
             String status = "";
           String formattedDestMsisdn = phone;        
        System.out.println(formattedDestMsisdn);
        if (formattedDestMsisdn.equals("invalid")) {
            return "invalid";
        } else {
            URL aPortUrl = new URL(urlComposer(this.gatewayHost,
                    this.gatewayPort, header, formattedDestMsisdn, text));
            StringBuilder aResult = new StringBuilder();
            try {
                URLConnection con = aPortUrl.openConnection();
                status = "sent";
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    aResult.append(inputLine);
                }
                in.close();
            } catch (Exception e) {
                System.out.println("Connection unvailable, message not sent... will try later");
        
                System.out.println(e.getMessage());
                e.printStackTrace();
                return "error";
            }
            return status;
        }
    }

    
    

}
