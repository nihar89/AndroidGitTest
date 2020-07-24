package com.mahalati.mahalatibusiness;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.mahalati.mahalatibusiness.utils.CommonUtils;

public class ActivitySplash extends AppCompatActivity {

    String token = "";
    TextView mTextViewVersion;

    TextView textViewToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_splash_screen);

        mTextViewVersion = (TextView) findViewById(R.id.version);
        textViewToken = (TextView) findViewById(R.id.tokenversion);

        //token
        // 2nd token
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnSuccessListener(new OnSuccessListener<InstanceIdResult>() {
                    @Override
                    public void onSuccess(InstanceIdResult instanceIdResult) {
                        token = instanceIdResult.getToken();
                        System.out.println("business splash token 0.." + token);
                        textViewToken.setText("Token : " + token);
                        sendRegistrationToServer(token);
                    }
                });

        try {
            PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            String version = pInfo.versionName;
            mTextViewVersion.setText(getString(R.string.version) + " " + version);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void sendRegistrationToServer(String token) {
        CommonUtils.setStringSharedPref(this, CommonUtils.SF_DEVICE_TOKEN, token);
    }
}