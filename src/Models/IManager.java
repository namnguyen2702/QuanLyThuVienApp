/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public interface IManager {
    public abstract void loadUser(DefaultTableModel model);
    public abstract String[] loadAnalytics();
}
