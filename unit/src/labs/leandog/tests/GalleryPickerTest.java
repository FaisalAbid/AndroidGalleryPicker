package labs.leandog.tests;

import static com.xtremelabs.robolectric.Robolectric.shadowOf;
import static labs.leandog.android.testing.matchers.ActivityCoreMatchers.*;
import static labs.leandog.android.testing.matchers.IntentCoreMatchers.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import labs.leandog.LibraryTestRunner;
import labs.leandog.android.testing.matchers.StartedActivityContainingParcealbeMatcher;
import labs.leandog.gallery.picker.GalleryPicker;
import labs.leandog.gallery.picker.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.widget.ListView;

import com.xtremelabs.robolectric.shadows.ShadowAlertDialog;
import com.xtremelabs.robolectric.shadows.ShadowDialog;

@RunWith(LibraryTestRunner.class)
public class GalleryPickerTest {

    GalleryPicker activity = new GalleryPicker();

    @Before
    public void setup() {
        createActivityLifeCycle(activity);
    }

    @Test
    public void it_should_ask_user_to_pick_from_camera_or_gallery_when_shown() {
        String expectedTitle = activity.getString(R.string.get_media_from);
        ShadowDialog shadowDialog = getLatestDialog();
        assertThat(shadowDialog.getTitle().toString(), equalTo(expectedTitle));
    }

    @Test
    public void it_should_offer_camera_as_first_option() {
        String firstItem = getItemFromMediaTypeDialog(0);
        assertThat(firstItem, equalTo(activity.getString(R.string.camera)));
    }

    @Test
    public void it_should_offer_gallery_as_second_option() {
        String firstItem = getItemFromMediaTypeDialog(1);
        assertThat(firstItem, equalTo(activity.getString(R.string.gallery)));
    }

    @Test
    public void picking_camera_should_start_camera_for_result() {
        clickOnCamera();
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        assertThat(activity, startedForResult(intent, R.id.result_code_camera));
    }
    
    @Test
    public void it_privides_the_uri_to_record_image_to() {
        clickOnCamera();
        Uri expectedValue = Uri.parse("/mnt/sdcard/pictures/2013_01_05-12-15-30-123.jpg");
        assertThat(activity, startedActivityForResultContainingParcelableExtra(MediaStore.EXTRA_OUTPUT, expectedValue));
    }

    @Test
    public void picking_gallery_should_start_gallery_for_result() {
        clickOnGallery();
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        assertThat(activity, startedForResult(intent, R.id.result_code_gallery));
    }

    private void clickOnCamera() {
        clickOnMediaTypeDialog(0);
    }

    private void clickOnGallery() {
        clickOnMediaTypeDialog(1);
    }

    private void clickOnMediaTypeDialog(int position) {
        ListView listView = ShadowAlertDialog.getLatestAlertDialog().getListView();
        listView.performItemClick(null, position, 0);
    }

    private String getItemFromMediaTypeDialog(int position) {
        return (String) ShadowAlertDialog.getLatestAlertDialog().getListView().getItemAtPosition(position);
    }

    private void createActivityLifeCycle(GalleryPicker activity) {
        shadowOf(activity).create();
    }

    private ShadowDialog getLatestDialog() {
        Dialog dialog = ShadowAlertDialog.getLatestDialog();
        ShadowDialog shadowDialog = shadowOf(dialog);
        return shadowDialog;
    }
}
