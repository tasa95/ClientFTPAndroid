package ClientFTP;

import android.os.Environment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by tasa on 31/05/2014.
 */
public class SocketDonnee extends SocketFTP {


    public SocketDonnee(ParamFTP paramFTP) throws IOException {
        super(paramFTP,20);
    }



    public void sendFileToFTP(String fichier) throws Exception {


        this.write(this.lireContenuFichier(fichier));
        this.setEtat(FTPEnum.AjouterFichier);
    }


    public String getFileFromFTP(String file) throws Exception {

        String text = this.readMultiplesLineTillGetParam(null);
        ecrireContenuFichier(Environment.DIRECTORY_DOWNLOADS +"/"+file,text);
        this.setEtat(FTPEnum.RecupererFichier);
        return Environment.DIRECTORY_DOWNLOADS +"/"+file;


    }


    private String lireContenuFichier(String path) throws IOException {

        File file = new File(path);

        FileInputStream fip = new FileInputStream(new FileDescriptor());

        BufferedReader input = null;
        input = new BufferedReader(new InputStreamReader( new FileInputStream(file)));
        String line;

        StringBuffer buffer = new StringBuffer();

        while ((line = input.readLine()) != null) {

            buffer.append(line);

        }
        input.close();
        return buffer.toString();
    }


    private void ecrireContenuFichier(String path,String content) throws IOException {

        File newfile = new File(path);

        newfile.createNewFile();

       BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newfile)));

        writer.write(content);

        writer.close();
    }
    protected void setEtat(FTPEnum ftpEnum) throws Exception {
        this.setChanged(ftpEnum);
    }


    public String read(FTPEnum ftpEnum) throws Exception {

        String res = this.readMultiplesLineTillGetParam(null);
        this.setEtat(ftpEnum);
        return res;
    }
}
