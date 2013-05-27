package account.ourdependencies;

public class AccountBean {

    private String siret;
    private String password;
    private String email;

    public AccountBean() {
    }
    
    public AccountBean(String password, String email, String siret) {
        this.password = password;
        this.email = email;
        this.siret = siret;
    }

    public String getSiret() {
        return siret;
    }

    public String getPassword() {
        return password;
    }

    public String getCondGeneMobile() {
        return null;
    }

    public String getCondGeneInternet() {
        return null;
    }

    public String getEmail() {
        return email;
    }

}
