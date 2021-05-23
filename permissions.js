
import {
    NativeEventEmitter,
    NativeModules,
    PermissionsAndroid,
    Platform,
} from 'react-native';

const hasPermissions = async (permissions) => {
    if (Platform.OS === 'android' && Platform.Version < 23) {
        return true;
    }



    const promises = permissions.map(async currentPermission => {
        const result =  await PermissionsAndroid.check(
            currentPermission
        );
        return result
    })

    const allResults = await Promise.all(promises)

    return allResults
        .reduce((previousValue, currentValue) => previousValue && currentValue)


};

export async function requestPermissionsForSendSms() {
    return requestPermissions([
        PermissionsAndroid.PERMISSIONS.READ_PHONE_STATE,
        PermissionsAndroid.PERMISSIONS.SEND_SMS]);
}

export async function requestPermissionsForReceiveSms() {

    let allPermissions = [
        PermissionsAndroid.PERMISSIONS.RECEIVE_SMS,
        PermissionsAndroid.PERMISSIONS.READ_SMS,
        PermissionsAndroid.PERMISSIONS.READ_PHONE_STATE
    ];

    return requestPermissions(allPermissions);
}

export async function requestPermissionsForGetAllSims() {

    let allPermissions = [
        PermissionsAndroid.PERMISSIONS.READ_PHONE_STATE
    ];

    return requestPermissions(allPermissions);
}

export async function requestPermissionsForReadAllMessages() {

    let allPermissions = [
        PermissionsAndroid.PERMISSIONS.READ_PHONE_STATE,
        PermissionsAndroid.PERMISSIONS.RECEIVE_SMS,
        PermissionsAndroid.PERMISSIONS.READ_SMS,
    ];

    return requestPermissions(allPermissions);
}


export async function requestPermissions(allPermissions) {


    if (Platform.OS === 'android') {
        const hasPermission = await hasPermissions(allPermissions);
        if (hasPermission) {
            return true;
        }


        // let allPermissions = [
        //     PermissionsAndroid.PERMISSIONS.RECEIVE_SMS,
        //     PermissionsAndroid.PERMISSIONS.SEND_SMS,
        //     PermissionsAndroid.PERMISSIONS.READ_SMS,
        //     PermissionsAndroid.PERMISSIONS.READ_PHONE_STATE,
        // ];
        const statuses = await PermissionsAndroid.requestMultiple(allPermissions);


        const grantedItems =  allPermissions
            .filter(value => statuses[value] === PermissionsAndroid.RESULTS.GRANTED)


        if (grantedItems.length === allPermissions.length) {
            return true;
        }

        const deniedItems =  allPermissions
            .filter(value => statuses[value] === PermissionsAndroid.RESULTS.DENIED)

        const permanentlyDeniedItems =  allPermissions
            .filter(value => statuses[value] === PermissionsAndroid.RESULTS.DENIED)

        if (deniedItems.length > 0) {
            console.log('permissions denied by user.', deniedItems);
        }

        if (permanentlyDeniedItems.length > 0) {
            console.log('permissions revoked by user.', permanentlyDeniedItems);
        }
        return false;
    }
    return true;

}

