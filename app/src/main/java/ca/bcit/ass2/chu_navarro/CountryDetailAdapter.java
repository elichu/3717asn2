package ca.bcit.ass2.chu_navarro;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by E on 2017-10-09.
 */

public class CountryDetailAdapter extends ArrayAdapter<Country> {

    Context _context;
    public CountryDetailAdapter(Context context, ArrayList<Country> countries) {
        super(context, 0, countries);
        _context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Activity activity = (Activity) _context;
        // Get the data item for this position
        Country country = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.country_detail, parent, false);
        }
        TextView countryName = (TextView) convertView.findViewById(R.id.country);
        TextView capital = (TextView) convertView.findViewById(R.id.capital);
        TextView region = (TextView) convertView.findViewById(R.id.region);
        TextView population = (TextView) convertView.findViewById(R.id.population);
        TextView area = (TextView) convertView.findViewById(R.id.area);
        TextView borders = (TextView) convertView.findViewById(R.id.borders);

        countryName.setText(country.getCountry());
        capital.setText(country.getCapital());
        region.setText(country.getRegion());
        population.setText(country.getPopulation());
        area.setText(country.getArea());
        borders.setText(country.getBorders().toString());

        WebView imgOnePhoto = (WebView) convertView.findViewById(R.id.flag);
        if (country.getFlag() != null) {
            new ImageDownloaderTask(imgOnePhoto).execute(country.getFlag());
        }
        return convertView;
    }


}
