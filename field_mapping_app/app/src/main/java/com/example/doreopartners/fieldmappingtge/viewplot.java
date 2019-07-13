package com.example.doreopartners.fieldmappingtge;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonIOException;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.PointsGraphSeries;
import com.jjoe64.graphview.series.Series;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class viewplot extends AppCompatActivity  {
    private static final String TAG = "MainActivity";

    //add PointsGraphSeries of DataPoint type
    PointsGraphSeries<DataPoint> xySeries;
    PointsGraphSeries<DataPoint>xySeries2;
    PointsGraphSeries<DataPoint>xySeries3;

    PointsGraphSeries<DataPoint> onClickSeries;
    LocationManager locationManager;
    //create graphview object
    GraphView mScatterPlot;
    //GraphView mScatterplot2;
    String provider;
    //make xyValueArray global
    ArrayList<XYValue> xyValueArray;
    //ArrayList<XYValue> xyValueArray2;
    ArrayList<XYValue>xyValueArray3;
    double lat;
    double lng;
    double lating;
    double lnging;
    Button viewplot;
    SharedPreferences member;
    SharedPreferences.Editor memEdit;
    SharedPreferences prefs2;

    ArrayList<Double> lats;
    ArrayList<Double> longs;
    ArrayList<Double> time;
    ArrayList<Double> latlongs;
    final long MIN_LOC_UPDATE_TIME = 500;
    String walkOrBike;
    //these will be varied
    float MIN_LOC_UPDATE_DISTANCE;
    float MAX_WALKING_SPEED;
    float MAX_BIKE_SPEED;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewplot);

        time = new ArrayList<>();
        latlongs = new ArrayList<>();
        member = getSharedPreferences("member", MODE_PRIVATE);
        prefs2 = getSharedPreferences("prefs", MODE_PRIVATE);
        memEdit = member.edit();
        viewplot=findViewById(R.id.view);
        //declare graphview object
        mScatterPlot = (GraphView) findViewById(R.id.scatterplot);
        lats = (ArrayList<Double>) getIntent().getSerializableExtra("lats");
        longs = (ArrayList<Double>) getIntent().getSerializableExtra("lngs");

        xySeries = new PointsGraphSeries<>();
        // xySeries2=new PointsGraphSeries<>();
        //xySeries3=new PointsGraphSeries<>();
        //take_picture = findViewById(R.id.take_picture);
        //generate two lists of random values, one for x and one for y.
        xyValueArray = new ArrayList<>();
        //  xyValueArray2=new ArrayList<>();
        //xyValueArray3=new ArrayList<>();
        double start = -100;
        double end = 100;
