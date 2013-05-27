package account.creation;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import org.junit.Test;

import account.ourdependencies.AccountBean;
import account.thirdpartyframework.HttpRequest;
import account.thirdpartyframework.HttpResponse;



public class AccountCreationControllerTest {

    @Test public void 
    fails_with_errors() throws Exception {
         AccountCreationController controler = new AccountCreationController(mock(AccountService.class));
         HttpResponse response = new HttpResponse();
         controler.doAction(new AccountBean(), new HttpRequest(), response);
         assertThat(response.getAttribute("error_fields_create_compte")).isNotEmpty();
    }
}
