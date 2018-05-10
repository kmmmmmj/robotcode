package project.sk.robocode3.robot_project;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.roboid.android.Connector;
import org.roboid.android.HardwareWorld;
import org.roboid.android.RobotActivity;
import org.roboid.robot.Robot;

import java.util.Random;

import kr.robomation.physical.UoAlbert;

import static project.sk.robocode3.robot_project.CommonObject.capital_button;
import static project.sk.robocode3.robot_project.CommonObject.nation_capital;
import static project.sk.robocode3.robot_project.CommonObject.nation_name;
import static project.sk.robocode3.robot_project.CommonObject.robot;

public class QuizActivity extends RobotActivity implements View.OnClickListener {

    private int index;
    private TextView Textview_quiz;
    private ImageButton button_answer1, button_answer2;
    private int answer;

    private PopupWindow popupWindow_correct;
    private View popupView_correct;
    private TextView textview_content;
    private Button button_go_home;

    public int red=0,green=0,blue=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        index = getIntent().getExtras().getInt("index");

        HardwareWorld hardwareWorld = Connector.mWorld;
        robot = hardwareWorld.findRobotById(UoAlbert.ID, 0);

        CommonObject.mLeftEyeDevice = robot.findDeviceById(UoAlbert.LEFT_EYE);
        CommonObject.mRightEyeDevice = robot.findDeviceById(UoAlbert.RIGHT_EYE);
        CommonObject.mBuzzerDevice = robot.findDeviceById(UoAlbert.BUZZER);


        Textview_quiz = (TextView)findViewById(R.id.Textview_quiz);
        button_answer1 = (ImageButton)findViewById(R.id.button_answer1);
        button_answer2 = (ImageButton)findViewById(R.id.button_answer2);

        Textview_quiz.setText(""+nation_name[index]+"의 수도는?");

        popupView_correct = getLayoutInflater().inflate(R.layout.popup_quiz_finish, null);
        popupWindow_correct = new PopupWindow(popupView_correct, RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT,true);
        textview_content = (TextView) popupView_correct.findViewById(R.id.textview_content);
        textview_content.setText("정답입니다! "+nation_name[index]+"의 수도는 "+nation_capital[index]+"입니다!");
        button_go_home = (Button)popupView_correct.findViewById(R.id.button_go_home);


        Random r = new Random();
        answer = r.nextInt(2)+1;
        int another_answer;
        while(true){
            another_answer = r.nextInt(nation_name.length);
            if(another_answer == index)
                continue;
            else
                break;
        }

        if(answer == 1){
            button_answer1.setBackgroundResource(capital_button[index]);
            button_answer2.setBackgroundResource(capital_button[another_answer]);
        }else{
            button_answer2.setBackgroundResource(capital_button[index]);
            button_answer1.setBackgroundResource(capital_button[another_answer]);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_answer1 :
                if(answer == 1){
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
                                red = 0;
                                green = 0;
                                blue = 0;
                                CommonObject.mLeftEyeDevice.write(0,red);
                                CommonObject.mRightEyeDevice.write(0,red);
                                CommonObject.mLeftEyeDevice.write(1,green);
                                CommonObject.mRightEyeDevice.write(1,green);
                                CommonObject.mLeftEyeDevice.write(2,blue);
                                CommonObject.mRightEyeDevice.write(2,blue);
                            }
                        }
                    },1000);
                }else{
                    button_answer1.setImageResource(R.drawable.incorrect);
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
                                CommonObject.mBuzzerDevice.write(0);
                        }
                    },500);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
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
                    },1000);
                }
                break;
            case R.id.button_answer2 :
                if(answer == 2){
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
                                red = 0;
                                green = 0;
                                blue = 0;
                                CommonObject.mLeftEyeDevice.write(0,red);
                                CommonObject.mRightEyeDevice.write(0,red);
                                CommonObject.mLeftEyeDevice.write(1,green);
                                CommonObject.mRightEyeDevice.write(1,green);
                                CommonObject.mLeftEyeDevice.write(2,blue);
                                CommonObject.mRightEyeDevice.write(2,blue);
                            }
                        }
                    },1000);
                }else{
                    button_answer2.setImageResource(R.drawable.incorrect);
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
                                CommonObject.mBuzzerDevice.write(0);
                        }
                    },500);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
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
                    },1000);
                }
                break;
            case R.id.button_go_home:
                Intent intent = new Intent(getApplicationContext(),StartActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    @Override
    public void onInitialized(Robot robot) {

    }
}
