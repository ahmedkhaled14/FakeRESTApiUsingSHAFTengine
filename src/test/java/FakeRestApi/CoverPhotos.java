package FakeRestApi;

import FakeRestApiObjectModels.CoverPhotosCRUD;
import FakeRestApiObjectModels.FakeRestApiAuthUsers;
import com.shaft.api.RestActions;
import com.shaft.driver.DriverFactory;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CoverPhotos {
    private RestActions apiObject;
    private CoverPhotosCRUD coverPhotosCRUD;
    private FakeRestApiAuthUsers restApiAuthUsers;

    @BeforeClass
    public void beforeClass() {
        apiObject = DriverFactory.getAPIDriver(FakeRestApiAuthUsers.BaseURl);
        coverPhotosCRUD = new CoverPhotosCRUD(apiObject);
        restApiAuthUsers = new FakeRestApiAuthUsers(apiObject);
        restApiAuthUsers.CreateUser("Ahmed", "pass123");
    }

    @Test(description = "Get Cover Photos")
    public void GetCoverPhotos() {
        coverPhotosCRUD.GetCoverPhotos();
    }

    @Test(description = "Create Cover Photo")
    public void CreateCoverPhoto() {
        coverPhotosCRUD.CreateCoverPhoto("Ahmed");
    }

    @Test(description = "Get Cover Photos By Book ID")
    public void GetCoverPhotosByBookID() {
        Response GetCoverPhotoIDs = coverPhotosCRUD.GetCoverPhotos();
        int idBook = Integer.parseInt(RestActions.getResponseJSONValue(GetCoverPhotoIDs, "idBook[0]"));
        coverPhotosCRUD.GetCoverPhotosByBookID(idBook);
    }

    @Test(description = "Get Cover Photos By ID")
    public void GetCoverPhotosByID() {
        Response GetCoverPhotoIDs = coverPhotosCRUD.GetCoverPhotos();
        int ID = Integer.parseInt(RestActions.getResponseJSONValue(GetCoverPhotoIDs, "id[0]"));
        coverPhotosCRUD.GetCoverPhotosByID(ID);
    }

    @Test(description = "Update Cover Photo")
    public void UpdateCoverPhoto() {
        Response GetCoverPhotoIDs = coverPhotosCRUD.GetCoverPhotos();
        int id = Integer.parseInt(RestActions.getResponseJSONValue(GetCoverPhotoIDs, "id[0]"));
        coverPhotosCRUD.UpdateCoverPhoto(id, "https://UpdateCoverPhoto");
    }

    @Test(description = "Delete Cover Photo")
    public void DeleteCoverPhoto() {
        Response GetCoverPhotoIDs = coverPhotosCRUD.GetCoverPhotos();
        int id = Integer.parseInt(RestActions.getResponseJSONValue(GetCoverPhotoIDs, "id[0]"));
        coverPhotosCRUD.DeleteCoverPhoto(id);
    }


}
