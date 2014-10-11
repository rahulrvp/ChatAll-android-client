package ch.bullfin.multilanguagechat.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

import ch.bullfin.multilanguagechat.R;
import ch.bullfin.multilanguagechat.async.TranslatorTask;
import ch.bullfin.multilanguagechat.model.Message;

/**
 * Created by root on 11/10/14.
 */
public class ChatDetailsAdapter extends BaseAdapter {

    private ArrayList<Message> messages;
    private Context context;

    public ChatDetailsAdapter(Context context) {
        this.context = context;
        this.messages = new ArrayList<Message>();
    }

    public void updateMessages(Message[] messages) {
        Collections.addAll(this.messages, messages);
    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Object getItem(int position) {
        return messages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return messages.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_chat_message_item, null, false);

            viewHolder = new ViewHolder();
            viewHolder.senderNameText = (TextView) convertView.findViewById(R.id.message_sender_name);
            viewHolder.messageText = (TextView) convertView.findViewById(R.id.message_text);
            viewHolder.sendingTimeText = (TextView) convertView.findViewById(R.id.message_time);

            convertView.setTag(viewHolder);
        }

        viewHolder = (ViewHolder) convertView.getTag();
        Message message = (Message) getItem(position);
        if (viewHolder != null && message != null) {
            if (viewHolder.senderNameText != null) {
                viewHolder.senderNameText.setText(message.getSender().getName());
            }

            if (viewHolder.messageText != null) {
                final ViewHolder finalViewHolder = viewHolder;
                TranslatorTask.TranslationCallback callback = new TranslatorTask.TranslationCallback() {
                    @Override
                    public void onTranslationCompleted(String message) {
                        finalViewHolder.messageText.setText(message);
                    }

                    @Override
                    public void onTranslationFailed() {

                    }
                };

                new TranslatorTask(message.getLanguage_code(),
                        "hi",
                        message.getText(),
                        callback).execute();
            }

            if (viewHolder.sendingTimeText != null) {
                viewHolder.sendingTimeText.setText("11:30pm");
            }
        }

        return convertView;
    }

    public class ViewHolder {
        TextView senderNameText;
        TextView messageText;
        TextView sendingTimeText;
    }
}
