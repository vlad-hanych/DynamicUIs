package com.foxy_corporation.dynamicuis.model.data_models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by VlArCo on 13.07.2018.
 */

public class PreferenceData {
    @SerializedName("key")
    @Expose
    private String key;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("options")
    @Expose
    private List<OptionData> options = null;
    @SerializedName("min")
    @Expose
    private Integer min;

    @SerializedName("max")
    @Expose
    private Integer max;

    @SerializedName("step")
    @Expose
    private Integer step;

    @SerializedName("label")
    @Expose
    private String label;

    public PreferenceData(String key, String title, String type, List<OptionData> options, Integer min, Integer max, Integer step, String label) {
        PreferenceData.this.key = key;
        PreferenceData.this.title = title;
        PreferenceData.this.type = type;
        PreferenceData.this.options = options;
        PreferenceData.this.min = min;
        PreferenceData.this.max = max;
        PreferenceData.this.step = step;
        PreferenceData.this.label = label;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<OptionData> getOptions() {
        return options;
    }

    public void setOptions(List<OptionData> options) {
        this.options = options;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

}
