/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;


/**
 *
 * @author Chathuranga
 */


public class Payments {
    private String id;
    private String type;
    private String amount;
    private String date;
    
    public Payments(){}
    public Payments(String id,String type,String amount,String date){
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public String getDate() {
        return date;
    }
    
    public Date returnDate()throws ParseException{
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        Date returnDate ;
        returnDate = dateformat.parse(date);
        return returnDate;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
