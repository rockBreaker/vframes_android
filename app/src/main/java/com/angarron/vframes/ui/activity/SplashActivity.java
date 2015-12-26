package com.angarron.vframes.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.angarron.vframes.R;
import com.angarron.vframes.application.VFramesApplication;
import com.angarron.vframes.data.IDataSource;

import data.model.IDataModel;

//This activity is meant to be shown while loading data either
//over the network or from shared preferences.
public class SplashActivity extends Activity {

    private VFramesApplication application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        loadData();
    }

    private void loadData() {
        application = (VFramesApplication) getApplication();
        IDataSource dataSource = application.getDataSource();
        dataSource.fetchData(new DataSourceListener());
    }

    private class DataSourceListener implements IDataSource.Listener {

        @Override
        public void onDataReceived(IDataModel data) {
            Toast.makeText(SplashActivity.this, "Fetched data successfully", Toast.LENGTH_LONG).show();

            //Store the data in the application model and launch the HomeScreenActivity.
            application.setDataModel(data);
            launchHomeScreenActivity();
        }

        @Override
        public void onDataFetchFailed(IDataSource.FetchFailureReason failureReason) {
            Toast.makeText(SplashActivity.this, "failed to fetch data", Toast.LENGTH_LONG).show();
            Log.e(VFramesApplication.APP_LOGGING_TAG, "failed to fetch data: " + failureReason);
            //TODO: log this error to crashlytics
        }
    }

    private void launchHomeScreenActivity() {
        Intent launchHomeScreenIntent = new Intent(this, CharacterSelectActivity.class);
        launchHomeScreenIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(launchHomeScreenIntent);
        finish();
    }
}