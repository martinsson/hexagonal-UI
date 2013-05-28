package account.creation;

import static account.creation.AccountCreationController.DEFAULT_FINAL_URL_NO_CREATED;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;

import account.ourdependencies.ErrorsList;
import account.thirdpartyframework.HttpResponse;

public class CreationResponseTest {
    HttpResponse httpResponse = mock(HttpResponse.class);
    CreationResponse response = new HTTPCreationResponse(httpResponse );

    @Test public void 
    in_the_case_of_pending_it_informs_about_pending_accountCreation() throws Exception {
        response.pending();
        verify(httpResponse).sendRedirect(DEFAULT_FINAL_URL_NO_CREATED);
    }
    
    @Test public void 
    in_the_case_of_success_it_redirects_to_success_page() throws Exception {
        response.success();
        verify(httpResponse).setRenderParameter("action", "redirect");
    }
    
    @Test public void 
    in_the_case_of_error_it_displays_the_form_with_errors() throws Exception {
        ErrorsList errorsList = new ErrorsList();
        response.error(errorsList.generateObjectJson());
        
        verify(httpResponse).setAttribute("error_fields_create_compte", errorsList.generateObjectJson());
        verify(httpResponse).setRenderParameter("action", "view");
    }
    
}
