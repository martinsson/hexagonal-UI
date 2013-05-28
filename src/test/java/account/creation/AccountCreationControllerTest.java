package account.creation;

import static account.creation.AccountCreationController.DEFAULT_FINAL_URL_NO_CREATED;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

import org.junit.Test;

import account.ourdependencies.AccountBean;
import account.thirdpartyframework.HttpRequest;
import account.thirdpartyframework.HttpResponse;

public class AccountCreationControllerTest {
    private AccountService service = mock(AccountService.class);
    AccountCreationController controler = new AccountCreationController(service );
    HttpResponse response = new HttpResponse();
    HttpRequest request = new HttpRequest();
    AccountBean accountBean = new AccountBean();

    @Test public void 
    fails_with_errors_when_the_accountBean_doesnt_contain_required_data() throws Exception {
        controler.doAction(accountBean, request, response);
        assertThat(response.getAttribute("error_fields_create_compte")).isNotEmpty();
        verifyZeroInteractions(service);
    }

    @Test public void 
    it_asks_for_account_creation_and_provides_an_answer_object() throws Exception {
        AccountBean accountBean = new AccountBean("password", "email@home", "siret");
        HttpResponse response = mock(HttpResponse.class);
        
        controler.doAction(accountBean, request, response);
        
        verify(service).createAccount(eq(accountBean), any(CreationResponse.class));
        
        verify(response).sendRedirect(DEFAULT_FINAL_URL_NO_CREATED);
    }

    
    /*
     * 
    @Test public void 
    it_redirects_to_error_page_if_service_returns_false() throws Exception {
        AccountBean accountBean = new AccountBean("password", "email@home", "siret");
        HttpResponse response = mock(HttpResponse.class);
        controler.doAction(accountBean, request, response);
        verify(response).sendRedirect(DEFAULT_FINAL_URL_NO_CREATED);
    }
    
    @Test public void 
    it_redirects_to_error_page_if_service_throws_an_exception() throws Exception {
        AccountBean accountBean = new AccountBean("password", "email@home", "siret");
        HttpResponse response = mock(HttpResponse.class);
        // TODO verify the log, using a real instantiated exception
        when(service.createAccount(accountBean)).thenThrow(TechnicalException.class);
        controler.doAction(accountBean, request, response);
        verify(response).sendRedirect(DEFAULT_FINAL_URL_NO_CREATED);
    }
    
    @Test public void 
    it_redirects_to_success_page_if_service_returns_true() throws Exception {
        AccountBean accountBean = new AccountBean("password", "email@home", "siret");
        HttpResponse response = mock(HttpResponse.class);
        when(service.createAccount(accountBean)).thenReturn(true);
        controler.doAction(accountBean, request, response);
        verify(response).setRenderParameter("action", "redirect");
    }

     */
}
