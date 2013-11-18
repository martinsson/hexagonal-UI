package account.creation;

import java.io.IOException;

import org.approvaltests.legacycode.LegacyApprovals;
import org.junit.Test;

import account.ourdependencies.AccountBean;
import account.ourdependencies.DataList;
import account.ourdependencies.ProfilService;
import account.ourdependencies.UserService;
import account.thirdpartyframework.HttpResponse;

public class GoldenMaster {

    private static final Object[] BOTH_BOOLEAN_VALS = {true, false};

    @Test
    public void non_regression() throws Exception {
        String[] email = {"me@home.fr", null, "", "without_AT", "no_dots"};
        String[] siret = {"52448536400011", "", null, "123", "52448536400011524485364000115244853640001152448536400011"};
        Object[] siretException = BOTH_BOOLEAN_VALS;
        Object[] restricted = BOTH_BOOLEAN_VALS;
        Object[] emailUsed = BOTH_BOOLEAN_VALS;
        LegacyApprovals.LockDown(this, "callACC", email, siret, siretException, restricted, emailUsed);
        
    }
    
    public void callACC(String email, String siret, Boolean siretException, Boolean restricted, Boolean emailUsed) throws IOException {
        ProfilService profilService = new ProfilService();
        UserService userService = new UserService();
        DataList datalist = new DataList();
        AccountCreationController controller = new AccountCreationController(new AccountService(userService, profilService, datalist));
        controller.doAction(new AccountBean(null, email, siret), null, new HttpResponse());
        
        // TODO Auto-generated method stub

    }

}
