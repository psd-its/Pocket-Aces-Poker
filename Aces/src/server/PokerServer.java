package server;

import java.io.IOException;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

import model.facade.AcesFacade;
import model.facade.AcesModel;

public class PokerServer
{

    public PokerServer()
    {
        // TODO Auto-generated constructor stub
    }

    public static void main(String[] args)
    {
        // TODO Auto-generated method stub
        AcesFacade facade = new AcesModel();
        SSLServerSocket server = null;
        SSLSocket socket = null;
        try
        {
            // Get default SSL setting for the server
            SSLServerSocketFactory factory = (SSLServerSocketFactory) 
                    SSLServerSocketFactory.getDefault();
            // Create a secure server socket on the specified port
            server = (SSLServerSocket) 
                    factory.createServerSocket(ServerConst.PORT);
            while(true)
            {
                try
                {
                    // Accept client socket
                    socket = (SSLSocket) server.accept();
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }
                // Put client socket on its own thread
                new Thread(new SocketThread(socket, facade)).start();
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                // close server socket to prevent memory leaks
                server.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        

    }

}
