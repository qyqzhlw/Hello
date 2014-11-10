package cn.csdn.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONObject;

import android.R.string;

public class JsonUtil {
	public JSONObject query(ArrayList<string> para) throws Exception
	{
		// 使用Map封装请求参数
		Map<String, String> map = new HashMap<String, String>();
		Iterator<string> it=para.iterator(); 
		while(it.hasNext()){ 
		    string s1=it.next();
		    string s2=it.next();
//		    map.put(s1,s2);
		}

		// 定义发送请求的URL
		String url = HttpUtil.BASE_URL + "processLogin.action";
		// 发送请求
		return new JSONObject(HttpUtil.postRequest(url, map));
	}
	
  }
