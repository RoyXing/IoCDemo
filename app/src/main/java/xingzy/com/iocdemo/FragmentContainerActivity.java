package xingzy.com.iocdemo;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class FragmentContainerActivity extends AppCompatActivity {

    private static final String TAG = FragmentContainerActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_container);

        FragmentManager manager = getSupportFragmentManager();
        Fragment1 fragment = (Fragment1) manager.findFragmentByTag(TAG);
        if (fragment == null) {
            fragment = new Fragment1();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.add(R.id.fragment_root, fragment, TAG);
            transaction.commit();
        }
    }
}
