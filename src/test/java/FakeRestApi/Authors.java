package FakeRestApi;

import FakeRestApiObjectModels.AuthorsCRUD;
import FakeRestApiObjectModels.FakeRestApiAuthUsers;
import com.shaft.api.RestActions;
import com.shaft.driver.DriverFactory;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Authors {

    private RestActions apiObject;
    private AuthorsCRUD authorsCRUD;
    private FakeRestApiAuthUsers restApiAuthUsers;

    @BeforeClass
    public void beforeClass() {
        apiObject = DriverFactory.getAPIDriver(FakeRestApiAuthUsers.BaseURl);
        restApiAuthUsers = new FakeRestApiAuthUsers(apiObject);
        authorsCRUD = new AuthorsCRUD(apiObject);
        restApiAuthUsers.CreateUser("Admin", "pass123");
    }

    @Test(description = "Get All Authors")
    public void GetALlAuthors() {
        authorsCRUD.GetAllAuthors();
    }

    @Test(description = "Create New Authors")
    public void CreateAuthor() {
        authorsCRUD.CreateAuthor(0, "Ahmed", "khaled");
    }

    @Test(description = "Get Author By BookId")
    public void GetAuthorByBookId() {
        Response GetAuthor = authorsCRUD.GetAllAuthors();
        int BookId = Integer.parseInt(RestActions.getResponseJSONValue(GetAuthor, "idBook[0]"));
        authorsCRUD.GetAuthorByBookId(BookId);
    }

    @Test(description = "Get Author By Id")
    public void GetAuthorById(){
        Response GetAuthor = authorsCRUD.GetAllAuthors();
        int Id = Integer.parseInt(RestActions.getResponseJSONValue(GetAuthor, "id[0]"));
        authorsCRUD.GetAuthorById(Id);
    }

    @Test(description = "Update Author")
    public void UpdateAuthor(){
        Response GetAuthor = authorsCRUD.GetAllAuthors();
        int Id = Integer.parseInt(RestActions.getResponseJSONValue(GetAuthor, "id[0]"));
        authorsCRUD.UpdateAuthor(Id,1,"Mohamed","Ali");
    }

    @Test(description = "Delete Author")
    public void DeleteAuthor(){
        Response GetAuthor = authorsCRUD.GetAllAuthors();
        int Id = Integer.parseInt(RestActions.getResponseJSONValue(GetAuthor, "id[0]"));
        authorsCRUD.DeleteAuthor(Id);
    }


}
