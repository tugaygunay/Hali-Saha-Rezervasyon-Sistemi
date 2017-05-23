package t.g.halisaharezervasyon;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by AsuS on 22.05.2017.
 */

public class KayitliVeriAdaptor extends BaseAdapter {
    private LayoutInflater inflater;
    private List<KayitliVeriler> veriler;

    public KayitliVeriAdaptor(Activity activity, List<KayitliVeriler> veriler) {
        this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.veriler = veriler;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView;

        rowView = inflater.inflate(R.layout.veri_visualize,null);

        TextView IsimSoyisim = (TextView) rowView.findViewById(R.id.tvIsimSoyisim);
        TextView Telefon = (TextView) rowView.findViewById(R.id.tvTelefon);
        TextView Tarih = (TextView) rowView.findViewById(R.id.tvTarih);
        TextView SahaIsmi = (TextView) rowView.findViewById(R.id.tvSahaIsmi);

        KayitliVeriler kayitliVeri = veriler.get(position);

        IsimSoyisim.setText(kayitliVeri.getKullainiciIsmi());
        Telefon.setText(kayitliVeri.getKullaniciTelefon());
        //Tarih.setText(kayitliVeri.getGun() + "." + kayitliVeri.getSaat() );
        SahaIsmi.setText(kayitliVeri.getSahaIsmi());

        return rowView;
    }
}//KayitliVeriAdaptor END
