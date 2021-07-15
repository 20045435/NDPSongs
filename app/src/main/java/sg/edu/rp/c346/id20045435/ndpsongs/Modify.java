package sg.edu.rp.c346.id20045435.ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Set;

public class Modify extends AppCompatActivity {

    EditText etId, etTitle, etSinger, etYear;
    RadioGroup rgbStar;
    RadioButton rb1, rb2, rb3, rb4, rb5;
    Button btnUpdate, btnDelete, btnCancel;
    Song data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);

        etId = findViewById(R.id.etID);
        etTitle = findViewById(R.id.etTitle1);
        etSinger = findViewById(R.id.etSingers1);
        etYear = findViewById(R.id.etYear1);
        rgbStar = findViewById(R.id.rgbStar);
        rb1 = findViewById(R.id.radioButton);
        rb2 = findViewById(R.id.radioButton2);
        rb3 = findViewById(R.id.radioButton3);
        rb4 = findViewById(R.id.radioButton4);
        rb5 = findViewById(R.id.radioButton5);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);

        Intent i = getIntent();
        data = (Song) i.getSerializableExtra("data");

        etId.setText(data.get_id() + "");
        etTitle.setText(data.getTitle());
        etSinger.setText(data.getSingers());
        etYear.setText(data.getYear() + "");

        if (data.getStars() == 1) {
            rb1.setChecked(true);
        }
        else if (data.getStars() == 2){
            rb2.setChecked(true);
        }
        else if (data.getStars() == 3){
            rb3.setChecked(true);
        }
        else if (data.getStars() == 4){
            rb4.setChecked(true);
        }
        else if (data.getStars() == 5){
            rb5.setChecked(true);
        }

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(Modify.this);
                dbh.deleteSong(data.get_id());
                finish();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(Modify.this);

                int stars = 0;
                String title = etTitle.getText().toString();
                String singer = etSinger.getText().toString();
                int year = Integer.parseInt(etYear.getText().toString());

                if(rb1.isChecked()){
                    stars=1;
                }
                else if(rb2.isChecked()){
                    stars=2;
                }
                else if(rb3.isChecked()){
                    stars=3;
                }
                else if(rb4.isChecked()){
                    stars=4;
                }
                else if(rb5.isChecked()){
                    stars=5;
                }

                data.setSongContent(title, singer, year, stars);
                dbh.updateSong(data);
                dbh.close();
                finish();
            }
        });
    }
}