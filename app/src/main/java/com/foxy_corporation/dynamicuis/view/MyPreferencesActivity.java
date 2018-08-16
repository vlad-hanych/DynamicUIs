package com.foxy_corporation.dynamicuis.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.foxy_corporation.dynamicuis.R;
import com.foxy_corporation.dynamicuis.controller.engines.PreferenceEngine;
import com.foxy_corporation.dynamicuis.model.data_models.OptionData;
import com.foxy_corporation.dynamicuis.model.data_models.PreferenceData;
import com.nex3z.flowlayout.FlowLayout;
import com.rm.rmswitch.RMSwitch;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.bendik.simplerangeview.SimpleRangeView;

public class MyPreferencesActivity extends AppCompatActivity {
    private static final int DIVIDER_DP_HEIGHT = 5;

    private static final int STANDARD_CONTAINER_DP_HEIGHT = 110;

    private static final int SLIDER_CONTAINER_DP_HEIGHT = 130;

    private static final float MARGIN = 0.5f;

    public static final String EMPTY_STRING = "";

    public static final int ZERO = 0;
    public static final String SELECT_PREFERENCE_TYPE = "SELECT";
    public static final String SLIDER_PREFERENCE_TYPE = "SLIDER";
    public static final String SWITCH_PREFERENCE_TYPE = "SWITCH";
    public static final String MULTI_SELECT_PREFERENCE_TYPE = "MULTI_SELECT";
    public static final String TAG_CLOUD_PREFERENCE_TYPE = "TAG_CLOUD";
    public static final int SLIDER_LEFT_MARGIN = 5;
    public static final int SLIDER_TOP_AND_RIGHT_MARGIN = 10;
    public static final int SLIDER_BOTTOM_MARGIN = 3;
    public static final int SWITCH_OPTION_BOTTOM_AND_LEFT_MARGIN = 20;
    public static final int SWITCH_BUTT_WIDTH = 50;
    public static final int SWITCH_BUTT_HEIGHT = 30;
    public static final int SWITCH_BUTT_BOTTOM_MARGIN = 20;
    public static final int SWITCH_BUTT_RIGHT_MARGIN = 25;
    public static final int ONE = 1;

    private LayoutInflater mInflater;

    private float mScale;

    private DialogChoiseAdapter adapter;

    @BindView(R.id.dynamicalPreferencesContainer_lilLay_AMPD)
    LinearLayout mDynamicalPreferencesContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mScale = getResources().getDisplayMetrics().density;

        setContentView(R.layout.activity_my_preferences_dynamic);

        ButterKnife.bind(MyPreferencesActivity.this);

        PreferenceEngine myPreferenceEngine = new PreferenceEngine(MyPreferencesActivity.this);

        List<PreferenceData> preferencesList = myPreferenceEngine.formPreferences();

