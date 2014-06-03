package ClientFTP;

/**
 * Created by tasa on 31/05/2014.
 * Paramétre FTP
 */
public class ParamFTP {


    private String host ;



    private String login;
    private String pwd;

    public ParamFTP(String host, String login, String pwd) {
        this.host = host;
        this.setLogin(login);
        this.pwd = pwd;
    }

    public String getHost() {
        return host;
    }

    private void setHost(String host) {
        this.host = host;
    }

    public String getLogin() {
        return login;
    }

    private void setLogin(String login) {
        if(login.equals(""))
        {
            this.login = "anonymous";
        }
        else {
            this.login = login;
        }
    }

    private String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }


}
