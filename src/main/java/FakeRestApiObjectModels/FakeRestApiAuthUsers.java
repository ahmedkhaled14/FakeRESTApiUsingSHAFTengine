package FakeRestApiObjectModels;

import com.shaft.api.RestActions;
import com.shaft.api.RestActions.RequestType;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;



@SuppressWarnings("ALL")
public class FakeRestApiAuthUsers {
    private final RestActions apiObject;
    public static final String BaseURl = System.getProperty("BaseURl");
    String UsersServiceName = System.getProperty("UsersServiceName");

    public FakeRestApiAuthUsers(RestActions apiObject) {
        this.apiObject = apiObject;
    }

    /**
     * @param UserName String Value From CreateUser.Json
     * @param Password String Value From CreateUser.Json
     * @return New User as JSONObject Value
     */
    private JSONObject Auth(String UserName, String Password) {
        JSONObject AUTH = new JSONObject();
        AUTH.put("userName", UserName);
        AUTH.put("password", Password);
        return AUTH;
    }

    /**
     * @param UserName String Value From createUser.Json
     * @param Password String Value From createUser.Json
     * @return Create New User
     */
    public Response CreateUser(String UserName, String Password) {
        return apiObject.buildNewRequest(UsersServiceName, RequestType.POST)
                .setRequestBody(Auth(UserName, Password))
                .setContentType(ContentType.JSON)
                .performRequest();
    }

    /**
     * @return All Users
     */
    public Response GetUsers() {
        return apiObject.buildNewRequest(UsersServiceName, RequestType.GET)
                .setContentType(ContentType.JSON)
                .performRequest();
    }

    /**
     * @param id int Value
     * @return Specific User By ID
     */
    public Response GetUserByID(int id) {
        return apiObject.buildNewRequest(UsersServiceName + "/" + id, RequestType.GET).performRequest();
    }

    /**
     * @param userName String Value From UpdateUser.Json
     * @param password String Value From UpdateUser.Json
     * @return Updated User as JSONObject Value
     */
    private JSONObject updateUser(String userName, String password) {
        JSONObject updateUser = new JSONObject();
        updateUser.put("userName", userName);
        updateUser.put("password", password);
        return updateUser;
    }

    /**
     * @param id       int Value
     * @param username String Value From UpdateUser.Json
     * @param password String Value From UpdateUser.Json
     * @return
     */
    public Response UpdateUser(int id, String username, String password) {
        return apiObject.buildNewRequest(UsersServiceName + "/" + id, RequestType.PUT)
                .setRequestBody(updateUser(username, password))
                .setContentType(ContentType.JSON)
                .performRequest();
    }

    /**
     * @param id int value
     * @return Deleted User
     */
    public Response DeleteUser(int id) {
        return apiObject.buildNewRequest(UsersServiceName + "/" + id, RequestType.DELETE)
                .setContentType(ContentType.JSON)
                .performRequest();
    }


}
