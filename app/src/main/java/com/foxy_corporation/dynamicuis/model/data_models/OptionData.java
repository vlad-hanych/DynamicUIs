package com.foxy_corporation.dynamicuis.model.data_models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by VlArCo on 13.07.2018.
 */

public class OptionData {
    @SerializedName("label")
    @Expose
    private String label;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("value")
    @Expose
    private Object value;

    public OptionData(String label, String type, Object value) {
        OptionData.this.label = label;
        OptionData.this.type = type;
        OptionData.this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

}
