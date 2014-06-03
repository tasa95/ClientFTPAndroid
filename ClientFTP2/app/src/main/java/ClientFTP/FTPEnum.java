package ClientFTP;

/**
 * Created by tasa on 31/05/2014.
 *
 * Enumeration des états traités par le clientFTP
 */
public enum FTPEnum {


    AnnulerTransfer("ABOR","AnnulerTransfert"),
    AjouterFichier("APPE","AjouterFichier"),
    OuvrirDossier("CWD","OuvrirDossier"),
    SupprimerFichier("DELE","SupprimerFichier"),
    InformationFichier("LIST","InformationFichier"),
    CreerRepertoire("MKD","CreerRepertoire"),
    Lister("MLSD","Lister"),
    Noop("NOOP","AucuneOperation"),
    AuthentificationPassword("PASS","Password"),
    ModePassif("PASV","ModePassif"),
    AvoirCheminRepertoire("PWD","CheminRepertoire"),
    RecupererFichier("RETR","RecupererFichier"),
    SupprimerDossier("RMD","SupprimerDossier"),
    RenommerFichier("RNTO","RenommerFichier"),
    AuthentificationUser("USER","AuthentificationUser"),
    ServeurPret("220","ServeurPret"),
    Connecte("230","Connecté"),
    DemandeMotDePasse("331","DemandeMotDePasse"),
    Deconnexion("QUIT","Deconnexion"),
    Exception("Exception","Exception");

   private String cmd;
   private String etat;


     FTPEnum(String cmd, String etat)
    {
        this.cmd = cmd;
        this.etat = etat;
    }


    String getCmd()
    {
        return this.cmd;
    }

    String getEtat()
    {
        return this.etat;
    }
}
