package account.creation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
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
    UserService userService = mock(UserService.class);
    ProfilService profilService = mock(ProfilService.class);
    DataList datalist = mock(DataList.class);
    AccountService service = new AccountService(userService, profilService, datalist);
    CreationResponse response = mock(CreationResponse.class);

    @Test public void 
    it_informs_about_success_if_everything_is_fine() throws Exception {
        String siret = "1234567890123";
        when(profilService.findProfilWithSiret(siret)).thenReturn(new ModelProfil());
        
        service.createAccount(new AccountBean("", "", siret), response);
        
        verify(response).success();
    }
    
    @Test public void 
    it_informs_about_pending_account_creation_if_siret_is_restricted() throws Exception {
        String restrictedSiret = "1112223334445";
        ModelProfil modelProfil = new ModelProfil();
        when(profilService.findProfilWithSiret(restrictedSiret)).thenReturn(modelProfil);
        when(datalist.findAndCheckSiret(restrictedSiret)).thenReturn(true);

        service.createAccount(new AccountBean("", "", restrictedSiret), response);
        
        verify(response).pending();
    }
    
    @Test public void 
    it_informs_about_pending_account_creation_if_email_is_already_used() throws Exception {
        ModelProfil modelProfil = new ModelProfil();
        when(profilService.findProfilWithSiret(anyString())).thenReturn(modelProfil);
        String email = "bill@gates.org";
        when(userService.isEmailAlreadyUsed(email)).thenReturn(true);
        
        service.createAccount(new AccountBean("", email, ""), response);
        
        verify(response).pending();
    }
    
    @Test public void 
    it_informs_about_pending_account_creation_if_anything_goes_wrong_in_Wref() throws Exception {
        when(profilService.findProfilWithSiret(anyString())).thenThrow(new WrefTechnicalException());
        service.createAccount(new AccountBean("", "", ""), response);
        verify(response).pending();
    }
    
    @Test public void 
    it_informs_about_pending_account_creation_if_anything_goes_wrong_in_UserRegistry() throws Exception {
        when(profilService.findProfilWithSiret(anyString())).thenReturn(new ModelProfil());
        when(userService.isEmailAlreadyUsed(anyString())).thenThrow(new UserAPIUserException());
        
        service.createAccount(new AccountBean("", "", ""), response);
        verify(response).pending();
    }

    @Test public void 
    it_informs_about_pending_account_creation_if_anything_goes_wrong_in_UserRegistry2() throws Exception {
        when(profilService.findProfilWithSiret(anyString())).thenReturn(new ModelProfil());
        when(userService.isEmailAlreadyUsed(anyString())).thenThrow(new UserAPICoreException());
        
        service.createAccount(new AccountBean("", "", ""), response);
        verify(response).pending();
    }
    
    @Test public void 
    it_informs_about_pending_account_creation_if_anything_goes_wrong_in_Persistence() throws Exception {
        when(profilService.findProfilWithSiret(anyString())).thenReturn(new ModelProfil());
        when(datalist.findAndCheckSiret(anyString())).thenThrow(new PortalException());
        
        service.createAccount(new AccountBean("", "", ""), response);
        verify(response).pending();
    }

    @Test public void 
    it_informs_about_pending_account_creation_if_anything_goes_wrong_in_Persistence2() throws Exception {
        when(profilService.findProfilWithSiret(anyString())).thenReturn(new ModelProfil());
        when(datalist.findAndCheckSiret(anyString())).thenThrow(new SystemException());
        
        service.createAccount(new AccountBean("", "", ""), response);
        verify(response).pending();
    }
    
}
