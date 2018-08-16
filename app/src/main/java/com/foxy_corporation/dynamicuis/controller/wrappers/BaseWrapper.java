package com.foxy_corporation.dynamicuis.controller.wrappers;

import android.content.Context;

/**
 * Created by VlArCo on 13.07.2018.
 */

public abstract class BaseWrapper {
    private Context mContext;

    public BaseWrapper(Context context){
        BaseWrapper.this.mContext = context;
    }

    protected Context getContext(){
        return BaseWrapper.this.mContext;
    }
}
