/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic;

import DTO.Registration;
import Model.Members;
import Model.Users;
import Util.NewHibernateUtil;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Chathuranga
 */
public class RegistrationService {
    // Method to check if the user is already exists
        public boolean checkExist(String id) throws SQLException{
            Session session = null;
            Transaction tx = null;
            boolean result = false;
            
            try
            {
                session = NewHibernateUtil.getSessionFactory().openSession();
                tx = session.getTransaction();
                tx.begin();

                Query query = session.createQuery("SELECT * FROM users where id='"+ id +"'");
                Users queryResult = (Users)query.uniqueResult();// stmt.executeQuery(sql);

                if(queryResult.getId().equals(id)){
                    result = true;
                }
                tx.commit();
            }
            catch (Exception e)
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

        
            return result;
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
            s.save(userModel);
            s.save(memberModel);
            tr.commit();

            result = "Successfully registered. Username : " + newUser.getID() + " Password : " + newUser.getPassword() + ".Proceed to login.";
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
    
}
