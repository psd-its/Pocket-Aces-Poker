package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.net.SocketFactory;
import javax.net.ssl.SSLSocket;

import model.facade.AcesFacade;
import model.player.Player;
import view.main.MainView;

public class PokerClient
{
    private Socket socket;
    private Player player;
    private AcesFacade model;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private MainView view;
    public PokerClient(MainView view, Player player)
    {
        // TODO Auto-generated constructor stub
        this.view = view;
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
            SocketFactory factory = (SocketFactory) 
                    SocketFactory.getDefault();
            socket = (Socket) factory.createSocket(ServerConst.LOCAL,
                    ServerConst.PORT);
            // open streams
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
            /// get the facade from the server
            while(model == null)
            {
                model = (AcesFacade) in.readObject();
                if (model != null)
                {
                    view.setFacade(model);
                }
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
