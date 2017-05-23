package t.g.halisaharezervasyon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class TumKayitlarActivity extends AppCompatActivity {
    ListView Kayitlar;
    RezervasyonVeritabanı veritabanı;
    ArrayList<KayitliVeriler> kayitliVeriler = new ArrayList<>();
    KayitliVeriAdaptor veriAdaptor;
    ArrayList<String> mylist = new ArrayList<String>();
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tum_kayitlar);
        this.veritabanı = new RezervasyonVeritabanı(getApplicationContext());
        Kayitlar = (ListView) findViewById(R.id.lvKayitlar);

        int j = veritabanı.RezervasyonTabloBuyuklugu();

        //ListView görüntüleme işlemi
        for(int i=1;i<=j;i++)
        {
            Rezervasyonlar rezervasyon = veritabanı.RezervasyonGetir(i);
            Kullanici kullanici = veritabanı.KullaniciGetirIdile(rezervasyon.getKullaniciId());
            Saha saha = veritabanı.SahaGetir(rezervasyon.getSahaId());
            GunSaat gunsaat = veritabanı.GunSaatGetir(rezervasyon.getGunSaatId());

            mylist.add(kullanici.getIsimSoyisim() + " " + kullanici.getTelefon() + " "
                        + saha.getSahaIsmi() + " " + " " + String.valueOf(gunsaat.getGun()) + " " + gunsaat.getSaat());
        }
        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,mylist);
        Kayitlar.setAdapter(arrayAdapter);

    }//onCreate END

}//TumKayitlarActivity END
