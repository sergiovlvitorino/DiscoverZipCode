package com.github.sergiovlvitorino.discoverzipcode.util;

import org.json.JSONException;
import org.json.JSONObject;

import com.github.sergiovlvitorino.discoverzipcode.Country;
import com.github.sergiovlvitorino.discoverzipcode.bean.AddressBean;

/**
 * Created by sergio on 12/09/15.
 */
public class Converter {

    public AddressBean convertStringResultToAddressBean(Country country, String jsonString){
        switch (country) {
            case BR:
                return convertCorreioControlJsonToAddressBean(jsonString);
            default:
                return convertZippopotamJsonToAddressBean(jsonString);
        }
    }

    public AddressBean convertZippopotamJsonToAddressBean(String jsonString){
        AddressBean addressBean = null;
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            if (jsonObject.length() > 0) {
                addressBean = new AddressBean();
                addressBean.setPostCode(jsonObject.getString("post code"));
                addressBean.setCountry(jsonObject.getString("country"));
                addressBean.setCountryAbbreviation(jsonObject.getString("country abbreviation"));
                addressBean.setCity(jsonObject.getJSONArray("places").getJSONObject(0).getString("place name"));
                addressBean.setState(jsonObject.getJSONArray("places").getJSONObject(0).getString("state"));
                addressBean.setLatitude(jsonObject.getJSONArray("places").getJSONObject(0).getString("latitude"));
                addressBean.setLongitude(jsonObject.getJSONArray("places").getJSONObject(0).getString("longitude"));
                addressBean.setStateAbbreviation(jsonObject.getJSONArray("places").getJSONObject(0).getString("state abbreviation"));
            }
        } catch (JSONException e) {

        }
        return addressBean;
    }

    public AddressBean convertCorreioControlJsonToAddressBean(String jsonString){
        AddressBean addressBean = null;
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            if (jsonObject.length() > 0) {
                addressBean = new AddressBean();
                addressBean.setPostCode(jsonObject.getString("cep"));
                addressBean.setCountry("Brazil");
                addressBean.setCountryAbbreviation("BR");
                addressBean.setStreet(jsonObject.getString("logradouro"));
                addressBean.setNeighborhood(jsonObject.getString("bairro"));
                addressBean.setCity(jsonObject.getString("localidade"));
                addressBean.setState(jsonObject.getString("estado"));
                addressBean.setStateAbbreviation(jsonObject.getString("uf"));
            }
        } catch (JSONException e) {

        }
        return addressBean;
    }

}
