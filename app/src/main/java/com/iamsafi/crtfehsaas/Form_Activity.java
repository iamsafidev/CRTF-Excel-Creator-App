package com.iamsafi.crtfehsaas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.iamsafi.crtfehsaas.database.Person;
import com.iamsafi.crtfehsaas.utils.Consts;
import com.iamsafi.crtfehsaas.viewModel.FormActivityViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@SuppressWarnings("ALL")
public class Form_Activity extends AppCompatActivity {
    @BindView(R.id.etFullName)
    EditText et_fname;
    @BindView(R.id.etCnic)
    EditText et_cnic;
    @BindView(R.id.etMobile)
    EditText et_mobile;
    @BindView(R.id.flatnumber)
    EditText et_flatnumbr;
    @BindView(R.id.etMohallah)
    EditText et_Mohallah;
    @BindView(R.id.etCity)
    EditText et_city;
    @BindView(R.id.etZila)
    EditText et_zila;
    @BindView(R.id.etTehsil)
    EditText et_Tehsil;

    @BindView(R.id.cnic_month)
    Spinner sp_month;
    @BindView(R.id.cnic_day)
    Spinner sp_day;
    @BindView(R.id.cnic_year)
    Spinner sp_year;
    @BindView(R.id.sp_gender)
    Spinner sp_gender;
    @BindView(R.id.sp_ms)
    Spinner sp_ms;
    public static final int RESULT_CODE_DONE = 007;
    Consts consts;
    @OnClick(R.id.registerBtn)
    void register() {
        if (!et_fname.getText().toString().trim().equalsIgnoreCase("")) {
            if (!et_cnic.getText().toString().trim().equalsIgnoreCase("") && et_cnic.getText().toString().length() == 13) {
                if (!et_mobile.getText().toString().trim().equalsIgnoreCase("") && et_mobile.getText().toString().length() == 11) {
                    if (!sp_month.getSelectedItem().toString().equalsIgnoreCase("Month") && (!sp_day.getSelectedItem().toString().equalsIgnoreCase("Day")) && (!sp_year.getSelectedItem().toString().equalsIgnoreCase("Year"))) {
                        if (!sp_ms.getSelectedItem().toString().equalsIgnoreCase("Martial Status") && !sp_gender.getSelectedItem().toString().equalsIgnoreCase("Gender")) {
                            if (!et_flatnumbr.getText().toString().trim().equalsIgnoreCase("")) {
                                if (!et_Mohallah.getText().toString().trim().equalsIgnoreCase("")) {
                                    if (!et_city.getText().toString().trim().equalsIgnoreCase("")) {
                                        if (!et_zila.getText().toString().trim().equalsIgnoreCase("")) {
                                            if (!et_Tehsil.getText().toString().trim().equalsIgnoreCase("")) {
                                                Person person = new Person();
                                                person.setFull_Name(et_fname.getText().toString());
                                                person.setCNIC(insertString(et_cnic.getText().toString(), "-", 0));
                                                person.setMobile(insertString(et_mobile.getText().toString(), "-", 3));
                                                person.setCNIC_Issue_Date(sp_day.getSelectedItem().toString() + "/" + sp_month.getSelectedItem().toString() + "/" + sp_year.getSelectedItem().toString());
                                                person.setMartial_Status(sp_ms.getSelectedItem().toString());
                                                person.setGender(sp_gender.getSelectedItem().toString());
                                                person.setFlat_No(et_flatnumbr.getText().toString());
                                                person.setArea(et_Mohallah.getText().toString());
                                                person.setCity(et_city.getText().toString());
                                                person.setZilla(et_zila.getText().toString());
                                                person.setTehsil(et_Tehsil.getText().toString());
                                                person.setRegistration_Status("UnRegistered");
                                                formActivityViewModel.addPeople(person);
                                                setResult(RESULT_CODE_DONE);
                                                finish();
                                               /* Log.i("check", "Full Name:" + et_fname.getText().toString() +
                                                        "\nCNIC:" + insertString(et_cnic.getText().toString(), "-", 0) +
                                                        "\nMobile:" + insertString(et_mobile.getText().toString(), "-", 3) +
                                                        "\nDate of Issue" + sp_month.getSelectedItem().toString() + "/" + sp_day.getSelectedItem().toString() + "/" + sp_year.getSelectedItem().toString() +
                                                        "\nMartial Status:" + sp_ms.getSelectedItem().toString() +
                                                        "\nGender" + sp_gender.getSelectedItem().toString() +
                                                        "\nFlat #" + et_flatnumbr.getText().toString() +
                                                        "\nMohallah:" + et_Mohallah.getText().toString() +
                                                        "\nCity: " + et_city.getText().toString() +
                                                        "\nZilla: " + et_zila.getText().toString() +
                                                        "\nTehsil: " + et_Tehsil.getText().toString());*/
                                            } else {
                                                showToast("Enter the Tehsil.");
                                            }
                                        } else {
                                            showToast("Enter the Zilla");
                                        }
                                    } else {
                                        showToast("Enter the city name");
                                    }
                                } else {
                                    showToast("Fill the Mohallah / Area field. ");
                                }
                            } else {
                                showToast("Fill the Flat / House Number. ");
                            }
                        } else {
                            showToast("Select the Gender & Martial Status");
                        }

                    } else {
                        showToast("Check the Issue Date of CNIC");
                    }
                } else {
                    showToast("Mobile Number is Invalid");
                }
            } else {
                showToast("CNIC Number is Invalid");
            }
        } else {
            showToast("Fill the Full Name");
        }

    }

    FormActivityViewModel formActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_activity);
        ButterKnife.bind(this);
        consts=new Consts(Form_Activity.this);
        intiliazeSpinners();
        initializeViewModel();
    }

    private void initializeViewModel() {
        formActivityViewModel = ViewModelProviders.of(Form_Activity.this).get(FormActivityViewModel.class);
    }

    private void intiliazeSpinners() {
        et_flatnumbr.setText(consts.getFlatNo());
        et_city.setText(consts.getPlace());
        et_Mohallah.setText(consts.getMohallah());
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.months_array, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_month.setAdapter(adapter1);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.days_array, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_day.setAdapter(adapter2);

        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.year_array, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_year.setAdapter(adapter3);

        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this,
                R.array.gender_array, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_gender.setAdapter(adapter4);

        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(this,
                R.array.ms_array, android.R.layout.simple_spinner_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_ms.setAdapter(adapter5);
    }

    private void showToast(String fill_msg) {
        Toast.makeText(Form_Activity.this, fill_msg, Toast.LENGTH_LONG).show();

    }

    public static String insertString(
            String originalString,
            String stringToBeInserted,
            int index) {

        // Create a new string
        String newString = new String();

        for (int i = 0; i < originalString.length(); i++) {

            // Insert the original string character
            // into the new string
            newString += originalString.charAt(i);
            if (index == 0) {
                if (i == 4 || i == 11) {
                    // Insert the string to be inserted
                    // into the new string
                    newString += stringToBeInserted;
                }
            } else {
                if (i == index) {
                    newString += stringToBeInserted;
                }
            }
        }

        // return the modified String
        return newString;
    }
}
