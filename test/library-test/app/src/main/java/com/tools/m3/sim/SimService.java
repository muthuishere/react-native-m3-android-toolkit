package com.tools.m3.sim;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;

import androidx.core.app.ActivityCompat;

import com.tools.m3.shared.AppException;
import com.tools.m3.context.AppContextApi;
import com.tools.m3.sms.shared.SmsManagerService;

import java.util.ArrayList;
import java.util.List;

public class SimService {

    private BroadcastReceiver msgReceiver;
    private final Context context;
    SmsManagerService smsManagerService;


    public SimService(AppContextApi reactContextApi) {
        context =reactContextApi.getContext();
        smsManagerService= new SmsManagerService(reactContextApi);
    }


    public List<SimDetail> getAllSims(){
        List<SimDetail> results = new ArrayList<>();

        try {

            smsManagerService.initSmsManagers();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
                SubscriptionManager localSubscriptionManager = SubscriptionManager.from(context);
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    throw new AppException("No Permission for reading phone state");

                }
                // if (localSubscriptionManager.getActiveSubscriptionInfoCount() > 1) {

                List<SubscriptionInfo> activeSubscriptionInfoList = localSubscriptionManager.getActiveSubscriptionInfoList();


                for(int i =0;i<activeSubscriptionInfoList.size();i++){

                    SubscriptionInfo subscriptionInfo= activeSubscriptionInfoList.get(i);
                    results.add(new SimDetail(subscriptionInfo,smsManagerService.getAt(i)));
                }






                //  }
            }else{
                throw new AppException("cannot read sim state as version is low");

            }


        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException("Error" + e.getMessage());
        }

        return results;
    }
}
