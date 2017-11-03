package com.example.anishkelkar.androidbrowsersoftlab;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.provider.Browser;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebBackForwardList;
import android.webkit.WebChromeClient;
import android.webkit.WebHistoryItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.view.KeyEvent;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    WebView brow;
    EditText urledit;
    ImageButton go;
    public static final String MyPREFERENCES = "MyPrefs" ;

    Button forward,back,clear,reload, history;
    ProgressBar progressBar;
    SharedPreferences sharedPreferences ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        history = (Button) findViewById(R.id.btn_hist);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        brow= (WebView)findViewById(R.id.wv_brow);
        urledit = (EditText)findViewById(R.id.et_url);
        go = (ImageButton)findViewById(R.id.btn_go);
        forward = (Button)findViewById(R.id.btn_fwd);
        back = (Button)findViewById(R.id.btn_bck);
        //clear = (Button)findViewById(R.id.btn_clear);
        reload = (Button)findViewById(R.id.btn_reload);

        // When we click on something in our browser this enables to open the link in the same browser instead of opening anohter browser

        brow.setWebViewClient(new ourViewClient());
        brow.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                progressBar.setProgress(newProgress);

                if(newProgress == 100){
                    progressBar.setVisibility(View.GONE);
                }else{
                    progressBar.setVisibility(View.VISIBLE);

                }
            }
        });

        WebSettings websettings = brow.getSettings();
        websettings.setJavaScriptEnabled(true);

        brow.loadUrl("http://www.google.com");

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String editextvalue = urledit.getText().toString();
                if(editextvalue.toLowerCase().contains(".com".toLowerCase())|| editextvalue.toLowerCase().contains(".in".toLowerCase()) || editextvalue.toLowerCase().contains(".org".toLowerCase()))
                {
                    if(!editextvalue.startsWith("http://"))
                        editextvalue = "http://" + editextvalue;

                    String url = editextvalue;
                    brow.loadUrl(url);
                }
                else
                {
                    String url="https://www.google.com/search?q="+editextvalue;
                    brow.loadUrl(url);
                }

                //Hide keyboard after using EditText
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(urledit.getWindowToken(),0);
            }
        });


//        public static final String HISTORY_LS="";
        history.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View view) {

                sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                ArrayList<String>al = new ArrayList();
                WebBackForwardList currentList = brow.copyBackForwardList();
                int currentSize = currentList.getSize();
                String url;
                for(int i = 0 ; i < currentSize ; i++)
                {
                    WebHistoryItem item = currentList.getItemAtIndex(i);
                    url = item.getUrl();
                    String ii = String.valueOf(i);
                    editor.putString(ii, url);
                    editor.commit();
//                    LOG.d(TAG,al.toString());
                }
//                System.out.println(al.toString());
//
                Intent intent = new Intent(MainActivity.this, History.class);
//
//                intent.putExtra("historyList",al.toString());

                startActivity(intent);

            }

        });

        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(brow.canGoForward())
                    brow.goForward();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(brow.canGoBack())
                    brow.goBack();
            }
        });

        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                brow.reload();
            }
        });

        /*clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                brow.clearHistory();
                //sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
               *//* SharedPreferences.Editor editor = sharedPreferences.edit();

                for(int i=0;i<5;i++){
                    editor.remove(String.valueOf(i));
                    editor.clear();
                    editor.commit();
                }*//*
            }
        });*/

        urledit.setOnKeyListener(new View.OnKeyListener()
        {
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    switch (keyCode)
                    {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:

                            String editextvalue = urledit.getText().toString();
                            if(editextvalue.toLowerCase().contains(".com".toLowerCase())|| editextvalue.toLowerCase().contains(".in".toLowerCase()) || editextvalue.toLowerCase().contains(".org".toLowerCase()))
                            {
                                if(!editextvalue.startsWith("http://"))
                                    editextvalue = "http://" + editextvalue;

                                String url = editextvalue;
                                brow.loadUrl(url);
                            }
                            else
                            {
                                String url="https://www.google.com/search?q="+editextvalue;
                                brow.loadUrl(url);
                            }

                            //Hide keyboard after using EditText
                            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(urledit.getWindowToken(),0);

                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });



    }
}

