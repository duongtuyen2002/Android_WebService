package com.example.webservice;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.xml.transform.ErrorListener;

public class UpdateNV extends AppCompatActivity {
    EditText editMa,editTen,editSDT;
    Button xacNhan, thoat;
    String url ="http://kubjnaluoi-001-site1.ftempurl.com/Update.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_nv);

        editMa = findViewById(R.id.edt_Ma);
        editTen = findViewById(R.id.edt_Ten);
        editSDT = findViewById(R.id.edt_SDT);
        xacNhan = findViewById(R.id.btnUpdate);
        thoat = findViewById(R.id.btnOutUpdate);

        Intent intent = getIntent();
        NhanVien nhanVien = (NhanVien) intent.getSerializableExtra("dataNV");

        editMa.setText(String.valueOf(nhanVien.getMaNV()));
        editTen.setText(nhanVien.getTenNV());
        editSDT.setText(nhanVien.getSDT());

        xacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ten = editTen.getText().toString().trim();
                String ma = editMa.getText().toString().trim();
                String SDT = editSDT.getText().toString().trim();

                if(ten.equals("") || ma.equals("") || SDT.equals("")){
                    Toast.makeText(UpdateNV.this,"Vui lòng nhập đầy đủ thông tin.",Toast.LENGTH_LONG).show();
                }else {
                    updateNhanVien(url);
                }
            }
        });

        thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UpdateNV.this,MainActivity.class));
            }
        });
    }

    private void updateNhanVien(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.trim().equals("update data successful")){
                    Toast.makeText(UpdateNV.this,"Cập nhật thành công",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(UpdateNV.this,MainActivity.class));
                }else{
                    Toast.makeText(UpdateNV.this,"Lỗi",Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(UpdateNV.this,"Cập nhật bị lỗi, vui lòng kiểm tra lại!",Toast.LENGTH_LONG).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("MaNV",editMa.getText().toString().trim());
                params.put("TenNV",editTen.getText().toString().trim());
                params.put("SDT",editSDT.getText().toString().trim());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }


}