package com.apporioinfolabs.apporiotaxislidingbutton;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.ncorti.slidetoact.SlideToActView;

public class ApporioTaxiSlidingButton extends LinearLayout {


    public static int SLIDING_BUTTON = 0 ;
    public static int STRICT_BUTTON = 1 ;
    private static int BUTTON_TYPE = SLIDING_BUTTON ;


    private Context mContext  = null  ;
    private static final String TAG = "ApporioTaxiSlidingButto";
    private SlideToActView slideToActView;
    private LinearLayout loading_layout ;
    private TextView strict_button, cancel_button, loading_text ;
    private OnTaxiSlidngListener onTaxiSlidngListener ;
    private boolean cancelVisibility = false ;

    private static String displayText = "Sample text";


    public ApporioTaxiSlidingButton(Context context) {
        super(context);
        initializeViews(context);
    }

    public ApporioTaxiSlidingButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initializeViews(context);
    }

    public ApporioTaxiSlidingButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initializeViews(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ApporioTaxiSlidingButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initializeViews(context);
    }


    private void initializeViews(Context context) {
        mContext = context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.sliding_button_view, this);

    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        try{
            slideToActView = findViewById(R.id.sliding_button_view);
            loading_layout = findViewById(R.id.loading_layout);
            strict_button = findViewById(R.id.strict_button);
            cancel_button = findViewById(R.id.cancel_button);
            loading_text = findViewById(R.id.loading_text);

          slideToActView.setOnSlideCompleteListener(new SlideToActView.OnSlideCompleteListener() {
              @Override
              public void onSlideComplete(SlideToActView slideToActView) {
                  onTaxiSlidngListener.onAction();
              }
          });

          strict_button.setOnClickListener(new OnClickListener() {
              @Override
              public void onClick(View view) {
                  onTaxiSlidngListener.onAction();
              }
          });

          cancel_button.setOnClickListener(new OnClickListener() {
              @Override
              public void onClick(View view) {
                  onTaxiSlidngListener.onCancel();
              }
          });


          setButtonType(SLIDING_BUTTON);
        }catch (Exception e){
            Log.e(TAG, ""+e.getMessage() );
        }
    }


    public void setButtonType(int type){
        BUTTON_TYPE = type ;
        if(BUTTON_TYPE == STRICT_BUTTON){
            slideToActView.setVisibility(GONE);
            strict_button.setVisibility(VISIBLE);
        }if(BUTTON_TYPE == SLIDING_BUTTON){
            slideToActView.setVisibility(VISIBLE);
            strict_button.setVisibility(GONE);
        }
        slideToActView.resetSlider();
        setText(displayText);
        setCancelable(cancelVisibility);
    }




    public void setText(String text){
        displayText = text ;
        slideToActView.setText(""+displayText);
        strict_button.setText(""+displayText);
    }
    public void startLoading(String loadingText){
        loading_text.setText(""+loadingText);
        slideToActView.setVisibility(GONE);
        strict_button.setVisibility(GONE);
        cancel_button.setVisibility(GONE);
        loading_layout.setVisibility(VISIBLE);
    }
    public void stopLoading(){
        setButtonType(BUTTON_TYPE);
        loading_layout.setVisibility(GONE);
    }

    public void setCancelable(boolean value){
        cancelVisibility = value ;
        if(cancelVisibility){ cancel_button.setVisibility(VISIBLE); }
        else{ cancel_button.setVisibility(GONE); }
    }

    public void setButtonColour(String colour){
        slideToActView.setOuterColor(Color.parseColor("#"+colour));
        strict_button.setBackgroundColor(Color.parseColor("#"+colour));

    }
    public void setButtonColour(int colour){
        slideToActView.setOuterColor(colour);
        strict_button.setBackgroundColor(colour);
    }

    public void setLoadingColour(String color){
        loading_layout.setBackgroundColor(Color.parseColor("#"+color));
    }




    public void setListeners(OnTaxiSlidngListener onTaxiSlidngListener){
        this.onTaxiSlidngListener = onTaxiSlidngListener ;
    }


    public interface OnTaxiSlidngListener {
        void onAction();
        void onCancel();

    }



}
