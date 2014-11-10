package cn.csdn.activity;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import cn.csdn.util.HttpUtil;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity{

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.main);
	   	
	    Button bn1 = (Button)findViewById(R.id.text_two);
	    bn1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				try {
					JSONArray a = getUser();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	
	}

	  private JSONArray getUser() throws Exception
		{
		
			// 定义发送请求的URL
			String url = HttpUtil.BASE_URL+ "ContactList.jsp";
	//		DialogUtil.showDialog(this,url,false);
			// 发送请求
			return new JSONArray(HttpUtil.postRequest(url, null));
		}
		
		
	


	
	
	


}
