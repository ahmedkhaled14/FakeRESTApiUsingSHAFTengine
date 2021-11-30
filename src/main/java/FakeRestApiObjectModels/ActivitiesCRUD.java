package FakeRestApiObjectModels;

import com.shaft.api.RestActions;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

@SuppressWarnings("ALL")
public class ActivitiesCRUD {
    private RestActions apiObject;
    private String ActivitiesServiceName = "Activities";

    public ActivitiesCRUD(RestActions apiObject) {
        this.apiObject = apiObject;
    }

    public Response GetActivities() {
        return apiObject.buildNewRequest(ActivitiesServiceName, RestActions.RequestType.GET).performRequest();
    }

    private JSONObject CreateActivityBody(String title, String dueDate, Boolean completed) {
        JSONObject Activity = new JSONObject();
        Activity.put("title", "AhmedActivity");
        Activity.put("dueDate", "2021-11-28T03:48:01.118Z");
        Activity.put("completed", true);
        return Activity;
    }

    public Response CreateActivity(String title, String dueDate, Boolean completed) {
        return apiObject.buildNewRequest(ActivitiesServiceName, RestActions.RequestType.POST)
                .setRequestBody(CreateActivityBody(title, dueDate, completed))
                .setContentType(ContentType.JSON)
                .performRequest();
    }

    public Response GetActivityByID(int id) {
        return apiObject.buildNewRequest(ActivitiesServiceName +"/" + id, RestActions.RequestType.GET)
                .setContentType(ContentType.JSON)
                .performRequest();
    }

    private JSONObject UpdateActivityBody(String title, String dueDate, boolean completed) {
        JSONObject UpdatedActivity = new JSONObject();
        UpdatedActivity.put("title", title);
        UpdatedActivity.put("dueDate", dueDate);
        UpdatedActivity.put("completed", completed);
        return UpdatedActivity;
    }

    public Response UpdatedActivity(int id, String title, String dueDate, boolean completed) {
        return apiObject.buildNewRequest(ActivitiesServiceName+ "/" + id, RestActions.RequestType.PUT)
                .setRequestBody(UpdateActivityBody(title, dueDate, completed))
                .setContentType(ContentType.JSON)
                .performRequest();
    }

    public Response DeleteActivity(int id) {
        return apiObject.buildNewRequest(ActivitiesServiceName +"/" + id, RestActions.RequestType.DELETE)
                .setContentType(ContentType.JSON)
                .performRequest();
    }


}
