package account.creation;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.spy;

import java.io.IOException;

import org.approvaltests.legacycode.LegacyApprovals;
import org.junit.Test;

import account.ourdependencies.AccountBean;
import account.ourdependencies.DataList;
import account.ourdependencies.ProfilService;
import account.ourdependencies.UserService;
import account.thirdpartyframework.HttpResponse;
import account.thirdpartyframework.WrefTechnicalException;

public class GoldenMasterTest {

    private static final Object[] BOTH_BOOLEAN_VALS = {true, false};

    @Test
    public void non_regression() throws Exception {
        String[] email = {"me@home.fr", null, "", "without_AT", "no_dots"};
        String[] siret = {"52448536400011", "", "123", "52448536400011524485364000115244853640001152448536400011"};
        Object[] siretException = BOTH_BOOLEAN_VALS;
        Object[] restricted = BOTH_BOOLEAN_VALS;
        Object[] emailUsed = BOTH_BOOLEAN_VALS;
        LegacyApprovals.LockDown(this, "callACC", email, siret, siretException, restricted, emailUsed);
        
    }
    
    public String callACC(String email, String siret, Boolean siretException, Boolean restricted, Boolean emailUsed) throws IOException {
        ProfilService profilService = spy(new  ProfilService());
        UserService userService = spy(new UserService());
        DataList datalist = spy(new DataList());
        
        doReturn(emailUsed).when(userService).isEmailAlreadyUsed(email);
//        if (siretException)
//            System.out.println("");
////            doThrow(new WrefTechnicalException()).when(datalist).findAndCheckSiret(siret);
//        else
            doReturn(restricted).when(datalist).findAndCheckSiret(siret);
        
        AccountCreationController controller = new AccountCreationController(new AccountService(userService, profilService, datalist));
        HttpResponse response = new HttpResponse();
        controller.doAction(new AccountBean(null, email, siret), null, response);
        
        return response.toString();
        // TODO Auto-generated method stub

    }

}
