package at.aau;

import android.content.Intent;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Sender extends AsyncTask<String, Void, Void> {

    BufferedReader fromServ;
    DataOutputStream outToServ;
    Socket client;

    @Override
    protected Void doInBackground(String... voids) {
        String matrikelnummer = voids[0]; // = input from EditText matrikelnummer (MainActivity)
        System.out.println("DEBUG IN SENDER: " + matrikelnummer);
        try {

            // This socket provides the connection from client to server
            client = new Socket("se2-isys.aau.at",53212);
            System.out.println("connected? " + client.isConnected());
            // for sending Data
            outToServ = new DataOutputStream(client.getOutputStream());
            // for receiving Data
            fromServ = new BufferedReader(new InputStreamReader(client.getInputStream()));

            // sending matrikelnummer to server
            outToServ.writeBytes(matrikelnummer + "\n");    // \n to let server know input is done
            // answer from server
            String responseFromServ = fromServ.readLine();

            // save answer in TextView
            MainActivity.responseFromServer = responseFromServ;

            outToServ.flush();
            outToServ.close();
            client.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}