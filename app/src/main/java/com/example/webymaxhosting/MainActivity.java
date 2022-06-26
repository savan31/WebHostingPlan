package com.example.webymaxhosting;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private TextView textViewTitle;
    private TextInputLayout filledTextFieldFirstName;
    private TextInputEditText editTextName;
    private RadioGroup radGroup;
    private RadioButton radioBtnStartUp;
    private RadioButton radioBtnGrowBig;
    private CheckBox checkboxUnlimitedData;
    private CheckBox checkboxStaging;
    private TextView textViewSize;
    private Spinner spinnerDataSize;
    private LinearLayout l1;
    private RadioButton radioBtnOnDemandBackup;
    private RadioButton radioBtnPhp;
    private RadioButton radioBtnStagingGit;
    private RadioButton radioBtnPrioritySupport;
    private AutoCompleteTextView autoTextViewProvince;
    private ImageButton imageBtnDatePicker;
    private EditText editTextDate;
    private Button submitButton;
    private RadioGroup radioGrp2;
    private RadioGroup radioGrp3;

    //Variables
    String[] provinceArrayList = new String[] {"Ontario", "British Columbia","Alberta","Manitoba","Quebec","Northwest Territories",
            "Nova Scotia","Nunavut"};
    DatePickerDialog datePickerDialog;
    double totalCost = 0;
    double dataSizeCost = 0;
    double additionalFeatureCost = 0;
    double dataStagingCost = 0;
    String dataStagingString;
    String selectedSize;
    String additionalFeatureString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //calling all methods
        bindWithId();
        countSizeOfWebSpace();
        onClickEventHandler();
        autoCompleteText();
        datePicker();

    }

    //bind all items by findviewbyme
    void bindWithId(){
        //binding view with id
        textViewTitle = findViewById(R.id.textViewTitle);
        filledTextFieldFirstName = findViewById(R.id.filledTextFieldFirstName);
        editTextName = findViewById(R.id.editTextName);
        radGroup = findViewById(R.id.radGroup);
        radioBtnStartUp = findViewById(R.id.radioBtnStartUp);
        radioBtnGrowBig = findViewById(R.id.radioBtnGrowBig);
        checkboxUnlimitedData = findViewById(R.id.checkboxUnlimitedData);
        checkboxStaging = findViewById(R.id.checkboxStaging);
        textViewSize = findViewById(R.id.textViewSize);
        spinnerDataSize = findViewById(R.id.spinnerDataSize);
        l1 = findViewById(R.id.l1);
        radioBtnOnDemandBackup = findViewById(R.id.radioBtnOnDemandBackup);
        radioBtnPhp = findViewById(R.id.radioBtnPhp);
        radioBtnStagingGit = findViewById(R.id.radioBtnStagingGit);
        radioBtnPrioritySupport = findViewById(R.id.radioBtnPrioritySupport);
        autoTextViewProvince = findViewById(R.id.autoTextViewProvince);
        imageBtnDatePicker = findViewById(R.id.imageBtnDatePicker);
        editTextDate = findViewById(R.id.editTextDate);
        submitButton = findViewById(R.id.submitButton);
        radioGrp2 = findViewById(R.id.radioGrp2);
        radioGrp3 = findViewById(R.id.radioGrp3);
    }

    //all onclick event listner
    void onClickEventHandler(){
        radioBtnStartUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                additionalFeaturesVisibility();
            }
        });
        radioBtnGrowBig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                additionalFeaturesVisibility();
            }
        });
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countAdditionalFeatureCost();
                countDataStaging();
                if(radioBtnStartUp.isChecked()){
                    totalCost = dataSizeCost+additionalFeatureCost+47.88+dataStagingCost;
                }
                else if(radioBtnGrowBig.isChecked()){
                    totalCost = dataSizeCost+additionalFeatureCost+80.82+dataStagingCost;

                }
                String finalOutput = "On "+editTextDate.getText().toString()+", for "+editTextName.getText().toString()+" from "+autoTextViewProvince.getText().toString()+
                        ", with "+dataStagingString+" , "+selectedSize+" webspace, and "+additionalFeatureString+" , Cost :"+totalCost ;
                Toast.makeText(getApplicationContext(),finalOutput,Toast.LENGTH_LONG).show();
            }
        });
    }

    //for get user selection information of database type
void countDataStaging(){
    if(checkboxUnlimitedData.isChecked()&&checkboxStaging.isChecked()){
        dataStagingCost = 16.74;
        dataStagingString = "Unlimited Database and staging";
    }
    else if(checkboxStaging.isChecked()){
        dataStagingCost = 7.49;
        dataStagingString = "Staging";

    }
    else if (checkboxUnlimitedData.isChecked()){
        dataStagingCost = 9.25;
        dataStagingString = "Unlimited Database";

    }
    else{
        dataStagingCost = 0;
    }
    Log.d(TAG, "countDataStaging:" +String.valueOf(dataStagingCost));

}
//for counting additional feature cost
    void countAdditionalFeatureCost(){
        if(radioBtnOnDemandBackup.isChecked()){
            additionalFeatureCost = 3.99;
            additionalFeatureString = "on-demand backup";
        }
        else if(radioBtnPhp.isChecked()){
            additionalFeatureCost = 7.99;
            additionalFeatureString = "ultrafast PHP";
        }
        else if(radioBtnStagingGit.isChecked()){
            additionalFeatureCost = 5.99;
            additionalFeatureString = "staging+git";
        }
        else if(radioBtnPrioritySupport.isChecked()){
            additionalFeatureCost = 8.99;
            additionalFeatureString = "priority support";
        }
        else{
            additionalFeatureCost = 0;
            additionalFeatureString = "";
        }
        Log.d(TAG, "countAdditionalFeatureCost: "+additionalFeatureCost);
    }

//for counting size of webspace from spinner
    void countSizeOfWebSpace(){
        spinnerDataSize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedSize = adapterView.getItemAtPosition((int) i).toString();
                Log.d(TAG, "onItemSelected: "+selectedSize);
                if (selectedSize.equals("10GB")){
                    dataSizeCost =3.99;
                }
                else if(selectedSize.equals("20GB") ){
                    dataSizeCost =6.99;
                }
                else if(selectedSize.equals("40GB")){
                    dataSizeCost =12.99;
                }
                else{
                    dataSizeCost = 0;
                }
                Log.d(TAG, "dataSizeCost: "+dataSizeCost);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    //Auto Complete Text View by arraylist
    void autoCompleteText(){
        ArrayAdapter<String> arrayAdapterProvince = new ArrayAdapter<>(this, android.R.layout.select_dialog_item, provinceArrayList);
        autoTextViewProvince.setAdapter(arrayAdapterProvince);
        autoTextViewProvince.setThreshold(2);

    }

    //create datepicker and set date
    void datePicker(){

        imageBtnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // dialog for datePicker
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        editTextDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
    }



    //Manipulate additional features visibility based on hosting plan
    void additionalFeaturesVisibility(){
        if(radioBtnStartUp.isChecked()){
            radioGrp2.setVisibility(View.VISIBLE);
        }
        else{
            radioGrp2.setVisibility(View.GONE);
        }
        if(radioBtnGrowBig.isChecked()){
            radioGrp3.setVisibility(View.VISIBLE);
        }
        else{
            radioGrp3.setVisibility(View.GONE);
        }
    }

}