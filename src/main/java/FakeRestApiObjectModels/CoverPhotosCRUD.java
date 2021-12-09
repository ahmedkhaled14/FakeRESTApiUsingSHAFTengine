package FakeRestApiObjectModels;

import com.shaft.api.RestActions;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

@SuppressWarnings("ALL")
public class CoverPhotosCRUD {
    private RestActions apiObject;
    private final String CoverPhotosServiceName = System.getProperty("CoverPhotosServiceName");

    public CoverPhotosCRUD(RestActions apiObject) {
        this.apiObject = apiObject;
    }

    public Response GetCoverPhotos() {
        return apiObject.buildNewRequest(CoverPhotosServiceName, RestActions.RequestType.GET)
                .setContentType(ContentType.JSON)
                .performRequest();

    }

    private JSONObject CoverPhotoBody(String url) {
        JSONObject coverPhotoBody = new JSONObject();
        coverPhotoBody.put("url", url);
        return coverPhotoBody;
    }

    public Response CreateCoverPhoto(String url) {
        return apiObject.buildNewRequest(CoverPhotosServiceName, RestActions.RequestType.POST)
                .setContentType(ContentType.JSON)
                .setRequestBody(CoverPhotoBody(url))
                .performRequest();
    }

    public Response GetCoverPhotosByBookID(int idBook) {
        return apiObject.buildNewRequest(CoverPhotosServiceName + "/books/covers/" + idBook, RestActions.RequestType.GET)
                .setContentType(ContentType.JSON)
                .performRequest();
    }

    public Response GetCoverPhotosByID(int ID) {
        return apiObject.buildNewRequest(CoverPhotosServiceName + "/" + ID, RestActions.RequestType.GET)
                .setContentType(ContentType.JSON)
                .performRequest();
    }

    private JSONObject UpdateCoverPhotoBody(String url) {
        JSONObject updatecoverPhotoBody = new JSONObject();
        updatecoverPhotoBody.put("url", url);
        return updatecoverPhotoBody;
    }

    public Response UpdateCoverPhoto(int id, String url) {
        return apiObject.buildNewRequest(CoverPhotosServiceName + "/" + id, RestActions.RequestType.PUT)
                .setContentType(ContentType.JSON)
                .setRequestBody(UpdateCoverPhotoBody(url))
                .performRequest();
    }

    public Response DeleteCoverPhoto(int id) {
        return apiObject.buildNewRequest(CoverPhotosServiceName + "/" + id, RestActions.RequestType.DELETE)
                .setContentType(ContentType.JSON)
                .performRequest();
    }


}
