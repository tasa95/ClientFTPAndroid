package ClientFTP;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Observable;

/**
 * Created by tasa on 31/05/2014.
 *
 * Socket générale du client FTP
 */
public class SocketFTP extends Observable {
    private BufferedWriter out;
    private BufferedReader in;
    private Socket socket;
    private int port;
    private ParamFTP paramFTP;



    private FTPEnum etat = FTPEnum.Noop;


    public SocketFTP(ParamFTP paramFTP,int port) throws IOException {

        this.paramFTP = paramFTP;
        this.port = port;
        this.socket = new Socket(this.paramFTP.getHost(),this.port);
        this.in =new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        this.out = new BufferedWriter(
                new OutputStreamWriter(socket.getOutputStream()));
    }


    public SocketFTP(String host,String login,String pwd,int port) throws IOException {
        this.paramFTP = new ParamFTP(host,login,pwd);
        this.port = port;
        this.socket = new Socket(this.paramFTP.getHost(),this.port);
        this.in =new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        this.out = new BufferedWriter(
                new OutputStreamWriter(socket.getOutputStream()));
    }


    private String readLine() throws IOException {
        String line;
        if((line = this.in.readLine()) != null)
        {
            Log.d("lecture en entrée",line);
            return line;
        }
        else
        {
            return null;
        }
    }

    protected String readMultiplesLineTillGetParam(String text) throws IOException{

        String line ;
        StringBuilder buffer = new StringBuilder();
        while((line = this.readLine()) != null && !line.equals(text))
        {
            Log.d("lecture en entrée",line);
            buffer.append(line);
        }
        String res = buffer.toString();
        if(res.equals(""))
            return null;
        else
            return res;
    }

    protected void WriteLine(String line) throws IOException {

        this.out.write(line +"\r\n");
    }

    protected void write(String line) throws IOException {

        this.out.write(line);
    }

    protected void setChanged(FTPEnum ftpEnum) throws Exception {

        FTPEnum[] tabFTPEnum= FTPEnum.values();
        int i = 0;

        while( !tabFTPEnum[i].equals(ftpEnum))
        {
            i++;
        }

        if( i < tabFTPEnum.length)
        {
            this.setEtat(ftpEnum);
            super.setChanged();
            super.notifyObservers(ftpEnum);
        }
        else
        {
            throw new Exception("Etat inconnue : "+ftpEnum.getEtat());
        }
    }

    public FTPEnum getEtat() {
        return etat;
    }

    protected void setEtat(FTPEnum etat) throws Exception  {
        this.etat = etat;
    }

    protected String readMultiplesLineTillNull(String text) throws IOException{

        String line ;
        StringBuffer buffer = new StringBuffer();
        while((line = this.readLine()) != null && !line.equals(text))
        {
            buffer.append(line);
        }
        String res = buffer.toString();
        if(res.equals(""))
            return null;
        else
            return res;
    }

    protected void deconnexion() throws Exception {
        try {
            this.in.close();
            this.out.close();
            this.socket.close();
        } catch (IOException e) {
            Log.e("Deconnexion",e.getMessage());
            if(this.in != null)
            {
                this.in = null;
            }
            if(this.out != null)
            {
                this.out = null;
            }

            if(this.socket != null)
            {
                this.out = null;
            }
            throw new IOException("Fermeture impossible du socket "+e.getMessage()+" "+this.getClass().getCanonicalName());
        }
    }


}
