package com.example.formresepbaking;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataViewHolder> {
    private Context mContext;
    private Cursor mCursor;
    private OnClickListenerData listenerData;


    public DataAdapter(Context context, Cursor cursor) {
        this.mContext = context;
        this.mCursor = cursor;
    }
    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.cardresep,parent,false);
        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        if(!mCursor.moveToPosition(position)){
            return;
        }

        String namaresep = mCursor.getString(mCursor.getColumnIndex(DbMain.row_namaresep));
        String ingredients = mCursor.getString(mCursor.getColumnIndex(DbMain.row_ingredients));
        String caramemasak = mCursor.getString(mCursor.getColumnIndex(DbMain.row_kategori));
        String nutrition = mCursor.getString(mCursor.getColumnIndex(DbMain.row_nutrition));

        long id = mCursor.getLong(mCursor.getColumnIndex(DbMain.row_id));
        holder.itemView.setTag(id);
        holder.namaresep.setText(namaresep);
        holder.ingredients.setText(ingredients);
        holder.caramemasak.setText(caramemasak);
        holder.nutrition.setText(nutrition);

    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public class DataViewHolder extends RecyclerView.ViewHolder {
        private TextView namaresep, ingredients, caramemasak, nutrition;
        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            namaresep = itemView.findViewById(R.id.namaresep);
            ingredients = itemView.findViewById(R.id.ingredients);
            caramemasak = itemView.findViewById(R.id.caramemasak);
            nutrition = itemView.findViewById(R.id.nutrition);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    long position = (long) itemView.getTag();
                    listenerData.OnItemClickData(position);
                }
            });
        }
    }
    public interface OnClickListenerData{
        void OnItemClickData(long id);
    }

    public void setOnClickListenerData(OnClickListenerData listenerData){
        this.listenerData = listenerData;
    }

    public void swapCursor(Cursor newCursor){
        if(mCursor!=null){
            mCursor.close();
        }
        mCursor=newCursor;

        if(newCursor!=null){
            this.notifyDataSetChanged();
        }
    }
}