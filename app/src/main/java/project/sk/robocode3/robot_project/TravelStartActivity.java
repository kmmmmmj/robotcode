package project.sk.robocode3.robot_project;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import org.roboid.android.Connector;
import org.roboid.android.HardwareWorld;
import org.roboid.android.RobotActivity;
import org.roboid.robot.Robot;

import kr.robomation.physical.UoAlbert;

import static project.sk.robocode3.robot_project.CommonObject.left_wheel_speed;
import static project.sk.robocode3.robot_project.CommonObject.nation_oid;
import static project.sk.robocode3.robot_project.CommonObject.right_wheel_speed;
import static project.sk.robocode3.robot_project.CommonObject.robot;


public class TravelStartActivity extends RobotActivity {

    private int index;
    private Thread th,th2;
    private Button button_move_up,button_move_down,button_move_left,button_move_right,button_submit;
    public int frontOID;
    private View popupView_correct,popupView_incorrect;
    private PopupWindow popupWindow_correct,popupWindow_incorrect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_start);

        index = getIntent().getExtras().getInt("index");

        HardwareWorld hardwareWorld = Connector.mWorld;
        robot = hardwareWorld.findRobotById(UoAlbert.ID, 0);

        CommonObject.mLeftWheelDevice = robot.findDeviceById(UoAlbert.LEFT_WHEEL);
        CommonObject.mRightWheelDevice = robot.findDeviceById(UoAlbert.RIGHT_WHEEL);
        CommonObject.mOidDevice =  robot.findDeviceById(UoAlbert.OID);

        th = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    CommonObject.mLeftWheelDevice.write(left_wheel_speed);
                    CommonObject.mRightWheelDevice.write(right_wheel_speed);
                }
            }
        });
        th.start();

        th2 = new Thread(new Runnable(){
            @Override
            public void run() {
                frontOID = -1;
                while(true){
                    if( CommonObject.mOidDevice.e()) // 이벤트가 발생하였다.
                    {
                        frontOID =  CommonObject.mOidDevice.read();
                        Log.e("frontOID",""+frontOID);
                    }
                }
            }
        });
        th2.start();

        popupView_correct = getLayoutInflater().inflate(R.layout.popup_correct, null);
        popupWindow_correct = new PopupWindow(popupView_correct, RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT,true);

        popupView_incorrect = getLayoutInflater().inflate(R.layout.popup_incorrect, null);
        popupWindow_incorrect = new PopupWindow(popupView_incorrect, RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT,true);


        button_move_up = (Button)findViewById(R.id.button_move_up);
        button_move_down = (Button)findViewById(R.id.button_move_down);
        button_move_left = (Button)findViewById(R.id.button_move_left);
        button_move_right = (Button)findViewById(R.id.button_move_right);
        button_submit = (Button)findViewById(R.id.button_finish);

        button_move_up.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                if(action == MotionEvent.ACTION_DOWN) {
                    left_wheel_speed = 100;
                    right_wheel_speed = 100;
                }else if(action == MotionEvent.ACTION_UP) {
                    left_wheel_speed = 0;
                    right_wheel_speed = 0;

                }else{
                }
                return false;
            }
        });

        button_move_down.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                if(action == MotionEvent.ACTION_DOWN) {
                    left_wheel_speed = -100;
                    right_wheel_speed = -100;
                }else if(action == MotionEvent.ACTION_UP) {
                    left_wheel_speed = 0;
                    right_wheel_speed = 0;

                }else{
                }
                return false;
            }
        });

        button_move_left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                if(action == MotionEvent.ACTION_DOWN) {
                    left_wheel_speed = -100;
                    right_wheel_speed = 100;
                }else if(action == MotionEvent.ACTION_UP) {
                    left_wheel_speed = 0;
                    right_wheel_speed = 0;

                }else{
                }
                return false;
            }
        });

        button_move_right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                if(action == MotionEvent.ACTION_DOWN) {
                    left_wheel_speed = 100;
                    right_wheel_speed = -100;
                }else if(action == MotionEvent.ACTION_UP) {
                    left_wheel_speed = 0;
                    right_wheel_speed = 0;

                }else{
                }
                return false;
            }
        });

        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(frontOID == nation_oid[index]){
                    popupWindow_correct.showAtLocation(popupView_correct, Gravity.CENTER,0,0);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(popupWindow_correct.isShowing()){
                                popupWindow_correct.dismiss();
                                Intent intent =  new Intent(getApplicationContext(), QuizActivity.class);
                                intent.putExtra("index",index);
                                startActivity(intent);
                            }
                        }
                    },3000);

                }else{
                    popupWindow_incorrect.showAtLocation(popupView_correct, Gravity.CENTER,0,0);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(popupWindow_incorrect.isShowing()){
                                popupWindow_incorrect.dismiss();
                            }
                        }
                    },3000);

                }
            }
        });

    }

    @Override
    public void onInitialized(Robot robot) {

    }


}
