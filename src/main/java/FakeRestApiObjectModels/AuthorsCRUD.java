package FakeRestApiObjectModels;

import com.shaft.api.RestActions;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

@SuppressWarnings("ALL")
public class AuthorsCRUD {

    private final RestActions apiObject;
    private final String AuthorsService = System.getProperty("AuthorsService");

    public AuthorsCRUD(RestActions apiObject) {
        this.apiObject = apiObject;
    }

    public Response GetAllAuthors() {
        return apiObject.buildNewRequest(AuthorsService, RestActions.RequestType.GET)
                .setContentType(ContentType.JSON)
                .performRequest();
    }

    private JSONObject AuthorBody(int idBook, String firstName, String lastName) {
        JSONObject authorBody = new JSONObject();
        authorBody.put("idBook", idBook);
        authorBody.put("firstName", firstName);
        authorBody.put("lastName", lastName);
        return authorBody;
    }

    public Response CreateAuthor(int idBook, String firstName, String lastName) {
        return apiObject.buildNewRequest(AuthorsService, RestActions.RequestType.POST)
                .setContentType(ContentType.JSON)
                .setRequestBody(AuthorBody(idBook, firstName, lastName))
                .performRequest();
    }

    public Response GetAuthorByBookId(int BookID) {
        return apiObject.buildNewRequest(AuthorsService + "/authors/books/" + BookID, RestActions.RequestType.GET)
                .setContentType(ContentType.JSON)
                .performRequest();
    }

    public Response GetAuthorById(int ID) {
        return apiObject.buildNewRequest(AuthorsService + "/" + ID, RestActions.RequestType.GET)
                .setContentType(ContentType.JSON)
                .performRequest();
    }

    private JSONObject AuthorUpdatedBody(int UpdatedidBook, String UpdatedfirstName, String UpdatedlastName) {
        JSONObject authorUpdatedBody = new JSONObject();
        authorUpdatedBody.put("idBook", UpdatedidBook);
        authorUpdatedBody.put("firstName", UpdatedfirstName);
        authorUpdatedBody.put("lastName", UpdatedlastName);
        return authorUpdatedBody;
    }

    public Response UpdateAuthor(int ID, int UpdatedidBook, String UpdatedfirstName, String UpdatedlastName) {
        return apiObject.buildNewRequest(AuthorsService + "/" + ID, RestActions.RequestType.PUT)
                .setContentType(ContentType.JSON)
                .setRequestBody(AuthorUpdatedBody(UpdatedidBook,UpdatedfirstName,UpdatedlastName))
                .performRequest();
    }

    public Response DeleteAuthor(int ID){
        return apiObject.buildNewRequest(AuthorsService+"/"+ID, RestActions.RequestType.DELETE)
                .setContentType(ContentType.JSON)
                .performRequest();

    }


}
