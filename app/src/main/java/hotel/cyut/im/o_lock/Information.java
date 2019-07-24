package hotel.cyut.im.o_lock;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

public class Information extends AsyncTask<String,Void,String> {
    private final Context context;
    private static final String ACTIVITY_TAG = "Log";
    public Information(Context ctx) { this.context = ctx; }

    @Override
    protected String doInBackground(String... params) {
        String roomUrl = "http://163.17.9.125/olock_app/booking_confirm.php";
        Log.d(Information.ACTIVITY_TAG,"Book a room");
        try {
            String room_type = params[0];
            String start = params[1];
            String end = params[2];
            String room_num = params[3];
            String price = params[4];
            String no = params[5];
            URL url = new URL(roomUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String post_data = URLEncoder.encode("type", "UTF-8")+"="+URLEncoder.encode(room_type, "UTF-8")
                    +"&"+URLEncoder.encode("start", "UTF-8")+"="+URLEncoder.encode(start, "UTF-8")
                    +"&"+URLEncoder.encode("end", "UTF-8")+"="+URLEncoder.encode(end, "UTF-8")
                    +"&"+URLEncoder.encode("r_room", "UTF-8")+"="+URLEncoder.encode(room_num, "UTF-8")
                    +"&"+URLEncoder.encode("price", "UTF-8")+"="+URLEncoder.encode(price, "UTF-8")
                    +"&"+URLEncoder.encode("m_no", "UTF-8")+"="+URLEncoder.encode(no, "UTF-8");
            Log.d("POST_DATA","doInBackground"+ post_data);

            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
            String result="";
            String line=null;
            while ((line= bufferedReader.readLine())!= null){
                result += line;
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            return result;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        Intent intent = new Intent(context,Payment.class);
        context.startActivity(intent);
        ((Activity)context).finish();
    }

    @Override
    protected void onProgressUpdate(Void... values) { super.onProgressUpdate(values); }
}
