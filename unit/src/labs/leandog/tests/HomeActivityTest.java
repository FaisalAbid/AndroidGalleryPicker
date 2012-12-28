package labs.leandog.tests;

import static com.xtremelabs.robolectric.Robolectric.shadowOf;
import static labs.leandog.android.testing.matchers.ActivityCoreMatchers.startedForResult;
import static org.junit.Assert.assertThat;
import labs.leandog.ExampleTestRunner;
import labs.leandog.gallery.picker.GalleryPicker;
import labs.leandog.gallery.picker.example.HomeActivity;
import labs.leandog.gallery.picker.example.R;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(ExampleTestRunner.class)
public class HomeActivityTest {

    @Test
    public void it_launches_gallery_picker_when_requested() {
        HomeActivity activity = new HomeActivity();
        startActivity(activity);
        activity.findViewById(R.id.pick_image).performClick();
        assertThat(activity, startedForResult(GalleryPicker.class, R.id.result_code_pick_from_gallery));
    }

    private void startActivity(HomeActivity activity) {
        shadowOf(activity).create();
    }
}
