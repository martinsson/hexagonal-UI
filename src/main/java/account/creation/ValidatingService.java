package account.creation;

import account.ourdependencies.AccountBean;
import account.ourdependencies.ErrorsList;

public class ValidatingService {

    private AccountService accountService;

    public ValidatingService(AccountService accountService) {
        this.accountService = accountService;
    }

    public void createAccount(AccountBean accountBean, CreationResponse response) {
        
        ErrorsList listErrorFields = checkFieldsForm(accountBean);

        if (listErrorFields.hasError()) {
            response.error(listErrorFields.generateObjectJson());
        } else { 
            accountService.createAccount(accountBean, response);
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
