package project.sk.robocode3.robot_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import org.roboid.android.RobotActivity;
import org.roboid.robot.Robot;

import java.util.Random;

import static project.sk.robocode3.robot_project.CommonObject.nation_name;

public class StartActivity extends RobotActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

       }


    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.button_choose_nation :
                intent = new Intent(getApplicationContext(), SelectActivity.class);
                startActivity(intent);
                break;
            case R.id.button_start_travel :
                intent = new Intent(getApplicationContext(), TravelStartActivity.class);
                Random r = new Random();
                int index = r.nextInt(nation_name.length);
                intent.putExtra("index",index);
                startActivity(intent);
                break;
            case R.id.button_study :
                intent = new Intent(getApplicationContext(), StudyActivity.class);
                startActivity(intent);
                break;
        }
    }


    @Override
    public void onInitialized(Robot robot) {

    }
}
