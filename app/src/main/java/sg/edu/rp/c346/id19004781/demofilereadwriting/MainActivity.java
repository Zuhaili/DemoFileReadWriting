package sg.edu.rp.c346.id19004781.demofilereadwriting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class MainActivity extends AppCompatActivity {

    String folderLocation;
    Button btnRead, btnWrite;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRead = findViewById(R.id.btnRead);
        btnWrite = findViewById(R.id.btnWrite);
        textView = findViewById(R.id.textView);

        //Folder creation
        String folderLocation = getFilesDir().getAbsolutePath() + "/MyFolder";
//        String folderLocation = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Folder";

        String[] permission = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
        ActivityCompat.requestPermissions(MainActivity.this, permission, 0);

        File folder = new File(folderLocation);
        Log.d("folder", folderLocation);
        if (folder.exists() == false) {
            boolean result = folder.mkdir();
            if (result == true) {
                Log.d("File Read/Write", "Folder created");
                Toast.makeText(MainActivity.this, "Folder created", Toast.LENGTH_SHORT).show();
            }
        }


        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Code for file writing
                String folderLocation_I = getFilesDir().getAbsolutePath() + "/Folder";
                File folder_I = new File(folderLocation_I);
                if (folder_I.exists() == false) {
                    boolean result = folder_I.mkdir();
                    if (result == true) {
                        Log.d("File Read/Write", "Folder created");
                        Toast.makeText(MainActivity.this, "Folder created", Toast.LENGTH_SHORT).show();
                    }
                }


//                    Log.d("folderlocation",folderLocation);
                try {
//                        String folderLocation_I= getFilesDir().getAbsolutePath() + "/Folder";
                    File targetFile_I = new File(folderLocation, "data.txt");
                    FileWriter writer_I = new FileWriter(targetFile_I, true);
                    writer_I.write("test data" + "\n");
                    writer_I.flush();
                    writer_I.close();
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Failed to write!", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }


                //External
                String folderLocation = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Folder";
                File folder = new File(folderLocation);
                if (folder.exists() == false) {
                    boolean result = folder.mkdir();
                    if (result == true) {
                        Log.d("File Read/Write", "Folder created");
                    }
                }

                try {
//                    String folderLocation= Environment.getExternalStorageDirectory().getAbsolutePath() + "/Folder";
                    File targetFile = new File(folderLocation, "data.txt");
                    FileWriter writer = new FileWriter(targetFile, true);
                    writer.write("Hello world" + "\n");
                    writer.flush();
                    writer.close();
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Failed to write!", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }


            }
        });

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Code for file reading
                File targetFile = new File(folderLocation, "data.txt");

                if (targetFile.exists() == true) {
                    String data = "";
                    try {
                        FileReader reader = new FileReader(targetFile);
                        BufferedReader br = new BufferedReader(reader);
                        String line = br.readLine();
                        while (line != null) {
                            data += line + "\n";
                            line = br.readLine();
                        }
                        textView.setText(data);
                        br.close();
                        reader.close();
                    } catch (Exception e) {
                        Toast.makeText(MainActivity.this, "Failed to read!", Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                    Log.d("Content", data);
                }
            }
        });

    }
}