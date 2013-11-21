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
    
    @Test public void 
    it_redirects_to_error_page_if_service_throws_an_exception() 
            throws Exception {
        AccountBean accountBean = new AccountBean("password", "email@home", "siret");
        HttpResponse response = mock(HttpResponse.class);
        // TODO verify the log, using a real instantiated exception
//        when(service.createAccount(accountBean)).thenThrow(new TechnicalException(null));
        controler.doAction(accountBean, request, response);
        verify(response).sendRedirect(DEFAULT_FINAL_URL_NO_CREATED);
    }
    
    @Test public void 
    it_redirects_to_success_page_if_service_returns_true() 
            throws Exception {
        AccountBean accountBean = new AccountBean("password", "email@home", "siret");
        HttpResponse response = mock(HttpResponse.class);
//        when(service.createAccount(accountBean)).thenReturn(true);
        controler.doAction(accountBean, request, response);
        verify(response).setRenderParameter("action", "redirect");
    }

    private AccountService service = mock(AccountService.class);
    AccountCreationController controler = new AccountCreationController(service );
    HttpResponse response = new HttpResponse();
    HttpRequest request = new HttpRequest();
    AccountBean accountBean = new AccountBean();
}
