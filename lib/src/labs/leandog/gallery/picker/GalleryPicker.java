package labs.leandog.gallery.picker;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

@SuppressWarnings("deprecation")
public class GalleryPicker extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showDialog(R.id.dialog_id_media_source);
    }
    
    @Override
    protected Dialog onCreateDialog(int id) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Get Media From:");
        return dialogBuilder.create();
    }

}
