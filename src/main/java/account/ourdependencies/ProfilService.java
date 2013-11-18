package account.ourdependencies;

import account.thirdpartyframework.WrefTechnicalException;

public class ProfilService {

    public ModelProfil findProfilWithSiret(String siret) throws WrefTechnicalException{
        // TODO Auto-generated method stub
        ModelProfil modelProfil = new ModelProfil(siret);
        return modelProfil;
    }

}
