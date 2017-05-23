package t.g.halisaharezervasyon;

/**
 * Created by AsuS on 19.05.2017.
 */

public class Saha {
    int id;
    String SahaIsmi;

    public Saha() {
    }

    public Saha(String sahaIsmi) {
        SahaIsmi = sahaIsmi;
    }

    public Saha(int id, String sahaIsmi) {
        this.id = id;
        SahaIsmi = sahaIsmi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSahaIsmi() {
        return SahaIsmi;
    }

    public void setSahaIsmi(String sahaIsmi) {
        SahaIsmi = sahaIsmi;
    }
}//Saha END
