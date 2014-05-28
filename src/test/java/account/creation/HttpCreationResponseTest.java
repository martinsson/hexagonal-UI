package account.creation;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;

import account.thirdpartyframework.HttpResponse;


public class HttpCreationResponseTest {

    @Test public void 
    success_sets_the_renderParameter_to_redirect() throws Exception {
	HttpResponse frameworkResponse = mock(HttpResponse.class);
	HttpCreationResponse myResponse = new HttpCreationResponse(frameworkResponse);
	
	myResponse.success();
	
	verify(frameworkResponse).setRenderParameter("action", "redirect");
    }

    @Test public void 
    pending_sets_the_renderParameter_to_redirect() throws Exception {
	HttpResponse frameworkResponse = mock(HttpResponse.class);
	HttpCreationResponse myResponse = new HttpCreationResponse(frameworkResponse);
	
	myResponse.pending();
	
	verify(frameworkResponse).sendRedirect("pending-page");
    }
}
