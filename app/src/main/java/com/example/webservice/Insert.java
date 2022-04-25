package com.example.webservice;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Insert extends AppCompatActivity {
    EditText txtMa,txtTen,txtSDT;
    Button add,out;
    String url = "http://kubjnaluoi-001-site1.ftempurl.com/insert.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        txtMa = findViewById(R.id.txtMa);
        txtTen = findViewById(R.id.txtTen);
        txtSDT = findViewById(R.id.txtSDT);
        add = findViewById(R.id.btnAdd);
        out = findViewById(R.id.btnOut);

        out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Insert.this,MainActivity.class);
                startActivity(intent);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                themNhanVien(url);
            }
        });
    }

    private void themNhanVien(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.trim().equals("insert data successful")) {
                    Toast.makeText(Insert.this, "Thêm thành công", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(Insert.this, MainActivity.class));
                }
                else
                    Toast.makeText(Insert.this, "Thêm không thành công", Toast.LENGTH_LONG).show();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Insert.this, "Xảy ra lỗi!!!", Toast.LENGTH_LONG).show();
                        Log.d("AAA","Lỗi!\n"+error.toString());
                    }
                }
        ) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("MaNV",txtMa.getText().toString());
                params.put("TenNV",txtTen.getText().toString());
                params.put("SDT",txtSDT.getText().toString());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}