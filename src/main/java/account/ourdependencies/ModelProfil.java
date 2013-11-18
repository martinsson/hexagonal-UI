package account.ourdependencies;

public class ModelProfil {

    private String siret;

    public ModelProfil(String siret) {
        this.siret = siret;
    }

    public String getIdent() {
        return siret.hashCode()+ "";
    }

    public String getIdeta() {
        return null;
    }

    public String getSegClientel() {
        return "grand compte";
    }

}
