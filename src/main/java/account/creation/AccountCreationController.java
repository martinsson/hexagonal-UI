package account.creation;

import java.io.IOException;

import account.ourdependencies.AccountBean;
import account.ourdependencies.ErrorsList;
import account.ourdependencies.Log;
import account.ourdependencies.LogTool;
import account.ourdependencies.TechnicalException;
import account.thirdpartyframework.ActionMapping;
import account.thirdpartyframework.Controller;
import account.thirdpartyframework.HttpRequest;
import account.thirdpartyframework.HttpResponse;
import account.thirdpartyframework.ModelAttribute;
import account.thirdpartyframework.RequestMapping;

/**
 * 
 * Creation du compte avec redirection et connexion automatique.
 * 
 */
@Controller
@RequestMapping("VIEW")
public class AccountCreationController {

    static final Log LOG = LogTool.logFor(AccountCreationController.class);

    public static final int MAX_SIU_TRIES = 4;
    public static final int WAIT_TIME_BEFORE_SIU_RETRY = 500;

    public static final String DEFAULT_FINAL_URL_NO_CREATED = "someUrl";

    private ValidatingService validatingService;


    public AccountCreationController(ValidatingService service) {
        this.validatingService = service;
    }


    @ActionMapping(params = "action=doFormAction")
    public void doAction(@ModelAttribute AccountBean accountBean, HttpRequest request,
             HttpResponse response) throws IOException {

        ErrorsList listErrorFields = checkFieldsForm(accountBean);

        if (listErrorFields.hasError()) {
            response.setAttribute("error_fields_create_compte", listErrorFields.generateObjectJson());
            response.setRenderParameter("action", "view");
        } else {
            validatingService.createAccount(accountBean, new HTTPCreationResponse());
        }
    }


    private account.ourdependencies.ErrorsList checkFieldsForm(AccountBean eceAccountBean) {
        
        ErrorsList errorsList = new ErrorsList();
        if (eceAccountBean.getEmail() == null) {
            errorsList.add("email missing");
        } 
        return errorsList;
    }


}
