package ca.bcit.ass2.chu_navarro;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by E on 2017-10-08.
 */

public class CountryAdapter extends ArrayAdapter<Country> {
    Context _context;
    public CountryAdapter(Context context, ArrayList<Country> countries) {
        super(context, 0, countries);
        _context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Activity activity = (Activity) _context;
        // Get the data item for this position
        Country country = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.country_list, parent, false);
        }

        TextView countryName = (TextView) convertView.findViewById(R.id.countryName);
        countryName.setText(country.getCountry());

//        ImageView imgOnePhoto = (ImageView) convertView.findViewById(R.id.flag);
////        //DownloadImageTask dit = new DownloadImageTask(_context, imgOnePhoto);
////        //dit.execute(toon.getPicture());
//        if (country.getFlag() != null) {
//            new ImageDownloaderTask(imgOnePhoto).execute(country.getFlag());
//        }

        return convertView;
    }

}
