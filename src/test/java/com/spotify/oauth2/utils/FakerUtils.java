package com.spotify.oauth2.utils;

import com.github.javafaker.Faker;

public class FakerUtils {

    public static String generateName(){
        return "Playlist_" + new Faker().regexify("[A-Za-z0-9]{10}");
    }

    public static String generateDescription(){
        return "Description_" + new Faker().regexify("[A-Za-z0-9_@$/*]{20}");
    }
}
