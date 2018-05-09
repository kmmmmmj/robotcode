package project.sk.robocode3.robot_project;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

import static project.sk.robocode3.robot_project.CommonObject.nation_capital;
import static project.sk.robocode3.robot_project.CommonObject.nation_name;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {

    private int index;
    private TextView Textview_quiz;
    private Button button_answer1, button_answer2;
    private int answer;

    private PopupWindow popupWindow_correct;
    private View popupView_correct;
    private TextView textview_content;
    private Button button_go_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        index = getIntent().getExtras().getInt("index");

        Textview_quiz = (TextView)findViewById(R.id.Textview_quiz);
        button_answer1 = (Button)findViewById(R.id.button_answer1);
        button_answer2 = (Button)findViewById(R.id.button_answer2);

        Textview_quiz.setText(""+nation_name[index]+"의 수도는?");

        popupView_correct = getLayoutInflater().inflate(R.layout.popup_quiz_finish, null);
        popupWindow_correct = new PopupWindow(popupView_correct, RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT,true);
        textview_content = (TextView) popupView_correct.findViewById(R.id.textview_content);
        textview_content.setText("정답입니다! "+nation_name[index]+"의 수도는 "+nation_capital[index]+"입니다!");
        button_go_home = (Button)popupView_correct.findViewById(R.id.button_go_home);


        Random r = new Random();
        answer = r.nextInt(1)+1;
        int another_answer;
        while(true){
            another_answer = r.nextInt(nation_name.length);
            if(another_answer == index)
                continue;
            else
                break;
        }

        if(answer == 1){
            button_answer1.setText(nation_capital[index]);
            button_answer2.setText(nation_capital[another_answer]);
        }else{
            button_answer1.setText(nation_capital[another_answer]);
            button_answer2.setText(nation_capital[index]);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_answer1 :
                if(answer == 1){
                    popupWindow_correct.showAtLocation(popupView_correct, Gravity.CENTER,0,0);
                }else{
                    button_answer1.setBackgroundColor(Color.BLACK);
                }
                break;
            case R.id.button_answer2 :
                if(answer == 2){
                    popupWindow_correct.showAtLocation(popupView_correct, Gravity.CENTER,0,0);
                }else{
                    button_answer2.setBackgroundColor(Color.BLACK);
                }
                break;
            case R.id.button_go_home:
                Intent intent = new Intent(getApplicationContext(),StartActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
