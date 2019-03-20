package at.aau;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    EditText matrikelnummer;
    TextView response;
    Button btnAbschicken;
    Button btnAscii;
    static String responseFromServer;
    String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Connect objects
        matrikelnummer = findViewById(R.id.input);
        response = findViewById(R.id.response);
        btnAbschicken = findViewById(R.id.btnAbschicken);
        btnAscii = findViewById(R.id.btnAscii);

        // Button Abschicken:
        btnAbschicken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // establish connection with socket (after clicked)
                if(matrikelnummer != null){
                    message = matrikelnummer.getText().toString();
                    send(v);
                }
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                response.setText(responseFromServer);
            }
        });
    }

    public void send(View v){
        Sender sender = new Sender();
        sender.execute(message);
    }
}
