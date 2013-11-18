package account.creation;

import account.thirdpartyframework.HttpResponse;

public class HttpCreationResponse implements CreationResponse {

    private HttpResponse response;

    public HttpCreationResponse(HttpResponse response) {
        this.response = response;
    }

    public HttpCreationResponse() {
    }

    @Override
    public void success() {
        response.setRenderParameter("action", "redirect");
    }

    @Override
    public void pending() {
        response.sendRedirect(AccountCreationController.DEFAULT_FINAL_URL_NO_CREATED);
    }

}
