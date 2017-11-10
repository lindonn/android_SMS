package miage.m2.tp_pietraru_legarrec;

import android.Manifest;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button envoyer = null;
    EditText numero = null;
    EditText message = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // On récupère toutes les vues dont on a besoin
        envoyer = (Button)findViewById(R.id.envoyer);
        numero = (EditText)findViewById(R.id.numero);
        message = (EditText)findViewById(R.id.message);

        // On attribue un listener adapté aux vues qui en ont besoin
        envoyer.setOnClickListener(envoyerListener);
        //numero.addTextChangedListener(textWatcher);
        //message.addTextChangedListener(textWatcher);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 1);


    }

        private  OnClickListener envoyerListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            String n = numero.getText().toString();
            String m = message.getText().toString();

            String alln[];
            alln = n.split(";");

            if(n.length() < 4)
                Toast.makeText(MainActivity.this, "Il faut rentrer au moins 4 chiffres", Toast.LENGTH_SHORT).show();
            else {
                if(m.isEmpty())
                    Toast.makeText(MainActivity.this, "Ton message est vide", Toast.LENGTH_SHORT).show();
                else {

                    try {
                        SmsManager smsManager = SmsManager.getDefault();
                        for(int i = 0; i < alln.length; i++) {
                            n=alln[i];
                            smsManager.sendTextMessage(n, null, m, null, null);
                            Toast.makeText(getApplicationContext(), "SMS sent.", Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "SMS faild, please try again.", Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                }
            }
        }
    };
}
