package framework.common;

import static framework.data.DeviceParameter.*;
import static framework.data.ObjectType.*;
import static framework.data.OperationType.*;
import static framework.data.ResIdTextAndDesc.*;
import static framework.excute.Excute.*;

//import java.awt.image.*;
import java.io.File;
import java.io.IOException;

//import javax.imageio.ImageIO;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

import framework.driver.AndroidObject;

public class MusicPlayerCommon 
{
	
	public static void addnewplaylist()
	{
		excute(Object_Text, Operation_ClickWait, "歌曲");
		excute(Object_ResourceId, Operation_LongClick, "com.android.music:id/line1");
		excute(Object_Text, Operation_ClickWait, "添加到播放列表");
		excute(Object_Text, Operation_ClickWait, "新建播放列表");
		String playlistName = (String)excute(Object_ResourceId, Operation_GetText, "com.android.music:id/playlist");
		excute(Object_Text, Operation_ClickWait, "保存");
	}
	public static void longclicknewplaylist(String playlistName)
	{
		excute(Object_Text, Operation_ClickWait, "播放列表");
		excute(Object_Text, Operation_LongClick, playlistName);
	}
	public static void addandlongclicknewplaylist()
	{
		excute(Object_Text, Operation_ClickWait, "歌曲");
		excute(Object_ResourceId, Operation_LongClick, "com.android.music:id/line1");
		excute(Object_Text, Operation_ClickWait, "添加到播放列表");
		excute(Object_Text, Operation_ClickWait, "新建播放列表");
		String playlistName = (String)excute(Object_ResourceId, Operation_GetText, "com.android.music:id/playlist");
		excute(Object_Text, Operation_ClickWait, "保存");
		excute(Object_Text, Operation_ClickWait, "播放列表");
		excute(Object_Text, Operation_LongClick, playlistName);
	}
	public static void deleteallplaylist()
	{
		int num = (Integer) excute(Object_ResourceId, Operation_GetChildCount, "android:id/list");
		for (int i=1; i<num; i++)
		{
			excute(Object_ResIdInstance, Operation_LongClick, "com.android.music:id/icon", "1");
			excute(Object_Text, Operation_ClickWait, "删除");
			excute(Object_Text, Operation_ClickWait, "确定");
		}
		
	}

//	public static void screenshort(String Name) {
//		try {
//		//从特定文件载入
//		Rect ModArea = (Rect) excute(Object_ResourceId, Operation_GetBounds, "com.android.music:id/shuffle");
//		int x = ModArea.right-ModArea.left;
//		int y = ModArea.bottom-ModArea.top;
//		BufferedImage bi = ImageIO.read(new File("/sdcard/Temp/"+Name+".png"));
//		bi.getSubimage(ModArea.left, ModArea.top, x, y);//前两个值是坐标位置X、Y，后两个是长和宽
//		} catch (IOException e) {
//		e.printStackTrace();
//		}
//		}

	/**
	 * 获得暂停状态播放的时间
	 */
	public static String getTimeInPause()
	{
		UiObject uiObject=null;
		String curTime="";

		int count = 600;
		while( count-->0 )
		{
			try{
					uiObject = (UiObject ) excute(Object_ResourceId,Operate_ReturnObject,"com.android.music:id/currenttime");
					curTime = uiObject.getText();
					break;
			}
			catch (Exception e) {
				//e.printStackTrace();
				System.out.println("meet with exception but do nothing");
			}
		}
		//System.out.println("count"+count);
		/*System.out.println("Start wait");
		uiObject.waitForExists(50000);
		curTime = uiObject.getText();*/
		return curTime;
		//System.out.println("curTime"+curTime);
	}

}