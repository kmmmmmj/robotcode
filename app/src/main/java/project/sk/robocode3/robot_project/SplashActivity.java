package project.sk.robocode3.robot_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    /*
    static String[] nation_name = new String[]{"한국","일본","중국","미국"};
    static String[] nation_capital = new String[]{"서울","도쿄","베이징","워싱턴"};
    static int[] nation_oid = new int[]{51814,51813,51811,51815};
    static int[] nation_flag = new int[]{R.drawable.korea,R.drawable.japan,R.drawable.china,R.drawable.america};
    static int[] capital_button = new int[]{R.drawable.seoul,R.drawable.tokyo,R.drawable.beijing,R.drawable.washington};
     */

    int[] index;
    String[] name = new String[]{"한국","일본","중국","미국"};
    String[] dae = new String[]{"아시아","아시아","아시아","북아메리카"};
    String[] cho = new String[]{"ㅎㄱ","ㅇㅂ","ㅈㄱ","ㅁㄱ"};
    int[] oid = new int[]{51814,51813,51811,51815};;
    String[] color = new String[]{"아시아","아시아","아시아","북아메리카"};;
    int[] flag = new int[]{R.drawable.korea,R.drawable.japan,R.drawable.china,R.drawable.america};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        finish();

        //나라객체초기화
        for(int i = 0;i<name.length;i++){
            CommonObject.nation[i] = new NationObject(i,this.name[i],this.dae[i],this.cho[i],this.oid[i],this.color[i],this.flag[i]);
        }
    }

}
