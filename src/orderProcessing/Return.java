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
    PointOfSaleUI ui = PointOfSaleUI.getInstance();

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
        this.log("TRANSACTION " + this.getTransactionID() + " - RETURN - Start Transaction");

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
                        
                        String newItemName = this.quantity > 1 ? itemName + 's' : itemName;
                        ui.sendAlertMessage("Successfully returned " + this.quantity + " " + newItemName);                        
                        
                        this.log("TRANSACTION " + this.getTransactionID() + " - RETURN - Removed " + cashToReturnToCustomer + " From Bank Account");
                        this.log("TRANSACTION " + this.getTransactionID() + " - RETURN - Increased " + item.getName() + " Quantity By " + this.quantity);
                    }
                    else
                    {
                        ui.sendAlertMessage("we don't have enough money to give you. sorry");                        
                        this.log("TRANSACTION " + this.getTransactionID() + " - RETURN - Not Enough Money In Bank Account. Return Canceled");

                    }
                }
            }
        } else {
            // item does not exist in the inventory
            ui.sendAlertMessage("we do not carry this item");                        
            this.log("TRANSACTION " + this.getTransactionID() + " - RETURN - Do Not Carry Item. Return Canceled");
        }
        
        this.log("TRANSACTION " + this.getTransactionID() + " - Return - End Transaction");
    }
    
    @Override
    public void run() {
        this.process();
    }
    
}
