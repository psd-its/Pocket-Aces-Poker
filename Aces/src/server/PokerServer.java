package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.net.ServerSocketFactory;
import javax.net.ssl.SSLSocket;

import model.facade.AcesFacade;
import model.facade.AcesModel;
import model.game.texas.TexasPoker;
import model.table.TexasTable;

public class PokerServer
{
    public static void main(String[] args)
    {
        // TODO Auto-generated method stub
        AcesFacade facade = new AcesModel();
        facade.createGame(new TexasPoker());
        facade.getGame().addTable(new TexasTable());
        ServerSocket server = null;
        Socket socket = null;
        try
        {
            // Get default SSL setting for the server
            ServerSocketFactory factory = (ServerSocketFactory) 
                    ServerSocketFactory.getDefault();
            // Create a secure server socket on the specified port
            server = (ServerSocket) 
                    factory.createServerSocket(ServerConst.PORT);
            System.out.println("Server started on port " + ServerConst.PORT);
            while(true)
            {
                try
                {
                    // Accept client socket
                    System.out.println("Waiting for client");
                    socket = (Socket) server.accept();
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }
                // Put client socket on its own thread
                System.out.println("Creating new thread for client connection");
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
                System.out.println("Closing the server");
                server.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        

    }

}
