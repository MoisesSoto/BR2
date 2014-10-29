package com.moises;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.plus.model.people.Person;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import com.moises.brendpoint.thePersonAip.ThePersonAip;
import com.moises.brendpoint.thePersonAip.model.ThePerson;
import java.io.IOException;


public class GplusSaveInfoAsyncTask extends AsyncTask<Void, Void, String> {
    private static ThePersonAip myApiService = null;
    private Context context;
    private String me;
    private static final String TAG = "====>";
    GplusSaveInfoAsyncTask(Context context,Person currentPerson) {
        this.context = context;
        this.me = currentPerson.getDisplayName();
    }

    @Override
    protected String doInBackground(Void... params) {
        Log.i(TAG, "GplusLoginInfo-11");
        if(myApiService == null) {
                        // Only do this once
            Log.i(TAG, "GplusLoginInfo-22"+ me);
            ThePersonAip.Builder builder = new ThePersonAip.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // Local IP address devappserver is 10.0.2.2 - turn off compression
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            Log.i(TAG, "GplusLoginInfo-33");
            myApiService = builder.build();
        }
        try {
            Log.i(TAG, "GplusLoginInfo-44");
            ThePerson test = new ThePerson();
            test.setName(me);
            return myApiService.ofyInsertPerson(test).execute().getName();
        } catch (IOException e) {
            return "Fail";
        }
    }

    @Override
    protected void onPostExecute(String result) {
        //for (Person q : result) {
        Log.i(TAG, "GplusLoginInfo-55");
            Toast.makeText(context, result , Toast.LENGTH_LONG).show();
        //}
    }
}
