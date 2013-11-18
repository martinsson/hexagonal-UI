/**
 *
 */
package account.creation;

import account.ourdependencies.AccountBean;
import account.ourdependencies.DataList;
import account.ourdependencies.ElementsInfoForMailCreation;
import account.ourdependencies.Log;
import account.ourdependencies.ModelProfil;
import account.ourdependencies.ProfilService;
import account.ourdependencies.TechnicalException;
import account.ourdependencies.TemplateMail;
import account.ourdependencies.EmailUsed;
import account.ourdependencies.SiretRestricted;
import account.ourdependencies.CreationKo;
import account.ourdependencies.CreationReussie;
import account.ourdependencies.UserInfo;
import account.ourdependencies.UserService;
import account.thirdpartyframework.WrefTechnicalException;

public class AccountService {

    private static final Log LOG = account.ourdependencies.LogTool.logFor(AccountService.class);

    private final UserService userService;
    private final ProfilService profilService;

    private DataList dataList;

    public AccountService(UserService userService, ProfilService profilService, DataList datalist) {
        this.userService = userService;
        this.profilService = profilService;
        this.dataList = datalist;
    }

    public boolean createAccount(AccountBean accountBean) throws account.ourdependencies.TechnicalException {
        boolean resultCreation = true;
        try {
            ModelProfil modelProfil = profilService.findProfilWithSiret(accountBean.getSiret());

            ElementsInfoForMailCreation elementsInfoForMail = geneBeanElementsForMail(accountBean, modelProfil);
            String contactEmailBO = findEmailBackOffice(modelProfil.getSegClientel());

            if (dataList.findAndCheckSiret(accountBean.getSiret())) {
                createMail(new SiretRestricted(elementsInfoForMail, contactEmailBO));
                resultCreation = false;
            } else if (userService.isEmailAlreadyUsed(accountBean.getEmail())) {
                createMail(new EmailUsed(elementsInfoForMail, contactEmailBO));
                resultCreation = false;
            } else {
                UserInfo userInfo = assembleUserInfo(accountBean, modelProfil);
                userService.createAccount(userInfo, accountBean.getPassword(), accountBean.getCondGeneInternet(), accountBean.getCondGeneMobile());
                createMail(new CreationReussie(elementsInfoForMail, contactEmailBO));
                resultCreation = true;
            }
        } catch (WrefTechnicalException e) {
            LOG.warning("WrefTechnicalException: " + e.getMessage());
            createMail(new CreationKo(accountBean, defaultBoEmail()));
            throw new TechnicalException(e);
        }
        return resultCreation;

    }

    private UserInfo assembleUserInfo(AccountBean accountBean, ModelProfil modelProfil) {
        UserInfo userInfo = fillSIUUserInfoFromEceAccountBean(accountBean);
        userInfo.setIdent(modelProfil.getIdent());
        return userInfo;
    }
    
    private UserInfo fillSIUUserInfoFromEceAccountBean(AccountBean accountBean) {
        return new UserInfo();
    }
    
    private ElementsInfoForMailCreation geneBeanElementsForMail(AccountBean accountBean, Object object) {
        return new ElementsInfoForMailCreation();
    }
    
    private String defaultBoEmail() {
        return null;
    }

    private String findEmailBackOffice(Object object) {
        return null;
    }

    private void createMail(TemplateMail template) {
    }

    public String findRaisonSociale(String siret) throws TechnicalException {
        return null;
    }

    protected String findConditionGeneVente(AccountBean accountBean) {
        return null;
    }

}
