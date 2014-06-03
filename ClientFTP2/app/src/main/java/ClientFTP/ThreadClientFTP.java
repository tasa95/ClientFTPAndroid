package ClientFTP;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;


/**
 * Created by tasa on 31/05/2014.
 * Thread du Client FTP
 */
public class ThreadClientFTP extends  AsyncTask<Object,Object ,Object > {


    protected ClientFTP clientFTP;
    protected ParamFTP paramFTP;
    private static ThreadClientFTP thread ;

    public static ThreadClientFTP getThread(String login,String host,String pwd) throws IOException {

        if(thread == null)
        {

            thread = new ThreadClientFTP(new ParamFTP(host,login,pwd ) );
        }

        return thread;
    }

    public static ThreadClientFTP getThread(ParamFTP paramFTP) throws IOException {

        if(thread == null)
        {

            thread = new ThreadClientFTP(paramFTP );
        }

        return thread;
    }

    public static ThreadClientFTP getThread() throws Exception {

        if(thread == null || thread.paramFTP == null)
        {

            throw new Exception(" Le processus n'a jamais été initialisé");
        }

        return thread;
    }



    private  ThreadClientFTP(ParamFTP paramFTP) {

        this.paramFTP = paramFTP;
    }

    @Override
    protected Object doInBackground(Object[] objects) {

        try {
            this.clientFTP = new ClientFTP(this.paramFTP);
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Connexion",e.getMessage());
        }

        thread.clientFTP.Connexion();
        return null;
    }


    public ClientFTP getClientFTP()
    {
        return this.clientFTP;
    }
}
