package account.creation;

import account.thirdpartyframework.HttpResponse;

public final class HttpCreationResponse implements CreationResponse {
    private final HttpResponse response;

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