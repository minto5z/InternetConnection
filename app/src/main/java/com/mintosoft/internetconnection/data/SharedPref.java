package com.mintosoft.internetconnection.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;

import com.mintosoft.internetconnection.BuildConfig;
import com.mintosoft.internetconnection.R;

public class SharedPref {

    private Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences prefs;
    public static final int MAX_OPEN_COUNTER = BuildConfig.DEBUG ? 1 : Constant.OPEN_COUNTER;

    private static final String FCM_PREF_KEY = "_.FCM_PREF_KEY";
    private static final String FIRST_LAUNCH = "_.FIRST_LAUNCH";
    private static final String INFO_DATA = "_.INFO_DATA_KEY";
    private static final String BUYER_PROFILE = "_.BUYER_PROFILE_KEY";

    public SharedPref(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("MAIN_PREF", Context.MODE_PRIVATE);
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    /**
     * Preference for first launch
     */
    public void setFirstLaunch(boolean flag) {
        sharedPreferences.edit().putBoolean(FIRST_LAUNCH, flag).apply();
    }

    public boolean isFirstLaunch() {
        return sharedPreferences.getBoolean(FIRST_LAUNCH, true);
    }

    /**
     * Preference for Fcm register
     */
    public void setFcmRegId(String fcmRegId) {
        sharedPreferences.edit().putString(FCM_PREF_KEY, fcmRegId).apply();
    }

    public String getFcmRegId() {
        return sharedPreferences.getString(FCM_PREF_KEY, null);
    }

    public boolean isFcmRegIdEmpty() {
        return TextUtils.isEmpty(getFcmRegId());
    }

    /**
     * For notifications flag
     */
    public boolean getNotification() {
        return prefs.getBoolean(context.getString(R.string.pref_title_notif), true);
    }

    public String getRingtone() {
        return prefs.getString(context.getString(R.string.pref_title_ringtone), "content://settings/system/notification_sound");
    }

    public boolean getVibration() {
        return prefs.getBoolean(context.getString(R.string.pref_title_vibrate), true);
    }


    /**
     * To save dialog permission state
     */
    public void setNeverAskAgain(String key, boolean value) {
        sharedPreferences.edit().putBoolean(key, value).apply();
    }

    public boolean getNeverAskAgain(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    // when app open N-times it will update fcm RegID at server
    public boolean isOpenAppCounterReach() {
        int counter = sharedPreferences.getInt("OPEN_COUNTER_KEY", MAX_OPEN_COUNTER) + 1;
        setOpenAppCounter(counter);
        Log.e("COUNTER", "" + counter);
        return (counter >= MAX_OPEN_COUNTER);
    }

    public void setOpenAppCounter(int val) {
        sharedPreferences.edit().putInt("OPEN_COUNTER_KEY", val).apply();
    }



    public void clearInfoData() {
        sharedPreferences.edit().putString(INFO_DATA, null).apply();
    }






    public void clearBuyerProfile() {
        sharedPreferences.edit().putString(BUYER_PROFILE, null).apply();
    }
}
