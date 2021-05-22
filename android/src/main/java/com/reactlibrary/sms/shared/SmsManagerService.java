package com.reactlibrary.sms.shared;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.SmsManager;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;

import androidx.core.app.ActivityCompat;

import com.reactlibrary.shared.AppException;
import com.reactlibrary.context.AppContextApi;

import java.util.ArrayList;
import java.util.List;

public class SmsManagerService {
    List<SmsManager> smsManagers = new ArrayList<>();



    Context context;
    public SmsManagerService(AppContextApi reactContextApi) {

        this.context = reactContextApi.getContext();

    }


    public void initSmsManagers() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            // TODO: Consider calling
            SubscriptionManager localSubscriptionManager = SubscriptionManager.from(context);
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                throw new AppException("No Permission for sending SMS");

            }

            List localList = localSubscriptionManager.getActiveSubscriptionInfoList();
            for (int i = 0; i < localList.size(); i++) {
                SubscriptionInfo selectedSIM = (SubscriptionInfo) localList.get(i);
                smsManagers.add(SmsManager.getSmsManagerForSubscriptionId(selectedSIM.getSubscriptionId()));
            }


        } else {
            smsManagers.add(SmsManager.getDefault());
        }

    }

    public SmsManager getAt(int index) {

        if (index >= smsManagers.size() )
            throw new AppException("Phone Does not have that many SIM slots or device not supported");


        return smsManagers.get(index);

    }
}



