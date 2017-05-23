package t.g.halisaharezervasyon;

/**
 * Created by AsuS on 19.05.2017.
 */

//Bu tabloda rezervasyon yapan kullanıcının bilgileri tututluyor.

public class Kullanici {
    int Id;
    String IsimSoyisim;
    String Telefon;

    public Kullanici() {
    }

    public Kullanici(String isimSoyisim, String telefon) {
        IsimSoyisim = isimSoyisim;
        Telefon = telefon;
    }

    public Kullanici(int id, String isimSoyisim, String telefon) {
        Id = id;
        IsimSoyisim = isimSoyisim;
        Telefon = telefon;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getIsimSoyisim() {
        return IsimSoyisim;
    }

    public void setIsimSoyisim(String isimSoyisim) {
        IsimSoyisim = isimSoyisim;
    }

    public String getTelefon() {
        return Telefon;
    }

    public void setTelefon(String telefon) {
        Telefon = telefon;
    }
}
