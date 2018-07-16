package Model;
// Generated Jul 15, 2018 12:28:05 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Payments generated by hbm2java
 */
public class Payments  implements java.io.Serializable {


     private Integer id;
     private String memId;
     private String typeOfPayment;
     private float amount;
     private Date date;

    public Payments() {
    }

    public Payments(String memId, String typeOfPayment, float amount, Date date) {
       this.memId = memId;
       this.typeOfPayment = typeOfPayment;
       this.amount = amount;
       this.date = date;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getMemId() {
        return this.memId;
    }
    
    public void setMemId(String memId) {
        this.memId = memId;
    }
    public String getTypeOfPayment() {
        return this.typeOfPayment;
    }
    
    public void setTypeOfPayment(String typeOfPayment) {
        this.typeOfPayment = typeOfPayment;
    }
    public float getAmount() {
        return this.amount;
    }
    
    public void setAmount(float amount) {
        this.amount = amount;
    }
    public Date getDate() {
        return this.date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }




}

