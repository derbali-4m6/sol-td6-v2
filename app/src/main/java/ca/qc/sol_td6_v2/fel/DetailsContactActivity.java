package ca.qc.sol_td6_v2.fel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import ca.qc.sol_td6_v2.R;

public class DetailsContactActivity extends AppCompatActivity {

    TextView contactName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_contact);

        contactName = findViewById(R.id.details_name_contact);
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            String name = extras.getString("name");
            contactName.setText(name);
        }
    }
}