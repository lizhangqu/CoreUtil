package zafu.edu.cn.coreutil.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import zafu.edu.cn.coreutil.ManifestUtil;
import zafu.edu.cn.coreutil.ToastUtil;

public class MainActivity extends AppCompatActivity {
    private static final String TAG="TAG";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, ManifestUtil.getPermissions(this).toString());

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.show(MainActivity.this, "你好！！！！！！！！", Toast.LENGTH_LONG);
            }
        });

    }


}
