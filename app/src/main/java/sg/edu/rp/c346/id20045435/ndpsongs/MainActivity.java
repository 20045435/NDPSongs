package sg.edu.rp.c346.id20045435.ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etTitle, etSinger, etYear;
    RadioGroup rgbStar;
    Button btnAdd, btnShowList;
    ArrayList<Song> al;
    ArrayAdapter<Song> aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTitle = findViewById(R.id.etTitle);
        etSinger = findViewById(R.id.etSinger);
        etYear = findViewById(R.id.etYear);
        rgbStar = findViewById(R.id.rgbStar);
        btnAdd = findViewById(R.id.btnAdd);
        btnShowList = findViewById(R.id.btnShowList);

        al = new ArrayList<Song>();
        aa = new ArrayAdapter<Song>(this, android.R.layout.simple_list_item_1, al);


        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShowList.class);
                startActivity(intent);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etTitle.getText().toString();
                String singer = etSinger.getText().toString();
                int year = Integer.parseInt(etYear.getText().toString());
                int stars = -1;

                if (rgbStar.getCheckedRadioButtonId() == R.id.rb1) {
                    stars = 1;
                }
                else if (rgbStar.getCheckedRadioButtonId() == R.id.rb2) {
                    stars = 2;
                }
                else if (rgbStar.getCheckedRadioButtonId() == R.id.rb3) {
                    stars = 3;
                }
                else if (rgbStar.getCheckedRadioButtonId() == R.id.rb4) {
                    stars = 4;
                }
                else if (rgbStar.getCheckedRadioButtonId() == R.id.rb5) {
                    stars = 5;
                }

                DBHelper dbh = new DBHelper(MainActivity.this);
                long inserted_id = dbh.insertSong(title, singer, year, stars);

                if (inserted_id != -1){
                    al.clear();
                    al.addAll(dbh.getAllSongs());
                    aa.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "Insert successful", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}