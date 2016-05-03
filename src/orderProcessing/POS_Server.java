/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderProcessing;

import java.net.*;
import java.io.*;
import java.util.*;

public class POS_Server extends Thread
{
	private ServerSocket serverSocket;
	private boolean keepGoing;
	private ArrayList<Socket> clientList;

	public POS_Server(int port) throws IOException
	{
		this.keepGoing = true;
		this.serverSocket = new ServerSocket(port);
		this.clientList = new ArrayList<Socket>();
	}
	
	public static void main(String [] args)
	{
		int port = 5001;

		try
		{
			Thread POS_Server = new POS_Server(port);
			POS_Server.start();
		}
		catch(IOException e)
		{
			System.out.println("IOException in TalkServer main()");
		}
	}
	
	public void makeConnections()
	{
		while(true)
		{
			try
			{
			
				System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "...");
				Socket client = serverSocket.accept();
                                
				System.out.println("Clients Connected to POS_Server: " + clientList.size());
				this.clientList.add(client);
				System.out.println("Just connected to " + client.getRemoteSocketAddress());
                                
				DataInputStream in = new DataInputStream(client.getInputStream());	
                                
				int transactionType = in.readInt();
                                System.out.println(transactionType);
                                String item1 = in.readUTF();
                                int quantity = in.readInt();
//                                String item2 = in.readUTF();
                                
				System.out.println("Transaction from " + client.getRemoteSocketAddress() + ": " + transactionType + ", " + quantity + ", " + item1);
				
                                if(transactionType == 1)
                                    OrderProcessing.submitBuyTransaction(item1, quantity);
                                else if(transactionType == 2)
                                    OrderProcessing.submitReturnTransaction(item1, quantity);
                                else if(transactionType == 3)
                                    OrderProcessing.submitInventoryAdjustmentTransaction(item1, quantity);
//                                else if(transactionType == 4)
//                                    OrderProcessing.submitExchangeTransaction(item1, item2);
                                else
                                    System.out.println("Error: invalid transaction type");
                                
//                                DataOutputStream out = new DataOutputStream(client.getOutputStream());
//				out.writeUTF();
			}
			catch(IOException e)
			{
				System.out.println("IOException in TalkServer makeConnections()");
				return;
			}
		}
	}

	public void run()
	{		
		this.makeConnections();
		return;
		
	}

}