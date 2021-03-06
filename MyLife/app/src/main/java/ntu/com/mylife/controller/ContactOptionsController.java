package ntu.com.mylife.controller;

import android.app.Activity;
import android.content.Context;

import ntu.com.mylife.common.entity.applicationentity.SharedPreferencesKey;
import ntu.com.mylife.common.service.DatabaseDaoMedicalRecord;
import ntu.com.mylife.common.service.DatabaseDaoMedicalRecordImpl;
import ntu.com.mylife.common.service.SharedPreferencesService;

/**
 * Created by LENOVO on 23/09/2016.
 */
public class ContactOptionsController {

    private Activity myActivity;
    private DatabaseDaoMedicalRecord dbMedicalRecord;
    private SharedPreferencesService sharedPreferencesService;

    public ContactOptionsController(Activity myActivity){

        this.myActivity = myActivity;
        sharedPreferencesService = new SharedPreferencesService(myActivity.getBaseContext());
        dbMedicalRecord = new DatabaseDaoMedicalRecordImpl(myActivity);
    }

    public void submitMedicalReport(Object object){
        //directly add the file using this constructor
        dbMedicalRecord.addNewMedicalRecord(object,sharedPreferencesService.getDataFromSharedPreferences
                (SharedPreferencesKey.NAME_SHARED_PREFERENCES,SharedPreferencesKey.CURRENT_CLICK_CONTACT));
    }

    public void createNotification(){

    }

}
