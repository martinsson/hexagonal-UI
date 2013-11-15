package account.creation;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;

import account.thirdpartyframework.HttpResponse;

public class HttpCreationResponseTest {

    @Test
    public void success_redirects_user_to_home_as_logged_user() {
        HttpResponse frameworkResponse = mock(HttpResponse.class);
        CreationResponse response = new HttpCreationResponse(frameworkResponse);
        response.success();
        
        verify(frameworkResponse).setRenderParameter("action", "redirect");
    }

    @Test
    public void pending_informs_user_that_creation_will_be_handled_by_backoffice() {
        HttpResponse frameworkResponse = mock(HttpResponse.class);
        CreationResponse response = new HttpCreationResponse(frameworkResponse);
        response.pending();
        
        verify(frameworkResponse).sendRedirect(AccountCreationController.DEFAULT_FINAL_URL_NO_CREATED);
    }

}
