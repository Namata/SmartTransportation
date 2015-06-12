package com.example.beckie.smarttransportation;

/**
 * Created by BRIAN on 1/14/2015.
 */

        import android.app.Activity;
        import android.content.Context;
        import android.graphics.Color;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.ImageView;
        import android.widget.TextView;

        import java.util.List;

//import com.agm.automaticwifiweblogin.R;

//import com.theopentutorials.android.R;
//import com.theopentutorials.android.beans.RowItem;

public class CustomListViewAdapter extends ArrayAdapter<RowItem> {

    Context context;
    int resourceID;

    public CustomListViewAdapter(Context context, int resourceId,
                                 List<RowItem> items) {
        super(context, resourceId, items);
        this.context = context;
        this.resourceID = resourceId;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        RowItem rowItem = getItem(position);


        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(resourceID, null);
            //convertView.setBackgroundColor(Color.parseColor("#ff000000"));
            holder = new ViewHolder();
            holder.txtTimeOfDeparture = (TextView) convertView.findViewById(R.id.textView_time_of_dep);
            holder.txtRoute = (TextView) convertView.findViewById(R.id.textView_route);
            //holder.imageView = (ImageView) convertView.findViewById(R.id.icon);
            //holder.txtDate = (TextView) convertView.findViewById(R.id.date);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        holder.txtTimeOfDeparture.setText(rowItem.getTime_of_dep());
        holder.txtRoute.setText(rowItem.getRoute());
        //holder.imageView.setImageResource(rowItem.getImageId());
        //holder.txtDate.setText(rowItem.getDate());

        //convertView.setBackgroundColor(Color.WHITE);

        return convertView;
    }

    /*private view holder class*/
    private class ViewHolder {
        //ImageView imageView;
        TextView txtRoute;
        TextView txtTimeOfDeparture;
        //TextView txtDate;
    }
}
