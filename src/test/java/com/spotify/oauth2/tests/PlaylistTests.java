package com.spotify.oauth2.tests;

import com.spotify.oauth2.api.StatusCode;
import com.spotify.oauth2.api.applicationAPI.PlaylistAPI;
import com.spotify.oauth2.pojo.Error;
import com.spotify.oauth2.pojo.Playlist;
import com.spotify.oauth2.utils.DataLoader;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.spotify.oauth2.utils.FakerUtils.generateDescription;
import static com.spotify.oauth2.utils.FakerUtils.generateName;
import static io.qameta.allure.SeverityLevel.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PlaylistTests extends BaseTest{

    @Test(description = "Should be able to create a playlist")
    @Description("Playlist creation testcase")
    @Severity(CRITICAL)
    @Owner("Lokesh Kumar01")
    @Link(name = "Website", url = "https://dev.example.com")
    @Issue("AUTH-123")
    @TmsLink("TMS-456")
    @Epic("Playlist Create")
    @Feature("Playlist Create01")
    @Story("Playlist Create01-01")
    public void shouldBeAbleToCreatePlaylist() {

        Playlist requestPayload = playlistBuilder(generateName(), generateDescription(), false);
        Response response = PlaylistAPI.post(requestPayload);
        assertStatusCode(response.statusCode(), StatusCode.STATUS_CODE_201.getCode());
        assertPlaylistEqual(requestPayload, response.as(Playlist.class));
    }

    @Test(description = "Get a playlist")
    @Description("Playlist get testcase")
    @Severity(CRITICAL)
    @Owner("Lokesh Kumar02")
    @Link(name = "Website", url = "https://dev.example.com")
    @Issue("AUTH-123")
    @TmsLink("TMS-456")
    @Epic("Playlist Get")
    @Feature("Playlist Get01")
    @Story("Playlist Get01-01")
    public void shouldBeAbleToGetPlaylist() {

        Playlist requestPlaylist = playlistBuilder("Playlist #1", "Playlist1", true);
        Response response = PlaylistAPI.get(DataLoader.getInstance().getProperty("playlist_id_1"));
        assertStatusCode(response.statusCode(), StatusCode.STATUS_CODE_200.getCode());
        assertPlaylistEqual(requestPlaylist, response.as(Playlist.class));
    }

    @Test
    public void getCurrentUserPlaylist(){
        Response response = PlaylistAPI.getMyPlaylist();
        assertStatusCode(response.statusCode(), StatusCode.STATUS_CODE_200.getCode());
    }


    @Test
    @Description("Playlist update testcase")
    @Severity(NORMAL)
    @Owner("Lokesh Kumar03")
    @Link(name = "Website", url = "https://dev.example.com")
    @Issue("AUTH-123")
    @TmsLink("TMS-456")
    public void shouldBeAbleToUpdatePlaylist() {
        Playlist requestPlaylist = playlistBuilder(generateName(), generateDescription(),
                false);

        Response response = PlaylistAPI.update(requestPlaylist, DataLoader.getInstance().getProperty("playlist_id_2"));
        assertStatusCode(response.statusCode(), StatusCode.STATUS_CODE_200.getCode());
    }

    @Test
    @Description("Create playlist without name testcase")
    @Severity(MINOR)
    @Owner("Lokesh Kumar04")
    @Link(name = "Website", url = "https://dev.example.com")
    @Issue("AUTH-123")
    @TmsLink("TMS-456")
    public void shouldNotBeAbleToCreatePlaylistWithoutName() {

        Playlist requestPayload = playlistBuilder("", generateDescription(),
                false);
        Response response = PlaylistAPI.post(requestPayload);
        assertStatusCode(response.statusCode(), 400);
        assertError(response.as(Error.class), StatusCode.STATUS_CODE_400.getCode(), StatusCode.STATUS_CODE_400.getMsg());

    }

    @Test
    @Description("Create playlist WithExpiredToken testcase")
    @Severity(MINOR)
    @Owner("Lokesh Kumar05")
    @Link(name = "Website", url = "https://dev.example.com")
    @Issue("AUTH-123")
    @TmsLink("TMS-456")
    public void shouldNotBeAbleToCreatePlaylistWithExpiredToken() {

        String invalid_token = "12345";
        Playlist requestPlaylist = playlistBuilder(generateName(), generateDescription(), false);
        Response response = PlaylistAPI.post(invalid_token, requestPlaylist);
        assertStatusCode(response.statusCode(), StatusCode.STATUS_CODE_401.getCode());
        assertError(response.as(Error.class), StatusCode.STATUS_CODE_401.getCode(), StatusCode.STATUS_CODE_401.getMsg());

    }


    @Step
    public Playlist playlistBuilder(String playlistName, String description, boolean _public) {
        return Playlist.builder().name(playlistName).description(description)._public(_public).build();
    }

    @Step
    public void assertPlaylistEqual(Playlist requestPayload, Playlist responsePayload) {
        assertThat(requestPayload.getName(), equalTo(responsePayload.getName()));
        assertThat(requestPayload.getDescription(), equalTo(responsePayload.getDescription()));
        assertThat(requestPayload.get_public(), equalTo(responsePayload.get_public()));
    }

    @Step
    public void assertStatusCode(int actualStatusCode, int expectedStatusCode) {
        assertThat(actualStatusCode, equalTo(expectedStatusCode));
    }

    @Step
    public void assertError(Error responseError, int expectedStatusCode, String expectedErrorMsg) {
        assertThat(responseError.getError_().getStatus(), equalTo(expectedStatusCode));
        assertThat(responseError.getError_().getMessage(), equalTo(expectedErrorMsg));
    }


}
