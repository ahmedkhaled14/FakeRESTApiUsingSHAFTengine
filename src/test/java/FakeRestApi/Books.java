package FakeRestApi;

import FakeRestApiObjectModels.BooksCRUD;
import FakeRestApiObjectModels.FakeRestApiAuthUsers;
import com.shaft.api.RestActions;
import com.shaft.driver.DriverFactory;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static FakeRestApi.Users.createUserJson;

public class Books {
    private RestActions apiObject;
    private BooksCRUD booksCRUD;
    private FakeRestApiAuthUsers restApiAuthUsers;

    @BeforeClass
    public void beforeClass() {
        apiObject = DriverFactory.getAPIDriver(FakeRestApiAuthUsers.BaseURl);
        booksCRUD = new BooksCRUD(apiObject);
        restApiAuthUsers = new FakeRestApiAuthUsers(apiObject);
        restApiAuthUsers.CreateUser(createUserJson.getTestData("userName"), createUserJson.getTestData("password"));
    }

    @Test(description = "Get All Books")
    public void GetBooks() {
        booksCRUD.GetBooks();
    }

    @Test(description = "Create Book")
    public void CreateBook() {
        booksCRUD.CreateBook("AhmedBook", "String", 0, "String", "2021-12-08T04:53:45.882Z");
    }

    @Test(description = "Get Book By ID")
    public void GetBookByID() {
        Response GetBookIDs = booksCRUD.GetBooks();
        int id = Integer.parseInt(RestActions.getResponseJSONValue(GetBookIDs, "id[0]"));
        booksCRUD.GetBookByID(id);
    }

    @Test(description = "Update Book")
    public void UpdateBook() {
        Response GetBookIDs = booksCRUD.GetBooks();
        int id = Integer.parseInt(RestActions.getResponseJSONValue(GetBookIDs, "id[0]"));
        booksCRUD.UpdateBook(id, "String", "String", 0, "String", "2021-12-08T05:03:31.654Z");
    }

    @Test(description = "Delete Book")
    public void DeleteBook() {
        Response GetBookIDs = booksCRUD.GetBooks();
        int id = Integer.parseInt(RestActions.getResponseJSONValue(GetBookIDs, "id[0]"));
        booksCRUD.DeleteBook(id);
    }


}
