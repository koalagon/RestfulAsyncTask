package com.westside.app;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.westside.restful.*;

public class MainActivity extends AppCompatActivity implements AsyncEventHandler {

    private static final int HTTP_GET_MESSAGE = 1;
    private static final int HTTP_POST_MESSAGE = 2;
    private static final int HTTP_PUT_MESSAGE = 3;
    private static final int HTTP_DELETE_MESSAGE = 4;

    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((Button)findViewById(R.id.request_get)).setOnClickListener(buttonClickHandler);
        ((Button)findViewById(R.id.request_post)).setOnClickListener(buttonClickHandler);
        ((Button)findViewById(R.id.request_put)).setOnClickListener(buttonClickHandler);
        ((Button)findViewById(R.id.request_delete)).setOnClickListener(buttonClickHandler);
    }

    View.OnClickListener buttonClickHandler = new View.OnClickListener() {
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.request_get:
                    HttpGetAsyncTask task1 = new HttpGetAsyncTask("https://raw.githubusercontent.com/angular/angular-phonecat/master/app/phones/phones.json", MainActivity.this, HTTP_GET_MESSAGE, null, "q1=test");
                    task1.execute();
                    break;
                case R.id.request_post:
                    HttpPostAsyncTask task2 = new HttpPostAsyncTask("your restful server url", MainActivity.this, HTTP_GET_MESSAGE, null, "your json data");
                    task2.execute();
                    break;
                case R.id.request_put:
                    HttpPutAsyncTask task3 = new HttpPutAsyncTask("your restful server url", MainActivity.this, HTTP_GET_MESSAGE, null, "your json data");
                    task3.execute();
                    break;
                case R.id.request_delete:
                    HttpDeleteAsyncTask task4 = new HttpDeleteAsyncTask("your restful server url", MainActivity.this, HTTP_GET_MESSAGE, null, "your json data");
                    task4.execute();
                    break;
            }
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onAsyncTaskStarted(int message) {
        dialog = new ProgressDialog(this);
        dialog.setMessage("잠시만 기다려 주세요...");

        if (!dialog.isShowing())
            dialog.show();
    }

    @Override
    public void onAsyncTaskFinished(int message, String result) {
        switch (message) {
            case HTTP_GET_MESSAGE:
                ((EditText) findViewById(R.id.response_text)).setText(result);
                break;
            case HTTP_POST_MESSAGE:
                ((EditText) findViewById(R.id.response_text)).setText(result);
                break;
            case HTTP_PUT_MESSAGE:
                ((EditText) findViewById(R.id.response_text)).setText(result);
                break;
            case HTTP_DELETE_MESSAGE:
                ((EditText) findViewById(R.id.response_text)).setText(result);
                break;
        }

        if (dialog.isShowing())
            dialog.hide();
    }
}
