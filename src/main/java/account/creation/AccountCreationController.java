package account.creation;

import java.io.IOException;

import account.ourdependencies.AccountBean;
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

    public static final String DEFAULT_FINAL_URL_NO_CREATED = "pending-page";

    private AccountService accountService;


    public AccountCreationController(AccountService accountService) {
        this.accountService = accountService;
    }


    @ActionMapping(params = "action=doFormAction")
    public void doAction(@ModelAttribute AccountBean accountBean, HttpRequest request,
             final HttpResponse response) throws IOException {


        CreationResponse creationResponse = new HttpCreationResponse(response);
        accountService.createAccount(accountBean, creationResponse);
    }


}
