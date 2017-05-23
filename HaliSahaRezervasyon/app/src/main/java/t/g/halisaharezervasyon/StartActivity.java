package t.g.halisaharezervasyon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class StartActivity extends AppCompatActivity {
    CalendarView Takvim;
    DatePicker GununTarihi;
    Button btnRezervasyonYap;
    TextView SeciliTarih;
    RezervasyonVeritabanı veritabanı;
    int haftanumarasi, ay, dayOfWeek,gun;

    ArrayList<Saha> SahaListesi = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Takvim = (CalendarView) findViewById(R.id.takvim);
        GununTarihi = (DatePicker) findViewById(R.id.gununtarihi);
        btnRezervasyonYap = (Button) findViewById(R.id.btnRezervasyon);
        SeciliTarih = (TextView) findViewById(R.id.tvSeciliTarih);

        this.veritabanı = new RezervasyonVeritabanı(getApplicationContext());

        Saha s1 = new Saha(1,"Saha1");
        Saha s2 = new Saha(2,"Saha2");
        veritabanı.SahaEkle(s1);
        veritabanı.SahaEkle(s2);

        //İçinde bulunulan güne ait değerler
        Calendar calendar = Calendar.getInstance();
        calendar.set(GununTarihi.getYear(),GununTarihi.getMonth(),GununTarihi.getDayOfMonth());
        haftanumarasi = calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH);
        ay = calendar.get(Calendar.MONTH);
        gun = calendar.get(Calendar.DAY_OF_WEEK);

        Takvim.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth)
            {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);
                dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
                int WeekNumber = calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH);
                SeciliTarih.setText("Seçtiğiniz Tarih: " + dayOfMonth + "." + (month+1) + "." + year);

                if( haftanumarasi == WeekNumber && ay == month &&gun<=dayOfWeek)
                    btnRezervasyonYap.setClickable(true);
                else
                {
                    btnRezervasyonYap.setClickable(false);
                    Toast.makeText(getApplicationContext(),"Yalnızca geçerli haftaya rezervasyon yapılabilir!",Toast.LENGTH_LONG).show();
                }
            }
        });//Takvim SetOnDate END

    }//onCreate END


    public void rezervasyon(View view) {
        //Veri taşımak için Bundle Oluşturuluyor
        Bundle extras = new Bundle();
        extras.putInt("RezervasyonGun",dayOfWeek);

        Intent intent = new Intent();
        intent.putExtras(extras);

        intent.setClass(getApplicationContext(),RezervasyonActivity.class);
        startActivity(intent);
    }
}//StartActivity END
