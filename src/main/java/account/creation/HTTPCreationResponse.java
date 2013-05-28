package account.creation;

import static account.creation.AccountCreationController.DEFAULT_FINAL_URL_NO_CREATED;
import account.ourdependencies.ErrorsList;
import account.thirdpartyframework.HttpResponse;

public class HTTPCreationResponse implements CreationResponse {

    private HttpResponse response;
    public HTTPCreationResponse() {
    }
    public HTTPCreationResponse(HttpResponse response) {
        this.response = response;
    }
    /* (non-Javadoc)
     * @see account.creation.CreationResponse#error()
     */
    public void pending() {
        response.sendRedirect(DEFAULT_FINAL_URL_NO_CREATED);
        
    }
    /* (non-Javadoc)
     * @see account.creation.CreationResponse#success()
     */
    public void success() {
        response.setRenderParameter("action",  "redirect");
    }
    
    @Override
    public void error(String errors) {
        response.setAttribute("error_fields_create_compte", errors);
        response.setRenderParameter("action", "view");
        
    }

}
