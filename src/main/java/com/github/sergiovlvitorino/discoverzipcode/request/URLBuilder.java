package com.github.sergiovlvitorino.discoverzipcode.request;

import com.github.sergiovlvitorino.discoverzipcode.Country;
import com.github.sergiovlvitorino.discoverzipcode.util.Constants;

/**
 * Created by sergio on 12/09/15.
 */
public class URLBuilder {

    public String buildURL(Country country, String postCode){
        switch (country) {
            case BR:
                return getCorreioControlURL(postCode);
            default:
                return getZippopotamURL(country, postCode);
        }
    }

    public String getZippopotamURL(Country country, String postCode){
        StringBuilder sb = new StringBuilder();
        sb.append(Constants.ZIPPOPOTAM_URL);
        sb.append("/");
        sb.append(country.name());
        sb.append("/");
        sb.append(postCode);
        return sb.toString();
    }

    public String getCorreioControlURL(String postCode){
        StringBuilder url = new StringBuilder();
        url.append(Constants.CORREIO_CONTROL);
        url.append("/");
        url.append(postCode);
        url.append(".json");
        return url.toString();
    }

}
