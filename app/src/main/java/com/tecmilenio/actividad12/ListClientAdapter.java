package com.tecmilenio.actividad12;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListClientAdapter extends RecyclerView.Adapter<ListClientAdapter.ClientViewHolder>{

    ArrayList<Clients> listClients;

    ArrayList<Clients>originalListClients;

    public ListClientAdapter(ArrayList<Clients> listClients){
        this.listClients = listClients;
        originalListClients = new ArrayList<>();
        originalListClients.addAll(listClients);
    }

    /**
     * Busco una registro que coincida con lo que se está colocando en la barra de busqueda
     * @param petition
     */
    public void filterRecords(String petition){
        int longitud = petition.length();
        //Si no hay nada en la barra de búsqueda
        if(longitud == 0){
            listClients.clear();
            listClients.addAll(originalListClients);
        }else {
            //Si queremos buscar algo
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Clients> collection = listClients.stream().
                        filter(i -> i.getName().toLowerCase().contains(petition.toLowerCase())).
                        collect(Collectors.toList());
                listClients.clear();
                listClients.addAll(collection);
            }else{
                for (Clients clients :originalListClients) {
                    if(clients.getName().toLowerCase().contains(petition.toLowerCase())){
                        listClients.add(clients);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ClientViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_client,null,false);
        return new ListClientAdapter.ClientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListClientAdapter.ClientViewHolder holder, int position) {
        holder.nameOfClientItem.setText(listClients.get(position).getName());
        holder.firstNameOfClientItem.setText(listClients.get(position).getFirstName());
        holder.secondNameOfClientItem.setText(listClients.get(position).getSecondName());
        holder.emailOfClientItem.setText(listClients.get(position).getEmail());
        holder.phoneOfClientItem.setText(listClients.get(position).getPhone());
    }

    @Override
    public int getItemCount() {
        return listClients.size();
    }

    public class ClientViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView nameOfClientItem;
        TextView firstNameOfClientItem;
        TextView secondNameOfClientItem;
        TextView emailOfClientItem;
        TextView phoneOfClientItem;


        public ClientViewHolder(View itemView) {
            super(itemView);

            nameOfClientItem = itemView.findViewById(R.id.nameOfClientItem);
            firstNameOfClientItem = itemView.findViewById(R.id.firstNameOfClientItem);
            secondNameOfClientItem = itemView.findViewById(R.id.secondNameOfClientItem);
            emailOfClientItem = itemView.findViewById(R.id.emailOfClientItem);
            phoneOfClientItem = itemView.findViewById(R.id.phoneOfClientItem);

            //Al hacer click en un registro
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Context context = view.getContext();
            Intent intent = new Intent(context, ShowInformationClients.class);
            intent.putExtra("ID", listClients.get(getAdapterPosition()).getId());
            context.startActivity(intent);
        }
    }
}
