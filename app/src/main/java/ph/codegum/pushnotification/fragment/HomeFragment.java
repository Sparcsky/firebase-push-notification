package ph.codegum.pushnotification.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import ph.codegum.pushnotification.R;

import static android.content.Context.MODE_PRIVATE;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private Switch swAnnouncement;
    private Switch swNews;

    private static final String SUBSCRIBE_PREF = "subscribe";

    private static final String ANNOUNCEMENT_TOPIC = "announcement";
    private static final String NEWS_TOPIC = "news";

    private SharedPreferences pref;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        pref = view.getContext().getSharedPreferences(SUBSCRIBE_PREF, MODE_PRIVATE);

        swAnnouncement = view.findViewById(R.id.toggle_button_announcement);
        swNews = view.findViewById(R.id.toggle_button_news);

        swAnnouncement.setOnClickListener(this);
        swNews.setOnClickListener(this);

        boolean isSubscribeToNews = pref.getBoolean(NEWS_TOPIC, true);
        boolean isSubscribeToAnnouncement = pref.getBoolean(ANNOUNCEMENT_TOPIC, true);

        swAnnouncement.setChecked(isSubscribeToAnnouncement);
        swNews.setChecked(isSubscribeToNews);
        return view;
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (pref.getBoolean("firstrun", true)) {

            pref.edit().putBoolean("firstrun", false).apply();
        }
    }

    @Override
    public void onClick(View v) {
        SharedPreferences.Editor editor = pref.edit();

        if (v.getId() == R.id.toggle_button_announcement) {
            if (swAnnouncement.isChecked()) {
                subscribe(ANNOUNCEMENT_TOPIC, String.format(getString(R.string.msg_subscribe), ANNOUNCEMENT_TOPIC));
            } else {
                unSubscribe(ANNOUNCEMENT_TOPIC, String.format(getString(R.string.msg_unsubscribe), ANNOUNCEMENT_TOPIC));
            }
            editor.putBoolean(ANNOUNCEMENT_TOPIC, swAnnouncement.isChecked());

        } else if (v.getId() == R.id.toggle_button_news) {
            if (swNews.isChecked()) {
                subscribe(NEWS_TOPIC, String.format(getString(R.string.msg_subscribe), NEWS_TOPIC));
            } else {
                unSubscribe(NEWS_TOPIC, String.format(getString(R.string.msg_unsubscribe), NEWS_TOPIC));
            }
            editor.putBoolean(NEWS_TOPIC, swNews.isChecked());
        }

        editor.apply();
    }


    public void subscribe(String topic, final String msg) {
        FirebaseMessaging.getInstance().subscribeToTopic(topic).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void unSubscribe(String topic, final String msg) {
        FirebaseMessaging.getInstance().unsubscribeFromTopic(topic).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
