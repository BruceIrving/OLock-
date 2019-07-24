package hotel.cyut.im.o_lock;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


/**
 * A simple {@link Fragment} subclass.
 */
public class three extends Fragment implements View.OnClickListener {

    Fragment three;
    TextView scan_content, scan_from;
    Button scan_btn;

    public three() {
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
        final View view = inflater.inflate(R.layout.fragment_three, container, false);

        scan_content=view.findViewById(R.id.scan_content);
        scan_from= view.findViewById(R.id.scan_from);
        three=this;
        scan_content.setAutoLinkMask(Linkify.EMAIL_ADDRESSES|Linkify.WEB_URLS);
        scan_btn=view.findViewById(R.id.scan_btn);
        scan_btn.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        FragmentIntentIntegrator scanIntegrator = new FragmentIntentIntegrator(this); //IntentIntegrator(three) -> IntentIntegrator(getActivity())
        scanIntegrator.initiateScan();
    }


    public void onActivityResult(int requestCode, int resultCode, Intent intent){
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult!=null){
            String scanContent=scanningResult.getContents();
            scan_content.setText(scanContent);
            scan_from.setText("如出現重啟訊息請確認是否開始相機權限\n" +
                    "前往設定→應用程式→找到此app名稱點選它→點選權限");

        }else {
            Toast.makeText(getContext(),"nothing",Toast.LENGTH_SHORT).show();   //getApplication?Context() -> getColntext()
        }
    }

     final class FragmentIntentIntegrator extends IntentIntegrator {

        private final Fragment fragment;

        public FragmentIntentIntegrator(Fragment fragment) {
            super(fragment.getActivity());
            this.fragment = fragment;
        }

        @Override
        protected void startActivityForResult(Intent intent, int code) {
            fragment.startActivityForResult(intent, code);
        }
    }
}
