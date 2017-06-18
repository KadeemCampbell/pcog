package org.jamaica.pcog.mobile.social;


import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import org.jamaica.pcog.mobile.MainActivity;
import org.jamaica.pcog.mobile.R;

import im.delight.android.webview.AdvancedWebView;

/**
 * A simple {@link Fragment} subclass.
 */
public class TwitterFragment extends Fragment implements AdvancedWebView.Listener{

    private AdvancedWebView mWebView;
    ImageButton img;

    public TwitterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_twitter, container, false);

        mWebView = (AdvancedWebView) v.findViewById(R.id.webview);
        //mWebView.setListener(this, this);
        mWebView.loadUrl("https://twitter.com/portmorecog");

        return v;
    }
    @SuppressLint("NewApi")
    @Override
    public void onResume() {
        super.onResume();
        mWebView.onResume();
        // ...
    }

    @SuppressLint("NewApi")
    @Override
    public void onPause() {
        mWebView.onPause();
        // ...
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mWebView.onDestroy();
        // ...
        super.onDestroy();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        mWebView.onActivityResult(requestCode, resultCode, intent);
        // ...
    }


    public void onBackPressed() {
        if (!mWebView.onBackPressed()) { return; }
        // ...
        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);

    }

    @Override
    public void onPageStarted(String url, Bitmap favicon) { }

    @Override
    public void onPageFinished(String url) { }

    @Override
    public void onPageError(int errorCode, String description, String failingUrl) {
        if (errorCode == -2) {

            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setCancelable(false);
            builder.setTitle(Html.fromHtml("<font color='#0d4d8b'><b>PCOG</b></font>"));
            builder.setMessage(Html.fromHtml("<font color='#120049'>The Facebook Page will not open, your data services are not working. Please check your data services.</font>"));
            builder.setPositiveButton(Html.fromHtml("<font color='#7F02AE'><center><b>OK</b></center></font>"), new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //SplashScreenActivity.class is your Launcher Activity
                    // In Case of Fragment instead of Activity Replace getApplicationContext()  with getActivity()

                    Intent intent = new Intent(getContext(), MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("EXIT", true);
                    startActivity(intent);

                }
            });
            AlertDialog alert = builder.create();
            alert.show();
        }
    }
    @Override
    public void onDownloadRequested(String url, String suggestedFilename, String mimeType, long contentLength, String contentDisposition, String userAgent) { }

    @Override
    public void onExternalPageRequest(String url) { }
}
