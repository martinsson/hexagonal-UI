package account.creation;

import static account.creation.AccountCreationController.DEFAULT_FINAL_URL_NO_CREATED;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Ignore;
import org.junit.Test;

import account.ourdependencies.AccountBean;
import account.ourdependencies.TechnicalException;
import account.thirdpartyframework.HttpRequest;
import account.thirdpartyframework.HttpResponse;

@Ignore
public class AccountCreationControllerTest {

    @Test public void 
    it_redirects_to_error_page_if_service_returns_false() 
            throws Exception {
        AccountBean accountBean = new AccountBean("password", "email@home", "siret");
        HttpResponse response = mock(HttpResponse.class);
        controler.doAction(accountBean, request, response);
        verify(response).sendRedirect(DEFAULT_FINAL_URL_NO_CREATED);
    }
    
    

    private CreationResponse creationResponse = new HttpCreationResponse();
    private AccountService service = mock(AccountService.class);
    AccountCreationController controler = new AccountCreationController(service );
    HttpResponse response = new HttpResponse();
    HttpRequest request = new HttpRequest();
    AccountBean accountBean = new AccountBean();
}
