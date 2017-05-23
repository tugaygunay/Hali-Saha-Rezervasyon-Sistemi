package t.g.halisaharezervasyon;

/**
 * Created by AsuS on 22.05.2017.
 */

//Veritabanındaki kayıtları görüntülemek için kullanılıyor.

public class KayitliVeriler {
    String KullainiciIsmi;
    String KullaniciTelefon;
    String SahaIsmi;
    int Gun;
    String Saat;

    public KayitliVeriler() {
    }

    public KayitliVeriler(String kullainiciIsmi, String kullaniciTelefon, String sahaIsmi, int gun, String saat) {
        KullainiciIsmi = kullainiciIsmi;
        KullaniciTelefon = kullaniciTelefon;
        SahaIsmi = sahaIsmi;
        Gun = gun;
        Saat = saat;
    }

    public String getKullainiciIsmi() {
        return KullainiciIsmi;
    }

    public void setKullainiciIsmi(String kullainiciIsmi) {
        KullainiciIsmi = kullainiciIsmi;
    }

    public String getKullaniciTelefon() {
        return KullaniciTelefon;
    }

    public void setKullaniciTelefon(String kullaniciTelefon) {
        KullaniciTelefon = kullaniciTelefon;
    }

    public String getSahaIsmi() {
        return SahaIsmi;
    }

    public void setSahaIsmi(String sahaIsmi) {
        SahaIsmi = sahaIsmi;
    }

    public int getGun() {
        return Gun;
    }

    public void setGun(int gun) {
        Gun = gun;
    }

    public String getSaat() {
        return Saat;
    }

    public void setSaat(String saat) {
        Saat = saat;
    }
}
