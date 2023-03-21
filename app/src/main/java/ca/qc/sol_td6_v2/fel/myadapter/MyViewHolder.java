package ca.qc.sol_td6_v2.fel.myadapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ca.qc.sol_td6_v2.R;

public class MyViewHolder extends RecyclerView.ViewHolder {
    ImageView imgContact;
    TextView lblName, lblPhoneNumber;
    ImageView deleteAction, editAction;
    View container;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        container = itemView;
        imgContact = itemView.findViewById(R.id.img_contact);
        lblName = itemView.findViewById(R.id.name_contact);
        lblPhoneNumber = itemView.findViewById(R.id.phone_contact);
        editAction = itemView.findViewById(R.id.edit_contact);
        deleteAction= itemView.findViewById(R.id.delete_contact);
    }
}
