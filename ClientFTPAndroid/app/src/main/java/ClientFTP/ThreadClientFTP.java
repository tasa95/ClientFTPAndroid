package ClientFTP;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.util.Observable;

/**
 * Created by tasa on 31/05/2014.
 */
public class ThreadClientFTP extends Observable implements AsyncTask {



    protected  ClientFTP clientFTP;
    private static ThreadClientFTP thread ;

    public static ThreadClientFTP getInstance(String login,String host,String pwd) throws IOException {

        if(thread == null)
        {

            thread = new ThreadClientFTP(new ClientFTP(new ParamFTP(host,login,pwd )) );
        }

        return thread;
    }

    private  ThreadClientFTP(ClientFTP clientFTP) {

        this.clientFTP = clientFTP;
    }

    @Override
    protected Object doInBackground(Object[] objects) {

        try {
            this.clientFTP.Connexion();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Erreur Connexion ",e.getMessage());
            this.notifyObservers(FTPEnum.Exception + " : "+e.getMessage());
        }
        return null;
    }
}
