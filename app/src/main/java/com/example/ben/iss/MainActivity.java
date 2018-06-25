package com.example.ben.iss;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFrame;
import java.util.List;
import java.util.Map;
import io.github.sac.Socket;
import io.github.sac.Ack;
import io.github.sac.BasicListener;
import io.github.sac.Emitter;
import io.github.sac.EventThread;
import io.github.sac.ReconnectStrategy;

public
class MainActivity extends AppCompatActivity {

    EditText myEditText;
    Button myButton;

    String webUrl="ws://192.168.0.4:8000/socketcluster/";
    Socket socket;

    @Override
    protected
    void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         myEditText = findViewById(R.id.tipValue);
         myButton = findViewById(R.id.getValueButton);
         myButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 if (myEditText.getText() != null) {
                     String newString = myEditText.getText().toString();
                     Toast.makeText(MainActivity.this,
                             newString, Toast.LENGTH_LONG).show();
                 }
             }
         });
         socket = new Socket(webUrl);
         socket.setListener(new BasicListener() {
             @Override
             public void onConnected(Socket socket, Map<String, List<String>> headers) {
                 Log.i("Success ","Connected to endpoint");
                 socket.createChannel("MyClassroom").subscribe();
             }

             @Override
             public void onDisconnected(Socket socket, WebSocketFrame serverCloseFrame, WebSocketFrame clientCloseFrame, boolean closedByServer) {
                 Log.i("Success ","Got connect error ");
             }

             @Override
             public void onConnectError(Socket socket, WebSocketException exception) {

             }

             @Override
             public void onAuthentication(Socket socket, Boolean status) {
                 if (status) {
                     Log.i("Success ","socket is authenticated");
                 } else {
                     Log.i("Success ","Authentication is required optional)");
                 }
             }

             @Override
             public void onSetAuthToken(String token, Socket socket) {
                 socket.setAuthToken(token);
             }
         });

        socket.setReconnection(new
                ReconnectStrategy().setMaxAttempts(10).setDelay(3000));
    }
}
