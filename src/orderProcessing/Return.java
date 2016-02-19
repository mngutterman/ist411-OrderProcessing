/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderProcessing;

import java.util.Map;

/**
 *
 * @author mdr5325
 */
public class Return extends Transaction{
    Inventory inventory = Inventory.getInstance();
    private String itemName;
    private int quantity;
    
    public Return(int id, String _itemName, int _quantity) {
        super(id);
        this.itemName = _itemName;
        this.quantity = _quantity;
    }
    
    @Override
    public void process()
    {
        Map<String, InventoryItem> items = inventory.getItems();
        
        // If we do not carry the item, cancel the transaction
        InventoryItem item = items.get(itemName);
        if (item != null) {
            double cashToReturnToCustomer = item.getCost() * this.quantity;
            
            // this must be synchronized on the inventory incase the inventory's cash
            // dips below what you need after you check it in the if statement.
            
            // SEEMS THREAD SAFE
            synchronized(item){
                synchronized(inventory.getBankAccount()){

                    if (cashToReturnToCustomer < inventory.getCashFromBankAccount()/*inventory.getCash()*/)
                    {
                        inventory.removeMoneyFromBankAccount(cashToReturnToCustomer);
                        //inventory.removeCash(cashToReturnToCustomer);
                        item.increaseQuantityBy(this.quantity);
                    }
                    else
                    {
                        System.out.println("We do not have enough cash to give to you, sorry.");
                    }
                }
            }
        } else {
            // item does not exist in the inventory
            System.out.println("We do not carry this item.");
        }
        
        
    }
    
    @Override
    public void run() {
        this.process();
    }
    
}
