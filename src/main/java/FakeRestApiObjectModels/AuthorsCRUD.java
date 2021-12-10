package FakeRestApiObjectModels;

import com.shaft.api.RestActions;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;


@SuppressWarnings("ALL")
public class AuthorsCRUD {

    private final RestActions apiObject;
    private final String AuthorsService = System.getProperty("AuthorsServiceName");

    public AuthorsCRUD(RestActions apiObject) {
        this.apiObject = apiObject;
    }

    /**
     * @return All Authors
     */
    public Response GetAllAuthors() {
        return apiObject.buildNewRequest(AuthorsService, RestActions.RequestType.GET)
                .setContentType(ContentType.JSON)
                .performRequest();
    }

    /**
     * @param idBook    int Value From CreateAuthor.json
     * @param firstName String Value From CreateAuthor.json
     * @param lastName  String Value From CreateAuthor.json
     * @return New Author Body as JSONObject Value
     */
    private JSONObject AuthorBody(int idBook, String firstName, String lastName) {
        JSONObject authorBody = new JSONObject();
        authorBody.put("idBook", idBook);
        authorBody.put("firstName", firstName);
        authorBody.put("lastName", lastName);
        return authorBody;
    }

    /**
     * @param idBook    int Value From CreateAuthor.json
     * @param firstName String Value From CreateAuthor.json
     * @param lastName  String Value From CreateAuthor.json
     * @return Create New Author
     */
    public Response CreateAuthor(int idBook, String firstName, String lastName) {
        return apiObject.buildNewRequest(AuthorsService, RestActions.RequestType.POST)
                .setContentType(ContentType.JSON)
                .setRequestBody(AuthorBody(idBook, firstName, lastName))
                .performRequest();
    }

    /**
     * @param BookID int Value
     * @return Specific Author By Book Id
     */
    public Response GetAuthorByBookId(int BookID) {
        return apiObject.buildNewRequest(AuthorsService + "/authors/books/" + BookID, RestActions.RequestType.GET)
                .setContentType(ContentType.JSON)
                .performRequest();
    }

    /**
     * @param ID int Value
     * @return Specific Author By Id
     */
    public Response GetAuthorById(int ID) {
        return apiObject.buildNewRequest(AuthorsService + "/" + ID, RestActions.RequestType.GET)
                .setContentType(ContentType.JSON)
                .performRequest();
    }

    /**
     * @param UpdatedidBook    int Value From UpdateAuthor.json
     * @param UpdatedfirstName String Value From UpdateAuthor.json
     * @param UpdatedlastName  String Value From UpdateAuthor.json
     * @return Update Author Body as JSONObject Value
     */
    private JSONObject AuthorUpdatedBody(int UpdatedidBook, String UpdatedfirstName, String UpdatedlastName) {
        JSONObject authorUpdatedBody = new JSONObject();
        authorUpdatedBody.put("idBook", UpdatedidBook);
        authorUpdatedBody.put("firstName", UpdatedfirstName);
        authorUpdatedBody.put("lastName", UpdatedlastName);
        return authorUpdatedBody;
    }

    /**
     * @param ID               int Value
     * @param UpdatedidBook    int Value From UpdateAuthor.json
     * @param UpdatedfirstName String Value From UpdateAuthor.json
     * @param UpdatedlastName  String Value From UpdateAuthor.json
     * @return Updated Author
     */
    public Response UpdateAuthor(int ID, int UpdatedidBook, String UpdatedfirstName, String UpdatedlastName) {
        return apiObject.buildNewRequest(AuthorsService + "/" + ID, RestActions.RequestType.PUT)
                .setContentType(ContentType.JSON)
                .setRequestBody(AuthorUpdatedBody(UpdatedidBook, UpdatedfirstName, UpdatedlastName))
                .performRequest();
    }

    /**
     * @param ID int Value
     * @return Deleted Author
     */
    public Response DeleteAuthor(int ID) {
        return apiObject.buildNewRequest(AuthorsService + "/" + ID, RestActions.RequestType.DELETE)
                .setContentType(ContentType.JSON)
                .performRequest();

    }


}
