package com.example.bdyst.readdatafromiternet;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {
ImageView imghinh;
TextView txtnoidung;
Button btn;
Bitmap bmp;
String link="https://www.facebook.com/tung.ngocdao";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(Build.VERSION.SDK_INT>9){
            StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

        }
        anhxa();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtnoidung.setText(readdata(link));
                Toast.makeText(MainActivity.this, ""+readdata(link), Toast.LENGTH_SHORT).show();
                new getimagefrominternet().execute("https://scontent.fhph1-2.fna.fbcdn.net/v/t1.0-9/49384494_789387848065930_2135154549239840768_n.jpg?_nc_cat=105&_nc_ht=scontent.fhph1-2.fna&oh=a39f6919a019d80be592d1dcf28a8394&oe=5CC0D968");
            }
        });
    }
    private class getimagefrominternet extends AsyncTask<String,Integer,Bitmap>{






        @Override
        protected Bitmap doInBackground(String... strings) { // ở đây trả ra 1 array
            URL u= null;
            try {
                u = new URL(strings[0]); // nên ở đây phải lấy phần tử thứ 0
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            try {
                bmp=BitmapFactory.decodeStream(u.openConnection().getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }


            return bmp;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            imghinh.setImageBitmap(bitmap);
            super.onPostExecute(bitmap);
        }
    }

    private void anhxa() {
        imghinh=findViewById(R.id.imageView);
        txtnoidung=findViewById(R.id.textView);
        btn=findViewById(R.id.button);


    }
    private String readdata(String u){
        StringBuilder stringBuilder=new StringBuilder();
        try {
            URL url=new URL(u);
            URLConnection connection=url.openConnection();
            BufferedReader buf=new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while((line=buf.readLine())!=null){
                stringBuilder.append(line+"\n");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
