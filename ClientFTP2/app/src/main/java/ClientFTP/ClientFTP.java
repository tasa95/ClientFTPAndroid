package ClientFTP;
import android.util.Log;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;


/**
 * Created by tasa on 31/05/2014.
 * Client FTP ayant un socket de controle et un socket de donnee
 */
public class ClientFTP extends Observable implements Observer{

       private ParamFTP paramFTP;



    public FTPEnum etat;
       private SocketControl socketControl;
       private SocketDonnee socketDonnee;

    String content = null;
    String path = null;
    boolean error = false;


    public ClientFTP(ParamFTP paramFTP) throws IOException {
       this.paramFTP = paramFTP;
       this.socketControl = new SocketControl(paramFTP);
       this.socketControl.addObserver(this);
    }


    public void Deconnexion() {

        try
        {
            this.socketControl.deconnexion();
        }
        catch(IOException e)
        {
            this.exceptonGenere(e.getMessage());
        } catch (Exception e1) {
            this.exceptonGenere(e1.getMessage());
        }
    }

    public void Connexion() {
        try
        {
            this.socketControl.CheckServeurPret();

        }
        catch(IOException e)
        {
            this.exceptonGenere(e.getMessage());
        }
    }

    public void AjouterFichier(String file) {
        try
        {
            this.path = file;
            this.socketControl.ajouterFichier(file);
        }
        catch(Exception e)
        {
            this.exceptonGenere(e.getMessage());
        }
    }

    public void AjouterFichier(String file,String newFileName) {

        try
        {
            this.socketControl.ajouterFichier(file,newFileName);
        }
        catch(Exception e)
        {
            this.exceptonGenere(e.getMessage());
        }

    }

    public void AnnulerTransfer(String file){
        try
        {
            this.socketControl.AnnulerTransfer(file);
        }
        catch(Exception e)
        {
            this.exceptonGenere(e.getMessage());
        }
    }

    public void OuvrirDossier(String file){
        try
        {
            this.socketControl.OuvrirDossier(file);
        }
        catch(Exception e)
        {
            this.exceptonGenere(e.getMessage());
        }


    }


    protected void supprimerFichier(String file){
        try {
            this.socketControl.supprimerFichier(file);
        }
        catch(Exception e)
        {
            this.exceptonGenere(e.getMessage());
        }
    }


    protected void informationFichier(String file){
        try {
            this.socketControl.informationFichier(file);
        }
        catch(Exception e)
        {
            this.exceptonGenere(e.getMessage());
        }
    }

    protected void creerRepertoire(String file){

        try {
            this.socketControl.creerRepertoire(file);
        }
        catch(Exception e)
        {
            this.exceptonGenere(e.getMessage());
        }
    }


    protected void lister(){

        try {
            this.socketControl.lister();
        }
        catch(Exception e)
        {
            this.exceptonGenere(e.getMessage());
        }
    }

    protected void noOperation(){
        try {
            this.socketControl.noOperation();
        }
        catch(Exception e)
        {
            this.exceptonGenere(e.getMessage());
        }
    }

    protected void AuthentificationUser(){

        try {
            this.socketControl.SendUser(this.paramFTP);
        }
        catch(Exception e)
        {
            this.exceptonGenere(e.getMessage());
        }
    }

    protected void AuthentificationPwd(){

        try
        {
            this.socketControl.sendPwd(this.paramFTP);
        }
        catch (Exception e)
        {
            this.exceptonGenere(e.getMessage());
        }

    }

   /* protected void ModePassif()
    {
        this.
    }
    */

    protected void avoirCheminRepertoire(){

        try {
            this.socketControl.avoirCheminRepertoire();
        }
        catch(Exception e)
        {
            this.exceptonGenere(e.getMessage());
        }
    }


    protected void RecupererFichier(String file){
        try
        {
            this.socketControl.RecupererFichier(file);
        }
        catch (Exception e)
        {
            this.exceptonGenere(e.getMessage());
        }

    }


    protected void SupprimerDossier(String dossier){
        try {
            this.socketControl.SupprimerDossier(dossier);
        }
        catch (Exception e)
        {
            this.exceptonGenere(e.getMessage());
        }
    }

