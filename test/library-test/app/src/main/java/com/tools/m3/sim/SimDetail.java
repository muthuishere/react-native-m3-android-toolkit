package com.tools.m3.sim;

import android.os.Build;
import android.telephony.SmsManager;
import android.telephony.SubscriptionInfo;

public class SimDetail {

    SubscriptionInfo subscriptionInfo;
    String tag="";
    String iccId;
    String number;
    int simSlotIndex;


    SmsManager smsManager;
    String carrier;
    String displayName;

    public SimDetail(SubscriptionInfo subscriptionInfo){

        this(subscriptionInfo,null);

    }

    public SimDetail(SubscriptionInfo subscriptionInfo, SmsManager smsManager) {

        this.subscriptionInfo=subscriptionInfo;
        this.smsManager=smsManager;

        StringBuilder sb = new StringBuilder();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            sb.append("subscriptionInfo.getCardId()   " + subscriptionInfo.getCardId()).append("\n");

            sb.append("subscriptionInfo.getMccString()   " + subscriptionInfo.getMccString()).append("\n");
            sb.append("subscriptionInfo.getSubscriptionType()   " + subscriptionInfo.getSubscriptionType()).append("\n");
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            this.iccId=subscriptionInfo.getIccId();
            number=subscriptionInfo.getNumber();
            simSlotIndex=subscriptionInfo.getSimSlotIndex();
            carrier=subscriptionInfo.getCarrierName().toString();
            displayName=subscriptionInfo.getDisplayName().toString();

            sb.append("subscriptionInfo.getIccId()   " + subscriptionInfo.getIccId()).append("\n");
            sb.append("subscriptionInfo.getNumber()   " + subscriptionInfo.getNumber()).append("\n");
            sb.append("subscriptionInfo.getSimSlotIndex()   " + subscriptionInfo.getSimSlotIndex()).append("\n");
            sb.append("subscriptionInfo.getCarrierName()   " + subscriptionInfo.getCarrierName()).append("\n");
            sb.append("subscriptionInfo.getCountryIso()   " + subscriptionInfo.getCountryIso()).append("\n");
            sb.append("subscriptionInfo.getDisplayName()   " + subscriptionInfo.getDisplayName()).append("\n");
            sb.append("subscriptionInfo.getSubscriptionId()   " + subscriptionInfo.getSubscriptionId()).append("\n");

            sb.append(appendSmsManagerDetails());

        }
        this.tag=sb.toString();
    }

    public String appendSmsManagerDetails(){
        StringBuilder sb = new StringBuilder();

        if(null != smsManager){

            sb.append("smsManager   " + smsManager.toString()).append("\n");
        }
        return sb.toString();

    }

    public SubscriptionInfo getSubscriptionInfo() {
        return subscriptionInfo;
    }

    public void setSubscriptionInfo(SubscriptionInfo subscriptionInfo) {
        this.subscriptionInfo = subscriptionInfo;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getIccId() {
        return iccId;
    }

    public void setIccId(String iccId) {
        this.iccId = iccId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getSimSlotIndex() {
        return simSlotIndex;
    }

    public void setSimSlotIndex(int simSlotIndex) {
        this.simSlotIndex = simSlotIndex;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return "SimDetail{" +
                "tag='" + tag + '\'' +
                ", iccId='" + iccId + '\'' +
                ", number='" + number + '\'' +
                ", simSlotIndex=" + simSlotIndex +
                ", carrier='" + carrier + '\'' +
                ", displayName='" + displayName + '\'' +
                '}';
    }
}
