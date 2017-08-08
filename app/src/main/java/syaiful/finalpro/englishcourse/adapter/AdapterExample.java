package syaiful.finalpro.englishcourse.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import syaiful.finalpro.englishcourse.R;
import syaiful.finalpro.englishcourse.config.Config;
import syaiful.finalpro.englishcourse.custom.CustomItemClickListener;

/**
 * Created by syaiful9508 on 28/07/17.
 */

public class AdapterExample extends RecyclerView.Adapter<AdapterExample.ViewHolder> {

    Context context;
    ArrayList<HashMap<String, String>> list_data;

    CustomItemClickListener listener;
    private Config config = new Config();
    public AdapterExample(Context context, ArrayList<HashMap<String , String >> list_data) {
        this.context = context;
        this.list_data = list_data;

    }


    @Override
    public AdapterExample.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_example, parent, false);
        //AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        //07-07-2017
        final AdapterExample.ViewHolder mViewHolder = new AdapterExample.ViewHolder(view);

        //07-07-2017
        return new AdapterExample.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterExample.ViewHolder holder, int position) {

        holder.tvtitleexample.setText(list_data.get(position).get(config.TAG_TITLE_EXAM));
        holder.subexample1.setText(list_data.get(position).get(config.TAG_SUBEXAMPLE1));
        holder.subexample2.setText(list_data.get(position).get(config.TAG_SUBEXAMPLE2));





    }

    @Override
    public int getItemCount() {
        return list_data.size();
    }

    //custom onitemclick
    public void setClickListener(CustomItemClickListener itemClickListener) {
        this.listener = itemClickListener;
    }




    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvtitleexample,subexample1,subexample2;

        public ViewHolder(View itemView) {
            super(itemView);
            tvtitleexample = (TextView) itemView.findViewById(R.id.tvtitleexam);
            subexample1    = (TextView) itemView.findViewById(R.id.tvsubexample1);
            subexample2    = (TextView) itemView.findViewById(R.id.tvsubexample2);

            Typeface titlefont = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Light.ttf");
            tvtitleexample.setTypeface(titlefont);
            Typeface contentfont = Typeface.createFromAsset(context.getAssets(), "fonts/SFCartoonistHand.ttf");
            subexample1.setTypeface(contentfont);
            subexample2.setTypeface(contentfont);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (listener != null)
                listener.onItemClick(view, getAdapterPosition());

        }


        //Logic for OnItemClick
        //Date jum, jul 7


        //END OF CLASS VIEWHOLDER
    }
}
