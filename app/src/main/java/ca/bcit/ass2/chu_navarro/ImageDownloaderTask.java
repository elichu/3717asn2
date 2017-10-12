package ca.bcit.ass2.chu_navarro;

/**
 * Created by E on 2017-10-08.
 */

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;

import com.larvalabs.svgandroid.SVG;
import com.larvalabs.svgandroid.SVGParser;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

class ImageDownloaderTask extends AsyncTask<String, Void, URL> {
    private final WeakReference<WebView> imageViewReference;

    public ImageDownloaderTask(WebView webview) {
        imageViewReference = new WeakReference<WebView>(webview);
    }

    @Override
    protected URL doInBackground(String... params) {
        return getURL(params[0]);
    }

    @Override
    protected void onPostExecute(URL url) {
        if (isCancelled()) {
            url = null;
        }

        if (imageViewReference != null) {
            WebView imageView = imageViewReference.get();
            if (imageView != null) {
                if (url != null) {
                    //imageView.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);
                    imageView.loadUrl(url.toString());

                } else {
                    //Drawable placeholder = imageView.getContext().getResources().getDrawable(R.drawable.placeholder);
                    //imageView.setImageDrawable(placeholder);
                }
            }
        }
    }

    private URL getURL(String url) {
        HttpURLConnection urlConnection = null;
        try {
            URL uri = new URL(url);
            urlConnection = (HttpURLConnection) uri.openConnection();
            int statusCode = urlConnection.getResponseCode();
            if (statusCode !=  HttpURLConnection.HTTP_OK) {
                return null;
            } else {
                return uri;
            }

//            InputStream inputStream = urlConnection.getInputStream();
//            SVG svg = SVGParser.getSVGFromInputStream(inputStream);
//            svg.createPictureDrawable();

//            if (inputStream != null) {
//                //Bitmap bitmap = Bitmap.createBitMapsvg.createPictureDrawable());
////                Bitmap bitmap = null;
////                return bitmap;
//            }
        } catch (Exception e) {
            urlConnection.disconnect();
            Log.w("ImageDownloader", "Error downloading image from " + url);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return null;
    }


}
