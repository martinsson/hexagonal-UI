package account.thirdpartyframework;

import java.util.HashMap;
import java.util.Map;


public class HttpResponse {

    Map<String, String> attributes = new HashMap<String, String>();
    public void setAttribute(String attribute, String value) {
        attributes.put(attribute, value);
    }

    public void setRenderParameter(String string, String string2) {
        // TODO Auto-generated method stub
        
    }

    public void sendRedirect(String defaultFinalUrlNoCreated) {
        // TODO Auto-generated method stub
        
    }

    public String getAttribute(String string) {
        return attributes.get(string);
    }

}
