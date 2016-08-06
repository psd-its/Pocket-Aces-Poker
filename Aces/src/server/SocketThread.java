package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.net.ssl.SSLSocket;

public class SocketThread implements Runnable
{
    private SSLSocket client;
    public SocketThread(SSLSocket client)
    {
        // TODO Auto-generated constructor stub
        this.client = client;
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
