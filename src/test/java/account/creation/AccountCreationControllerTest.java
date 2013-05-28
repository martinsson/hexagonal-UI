package account.creation;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;

import account.ourdependencies.AccountBean;
import account.thirdpartyframework.HttpRequest;
import account.thirdpartyframework.HttpResponse;

public class AccountCreationControllerTest {
    private ValidatingService service = mock(ValidatingService.class);
    AccountCreationController controler = new AccountCreationController(service );
    HttpResponse response = new HttpResponse();
    HttpRequest request = new HttpRequest();
    AccountBean accountBean = new AccountBean();

    
    @Test public void 
    it_asks_for_account_creation_and_provides_an_answer_object() throws Exception {
        AccountBean accountBean = new AccountBean("password", "email@home", "siret");
        controler.doAction(accountBean, request, response);
        
        verify(service).createAccount(eq(accountBean), any(HTTPCreationResponse.class));
    }

    
}
