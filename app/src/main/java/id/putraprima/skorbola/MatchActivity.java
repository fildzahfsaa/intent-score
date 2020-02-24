package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MatchActivity extends AppCompatActivity {
    private static final String RESULT_KEY = "result";
    private TextView homeText;
    private TextView awayText;
    private ImageView homeImage;
    private ImageView awayImage;
    int scoreHome=0;
    int scoreAway=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        homeText = findViewById(R.id.txt_home);
        awayText = findViewById(R.id.txt_away);
        homeImage = findViewById(R.id.home_logo);
        awayImage = findViewById(R.id.away_logo);

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            Bundle extra = getIntent().getExtras();
            Bitmap bm = extra.getParcelable("imageHome");
            Bitmap bm2 = extra.getParcelable("imageAway");

            homeText.setText(extras.getString("home"));
            awayText.setText(extras.getString("away"));

            homeImage.setImageBitmap(bm);
            awayImage.setImageBitmap(bm2);
        }
        //TODO
        //1.Menampilkan detail match sesuai data dari main activity
        //2.Tombol add score menambahkan satu angka dari angka 0, setiap kali di tekan
        //3.Tombol Cek Result menghitung pemenang dari kedua tim dan mengirim nama pemenang ke ResultActivity, jika seri di kirim text "Draw"
    }

    public void addScoreHome(int scoreHome) {
        TextView scoreView=findViewById(R.id.score_home);
        scoreView.setText(String.valueOf(scoreHome));
    }

    public void addHome(View view) {
        scoreHome++;
        addScoreHome(scoreHome);
    }

    public void addScoreAway(int scoreAway){
        TextView scoreView = findViewById(R.id.score_away);
        scoreView.setText(String.valueOf(scoreAway));
    }

    public void addAway(View view) {
        scoreAway++;
        addScoreAway(scoreAway);
    }

    public void cekScore(View view) {
        String result = null;
        if (scoreHome==scoreAway){
            result = "DRAW";
        }else if (scoreHome>scoreAway){
            result = homeText.getText().toString();
        }else if (scoreAway>scoreHome){
            result = homeText.getText().toString();
        }
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra(RESULT_KEY, result);
        startActivity(intent);
    }
}
