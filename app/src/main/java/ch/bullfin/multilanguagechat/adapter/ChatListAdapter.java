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
import java.util.Collections;

import ch.bullfin.multilanguagechat.R;
import ch.bullfin.multilanguagechat.model.Chat;
import ch.bullfin.multilanguagechat.model.Message;
import ch.bullfin.multilanguagechat.model.Participant;
import ch.bullfin.multilanguagechat.model.User;

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
        return mchats.size();
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
                Chat chat = (Chat) getItem(position);
                if (chat !=null) {
                    Message[] message = chat.getMessage_texts();
                    Participant[] participants = chat.getParticipants();
                    for (int i = 0; i <participants.length ; i++) {
                        if (participants[i].getUser().getId() != User.getInstance(mContext).getId()){
                            if (viewHolder.name != null) {
                                viewHolder.name.setText(participants[i].getUser().getName());
                            }
                        }
                    }

                    if (viewHolder.time != null) {
                        viewHolder.time.setText("");
                    }
                    if (viewHolder.chatmsg != null && message != null) {
                        viewHolder.chatmsg.setText(message[(message.length)-1].getText());
                    }
                }
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

    public void setValues(Chat[] chats) {
        mchats.clear();
        Collections.addAll(mchats, chats);
        notifyDataSetChanged();
    }
}
