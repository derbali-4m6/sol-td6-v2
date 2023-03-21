package ca.qc.sol_td6_v2.fel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import ca.qc.sol_td6_v2.R;
import ca.qc.sol_td6_v2.fel.myadapter.MyContactAdapter;
import ca.qc.sol_td6_v2.models.dao.ContactDAO;
import ca.qc.sol_td6_v2.models.dao.IContactDAO;
import ca.qc.sol_td6_v2.models.entities.Contact;

public class ContactsActivity extends AppCompatActivity {

    RecyclerView rvContacts;
    MyContactAdapter adapter;
    List<Contact> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        //init
        rvContacts = findViewById(R.id.rv_contacts);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rvContacts.setLayoutManager(manager);

        // get all contacts from SQlite db
        IContactDAO dao = new ContactDAO(this);
        contacts = dao.getAllContacts();
        //Log.()

        // use adapter to display data
        adapter = new MyContactAdapter(contacts, this);
        rvContacts.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_ajouter:
                //Toast.makeText(this, "Action Ajouter", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, AjoutActivity.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}













