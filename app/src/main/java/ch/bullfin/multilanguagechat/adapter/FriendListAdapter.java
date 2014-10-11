package ch.bullfin.multilanguagechat.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import ch.bullfin.multilanguagechat.R;
import ch.bullfin.multilanguagechat.model.User;

/**
 * Created by sreejith on 11/10/14.
 */
public class FriendListAdapter extends BaseAdapter {
    Context mContext;
    private ArrayList<User> mFriends;

    public FriendListAdapter(Context context) {
        this.mContext = context;
        mFriends = new ArrayList<User>();
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public User getItem(int position) {
        return mFriends.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.friend_list_item, null, false);

            if (convertView == null) return null;

            viewHolder = new ViewHolder();
            viewHolder.prof_pic = (ImageView) convertView.findViewById(R.id.friend_image);
            viewHolder.name = (TextView) convertView.findViewById(R.id.friend_name);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

//        Fill data to List Cell
        return convertView;
    }

    private class ViewHolder {
        TextView name;
        ImageView prof_pic;
    }

    public void setValues(ArrayList<User> enquires) {
        mFriends.clear();
        mFriends.addAll(enquires);
        notifyDataSetChanged();
    }
}
