package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.UnknownHostException;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

import model.facade.AcesFacade;
import model.player.Player;

public class PokerClient
{
    private SSLSocket socket;
    private Player player;
    private AcesFacade model;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    public PokerClient(Player player)
    {
        // TODO Auto-generated constructor stub
        
        this.player = player;
        this.model = null;
        this.in = null;
        this.out = null;
        
    }

    public void join()
    {
        // TODO Auto-generated method stub
     
        try
        {
            // Join the server
            SSLSocketFactory factory = (SSLSocketFactory) 
                    SSLSocketFactory.getDefault();
            socket = (SSLSocket) factory.createSocket(ServerConst.TITAN,
                    ServerConst.PORT);
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
            while(model == null)
            {
                model = (AcesFacade) in.readObject();
            }
        }
        catch (UnknownHostException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

}
