package zafu.edu.cn.coreutil.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.security.PublicKey;

import zafu.edu.cn.coreutil.MD5Util;
import zafu.edu.cn.coreutil.ManifestUtil;
import zafu.edu.cn.coreutil.RSAUtil;
import zafu.edu.cn.coreutil.ToastUtil;

public class MainActivity extends AppCompatActivity {
    private static final String TAG="TAG";
    private static String exponent="010001";
    private static String modulus="008274662caa86d64f983c948ff6f9bb07a319673dbcedfbeff1b8331e5f6848fc12fbf0ccaa2fbaaf2b4b416acd2f5c0d6d51ca4eb82398e25fcc94906c6c5a2c04b4f1c419d1ac72ebf7e814ad0c3db6ae78e8dc132b384c740ecb7413b13e8968444675f5d09b6daf8ef83e72bb7ab8a3b642338480af4f7b01f0048969945d";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, ManifestUtil.getPermissions(this).toString());
        Log.d(TAG, MD5Util.getMD5String("123456"));
        PublicKey key=RSAUtil.getRSAPublicKey(modulus, exponent);
        Log.d(TAG, RSAUtil.encryptStringByJs(key, "lizhangqu"));
        String str=RSAUtil.encryptStringByJs("lizhangqu");
        Log.d(TAG,RSAUtil.encryptStringByJs("lizhangqu"));
        Log.d(TAG,RSAUtil.decryptStringByJs(str));
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.show(MainActivity.this, "你好！！！！！！！！", Toast.LENGTH_LONG);
            }
        });

    }


}
