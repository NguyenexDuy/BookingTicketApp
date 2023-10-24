package com.example.cnpmnc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cnpmnc.R;
import com.google.firebase.auth.FirebaseAuth;

public class DangKyActivity extends AppCompatActivity {

    private EditText edtEmail,edtPass,edtRepass,edtHoten,edtNgaySinh,edtSDT,edtQuocTich,;
    private Button BtnDangKy;

    private TextView Layout_Dang_Ky;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);

        initUI();
        initListener();

    }
    private void initUI(){
        edtEmail = findViewById(R.id.edtEmailDangKy);
        edtPass = findViewById(R.id.edtPassDangKy);
        edtRepass = findViewById(R.id.edtRePassDangKy);
        BtnDangKy = findViewById(R.id.BtnDangKy);
        Layout_Dang_Ky = findViewById(R.id.layout_dangky);
//        edtHoten = findViewById(R.id.);
//        edtNgaySinh = findViewById(R.id.);
//        edtSDT = findViewById(R.id);
//        edtQuocTich = findViewById(R.id);
    }
    private void initListener(){
        BtnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strEmail = edtEmail.getText().toString().trim();
                String strPass = edtPass.getText().toString().trim();

                FirebaseAuth auth = FirebaseAuth.getInstance();
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    updateUI(null);
                                }
                            }
                        });

            }
        });
        Layout_Dang_Ky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DangKyActivity.this,DangNhapActivity.class);
                startActivity(intent);
            }
        });
    }
}
