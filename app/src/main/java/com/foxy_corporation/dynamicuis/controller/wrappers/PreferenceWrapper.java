package com.foxy_corporation.dynamicuis.controller.wrappers;

import android.content.Context;

import com.foxy_corporation.dynamicuis.R;
import com.foxy_corporation.dynamicuis.model.data_models.PreferenceData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by VlArCo on 13.07.2018.
 */

public class PreferenceWrapper extends BaseWrapper {
    private List<PreferenceData> mPreferences;

    public PreferenceWrapper(Context context) {
        super(context);

        /// temporary test solution
        formDummyTestData();
    }

    /// temporary test solution
    private void formDummyTestData() {
        String jsonString = getJsonStringFromResources();

        JSONArray preferencesJSONArray = null;

        try {
            preferencesJSONArray = new JSONObject(jsonString).optJSONArray("preferences");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();

        PreferenceWrapper.this.mPreferences = gson.fromJson(String.valueOf(preferencesJSONArray), new TypeToken<List<PreferenceData>>(){}.getType());
    }

    /// temporary test solution
    private String getJsonStringFromResources() {
        try {
            // Read file into string builder
            InputStream inputStream = getContext().getResources().openRawResource(R.raw.dummy_data);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder builder = new StringBuilder();

            for (String line; (line = reader.readLine()) != null; ) {
                builder.append(line).append("\n");
            }

            // Parse into JSONObject
            return builder.toString();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    public List<PreferenceData> getPreferencesList () {
        return mPreferences;
    }

}
