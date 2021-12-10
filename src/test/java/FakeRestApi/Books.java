package FakeRestApi;

import FakeRestApiObjectModels.BooksCRUD;
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
@Epic("Fake RestAPI")
@Feature("API")
public class Books {
    private RestActions apiObject;
    private BooksCRUD booksCRUD;
    private JSONFileManager createBookJson;
    private JSONFileManager getBookByIdJson;
    private JSONFileManager updateBookJson;

    @BeforeClass
    public void beforeClass() {
        apiObject = DriverFactory.getAPIDriver(FakeRestApiAuthUsers.BaseURl);
        booksCRUD = new BooksCRUD(apiObject);
        createBookJson = new JSONFileManager(System.getProperty("createBookJson"));
        getBookByIdJson = new JSONFileManager(System.getProperty("getBookByIdJson"));
        updateBookJson = new JSONFileManager(System.getProperty("updateBookJson"));
    }

    @Story("Books CRUD")
    @Test(description = "Get All Books")
    public void GetBooks() {
        Response getBooks = booksCRUD.GetBooks();
        int StatusCode = RestActions.getResponseStatusCode(getBooks);

        Validations.assertThat()
                .object(StatusCode)
                .isEqualTo(200)
                .withCustomReportMessage("assert that Response Status Code is equal 200 Success ")
                .perform();
    }

    @Story("Books CRUD")
    @Test(description = "Create Book")
    public void CreateBook() {
        Response createBook = booksCRUD.CreateBook
                (
                        createBookJson.getTestData("title"),
                        createBookJson.getTestData("description"),
                        Integer.parseInt(createBookJson.getTestData("pageCount")),
                        createBookJson.getTestData("excerpt"),
                        createBookJson.getTestData("publishDate")
                );

        Validations.assertThat()
                .response(createBook)
                .extractedJsonValue("title")
                .isEqualTo(createBookJson.getTestData("title"))
                .perform();

    }

    @Story("Books CRUD")
    @Test(description = "Get Book By ID")
    public void GetBookByID() {
        Response GetBookIDs = booksCRUD.GetBooks();
        int id = Integer.parseInt(RestActions.getResponseJSONValue(GetBookIDs, "id[0]"));
        Response GetBook = booksCRUD.GetBookByID(id);

        Validations.assertThat()
                .response(GetBook)
                .extractedJsonValue("title")
                .isEqualTo(getBookByIdJson.getTestData("title"))
                .perform();
    }

    @Story("Books CRUD")
    @Test(description = "Update Book")
    public void UpdateBook() {
        Response GetBookIDs = booksCRUD.GetBooks();
        int id = Integer.parseInt(RestActions.getResponseJSONValue(GetBookIDs, "id[0]"));
        Response updateBook = booksCRUD.UpdateBook(
                id,
                updateBookJson.getTestData("title"),
                updateBookJson.getTestData("description"),
                Integer.parseInt(updateBookJson.getTestData("pageCount")),
                updateBookJson.getTestData("excerpt"),
                updateBookJson.getTestData("publishDate"));

        Validations.assertThat()
                .response(updateBook)
                .extractedJsonValue("title")
                .isEqualTo(updateBookJson.getTestData("title"))
                .perform();
    }

    @Story("Books CRUD")
    @Test(description = "Delete Book")
    public void DeleteBook() {
        Response GetBookIDs = booksCRUD.GetBooks();
        int id = Integer.parseInt(RestActions.getResponseJSONValue(GetBookIDs, "id[0]"));
        Response DeleteResponse = booksCRUD.DeleteBook(id);
        String DeleteResponseBody = RestActions.getResponseBody(DeleteResponse);
        Validations.assertThat()
                .object(DeleteResponseBody).isEqualTo("")
                .withCustomReportMessage("assert that Delete Response Body return Empty Body")
                .perform();
    }


}
