package sg.edu.rp.c346.id20045435.ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ShowList extends AppCompatActivity {

    Button btnShow;
    ListView lvSong;
    ArrayList<Song> al;
    ArrayAdapter<Song> aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list);

        btnShow = findViewById(R.id.btnShow);
        lvSong = findViewById(R.id.lvSong);

        al = new ArrayList<Song>();
        DBHelper dbh = new DBHelper(ShowList.this);
        al.addAll(dbh.getAllSongs());
        aa = new ArrayAdapter<Song>(this, android.R.layout.simple_list_item_1, al);
        lvSong.setAdapter(aa);
        dbh.close();

        lvSong.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Song data = al.get(position);
                Intent i = new Intent(ShowList.this, Modify.class);
                i.putExtra("data", data);
                startActivity(i);
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ShowList.this);
                al.clear();
                String rate = "5";
                al.addAll(dbh.getAllSongs(rate));
                aa.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        DBHelper dbh = new DBHelper(ShowList.this);
        al.clear();
        al.addAll(dbh.getAllSongs());
        aa.notifyDataSetChanged();
        dbh.close();
    }
}