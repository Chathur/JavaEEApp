/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic;

import DTO.User;
import Model.Members;
import Model.Payments;
import Model.Users;
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
public class PaymentService {
    
    public boolean updatePayment(User user, double payment)
    {
        Session session = null;
        Transaction tx = null;
        boolean returnValue = false;
        
        try
        {
                session = NewHibernateUtil.getSessionFactory().openSession();
                tx = session.getTransaction();
                tx.begin();
                
                Query query = session.createQuery("SELECT * FROM users where id='"+ user.getID() +"'");
                Members queryResult = (Members)query.uniqueResult();// stmt.executeQuery(sql);
                queryResult.setBalance((float) (queryResult.getBalance()+ payment));
                session.update(queryResult);
                
                Payments paymentModel = new Payments();
                paymentModel.setMemId(user.getID());
                paymentModel.setTypeOfPayment("PAYMENT");
                paymentModel.setAmount((float) payment);
                paymentModel.setDate(new Date());
                
                session.save(paymentModel);
                tx.commit();
                
                returnValue = true;

        }
        catch(Exception e)
        {
                if (tx != null)
                {
                    tx.rollback();
                }
                e.printStackTrace();
        }
        finally
        {
            session.close();
        }
        
        return returnValue;
    }
    
    public List getAllRecords()
    {
        Session session = null;
        Transaction tx = null;
        List list = new ArrayList();
        
        session = NewHibernateUtil.getSessionFactory().openSession();
        tx = session.getTransaction();
        tx.begin();

        Query query = session.createQuery("SELECT * FROM payments");
        List<Payments> queryResult = (List<Payments>)query.uniqueResult();
        
        queryResult.forEach(element -> {
            list.add(element);
        });
        
        tx.commit();
        session.close();
        return list;
    }
    
    public List getRecordsById(User user)
    {
        Session session = null;
        Transaction tx = null;
        List list = new ArrayList();
        
        session = NewHibernateUtil.getSessionFactory().openSession();
        tx = session.getTransaction();
        tx.begin();
        Query query = session.createQuery("SELECT * FROM payments WHERE mem_id='"+ user.getID() +"'");
        List<Payments> queryResult = (List<Payments>)query.uniqueResult();
        queryResult.forEach(element -> {
            list.add(element);
        });
        
        session.close();
        return list;
    }
    
    public void chargePayment(List list,double amount)throws SQLException{
        Session session = null;
        Transaction tx = null;
        
        session = NewHibernateUtil.getSessionFactory().openSession();
        tx = session.getTransaction();
        tx.begin();
        
        for (Object list1 : list) {
            User user = (User) list1;
            if(!user.isUserValid().equals("DELETED")){
                Query query = session.createQuery("UPDATE members SET balance=balance-"+ amount +
                        " WHERE id='"+ user.getID() + "'");
                query.executeUpdate();
                tx.commit();
            }
        }
        session.close();
    }
}
