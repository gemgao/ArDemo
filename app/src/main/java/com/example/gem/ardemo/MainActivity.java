package com.example.gem.ardemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tvLocal = (TextView) findViewById(R.id.tvLocal);
        TextView tvNetwork = (TextView) findViewById(R.id.tvNetwork);
        tvLocal.setOnClickListener(this);
        tvNetwork.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.tvLocal:
                intent = new Intent(MainActivity.this,LocalActivity.class);
                intent.putExtra("type",1);
                startActivity(intent);
                break;
            case R.id.tvNetwork:
                intent = new Intent(MainActivity.this,LocalActivity.class);
                intent.putExtra("type",2);
                startActivity(intent);
                break;
        }
    }
}
