package syaiful.finalpro.englishcourse.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;
import syaiful.finalpro.englishcourse.R;
import syaiful.finalpro.englishcourse.config.Config;
import syaiful.finalpro.englishcourse.custom.CustomItemClickListener;

/**
 * Created by syaiful9508 on 28/07/17.
 */

public class AdapterFormula extends RecyclerView.Adapter<AdapterFormula.ViewHolder> {

    Context context;
    ArrayList<HashMap<String, String>> list_data;

    CustomItemClickListener listener;
    private Config config = new Config();


    public AdapterFormula(Context context, ArrayList<HashMap<String , String >> list_data) {
        this.context = context;
        this.list_data = list_data;

    }

    @Override
    public AdapterFormula.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_formula, parent, false);
        //AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        //07-07-2017
        final AdapterFormula.ViewHolder mViewHolder = new AdapterFormula.ViewHolder(view);

        //07-07-2017
        return new AdapterFormula.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterFormula.ViewHolder holder, int position) {
        holder.tvtitle.setText(list_data.get(position).get(config.TAG_TITLE_FRM));
        holder.sub1.setText(list_data.get(position).get(config.TAG_SUB1));
        holder.sub2.setText(list_data.get(position).get(config.TAG_SUB2));
        holder.sub3.setText(list_data.get(position).get(config.TAG_SUB3));
        if (position % 2 ==1)
            holder.linearLayout.setBackgroundResource(R.color.red_700);
        else
            holder.linearLayout.setBackgroundResource(R.color.green_900);





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
        TextView tvtitle,sub1,sub2,sub3;
        CardView linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            tvtitle = (TextView) itemView.findViewById(R.id.tvtitleformula);
            sub1    = (TextView) itemView.findViewById(R.id.tvsub1);
            sub2    = (TextView) itemView.findViewById(R.id.tvsub2);
            sub3    = (TextView) itemView.findViewById(R.id.tvsub3);
            linearLayout = (CardView) itemView.findViewById(R.id.linearcard);

            //custom font styles
            Typeface titlefont = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Light.ttf");
            tvtitle.setTypeface(titlefont);
            Typeface contentfont = Typeface.createFromAsset(context.getAssets(), "fonts/SFCartoonistHand.ttf");
            sub1.setTypeface(contentfont);
            sub2.setTypeface(contentfont);
            sub3.setTypeface(contentfont);
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
