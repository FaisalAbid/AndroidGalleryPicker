package labs.leandog.tests;

import static com.xtremelabs.robolectric.Robolectric.shadowOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import labs.leandog.LibraryTestRunner;
import labs.leandog.gallery.picker.GalleryPicker;
import labs.leandog.gallery.picker.R;

import org.junit.Test;
import org.junit.runner.RunWith;

import android.app.Dialog;

import com.xtremelabs.robolectric.shadows.ShadowAlertDialog;
import com.xtremelabs.robolectric.shadows.ShadowDialog;
import com.xtremelabs.robolectric.shadows.ShadowAlertDialog.ShadowBuilder;

@RunWith(LibraryTestRunner.class)
public class GalleryPickerTest {

    GalleryPicker activity = new GalleryPicker();

    @Test
    public void it_should_ask_user_to_pick_from_camera_or_gallery_when_shown() {
        createActivityLifeCycle(activity);
        String expectedTitle = activity.getString(R.string.get_media_from);
        ShadowDialog shadowDialog = getLatestDialog();
        assertThat(shadowDialog.getTitle().toString(), equalTo(expectedTitle));
    }
    
    @Test
    public void it_should_offer_camera_as_first_option() {
        createActivityLifeCycle(activity);
        String firstItem = getItemFromMediaTypeDialog(0);
        assertThat(firstItem, equalTo(activity.getString(R.string.camera)));
    }

    private String getItemFromMediaTypeDialog(int position) {
        String firstItem = (String) ShadowAlertDialog.getLatestAlertDialog().getListView().getItemAtPosition(position);
        return firstItem;
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
