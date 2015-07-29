package zafu.edu.cn.coreutil.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.Date;

import zafu.edu.cn.coreutil.DateUtil;

public class MainActivity extends AppCompatActivity {
    private static final String TAG="TAG";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, String.valueOf(DateUtil.getTimeMillis(new Date())));
    }


}
