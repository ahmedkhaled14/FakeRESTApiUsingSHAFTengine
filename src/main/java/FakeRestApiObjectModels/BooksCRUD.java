package FakeRestApiObjectModels;

import com.shaft.api.RestActions;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

@SuppressWarnings("ALL")
public class BooksCRUD {
    private RestActions apiObject;
    private final String BooksServiceName = "/Books";

    public BooksCRUD(RestActions apiObject) {
        this.apiObject = apiObject;
    }

    public Response GetBooks() {
        return apiObject.buildNewRequest(BooksServiceName, RestActions.RequestType.GET)
                .setContentType(ContentType.JSON)
                .performRequest();
    }

    private JSONObject CreateBookBody(String title, String description, int pageCount, String excerpt, String publishDate) {
        JSONObject createBookBody = new JSONObject();
        createBookBody.put("title", title);
        createBookBody.put("description", description);
        createBookBody.put("pageCount", pageCount);
        createBookBody.put("excerpt", excerpt);
        createBookBody.put("publishDate", publishDate);
        return createBookBody;
    }

    public Response CreateBook(String title, String description, int pageCount, String excerpt, String publishDate) {
        return apiObject.buildNewRequest(BooksServiceName, RestActions.RequestType.POST)
                .setContentType(ContentType.JSON)
                .setRequestBody(CreateBookBody(title, description, pageCount, excerpt, publishDate))
                .performRequest();
    }

    public Response GetBookByID(int id) {
        return apiObject.buildNewRequest(BooksServiceName + "/" + id, RestActions.RequestType.GET)
                .setContentType(ContentType.JSON)
                .performRequest();
    }

    private JSONObject UpdateBookBody(String title, String description, int pageCount, String excerpt, String publishDate) {
        JSONObject updateBookBody = new JSONObject();
        updateBookBody.put("title", title);
        updateBookBody.put("description", description);
        updateBookBody.put("pageCount", pageCount);
        updateBookBody.put("excerpt", excerpt);
        updateBookBody.put("publishDate", publishDate);
        return updateBookBody;
    }

    public Response UpdateBook(int id, String title, String description, int pageCount, String excerpt, String publishDate) {
        return apiObject.buildNewRequest(BooksServiceName + "/" + id, RestActions.RequestType.PUT)
                .setContentType(ContentType.JSON)
                .setRequestBody(CreateBookBody(title, description, pageCount, excerpt, publishDate))
                .performRequest();
    }

    public Response DeleteBook(int id) {
        return apiObject.buildNewRequest(BooksServiceName + "/" + id, RestActions.RequestType.DELETE)
                .setContentType(ContentType.JSON)
                .performRequest();

    }


}
