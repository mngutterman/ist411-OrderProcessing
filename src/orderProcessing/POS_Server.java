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
    public static final int BUY = 1;
    public static final int RETURN = 2;
    public static final int INVENTORY_ADJUSTMENT = 3;
    public static final int EXCHANGE = 4;
    
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

                String message = in.readUTF();
                System.out.println(message);

                DataOutputStream out = new DataOutputStream(client.getOutputStream());
                out.writeUTF("You're connected to " + client.getLocalSocketAddress());
                Thread serverMessageHandler = new ServerMessageHandler(clientList.size()-1, this.clientList);
                serverMessageHandler.start();

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