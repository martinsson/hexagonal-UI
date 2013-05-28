package account.creation;

import static account.creation.AccountCreationController.DEFAULT_FINAL_URL_NO_CREATED;
import account.thirdpartyframework.HttpResponse;

public class CreationResponse {

    private HttpResponse response;
    public CreationResponse() {
    }
    public CreationResponse(HttpResponse response) {
        this.response = response;
    }
    public void error() {
        response.sendRedirect(DEFAULT_FINAL_URL_NO_CREATED);
        
    }
    public void success() {
        response.setRenderParameter("action",  "redirect");
    }

}
