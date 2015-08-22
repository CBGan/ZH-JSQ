package sdt.team.zhjsq;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;


public class LoadActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        getWindow().setFormat(PixelFormat.RGBA_8888);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DITHER);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent LoadActivity = new Intent(LoadActivity.this,Menu.class);
                LoadActivity.this.startActivity(LoadActivity);
                LoadActivity.this.finish();
            }
        },1500);
    }
}