package FakeRestApi;

import FakeRestApiObjectModels.ActivitiesCRUD;
import FakeRestApiObjectModels.FakeRestApiAuthUsers;
import com.shaft.api.RestActions;
import com.shaft.driver.DriverFactory;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Activities {
    RestActions apiObject;
    FakeRestApiAuthUsers restApiAuth;
    ActivitiesCRUD activitiesCRUD;

    @BeforeClass(description = " before Class ==> User Login")
    public void beforeClass() {
        apiObject =  DriverFactory.getAPIDriver(FakeRestApiAuthUsers.BaseURl);
        restApiAuth = new FakeRestApiAuthUsers(apiObject);
        activitiesCRUD = new ActivitiesCRUD(apiObject);
        restApiAuth.CreateUser("Admin", "pass123");
    }

    @Test(description = "Get All Activities")
    public void GetActivities() {
        activitiesCRUD.GetActivities();
    }

    @Test(description = "Create New Activity")
    public void CreateActivity() {
        activitiesCRUD.CreateActivity("AhmedActivity", "10-10-2021", true);
    }

    @Test(description = "Get Specific Activity By ID ")
    public void GetActivityByID() {
        Response Activities = activitiesCRUD.GetActivities();
        int id = Integer.parseInt(RestActions.getResponseJSONValue(Activities, "id[0]"));
        activitiesCRUD.GetActivityByID(id);
    }

    @Test(description = "Update Activity")
    public void UpdatedActivity() {
        Response Activities = activitiesCRUD.GetActivities();
        int id = Integer.parseInt(RestActions.getResponseJSONValue(Activities, "id[1]"));
        activitiesCRUD.UpdatedActivity(id, "mohamed", "2021-11-30T04:20:31.504Z", true);
    }

    @Test(description = "Delete Activity")
    public void DeleteActivity() {
        Response Activities = activitiesCRUD.GetActivities();
        int id = Integer.parseInt(RestActions.getResponseJSONValue(Activities, "id[1]"));
        activitiesCRUD.DeleteActivity(id);
    }

}
