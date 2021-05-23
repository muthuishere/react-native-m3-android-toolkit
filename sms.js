import { NativeModules } from 'react-native';



const { M3AndroidToolkit } = NativeModules;

module.exports.M3AndroidToolkit =M3AndroidToolkit

module.exports.sendSms =(data)=>{


    return new Promise((resolve, reject) => {

        M3AndroidToolkit.sendSms(data,resolve,reject)
    });

}

module.exports.getAllSims =()=>{


    return new Promise((resolve, reject) => {

        M3AndroidToolkit.getAllSims(resolve,reject)
    });

}

/*

    @Override
    public String getName() {
        return "M3AndroidToolkit";
    }

    @ReactMethod
    public void sampleMethod(String stringArgument, int numberArgument, Callback callback) {
        // TODO: Implement some actually useful functionality
        callback.invoke("Received numberArgument: " + numberArgument + " stringArgument: " + stringArgument);
    }

    @ReactMethod
    public void sendSms(ReadableMap data,Callback success,Callback error){
        try {
            SendSmsResult sendSmsResult= sendService.sendSms(createFrom(data));
            success.invoke(sendSmsResult);
        }catch (AppException e){
            error.invoke(e.getMessage());
        }
    }

    public static SendSmsRequest createFrom(ReadableMap data) {

        SendSmsRequest sendSmsRequest=new SendSmsRequest();
        sendSmsRequest.setMessage(data.getString("message"));
        sendSmsRequest.setMobileNumber(data.getString("mobileNumber"));
        sendSmsRequest.setSimIndex(data.getInt("simIndex"));
        return sendSmsRequest;
    }

    @ReactMethod
    public void subscribeForSmsReceived(Callback success,Callback error){
        try {
            String sendSmsResult= smsOnReceiveService.subscribe();
            success.invoke(sendSmsResult);
        }catch (AppException e){
            error.invoke(e.getMessage());
        }
    }
    @ReactMethod
    public void unsubscribeForSmsReceived(Callback success,Callback error){
        try {
            String response= smsOnReceiveService.unsubscribe();
            success.invoke(response);
        }catch (AppException e){
            error.invoke(e.getMessage());
        }
    }

    @ReactMethod
    public void readAllSms(Callback success,Callback error){
        try {
            List<SmsReceivedMessage> response= readService.getAllSms();
            success.invoke(response);
        }catch (AppException e){
            error.invoke(e.getMessage());
        }
    }

    @ReactMethod
    public void getAllSims(Callback success,Callback error){
        try {
            List<SimDetail> response= simService.getAllSims();
            success.invoke(response);
        }catch (AppException e){
            error.invoke(e.getMessage());
        }
    }


}

*/