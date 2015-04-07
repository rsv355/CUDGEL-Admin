package base;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.PushService;
import com.parse.SaveCallback;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by krishn on 2/1/2015.
 */
public class MyApplication extends Application{
    static final String TAG = "MyApp";
    String DATABASE_VERSION="NULL";



    @Override
    public void onCreate() {
        super.onCreate();
        // app key --  client key
        Parse.initialize(this, "ha9xuGetKeAc9qNh4ST2BP0QBNRkFtF8jcT305xX", "ivbh23bJ1OKzBxcnbmbEH91dFBj5tSBCBG74MAaG");

        // intializing the Shared prefernces
       // Prefs.initPrefs(this);

        // Specify a Activity to handle all pushes by default.
        PushService.setDefaultPushCallback(this, MyDrawerActivity.class);

        // Save the current installation.
        ParseInstallation.getCurrentInstallation().saveInBackground();



        ParsePush.subscribeInBackground("", new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Log.e("com.parse.push", "successfully subscribed to the broadcast channel.");
                } else {
                    Log.e("com.parse.push", "failed to subscribe for push", e);
                }
            }
        });

//end of oncreate
    }




 //end of main clasa
}
