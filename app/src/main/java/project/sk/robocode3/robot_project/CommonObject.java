package project.sk.robocode3.robot_project;

import org.roboid.robot.Device;
import org.roboid.robot.Robot;

/**
 * Created by minjeong on 2018-05-09.
 */

public class CommonObject {
    //연결된 하드웨어 장치. main에서 셋팅함.
    static String[] nation = new String[]{"한국","일본","중국","미국"};
    static Robot robot;
    static Device mLeftWheelDevice;
    static Device mRightWheelDevice;
    static Device mOidDevice;
    static int right_wheel_speed = 0;
    static int left_wheel_speed = 0;

}
