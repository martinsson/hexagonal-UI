package account.creation;

import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;

import account.creation.AccountCreationController.HttpCreationResponse;
import account.ourdependencies.AccountBean;
import account.thirdpartyframework.HttpRequest;
import account.thirdpartyframework.HttpResponse;

public class AccountCreationControllerTest {

    @Test public void 
    it_passes_a_httpCreationResponse_to_the_application() 
            throws Exception {
        AccountBean accountBean = new AccountBean("password", "email@home", "siret");
        HttpResponse response = mock(HttpResponse.class);
        controler.doAction(accountBean, request, response);
        
        verify(service).createAccount(eq(accountBean), isA(HttpCreationResponse.class));
    }
    
    private AccountService service = mock(AccountService.class);
    AccountCreationController controler = new AccountCreationController(service );
    HttpRequest request = new HttpRequest();
}
