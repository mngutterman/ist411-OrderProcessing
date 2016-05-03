/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderProcessing;

import java.io.*;
import java.net.*;
import java.util.*;
public class ServerMessageHandler extends Thread
{
    public static final int INDEX_ORDER_TYPE = 0;
    public static final int INDEX_FIRST_ITEM_NAME = 1;
    public static final int INDEX_FIRST_ITEM_QUANTITY = 2;
    public static final int INDEX_SECOND_ITEM_NAME = 3;
    
    public static final int BUY = 1;
    public static final int RETURN = 2;
    public static final int INVENTORY_ADJUSTMENT = 3;
    public static final int EXCHANGE = 4;
    
    ArrayList<Socket> clientList;
    Socket clientConnection;
    int clientNumber;
    String transaction = "";
    
    public ServerMessageHandler(int clientNum, ArrayList<Socket> cList)
    {
        this.clientList = cList;
        this.clientConnection = this.clientList.get(clientNum);
        this.clientNumber = this.clientList.indexOf(clientConnection);
        System.out.println("Index of this ServerMessageHandler in clientList: " + clientNumber);
    }

    public void listenForMessage()
    {
        boolean keepGoing = true;
        System.out.println("clientCount in listenForMessage: " + this.clientList.size());
        while(keepGoing)
        {
            try
            {
                DataInputStream dataInputStream = new DataInputStream(this.clientConnection.getInputStream());
                transaction = dataInputStream.readUTF();
                //System.out.println("Message from " + this.clientConnection.getRemoteSocketAddress() + ": " + transaction);
                
                
                System.out.println("Transaction Details: " + transaction);
                processTransaction(transaction);
                killConnection();
                return;
            }
            catch(IOException e)
            {
                    System.out.println("IOException in ServerMessageHandler listenForMessage()");
            }
        }
    }

    public void processTransaction(String transaction)
    {
            System.out.println("clientCount in sendMessage: " + this.clientList.size());
            try
            {
                String[] transactionPieces = transaction.split(",");//new String[NUM_TRANSACTION_PIECES];
                int orderType = Integer.parseInt(transactionPieces[INDEX_ORDER_TYPE]);
                String firstItemName = transactionPieces[INDEX_FIRST_ITEM_NAME];
                int firstItemQuantity = Integer.parseInt(transactionPieces[INDEX_FIRST_ITEM_QUANTITY]);

                /*System.out.println("orderType: " + orderType);
                System.out.println("firstItemName: " + firstItemName);
                System.out.println("firstItemQuantity: " + firstItemQuantity);
                System.out.println("secondItemName: " + secondItemName);*/

                switch(orderType){
                    case BUY:
                        OrderProcessing.submitBuyTransaction(firstItemName, firstItemQuantity);
                        break;
                    case RETURN:
                        OrderProcessing.submitReturnTransaction(firstItemName, firstItemQuantity);
                        break;
                    case INVENTORY_ADJUSTMENT:
                        OrderProcessing.submitInventoryAdjustmentTransaction(firstItemName, firstItemQuantity);
                        break;
                    case EXCHANGE:
                        String secondItemName = transactionPieces[INDEX_SECOND_ITEM_NAME];
                        OrderProcessing.submitExchangeTransaction(firstItemName, secondItemName);
                        break;
                }
                
                
                DataOutputStream dataOutToClient = new DataOutputStream(this.clientList.get(clientNumber).getOutputStream());
                //System.out.println("Message from " + this.clientConnection.getRemoteSocketAddress() + ": " + message);
                dataOutToClient.writeUTF("Message from " + this.clientConnection.getRemoteSocketAddress() + ": SUCESSFULL!?!?!" /*+ message*/);

            }
            catch(IOException e)
            {
                System.out.println("IOException in ServerMessageHandler sendMessage()");
            }
            return;
    }

    public void killConnection()
    {
        try
        {
                this.clientConnection.close();
                this.clientList.remove(this.clientList.indexOf(clientConnection));
        }
        catch(IOException e)
        {
                System.out.println("IOException in ServerMessageHandler killConnection()");
        }
        return;

    }

    public void run()
    {
            this.listenForMessage();
            return;
    }
}