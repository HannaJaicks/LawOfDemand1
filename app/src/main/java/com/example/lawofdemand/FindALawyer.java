package com.example.lawofdemand;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;


public class FindALawyer extends Activity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    Button submit;
    private String[] specialitylist= {"Administrative", "Admiralty & Maritime",
            "Adoption",
            "Agriculture",
            "Alternative Dispute Resolution",
            "Animal Bites",
            "Antitrust",
            "Asbestos Mesothelioma",
            "Assault & Battery -- Plaintiff",
            "Auto Dealer Fraud",
            "Aviation",
            "Aviation & Mass Transit Accidents",
            "Bad Faith Insurance",
            "Banking & Finance",
            "Bankruptcy",
            "Business & Commercial",
            "Business Organizations",
            "Car Accident",
            "Child Custody",
            "Child Support",
            "Civil Rights",
            "Class Actions",
            "Collaborative",
            "Collections",
            "Communications & Media",
            "Constitutional",
            "Construction",
            "Consumer Protection",
            "Contracts",
            "Criminal Defense",
            "DUI / DWI",
            "Debtor-Creditor",
            "Discrimination",
            "Divorce",
            "Drugs & Medical Devices",
            "Education",
            "Elder Law",
            "Eminent Domain",
            "Employment",
            "Employment Law -- Employer",
            "Energy",
            "Entertainment, Sports & Leisure",
            "Environmental",
            "Estate Planning",
            "Ethics & Professional Responsibility",
            "Family Law",
            "Foreclosure & Alternatives",
            "Franchising",
            "Gaming",
            "Government Agencies & Programs",
            "Government Contracts",
            "Health Care",
            "Housing & Construction Defects",
            "Immigration",
            "Insurance",
            "Insurance Defense",
            "Intellectual Property",
            "International Law",
            "Internet",
            "Labor",
            "Land Use & Zoning",
            "Landlord-Tenant",
            "Legal Malpractice",
            "Lemon Law",
            "Litigation & Appeals",
            "Medical Malpractice",
            "Mergers & Acquisitions",
            "Military",
            "Motor Vehicle Defects",
            "Natural Resources",
            "Nursing Home Abuse",
            "Patents",
            "Personal Injury",
            "Personal Injury -- Defense",
            "Premises Liability",
            "Probate & Estate Administration",
            "Products Liability",
            "Professional Malpractice",
            "Railroad Worker Injury-FELA",
            "Real Estate",
            "Science & Technology",
            "Securities",
            "Sexual Abuse",
            "Sexual Harassment",
            "Social Security -- Disability",
            "State, Local & Municipal Law",
            "Tax",
            "Tax Increment Financing",
            "Toxic Mold",
            "Toxic Torts",
            "Trademarks",
            "Traffic Ticket",
            "Transportation",
            "Trusts",
            "Whistleblower-Qui Tam",
            "White Collar Crimes",
            "Wills",
            "Workers' Compensation",
            "Wrongful Death"
    };

    Spinner sspeciality;
    TextView speciality;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_alawyer);
        submit=(Button) findViewById(R.id.submitb);
        sspeciality=(Spinner) findViewById(R.id.spinnerspeciality);
        speciality=(TextView) findViewById(R.id.specialitytv);
        submit.setOnClickListener(this);

        ArrayAdapter<String> adapter_state = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, specialitylist);
        adapter_state
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sspeciality.setAdapter(adapter_state);
        sspeciality.setOnItemSelectedListener(this);




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_find_alawyer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.submitb:
               /* Intent submit = new Intent(FindALawyer.this, LawyersDetailsDisplay.class);
                startActivity(submit);*/
                Intent intent = new Intent(this, ChatActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        sspeciality.setSelection(i);
        String selState = (String) sspeciality.getSelectedItem();
        speciality.setText("Selected Speciality:" + selState);
    }



    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(this, Sixth.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }
}
