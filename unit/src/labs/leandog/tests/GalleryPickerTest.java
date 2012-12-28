package labs.leandog.tests;

import static com.xtremelabs.robolectric.Robolectric.shadowOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import labs.leandog.TestRunner;
import labs.leandog.gallery.picker.GalleryPicker;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;

import android.app.Dialog;

import com.xtremelabs.robolectric.shadows.ShadowAlertDialog;
import com.xtremelabs.robolectric.shadows.ShadowDialog;


@RunWith(TestRunner.class)
public class GalleryPickerTest {

    @Spy
    GalleryPicker activity = new GalleryPicker();

    @Test
    public void it_should_ask_user_to_pick_from_camera_or_gallery_when_shown() {
        createActivityLifeCycle(activity);
        Dialog dialog = ShadowAlertDialog.getLatestDialog();
        ShadowDialog shadowDialog = shadowOf(dialog);
        assertThat(shadowDialog.getTitle().toString(), equalTo("Get Media From:"));
    }

    private void createActivityLifeCycle(GalleryPicker activity) {
        shadowOf(activity).create();
    }
}
