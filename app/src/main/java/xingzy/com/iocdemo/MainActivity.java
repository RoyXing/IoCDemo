package xingzy.com.iocdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.xingzy.InjectManager;
import com.xingzy.annotation.ContentView;
import com.xingzy.annotation.OnClick;
import com.xingzy.annotation.OnItemClick;
import com.xingzy.annotation.ViewInject;
import com.xingzy.recyclerview.RView;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @ViewInject(R.id.btn1)
    Button button;
    @ViewInject(R.id.list_view)
    RView rView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InjectManager.inject(this);

        button.setText("aaaaaaaaa");
        DemoAdapter demoAdapter = new DemoAdapter(getDatas());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rView.setRvAdapter(demoAdapter);
        rView.setLayoutManager(linearLayoutManager);
        rView.setAdapter(demoAdapter);
        InjectManager.injectEvents(this);
    }

    @OnClick({R.id.btn1})
    public void show(View view) {
        startActivity(new Intent(this, FragmentContainerActivity.class));
    }

    public List<String> getDatas() {
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add("这是一条测试数据" + i);
        }
        return data;
    }

    @OnItemClick(R.id.list_view)
    public void onItemClick(View view, String s, int position) {
        Toast.makeText(this, "点击了onItemClick", Toast.LENGTH_SHORT).show();
    }

    @OnItemClick(R.id.list_view)
    public boolean onItemLongClick(View view, String s, int position) {
        Toast.makeText(this, "点击了onItemLongClick", Toast.LENGTH_SHORT).show();
        return false;
    }
}
