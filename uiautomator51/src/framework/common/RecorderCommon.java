package framework.common;

import static framework.data.ObjectType.*;
import static framework.data.OperationType.*;
import static framework.data.ResIdTextAndDesc.*;
import static framework.excute.Excute.*;
import junit.framework.Assert;
import android.graphics.Rect;

import android.os.RemoteException;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;


import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.android.uiautomator.core.UiDevice;
import framework.common.DeviceCommon;
import testcase.Recorder;;

public  class RecorderCommon {
	
	
	
	/**
	 * 检查初始状态，若不是，则恢复初始状态
	 */
	public static void checkState (){
		
		if((boolean)check(Object_Text, Operation_Exists, "录音"))
		{
			excute(Object_ResourceId,Operation_ClickWait,"com.android.soundrecorder:id/stopButton");
			if((boolean)excute(Object_Text, Operation_Exists, "取消"))
			{
				excute(Object_Text,Operation_ClickWait,"取消");
			}
		}
	}
	
	
	/**
	 * 创建一个录音文件
	 */
	public static void creatRecorder(){
		Rect rect_start =(Rect)excute(Object_ResourceId,Operation_GetBounds,"com.android.soundrecorder:id/recordButton");
		Rect  rect_stop =(Rect)excute(Object_ResourceId,Operation_GetBounds,"com.android.soundrecorder:id/stopButton");
		//excute(Object_ResourceId,Operation_ClickWait,"com.android.soundrecorder:id/recordButton");
		UiDevice.getInstance().click(rect_start.centerX(), rect_start.centerY());
		Wait(1500);
		//excute(Object_ResourceId,Operation_ClickWait,"com.android.soundrecorder:id/stopButton");
		UiDevice.getInstance().click(rect_stop.centerX(),rect_stop.centerY());
		excute(Object_Text,Operation_ClickWait,"确定");
	}
}