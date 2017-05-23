package t.g.halisaharezervasyon;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ListMenuItemView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RezervasyonActivity extends AppCompatActivity {
    TextView tvSaatSeciniz;
    ListView lvSaha1,lvSaha2;
    EditText etIsimSoyisim,etTelefon;
    Button Tamamla,Goruntule;
    int RezervasyonGunu; //Veritabanına aktarılan ve önceki aktivity'den çekilen veri
    String SecilenSaat; //Veritabanına aktarılacak saat verisi
    GunSaat yeniGunSaat;
    Saha saha;

    RezervasyonVeritabanı veritabanı;
    private Bundle extras = null;

    private String[] SaatString1 = {"11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00","21:00","22:00","23:00","23:59"};
    private String[] SaatString2 = {"11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00","21:00","22:00","23:00","23:59"};
    private ArrayAdapter<String> SaatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rezervasyon);
        tvSaatSeciniz = (TextView) findViewById(R.id.tvSaatSeciniz);
        Goruntule = (Button) findViewById(R.id.btnGoruntule);
        Tamamla = (Button) findViewById(R.id.btnTamamla);
        etIsimSoyisim = (EditText) findViewById(R.id.etIsimSoyisim);
        etTelefon = (EditText) findViewById(R.id.etTelefon);
        lvSaha1 = (ListView) findViewById(R.id.lvSaha1);
        lvSaha2 = (ListView) findViewById(R.id.lvSaha2);


        this.veritabanı = new RezervasyonVeritabanı(getApplicationContext());

        Bundle extras;
        extras = getIntent().getExtras();
        RezervasyonGunu = extras.getInt("RezervasyonGun");

        ListView1İcerik();
        ListView2İcerik();


    }//onCreate END

    public void ListView1İcerik()
    {
       DoluSaatSorgula(1);
        SaatAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,SaatString1);
        lvSaha1.setAdapter(SaatAdapter);

        lvSaha1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                SecilenSaat = String.valueOf(lvSaha1.getItemAtPosition(position));
                if(SecilenSaat.length() < 7)
                {
                    tvSaatSeciniz.setText(String.valueOf("Saha1  " + lvSaha1.getItemAtPosition(position)));
                    yeniGunSaat = new GunSaat(RezervasyonGunu,SecilenSaat);
                    saha = veritabanı.SahaGetir(1);
                    lvSaha1.getChildAt(position).setBackgroundColor(Color.YELLOW);
                }
                else
                    Toast.makeText(getApplicationContext(),"Seçtiğiniz saha bu saatte dolu",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void ListView2İcerik()
    {
        DoluSaatSorgula(2);
        SaatAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,SaatString2);
        lvSaha2.setAdapter(SaatAdapter);
        lvSaha2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                SecilenSaat = String.valueOf(lvSaha2.getItemAtPosition(position));
                if(SecilenSaat.length() < 7)
                {
                    tvSaatSeciniz.setText(String.valueOf("Saha2  " + lvSaha2.getItemAtPosition(position)));
                    yeniGunSaat = new GunSaat(RezervasyonGunu,SecilenSaat);
                    saha = veritabanı.SahaGetir(2);
                }
                else
                    Toast.makeText(getApplicationContext(),"Seçtiğiniz saha bu saatte dolu",Toast.LENGTH_LONG).show();
            }
        });
    }

    //Dolu olan saatler sorgulanıyor.
    public void DoluSaatSorgula(int sahaid)
    {
        Rezervasyonlar rezervasyon = null;
        GunSaat gunsaat = null;
        int rezervasyonBoyut = veritabanı.RezervasyonTabloBuyuklugu();
        int gunSaatBoyut = veritabanı.GunSaatTabloBuyuklugu();

        for(int j=1;j<=rezervasyonBoyut;j++)
        {
            rezervasyon = veritabanı.RezervasyonGetir(j);
            if(rezervasyon.getSahaId() == sahaid)
            {
                gunsaat = veritabanı.GunSaatGetir(rezervasyon.getGunSaatId());
                    if(gunsaat.getGun() == RezervasyonGunu)
                    {
                        for(int k=0;k<SaatString1.length;k++)
                        {
                            String s = gunsaat.getSaat();
                            String t = SaatString1[k];
                            boolean correct = s.equals(t);
                            if(correct)
                            {
                                if(rezervasyon.getSahaId() == sahaid && gunsaat.getGun() == RezervasyonGunu && sahaid == 1)
                                {
                                    SaatString1[k] = SaatString1[k] + "    DOLU!!!";
                                }
                                else if(rezervasyon.getSahaId() == sahaid && gunsaat.getGun() == RezervasyonGunu && sahaid == 2)
                                {
                                    SaatString2[k] = SaatString2[k] + "    DOLU!!!";
                                }
                            }
                        }
                    }
            }
        }
    }


    //İşlemi Tamamla Buton Tıklandığında Veri Tabanı kayıt eklemesi
    public void tamamla(View view)
    {
        if(etIsimSoyisim.getText() != null && etTelefon.getText() != null && yeniGunSaat != null)
        {
            String isimsoyisim = etIsimSoyisim.getText().toString();
            String telefon = etTelefon.getText().toString();

            Kullanici kullanici = new Kullanici(isimsoyisim,telefon);

            veritabanı.KullaniciEkle(kullanici);
            int j = veritabanı.GunSaatEkle(yeniGunSaat);

            int i = veritabanı.KullaniciTabloBuyuklugu();

            Rezervasyonlar rezervasyon = new Rezervasyonlar(i,saha.getId(),j);


            veritabanı.RezervasyonEkle(rezervasyon);
            Toast.makeText(getApplicationContext(),"İşleminiz Gerçekleştirilmiştir.",Toast.LENGTH_LONG).show();
            etIsimSoyisim.setText(null);
            etTelefon.setText(null);
        }

        else
            Toast.makeText(getApplicationContext(),"Lütfen Gerekli Tüm Bilgileri Doldurunuz...",Toast.LENGTH_LONG).show();
    }

    //Yapılmış Tüm rezervasyonlar görüntülenir.
    public void goruntule(View view)
    {
        Intent intent = new Intent();

        intent.setClass(getApplicationContext(),TumKayitlarActivity.class);
        startActivity(intent);
    }



}//RezervasyonActivity END
