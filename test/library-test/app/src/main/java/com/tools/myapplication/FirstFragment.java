package com.tools.myapplication;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.tools.m3.sms.send.SendSmsRequest;
import com.tools.myapplication.databinding.FragmentFirstBinding;

import java.util.HashMap;
import java.util.Map;

import static com.tools.myapplication.PermissionCodes.*;

public class FirstFragment extends Fragment implements ActivityLogger {

    private FragmentFirstBinding binding;

   public CoreService coreService;

    Map<String,Integer> permissionCodes = new HashMap<>();

    public FirstFragment() {
        permissionCodes.put(Manifest.permission.READ_PHONE_STATE, PHONE_STATE_READ_PERMISSION);
        permissionCodes.put(Manifest.permission.READ_SMS, READ_SMS_PERMISSION);
        permissionCodes.put(Manifest.permission.RECEIVE_SMS, RECEIVE_SMS_PERMISSION);
        permissionCodes.put(Manifest.permission.SEND_SMS, SEND_SMS_PERMISSION);

    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        coreService =new CoreService(getActivity(),this);

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    static final String TAG="FirstFragment";
    @Override
    public void logText(String msg){

        Log.i(TAG, "logText:  " + msg);
//        getActivity().runOnUiThread(() -> {
//            if(null != binding.txtarealog)
//                    binding.txtarealog.append(msg + "\n");
//        });


    }




    static int uniqueNumber =2;
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnunsubscribe.setVisibility(View.INVISIBLE);

        binding.btngetsims.setOnClickListener(view1 ->{


            executeIfPermissionExists(Manifest.permission.READ_PHONE_STATE, ()->{

                coreService.getAllSims();
            });




        } );
        binding.btnreadallmessages.setOnClickListener(view1 -> {


            executeIfPermissionExists(Manifest.permission.READ_SMS,()->{

                coreService.readAllSms();
            });





        });


        binding.btnSubscribe.setOnClickListener(view1 -> {



            executeIfPermissionExists(Manifest.permission.RECEIVE_SMS, ()->{

                coreService.subscribeForSmsReceived();
                binding.btnSubscribe.setVisibility(View.INVISIBLE);
                binding.btnunsubscribe.setVisibility(View.VISIBLE);

            });



        });
        binding.btnunsubscribe.setOnClickListener(view1 ->{
            coreService.unsubscribeForSmsReceived();
            binding.btnunsubscribe.setVisibility(View.INVISIBLE);
            binding.btnSubscribe.setVisibility(View.VISIBLE);

        } );


        //sms send +651234567 Hello my friend!
        binding.btnsendsms.setOnClickListener(view1 -> {
            executeIfPermissionExists(Manifest.permission.SEND_SMS, ()->{
                uniqueNumber++;
                coreService.sendSms(new SendSmsRequest("+651234567","Hello message" + uniqueNumber));
            });


        });



    }



    private void executeIfPermissionExists(String permission, Executor executor) {

        int requestCode =permissionCodes.get(permission);
        if (ContextCompat.checkSelfPermission(getActivity(), permission) == PackageManager.PERMISSION_DENIED) {

            // Requesting the permission
            ActivityCompat.requestPermissions(getActivity(), new String[] { permission }, requestCode);
        }
        else {
            executor.execute();
        }

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}