package FakeRestApi;

import FakeRestApiObjectModels.FakeRestApiAuthUsers;
import com.shaft.api.RestActions;
import com.shaft.driver.DriverFactory;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Users {
    RestActions apiObject;
    FakeRestApiAuthUsers restApiAuthUsers;

    @BeforeClass
    public void beforeClass() {
        apiObject = DriverFactory.getAPIDriver(FakeRestApiAuthUsers.BaseURl);
        restApiAuthUsers = new FakeRestApiAuthUsers(apiObject);
    }

    @Test(description = "Create New User")
    public void CreateUser() {
        restApiAuthUsers.CreateUser("Admin", "pass123");
    }

    @Test(description = "Get All Users")
    public void GetUsers() {
        restApiAuthUsers.GetUsers();
    }

    @Test(description = "Get Specific User By ID")
    public void GetUserByID() {
        restApiAuthUsers.GetUserByID();
    }

    @Test(description = "Update User")
    public void UpdateUser() {
        Response GetUser = restApiAuthUsers.GetUsers();
        int id = Integer.parseInt(RestActions.getResponseJSONValue(GetUser, "id[0]"));
        restApiAuthUsers.UpdateUser(id, "mohamed", "pass456");
    }

    @Test(description = "Delete User ")
    public void DeleteUser() {
        Response GetUser = restApiAuthUsers.GetUsers();
        int id = Integer.parseInt(RestActions.getResponseJSONValue(GetUser, "id[0]"));
        restApiAuthUsers.DeleteUser(id);
    }


}
