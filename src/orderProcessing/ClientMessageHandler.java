/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderProcessing;

import java.io.*;
import java.net.*;
import java.util.*;
public class ClientMessageHandler extends Thread
{
	private Socket serverConnection;
	public ClientMessageHandler(Socket server)
	{
            this.serverConnection = server;
	}
	
	public static boolean getMessageFromServer(Socket serverConnection)
	{
            String message = "";
            try		
            {
                InputStream inFromServer = serverConnection.getInputStream();
                DataInputStream dataIn = new DataInputStream(inFromServer);
                message = dataIn.readUTF();

                System.out.println(message);
                return true;

            }
            catch(IOException e)
            {
                System.out.println("IOException in ClientMessageHandler getMessageFromServer()");
                return false;
            }
	}
	
	
	public void run()
	{
            boolean done = false;
            while(!done)
            {
                done = getMessageFromServer(this.serverConnection);
            }
	}
}
