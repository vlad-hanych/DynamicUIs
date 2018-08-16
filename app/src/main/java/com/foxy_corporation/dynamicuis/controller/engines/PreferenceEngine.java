package com.foxy_corporation.dynamicuis.controller.engines;

import android.content.Context;

import com.foxy_corporation.dynamicuis.controller.wrappers.PreferenceWrapper;
import com.foxy_corporation.dynamicuis.model.data_models.PreferenceData;

import java.util.List;

/**
 * Created by VlArCo on 13.07.2018.
 */

public class PreferenceEngine extends BaseEngine {
    public PreferenceEngine(Context context) {
        super(context);
    }

    public List<PreferenceData> formPreferences () {
        PreferenceWrapper myPreferenceWrapper = new PreferenceWrapper(getContext());

        return myPreferenceWrapper.getPreferencesList();
    }
}
