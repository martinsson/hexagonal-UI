package account.creation;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import account.ourdependencies.AccountBean;
import account.ourdependencies.DataList;
import account.ourdependencies.ModelProfil;
import account.ourdependencies.ProfilService;
import account.ourdependencies.UserService;


public class AccountServiceTest {

    @Test public void 
    dontknowyet() throws Exception {
        UserService userService = mock(UserService.class);
        ProfilService profilService = mock(ProfilService.class);
        DataList datalist = mock(DataList.class);
        String siret = "1234567890123";
        when(profilService.findProfilWithSiret(siret)).thenReturn(new ModelProfil());
        AccountService service = new AccountService(userService, profilService, datalist);
        service.createAccount(new AccountBean("", "", "1234567890123"));
    }
}
