package com.example.todolist;

import android.content.Context;
import android.graphics.Paint; // Üstünü çizmek için gerekli
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat; // Renk değişimi için

import java.util.ArrayList;

public class GorevAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Gorev> liste; // DİKKAT: Artık String değil, Gorev tutuyoruz
    private LayoutInflater inflater;

    public GorevAdapter(Context context, ArrayList<Gorev> liste) {
        this.context = context;
        this.liste = liste;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() { return liste.size(); }

    @Override
    public Object getItem(int position) { return liste.get(position); }

    @Override
    public long getItemId(int position) { return position; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_gorev, parent, false);
        }

        TextView txtGorev = convertView.findViewById(R.id.txtGorevMetni);
        CheckBox checkBox = convertView.findViewById(R.id.checkboxYapildi);
        ImageButton btnSil = convertView.findViewById(R.id.btnSil);

        Gorev oAnkiGorev = liste.get(position);

        txtGorev.setText(oAnkiGorev.getMetin());

        checkBox.setOnCheckedChangeListener(null);
        checkBox.setChecked(oAnkiGorev.isTamamlandi());

        gorunumuGuncelle(txtGorev, oAnkiGorev.isTamamlandi());

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                oAnkiGorev.setTamamlandi(isChecked); // Veriyi güncelle
                gorunumuGuncelle(txtGorev, isChecked); // Görüntüyü anlık değiştir
            }
        });

        btnSil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                liste.remove(position);
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    private void gorunumuGuncelle(TextView textView, boolean tamamlandi) {
        if (tamamlandi) {
            textView.setPaintFlags(textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            textView.setTextColor(ContextCompat.getColor(context, R.color.text_secondary));
        } else {
            textView.setPaintFlags(textView.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            textView.setTextColor(ContextCompat.getColor(context, R.color.text_primary));
        }
    }
}