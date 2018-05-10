package project.sk.robocode3.robot_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_start_game :
                Intent intent = new Intent(getApplicationContext(), StartActivity.class);
                startActivity(intent);
                break;
            case R.id.button_exit_app :
                finish();
                break;
        }
    }

}
