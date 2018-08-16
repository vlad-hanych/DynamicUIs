package com.foxy_corporation.dynamicuis.controller.engines;

import android.content.Context;

/**
 * Created by VlArCo on 13.07.2018.
 */

public abstract class BaseEngine {
    private Context mContext;

    public BaseEngine(Context context){
        BaseEngine.this.mContext = context;
    }

    protected Context getContext(){
        return BaseEngine.this.mContext;
    }
}
