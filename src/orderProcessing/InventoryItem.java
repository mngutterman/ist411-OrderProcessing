/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderProcessing;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Matt
 */
public class InventoryItem {
    
    private int id;
    private String name;
    private int quantity;
    private double cost;
            
    public InventoryItem()
    {
    }
    public InventoryItem(int _id, String _name, int _quantity, double _cost)
    {
        id = _id;
        name = _name;
        quantity = _quantity;
        cost = _cost;
    }
    
    public int getId()
    {
        return this.id;
    }
    
    public synchronized double getCost()
    {
        return this.cost;
    }
    
    public synchronized String getName()
    {
        return this.name;
    }
    
    public synchronized int getQuantity()
    {
        return this.quantity;
    }
    public synchronized void setQuantity(int _quantity)
    {
        this.quantity = _quantity;
        
        try{
            Connection conn = dbConnect.ConnectionToMySql();
            Statement stmt = conn.createStatement();
            String sql = "UPDATE inventoryitems SET itemQuantity=" + this.quantity + " WHERE itemId=" + this.id;
            stmt.executeUpdate(sql);
            System.out.println("here");
        }
        catch(Exception ex){
            ex.printStackTrace();
        } 
    }
    public synchronized void increaseQuantityBy(int _quantity)
    {
        this.quantity += _quantity;
        
        try{
            Connection conn = dbConnect.ConnectionToMySql();
            Statement stmt = conn.createStatement();
            String sql = "UPDATE inventoryitems SET itemQuantity=" + this.quantity + " WHERE itemId=" + this.id;
            stmt.executeUpdate(sql);
            System.out.println("here");
        }
        catch(Exception ex){
            ex.printStackTrace();
        } 
        
        System.out.println("INVENTORY - Increase " + this.name + " Quantity by " + _quantity);
        System.out.println("INVENTORY - Current Quantity Of " + this.name + " Is " + this.quantity);
    }
    public synchronized void decreaseQuantityBy(int _quantity)
    {
        this.quantity -= _quantity;
        
        try{
            Connection conn = dbConnect.ConnectionToMySql();
            Statement stmt = conn.createStatement();
            String sql = "UPDATE inventoryitems SET itemQuantity=" + this.quantity + " WHERE itemId=" + this.id;
            stmt.executeUpdate(sql);
            System.out.println("here");
        }
        catch(Exception ex){
            ex.printStackTrace();
        } 
        
        System.out.println("INVENTORY - Decrease " + this.name + " Quantity by " + _quantity);
        System.out.println("INVENTORY - Current Quantity Of " + this.name + " Is " + this.quantity);
    }
}
