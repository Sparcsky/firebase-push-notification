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

public class NewsFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_news, container, false);

        TextView tvNewsTitle = view.findViewById(R.id.textview_news_header);
        TextView tvNewsBody = view.findViewById(R.id.textview_news_body);

        Bundle extras = getActivity().getIntent().getExtras();
        if (extras != null) {
            tvNewsTitle.setText(extras.getString(Constant.NOTIF_TITLE));
            tvNewsBody.setText(extras.getString(Constant.NOTIF_BODY));
        }

        return view;
    }

    public static NewsFragment newInstance(String title, String body) {
        NewsFragment fragment = new NewsFragment();
        Bundle args = new Bundle();
        args.putString(Constant.NOTIF_TITLE, title);
        args.putString(Constant.NOTIF_BODY, body);
        fragment.setArguments(args);
        return fragment;
    }


}
