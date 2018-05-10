package project.sk.robocode3.robot_project;

import android.content.Intent;
import android.os.Bundle;

import org.roboid.android.RobotActivity;
import org.roboid.robot.Robot;

public class TempActivity extends RobotActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);

        int index = getIntent().getExtras().getInt("index");

        Intent intent = new Intent(getApplicationContext(),TravelStartActivity.class);
        //여기 인덱스 값 넣어줘야 함 mPageNumber를 대신해서! mPageNumber사용한 CommonObject부분 코드 다 읽어볼것
        intent.putExtra("index",index);
        startActivity(intent);
    }

    @Override
    public void onInitialized(Robot robot) {

    }
}