        mInflater = (LayoutInflater) MyPreferencesActivity.this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        parsePreferencesList(preferencesList);
    }

    private void parsePreferencesList(List<PreferenceData> preferencesList) {
        int preferencesListSize = preferencesList.size();

        for (int i = ZERO; i < preferencesListSize; i++) {
            PreferenceData currentPreference = preferencesList.get(i);

            String preferenceType = currentPreference.getType();

            String preferenceTitle = currentPreference.getTitle();

            boolean isThisLastPreference;

            if (i == preferencesListSize - ONE)
                isThisLastPreference = true;
            else
                isThisLastPreference = false;

            switch (preferenceType) {
                case SELECT_PREFERENCE_TYPE:
                    formSelectPreference(currentPreference, isThisLastPreference);

                    break;

                case SLIDER_PREFERENCE_TYPE:
                    formSliderPreference(isThisLastPreference);

                    break;
                case SWITCH_PREFERENCE_TYPE:
                    formSwitchPreference(isThisLastPreference);

                    break;
                case MULTI_SELECT_PREFERENCE_TYPE:
                    formMultiselectPreferences(currentPreference, isThisLastPreference);

                    break;
                case TAG_CLOUD_PREFERENCE_TYPE:
                    formTagCloudPreferences(preferenceTitle, currentPreference.getOptions(), isThisLastPreference);
            }
        }
    }

    private void formSelectPreference(final PreferenceData preference, boolean isThisLastPreference) {
        RelativeLayout selectPreferenceContainer
                = (RelativeLayout) mInflater.inflate(R.layout.dynamical_select_preference_container, null);

        RelativeLayout.LayoutParams selectPreferenceLP
                = new RelativeLayout.LayoutParams
                (RelativeLayout.LayoutParams.MATCH_PARENT, (int) (STANDARD_CONTAINER_DP_HEIGHT * mScale + MARGIN));

        selectPreferenceContainer.setLayoutParams(selectPreferenceLP);

        selectPreferenceContainer.setMinimumHeight(((int) (STANDARD_CONTAINER_DP_HEIGHT * mScale + MARGIN)));

        TextView selectPreferenceTitle = selectPreferenceContainer.findViewById(R.id.selectPreferenceTitle_txt_DSPC);
        selectPreferenceTitle.setText(preference.getTitle());

        final TextView chosenPreference = selectPreferenceContainer.findViewById(R.id.chosenPreference_txt_DSPC);
        chosenPreference.setText(preference.getOptions().get(ZERO).getLabel());

        TextView change = selectPreferenceContainer.findViewById(R.id.textView_changeSelected_DRPC);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(MyPreferencesActivity.this, preference, chosenPreference);
            }
        });

        mDynamicalPreferencesContainer.addView(selectPreferenceContainer);

        if (!isThisLastPreference)
            addDivider();
    }

    public void showDialog(Activity activity, final PreferenceData preference, final TextView chosenPreferenceTxt) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_change_region);

        final List<OptionData> optionData = preference.getOptions();

        final RecyclerView recyclerView;
        recyclerView = dialog.findViewById(R.id.selectDialog);
        adapter = new DialogChoiseAdapter(this, optionData, chosenPreferenceTxt, dialog);
        recyclerView
                .setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        EditText tapToSearch = dialog.findViewById(R.id.tapToSearch);
        tapToSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String search = String.valueOf(charSequence);


                if (charSequence.equals(EMPTY_STRING)) {

                    adapter = new DialogChoiseAdapter(MyPreferencesActivity.this, optionData, chosenPreferenceTxt, dialog);
                    recyclerView.setAdapter(adapter);

                } else {
                    final List<OptionData> searchOptions = new ArrayList<>();
                    for (final OptionData data : preference.getOptions()) {

                        if (data.getLabel().toLowerCase().contains(search.toLowerCase())) {
                            searchOptions.add(data);
                        }

                    }
                    adapter = new DialogChoiseAdapter(MyPreferencesActivity.this, searchOptions, chosenPreferenceTxt, dialog);
                    adapter.notifyDataSetChanged();

                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        dialog.show();
    }

    private void addDivider() {
        View divider = mInflater.inflate(R.layout.dynamical_preference_divider, null);

        divider.setLayoutParams
                (new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (int) (DIVIDER_DP_HEIGHT * mScale + MARGIN)));

        mDynamicalPreferencesContainer
                .addView(divider);
    }

    private void formSliderPreference(boolean isThisLastPreference) {
        RelativeLayout sliderPreferenceContainer
                = (RelativeLayout) mInflater.inflate(R.layout.dynamical_slider_preference_container, null);

        RelativeLayout.LayoutParams sliderPreferenceContainerLP
                = new RelativeLayout.LayoutParams
                (RelativeLayout.LayoutParams.MATCH_PARENT, (int) (SLIDER_CONTAINER_DP_HEIGHT * mScale + MARGIN));

        sliderPreferenceContainer.setLayoutParams(sliderPreferenceContainerLP);

        sliderPreferenceContainer.setMinimumHeight(((int) (SLIDER_CONTAINER_DP_HEIGHT * mScale + MARGIN)));

        SimpleRangeView slider
                = sliderPreferenceContainer.findViewById(R.id.slider_simplrv_DSPC);

        RelativeLayout.LayoutParams sliderLayoutParams
                = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

        sliderLayoutParams.addRule(RelativeLayout.ABOVE, R.id.sun_imgv_DSPC);

        sliderLayoutParams.setMargins((int) (SLIDER_LEFT_MARGIN * mScale + MARGIN),
                (int) (SLIDER_TOP_AND_RIGHT_MARGIN * mScale + MARGIN),
                (int) (SLIDER_TOP_AND_RIGHT_MARGIN * mScale + MARGIN),
                (int) (SLIDER_BOTTOM_MARGIN * mScale + MARGIN));

        slider.setLayoutParams(sliderLayoutParams);

        mDynamicalPreferencesContainer.addView(sliderPreferenceContainer);

        if (!isThisLastPreference)
            addDivider();
    }

    private void formSwitchPreference(boolean isThisLastPreference) {
        RelativeLayout switchPreferenceContainer
                = (RelativeLayout) mInflater.inflate(R.layout.dynamical_switch_preference_container, null);

        RelativeLayout.LayoutParams switchPreferenceContainerLP
                = new RelativeLayout.LayoutParams
                (RelativeLayout.LayoutParams.MATCH_PARENT, (int) (STANDARD_CONTAINER_DP_HEIGHT * mScale + MARGIN));

        switchPreferenceContainer.setLayoutParams(switchPreferenceContainerLP);

        switchPreferenceContainer.setMinimumHeight(((int) (STANDARD_CONTAINER_DP_HEIGHT * mScale + MARGIN)));

        TextView switchOptionTitleTxt = switchPreferenceContainer.findViewById(R.id.switchOptionTitle_txt_DSPC);

        RelativeLayout.LayoutParams switchOptionTitleTxtLP
                = new RelativeLayout.LayoutParams
                (RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

        int bottomMargin = (int) (SWITCH_OPTION_BOTTOM_AND_LEFT_MARGIN * mScale + MARGIN);

        int leftMargin = (int) (SWITCH_OPTION_BOTTOM_AND_LEFT_MARGIN * mScale + MARGIN);

        switchOptionTitleTxtLP.setMargins(leftMargin, ZERO, ZERO, bottomMargin);

        switchOptionTitleTxtLP.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

        switchOptionTitleTxt.setLayoutParams(switchOptionTitleTxtLP);

        switchOptionTitleTxt.setGravity(Gravity.CENTER);

        RMSwitch switchButt = switchPreferenceContainer.findViewById(R.id.switch_swButt_DSPC);

        RelativeLayout.LayoutParams switchButtLP
                = new RelativeLayout.LayoutParams
                ((int) (SWITCH_BUTT_WIDTH * mScale + MARGIN), (int) (SWITCH_BUTT_HEIGHT * mScale + MARGIN));

        int switchBottomMargin = (int) (SWITCH_BUTT_BOTTOM_MARGIN * mScale + MARGIN);

        int switchRightMargin = (int) (SWITCH_BUTT_RIGHT_MARGIN * mScale + MARGIN);

        switchButtLP.setMargins(ZERO, ZERO, switchRightMargin, switchBottomMargin);

        switchButtLP.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

        switchButtLP.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

        switchButt.setLayoutParams(switchButtLP);

        mDynamicalPreferencesContainer.addView(switchPreferenceContainer);

        if (!isThisLastPreference)
            addDivider();
    }

    private void formMultiselectPreferences(PreferenceData preference, boolean isThisLastPreference) {
        for (int i = ZERO; i < preference.getOptions().size(); i++) {
            RelativeLayout switchPreferenceContainer
                    = (RelativeLayout) mInflater.inflate(R.layout.dynamical_multiselect_preference_container, null);

            TextView nameCheckOptions = switchPreferenceContainer.findViewById(R.id.textV_checkName_DGPC);

            TextView nameLayout = switchPreferenceContainer.findViewById(R.id.linerLayout_name_DGPC);

            if (i == ZERO) {
                nameLayout.setVisibility(View.VISIBLE);
            } else {
                nameLayout.setVisibility(View.GONE);
            }
            nameCheckOptions.setText(preference.getOptions().get(i).getLabel());

            mDynamicalPreferencesContainer.addView(switchPreferenceContainer);
        }

        if (!isThisLastPreference)
            addDivider();

    }

    private void formTagCloudPreferences(String title, List<OptionData> optionsList, boolean isThisLastPreference) {
        RelativeLayout tagPreferencesContainer
                = (RelativeLayout) mInflater.inflate(R.layout.dynamical_tag_cloud_preference_container, null);

        TextView headerTxt = (TextView) tagPreferencesContainer.getChildAt(ZERO);

        headerTxt.setText(title);

        FlowLayout dynamicalButtonsContainer = tagPreferencesContainer.findViewById(R.id.tagCloudsDynamicalButtonsContainer_lilbLay_DTCPC);

        for (int i = ZERO; i < optionsList.size(); i++) {
            String optionLabel = optionsList.get(i).getLabel();

            FrameLayout dynamicalButton = (FrameLayout) mInflater.inflate(R.layout.dynamical_tag_preference_button, null);

            TextView dynamicalButtonText = dynamicalButton.findViewById(R.id.dynamicalButtText_txt_DTPB);
            dynamicalButtonText.setText(optionLabel);

            dynamicalButtonsContainer.addView(dynamicalButton);
        }

        mDynamicalPreferencesContainer.addView(tagPreferencesContainer);

        if (!isThisLastPreference)
            addDivider();

    }

    @OnClick(R.id.save_butt_AMP)
    public void save() {
        startActivity(new Intent(MyPreferencesActivity.this, SettingsActivity.class));
    }
}
