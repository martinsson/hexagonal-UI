package account.creation;


public interface CreationResponse {

    void pending();

    void success();

    void error(String errors);

}