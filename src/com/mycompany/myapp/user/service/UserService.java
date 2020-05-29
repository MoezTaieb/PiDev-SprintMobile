/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.user.service;

import com.mycompany.entities.User;

/**
 *
 * @author fedi
 */
public class UserService {
    
 


    
    public void userConnected(User u)
    {
   
    
    }
      public void deconnect()
    {
      
    
    }
    
      public User  currentUser()
    {
 
    return new User("email@email.com", "user", "user", "nomUser", "prenomUser", "adresseUser", "telUser", "cinUser");
    }
      
    public User current(){
        return new User(3, "user1");
    }
    
}
