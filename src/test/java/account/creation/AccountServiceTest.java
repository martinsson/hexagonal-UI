package account.creation;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;

import account.ourdependencies.AccountBean;
import account.ourdependencies.DataList;
import account.ourdependencies.ModelProfil;
import account.ourdependencies.ProfilService;
import account.ourdependencies.UserService;
import account.thirdpartyframework.WrefTechnicalException;

import com.googlecode.zohhak.api.Coercion;
import com.googlecode.zohhak.api.TestWith;
import com.googlecode.zohhak.api.runners.ZohhakRunner;

@RunWith(ZohhakRunner.class)
public class AccountServiceTest {

    @Test public void 
    is_successful_by_default() throws Exception {

        String siret = "1234567890123";
        when(profilService.findProfilWithSiret(siret)).thenReturn(new ModelProfil());
        
        service.createAccount(new AccountBean("", "", siret), response);
        
        verify(response).success();
    }
    
    @Test public void 
    fails_if_siret_is_restricted() throws Exception {
        String restrictedSiret = "1112223334445";
        ModelProfil modelProfil = new ModelProfil();
        when(profilService.findProfilWithSiret(restrictedSiret)).thenReturn(modelProfil);
        when(datalist.findAndCheckSiret(restrictedSiret)).thenReturn(true);

        service.createAccount(new AccountBean("", "", restrictedSiret), response);
        
        verify(response).error();
    }
    
    @Test public void 
    fails_if_email_is_already_used() throws Exception {
        ModelProfil modelProfil = new ModelProfil();
        when(profilService.findProfilWithSiret(anyString())).thenReturn(modelProfil);
        String email = "bill@gates.org";
        when(userService.isEmailAlreadyUsed(email)).thenReturn(true);
        
        service.createAccount(new AccountBean("", email, ""), response);
        
        verify(response).error();
    }
    
    @Test public void 
    fails_if_the_user_cant_be_found() throws Exception {
        when(profilService.findProfilWithSiret(anyString())).thenThrow(new WrefTechnicalException());
        service.createAccount(new AccountBean("", "", ""), response);
        verify(response).error();
    }

    @TestWith({
        "account.thirdpartyframework.UserAPIUserException",
        "account.thirdpartyframework.UserAPICoreException",
    }) public void 
    fails_in_the_case_of_emailCheckFailure(Exception e) throws Exception {
        when(profilService.findProfilWithSiret(anyString())).thenReturn(new ModelProfil());
        when(userService.isEmailAlreadyUsed(anyString())).thenThrow(e);
        service.createAccount(new AccountBean("", "", ""), response);
        verify(response).error();
    }
    
    @TestWith({
        "account.thirdpartyframework.PortalException",
        "account.thirdpartyframework.SystemException",
    }) public void 
    fails_in_the_case_of_siretCheckFailure(Exception e) throws Exception {
        when(profilService.findProfilWithSiret(anyString())).thenReturn(new ModelProfil());
        when(datalist.findAndCheckSiret(anyString())).thenThrow(e);
        service.createAccount(new AccountBean("", "", ""), response);
        verify(response).error();
    }
    
    @Coercion 
    public Exception whicheverException(String name) throws Exception {
        return (Exception) Class.forName(name).newInstance();
    }
    UserService userService = mock(UserService.class);
    ProfilService profilService = mock(ProfilService.class);
    DataList datalist = mock(DataList.class);
    AccountService service = new AccountService(userService, profilService, datalist);
    HTTPCreationResponse response = mock(HTTPCreationResponse.class);
    
}
