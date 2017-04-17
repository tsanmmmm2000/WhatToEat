package com.whattoeat;

import java.util.Random;

import com.whattoeat.CustomAnimDrawable.AnimationDrawableListener;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends Activity implements OnClickListener{
	
	ImageView iv = null;
	String selected_store = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
				
		Button go = (Button) findViewById(R.id.go);
		iv = (ImageView) findViewById(R.id.imagestore);        
        go.setOnClickListener(this); 
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	   @Override
		public void onClick(View v) {
			// TODO Auto-generated method stub		
		   
		   RelativeLayout layout =(RelativeLayout)findViewById(R.id.background);
		   layout.setBackgroundResource(R.drawable.bg_dwon);
	        
		   
	        iv.setBackgroundResource(R.drawable.store);
	        
	        	
		    AnimationDrawable anim = null;
		    Object ob = iv.getBackground();
		    anim = (AnimationDrawable) ob;

		    
		    CustomAnimDrawable cusAnim = new CustomAnimDrawable(anim);
		    cusAnim.setAnimationListener(new FrameAnimationListener());  
		    cusAnim.stop();
		    cusAnim.start();
		    
	        
		}
		
		class FrameAnimationListener implements AnimationDrawableListener{
			@Override
			public void onAnimationEnd(AnimationDrawable animation) {
				int chose_food = getRandom(0,22);
		        selected_store = food[chose_food];
				iv.setBackgroundResource(R.drawable.store00+chose_food);
				RelativeLayout layout =(RelativeLayout)findViewById(R.id.background);
				layout.setBackgroundResource(R.drawable.bg_up);
				dialog(selected_store);
				
			}
			@Override
			public void onAnimationStart(AnimationDrawable animation) {
				
			}
		}
	
	private int getRandom(int min,int max){
        long seed = System.currentTimeMillis();
        Random r = new Random(); 
        r.setSeed(seed);        
        return (min + r.nextInt(max-min+1));
	}
	
	   protected void dialog(String store){
	        new AlertDialog.Builder(this)
	        .setTitle("就決定是你了！")
	        .setIcon(R.drawable.decided_monkey)
	        .setMessage("今天就吃"+store+"吧！")
	        .setPositiveButton("確定", new DialogInterface.OnClickListener() {
	            @Override
	            public void onClick(DialogInterface dialog, int which) {
	                dialog.dismiss();
	                //要讓他先取不同資源，不然下次又一樣的話他動畫不會轉
	    		    iv.setBackgroundResource(R.drawable.store_origin);
	            }
	        })
	        .show();
	    }
	   
	   private static final String[] food = new String[]{"羅師傅","八方雲集","大丸","丹丹漢堡","元福","百利","老饕燒肉飯","吳師父滷味","吳記水餃",
       		
       "拉波米亞","阿珠","阿雪","食在香","桌上賓","海灣拉麵","麻醬館","朝月冷","湘園","媽媽的手藝","愛買","福鴻","劉江便當","養生牛肉麵"};

		@Override 
	    public boolean onKeyDown(int keyCode, KeyEvent event) {
	        if(keyCode==KeyEvent.KEYCODE_BACK && event.getRepeatCount()==0){
	            dialog();
	            return true;
	        }else{
	            return super.onKeyDown(keyCode, event);
	        }
	    }

	    protected void dialog(){
	        new AlertDialog.Builder(this)
	        .setTitle("提示")
	        .setMessage("確定要退出嗎？")
	        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
	            @Override
	            public void onClick(DialogInterface dialog, int which) {
	                dialog.dismiss();
	            }
	        })
	        .setPositiveButton("確定", new DialogInterface.OnClickListener() {
	            @Override
	            public void onClick(DialogInterface dialog, int which) {
	                Intent home = new Intent(Intent.ACTION_MAIN);  
	                home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  
	                home.addCategory(Intent.CATEGORY_HOME);  
	                startActivity(home);  
	            }
	        })
	        .show();
	    }

	   
}