Log.d("latsmemem",lats.toString());
        for(int i = 0; i<lats.size(); i++){
            double lat=lats.get(i);
            double lng=longs.get(i);

            xyValueArray.add(new XYValue(lng,lat));

        }

        ArrayList<XYValue> dataVals=new ArrayList<XYValue>();


        xyValueArray = sortArray(xyValueArray);
        Log.d("xyvalue",String.valueOf(xyValueArray.get(0).getX()));
        //add the data to the series
        for(int i = 0;i <xyValueArray.size(); i++){
            double x = xyValueArray.get(i).getX();
            double y = xyValueArray.get(i).getY();
            xySeries.appendData(new DataPoint(x,y),true, 1000);
        }double max=0;
        createScatterPlot();
    }public double max()

    {
        //double max = 0;
        double max=xyValueArray.get(0).getY();

        for (int i = 0; i < xyValueArray.size(); i++) {
            if (xyValueArray.get(i).getY() > max) {
                max = xyValueArray.get(i).getY();
            }
        }
        return max;
    }
    public double min()

    {
        double maxi = xyValueArray.get(0).getY();
        Log.d("maxxxx",String .valueOf(maxi));

        for (int i = 0; i < xyValueArray.size(); i++) {
            if (xyValueArray.get(i).getY() < maxi) {
                maxi = xyValueArray.get(i).getY();
            }
        }
        return maxi;
    }

    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    private void createScatterPlot() {
        Log.d(TAG, "createScatterPlot: Creating scatter plot.");





        //set some properties
        xySeries.setShape(PointsGraphSeries.Shape.RECTANGLE);
        xySeries.setColor(Color.BLUE);
        xySeries.setSize(3f);
//        xySeries2.setShape(PointsGraphSeries.Shape.TRIANGLE);
//        xySeries2.setColor(Color.GREEN);
//        xySeries2.setSize(7f);
//        xySeries3.setShape(PointsGraphSeries.Shape.TRIANGLE);
//        xySeries3.setColor(Color.RED);
//        xySeries3.setSize(7f);


        //set Scrollable and Scaleable
        mScatterPlot.getViewport().setScalable(true);
        mScatterPlot.getViewport().setScalableY(true);
        mScatterPlot.getViewport().setScrollable(true);
        mScatterPlot.getViewport().setScrollableY(true);

        //set manual x bounds
        mScatterPlot.getViewport().setYAxisBoundsManual(true);
        Log.d("emini",String.valueOf(min()));
        Log.d("eminao",String.valueOf(max()));
        Log.d("eminini",String.valueOf(xyValueArray.get(0).getX()));
        Log.d("eminaononi",String.valueOf(xyValueArray.get(xyValueArray.size()-1).getX()));
        mScatterPlot.getViewport().setMinY(min());
        mScatterPlot.getViewport().setMaxY((max()));
//        mScatterPlot.getViewport().setMaxY(6);
//        mScatterPlot.getViewport().setMinY(7);

        //set manual y bounds
        mScatterPlot.getViewport().setXAxisBoundsManual(true);

        mScatterPlot.getViewport().setMinX(xyValueArray.get(0).getX());
        mScatterPlot.getViewport().setMaxX(xyValueArray.get(xyValueArray.size()-1).getX());
        //mScatterPlot.getViewport().setMinX(3);
        //mScatterPlot.getViewport().setMaxX(4);
        mScatterPlot.addSeries(xySeries);
//        mScatterPlot.addSeries(xySeries2);
//        mScatterPlot.addSeries(xySeries3);
    }

    /**
     * Sorts an ArrayList<XYValue> with respect to the x values.
     * @param array
     * @return
     */
    private ArrayList<XYValue> sortArray(ArrayList<XYValue> array){
        /*
        //Sorts the xyValues in Ascending order to prepare them for the PointsGraphSeries<DataSet>
         */
        int factor = Integer.parseInt(String.valueOf(Math.round(Math.pow(array.size(),2))));
        int m = array.size()-1;
        int count = 0;
        Log.d(TAG, "sortArray: Sorting the XYArray.");

        while(true){
            m--;
            if(m <= 0){
                m = array.size() - 1;
            }
            Log.d(TAG, "sortArray: m = " + m);
            try{
                //print out the y entrys so we know what the order looks like
                //Log.d(TAG, "sortArray: Order:");
                //for(int n = 0;n < array.size();n++){
                //Log.d(TAG, "sortArray: " + array.get(n).getY());
                //}
                double tempY = array.get(m-1).getY();
                double tempX = array.get(m-1).getX();
                if(tempX > array.get(m).getX() ){
                    array.get(m-1).setY(array.get(m).getY());
                    array.get(m).setY(tempY);
                    array.get(m-1).setX(array.get(m).getX());
                    array.get(m).setX(tempX);
                }
                else if(tempY == array.get(m).getY()){
                    count++;
                    Log.d(TAG, "sortArray: count = " + count);
                }

                else if(array.get(m).getX() > array.get(m-1).getX()){
                    count++;
                    Log.d(TAG, "sortArray: count = " + count);
                }
                //break when factorial is done
                if(count == factor ){
                    break;
                }
            }catch(ArrayIndexOutOfBoundsException e){
                Log.e(TAG, "sortArray: ArrayIndexOutOfBoundsException. Need more than 1 data point to create Plot." +
                        e.getMessage());
                break;
            }
        }
        return array;
    }


    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    } public void viewplot(View v)
    {
        // starting background task to update product
        Intent fp=new Intent(getApplicationContext(),MappingForm.class);
        startActivity(fp.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
    }
    public void onBackPressed()
    {
        //super.onBackPressed();
        new AlertDialog.Builder(viewplot.this)
                .setTitle("Certification")
                .setMessage("Do you wish to remap the field or Continue?")
                .setPositiveButton("YES, REMAP", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //TODO 2. This is where I send the data to your app, check the response function for the keys

                        Intent intent = new Intent(viewplot.this,MainActivity.class);


                            startActivity(intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));


                    }
                })

                .setNegativeButton("NO, CONTINUE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(viewplot.this,MappingForm.class);
                        startActivity(intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));


                    }
                })
                .show();


    }



}