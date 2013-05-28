package account.creation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import account.ourdependencies.AccountBean;
import account.ourdependencies.DataList;
import account.ourdependencies.ModelProfil;
import account.ourdependencies.ProfilService;
import account.ourdependencies.UserService;


public class AccountServiceTest {
    UserService userService = mock(UserService.class);
    ProfilService profilService = mock(ProfilService.class);
    DataList datalist = mock(DataList.class);
    AccountService service = new AccountService(userService, profilService, datalist);

    @Test public void 
    returns_true_by_default() throws Exception {
        String siret = "1234567890123";
        when(profilService.findProfilWithSiret(siret)).thenReturn(new ModelProfil());
        
        boolean result = service.createAccount(new AccountBean("", "", siret));
        
        assertTrue(result);
    }
    
    @Test public void 
    returns_false_if_siret_is_restricted() throws Exception {
        String restrictedSiret = "1112223334445";
        ModelProfil modelProfil = new ModelProfil();
        when(profilService.findProfilWithSiret(restrictedSiret)).thenReturn(modelProfil);
        when(datalist.findAndCheckSiret(restrictedSiret)).thenReturn(true);

        boolean result = service.createAccount(new AccountBean("", "", restrictedSiret));
        
        assertFalse(result);
    }
}
