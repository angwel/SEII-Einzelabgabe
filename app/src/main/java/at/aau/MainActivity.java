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
        //Button Ascii:

        btnAscii.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /** ASCII Table: switch every second number
                 * from Matrikelnummer to a suitable ASCII character
                 * e.g. 1 = a, 2 = b, ...
                 */

                // String[] to set characters
                String[] asciiArr = new String[10];

                asciiArr[0] = " ";
                asciiArr[1] = "a";
                asciiArr[2] = "b";
                asciiArr[3] = "c";
                asciiArr[4] = "d";
                asciiArr[5] = "e";
                asciiArr[6] = "f";
                asciiArr[7] = "g";
                asciiArr[8] = "h";
                asciiArr[9] = "i";

                message = matrikelnummer.getText().toString();

                String num = message;

                // entered numbers will be saved in a substring
                String[] arr = new String[num.length()];
                for (int i = 0; i < num.length(); i++) {
                    arr[i] = num.substring(i,(i+1));
                }

                // the substring (start, end) helps to skip indices
                for (int i = 1; i < arr.length; i+=2) {
                    int index = Integer.parseInt(arr[i]);
                    arr[i] = asciiArr[index];
                }



                response.setText(arr[0] + arr[1] + arr[2] + arr[3] + arr[4] + arr[5] + arr[6] +arr[7]);

            }
        });
    }

    public void send(View v){
        Sender sender = new Sender();
        sender.execute(message);
    }
}
