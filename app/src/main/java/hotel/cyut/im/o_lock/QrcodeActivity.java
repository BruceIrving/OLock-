package hotel.cyut.im.o_lock;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class QrcodeActivity extends Activity implements View.OnClickListener {

    Activity qrcode;
    TextView scan_content, scan_from;
    Button scan_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qrcode);

        scan_content=findViewById(R.id.scan_content);
        scan_from= findViewById(R.id.scan_from);
        qrcode=this;
        scan_content.setAutoLinkMask(Linkify.EMAIL_ADDRESSES|Linkify.WEB_URLS);
        scan_btn=findViewById(R.id.scan_btn);
        scan_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        IntentIntegrator scanIntegrator = new IntentIntegrator(qrcode);
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
            Toast.makeText(getApplicationContext(),"nothing",Toast.LENGTH_SHORT).show();
        }
    }
}
