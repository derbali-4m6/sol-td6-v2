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

public class AjoutActivity extends AppCompatActivity {

    EditText txtNom, txtTelephone;
    RadioButton rbHomme, rbFemme;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout);
        txtNom = findViewById(R.id.ajout_txt_nom);
        txtTelephone = findViewById(R.id.ajout_txt_telephone);
        rbHomme = findViewById(R.id.ajout_rb_homme);
        rbFemme = findViewById(R.id.ajout_rb_femme);
        rbHomme.setChecked(true);
    }

    public void ajouter(View view) {
        String nom = txtNom.getText().toString().trim();
        String telephone  = txtTelephone.getText().toString().trim();
        String genre = "Male";
        if(rbFemme.isChecked()){
            genre = "Female";
        }
        if(!nom.equals("") && !telephone.equals("")){
            IContactDAO dao = new ContactDAO(this);
            Contact contact = new Contact();
            contact.setName(nom);
            contact.setPhoneNumber(telephone);
            contact.setGender(genre);
            contact =  dao.addContact(contact);
            if(contact != null){
                Intent intent = new Intent(this, ContactsActivity.class);
                startActivity(intent);
                finish();
            }else {
                Toast.makeText(this, "Problème lors de l'ajout", Toast.LENGTH_LONG).show();
            }
        }else {
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_LONG).show();
        }

    }
}