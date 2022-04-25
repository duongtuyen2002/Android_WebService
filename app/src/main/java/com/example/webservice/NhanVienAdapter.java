package com.example.webservice;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Map;

public class NhanVienAdapter extends BaseAdapter {
    private MainActivity context;
    private int layout;
    private List<NhanVien> list;

    public NhanVienAdapter(MainActivity context, int layout, List<NhanVien> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder{
        TextView txtMaNV,txtTen,txtSDT;
        ImageView imgDel, imgEdit;

    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view==null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,null);
            holder.txtTen = view.findViewById(R.id.tenNV);
            holder.txtMaNV = view.findViewById(R.id.idNV);
            holder.txtSDT = view.findViewById(R.id.SDT);
            holder.imgDel = view.findViewById(R.id.imgXoa);
            holder.imgEdit = view.findViewById(R.id.imgSua);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        NhanVien nhanVien = list.get(i);
        holder.txtMaNV.setText(String.valueOf(nhanVien.getMaNV()));
        holder.txtTen.setText(nhanVien.getTenNV());
        holder.txtSDT.setText(nhanVien.getSDT());
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent= new Intent(context,UpdateNV.class);
               intent.putExtra("dataNV",nhanVien);
               context.startActivity(intent);
            }
        });

        holder.imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xacNhan(nhanVien.getTenNV(), nhanVien.getMaNV());
            }
        });
        return view;
    }

    private void xacNhan(String ten, int id){
        AlertDialog.Builder dialogDel = new AlertDialog.Builder(context);
        dialogDel.setMessage("Bạn có muốn xóa nhân viên " + ten + " không?");

        dialogDel.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                context.delete(id);
            }
        });

        dialogDel.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        dialogDel.show();
    }
}
