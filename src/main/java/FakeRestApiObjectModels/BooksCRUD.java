package FakeRestApiObjectModels;

import com.shaft.api.RestActions;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

@SuppressWarnings("ALL")
public class BooksCRUD {
    private RestActions apiObject;
    private final String BooksServiceName = System.getProperty("BooksServiceName");

    public BooksCRUD(RestActions apiObject) {
        this.apiObject = apiObject;
    }

    /**
     * @return All Books
     */
    public Response GetBooks() {
        return apiObject.buildNewRequest(BooksServiceName, RestActions.RequestType.GET)
                .setContentType(ContentType.JSON)
                .performRequest();
    }

    /**
     * @param title       String Value From CreateBook.json
     * @param description String Value From CreateBook.json
     * @param pageCount   int Value From CreateBook.json
     * @param excerpt     String Value From CreateBook.json
     * @param publishDate String Value From CreateBook.json
     * @return Create New BookBody as JSONObject Value
     */
    private JSONObject CreateBookBody(String title, String description, int pageCount, String excerpt, String publishDate) {
        JSONObject createBookBody = new JSONObject();
        createBookBody.put("title", title);
        createBookBody.put("description", description);
        createBookBody.put("pageCount", pageCount);
        createBookBody.put("excerpt", excerpt);
        createBookBody.put("publishDate", publishDate);
        return createBookBody;
    }

    /**
     * @param title       String Value From CreateBook.json
     * @param description String Value From CreateBook.json
     * @param pageCount   int Value From CreateBook.json
     * @param excerpt     String Value From CreateBook.json
     * @param publishDate String Value From CreateBook.json
     * @return Create New Book
     */
    public Response CreateBook(String title, String description, int pageCount, String excerpt, String publishDate) {
        return apiObject.buildNewRequest(BooksServiceName, RestActions.RequestType.POST)
                .setContentType(ContentType.JSON)
                .setRequestBody(CreateBookBody(title, description, pageCount, excerpt, publishDate))
                .performRequest();
    }

    /**
     * @param id int Value
     * @return Specific Book By ID
     */
    public Response GetBookByID(int id) {
        return apiObject.buildNewRequest(BooksServiceName + "/" + id, RestActions.RequestType.GET)
                .setContentType(ContentType.JSON)
                .performRequest();
    }

    /**
     * @param title       String Value From UpdateBook.json
     * @param description String Value From UpdateBook.json
     * @param pageCount   int Value From UpdateBook.json
     * @param excerpt     String Value From UpdateBook.json
     * @param publishDate String Value From UpdateBook.json
     * @return Updated BookBody as JSONObject Value
     */
    private JSONObject UpdateBookBody(String title, String description, int pageCount, String excerpt, String publishDate) {
        JSONObject updateBookBody = new JSONObject();
        updateBookBody.put("title", title);
        updateBookBody.put("description", description);
        updateBookBody.put("pageCount", pageCount);
        updateBookBody.put("excerpt", excerpt);
        updateBookBody.put("publishDate", publishDate);
        return updateBookBody;
    }

    /**
     * @param id          int Value
     * @param title       String Value From UpdateBook.json
     * @param description String Value From UpdateBook.json
     * @param pageCount   int Value From UpdateBook.json
     * @param excerpt     String Value From UpdateBook.json
     * @param publishDate String Value From UpdateBook.json
     * @return Updated Book
     */
    public Response UpdateBook(int id, String title, String description, int pageCount, String excerpt, String publishDate) {
        return apiObject.buildNewRequest(BooksServiceName + "/" + id, RestActions.RequestType.PUT)
                .setContentType(ContentType.JSON)
                .setRequestBody(CreateBookBody(title, description, pageCount, excerpt, publishDate))
                .performRequest();
    }

    /**
     * @param id int Value
     * @return Deleted Book
     */
    public Response DeleteBook(int id) {
        return apiObject.buildNewRequest(BooksServiceName + "/" + id, RestActions.RequestType.DELETE)
                .setContentType(ContentType.JSON)
                .performRequest();

    }


}