    protected void RenommerFichier(String file){
        try
        {
            this.socketControl.RenommerFichier(file);
        }
        catch(Exception e)
        {
            this.exceptonGenere(e.getMessage());
        }

    }

    protected void exceptonGenere(String text)
    {
            error = true;
            this.content = text;
            Log.e("ClientFTP", text);
            this.notifyObservers(text);

    }


    @Override
    public void update(Observable observable, Object o) {

        if(observable instanceof SocketControl && this.socketControl.equals((SocketControl)observable))
        {
                if(o instanceof FTPEnum)
                {

                    switch( ((FTPEnum)o) )
                    {



                        case AnnulerTransfer :

                                this.setChanged(((FTPEnum)o));

                            break;

                        case AjouterFichier :
                                try {
                                    this.socketDonnee.sendFileToFTP(this.getPath());
                                }
                                catch(Exception e)
                                {
                                    this.exceptonGenere(e.getMessage());
                                }
                                this.lister();

                            break;

                        case OuvrirDossier :

                                this.lister();

                            break;

                        case SupprimerFichier :

                                this.lister();

                            break;

                        case InformationFichier :

                                this.informationFichier(this.getPath());
                            break;

                        case CreerRepertoire:

                                this.lister();
                            break;

                        case Lister :
                            try {
                                this.content = this.socketDonnee.read(((FTPEnum) o));
                            }
                            catch (Exception e)
                            {
                                this.exceptonGenere(e.getMessage());
                            }

                            this.setChanged( ((FTPEnum)o) );

                            break;

                        case Noop :

                                this.setChanged( ((FTPEnum)o));

                            break;

                        case AuthentificationPassword:
                            try {
                                this.socketControl.sendPwd(this.paramFTP);
                            }
                            catch(Exception e)
                            {
                                this.exceptonGenere(e.getMessage());
                            }

                            break;

                        case ModePassif :


                            /***
                             *
                             *
                             *
                             */
                            break;

                        case AvoirCheminRepertoire :

                            try {
                                this.content = this.socketDonnee.read(((FTPEnum) o));
                            }
                            catch(Exception e)
                            {
                                this.exceptonGenere(e.getMessage());
                            }
                                this.setChanged(((FTPEnum)o));



                            break;



                        case RecupererFichier:

                            try {
                                content = this.socketDonnee.getFileFromFTP(this.getPath());
                            }
                            catch(Exception e)
                            {
                                this.exceptonGenere(e.getMessage());
                            }
                                this.setChanged(((FTPEnum)o));

                            break;



                        case SupprimerDossier :

                                this.lister();

                            break;

                        case RenommerFichier :


                                this.lister();

                            break;

                        case AuthentificationUser:


                                this.AuthentificationPwd();


                            break;

                        case ServeurPret :

                                this.AuthentificationUser();

                            break;

                        case DemandeMotDePasse :
                               try {
                                   this.socketControl.setChanged(FTPEnum.Connecte);
                               }
                               catch(Exception e)
                               {
                                   this.exceptonGenere(e.getMessage());
                               }

                            break;


                        case Deconnexion :
                            try {
                                this.socketDonnee.deconnexion();
                            }
                            catch(Exception e)
                            {
                                this.exceptonGenere(e.getMessage());
                            }
                                this.setChanged(FTPEnum.Deconnexion);


                            break;

                        case Connecte :
                            try {
                                this.socketDonnee = new SocketDonnee(this.paramFTP);
                            }
                            catch (Exception e)
                            {
                                this.exceptonGenere(e.getMessage());
                            }
                                this.lister();

                            break;
                        case Exception:
                            this.exceptonGenere("Exception generée");
                            break;


                    }
                }

        }

    }

    protected void setChanged(FTPEnum ftpEnum) {

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
            this.exceptonGenere("Etat inconnue : "+ftpEnum.getEtat());
        }
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public FTPEnum getEtat() {
        return etat;
    }

    public void setEtat(FTPEnum etat) {
        this.etat = etat;
    }


}
