package ch.bullfin.multilanguagechat.adapter;

import android.content.Context;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import ch.bullfin.multilanguagechat.R;
import ch.bullfin.multilanguagechat.model.Chat;

/**
 * Created by meera on 11/10/14.
 */
public class ChatListAdapter extends BaseAdapter {

    Context mContext;
    private ArrayList<Chat> mchats;

    public ChatListAdapter(Context context) {
        this.mContext = context;
        mchats = new ArrayList<Chat>();
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Chat getItem(int position) {
        return mchats.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.chat_list_item, null, false);

            if (convertView == null) return null;

            viewHolder = new ViewHolder();
            viewHolder.prof_pic = (ImageView) convertView.findViewById(R.id.friend_image);
            viewHolder.name = (TextView) convertView.findViewById(R.id.friend_name);
            viewHolder.time = (TextView) convertView.findViewById(R.id.time);
            viewHolder.chatmsg = (TextView) convertView.findViewById(R.id.msg);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
            if (viewHolder != null) {
                viewHolder.name.setText("Meera");
                viewHolder.time.setText("7.00pm");
                viewHolder.chatmsg.setText("hello");
            }
//        Fill data to List Cell
        return convertView;
    }

    private class ViewHolder {
        TextView name;
        ImageView prof_pic;
        TextView time;
        TextView chatmsg;
    }

    public void setValues(ArrayList<Chat> enquires) {
        mchats.clear();
        mchats.addAll(enquires);
        notifyDataSetChanged();
    }
}
