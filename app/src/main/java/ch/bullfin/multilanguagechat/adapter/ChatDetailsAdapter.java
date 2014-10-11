package ch.bullfin.multilanguagechat.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import ch.bullfin.multilanguagechat.R;
import ch.bullfin.multilanguagechat.async.TranslatorTask;

/**
 * Created by root on 11/10/14.
 */
public class ChatDetailsAdapter extends BaseAdapter {

    private Context context;

    public ChatDetailsAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
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
        if (viewHolder != null) {
            if (viewHolder.senderNameText != null) {
                viewHolder.senderNameText.setText("Suresh Babu");
            }

            if (viewHolder.messageText != null) {
                final ViewHolder finalViewHolder = viewHolder;
                new TranslatorTask("en",
                        "hi",
                        "Hi Suresh, this is the message for you.",
                        new TranslatorTask.TranslationCallback() {
                            @Override
                            public void onTranslationCompleted(String message) {
                                finalViewHolder.messageText.setText(message);
                            }

                            @Override
                            public void onTranslationFailed() {

                            }
                        }).execute();
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
