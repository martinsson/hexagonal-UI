package account.creation;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.Mock;

import account.ourdependencies.AccountBean;
import account.ourdependencies.DataList;
import account.ourdependencies.ModelProfil;
import account.ourdependencies.ProfilService;
import account.ourdependencies.TechnicalException;
import account.ourdependencies.UserService;
import account.thirdpartyframework.WrefTechnicalException;


public class AccountServiceTest {

    @Test public void 
    responds_success_when_all_is_OK() 
	    throws Exception {
        String siret = "1234567890123";
        when(profilService.findProfilWithSiret(siret)).thenReturn(new ModelProfil(siret));
        
        service.createAccount(new AccountBean("", "", siret), response);
        
        verify(response).success();
    }
    
    @Test public void 
    responds_with_pending_when_siret_is_restrained() 
	    throws Exception {
        String restrictedSiret = "1112223334445";
        ModelProfil modelProfil = new ModelProfil(restrictedSiret);
        when(profilService.findProfilWithSiret(restrictedSiret)).thenReturn(modelProfil);
        when(datalist.findAndCheckSiret(restrictedSiret)).thenReturn(true);

        service.createAccount(new AccountBean("", "", restrictedSiret), response);
        
        verify(response).pending();
    }
    
    @Test public void 
    responds_with_pending_when_email_is_already_used() 
	    throws Exception {
        ModelProfil modelProfil = new ModelProfil("");
        when(profilService.findProfilWithSiret(anyString())).thenReturn(modelProfil);
        String email = "bill@gates.org";
        when(userService.isEmailAlreadyUsed(email)).thenReturn(true);
        
        service.createAccount(new AccountBean("", email, ""), response);
        
        verify(response).pending();
    }
    
    @Test public void 
    responds_with_pending_when_theres_en_exception()
	    throws Exception {
        when(profilService.findProfilWithSiret(anyString())).thenThrow(new WrefTechnicalException());
        service.createAccount(new AccountBean("", "", ""), response);
        
        verify(response).pending();
    }
    
    UserService userService = mock(UserService.class);
    ProfilService profilService = mock(ProfilService.class);
    DataList datalist = mock(DataList.class);
    AccountService service = new AccountService(userService, profilService, datalist);
    CreationResponse response = mock(CreationResponse.class);

}
