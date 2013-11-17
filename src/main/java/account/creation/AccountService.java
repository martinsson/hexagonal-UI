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
import account.ourdependencies.TemplateNetMailCreatEmailSiuBo;
import account.ourdependencies.TemplateNetMailCreatSiretRestrBo;
import account.ourdependencies.TemplateNetMailCreatSiretRestrClient;
import account.ourdependencies.TemplateNetMailCreationKoBo;
import account.ourdependencies.TemplateNetMailCreationKoClient;
import account.ourdependencies.TemplateNetMailCreationReussieBo;
import account.ourdependencies.TemplateNetMailCreationReussieClient;
import account.ourdependencies.UserInfo;
import account.ourdependencies.UserService;
import account.thirdpartyframework.Component;
import account.thirdpartyframework.PortalException;
import account.thirdpartyframework.SystemException;
import account.thirdpartyframework.UserAPICoreException;
import account.thirdpartyframework.UserAPIUserException;
import account.thirdpartyframework.WrefTechnicalException;

/**
 * gestion des comptes
 * 
 */
@Component
public class AccountService {

    private static final Log LOG = account.ourdependencies.LogTool.logFor(AccountService.class);

    private final UserService userService;
    private final ProfilService profilService;

    private DataList dataList;

    /**
     * 
     * @param userService
     * @param service
     */
    public AccountService(UserService service) {
        this(service, new ProfilService(), new DataList());
    }

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
                createMail(new TemplateNetMailCreatSiretRestrBo(elementsInfoForMail, contactEmailBO));
                resultCreation = false;
            } else if (userService.isEmailAlreadyUsed(accountBean.getEmail())) {
                createMail(new TemplateNetMailCreatEmailSiuBo(elementsInfoForMail, contactEmailBO));
                resultCreation = false;
            } else {
                UserInfo userInfo = assembleUserInfo(accountBean, modelProfil);
                userService.createAccount(userInfo, accountBean.getPassword(), accountBean.getCondGeneInternet(), accountBean.getCondGeneMobile());
                createMail(new TemplateNetMailCreationReussieBo(elementsInfoForMail, contactEmailBO));
                resultCreation = true;
            }
        } catch (WrefTechnicalException e) {
            LOG.warning("WrefTechnicalException: " + e.getMessage());
            ElementsInfoForMailCreation elementsInfoForMail = new ElementsInfoForMailCreation();
            String contactEmailBO = findEmailBackOffice(null);
            createMail(new TemplateNetMailCreationKoBo(elementsInfoForMail, contactEmailBO));
            throw new TechnicalException(e);
        }
        return resultCreation;

    }

    private UserInfo assembleUserInfo(AccountBean accountBean, ModelProfil modelProfil) {
        UserInfo userInfo = fillSIUUserInfoFromEceAccountBean(accountBean);
        userInfo.setIdent(modelProfil.getIdent());
        return userInfo;
    }

    private String findEmailBackOffice(Object object) {
        // TODO Auto-generated method stub
        return null;
    }

    private void createMail(TemplateMail template) {
        // TODO Auto-generated method stub

    }

    private UserInfo fillSIUUserInfoFromEceAccountBean(AccountBean accountBean) {
        return new UserInfo();
    }

    private ElementsInfoForMailCreation geneBeanElementsForMail(AccountBean accountBean, Object object) {
        // TODO Auto-generated method stub
        return new ElementsInfoForMailCreation();
    }

    public String findRaisonSociale(String siret) throws TechnicalException {
        return null;
    }

    protected String findConditionGeneVente(AccountBean accountBean) {
        return null;
    }

}
