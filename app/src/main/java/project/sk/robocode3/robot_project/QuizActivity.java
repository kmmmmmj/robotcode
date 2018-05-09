package project.sk.robocode3.robot_project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

public class QuizActivity extends AppCompatActivity {

    private TextView Textview_quiz;
    private Button button_answer1, button_answer2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Textview_quiz = (TextView)findViewById(R.id.Textview_quiz);
        button_answer1 = (Button)findViewById(R.id.button_answer1);
        button_answer2 = (Button)findViewById(R.id.button_answer2);


    }
}
