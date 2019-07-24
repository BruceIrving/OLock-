package hotel.cyut.im.o_lock;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class two extends Fragment {

    Button but;
    TextView tx, tx1, tx2,tx3;

    public two() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_two, container, false);

        tx = view.findViewById(R.id.textView39);
        tx1 = view.findViewById(R.id.textView42);
        tx2 = view.findViewById(R.id.textView22);
        tx3 = view.findViewById(R.id.textView35);

        String start = getActivity().getIntent().getStringExtra("start");
        String end = getActivity().getIntent().getStringExtra("end");
        String room_num = getActivity().getIntent().getStringExtra("room_num");
        String room_pass = getActivity().getIntent().getStringExtra("room_pass");


        tx.setText(start);
        tx1.setText(end);
        tx2.setText(room_num);
        tx3.setText(room_pass);



        return view;
    }
    public void onClick(View v) {
        TabLayout tabLayout = (TabLayout)getActivity().findViewById(R.id.tabs);
        tabLayout.getTabAt(2).select();

    }
}
