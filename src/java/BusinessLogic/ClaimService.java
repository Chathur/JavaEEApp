/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic;

import DTO.User;
import Model.Claims;
import Util.NewHibernateUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Chathuranga
 */
public class ClaimService {
    
    public List getAllClaims(){
        List list = new ArrayList();
        Session session = null;
        Transaction tx = null;
        
        session = NewHibernateUtil.getSessionFactory().openSession();
        tx = session.getTransaction();
        tx.begin();
        Query query = session.createQuery("FROM Claims");
        List<Claims> claimsModel = (List<Claims>) query.list();
        claimsModel.forEach(element -> {
            list.add(element);
        });
        
        session.close();

        return list;
}
    
    
    public List getClaimsById(User user){
        
        List list = new ArrayList();
        Session session = null;
        Transaction tx = null;
        
        session = NewHibernateUtil.getSessionFactory().openSession();
        tx = session.getTransaction();
        tx.begin();
        Query query = session.createQuery("FROM Claims WHERE mem_id='"+ user.getID() +"'");
        List<Claims> claimsModel = (List<Claims>) query.list();
        claimsModel.forEach(element -> {
            list.add(element);
        });
        session.close();
        return list;
            
    } 
    
    public boolean add_claim(String userid, Double claimAmount, String claimRational){
        
        Session session = null;
        Transaction tx = null;
        boolean result = false;
        
        session = NewHibernateUtil.getSessionFactory().openSession();
        tx = session.getTransaction();
        tx.begin();
        Claims claimModel = new Claims();
            claimModel.setId(Integer.parseInt(userid));
            claimModel.setDate(new Date());
            claimModel.setRationale(claimRational);
            claimModel.setStatus("SUBMITTED");
            claimModel.setAmount(Float.parseFloat(claimAmount.toString()));
        session.save(claimModel);
        tx.commit();
        session.close();
        
        result = true;
        return result;
    }
    
    public void updateClaim(int id, String status){
        
        Session session = null;
        Transaction tx = null;
        session = NewHibernateUtil.getSessionFactory().openSession();
        tx = session.getTransaction();
        tx.begin();
        
        Query query = session.createQuery("FROM claims WHERE id="+ id );
        Claims claimModel = (Claims) query.uniqueResult();
        
        claimModel.setStatus(status);
        
        session.update(claimModel);
        tx.commit();
        session.close();

    }
    
    
    
}
