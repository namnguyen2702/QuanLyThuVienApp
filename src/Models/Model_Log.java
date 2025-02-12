/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class Model_Log {
    protected String user,action;
    protected Date time;

    public String getAction() {
        return action;
    }

    public Date getTime() {
        return time;
    }

    public String getUser() {
        return user;
    }
    
}
