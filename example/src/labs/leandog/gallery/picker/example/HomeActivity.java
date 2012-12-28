package labs.leandog.gallery.picker.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import labs.leandog.gallery.picker.GalleryPicker;

public class HomeActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        findViewById(R.id.pick_image).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                pickFromGallery();
            }
        });
    }

    private void pickFromGallery() {
        Intent intent = new Intent(this, GalleryPicker.class);
        startActivityForResult(intent, R.id.result_code_pick_from_gallery);
    }
}
