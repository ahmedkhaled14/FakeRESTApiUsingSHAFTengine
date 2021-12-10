package FakeRestApi;

import FakeRestApiObjectModels.AuthorsCRUD;
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
@Feature("Fake RestAPI")
@Epic("API")
public class Authors {

    private RestActions apiObject;
    private AuthorsCRUD authorsCRUD;
    private JSONFileManager createAuthorJson;
    private JSONFileManager getAuthorByBookIdJson;
    private JSONFileManager updateAuthorJson;

    @BeforeClass
    public void beforeClass() {
        apiObject = DriverFactory.getAPIDriver(FakeRestApiAuthUsers.BaseURl);
        authorsCRUD = new AuthorsCRUD(apiObject);
        createAuthorJson = new JSONFileManager(System.getProperty("createAuthorJson"));
        getAuthorByBookIdJson = new JSONFileManager(System.getProperty("getAuthorByBookIdJson"));
        updateAuthorJson = new JSONFileManager(System.getProperty("updateAuthorJson"));
    }

    @Story("Authors CRUD")
    @Test(description = "Get All Authors")
    public void GetALlAuthors() {
        Response getALlAuthors = authorsCRUD.GetAllAuthors();
        int StatusCode = RestActions.getResponseStatusCode(getALlAuthors);
        Validations.assertThat()
                .object(StatusCode)
                .isEqualTo(200)
                .withCustomReportMessage("assert that Response Status Code is equal 200 Success ")
                .perform();
    }

    @Story("Authors CRUD")
    @Test(description = "Create New Authors")
    public void CreateAuthor() {
        Response createAuthor = authorsCRUD.CreateAuthor
                (
                        Integer.parseInt(createAuthorJson.getTestData("idBook")),
                        createAuthorJson.getTestData("firstName"),
                        createAuthorJson.getTestData("lastName")
                );
        Validations.assertThat()
                .response(createAuthor)
                .extractedJsonValue("firstName")
                .isEqualTo(createAuthorJson.getTestData("firstName"))
                .perform();
    }

    @Story("Authors CRUD")
    @Test(description = "Get Author By BookId")
    public void GetAuthorByBookId() {
        Response GetAuthor = authorsCRUD.GetAllAuthors();
        int BookId = Integer.parseInt(RestActions.getResponseJSONValue(GetAuthor, "idBook[0]"));
        Response getAuthorByBookId = authorsCRUD.GetAuthorByBookId(BookId);
        Validations.assertThat()
                .response(getAuthorByBookId)
                .extractedJsonValue("firstName[0]")
                .isEqualTo(getAuthorByBookIdJson.getTestData("firstName"))
                .perform();
    }

    @Story("Authors CRUD")
    @Test(description = "Get Author By Id")
    public void GetAuthorById() {
        Response GetAuthor = authorsCRUD.GetAllAuthors();
        int Id = Integer.parseInt(RestActions.getResponseJSONValue(GetAuthor, "id[0]"));
        Response getAuthorById = authorsCRUD.GetAuthorById(Id);
        Validations.assertThat()
                .response(getAuthorById)
                .isEqualToFileContent(System.getProperty("getAuthorByIdJson"))
                .perform();
    }

    @Story("Authors CRUD")
    @Test(description = "Update Author")
    public void UpdateAuthor() {
        Response GetAuthor = authorsCRUD.GetAllAuthors();
        int Id = Integer.parseInt(RestActions.getResponseJSONValue(GetAuthor, "id[0]"));
        Response updateAuthor = authorsCRUD.UpdateAuthor
                (
                        Id,
                        Integer.parseInt(updateAuthorJson.getTestData("idBook")),
                        updateAuthorJson.getTestData("firstName"),
                        updateAuthorJson.getTestData("lastName")
                );
        Validations.assertThat()
                .response(updateAuthor)
                .extractedJsonValue("firstName")
                .isEqualTo(updateAuthorJson.getTestData("firstName"))
                .perform();
    }

    @Story("Authors CRUD")
    @Test(description = "Delete Author")
    public void DeleteAuthor() {
        Response GetAuthor = authorsCRUD.GetAllAuthors();
        int Id = Integer.parseInt(RestActions.getResponseJSONValue(GetAuthor, "id[0]"));
        Response DeleteResponse = authorsCRUD.DeleteAuthor(Id);
        String DeleteResponseBody = RestActions.getResponseBody(DeleteResponse);
        Validations.assertThat()
                .object(DeleteResponseBody).isEqualTo("")
                .withCustomReportMessage("assert that Delete Response Body return Empty Body")
                .perform();
    }


}
