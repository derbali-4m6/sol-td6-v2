package ca.qc.sol_td6_v2.fel.myadapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ca.qc.sol_td6_v2.R;
import ca.qc.sol_td6_v2.fel.DetailsContactActivity;
import ca.qc.sol_td6_v2.fel.EditionActivity;
import ca.qc.sol_td6_v2.models.dao.ContactDAO;
import ca.qc.sol_td6_v2.models.dao.IContactDAO;
import ca.qc.sol_td6_v2.models.entities.Contact;

public class MyContactAdapter extends RecyclerView.Adapter<MyViewHolder> {

    //data source
    List<Contact> contacts;
    Context context;
    public MyContactAdapter(List<Contact> contacts, Context context) {
        this.contacts = contacts;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.line_contact, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.lblName.setText(this.contacts.get(position).getName());
        holder.lblPhoneNumber.setText(this.contacts.get(position).getPhoneNumber());
        if(contacts.get(position).getGender().equals("Male")){
            holder.imgContact.setImageResource(R.mipmap.ic_gender_male);
        }else if (contacts.get(position).getGender().equals("Female")){
            holder.imgContact.setImageResource(R.mipmap.ic_gender_female);
        }
        final int pos = position;
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailsContactActivity.class);
                intent.putExtra("name", contacts.get(pos).getName());
                context.startActivity(intent);
            }
        });

        //click sur les actions delete ou edit
        holder.deleteAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //supprimer le contact de la BD
                IContactDAO dao = new ContactDAO(context);
                Contact contact =  dao.deleteContactByID(contacts.get(pos).getId());
                if(contact != null) {
                    //adapter l'affichage
                    contacts.remove(pos);
                    notifyDataSetChanged();
                }
            }
        });

        holder.editAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  =  new Intent(context, EditionActivity.class);
                intent.putExtra("id", contacts.get(pos).getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.contacts.size();
    }
}
