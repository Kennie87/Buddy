// Kenneth Johnson
// 09/26/2022

package androidbootcamp.net;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.joda.time.Period;
import org.joda.time.PeriodType;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Stats extends AppCompatActivity {

    Button btStartDate,btEndingDate,btCalculate;
    double daily,dnaMut;
    TextView mainResult;
    DatePickerDialog.OnDateSetListener dateSetListener1,dateSetListener2;

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        btStartDate = findViewById(R.id.startDate);
        btEndingDate = findViewById(R.id.endingDate);
        btCalculate = findViewById(R.id.bt_calculate);
        mainResult = findViewById(R.id.mainResult);
        final TextView cigCount = findViewById(R.id.totalCigCount);
        final EditText countCi = findViewById(R.id.dailyCigCount);
        final TextView dnaCount = findViewById(R.id.totalMutaCount);

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String date = simpleDateFormat.format(Calendar.getInstance().getTime());
        btStartDate.setText(date);

        btEndingDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Stats.this
                        ,android.R.style.Theme_Holo_Light_Dialog_MinWidth
                        ,dateSetListener1,year,month,day
                );
                datePickerDialog.getWindow().setBackgroundDrawable(new
                        ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        dateSetListener1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month + 1;
                String date = day + "/" + month + "/" + year;
                btEndingDate.setText(date);
            }
        };

        btStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Stats.this
                        ,android.R.style.Theme_Holo_Light_Dialog_MinWidth
                        ,dateSetListener2,year,month,day
                );
                datePickerDialog.getWindow().setBackgroundDrawable(new
                        ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        dateSetListener2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month + 1;
                String date = day + "/" + month + "/" + year;
                btStartDate.setText(date);
            }
        };

        btCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sDate = btStartDate.getText().toString();
                String eDate = btEndingDate.getText().toString();
                SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    Date date1 = simpleDateFormat1.parse(sDate);
                    Date date2 = simpleDateFormat1.parse(eDate);

                    long startDate = date1.getTime();
                    long endDate = date2.getTime();

                    if (startDate <= endDate){
                        Period period = new Period(startDate,endDate, PeriodType.yearMonthDay());
                        int years = period.getYears();
                        int months = period.getMonths();
                        int days = period.getDays();
                        int totalC = years * 365 + months * 30 + days;
                        daily = Double.parseDouble(countCi.getText().toString());
                        DecimalFormat whole = new DecimalFormat("#");
                        double dTotal = totalC * daily;
                        cigCount.setText(whole.format(dTotal));
                        double mutCount = dTotal / 15.25;
                        dnaCount.setText(whole.format(mutCount));

                        double estCost = dTotal / 20 * 5.25;

                        Toast.makeText(Stats.this,"Estimated Cost $" + Math.round(estCost * 100)/100.0,
                                Toast.LENGTH_SHORT).show();


                        mainResult.setText(years+" Years | "+months+" Months | "+days+" Days");
                    } else {
                        Toast.makeText(Stats.this,
                                "Start Date should not exceed End Date",Toast.LENGTH_SHORT).show();
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

        bottomNavigationView = findViewById(R.id.bottom_navigator);
        bottomNavigationView.setSelectedItemId(R.id.stats);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.stats:
                        return true;
                    case R.id.therapy:
                        startActivity(new Intent(getApplicationContext(),Therapy.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }
        });
    }
}