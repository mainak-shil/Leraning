package com.rp.learning.day1.adapter;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import com.rp.learning.R;
import com.rp.learning.day1.model.Message;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final String TAG = MessageAdapter.class.getSimpleName();

    private List<Message> messageList;

    public static final int LEFT_SIDE = 0;
    public static final int RIGHT_SIDE = 1;

    public MessageAdapter(List<Message> messageList) {
        this.messageList = messageList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == RIGHT_SIDE) {
            return new RightHolder(
                    LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.recycler_right, parent, false)
            );
        } else {
            return new LeftHolder(
                    LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.recycler_left, parent, false)
            );
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Log.e(TAG, "onBindViewHolder: " + position);

        if (holder instanceof RightHolder) {
            onBindViewHolder((RightHolder) holder, position);
        } else {
            onBindViewHolder((LeftHolder) holder, position);
        }

    }

    public void onBindViewHolder(@NonNull RightHolder holder, int position) {
        Message message = messageList.get(position);
        holder.showMessage(message.getMessgae());
    }

    public void onBindViewHolder(@NonNull LeftHolder holder, int position) {
        Message message = messageList.get(position);
        holder.showMessage(message.getMessgae());
    }

    public void addMessage(String message) {
        if (TextUtils.isEmpty(message))
            return;

        int currentSize = messageList.size();

        Message msg = new Message();
        msg.setPos(getItemCount());
        msg.setMessgae(message);

        messageList.add(msg);

        //notifyDataSetChanged();
        notifyItemInserted(currentSize);
    }

    public void removeMessage(int position) {
        messageList.remove(position);
        notifyItemRemoved(position);
//        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return messageList != null ? messageList.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        Message message = messageList.get(position);

        if (message.getPos() % 2 == 0)
            return RIGHT_SIDE;

        return LEFT_SIDE;
    }

    public class LeftHolder extends RecyclerView.ViewHolder {

        private AppCompatTextView tvMessage;

        public LeftHolder(@NonNull View itemView) {
            super(itemView);

            tvMessage = itemView.findViewById(R.id.tvMessage);
            tvMessage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeMessage(getAdapterPosition());
                }
            });
        }

        public void showMessage(String message) {
            tvMessage.setText(message);
        }
    }

    public class RightHolder extends RecyclerView.ViewHolder {

        private AppCompatTextView tvMessage;

        public RightHolder(@NonNull View itemView) {
            super(itemView);

            tvMessage = itemView.findViewById(R.id.tvMessage);
            tvMessage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeMessage(getAdapterPosition());
                }
            });
        }

        public void showMessage(String message) {
            tvMessage.setText(message);
        }
    }
}
