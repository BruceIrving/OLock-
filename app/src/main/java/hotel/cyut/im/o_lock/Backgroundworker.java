package hotel.cyut.im.o_lock;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

import static android.content.Context.MODE_PRIVATE;
import static android.support.constraint.Constraints.TAG;

public class Backgroundworker extends AsyncTask<String,Void,String> {
    private final Context context;
    private static final String ACTIVITY_TAG ="Logwrite";

    public Backgroundworker (Context ctx){
        this.context = ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        String type =params[0];
        String loginUrl="http://163.17.9.125/olock_app/login.php";
        String signupUrl="http://163.17.9.125/olock_app/signup.php";
        String questionUrl="http://163.17.9.125/olock_app/question.php";
        if(type.equals("login")){
            Log.d(Backgroundworker.ACTIVITY_TAG,"login if run");
            try {
                Log.d("Tagpar1",params[1]);
                String mail = params[1];
                String pwd = params[2];
                URL url = new URL(loginUrl);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("m_email","UTF-8")+"="+URLEncoder.encode(URLDecoder.decode(mail),"UTF-8")+"&"
                        +URLEncoder.encode("m_pw","UTF-8")+"="+URLEncoder.encode(pwd,"UTF-8");
                Log.d("POST_DATA", "doInBackground: "+post_data);

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                String result="";
                String line=null;
                while((line = bufferedReader.readLine())!= null) {
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

        }
        else if(type.equals("signup")){
            Log.d(Backgroundworker.ACTIVITY_TAG,"signup if run");
            try {
                String username = params[1];
                String email = params[2];
                String password = params[3];
                String tel = params[4];
                URL url = new URL(signupUrl);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("m_name","UTF-8")+"="+URLEncoder.encode(username,"UTF-8")+"&"
                        +URLEncoder.encode("m_email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"
                        +URLEncoder.encode("m_pw","UTF-8")+"="+URLEncoder.encode(password,"UTF-8")+"&"
                        +URLEncoder.encode("m_tel","UTF-8")+"="+URLEncoder.encode(tel,"UTF-8");
                Log.d("POST_DATA", "doInBackground: "+post_data);

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                String result="";
                String line=null;
                while((line = bufferedReader.readLine())!= null) {
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

        }
        else if (type.equals("question")) {
            Log.d(Backgroundworker.ACTIVITY_TAG, "question if run");
            try {
                String username = params[1];
                String email = params[2];
                String context = params[3];
                URL url = new URL(questionUrl);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("c_name", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&" +
                        URLEncoder.encode("c_email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") + "&" +
                        URLEncoder.encode("c_content", "UTF-8") + "=" + URLEncoder.encode(context, "UTF-8");
                Log.d("POST_DATA", "doInBackground:" + post_data);

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                String result = "";
                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
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

        }

        return null;
    }


    @Override
    protected void onPreExecute() {

    }

    @Override
    protected void onPostExecute(String result) {
        Log.d(TAG, "onPostExecute: "+result);
        if(result.contains("登入成功"))
        {
            String s =result;
            result=s.replaceAll("登入成功","");;

            String s1 = "";
            String s2 = "";
            String s3 = "";
            String s4 = "";

            try {
                JSONObject object = new JSONObject(result);
                Log.d(TAG,"ToJsonTAG>>>>:"+object);
                String name = object.getString("m_name");
                Log.d("TagForCheckingName","Name->>>>"+name);
                String pw = object.getString("m_pw");
                String email = object.getString("m_email");
                String tel = object.getString("m_tel");
                String no = object.getString("m_no");
                
               s=name;
               s1=pw;
               s2=email;
               s3=tel;
               s4=no;



            } catch (JSONException e) {
                e.printStackTrace();
            }

            Toast.makeText(context, "登入中…", Toast.LENGTH_SHORT).show();
            Intent toLoadView=new Intent(context,Hotle.class);
            Bundle bundle = new Bundle();
            bundle.putString("name", s);
            bundle.putString("pass", s1);
            bundle.putString("email", s2);
            bundle.putString("tel", s3);
            bundle.putString("no", s4);
            toLoadView.putExtras(bundle);
            context.startActivity(toLoadView);
            ((Activity)context).finish();
        }else if (result.contains("登入失敗")){
            Toast.makeText(context, "登入失敗！請檢查帳號密碼是否有誤", Toast.LENGTH_SHORT).show();
        }else if (result.contains("註冊成功")){
            Toast.makeText(context, "註冊成功！", Toast.LENGTH_SHORT).show();
            Intent toLoadView=new Intent(context,MainActivity.class);
            context.startActivity(toLoadView);
            ((Activity)context).finish();
        }else if (result.contains("註冊失敗")){
            Toast.makeText(context, "註冊失敗！", Toast.LENGTH_SHORT).show();
        }else if (result.contains("查無帳號")){
            Toast.makeText(context, "登入失敗！查無帳號", Toast.LENGTH_SHORT).show();
        }else if (result.contains("送出成功")){
            Toast.makeText(context, "成功送出!", Toast.LENGTH_SHORT).show();
            Intent toLoadView=new Intent(context,Question.class);
            context.startActivity(toLoadView);
            ((Activity)context).finish();
        }else if (result.contains("送出失敗")){
            Toast.makeText(context, "送出失敗", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }


}

