package FakeRestApi;

import FakeRestApiObjectModels.CoverPhotosCRUD;
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

@SuppressWarnings("FieldCanBeLocal")
@Epic("Fake RestAPI")
@Feature("API")
public class CoverPhotos {
    private RestActions apiObject;
    private CoverPhotosCRUD coverPhotosCRUD;
    private JSONFileManager createCoverPhotoJson;
    private JSONFileManager getCoverPhotosByBookIdJson;
    private JSONFileManager getCoverPhotosByIdJson;
    private JSONFileManager updateCoverPhotoJson;

    @BeforeClass
    public void beforeClass() {
        apiObject = DriverFactory.getAPIDriver(FakeRestApiAuthUsers.BaseURl);
        coverPhotosCRUD = new CoverPhotosCRUD(apiObject);
        createCoverPhotoJson = new JSONFileManager(System.getProperty("createCoverPhotoJson"));
        getCoverPhotosByBookIdJson = new JSONFileManager(System.getProperty("getCoverPhotosByBookIdJson"));
        getCoverPhotosByIdJson = new JSONFileManager(System.getProperty("getCoverPhotosByIdJson"));
        updateCoverPhotoJson = new JSONFileManager(System.getProperty("updateCoverPhotoJson"));
    }


    @Story("Cover Photos CRUD")
    @Test(description = "Get Cover Photos")
    public void GetCoverPhotos() {
        Response getCoverPhotos = coverPhotosCRUD.GetCoverPhotos();
        int StatusCode = RestActions.getResponseStatusCode(getCoverPhotos);
        Validations.assertThat()
                .object(StatusCode)
                .isEqualTo(200)
                .withCustomReportMessage("assert that Response Status Code is equal 200 Success ")
                .perform();
    }

    @Story("Cover Photos CRUD")
    @Test(description = "Create Cover Photo")
    public void CreateCoverPhoto() {
        Response createCoverPhoto = coverPhotosCRUD.CreateCoverPhoto(createCoverPhotoJson.getTestData("url"));
        Validations.assertThat()
                .response(createCoverPhoto)
                .extractedJsonValue("url")
                .isEqualTo(createCoverPhotoJson.getTestData("url"))
                .perform();

    }

    @Story("Cover Photos CRUD")
    @Test(description = "Get Cover Photos By Book ID")
    public void GetCoverPhotosByBookID() {
        Response GetCoverPhotoIDs = coverPhotosCRUD.GetCoverPhotos();
        int idBook = Integer.parseInt(RestActions.getResponseJSONValue(GetCoverPhotoIDs, "idBook[0]"));
        Response getCoverPhotosByBookID = coverPhotosCRUD.GetCoverPhotosByBookID(idBook);
        Validations.assertThat()
                .response(getCoverPhotosByBookID)
                .extractedJsonValue("idBook")
                .isEqualTo(getCoverPhotosByBookIdJson.getTestData("idBook"))
                .perform();
    }

    @Story("Cover Photos CRUD")
    @Test(description = "Get Cover Photos By ID")
    public void GetCoverPhotosByID() {
        Response GetCoverPhotoIDs = coverPhotosCRUD.GetCoverPhotos();
        int ID = Integer.parseInt(RestActions.getResponseJSONValue(GetCoverPhotoIDs, "id[0]"));
        Response getCoverPhotosByID = coverPhotosCRUD.GetCoverPhotosByID(ID);
        Validations.assertThat()
                .response(getCoverPhotosByID)
                .extractedJsonValue("id")
                .isEqualTo(getCoverPhotosByIdJson.getTestData("id"))
                .perform();
    }

    @Story("Cover Photos CRUD")
    @Test(description = "Update Cover Photo")
    public void UpdateCoverPhoto() {
        Response GetCoverPhotoIDs = coverPhotosCRUD.GetCoverPhotos();
        int id = Integer.parseInt(RestActions.getResponseJSONValue(GetCoverPhotoIDs, "id[0]"));
        Response updateCoverPhoto = coverPhotosCRUD.UpdateCoverPhoto(id, updateCoverPhotoJson.getTestData("url"));
        Validations.assertThat()
                .response(updateCoverPhoto)
                .extractedJsonValue("url")
                .isEqualTo(updateCoverPhotoJson.getTestData("url"))
                .perform();
    }

    @Story("Cover Photos CRUD")
    @Test(description = "Delete Cover Photo")
    public void DeleteCoverPhoto() {
        Response GetCoverPhotoIDs = coverPhotosCRUD.GetCoverPhotos();
        int id = Integer.parseInt(RestActions.getResponseJSONValue(GetCoverPhotoIDs, "id[0]"));
        Response DeleteResponse = coverPhotosCRUD.DeleteCoverPhoto(id);
        String DeleteResponseBody = RestActions.getResponseBody(DeleteResponse);
        Validations.assertThat()
                .object(DeleteResponseBody).isEqualTo("")
                .withCustomReportMessage("assert that Delete Response Body return Empty Body")
                .perform();
    }


}
