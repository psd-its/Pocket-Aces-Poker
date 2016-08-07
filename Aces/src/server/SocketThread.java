package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.net.ssl.SSLSocket;

import model.facade.AcesFacade;

public class SocketThread implements Runnable
{
    private SSLSocket client;
    private AcesFacade model;
    public SocketThread(SSLSocket client, AcesFacade facade)
    {
        // TODO Auto-generated constructor stub
        this.client = client;
        this.model = facade;
    }

    @Override
    public void run()
    {
        // Open object streamsI
        ObjectOutputStream ostream = null;
        ObjectInputStream istream = null;
        try
        {
            ostream = new ObjectOutputStream(client.getOutputStream());
            istream = new ObjectInputStream(client.getInputStream());
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally
        {
            try
            {
                /// House keeping
                ostream.close();
                istream.close();
                client.close();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
        
    }

}
