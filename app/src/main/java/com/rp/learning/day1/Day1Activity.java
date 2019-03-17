package com.rp.learning.day1;

import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.button.MaterialButton;
import com.rp.learning.R;
import com.rp.learning.day1.adapter.MessageAdapter;
import com.rp.learning.day1.model.Message;

import java.util.ArrayList;
import java.util.List;

public class Day1Activity extends AppCompatActivity {

    private RecyclerView recyclerMessages;
    private AppCompatEditText etMessage;
    private MessageAdapter messageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day1);

        recyclerMessages = findViewById(R.id.recyclerMessages);
        recyclerMessages.setHasFixedSize(true);
        recyclerMessages.setLayoutManager(new LinearLayoutManager(this));

        etMessage = findViewById(R.id.etMessage);

        List<Message> messageList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Message message = new Message();
            message.setPos(i);
            message.setMessgae("Text " + i);
            messageList.add(message);
        }

        messageAdapter = new MessageAdapter(messageList);
        recyclerMessages.setAdapter(messageAdapter);

        findViewById(R.id.btnSend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messageAdapter.addMessage(etMessage.getText().toString());
                etMessage.setText(null);
            }
        });


    }
}
