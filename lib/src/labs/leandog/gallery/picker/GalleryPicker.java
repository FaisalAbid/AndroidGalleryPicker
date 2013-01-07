package labs.leandog.gallery.picker;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.style.LineHeightSpan.WithDensity;

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
        dialogBuilder.setTitle(R.string.get_media_from);
        CharSequence[] items = getResources().getStringArray(R.array.meida_sources);
        dialogBuilder.setItems(items, new DialogOnClickListener());

        return dialogBuilder.create();
    }

    private void pickImageFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, R.id.result_code_gallery);
    }

    private void pickImageFromCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // cameraUri = mediaStoreManager.getUriToRecordPhoto();
        // intent.putExtra(MediaStore.EXTRA_OUTPUT, cameraUri);
        startActivityForResult(intent, R.id.result_code_camera);
    }

    private class DialogOnClickListener implements DialogInterface.OnClickListener {

        @Override
        public void onClick(DialogInterface dialog, int which) {
            if (which == 0)
                pickImageFromCamera();
            else if (which == 1)
                pickImageFromGallery();
        }
    }
}
