package ntu.com.mylife.controller;

import android.content.Context;

import java.util.ArrayList;

import ntu.com.mylife.common.entity.applicationentity.Contact;
import ntu.com.mylife.common.entity.databaseentity.UserType;
import ntu.com.mylife.common.service.DatabaseDaoContact;
import ntu.com.mylife.common.service.DatabaseDaoContactImpl;
import ntu.com.mylife.common.service.SharedPreferencesService;

/**
 * Created by micha on 9/17/2016.
 */
public class ContactController {

    private ArrayList<Contact> contactList;
    private Context context;

    private SharedPreferencesService sharedPreferencesService;
    private static String KEY_USER = "userName";
    private static String NAME_SHARED_PREFERENCES = "UserSharedPreferences";
    private static String USER_TYPE = "userType";

    private String userId;
    private String userType;

    private DatabaseDaoContact databaseDaoContact;

    public ContactController(ArrayList<Contact> contactList, Context context) {
        this.contactList = contactList;
        this.context = context;

        sharedPreferencesService = new SharedPreferencesService(context);

        userId = sharedPreferencesService.getDataFromSharedPreferences(NAME_SHARED_PREFERENCES, KEY_USER);
        userType = sharedPreferencesService.getDataFromSharedPreferences(NAME_SHARED_PREFERENCES, USER_TYPE);

        databaseDaoContact = new DatabaseDaoContactImpl(userType);
    }

    public void fetchContact() {
        ArrayList<Contact> tmpList =  (ArrayList<Contact>)databaseDaoContact.findData();

        contactList.clear();

        for(Contact c: tmpList) {
            contactList.add(c);
        }

        
    }


}
