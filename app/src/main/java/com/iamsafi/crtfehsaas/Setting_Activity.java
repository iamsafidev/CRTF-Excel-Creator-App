package com.iamsafi.crtfehsaas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.iamsafi.crtfehsaas.utils.Consts;
import com.iamsafi.crtfehsaas.utils.CustomButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Setting_Activity extends AppCompatActivity {
    @BindView(R.id.flatnumber)
    EditText et_flatnumbr;
    @BindView(R.id.etMohallah)
    EditText et_Mohallah;
    @BindView(R.id.etCity)
    EditText et_city;
    @BindView(R.id.registerBtn)
    CustomButton registerbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        Consts consts = new Consts(Setting_Activity.this);
        et_flatnumbr.setText(consts.getFlatNo());
        et_city.setText(consts.getPlace());
        et_Mohallah.setText(consts.getMohallah());
        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!et_flatnumbr.getText().toString().trim().equalsIgnoreCase("")) {
                    consts.setPlaceData(et_flatnumbr.getText().toString(), "", "");
                }
                if (!et_Mohallah.getText().toString().trim().equalsIgnoreCase("")) {
                    consts.setPlaceData("", et_Mohallah.getText().toString(), "");
                }
                if (!et_city.getText().toString().trim().equalsIgnoreCase("")) {
                    consts.setPlaceData("", "", et_city.getText().toString());
                }
                setResult(RESULT_OK);
                finish();
            }
        });
    }
}
