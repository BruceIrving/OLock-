package hotel.cyut.im.o_lock;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class one extends Fragment {

    TextView txt, txt1, txt2, txt3;


    public one() {
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
        final View view = inflater.inflate(R.layout.fragment_one, container, false);



        txt = (TextView) view.findViewById(R.id.textView24);
        txt1 = (TextView) view.findViewById(R.id.pw);
        txt2 = (TextView) view.findViewById(R.id.na);
        txt3 = (TextView) view.findViewById(R.id.tel1);

        String name =this.getActivity().getSharedPreferences("userdata",Context.MODE_PRIVATE)
                .getString("name","");
        String pass =this.getActivity().getSharedPreferences("userdata",Context.MODE_PRIVATE)
                .getString("pass","");
        String email =this.getActivity().getSharedPreferences("userdata",Context.MODE_PRIVATE)
                .getString("email","");
        String tel =this.getActivity().getSharedPreferences("userdata",Context.MODE_PRIVATE)
                .getString("tel","");

        /*Intent intent = Intent.getIntent();
        String name =intent.getStringExtra("name");
        String pw = intent.getStringExtra("pass");
        String email = getActivity().getIntent().getStringExtra("email");
        String tel = getActivity().getIntent().getStringExtra("tel");
*/
        txt.setText(email);
        txt1.setText(pass);
        txt2.setText(name);
        txt3.setText(tel);



        return view;
    }

}
