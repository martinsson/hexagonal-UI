package account.creation;

import static org.mockito.Mockito.mock;

import org.junit.Test;

import account.ourdependencies.AccountBean;
import account.thirdpartyframework.HttpRequest;
import account.thirdpartyframework.HttpResponse;



public class AccountCreationControllerTest {

    @Test public void 
    fails_with_errors() throws Exception {
         AccountCreationController controler = new AccountCreationController(mock(AccountService.class));
         controler.doAction(new AccountBean(), new HttpRequest(), new HttpResponse());
    }
}
