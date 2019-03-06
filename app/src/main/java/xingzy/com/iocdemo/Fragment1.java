package xingzy.com.iocdemo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.xingzy.InjectManager;
import com.xingzy.annotation.OnClick;
import com.xingzy.annotation.ViewInject;

/**
 * @author roy.xing
 * @date 2019/3/6
 */
public class Fragment1 extends Fragment {

    @ViewInject(R.id.text01)
    TextView textView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout1, container, false);
        InjectManager.inject(this, view);

        textView.setText("2222222222222");
        return view;
    }

    @OnClick(R.id.text01)
    public void show(View view){
        Toast.makeText(getContext(), "点击了", Toast.LENGTH_SHORT).show();
    }
}
