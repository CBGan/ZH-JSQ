package sdt.team.zhjsq;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Menu extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
    }
    public void fun(View v)
    {
        switch (v.getId())
        {
            case R.id.functionDrawer:
                Intent openFunctionDrawer=new Intent(Menu.this,FunctionDrawer.class);
                startActivity(openFunctionDrawer);
        }
    }
}
