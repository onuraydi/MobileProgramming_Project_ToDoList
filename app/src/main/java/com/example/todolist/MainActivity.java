package com.example.todolist;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
// Toast kütüphanesini ekledik
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Gorev> gorevListesi;
    GorevAdapter customAdapter;
    ListView listView;
    FloatingActionButton fabEkle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listViewGorevler);
        fabEkle = findViewById(R.id.fabEkle);

        gorevListesi = new ArrayList<>();
        gorevListesi.add(new Gorev("Markete git", false));
        gorevListesi.add(new Gorev("Java çalış", true));

        customAdapter = new GorevAdapter(this, gorevListesi);
        listView.setAdapter(customAdapter);

        fabEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yeniGorevDialog();
            }
        });
    }

    private void yeniGorevDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Yeni Görev");

        android.widget.FrameLayout container = new android.widget.FrameLayout(this);
        android.widget.FrameLayout.LayoutParams params = new  android.widget.FrameLayout.LayoutParams(
                android.view.ViewGroup.LayoutParams.MATCH_PARENT,
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT
        );
        params.leftMargin = 60;
        params.rightMargin = 60;

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        input.setHint("Örn: Kitap oku...");
        input.setBackgroundResource(R.drawable.bg_input_rounded);
        input.setPadding(50, 40, 50, 40);
        input.setLayoutParams(params);
        input.setTextColor(android.graphics.Color.parseColor("#263238"));
        input.setHintTextColor(android.graphics.Color.parseColor("#90A4AE"));

        container.addView(input);
        builder.setView(container);

        builder.setPositiveButton("EKLE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String metin = input.getText().toString();

                // Eğer metin boş değilse ekle ve Başarılı mesajı ver
                if (!metin.isEmpty()) {
                    gorevListesi.add(new Gorev(metin, false));
                    customAdapter.notifyDataSetChanged();

                    // --- BAŞARILI TOAST MESAJI ---
                    Toast.makeText(MainActivity.this, "Yeni görev eklendi!", Toast.LENGTH_SHORT).show();

                } else {
                    // --- UYARI TOAST MESAJI (Boş ise) ---
                    Toast.makeText(MainActivity.this, "Lütfen bir görev yazın!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setNegativeButton("İPTAL", (dialog, which) -> dialog.cancel());

        AlertDialog dialog = builder.create();
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.primary_blue));
        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(android.graphics.Color.GRAY);
    }
}