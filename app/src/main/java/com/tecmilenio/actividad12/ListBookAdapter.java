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
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.stream.Collectors;

public class ListBookAdapter extends RecyclerView.Adapter<ListBookAdapter.BookViewHolder> {

    ArrayList<Book>listBook;

    ArrayList<Book>originalListBook;

    public ListBookAdapter(ArrayList<Book> listBook){
        this.listBook = listBook;
        originalListBook = new ArrayList<>();
        originalListBook.addAll(listBook);
    }

    /**
     * Busco una registro que coincida con lo que se está colocando en la barra de busqueda
     * @param petition
     */
    public void filterRecords(String petition){
        int longitud = petition.length();
        //Si no hay nada en la barra de búsqueda
        if(longitud == 0){
            listBook.clear();
            listBook.addAll(originalListBook);
        }else {
            //Si queremos buscar algo
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Book> collection = listBook.stream().
                        filter(i -> i.getName().toLowerCase().contains(petition.toLowerCase())).
                        collect(Collectors.toList());
                listBook.clear();
                listBook.addAll(collection);
            }else{
                for (Book book :originalListBook) {
                    if(book.getName().toLowerCase().contains(petition.toLowerCase())){
                        listBook.add(book);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_book,null,false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListBookAdapter.BookViewHolder holder, int position) {
        holder.nameOfBookItem.setText(listBook.get(position).getName());
        holder.autorOfBookItem.setText(listBook.get(position).getAutor());
        holder.categoryOfBookItem.setText(listBook.get(position).getCategory());
    }

    @Override
    public int getItemCount() {
        return listBook.size();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView nameOfBookItem;
        TextView autorOfBookItem;
        TextView categoryOfBookItem;

        public BookViewHolder(View itemView) {
            super(itemView);

            nameOfBookItem = itemView.findViewById(R.id.nameOfBookItem);
            autorOfBookItem = itemView.findViewById(R.id.autorOfBookItem);
            categoryOfBookItem = itemView.findViewById(R.id.categoryOfBookItem);

            //Al hacer click en un registro
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Context context = view.getContext();
            Intent intent = new Intent(context, ShowInformationBook.class);
            intent.putExtra("ID", listBook.get(getAdapterPosition()).getId());
            context.startActivity(intent);
        }
    }
}

