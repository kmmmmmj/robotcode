package project.sk.robocode3.robot_project;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import org.roboid.android.Connector;
import org.roboid.android.HardwareWorld;
import org.roboid.android.RobotActivity;
import org.roboid.robot.Robot;

import kr.robomation.physical.UoAlbert;

import static project.sk.robocode3.robot_project.CommonObject.left_wheel_speed;
import static project.sk.robocode3.robot_project.CommonObject.nation_flag;
import static project.sk.robocode3.robot_project.CommonObject.nation_oid;
import static project.sk.robocode3.robot_project.CommonObject.right_wheel_speed;
import static project.sk.robocode3.robot_project.CommonObject.robot;


public class TravelStartActivity extends RobotActivity {

    private int index;
    private Thread th,th2,th3,th4;
    private Button button_move_up,button_move_down,button_move_left,button_move_right,button_submit;
    private ImageView imageview_nationflag;
    public int frontOID;
    private View popupView_correct,popupView_incorrect;
    private PopupWindow popupWindow_correct,popupWindow_incorrect;
    public int red=0,green=0,blue=0;
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
        CommonObject.mLeftEyeDevice = robot.findDeviceById(UoAlbert.LEFT_EYE);
        CommonObject.mRightEyeDevice = robot.findDeviceById(UoAlbert.RIGHT_EYE);
        CommonObject.mBuzzerDevice = robot.findDeviceById(UoAlbert.BUZZER);

        th = new Thread(new Runnable() {
            @Override
            public void run() {
                frontOID = -1;
                while(true){
                    CommonObject.mLeftWheelDevice.write(left_wheel_speed);
                    CommonObject.mRightWheelDevice.write(right_wheel_speed);
                    if( CommonObject.mOidDevice.e()) // 이벤트가 발생하였다.
                    {
                        frontOID =  CommonObject.mOidDevice.read();
                        Log.e("frontOID",""+frontOID);
                    }
                }
            }
        });
        th.start();


        popupView_correct = getLayoutInflater().inflate(R.layout.popup_correct, null);
        popupWindow_correct = new PopupWindow(popupView_correct, RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT,true);

        popupView_incorrect = getLayoutInflater().inflate(R.layout.popup_incorrect, null);
        popupWindow_incorrect = new PopupWindow(popupView_incorrect, RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT,true);


        button_move_up = (Button)findViewById(R.id.button_move_up);
        button_move_down = (Button)findViewById(R.id.button_move_down);
        button_move_left = (Button)findViewById(R.id.button_move_left);
        button_move_right = (Button)findViewById(R.id.button_move_right);
        button_submit = (Button)findViewById(R.id.button_finish);
        imageview_nationflag = (ImageView)findViewById(R.id.imageview_nationflag);

        imageview_nationflag.setImageResource(nation_flag[index]);

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
                    red = 69;
                    green = 121;
                    blue = 227;
                    CommonObject.mLeftEyeDevice.write(0,red);
                    CommonObject.mRightEyeDevice.write(0,red);
                    CommonObject.mLeftEyeDevice.write(1,green);
                    CommonObject.mRightEyeDevice.write(1,green);
                    CommonObject.mLeftEyeDevice.write(2,blue);
                    CommonObject.mRightEyeDevice.write(2,blue);
                    CommonObject.mBuzzerDevice.write(400);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(popupWindow_correct.isShowing()){
                                CommonObject.mBuzzerDevice.write(600);
                            }
                        }
                    },200);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(popupWindow_correct.isShowing()){
                                CommonObject.mBuzzerDevice.write(800);
                            }
                        }
                    },400);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(popupWindow_correct.isShowing()){

                                CommonObject.mBuzzerDevice.write(1000);
                            }
                        }
                    },600);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(popupWindow_correct.isShowing()){
                                CommonObject.mBuzzerDevice.write(0);
                            }
                        }
                    },800);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(popupWindow_correct.isShowing()){
                                popupWindow_correct.dismiss();
                                red = 0;
                                green = 0;
                                blue = 0;
                                CommonObject.mLeftEyeDevice.write(0,red);
                                CommonObject.mRightEyeDevice.write(0,red);
                                CommonObject.mLeftEyeDevice.write(1,green);
                                CommonObject.mRightEyeDevice.write(1,green);
                                CommonObject.mLeftEyeDevice.write(2,blue);
                                CommonObject.mRightEyeDevice.write(2,blue);
                                Intent intent =  new Intent(getApplicationContext(), QuizActivity.class);
                                intent.putExtra("index",index);
                                startActivity(intent);
                            }
                        }
                    },2700);

                }else{
                    popupWindow_incorrect.showAtLocation(popupView_correct, Gravity.CENTER,0,0);
                    red = 255;
                    green = 0;
                    blue = 0;
                    CommonObject.mLeftEyeDevice.write(0,red);
                    CommonObject.mRightEyeDevice.write(0,red);
                    CommonObject.mLeftEyeDevice.write(1,green);
                    CommonObject.mRightEyeDevice.write(1,green);
                    CommonObject.mLeftEyeDevice.write(2,blue);
                    CommonObject.mRightEyeDevice.write(2,blue);
                    CommonObject.mBuzzerDevice.write(200);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(popupWindow_incorrect.isShowing()){
                                CommonObject.mBuzzerDevice.write(0);
                            }
                        }
                    },500);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(popupWindow_incorrect.isShowing()){
                                popupWindow_incorrect.dismiss();
                                red = 0;
                                green = 0;
                                blue = 0;
                                CommonObject.mLeftEyeDevice.write(0,red);
                                CommonObject.mRightEyeDevice.write(0,red);
                                CommonObject.mLeftEyeDevice.write(1,green);
                                CommonObject.mRightEyeDevice.write(1,green);
                                CommonObject.mLeftEyeDevice.write(2,blue);
                                CommonObject.mRightEyeDevice.write(2,blue);
                                CommonObject.mBuzzerDevice.write(0);
                            }
                        }
                    },2500);

                }
            }
        });

    }

    @Override
    public void onInitialized(Robot robot) {

    }


}
