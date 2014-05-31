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


    public void Deconnexion() throws Exception {
        this.socketControl.deconnexion();
    }

    public void Connexion() throws IOException {
        this.socketControl.CheckServeurPret();
    }

    public void AjouterFichier(String file) throws Exception {
        this.setPath(file);
        this.socketControl.ajouterFichier(file);
    }

    public void AjouterFichier(String file,String newFileName) throws Exception {

        this.socketControl.ajouterFichier(file,newFileName);
    }

    public void AnnulerTransfer(String file) throws Exception {
        this.socketControl.AnnulerTransfer(file);
    }

    public void OuvrirDossier(String file) throws Exception {
        this.socketControl.OuvrirDossier(file);
    }


    protected void supprimerFichier(String file) throws Exception {
        this.socketControl.supprimerFichier(file);
    }


    protected void informationFichier(String file) throws Exception {
        this.socketControl.informationFichier(file);
    }

    protected void creerRepertoire(String file) throws Exception {
        this.socketControl.creerRepertoire(file);
    }


    protected void lister() throws Exception {
        this.socketControl.lister();
    }

    protected void noOperation() throws Exception {
        this.socketControl.noOperation();
    }

    protected void AuthentificationUser() throws  Exception{
        this.socketControl.SendUser(this.paramFTP);
    }

    protected void AuthentificationPwd() throws  Exception{
        this.socketControl.sendPwd(this.paramFTP);
    }

   /* protected void ModePassif()
    {
        this.
    }
    */

    protected void avoirCheminRepertoire() throws Exception {

        this.socketControl.avoirCheminRepertoire();
    }


    protected void RecupererFichier(String file) throws Exception {

        this.socketControl.RecupererFichier(file);
    }


    protected void SupprimerDossier(String dossier) throws Exception {
       this.socketControl.SupprimerDossier(dossier);
    }

    protected void RenommerFichier(String file) throws Exception {
       this.socketControl.RenommerFichier(file);
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
                            try {
                                this.setChanged(((FTPEnum)o));
                            } catch (Exception e) {
                                e.printStackTrace();
                               this.exceptonGenere(e.getMessage());


                            }

                            break;

                        case AjouterFichier :
                            try {
                                this.socketDonnee.sendFileToFTP(this.getPath());
                                this.lister();
                            } catch (Exception e) {
                                e.printStackTrace();
                                this.exceptonGenere(e.getMessage());
                            }
                            break;

                        case OuvrirDossier :
                            try {
                                this.lister();
                            } catch (Exception e) {
                                e.printStackTrace();
                                this.exceptonGenere(e.getMessage());
                            }
                            break;

                        case SupprimerFichier :
                            try {
                                this.lister();
                            } catch (Exception e) {
                                e.printStackTrace();
                                this.exceptonGenere(e.getMessage());

                            }
                            break;

                        case InformationFichier :
                            try {
                                this.informationFichier(this.getPath());
                            } catch (Exception e) {
                                e.printStackTrace();
                                this.exceptonGenere(e.getMessage());

                            }
                            break;

                        case CreerRepertoire:
                            try {
                                this.lister();
                            } catch (Exception e) {
                                e.printStackTrace();
                                this.exceptonGenere(e.getMessage());

                            }
                            break;

                        case Lister :
                            try {
                                this.content=this.socketDonnee.read(((FTPEnum)o));
                                this.setChanged( ((FTPEnum)o) );
                            } catch (Exception e) {
                                e.printStackTrace();
                                this.exceptonGenere(e.getMessage());

                            }
                            break;

                        case Noop :
                            try {
                                this.setChanged( ((FTPEnum)o));
                            } catch (Exception e) {
                                e.printStackTrace();
                                this.exceptonGenere(e.getMessage());

                            }
                            break;

                        case AuthentificationPassword:
                            try {
                                this.socketControl.sendPwd(this.paramFTP);
                            } catch (Exception e) {
                                e.printStackTrace();
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
                                this.content=this.socketDonnee.read( ((FTPEnum)o) );
                                this.setChanged(((FTPEnum)o));
                            } catch (Exception e) {
                                e.printStackTrace();
                                this.exceptonGenere(e.getMessage());

                            }


                            break;



                        case RecupererFichier:

                            try {
                                content =this.socketDonnee.getFileFromFTP(this.getPath());
                                this.setChanged(((FTPEnum)o));
                            } catch (Exception e) {
                                e.printStackTrace();
                                this.exceptonGenere(e.getMessage());

                            }
                            break;



                        case SupprimerDossier :
                            try {
                                this.lister();
                            } catch (Exception e) {
                                e.printStackTrace();
                                this.exceptonGenere(e.getMessage());

                            }
                            break;

                        case RenommerFichier :

                            try {
                                this.lister();
                            } catch (Exception e) {
                                e.printStackTrace();
                                this.exceptonGenere(e.getMessage());

                            }
                            break;

                        case AuthentificationUser:

                            try {
                                this.AuthentificationPwd();
                            } catch (Exception e) {
                                e.printStackTrace();
                                this.exceptonGenere(e.getMessage());

                            }

                            break;

                        case ServeurPret :
                            try {
                                this.AuthentificationUser();
                            } catch (Exception e) {
                                e.printStackTrace();
                                this.exceptonGenere(e.getMessage());

                            }
                            break;

                        case DemandeMotDePasse :
                            try {
                                this.socketControl.setChanged(FTPEnum.Connecte);
                            } catch (Exception e) {
                                e.printStackTrace();
                                this.exceptonGenere(e.getMessage());

                            }
                            break;


                        case Deconnexion :
                            try {
                                this.socketDonnee.deconnexion();
                                this.setChanged(FTPEnum.Deconnexion);
                            } catch (Exception e) {
                                e.printStackTrace();
                                this.exceptonGenere(e.getMessage());

                            }

                            break;

                        case Connecte :
                            try {
                                this.socketDonnee = new SocketDonnee(this.paramFTP);
                                this.lister();
                            } catch (Exception e) {
                                e.printStackTrace();
                                this.exceptonGenere(e.getMessage());

                            }
                            break;
                        case Exception:
                            this.exceptonGenere("Exception generée");
                            break;


                    }
                }

        }

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
