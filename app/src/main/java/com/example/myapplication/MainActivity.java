package com.example.myapplication;

import android.content.res.Resources;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends Activity
{
    class Pair
    {
        int pos,size;
        Pair()
        {
            pos=0;
            size=40;
        }
    }
    int i=0,size1=40;
    ArrayList<Pair> al=new ArrayList<>();
    int size=70;
    int x=size;
    public static int getWidth()
    {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(new GUIs(this));
        /*final View view = findViewById(R.id.im1);
        final Animation anim = AnimationUtils.loadAnimation(this, R.anim.scale);
        Button button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(anim);
            }
        });*/

    }
    public class GUIs extends View implements Runnable
    {
        DisplayMetrics displayMetrics=new DisplayMetrics();

        Thread t=new Thread();
        Paint paint=new Paint();
        public GUIs(Context context)
        {
            super(context);
        }
        public void start()
        {
            t.start();
        }
        public void run()
        {
            try
            {
                Thread.sleep(1000);
            }catch(InterruptedException e){}
        }
        @Override
        public void onDraw(Canvas canvas)
        {
            super.onDraw(canvas);

            paint.setStyle(Paint.Style.FILL);
            paint.setStrokeWidth(1);
            paint.setColor(Color.YELLOW);
            canvas.drawRect(0,0,getWidth(),getHeight(),paint);
            paint.setColor(Color.RED);
            canvas.drawCircle(100,500,size,paint);
            int y=size;
            /**y is a temporary variable. x is the size of main disc in the previous iteration */

            /**if disc was of max size in the previous iteration*/
            if(x==70){
                size=size-3;
            }

            /**if the disc was of minimum size in previous iteration*/
            else if(x==40){
                size=size+3;
            }

            /**if the disc is shrinking*/
            else if(x>size){
                size=size-3;
            }

            /**if the disc is expanding*/
            else if(x<size){
                size=size+3;
            }

            /**when disc is midway & shrinking, new disc is formed*/
            if(size==55 && x>size) {
                al.add(new Pair());
            }
            x=y;

            int l=al.size();
            int draw;

            /**moving the new formed disc towards right*/
            for(int ii=0;ii<l;ii++)
            {
                draw=1;
                Pair obj=al.get(ii);
                if(obj.pos<=600)
                    obj.pos=obj.pos+10;
                else
                {
                    obj.size=obj.size-2;

                    /**remove disc once it shrinks to 0 size*/
                    if(obj.size==0)
                    {
                        draw=0;
                        al.remove(ii);
                    }
                }
                if(draw==1) {
                    al.set(ii, obj);
                    canvas.drawCircle(100 + al.get(ii).pos, 500, al.get(ii).size, paint);
                }
                else
                {
                    l=al.size();
                }
            }
            /*canvas.drawCircle(100+i, 500, size1, paint);
            if(i<=400)
                i+=10;
            else
            {
                size1--;
                if(size1==1)
                {
                    i=50;
                    size1=20;
                }
            }*/
            /*if(i>getWidth())
                i=50;*/
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            invalidate();
        }

    }
}