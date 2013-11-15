package account.creation;

import static account.creation.AccountCreationController.DEFAULT_FINAL_URL_NO_CREATED;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Matchers.refEq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Ignore;
import org.junit.Test;

import account.ourdependencies.AccountBean;
import account.ourdependencies.TechnicalException;
import account.thirdpartyframework.HttpRequest;
import account.thirdpartyframework.HttpResponse;

public class AccountCreationControllerTest {
    private AccountService service = mock(AccountService.class);
    AccountCreationController controler = new AccountCreationController(service );
    HttpResponse response = new HttpResponse();
    HttpRequest request = new HttpRequest();
    AccountBean accountBean = new AccountBean();

    @Test public void 
    it_valides_the_form() throws Exception {
        controler.doAction(accountBean, request, response);
        assertThat(response.getAttribute("error_fields_create_compte")).isNotEmpty();
    }

    @Test public void 
    it_redirects_to_success_page_if_service_returns_true() throws Exception {
        AccountBean accountBean = new AccountBean("password", "email@home", "siret");
        HttpResponse response = mock(HttpResponse.class);
        controler.doAction(accountBean, request, response);
        CreationResponse httpCreationResponse = new HttpCreationResponse(response);
        verify(service).createAccount(refEq(accountBean), refEq(httpCreationResponse) );
    }

}
