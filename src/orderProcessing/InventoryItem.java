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
    
    public double getCost()
    {
        return cost;
    }
    
    public String getName()
    {
        return name;
    }
    
    public int getQuantity()
    {
        return quantity;
    }
    public void setQuantity(int _quantity)
    {
        quantity = _quantity; 
    }
    public void increaseQuantityBy(int _quantity)
    {
        quantity += _quantity; 
    }
    public boolean decreaseQuantityBy(int _quantity)
    {
        if(quantity - _quantity >= 0)
        {
            quantity -= _quantity; 
            return true;
        }
        else
        {
            return false;
        }
    }
}
