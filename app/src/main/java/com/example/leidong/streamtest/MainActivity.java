package com.example.leidong.streamtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private Button button1;
    private Button button2;
    private Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String writeStr = editText.getText().toString();
                File cacheDir = MyApplication.getContext().getFilesDir();
                File file = new File(cacheDir, "test.json");
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(file);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    fos.write(writeStr.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String readStr = "";
                StringBuilder sb = new StringBuilder();
                String filePath = MyApplication.getContext().getFilesDir().toString() + File.separator + "test.json";
                FileInputStream fis = null;
                try {
                    fis = new FileInputStream(filePath);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                byte[] buffer = new byte[1024];
                try {
                    while(fis.read(buffer) != -1){
                        sb.append(new String(buffer));
                    }
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                readStr = new String(sb);
                Toast.makeText(MyApplication.getContext(), readStr, Toast.LENGTH_LONG).show();
                editText.setText(readStr);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
            }
        });
    }

    private void init() {
        editText = (EditText) findViewById(R.id.editText);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
    }
}
