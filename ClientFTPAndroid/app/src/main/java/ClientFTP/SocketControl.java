package ClientFTP;

import android.util.Log;

import java.io.IOException;

/**
 * Created by tasa on 31/05/2014.
 * Socket de controle entre le client FTP et le serveur FTP
 */
public class SocketControl extends SocketFTP {



    public SocketControl(ParamFTP paramFTP) throws IOException {
        super(paramFTP,21);
    }


   protected void CheckServeurPret() throws IOException {
        String line = this.readMultiplesLineTillGetParam(FTPEnum.ServeurPret.getCmd());
        if(line != null && !line.contains(FTPEnum.ServeurPret.getCmd())) {
            throw new IOException("Le serveur n'est pas pret");
        }
            this.setChanged();
            this.notifyObservers(FTPEnum.ServeurPret.getEtat());

    }


    protected void SendUser(ParamFTP paramFTP) throws Exception {
        this.sendCmd(FTPEnum.AuthentificationUser+ " "+paramFTP.getLogin(),FTPEnum.AuthentificationUser );
    }


    protected void sendPwd(ParamFTP paramFTP) throws Exception
    {
        String line = this.readMultiplesLineTillGetParam(FTPEnum.DemandeMotDePasse.getCmd());
        if(line != null && !line.contains(FTPEnum.Connecte.getCmd())) {
                throw new IOException("Le serveur n'est pas pret");
        }
        this.sendCmd(FTPEnum.AuthentificationPassword.getCmd()+ " "+paramFTP.getLogin(),FTPEnum.AuthentificationPassword);
    }


    protected void OuvrirDossier(String dossier) throws Exception {
        this.sendCmd(FTPEnum.OuvrirDossier.getCmd()+ " "+dossier,FTPEnum.OuvrirDossier );
    }

    protected void AnnulerTransfer(String file) throws Exception {
        this.sendCmd(FTPEnum.AnnulerTransfer.getCmd()+ " "+file,FTPEnum.AnnulerTransfer );
    }


    protected void ajouterFichier(String file) throws Exception {
        this.sendCmd(FTPEnum.AjouterFichier.getCmd()+ " "+file,FTPEnum.AjouterFichier );
    }

    protected void ajouterFichier(String file, String newFileName) throws Exception {
        this.sendCmd(FTPEnum.AjouterFichier.getCmd()+ " "+file+" "+newFileName,FTPEnum.AjouterFichier);
    }

    protected void supprimerFichier(String file) throws Exception {
        this.sendCmd(FTPEnum.SupprimerFichier.getCmd()+ " "+file,FTPEnum.SupprimerFichier );
    }


    protected void informationFichier(String file) throws Exception {
        this.sendCmd(FTPEnum.InformationFichier.getCmd()+ " "+file,FTPEnum.InformationFichier);
    }

    protected void creerRepertoire(String file) throws Exception {
        this.sendCmd(FTPEnum.CreerRepertoire.getCmd()+ " "+file,FTPEnum.CreerRepertoire );
    }


    protected void lister() throws Exception {
        this.sendCmd(FTPEnum.Lister.getCmd(),FTPEnum.Lister );
    }

    protected void noOperation() throws Exception {
        this.sendCmd(FTPEnum.Noop.getCmd(), FTPEnum.Noop);
    }

   /* protected void ModePassif()
    {
        this.sendCmd(FTPEnum.ModePassif.getCmd() );
    }
    */

    protected void avoirCheminRepertoire() throws Exception {

        this.sendCmd(FTPEnum.AvoirCheminRepertoire.getCmd(),FTPEnum.AvoirCheminRepertoire);
    }


    protected void RecupererFichier(String file) throws Exception {

        this.sendCmd(FTPEnum.RecupererFichier.getCmd() + " " + file, FTPEnum.RecupererFichier);
    }


    protected void SupprimerDossier(String dossier) throws Exception {
        this.sendCmd(FTPEnum.SupprimerDossier.getCmd()+ " "+dossier,FTPEnum.SupprimerDossier);
    }

    protected void RenommerFichier(String file) throws Exception {
        this.sendCmd(FTPEnum.RenommerFichier.getCmd()+ " "+file,FTPEnum.RenommerFichier);
    }


    protected void sendCmd(String line,FTPEnum ftpEnum) throws Exception {
        Log.v("Contrôle", line);
        Log.d("FTPEnum ", ftpEnum.getEtat());
        this.WriteLine(line);
        this.setChanged(ftpEnum);
    }

    @Override
    protected void deconnexion() throws Exception {
        setChanged(FTPEnum.Deconnexion);
        super.deconnexion();
    }
}
