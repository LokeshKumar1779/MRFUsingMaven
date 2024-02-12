package com.spotify.oauth2.api.applicationAPI;

import com.spotify.oauth2.api.RestResource;
import com.spotify.oauth2.pojo.Playlist;
import com.spotify.oauth2.utils.ConfigLoader;
import com.spotify.oauth2.utils.DataLoader;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static com.spotify.oauth2.api.Routes.*;
import static com.spotify.oauth2.api.TokenManager.getToken;

public class PlaylistAPI {

//    private static String access_token = "BQD3exkqeixZffZ2Yk_QTUFCJMDIeDekIieOLqJq1YqFZ44Uj-IMxPsvkbPWxoyy95DRbs7V7r9GHyY2ud-KAl67c156ytqj5H5hgWjTHoRGUNf7LAPFu7Grjwka-0uCsH-iPXycSwvryIPytUr2m9TfY6FfVG94BIWBMl9_A6y1A87ZA13OvvYb9iQg1xkkALcEW70FZCyFPOFToNF_0Y0EHbVgq37EfPBC2avX-eqmUvKG4Xh-VOAVmd8438mQ9QZ4vmfMpy1dy8ca";

    @Step
    public static Response post(Playlist requestPayload) {

        return RestResource.post(USERS + "/" + ConfigLoader.getInstance().getProperty("user_id") + PLAYLISTS, getToken(), requestPayload);

    }

    public static Response get(String playlistId) {

        return RestResource.get(PLAYLISTS + "/" + playlistId, getToken());

    }

    public static Response getMyPlaylist() {

        return RestResource.get(MY_PLAYLISTS , getToken());
        //return RestResource.get(PLAYLISTS + "/" + playlistId, getToken());

    }

    public static Response update(Playlist requestPayload, String playlistId) {

        return RestResource.update(PLAYLISTS + "/" + playlistId, getToken(), requestPayload);


    }

    public static Response post(String token, Playlist requestPayload) {

        return RestResource.post(USERS + "/" +ConfigLoader.getInstance().getProperty("user_id") + PLAYLISTS, token, requestPayload);


    }

}
