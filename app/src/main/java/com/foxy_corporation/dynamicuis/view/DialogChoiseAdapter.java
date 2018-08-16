package com.foxy_corporation.dynamicuis.view;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.foxy_corporation.dynamicuis.R;
import com.foxy_corporation.dynamicuis.model.data_models.OptionData;

import java.util.List;

/**
 * Created by VlArCo on 20.07.2018.
 */

class DialogChoiseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater inflater = null;
    private List<OptionData> optionData;
    private TextView myTextview;
    private Dialog dialog;

    public DialogChoiseAdapter(Context context, List<OptionData> optionData,TextView myTextview,Dialog dialog){
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.optionData = optionData;
        this.myTextview = myTextview;
        this.dialog = dialog;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.adapter_item_dialog_choise, parent, false);
        return new ItemView(itemView);
    }

    private class ItemView extends RecyclerView.ViewHolder{
        View rootView;
        TextView name;
        ImageView check;
        private ItemView(View itemView) {
            super(itemView);
            rootView = itemView;
            name = itemView.findViewById(R.id.name);
            check = itemView.findViewById(R.id.check);
        }
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        String nameRegion = String.valueOf(myTextview.getText());

        if (nameRegion.contains(optionData.get(position).getLabel())){
            ((ItemView) holder).check.setVisibility(View.VISIBLE);

            ((ItemView) holder).name.setTextColor(context.getResources().getColor(R.color.colorAccent));
        }
        ((ItemView) holder).name.setText(optionData.get(position).getLabel());
        ((ItemView) holder).rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myTextview.setText(optionData.get(position).getLabel());

                dialog.dismiss();
            }
        });
    }

    @Override
    public int getItemCount() {
        return optionData.size();
    }
}
