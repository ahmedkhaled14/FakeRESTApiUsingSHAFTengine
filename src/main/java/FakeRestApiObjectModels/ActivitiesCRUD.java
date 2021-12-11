package FakeRestApiObjectModels;

import com.shaft.api.RestActions;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

@SuppressWarnings("ALL")
public class ActivitiesCRUD {
    private RestActions apiObject;
    private String ActivitiesServiceName = System.getProperty("ActivitiesServiceName");

    public ActivitiesCRUD(RestActions apiObject) {
        this.apiObject = apiObject;
    }

    /**
     * @return All Activities
     */
    public Response GetActivities() {
        return apiObject.buildNewRequest(ActivitiesServiceName, RestActions.RequestType.GET).performRequest();
    }

    /**
     * @param title     String Value From CreateActivity.json
     * @param dueDate   String Value From CreateActivity.json
     * @param completed String Value From CreateActivity.json
     * @return Create Activity Body as JSONObject
     */
    private JSONObject CreateActivityBody(String title, String dueDate, Boolean completed) {
        JSONObject Activity = new JSONObject();
        Activity.put("title", title);
        Activity.put("dueDate", dueDate);
        Activity.put("completed", completed);
        return Activity;
    }

    /**
     * @param title     String Value From CreateActivity.json
     * @param dueDate   String Value From CreateActivity.json
     * @param completed String Value From CreateActivity.json
     * @return Create New Activity
     */
    public Response CreateActivity(String title, String dueDate, Boolean completed) {
        return apiObject.buildNewRequest(ActivitiesServiceName, RestActions.RequestType.POST)
                .setRequestBody(CreateActivityBody(title, dueDate, completed))
                .setContentType(ContentType.JSON)
                .performRequest();
    }

    /**
     * @param id int Value
     * @return Activity By ID
     */
    public Response GetActivityByID(int id) {
        return apiObject.buildNewRequest(ActivitiesServiceName + "/" + id, RestActions.RequestType.GET)
                .setContentType(ContentType.JSON)
                .performRequest();
    }

    /**
     * @param title     String Value From UpdateActivity.json
     * @param dueDate   String Value From UpdateActivity.json
     * @param completed String Value From UpdateActivity.json
     * @return Updated Activity Body as JSONObject
     */
    private JSONObject UpdateActivityBody(String title, String dueDate, boolean completed) {
        JSONObject UpdatedActivity = new JSONObject();
        UpdatedActivity.put("title", title);
        UpdatedActivity.put("dueDate", dueDate);
        UpdatedActivity.put("completed", completed);
        return UpdatedActivity;
    }

    /**
     * @param id        int value
     * @param title     String Value From UpdateActivity.json
     * @param dueDate   String Value From UpdateActivity.json
     * @param completed String Value From UpdateActivity.json
     * @return Updated Activity
     */
    public Response UpdatedActivity(int id, String title, String dueDate, boolean completed) {
        return apiObject.buildNewRequest(ActivitiesServiceName + "/" + id, RestActions.RequestType.PUT)
                .setRequestBody(UpdateActivityBody(title, dueDate, completed))
                .setContentType(ContentType.JSON)
                .performRequest();
    }

    /**
     * @param id int Value
     * @return Deleted Activity
     */
    public Response DeleteActivity(int id) {
        return apiObject.buildNewRequest(ActivitiesServiceName + "/" + id, RestActions.RequestType.DELETE)
                .setContentType(ContentType.JSON)
                .performRequest();
    }


}
