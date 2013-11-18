package account.thirdpartyframework;

import java.util.HashMap;
import java.util.Map;


public class HttpResponse {

    Map<String, String> attributes = new HashMap<String, String>();
    private Map<String, String> renderParameters = new HashMap<String, String>();
    private String redirect;
    public void setAttribute(String attribute, String value) {
        attributes.put(attribute, value);
    }

    public void setRenderParameter(String param, String value) {
        renderParameters .put(param, value);
    }

    public void sendRedirect(String url) {
        redirect = url;
    }

    public String getAttribute(String string) {
        return attributes.get(string);
    }

    @Override
    public String toString() {
        return "HttpResponse [attributes=" + attributes + ", renderParameters=" + renderParameters + ", redirect=" + redirect + "]";
    }
    
    

}
