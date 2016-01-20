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
public class inventoryItem {
    private String name;
    private int quantity;
            
    public inventoryItem()
    {
    }
    public inventoryItem(String _name, int _quantity)
    {
        name = _name;
        quantity = _quantity;
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
