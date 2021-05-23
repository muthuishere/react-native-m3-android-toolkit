package com.tools.m3.sms.read;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.tools.m3.context.AppContextApi;
import com.tools.m3.shared.AppException;

import java.util.ArrayList;
import java.util.List;

public class ReadService {
    Context context;



    public static final String TAG = "ReadService";

    public ReadService(AppContextApi context) {

        this.context = context.getContext();
    }

    public final List<SmsReceivedMessage> getAllSms(){
//
//        SmsManager smsManager = SmsManager.getDefault();
//        ArrayList<SmsMessage> messages = smsManager.getAllMessagesFromIcc();
//        final int count = messages.size();
//        MatrixCursor cursor = new MatrixCursor(ICC_COLUMNS, count);
//        for (int i = 0; i < count; i++) {
//            SmsMessage message = messages.get(i);
//            if (message != null) {
//                cursor.addRow(convertIccToSms(message, i));
//            }
//        }

//

        Uri mSmsQueryUri = Uri.parse("content://sms/inbox");
        List<SmsReceivedMessage> messages = new ArrayList<>();
        Cursor cursor = null;



        cursor = context.getContentResolver().query(mSmsQueryUri, null, null, null, null);
        if (cursor == null) {
            Log.i(null, "cursor is null. uri: " + mSmsQueryUri);
            throw new AppException("cursor is null. uri: " + mSmsQueryUri);
        }

        for (boolean hasData = cursor.moveToFirst(); hasData; hasData = cursor.moveToNext()) {

            messages.add(SmsReceivedMessage.createWith(cursor));
        }

        return messages;


    }



}
