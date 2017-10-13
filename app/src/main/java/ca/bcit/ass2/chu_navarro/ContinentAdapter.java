package ca.bcit.ass2.chu_navarro;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by E on 2017-10-12.
 */

public class ContinentAdapter extends ArrayAdapter<String> {
    Context _context;
    public ContinentAdapter(Context context, ArrayList<String> regions) {
        super(context, 0, regions);
        _context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Activity activity = (Activity) _context;
        // Get the data item for this position
        String regionName = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.region_names, parent, false);
        }

        TextView regionEntry = (TextView) convertView.findViewById(R.id.regionName);
        regionEntry.setText(regionName);

        return convertView;
    }

}
