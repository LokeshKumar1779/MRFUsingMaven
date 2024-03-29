package com.spotify.oauth2.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Getter @Setter
@Builder
@Jacksonized
public class Error {
    @JsonProperty("error")
    private Error_ error_;

}
