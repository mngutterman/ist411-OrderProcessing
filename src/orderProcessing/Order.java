/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderProcessing;

import java.util.Map;
import java.util.Random;
import java.util.Scanner;
//import static orderProcessing.OrderProcessing.NUM_TRANSACTION_TYPES;

/**
 *
 * @author mdr5325
 */
public class Order extends Transaction {
        
    public static final int YES = 1;
    
    Inventory inventory = Inventory.getInstance();
    PointOfSaleUI ui = PointOfSaleUI.getInstance();
    
    private String itemName;
    private int quantity;
    private Customer customer;
    
    /*public Order(int id) {
        super(id);
    }*/
    
    public Order(int id, String _itemName, int _quantity, Customer _customer) {
        super(id);
        this.itemName = _itemName;
        this.quantity = _quantity;
        this.customer = _customer;
    }
    
    @Override
    public void process()
    {
        this.log("TRANSACTION " + this.getTransactionID() + " - ORDER - Start Transaction");
        
        Map<String, InventoryItem> items = inventory.getItems();
        InventoryItem item = items.get(itemName);
        
        if (item != null) {
            // this must be synchronized because if a different customer buys this item mid order,
            // you will still assume the item has it's original quantity
            synchronized(item){

                // item exists in the inventory
                int stockOfItem = item.getQuantity();

                if (stockOfItem >= this.quantity)
                {
                    // create a sale
                    this.log("TRANSACTION " + this.getTransactionID() + " - ORDER - Initiated Sale");
                    Sale sale = new Sale(this.transactionID,item.getName(),quantity, customer);
                    sale.process();
                }
                else if (stockOfItem > 0)
                {
                    // not enough in stock to fullfill order
                    /*Scanner reader = new Scanner(System.in);  // Reading from System.in
                    this.log("There is only " + stockOfItem + " " + itemName + "(s) left. Would you like to purchase that amount? (y/n): ");
                    String response = reader.nextLine().toLowerCase();
                     */
                    ui.sendAlertMessage("Not enough inventory to complete full order");           
                    this.log("TRANSACTION " + this.getTransactionID() + " - ORDER - Not Enough Inventory. Order Max Amount?");
                    
                    Random rand = new Random();
                    int randomNumber = rand.nextInt(2) + 1;

                    boolean customerWantsRemainingInventory = randomNumber == YES;

                   
                    if (customerWantsRemainingInventory/*response.equals(YES)*/)
                    {
                        this.log("TRANSACTION " + this.getTransactionID() + " - ORDER - Customer Said Yes");
                        this.log("TRANSACTION " + this.getTransactionID() + " - ORDER - Initiated Modified Sale");
                        Sale sale = new Sale(this.transactionID,item.getName(),stockOfItem, customer);
                        sale.process();
                    }
                    else
                    {
                        ui.sendAlertMessage("Transaction canceled");
                        this.log("TRANSACTION " + this.getTransactionID() + " - ORDER - Customer Said No. Order Canceled");
                    }                

                } 
                else
                {
                    ui.sendAlertMessage("item out of stock. transaction canceled");
                    this.log("TRANSACTION " + this.getTransactionID() + " - ORDER - Item Out Of Stock. Order Canceled");
                }

            } 
        }
        else 
        {
            // item does not exist in the inventory
            ui.sendAlertMessage("we do not carry this item. transaction canceled");
            this.log("TRANSACTION " + this.getTransactionID() + " - ORDER - Do Not Carry Item. Order Canceled");
        }
        
        this.log("TRANSACTION " + this.getTransactionID() + " - ORDER - End Transaction");
    }
    
    @Override
    public void run() {
        this.process();
    }
}