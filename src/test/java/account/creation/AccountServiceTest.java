package account.creation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import account.ourdependencies.AccountBean;
import account.ourdependencies.DataList;
import account.ourdependencies.ModelProfil;
import account.ourdependencies.ProfilService;
import account.ourdependencies.TechnicalException;
import account.ourdependencies.UserService;
import account.thirdpartyframework.PortalException;
import account.thirdpartyframework.SystemException;
import account.thirdpartyframework.UserAPICoreException;
import account.thirdpartyframework.UserAPIUserException;
import account.thirdpartyframework.WrefTechnicalException;


public class AccountServiceTest {

    @Test public void 
    returns_true_by_default() 
            throws Exception {
        String siret = "1234567890123";
        when(profilService.findProfilWithSiret(siret)).thenReturn(new ModelProfil());
        
        boolean result = service.createAccount(new AccountBean("", "", siret));
        
        assertTrue(result);
    }
    
    @Test public void 
    returns_false_if_siret_is_restricted() 
            throws Exception {
        String restrictedSiret = "1112223334445";
        ModelProfil modelProfil = new ModelProfil();
        when(profilService.findProfilWithSiret(restrictedSiret)).thenReturn(modelProfil);
        when(datalist.findAndCheckSiret(restrictedSiret)).thenReturn(true);

        boolean result = service.createAccount(new AccountBean("", "", restrictedSiret));
        
        assertFalse(result);
    }
    
    @Test public void 
    returns_false_if_email_is_already_used() 
            throws Exception {
        ModelProfil modelProfil = new ModelProfil();
        when(profilService.findProfilWithSiret(anyString())).thenReturn(modelProfil);
        String email = "bill@gates.org";
        when(userService.isEmailAlreadyUsed(email)).thenReturn(true);
        
        boolean result = service.createAccount(new AccountBean("", email, ""));
        
        assertFalse(result);
    }
    
    @Test(expected=TechnicalException.class) public void 
    wraps_WrefTechnicalException_in_TechnicalException() 
            throws Exception {
        when(profilService.findProfilWithSiret(anyString())).thenThrow(new WrefTechnicalException());
        service.createAccount(new AccountBean("", "", ""));
    }
    
    UserService userService = mock(UserService.class);
    ProfilService profilService = mock(ProfilService.class);
    DataList datalist = mock(DataList.class);
    AccountService service = new AccountService(userService, profilService, datalist);


}
