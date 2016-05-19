package framework.common;

import static framework.data.ObjectType.*;
import static framework.data.OperationType.*;
import static framework.data.ResIdTextAndDesc.Devices_Desc_Browser;
import static framework.excute.Excute.*;

import java.text.Collator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.graphics.Rect;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObjectNotFoundException;



public class DownloadCommon {
	
	/**
	 * 切换视图：网格视图，列表视图
	 */
	public static void SwitchMode(String type){
		excute(Object_Description,Operation_ClickWait,"更多选项");
		String txt = (String)excute(Object_ResourceId,Operation_GetText,"android:id/title");
		if(type.equals(txt)){
			excute(Object_ResourceId,Operation_ClickWait,"android:id/title");
		}
		else{
			excute(Object_Device, Operation_PressBack);
		}
	}
	
	/**
	 * 下载App从百度手机卫士
	 * @param num
	 * @throws UiObjectNotFoundException 
	 */
	public static void downloadAPP(int num) throws UiObjectNotFoundException{
		for(int i=0;i<num;i++){
		DeviceCommon.enterApp(Devices_Desc_Browser);
		excute(Object_ResourceId,Operation_ClickWait,"com.android.browser:id/url");
		excute(Object_ResourceId,Operation_SetText,"com.android.browser:id/url","shouji.baidu.com");
		excute(Object_Device, Operation_PressEnter);
		excute(Object_Description,Operation_WaitForExists,"百度手机助手 最具人气的应用商店","100000");
		Rect download = (Rect) excute(Object_DescInstance,Operation_GetBounds,"下载",String.valueOf(i));
		int x = download.centerX();
		int y = download.bottom;
		UiDevice.getInstance().click(x, y);
		excute(Object_ResourceId,Operation_WaitForExists,"android:id/alertTitle","100000");
		excute(Object_Text,Operation_ClickWait,"下载");
		excute(Object_ResourceId,Operation_WaitForExists,"com.android.browser:id/tabs","100000");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.browser:id/tabs");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.browser:id/closetab");
		}
	}
	

}