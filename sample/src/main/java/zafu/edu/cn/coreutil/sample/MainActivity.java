package zafu.edu.cn.coreutil.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import zafu.edu.cn.coreutil.ManifestUtil;

public class MainActivity extends AppCompatActivity {
    private static final String TAG="TAG";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, ManifestUtil.getApplicationMetaData(this,"key"));
    }


}
