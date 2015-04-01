package base;

import android.app.Application;

import com.parse.Parse;
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



//end of oncreate
    }




 //end of main clasa
}
