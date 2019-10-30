package com.example.fbchatbot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Accessors(chain = true)
public class ShippingAddress {

    private Long id;

    private String name;

    private String country;

    private String city;

    @JsonProperty("street_1")
    private String street1;

    @JsonProperty("street_2")
    private String street2;

    private String state;

    @JsonProperty("postal_code")
    private String postalCode;

}
