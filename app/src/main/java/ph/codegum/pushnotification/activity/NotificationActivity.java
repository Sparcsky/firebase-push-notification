package ph.codegum.pushnotification.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import ph.codegum.pushnotification.Constant;
import ph.codegum.pushnotification.R;
import ph.codegum.pushnotification.fragment.AnnouncementFragment;
import ph.codegum.pushnotification.fragment.HomeFragment;
import ph.codegum.pushnotification.fragment.NewsFragment;

public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(this, new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                String newToken = instanceIdResult.getToken();
                // registerTokenToServer(token);
            }
        });

        String notifTitle = getIntent().getStringExtra(getString(R.string.notification_title));
        String notifBody = getIntent().getStringExtra(getString(R.string.notification_body));

        Fragment fragment = HomeFragment.newInstance();

        if (notifTitle != null) {
            if (notifTitle.equalsIgnoreCase(Constant.ANNOUNCEMENT_TOPIC)) {
                fragment = AnnouncementFragment.newInstance(notifTitle, notifBody);
            } else if (notifTitle.equalsIgnoreCase(Constant.NEWS_TOPIC)) {
                fragment = NewsFragment.newInstance(notifTitle, notifBody);
            }
        }

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.fragments_container, fragment);
        ft.commit();
    }
}
