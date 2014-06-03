package ClientFTP;

import android.os.AsyncTask;

import java.io.IOException;


/**
 * Created by tasa on 31/05/2014.
 * Thread du Client FTP
 */
public class ThreadClientFTP extends  AsyncTask {



    protected ClientFTP clientFTP;
    private static ThreadClientFTP thread ;

    public static ThreadClientFTP getThread(String login,String host,String pwd) throws IOException {

        if(thread == null)
        {

            thread = new ThreadClientFTP(new ClientFTP(new ParamFTP(host,login,pwd )) );
        }

        return thread;
    }

    public static ThreadClientFTP getThread(ParamFTP paramFTP) throws IOException {

        if(thread == null)
        {

            thread = new ThreadClientFTP(new ClientFTP(paramFTP) );
        }

        return thread;
    }

    public static ThreadClientFTP getThread() throws Exception {

        if(thread == null)
        {

            throw new Exception(" Le processus n'a jamais été initialisé");
        }

        return thread;
    }



    private  ThreadClientFTP(ClientFTP clientFTP) {

        this.clientFTP = clientFTP;
    }

    @Override
    protected Object doInBackground(Object[] objects) {


            this.clientFTP.Connexion();
        return null;
    }

    public ClientFTP getClientFTP()
    {
        return this.clientFTP;
    }
}
