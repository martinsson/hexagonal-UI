package account.creation;

import account.thirdpartyframework.HttpResponse;

public class HttpCreationResponse implements CreationResponse {

    private HttpResponse frameworkResponse;

    public HttpCreationResponse(HttpResponse frameworkResponse) {
        this.frameworkResponse = frameworkResponse;
    }

    @Override
    public void success() {
        frameworkResponse.setRenderParameter("action", "redirect");
    }

    @Override
    public void pending() {
        frameworkResponse.sendRedirect(AccountCreationController.DEFAULT_FINAL_URL_NO_CREATED);
    }

}
