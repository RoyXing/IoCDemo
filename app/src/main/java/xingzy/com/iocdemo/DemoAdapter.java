package xingzy.com.iocdemo;

import android.widget.TextView;

import com.xingzy.recyclerview.RViewAdapter;
import com.xingzy.recyclerview.RViewHolder;

import java.util.List;

/**
 * @author roy.xing
 * @date 2019/3/5
 */
public class DemoAdapter extends RViewAdapter<String> {


    public DemoAdapter(List<String> datas) {
        super(datas);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main_item;
    }

    @Override
    public void convert(RViewHolder holder, String s) {
        TextView textView = holder.getView(R.id.item_text);
        textView.setText(s + "");
    }
}
