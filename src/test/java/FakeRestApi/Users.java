package FakeRestApi;

import FakeRestApiObjectModels.FakeRestApiAuthUsers;
import com.shaft.api.RestActions;
import com.shaft.driver.DriverFactory;
import com.shaft.tools.io.JSONFileManager;
import com.shaft.validation.Validations;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@SuppressWarnings("FieldCanBeLocal")
@Epic("Fake RestAPI")
@Feature("API")
public class Users {
    private RestActions apiObject;
    private FakeRestApiAuthUsers restApiAuthUsers;
    public static JSONFileManager createUserJson;
    private JSONFileManager updateUserJson;

    @BeforeClass
    public void beforeClass() {
        apiObject = DriverFactory.getAPIDriver(FakeRestApiAuthUsers.BaseURl);
        restApiAuthUsers = new FakeRestApiAuthUsers(apiObject);
        createUserJson = new JSONFileManager(System.getProperty("createUserJson"));
        updateUserJson = new JSONFileManager(System.getProperty("updateUserJson"));
    }

    @Story("Users CRUD")
    @Test(description = "Create New User")
    public void CreateUser() {
        Response createUser = restApiAuthUsers.CreateUser(createUserJson.getTestData("userName"), createUserJson.getTestData("password"));
        Validations.assertThat()
                .response(createUser)
                .extractedJsonValue("userName")
                .isEqualTo(createUserJson.getTestData("userName"))
                .perform();
    }

    @Story("Users CRUD")
    @Test(description = "Get All Users")
    public void GetUsers() {
        Response getUsers = restApiAuthUsers.GetUsers();
        int StatusCode = RestActions.getResponseStatusCode(getUsers);
        Validations.assertThat()
                .object(StatusCode)
                .isEqualTo(200)
                .withCustomReportMessage("assert that Response Status Code is equal 200 Success ")
                .perform();
    }

    @Story("Users CRUD")
    @Test(description = "Get Specific User By ID")
    public void GetUserByID() {
        Response AllUsers = restApiAuthUsers.GetUsers();
        int id = Integer.parseInt(RestActions.getResponseJSONValue(AllUsers, "id[0]"));
        Response getUserByID = restApiAuthUsers.GetUserByID(id);
        Validations.assertThat()
                .response(getUserByID)
                .isEqualToFileContent(System.getProperty("getUserByID"))
                .perform();
    }

    @Story("Users CRUD")
    @Test(description = "Update User")
    public void UpdateUser() {
        Response GetUser = restApiAuthUsers.GetUsers();
        int id = Integer.parseInt(RestActions.getResponseJSONValue(GetUser, "id[0]"));
        Response updateUser = restApiAuthUsers.UpdateUser(id, updateUserJson.getTestData("userName"), updateUserJson.getTestData("password"));
        Validations.assertThat()
                .response(updateUser)
                .extractedJsonValue("userName")
                .isEqualTo(updateUserJson.getTestData("userName"))
                .perform();
    }

    @Story("Users CRUD")
    @Test(description = "Delete User ")
    public void DeleteUser() {
        Response GetUser = restApiAuthUsers.GetUsers();
        int id = Integer.parseInt(RestActions.getResponseJSONValue(GetUser, "id[0]"));
        Response DeleteResponse = restApiAuthUsers.DeleteUser(id);
        String DeleteResponseBody = RestActions.getResponseBody(DeleteResponse);
        Validations.assertThat()
                .object(DeleteResponseBody).isEqualTo("")
                .withCustomReportMessage("assert that Delete Response Body return Empty Body")
                .perform();
    }


}
