package account.creation;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;

import account.ourdependencies.AccountBean;
import account.ourdependencies.ErrorsList;


public class ValidatingServiceTest {
    private AccountService accountService = mock(AccountService.class);
    ValidatingService service = new ValidatingService(accountService);
    CreationResponse response = mock(CreationResponse.class);

    
    @Test public void 
    fails_with_errors_when_the_accountBean_doesnt_contain_required_data() throws Exception {
        AccountBean accountBean = new AccountBean();
        service.createAccount(accountBean, response);
        verify(response).error(new ErrorsList().generateObjectJson());
    }
    
    @Test public void 
    it_forwards_to_accountService_if_accountBean_is_valid() throws Exception {
         
        AccountBean accountBean = new AccountBean("password", "email@home", "siret");
        service.createAccount(accountBean, response);
        verify(accountService).createAccount(accountBean, response);
    }

}
