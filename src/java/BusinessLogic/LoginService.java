/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic;

import DTO.User;
import Model.Users;
import Util.NewHibernateUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Chathuranga
 */
public class LoginService {
        public String authenticateUser (User userInput){
            
            String result = "false";
            Session session = null;
            Transaction tx = null;
        
            String username = userInput.getID();
            String passwordinput = userInput.getPassword();
            
            session = NewHibernateUtil.getSessionFactory().openSession();
            tx = session.getTransaction();
            tx.begin();
        
            Query query = session.createQuery("SELECT * FROM users where id ='" + username + "' and password ='"+ passwordinput +"'");
            Users queryResult = (Users)query.uniqueResult();
            String usernameDB = queryResult.getId();
            String passwordDB = queryResult.getPassword();
            if (username.equals(usernameDB) && passwordinput.equals(passwordDB)){
                if(queryResult.getStatus().equals("ADMIN"))
                    result = "ADMIN";
                else
                    result = "SUCCESS";
            }
            tx.commit();
            session.close();
        
            return result;
        }
    
}
