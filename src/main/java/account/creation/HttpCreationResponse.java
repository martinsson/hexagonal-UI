package account.creation;

import account.thirdpartyframework.HttpResponse;

public class HttpCreationResponse implements CreationResponse {

    private HttpResponse response;

    public HttpCreationResponse(HttpResponse response) {
        this.response = response;
    }

    public void success() {
        response.setRenderParameter("action", "redirect");
    }

    public void pending() {
        response.sendRedirect(AccountCreationController.DEFAULT_FINAL_URL_NO_CREATED);
    }

}
