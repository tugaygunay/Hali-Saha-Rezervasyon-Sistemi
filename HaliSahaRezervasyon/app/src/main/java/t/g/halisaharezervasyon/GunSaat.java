package t.g.halisaharezervasyon;

/**
 * Created by AsuS on 19.05.2017.
 */

//Bu tabloda rezervasyon işleminin gün ve saat bilgisi tutuluyor.

public class GunSaat {
    int Id;
    int Gun;
    String Saat;

    public GunSaat() {
    }

    public GunSaat(int id, int gun, String saat) {
        Id = id;
        Gun = gun;
        Saat = saat;
    }

    public GunSaat(int gun, String saat) {
        Gun = gun;
        Saat = saat;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
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
