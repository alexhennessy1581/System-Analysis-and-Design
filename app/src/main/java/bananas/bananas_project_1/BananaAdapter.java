package bananas.bananas_project_1;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class BananaAdapter extends ArrayAdapter<Banana> {

    private Context mContext;
    private List<Banana> mValues;

    public BananaAdapter(Context context,
                         int textViewResourceId, List<Banana> objects) {
        super(context, textViewResourceId, objects);
        this.mContext = context;
        this.mValues = objects;
    }

    @Override
    public int getCount(){
        return mValues.size();
    }

    @Override
    public Banana getItem(int position){
        return mValues.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //This is for the first item before dropdown or default state.
        TextView label = new TextView(mContext);
        label.setText("Select a Banana");
        return label;
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        //This is when user click the spinner and list of item display
        // beneath it
        TextView label = new TextView(mContext);
        Banana p = mValues.get(position);
        label.setText(" " + p.getDate() + " " + p.getTime() + " -- " + p.getColor());
        return label;
    }
}