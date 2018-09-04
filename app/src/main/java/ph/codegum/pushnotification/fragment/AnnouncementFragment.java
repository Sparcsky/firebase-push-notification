package ph.codegum.pushnotification.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ph.codegum.pushnotification.Constant;
import ph.codegum.pushnotification.R;

public class AnnouncementFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_announcement, container, false);

        TextView tvAnnouncementTitle = view.findViewById(R.id.textview_announcement_header);
        TextView tvAnnouncementBody = view.findViewById(R.id.textview_announcement_body);

        Bundle extras = getActivity().getIntent().getExtras();
        if (extras != null) {
            tvAnnouncementTitle.setText(extras.getString(Constant.NOTIF_TITLE));
            tvAnnouncementBody.setText(extras.getString(Constant.NOTIF_BODY));
        }

        return view;
    }

    public static AnnouncementFragment newInstance(String title, String body) {
        AnnouncementFragment fragment = new AnnouncementFragment();
        Bundle args = new Bundle();
        args.putString(Constant.NOTIF_TITLE, title);
        args.putString(Constant.NOTIF_BODY, body);
        fragment.setArguments(args);
        return fragment;
    }
}
