package me.zchi.lazyaccounts;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);


        TextView userEmail=findViewById(R.id.text_user_email);
        Button userActionBtn=findViewById(R.id.email_log_out_button);

        SharedPreferences sp=getSharedPreferences("userInfo",0);
        String email=sp.getString("email","");
        if(email.length()==0){
            userEmail.setText(R.string.log_in_comment);
            userActionBtn.setText(R.string.action_log_in);
            Toast.makeText(getApplicationContext(),R.string.log_in_comment,Toast.LENGTH_LONG).show();
            userActionBtn.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(UserActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            } );
        }else{
            userEmail.setText(email);
            userActionBtn.setText(R.string.action_log_out);
            userActionBtn.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    SharedPreferences sp=getSharedPreferences("userInfo",0);
                    SharedPreferences.Editor editor=sp.edit();
                    editor.putString("token","");
                    editor.putString("email","");
                    editor.apply();
                    finish();
                }
            } );
        }







    }
}
;