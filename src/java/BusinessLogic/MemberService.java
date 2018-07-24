/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic;

import DTO.Registration;
import DTO.User;
import Model.Members;
import Model.Users;
import Util.NewHibernateUtil;
import java.io.IOException;
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
public class MemberService {
    
    public List getAllRecords()
    {
        Session session = null;
        Transaction tx = null;
        List list = new ArrayList<>();
        User userModel = new User();
        
        session = NewHibernateUtil.getSessionFactory().openSession();
        tx = session.getTransaction();
        tx.begin();

        Query query = session.createQuery("FROM Members");
        List<Members> queryResult = (List<Members>)query.list();
        
        queryResult.forEach(element -> {
            
            userModel.setID(element.getId());
            userModel.setAddress(element.getAddress());
            userModel.setBalance(element.getBalance());
            //userModel.setDOB(element.getDob().toString());
            userModel.setDOR(element.getDor());
            userModel.setFirstName(element.getName());
            userModel.setLastName(element.getStatus());
            userModel.setUserValid(element.getStatus());
            
            list.add(userModel);
        });
        
        tx.commit();
        session.close();
        return list;
    }
    
    public User getSingleById(String id){
        User user = new User();
        
        Session session = null;
        Transaction tx = null;
        List list = new ArrayList();
        
        session = NewHibernateUtil.getSessionFactory().openSession();
        tx = session.getTransaction();
        tx.begin();

        Query query = session.createQuery("FROM Members where id ='" +id + "'");
        Members queryResult = (Members)query.uniqueResult();
        
        user.setID(queryResult.getId());
        user.setFirstName(queryResult.getName());
        user.setLastName(queryResult.getStatus());
        user.setAddress(queryResult.getAddress());
        user.setDOB(queryResult.getDob().toString());
        user.setDOR(queryResult.getDor());
        user.setBalance(queryResult.getBalance());
        user.setUserValid(queryResult.getStatus());
        
        tx.commit();
        session.close();
        
        return user;
    }
    
    public List getRecordsById(String id)
    {
        Session session = null;
        Transaction tx = null;
        List list = new ArrayList();
        
        session = NewHibernateUtil.getSessionFactory().openSession();
        tx = session.getTransaction();
        tx.begin();

        Query query = session.createQuery("FROM Members where id = '" +id+"'");
        List<Members> queryResult = (List<Members>)query.list();
        
        queryResult.forEach(element -> {
            list.add(element);
        });
        
        tx.commit();
        session.close();
        return list;
    }
    
    public List getRecordsByStatus(String status)
    {
        Session session = null;
        Transaction tx = null;
        List list = new ArrayList();
        
        session = NewHibernateUtil.getSessionFactory().openSession();
        tx = session.getTransaction();
        tx.begin();

        Query query = session.createQuery("FROM Members where status = '" +status+"'");
        List<Members> queryResult = (List<Members>)query.list();
        
        queryResult.forEach(element -> {
            list.add(element);
        });
        
        tx.commit();
        session.close();
        return list;
    }
    
    public String getColumn(String id,String column){
        //Session session = null;
        //Transaction tx = null;
        //List list = new ArrayList();
        
        //session = NewHibernateUtil.getSessionFactory().openSession();
        //tx = session.getTransaction();
       // tx.begin();
        
     //   Query query = session.createQuery( "SELECT " + column + " FROM members WHERE id='" + id + "'");
     //   String result = "";
     //   try{
     //       Members queryResult = (Members)query.uniqueResult();
     //           result = queryResult.getString(column);
      //      }
      //      rs.close();
      //      conn.close();
    return null;  
    }
    
    public String RegisterUser(Registration newUser) throws SQLException, IOException{
            
            String result = "false";
            Session s = null;
            try
            {
            s = NewHibernateUtil.getSessionFactory().openSession();
            Transaction tr = s.getTransaction();
            
            Users userModel = new Users();
                userModel.setId(newUser.getID());
                userModel.setPassword(newUser.getPassword());
                userModel.setStatus("APPLIED");
            
            Members memberModel = new Members();
                    memberModel.setId(newUser.getID());
                    memberModel.setName(result);
                    memberModel.setAddress(result);
                    memberModel.setDob(null);
                    memberModel.setDor(new Date());
                    memberModel.setStatus("Applied");
                    memberModel.setBalance((float) -10.00);
                    

            tr.begin();
            s.save(newUser);
            tr.commit();

            result = "User details successfully updated.";
            return result;
            }
            catch (Exception e)
            {
            return (e.getMessage());
            }
            finally
            {
                s.close();
            }
        }
     
    public String deleteUser(String id)throws SQLException{
        
        Session session = null;
        Transaction tx = null;
        List list = new ArrayList();
        
        session = NewHibernateUtil.getSessionFactory().openSession();
        tx = session.getTransaction();
        tx.begin();

        Query query1 = session.createQuery("DELETE FROM Users WHERE id='" + id + "'");
        Query query2 = session.createQuery("UPDATE Members SET status='DELETED' WHERE id='" + id + "';");
        query1.uniqueResult();
        query2.uniqueResult();
        
        String result = "User " + id + " has been deleted.";
        
        return result;
    }
    
}
