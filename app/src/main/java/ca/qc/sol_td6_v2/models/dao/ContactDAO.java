package ca.qc.sol_td6_v2.models.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ca.qc.sol_td6_v2.models.data.Singleton;
import ca.qc.sol_td6_v2.models.entities.Contact;


public class ContactDAO implements  IContactDAO{
    Singleton singleton;
    public ContactDAO(Context context) {
        this.singleton = Singleton.getSingleInstance(context);
    }

    @Override
    public List<Contact> getAllContacts() {
        SQLiteDatabase db = this.singleton.helper.getReadableDatabase();
        String request ="SELECT * FROM Contact";
        Cursor cursor = db.rawQuery(request, null);
        if(cursor !=null){
            cursor.moveToFirst();
            List<Contact> contacts = new ArrayList<>();
            while(!cursor.isAfterLast()){
                Contact contact = new Contact();
                contact.setId(cursor.getInt(0)); //id
                contact.setName(cursor.getString(1)); //name
                contact.setPhoneNumber(cursor.getString(2)); //phoneNumber
                contact.setGender(cursor.getString(3)); //gender
                //ajouter le contact à la liste
                contacts.add(contact);
                cursor.moveToNext();
            }
            db.close();
            return contacts;
        }
        return null;
    }

    @Override
    public Contact getContactById(int id) {
        SQLiteDatabase db = this.singleton.helper.getReadableDatabase();
        String request ="SELECT * FROM Contact WHERE id = " + id;
        Cursor cursor = db.rawQuery(request, null);
        if(cursor !=null){
            cursor.moveToFirst();
            Contact contact = new Contact();
            contact.setId(cursor.getInt(0)); //id
            contact.setName(cursor.getString(1)); //name
            contact.setPhoneNumber(cursor.getString(2)); //phoneNumber
            contact.setGender(cursor.getString(3)); //gender
            db.close();
            return contact;
        }
        return null;
    }

    @Override
    public Contact addContact(Contact contact) {
        SQLiteDatabase db = this.singleton.helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", contact.getName());
        values.put("phonenumber", contact.getPhoneNumber());
        values.put("gender", contact.getGender());

        int id = (int) db.insert("Contact", null, values);
        return getContactById(id);
    }

    @Override
    public Contact updateContactById(int id, Contact contact) {
        SQLiteDatabase db = this.singleton.helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", contact.getName());
        values.put("phonenumber", contact.getPhoneNumber());
        values.put("gender", contact.getGender());

        db.update("Contact",  values, "id = ?", new String[]{ id+"" });
        return getContactById(id);
    }

    @Override
    public Contact deleteContactByID(int id) {
        Contact contact = getContactById(id);
        if(contact != null) {
            SQLiteDatabase db = this.singleton.helper.getWritableDatabase();
            db.delete("Contact", "id = ?", new String[]{id + ""});
        }
        return contact;
    }
}
