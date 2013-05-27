package account.ourdependencies;

import java.util.ArrayList;
import java.util.List;

public class ErrorsList {

    List<String> errors = new ArrayList<String>();
    
    public String generateObjectJson() {
        return "{\"email\":\"missing\"";
    }

    public boolean hasError() {
        return !errors.isEmpty();
    }

    public void add(String error) {
        errors.add(error);
    }

}
