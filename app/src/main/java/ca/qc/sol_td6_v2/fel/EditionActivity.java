package ca.qc.sol_td6_v2.fel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import ca.qc.sol_td6_v2.R;
import ca.qc.sol_td6_v2.models.dao.ContactDAO;
import ca.qc.sol_td6_v2.models.dao.IContactDAO;
import ca.qc.sol_td6_v2.models.entities.Contact;

public class EditionActivity extends AppCompatActivity {
    EditText txtNom, txtTelephone;
    RadioButton rbHomme, rbFemme;
    IContactDAO dao;
    int idContact = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edition);
        txtNom = findViewById(R.id.edition_txt_nom);
        txtTelephone = findViewById(R.id.edition_txt_telephone);
        rbHomme = findViewById(R.id.edition_rb_homme);
        rbFemme = findViewById(R.id.edition_rb_femme);

        dao = new ContactDAO(this);

        Bundle extras = getIntent().getExtras();

        if(extras != null){
            idContact = extras.getInt("id");
            Contact contact = dao.getContactById(idContact);
            if(contact != null){
                txtNom.setText(contact.getName());
                txtTelephone.setText(contact.getPhoneNumber());
                if(contact.getGender().equals("Male"))
                    rbHomme.setChecked(true);
                else
                    rbFemme.setChecked(true);
            }
        }

    }

    public void modifier(View view) {
        String nom = txtNom.getText().toString().trim();
        String telephone  = txtTelephone.getText().toString().trim();
        String genre = "";
        if(rbFemme.isChecked()){
            genre = "Female";
        }else {
            genre = "Male";
        }
        if(!nom.equals("") && !telephone.equals("")){
            IContactDAO dao = new ContactDAO(this);
            Contact contact = new Contact();
            contact.setName(nom);
            contact.setPhoneNumber(telephone);
            contact.setGender(genre);
            contact =  dao.updateContactById(idContact,contact);
            if(contact != null){
                Intent intent = new Intent(this, ContactsActivity.class);
                startActivity(intent);
                finish();
            }else {
                Toast.makeText(this, "Problème lors de la mise à jour", Toast.LENGTH_LONG).show();
            }
        }else {
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_LONG).show();
        }
    }
}