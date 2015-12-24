package com.github.sergiovlvitorino.discoverzipcode;

import com.github.sergiovlvitorino.discoverzipcode.core.Core;

import android.content.Context;

/**
 * Created by sergio on 09/09/15.
 */
public class DiscoverZipCode {

    private Context context;
    private Core core;

    public DiscoverZipCode(Context context, int timeout){
        this(context, new Core(context, timeout));
    }

    public DiscoverZipCode(Context context, Core core){
        this.context = context;
        this.core = core;
    }

    public void getAddress(Country country, String postCode, ResultHandler resultHandler){
        core.getAddress(country, postCode, resultHandler);
    }

}
