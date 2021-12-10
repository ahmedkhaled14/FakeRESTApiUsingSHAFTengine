package FakeRestApi;

import FakeRestApiObjectModels.ActivitiesCRUD;
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

@SuppressWarnings("ALL")
@Feature("Fake RestAPI")
@Epic("API")
public class Activities {
    private RestActions apiObject;
    private ActivitiesCRUD activitiesCRUD;
    private JSONFileManager createActivityJson;
    private JSONFileManager getActivityByIDJson;
    private JSONFileManager updateActivityJson;

    @BeforeClass
    public void beforeClass() {
        apiObject = DriverFactory.getAPIDriver(FakeRestApiAuthUsers.BaseURl);
        activitiesCRUD = new ActivitiesCRUD(apiObject);
        createActivityJson = new JSONFileManager(System.getProperty("createActivityJson"));
        getActivityByIDJson = new JSONFileManager(System.getProperty("getActivityByIDJson"));
        updateActivityJson = new JSONFileManager(System.getProperty("updateActivityJson"));

    }

    @Story("Activities CRUD")
    @Test(description = "Get All Activities")
    public void GetActivities() {
        Response getActivities = activitiesCRUD.GetActivities();
        int StatusCode = RestActions.getResponseStatusCode(getActivities);
        Validations.assertThat()
                .object(StatusCode)
                .isEqualTo(200)
                .withCustomReportMessage("assert that Response Status Code is equal 200 Success ")
                .perform();
    }

    @Story("Activities CRUD")
    @Test(description = "Create New Activity")
    public void CreateActivity() {
        Response createActivity = activitiesCRUD.CreateActivity
                (
                        createActivityJson.getTestData("title"),
                        createActivityJson.getTestData("dueDate"),
                        Boolean.parseBoolean(createActivityJson.getTestData("completed"))
                );
        Validations.assertThat()
                .response(createActivity)
                .extractedJsonValue("title")
                .isEqualTo(createActivityJson.getTestData("title"))
                .perform();

    }

    @Story("Activities CRUD")
    @Test(description = "Get Specific Activity By ID ")
    public void GetActivityByID() {
        Response Activities = activitiesCRUD.GetActivities();
        int id = Integer.parseInt(RestActions.getResponseJSONValue(Activities, "id[0]"));
        Response getActivityByID = activitiesCRUD.GetActivityByID(id);
        Validations.assertThat()
                .response(getActivityByID)
                .extractedJsonValue("title")
                .isEqualTo(getActivityByIDJson.getTestData("title"))
                .perform();
    }

    @Story("Activities CRUD")
    @Test(description = "Update Activity")
    public void UpdatedActivity() {
        Response Activities = activitiesCRUD.GetActivities();
        int id = Integer.parseInt(RestActions.getResponseJSONValue(Activities, "id[1]"));
        Response updatedActivity = activitiesCRUD.UpdatedActivity
                (
                        id,
                        updateActivityJson.getTestData("title"),
                        updateActivityJson.getTestData("dueDate"),
                        Boolean.parseBoolean(updateActivityJson.getTestData("completed"))
                );
        Validations.assertThat()
                .response(updatedActivity)
                .extractedJsonValue("title")
                .isEqualTo(updateActivityJson.getTestData("title"))
                .perform();
    }

    @Story("Activities CRUD")
    @Test(description = "Delete Activity")
    public void DeleteActivity() {
        Response Activities = activitiesCRUD.GetActivities();
        int id = Integer.parseInt(RestActions.getResponseJSONValue(Activities, "id[1]"));
        Response DeleteResponse = activitiesCRUD.DeleteActivity(id);
        String DeleteResponseBody = RestActions.getResponseBody(DeleteResponse);
        Validations.assertThat()
                .object(DeleteResponseBody).isEqualTo("")
                .withCustomReportMessage("assert that Delete Response Body return Empty Body")
                .perform();
    }

}
