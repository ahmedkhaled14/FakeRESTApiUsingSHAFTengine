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

    /**
     * @return ِAll Cover Photos
     */
    public Response GetCoverPhotos() {
        return apiObject.buildNewRequest(CoverPhotosServiceName, RestActions.RequestType.GET)
                .setContentType(ContentType.JSON)
                .performRequest();

    }

    /**
     * @param url String Value from createCoverPhoto.json
     * @return CoverPhotoBody as JSONObject Value
     */
    private JSONObject CoverPhotoBody(String url) {
        JSONObject coverPhotoBody = new JSONObject();
        coverPhotoBody.put("url", url);
        return coverPhotoBody;
    }

    /**
     * @param url String Value from createCoverPhoto.json
     * @return Create New Cover Photo
     */
    public Response CreateCoverPhoto(String url) {
        return apiObject.buildNewRequest(CoverPhotosServiceName, RestActions.RequestType.POST)
                .setContentType(ContentType.JSON)
                .setRequestBody(CoverPhotoBody(url))
                .performRequest();
    }

    /**
     * @param idBook int Value
     * @return Cover Photos By Book ID
     */
    public Response GetCoverPhotosByBookID(int idBook) {
        return apiObject.buildNewRequest(CoverPhotosServiceName + "/books/covers/" + idBook, RestActions.RequestType.GET)
                .setContentType(ContentType.JSON)
                .performRequest();
    }

    /**
     * @param ID int Value
     * @return Specific Cover Photos By ID
     */
    public Response GetCoverPhotosByID(int ID) {
        return apiObject.buildNewRequest(CoverPhotosServiceName + "/" + ID, RestActions.RequestType.GET)
                .setContentType(ContentType.JSON)
                .performRequest();
    }

    /**
     * @param url String Value from updateCoverPhoto.json
     * @return Updated Cover Photo Body as JSONObject value
     */
    private JSONObject UpdateCoverPhotoBody(String url) {
        JSONObject updatecoverPhotoBody = new JSONObject();
        updatecoverPhotoBody.put("url", url);
        return updatecoverPhotoBody;
    }

    /**
     * @param id  int value
     * @param url String Value from updateCoverPhoto.json
     * @return Updated Cover Photo
     */
    public Response UpdateCoverPhoto(int id, String url) {
        return apiObject.buildNewRequest(CoverPhotosServiceName + "/" + id, RestActions.RequestType.PUT)
                .setContentType(ContentType.JSON)
                .setRequestBody(UpdateCoverPhotoBody(url))
                .performRequest();
    }

    /**
     * @param id int Value
     * @return Deleted Cover Photo
     */
    public Response DeleteCoverPhoto(int id) {
        return apiObject.buildNewRequest(CoverPhotosServiceName + "/" + id, RestActions.RequestType.DELETE)
                .setContentType(ContentType.JSON)
                .performRequest();
    }


}
