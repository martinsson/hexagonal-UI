package account.creation;

import static account.creation.AccountCreationController.DEFAULT_FINAL_URL_NO_CREATED;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;

import account.thirdpartyframework.HttpResponse;

public class CreationResponseTest {

    @Test public void 
    in_the_case_of_error_it_redirects_to_the_error_page() throws Exception {
        HttpResponse httpResponse = mock(HttpResponse.class);
        CreationResponse response = new CreationResponse(httpResponse );
        response.error();
        verify(httpResponse).sendRedirect(DEFAULT_FINAL_URL_NO_CREATED);
    }
    
    @Test public void 
    in_the_case_of_success_it_redirects_to_success_page() throws Exception {
        HttpResponse httpResponse = mock(HttpResponse.class);
        CreationResponse response = new CreationResponse(httpResponse );
        response.success();
        verify(httpResponse).setRenderParameter("action", "redirect");
    }
    
}
