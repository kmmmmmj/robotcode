package project.sk.robocode3.robot_project;

import org.roboid.robot.Device;
import org.roboid.robot.Robot;

/**
 * Created by minjeong on 2018-05-09.
 */

public class CommonObject {
    //연결된 하드웨어 장치. main에서 셋팅함.
    static String[] nation_name = new String[]{"한국","일본","중국","미국"};
    static String[] nation_capital = new String[]{"서울","도쿄","베이징","워싱턴"};
    static int[] nation_oid = new int[]{51814,51813,51811,51815};
    static int[] nation_flag = new int[]{R.drawable.korea,R.drawable.japan,R.drawable.china,R.drawable.america};
    static int[] capital_button = new int[]{R.drawable.seoul,R.drawable.tokyo,R.drawable.beijing,R.drawable.washington};
    //현재 초록깃발-51814(한국) 파랑-51815(미국) 빨강-51811(중국) 노랑-51813(일본)

    static Robot robot;
    static Device mLeftWheelDevice;
    static Device mRightWheelDevice;
    static Device mOidDevice;
    static Device mLeftEyeDevice;
    static Device mRightEyeDevice;
    static Device mBuzzerDevice;
    static int right_wheel_speed = 0;
    static int left_wheel_speed = 0;
}
