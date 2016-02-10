/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderProcessing;

/**
 *
 * @author Matt
 */
public class InventoryItem {
    private String name;
    private int quantity;
    private double cost;
            
    public InventoryItem()
    {
    }
    public InventoryItem(String _name, int _quantity, double _cost)
    {
        name = _name;
        quantity = _quantity;
        cost = _cost;
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
    }
    public synchronized void increaseQuantityBy(int _quantity)
    {
        System.out.println("adding " + _quantity + " to " + this.name + " quantity");
        this.quantity += _quantity; 
        
    }
    public synchronized void decreaseQuantityBy(int _quantity)
    {
        this.quantity -= _quantity; 
    }
}
