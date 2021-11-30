package FakeRestApiObjectModels;

import com.shaft.api.RestActions;
import com.shaft.api.RestActions.RequestType;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

@SuppressWarnings({"unchecked", "UnusedReturnValue"})
public class FakeRestApiAuthUsers {
    private final RestActions apiObject;
    public static final String BaseURl = "https://fakerestapi.azurewebsites.net/api/v1/";
    String UsersServiceName = "Users";

    public FakeRestApiAuthUsers(RestActions apiObject) {
        this.apiObject = apiObject;
    }

    private JSONObject Auth(String UserName, String Password) {
        JSONObject AUTH = new JSONObject();
        AUTH.put("userName", UserName);
        AUTH.put("password", Password);
        return AUTH;
    }

    public Response CreateUser(String UserName, String Password) {
        return apiObject.buildNewRequest(UsersServiceName, RequestType.POST)
                .setRequestBody(Auth(UserName, Password))
                .setContentType(ContentType.JSON)
                .performRequest();
    }

    public Response GetUsers() {
        return apiObject.buildNewRequest(UsersServiceName, RequestType.GET)
                .setContentType(ContentType.JSON)
                .performRequest();
    }

    public Response GetUserByID() {
        Response GetUser = apiObject.buildNewRequest(UsersServiceName, RequestType.GET)
                .setContentType(ContentType.JSON)
                .performRequest();
        int id = Integer.parseInt(RestActions.getResponseJSONValue(GetUser, "id[0]"));
        return apiObject.buildNewRequest(UsersServiceName + "/" + id, RequestType.GET).performRequest();
    }

    private JSONObject updateUser(String userName, String password) {
        JSONObject updateUser = new JSONObject();
        updateUser.put("userName", userName);
        updateUser.put("password", password);
        return updateUser;
    }

    public Response UpdateUser(int id, String username, String password) {

        return apiObject.buildNewRequest(UsersServiceName + "/" + id, RequestType.PUT)
                .setRequestBody(updateUser(username, password))
                .setContentType(ContentType.JSON)
                .performRequest();
    }

    public Response DeleteUser(int id){
        return apiObject.buildNewRequest(UsersServiceName + "/" + id, RequestType.DELETE)
                .setContentType(ContentType.JSON)
                .performRequest();
    }


}
