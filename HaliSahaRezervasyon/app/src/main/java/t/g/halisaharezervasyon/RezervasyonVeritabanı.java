package t.g.halisaharezervasyon;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by AsuS on 19.05.2017.
 */

public class RezervasyonVeritabanı extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "RezervasyonDB";


    //Saha Tablosu Tanımlamaları
    private static final String TABLE_SAHA = "Saha";
    private static final String COLUMN_SAHA_ID = "Id";
    private static final String COLUMN_SAHA_SAHAISMI = "SahaIsmi";

    //Kullanıcı Tablosu Tanımlamaları
    private static final String TABLE_KULLANICI = "Kullanici";
    private static final String COLUMN_KULLANICI_ID = "Id";
    private static final String COLUMN_KULLANICI_ISIMSOYISIM = "IsimSoyisim";
    private static final String COLUMN_KULLANICI_TELEFON = "Telefon";

    //GunSaat Tablosu Tanımlamaları
    private static final String TABLE_GUNSAAT = "GunSaat";
    private static final String COLUMN_GUNSAAT_ID = "Id";
    private static final String COLUMN_GUNSAAT_GUN = "Gun";
    private static final String COLUMN_GUNSAAT_SAAT = "Saat";

    //Rezervasyonlar Tablosu Tanımlamaları *Bu tabloda diğer 3 tablonun bileşimi tutuluyor.
    private static final String TABLE_REZERVASYONLAR = "Rezervasyonlar";
    private static final String COLUMN_REZERVASYONLAR_ID = "Id";
    private static final String COLUMN_REZERVASYONLAR_KULLANICI_ID = "KullaniciId";
    private static final String COLUMN_REZERVASYONLAR_SAHA_ID = "SahaId";
    private static final String COLUMN_REZERVASYONLAR_GUNSAAT_ID = "GunSaatId";

    public RezervasyonVeritabanı(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Tablolar Oluşturuluyor
        String Create_Table_Saha = " Create table if not exists " + TABLE_SAHA + " ( " +
                                    COLUMN_SAHA_ID + " INTEGER Primary Key Autoincrement, " +
                                    COLUMN_SAHA_SAHAISMI + " Text ); ";



            String Create_Table_Kullanici = " Create table if not exists " + TABLE_KULLANICI + " ( " +
                    COLUMN_KULLANICI_ID + " INTEGER Primary Key Autoincrement, " +
                    COLUMN_KULLANICI_ISIMSOYISIM + " Text, " +
                    COLUMN_KULLANICI_TELEFON + " Text ); ";


        String Create_Table_GunSaat = " Create table if not exists " + TABLE_GUNSAAT + " ( " +
                COLUMN_GUNSAAT_ID + " INTEGER Primary Key Autoincrement, " +
                COLUMN_GUNSAAT_GUN + " INTEGER, " +
                COLUMN_GUNSAAT_SAAT + " Text ); ";

        String Create_Table_Rezervasyonlar = " Create table if not exists " + TABLE_REZERVASYONLAR + " ( " +
                COLUMN_REZERVASYONLAR_ID + " INTEGER Primary Key Autoincrement, " +
                COLUMN_REZERVASYONLAR_KULLANICI_ID + " INTEGER, " +
                COLUMN_REZERVASYONLAR_SAHA_ID + " INTEGER, " +
                COLUMN_REZERVASYONLAR_GUNSAAT_ID + " INTEGER ); ";


        db.execSQL(Create_Table_Saha);
        db.execSQL(Create_Table_Kullanici);
        db.execSQL(Create_Table_GunSaat);
        db.execSQL(Create_Table_Rezervasyonlar);
    }//onCreate END


    //Herhangi bir tablo veritabanından kaldırılmak istendiğinde kullanmak için fonkisyon.
    public void Sil(String TableName)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DROP TABLE IF EXISTS '" + TableName + "'");
        db.close();
    }


    //Saha Tablosu İşlemleri    --------------------------------------------------------------------

    public void SahaEkle (Saha yeniSaha)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_SAHA_ID,yeniSaha.getId());
        values.put(COLUMN_SAHA_SAHAISMI,yeniSaha.getSahaIsmi());

        db.insert(TABLE_SAHA, null, values);
        db.close();
    }

    public void SahaSil(Saha silinecekSaha){

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_SAHA, COLUMN_SAHA_SAHAISMI + " = ? ", new String[] { silinecekSaha.getSahaIsmi() } );
        db.close();
    }

    public Saha SahaGetir(Integer id)
    {
        String select_command = "SELECT * FROM " + TABLE_SAHA + " WHERE " + COLUMN_SAHA_ID + " = " + id.toString();

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor =  db.rawQuery(select_command,null);

        Saha saha = null;

        if(cursor.moveToFirst()){
            String sahaismi = cursor.getString(cursor.getColumnIndex(COLUMN_SAHA_SAHAISMI));

            saha = new Saha(id,sahaismi);
        }
        cursor.close();
        db.close();

        return saha;
    }

    public ArrayList<Saha> TumSahalariGoruntule()
    {

        String Select_Komutu = " Select * From " + TABLE_SAHA;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(Select_Komutu,null);

        ArrayList<Saha> SahaListesi = new ArrayList<>();

        while (cursor.moveToNext())
        {
            int Saha_Id = cursor.getInt(cursor.getColumnIndex(COLUMN_SAHA_ID));
            String Saha_Ismi = cursor.getString(cursor.getColumnIndex(COLUMN_SAHA_SAHAISMI));

            Saha yeniSaha = new Saha(Saha_Id,Saha_Ismi);
            SahaListesi.add(yeniSaha);
        }

        cursor.close();
        db.close();

        return SahaListesi;
    }

    public void TumSahalariSil()
    {
        SQLiteDatabase db = getWritableDatabase();

        db.delete(TABLE_SAHA,null,null);
        db.close();
    }

    //Kullanıcı Tablosu İşlemleri   ----------------------------------------------------------------
    public void KullaniciEkle (Kullanici yeniKullanici)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_KULLANICI_ISIMSOYISIM,yeniKullanici.getIsimSoyisim());
        values.put(COLUMN_KULLANICI_TELEFON,yeniKullanici.getTelefon());

        db.insert(TABLE_KULLANICI, null, values);
        db.close();

    }
    public int KullaniciTabloBuyuklugu()
    {
        String select_command = "SELECT * FROM " + TABLE_KULLANICI;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor =  db.rawQuery(select_command,null);

        int i;
        i = cursor.getCount();
        cursor.close();
        db.close();
        return i;
    }

    public void KullaniciSil(Kullanici silinecekKullanici){

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_KULLANICI, COLUMN_KULLANICI_ISIMSOYISIM + " = ? ", new String[] { silinecekKullanici.getIsimSoyisim().toString() } );
        db.close();
    }

    public Kullanici KullaniciGetirIdile(Integer id)
    {
        String select_command = "SELECT * FROM " + TABLE_KULLANICI + " WHERE " + COLUMN_KULLANICI_ID + " = " + id.toString();

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor =  db.rawQuery(select_command,null);

        Kullanici kullanici = null;

        if(cursor.moveToFirst()){
            String isimsoyisim = cursor.getString(cursor.getColumnIndex(COLUMN_KULLANICI_ISIMSOYISIM));
            String telefon = cursor.getString(cursor.getColumnIndex(COLUMN_KULLANICI_TELEFON));

            kullanici = new Kullanici(id,isimsoyisim,telefon);
        }
        cursor.close();
        db.close();

        return kullanici;
    }
    public Kullanici KullaniciGetirIsimile(String isimsoyisim )
    {
        String select_command = "SELECT * FROM " + TABLE_KULLANICI + " WHERE " + COLUMN_KULLANICI_ISIMSOYISIM + " = " + isimsoyisim.toString();

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor =  db.rawQuery(select_command,null);

        Kullanici kullanici = null;

        if(cursor.moveToFirst()){
            int id = cursor.getInt(cursor.getColumnIndex(COLUMN_KULLANICI_ISIMSOYISIM));
            String telefon = cursor.getString(cursor.getColumnIndex(COLUMN_KULLANICI_TELEFON));

            kullanici = new Kullanici(id,isimsoyisim,telefon);
        }
        cursor.close();
        db.close();

        return kullanici;
    }

    public Kullanici KullaniciGetirTelefonile(String telefon)
    {
        String select_command = "SELECT * FROM " + TABLE_KULLANICI + " WHERE " + COLUMN_KULLANICI_TELEFON + " = " + telefon.toString();

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor =  db.rawQuery(select_command,null);

        Kullanici kullanici = null;

        if(cursor.moveToFirst()){
            int id = cursor.getInt(cursor.getColumnIndex(COLUMN_KULLANICI_ID));
            String isimsoyisim = cursor.getString(cursor.getColumnIndex(COLUMN_KULLANICI_ISIMSOYISIM));


            kullanici = new Kullanici(id,isimsoyisim,telefon);
        }
        cursor.close();
        db.close();

        return kullanici;
    }

    //GunSaat Tablosu İşlemleri --------------------------------------------------------------------
    public int GunSaatEkle (GunSaat yeniVeri)
    {
        String select_command = "SELECT * FROM " + TABLE_GUNSAAT ;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor =  db.rawQuery(select_command,null);

        int i = cursor.getCount();
        i++;

        ContentValues values = new ContentValues();
        values.put(COLUMN_GUNSAAT_ID,i);
        values.put(COLUMN_GUNSAAT_GUN,yeniVeri.getGun());
        values.put(COLUMN_GUNSAAT_SAAT,yeniVeri.getSaat());

        db.insert(TABLE_GUNSAAT, null, values);
        cursor.close();
        db.close();

        return i;
    }

    public GunSaat GunSaatGetir(Integer id)
    {
        String select_command = "SELECT * FROM " + TABLE_GUNSAAT + " WHERE " + COLUMN_GUNSAAT_ID + " = " + id.toString();

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor =  db.rawQuery(select_command,null);

        GunSaat gunSaat = null;

        if(cursor.moveToFirst()){

            int gun = cursor.getInt(cursor.getColumnIndex(COLUMN_GUNSAAT_GUN));
            String saat = cursor.getString(cursor.getColumnIndex(COLUMN_GUNSAAT_SAAT));

            gunSaat = new GunSaat(id,gun,saat);
        }
        cursor.close();
        db.close();

        return gunSaat;
    }
    public int GunSaatTabloBuyuklugu()
    {
        String select_command = "SELECT * FROM " + TABLE_GUNSAAT;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor =  db.rawQuery(select_command,null);

        int i;
        i = cursor.getCount();
        cursor.close();
        db.close();
        return i;
    }

    //Rezervasyonlar Tablosu İşlemleri  ------------------------------------------------------------
    public void RezervasyonEkle (Rezervasyonlar yeniVeri)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_REZERVASYONLAR_KULLANICI_ID,yeniVeri.getKullaniciId());
        values.put(COLUMN_REZERVASYONLAR_SAHA_ID,yeniVeri.getSahaId());
        values.put(COLUMN_REZERVASYONLAR_GUNSAAT_ID,yeniVeri.getGunSaatId());

        db.insert(TABLE_REZERVASYONLAR, null, values);
        db.close();
    }
    public Rezervasyonlar RezervasyonGetir(Integer id)
    {
        String select_command = "SELECT * FROM " + TABLE_REZERVASYONLAR + " WHERE " + COLUMN_REZERVASYONLAR_ID + " = " + id.toString();

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor =  db.rawQuery(select_command,null);

        Rezervasyonlar rezervasyon = null;

        if(cursor.moveToFirst()){


            int kullaniciId = cursor.getInt(cursor.getColumnIndex(COLUMN_REZERVASYONLAR_KULLANICI_ID));
            int SahaId = cursor.getInt(cursor.getColumnIndex(COLUMN_REZERVASYONLAR_SAHA_ID));
            int GunsaatId = cursor.getInt(cursor.getColumnIndex(COLUMN_REZERVASYONLAR_GUNSAAT_ID));

            rezervasyon = new Rezervasyonlar(id,kullaniciId,SahaId,GunsaatId);
        }
        cursor.close();
        db.close();

        return rezervasyon;
    }
    public int RezervasyonTabloBuyuklugu()
    {
        String select_command = "SELECT * FROM " + TABLE_REZERVASYONLAR;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor =  db.rawQuery(select_command,null);

        int i;
        i = cursor.getCount();
        cursor.close();
        db.close();
        return i;
    }


    //Veritabındaki Tüm Kayıtlı verileri görüntülemek için. Çalışmadı Sil..
    public ArrayList<KayitliVeriler> TumVerileriGoruntule(){

        //SELECT * FROM TABLE_NAME
        String select_all_command = " SELECT * FROM " + TABLE_REZERVASYONLAR;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(select_all_command,null);

        ArrayList<KayitliVeriler> verilistesi = new ArrayList<>();

        while(cursor.moveToNext()){

            int kullaniciId = cursor.getInt(cursor.getColumnIndex(COLUMN_REZERVASYONLAR_KULLANICI_ID));
            int sahaId = cursor.getInt(cursor.getColumnIndex(COLUMN_REZERVASYONLAR_SAHA_ID));
            int gunsaatId = cursor.getInt(cursor.getColumnIndex(COLUMN_REZERVASYONLAR_GUNSAAT_ID));

            Kullanici kullanici = KullaniciGetirIdile(1);
            Saha saha = SahaGetir(sahaId);
            GunSaat gunsaat = GunSaatGetir(1);


            KayitliVeriler kayitliveri = new KayitliVeriler(kullanici.getIsimSoyisim(),kullanici.getTelefon(),saha.getSahaIsmi(),gunsaat.getGun(),gunsaat.getSaat());

            verilistesi.add(kayitliveri);
        }
        cursor.close();
        db.close();

        return verilistesi;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}//RezervasyonVeritabanı END
