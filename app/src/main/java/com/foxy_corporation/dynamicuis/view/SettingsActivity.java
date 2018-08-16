package com.foxy_corporation.dynamicuis.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.foxy_corporation.dynamicuis.R;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingsActivity extends AppCompatActivity {
    private ArrayList<ImageView> mStars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settings);

        ButterKnife.bind(SettingsActivity.this);

        formStarsList ();
    }

    private void formStarsList () {
        mStars = new ArrayList<>();

        ImageView starOne = findViewById(R.id.starOne_imgv_AS);

        mStars.add(starOne);

        ImageView starTwo = findViewById(R.id.starTwo_imgv_AS);

        mStars.add(starTwo);

        ImageView starThree = findViewById(R.id.starThree_imgv_AS);

        mStars.add(starThree);

        ImageView starFour = findViewById(R.id.starFour_imgv_AS);

        mStars.add(starFour);

        ImageView starFive = findViewById(R.id.starFive_imgv_AS);

        mStars.add(starFive);
    }

    @OnClick({ R.id.starOne_imgv_AS, R.id.starTwo_imgv_AS, R.id.starThree_imgv_AS, R.id.starFour_imgv_AS, R.id.starFive_imgv_AS })
    public void setRating (ImageView selectedStarView) {
        for (ImageView starView : mStars) {
            starView.setImageResource(R.drawable.ic_star_not_filled);
        }

        for (ImageView starView : mStars) {
            starView.setImageResource(R.drawable.ic_star_filled);

            if (starView == selectedStarView)
                break;
        }
    }

    @OnClick(R.id.logOut_txt_AS)
    public void logOut() {
        showDialog(SettingsActivity.this,"abhishek.saxena@gmail.com");
    }

    public void showDialog(Activity activity, String msg){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_log_out);

        TextView text = dialog.findViewById(R.id.textV_mail_DLO);
        text.setText(msg);

        TextView dialogButtonCancel =  dialog.findViewById(R.id.textV_cancel_DLO);
        TextView dialogButtonConfirm =  dialog.findViewById(R.id.textV_confirm_DLO);
        dialogButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialogButtonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO logout functionality here.

                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @OnClick(R.id.back_imgv_AS)
    public void back() {
        finish();
    }
}
