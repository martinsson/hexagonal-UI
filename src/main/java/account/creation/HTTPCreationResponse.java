package account.creation;

import static account.creation.AccountCreationController.DEFAULT_FINAL_URL_NO_CREATED;
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
    public void error() {
        response.sendRedirect(DEFAULT_FINAL_URL_NO_CREATED);
        
    }
    /* (non-Javadoc)
     * @see account.creation.CreationResponse#success()
     */
    public void success() {
        response.setRenderParameter("action",  "redirect");
    }

}
