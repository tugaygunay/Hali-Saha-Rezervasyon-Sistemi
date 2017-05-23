package t.g.halisaharezervasyon;

/**
 * Created by AsuS on 21.05.2017.
 */

public class Rezervasyonlar {
    int Id;
    int KullaniciId;
    int SahaId;
    int GunSaatId;

    public Rezervasyonlar() {
    }

    public Rezervasyonlar(int id, int kullaniciId, int sahaId, int gunSaatId) {
        Id = id;
        KullaniciId = kullaniciId;
        SahaId = sahaId;
        GunSaatId = gunSaatId;
    }

    public Rezervasyonlar(int kullaniciId, int sahaId, int gunSaatId) {
        KullaniciId = kullaniciId;
        SahaId = sahaId;
        GunSaatId = gunSaatId;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getKullaniciId() {
        return KullaniciId;
    }

    public void setKullaniciId(int kullaniciId) {
        KullaniciId = kullaniciId;
    }

    public int getSahaId() {
        return SahaId;
    }

    public void setSahaId(int sahaId) {
        SahaId = sahaId;
    }

    public int getGunSaatId() {
        return GunSaatId;
    }

    public void setGunSaatId(int gunSaatId) {
        GunSaatId = gunSaatId;
    }
}
