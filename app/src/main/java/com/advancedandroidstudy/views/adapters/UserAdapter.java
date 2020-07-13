package com.advancedandroidstudy.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.advancedandroidstudy.R;
import com.advancedandroidstudy.models.User;
import com.advancedandroidstudy.views.adapters.Listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.Holder> {

    private Context context;
    private List<User> listUser;
    private OnItemClickListener onItemClickListener;

    public UserAdapter(Context context) {
        this.context = context;
        this.listUser = new ArrayList<>();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setListForAdpter(List<User> listUser) {
        notifyItemRangeRemoved(0, this.listUser.size());
        this.listUser.clear();
        this.listUser.addAll(listUser);
        notifyItemRangeInserted(0, this.listUser.size());
    }

    public void updateItemList(int posicao, User user) {
        this.listUser.set(posicao, user);
        notifyDataSetChanged();
    }

    public User getModelListAdapter(int posicao) {
        return this.listUser.get(posicao);
    }

    public void removeItemList(int posicao) {
        this.listUser.remove(posicao);
        notifyItemRemoved(posicao);
    }

    public void restoreItem(User user, int position) {
        this.listUser.add(user);
        notifyItemInserted(position);
    }

    public void addItemList(User user) {
        this.listUser.add(user);
        notifyDataSetChanged();
    }

    public void changesThePositionOfTheListItem(int startingPosition, int finalPosition) {
        Collections.swap(this.listUser, startingPosition, finalPosition);
        notifyItemMoved(startingPosition, finalPosition);
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(this.context).inflate(R.layout.layout_list_user, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.tvTitle.setText(this.listUser.get(position).getName());
        holder.tvSubTitle.setText(String.valueOf(this.listUser.get(position).getAge()));
        holder.user = this.listUser.get(position);
    }

    @Override
    public int getItemCount() {
        return this.listUser.size();
    }

    class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView tvTitle;
        private final TextView tvSubTitle;
        private User user;

        private Holder(@NonNull View itemView) {
            super(itemView);

            this.tvTitle = itemView.findViewById(R.id.layout_list_title);
            this.tvSubTitle = itemView.findViewById(R.id.layout_list_subTitle);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onItemClickListener.onItemClick(this.user, getAdapterPosition());
        }
    }
}