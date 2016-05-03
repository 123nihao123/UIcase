package testcase;

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
import framework.common.RecorderCommon;;

public class Recorder extends UiAutomatorTestCase 
{
	static Rect rect_start =null;
	static Rect  rect_stop =null;
	protected void setUp() throws UiObjectNotFoundException, RemoteException 
    {
		System.out.println("Enter the setUp!!!");	
		excute(Object_Device, Operation_WakeUp);
		DeviceCommon.unLock();	
		ClearBackgroundApp();
		DeviceCommon.enterApp( Devices_Desc_Recorder);
		UiDevice.getInstance().waitForWindowUpdate("com.android.soundrecorder", 2000);
		RecorderCommon.checkState();
		rect_start =(Rect)excute(Object_ResourceId,Operation_GetBounds,"com.android.soundrecorder:id/recordButton");
	    rect_stop =(Rect)excute(Object_ResourceId,Operation_GetBounds,"com.android.soundrecorder:id/stopButton");
   }
	@Override
	protected void tearDown() throws UiObjectNotFoundException, RemoteException 
    {
		
    }
	
	/**
	 * 进入录音机，恢复初始状态
	 */
	public static void test_000() throws UiObjectNotFoundException
	{
		
		//主体
		check(Object_ResourceId, Operation_checkExist, "com.android.soundrecorder:id/recordButton");
		if((boolean)check(Object_Text, Operation_Exists, "录音"))
		{
			
			UiDevice.getInstance().click(rect_stop.centerX(),rect_stop.centerY());
			if((boolean)check(Object_Text, Operation_Exists, "取消"))
			{
				excute(Object_Text,Operation_ClickWait,"取消");
			}
		}
		UiDevice.getInstance().click(rect_stop.centerX(),rect_stop.centerY());
		//Wait(1000);
		UiDevice.getInstance().waitForWindowUpdate("com.android.soundrecorder", 2000);
		if(!(boolean)check(Object_Text, Operation_Exists, "列表为空"))
		{
			System.out.println("start");
			excute(Object_ResourceId,Operation_LongClick,"com.android.soundrecorder:id/recode_icon");
			UiObject object = new UiObject(new UiSelector().text("全选"));
			if(object.isEnabled())
			{
				System.out.println("000000");
				excute(Object_Text,Operation_ClickWait,"全选");
			}
			excute(Object_Text,Operation_ClickWait,"删除");
			excute(Object_Text,Operation_ClickWait,"确定");
		}
		excute(Object_Device, Operation_PressBack);
		 
		
		
	}
	
	/**
	 * 进入录音机
	 */
	public static void test_001() 
	{
		//主体
		check(Object_ResourceId, Operation_checkExist, "com.android.soundrecorder:id/recordButton");
	}
	/**
	 * 录音
	 */
	public static void test_002() 
	{
		
		//主体
		UiDevice.getInstance().click(rect_start.centerX(), rect_start.centerY());
		Wait(2000);
		check(Object_Text, Operation_checkExist, "录音");
		//清场
		if((boolean)check(Object_Text, Operation_Exists, "录音"))
		{
			UiDevice.getInstance().click(rect_stop.centerX(),rect_stop.centerY());
			excute(Object_Text,Operation_ClickWait,"取消");
		}
	}
	/**
	 * 录音暂停
	 */
	public static void test_003()
	{
		//前提
		
		UiDevice.getInstance().click(rect_start.centerX(), rect_start.centerY());
		//主体
		Wait(2000);
		UiDevice.getInstance().click(rect_start.centerX(), rect_start.centerY());
		String old_time =(String)excute(Object_ResourceId, Operation_GetText, "com.android.soundrecorder:id/timerView");
		Wait(1500);
		String new_time =(String)excute(Object_ResourceId, Operation_GetText, "com.android.soundrecorder:id/timerView");
		Assert.assertTrue("time is same",old_time.equals(new_time));
		//清场
		if((boolean)check(Object_Text, Operation_Exists, "录音"))
		{
			UiDevice.getInstance().click(rect_stop.centerX(),rect_stop.centerY());
			excute(Object_Text,Operation_ClickWait,"取消");
		}
		
	}
	/**
	 * 暂停状态下继续
	 */
	public static void test_004()
	{
		//前提
		
		UiDevice.getInstance().click(rect_start.centerX(), rect_start.centerY());
		Wait(2000);
		UiDevice.getInstance().click(rect_start.centerX(), rect_start.centerY());
		//主体
		String old_time =(String)excute(Object_ResourceId, Operation_GetText, "com.android.soundrecorder:id/timerView");
		UiDevice.getInstance().click(rect_start.centerX(), rect_start.centerY());
		Wait(2000);
		String new_time =(String)excute(Object_ResourceId, Operation_GetText, "com.android.soundrecorder:id/timerView");
		Assert.assertFalse("is start",old_time.equals(new_time));
		//清场
		if((boolean)check(Object_Text, Operation_Exists, "录音"))
		{
			UiDevice.getInstance().click(rect_stop.centerX(),rect_stop.centerY());
			excute(Object_Text,Operation_ClickWait,"取消");
		}
	}
	/**
	 * 暂停状态下停止
	 */
	public static void test_005()
	{
		//前提
		
		UiDevice.getInstance().click(rect_start.centerX(), rect_start.centerY());
		Wait(2000);
		UiDevice.getInstance().click(rect_start.centerX(), rect_start.centerY());
		//主体
		UiDevice.getInstance().click(rect_stop.centerX(),rect_stop.centerY());
		//UiDevice.getInstance().waitForWindowUpdate("com.android.soundrecorder", 2000);
		//Wait(1000);
		check(Object_Text,Operation_checkExist,"是否保存录音？");
		//清场
		excute(Object_Text,Operation_ClickWait,"取消");
	}
	/**
	 * 录音停止状态下点取消
	 */
	public static void test_006()
	{
		//前提
		

		UiDevice.getInstance().click(rect_start.centerX(), rect_start.centerY());
		Wait(2000);
		UiDevice.getInstance().click(rect_start.centerX(), rect_start.centerY());
		//主体
		UiDevice.getInstance().click(rect_stop.centerX(),rect_stop.centerY());
		excute(Object_Text,Operation_ClickWait,"取消");
		UiDevice.getInstance().click(rect_stop.centerX(),rect_stop.centerY());
		
		check(Object_Text,Operation_checkExist,"列表为空");
	}
	/**
	 * 停止状态下点确定保存录音
	 */
	public static void test_007() throws UiObjectNotFoundException
	{
		//前提
		
		UiDevice.getInstance().click(rect_start.centerX(), rect_start.centerY());
		Wait(2000);
		UiDevice.getInstance().click(rect_start.centerX(), rect_start.centerY());
		//主体
		UiDevice.getInstance().click(rect_stop.centerX(),rect_stop.centerY());
		excute(Object_Text,Operation_ClickWait,"确定");
		UiDevice.getInstance().click(rect_stop.centerX(),rect_stop.centerY());
		UiDevice.getInstance().waitForWindowUpdate("com.android.soundrecorder", 2000);
		check(Object_Text,Operation_checkNoExist,"列表为空");
		
		//清场
		
		if(!(boolean)check(Object_Text,Operation_Exists,"列表为空"))
		{
			excute(Object_ResourceId,Operation_LongClick,"com.android.soundrecorder:id/recode_icon");
			UiObject object = new UiObject(new UiSelector().text("全选"));
			if(object.isEnabled())
			{
				excute(Object_Text,Operation_ClickWait,"全选");
			}
			excute(Object_Text,Operation_ClickWait,"删除");
			excute(Object_Text,Operation_ClickWait,"确定");
		}
		
	}
	/**
	 * 没有录音文件下进入录音文件列表
	 */
	public static void test_008()
	{
		//前提
		
		//主体
		UiDevice.getInstance().click(rect_stop.centerX(),rect_stop.centerY());
		UiDevice.getInstance().waitForWindowUpdate("com.android.soundrecorder", 2000);
		check(Object_Text,Operation_checkExist,"录音列表");
		
	}
	/**
	 * 有录音文件下进入录音文件列表
	 */
	public static void test_009() throws UiObjectNotFoundException
	{ 
		//前提
		
		RecorderCommon.creatRecorder();
		//主体
		UiDevice.getInstance().click(rect_stop.centerX(),rect_stop.centerY());
		UiDevice.getInstance().waitForWindowUpdate("com.android.soundrecorder", 2000);
		check(Object_Text,Operation_checkExist,"录音列表");
		//清场
		if(!(boolean)check(Object_Text,Operation_Exists,"列表为空"))
		{
			excute(Object_ResourceId,Operation_LongClick,"com.android.soundrecorder:id/recode_icon");
			UiObject object = new UiObject(new UiSelector().text("全选"));
			if(object.isEnabled())
			{
				excute(Object_Text,Operation_ClickWait,"全选");
			}
			excute(Object_Text,Operation_ClickWait,"删除");
			excute(Object_Text,Operation_ClickWait,"确定");
		}
		
	}
	/**
	 * 在录音列表中长按文件
	 */
	public static void test_010() throws UiObjectNotFoundException
	{
		//前提
		
		RecorderCommon.creatRecorder();
		//主体
		UiDevice.getInstance().click(rect_stop.centerX(),rect_stop.centerY());
		excute(Object_ResourceId,Operation_LongClick,"com.android.soundrecorder:id/recode_icon");
		check(Object_Text,Operation_checkExist,"删除");
		//清场
		UiObject object = new UiObject(new UiSelector().text("全选"));
		if(object.isEnabled())
		{
			excute(Object_Text,Operation_ClickWait,"全选");
		}
		excute(Object_Text,Operation_ClickWait,"删除");
		excute(Object_Text,Operation_ClickWait,"确定");
		
	}
	/**
	 * 有录音文件前提下在录音列表中单击全选
	 */
	public static void test_011()
	{
		//前提
		
		RecorderCommon.creatRecorder();
		RecorderCommon.creatRecorder();
		//主体
		UiDevice.getInstance().click(rect_stop.centerX(),rect_stop.centerY());
		UiDevice.getInstance().waitForWindowUpdate("com.android.soundrecorder", 2000);
		excute(Object_ResourceId,Operation_LongClick,"com.android.soundrecorder:id/recode_icon");
		excute(Object_Text,Operation_ClickWait,"全选");
		check(Object_Text,Operation_checkNoExist,"1");
		//check(Object_ResourceId,Operation_CheckedTrue,"com.android.soundrecorder:id/recode_checkbox");
		//清场
		excute(Object_Text,Operation_ClickWait,"删除");
		excute(Object_Text,Operation_ClickWait,"确定");
		
	}
	/**
	 * 有录音文件的时候，且录音机处于非录音状态，长按一个文件，点击取消选择
	 */
	public static void test_012()
	{
		//前提
		
		RecorderCommon.creatRecorder();
		//主体
		UiDevice.getInstance().click(rect_stop.centerX(),rect_stop.centerY());
		excute(Object_ResourceId,Operation_LongClick,"com.android.soundrecorder:id/recode_icon");
		excute(Object_Text,Operation_ClickWait,"取消选择");
		check(Object_Text,Operation_EnabledFalse,"删除");
		//清场
		excute(Object_Text,Operation_ClickWait,"全选");
		excute(Object_Text,Operation_ClickWait,"删除");
		excute(Object_Text,Operation_ClickWait,"确定");
		
	}
	/**
	 * 有录音文件的时候，且录音机处于非录音状态，长按一个文件，选中文件只有一个时，页面上的三个功能键“删除”“文件路径”“重命名”都处于可用状态
	 */
	public static void test_013()
	{
		//前提
		
		RecorderCommon.creatRecorder();
		//主体
		UiDevice.getInstance().click(rect_stop.centerX(),rect_stop.centerY());
		excute(Object_ResourceId,Operation_LongClick,"com.android.soundrecorder:id/recode_icon");
		check(Object_Text,Operation_EnabledTrue,"删除");
		check(Object_Text,Operation_EnabledTrue,"文件路径");
		check(Object_Text,Operation_EnabledTrue,"重命名");
		//清场
		excute(Object_Text,Operation_ClickWait,"全选");
		excute(Object_Text,Operation_ClickWait,"删除");
		excute(Object_Text,Operation_ClickWait,"确定");
		
	}
	/**
	 * 有录音文件的时候，且录音机处于非录音状态，长按一个文件，当选中文件多于一个时，页面上的三个功能键“删除”“文件路径”“重命名”，只有“删除”可用
	 */
	public static void test_014()
	{
		//前提
		
		RecorderCommon.creatRecorder();
		RecorderCommon.creatRecorder();
		//主体
		UiDevice.getInstance().click(rect_stop.centerX(),rect_stop.centerY());
		excute(Object_ResourceId,Operation_LongClick,"com.android.soundrecorder:id/recode_icon");
		excute(Object_Text,Operation_ClickWait,"全选");
		check(Object_Text,Operation_EnabledTrue,"删除");
		check(Object_Text,Operation_EnabledFalse,"文件路径");
		check(Object_Text,Operation_EnabledFalse,"重命名");
		//清场
		//
		excute(Object_Text,Operation_ClickWait,"删除");
		excute(Object_Text,Operation_ClickWait,"确定");
		
	}
	/**
	 * 有录音文件的时候，且录音机处于非录音状态，长按一个文件，选中文件只有一个时，点击页面上的删除
	 */
	public static void test_015()
	{
		//前提
		
		RecorderCommon.creatRecorder();
		//主体
		UiDevice.getInstance().click(rect_stop.centerX(),rect_stop.centerY());
		UiDevice.getInstance().waitForWindowUpdate("com.android.soundrecorder", 2000);
		excute(Object_ResourceId,Operation_LongClick,"com.android.soundrecorder:id/recode_icon");
		excute(Object_Text,Operation_ClickWait,"删除");
		check(Object_Text,Operation_checkExist,"确定");
		//清场
		excute(Object_Text,Operation_ClickWait,"确定");
		
	}
	/**
	 * 有录音文件的时候，且录音机处于非录音状态，长按一个文件，选中文件只有一个时，点击页面上的删除,点击取消，文件还在
	 */
	public static void test_016()
	{
		//前提
		
		RecorderCommon.creatRecorder();
		//主体
		UiDevice.getInstance().click(rect_stop.centerX(),rect_stop.centerY());
		excute(Object_ResourceId,Operation_LongClick,"com.android.soundrecorder:id/recode_icon");
		excute(Object_Text,Operation_ClickWait,"删除");
		excute(Object_Text,Operation_ClickWait,"取消");
		check(Object_ResourceId,Operation_checkExist,"com.android.soundrecorder:id/recode_icon");
		//清场
		excute(Object_Text,Operation_ClickWait,"删除");
		excute(Object_Text,Operation_ClickWait,"确定");
		
	}
	/**
	 * 有录音文件的时候，且录音机处于非录音状态，长按一个文件，选中文件只有一个时，点击页面上的删除,点击确定，文件不存在
	 */
	public static void test_017()
	{
		//前提
		
		RecorderCommon.creatRecorder();
		//主体
		UiDevice.getInstance().click(rect_stop.centerX(),rect_stop.centerY());
		excute(Object_ResourceId,Operation_LongClick,"com.android.soundrecorder:id/recode_icon");
		excute(Object_Text,Operation_ClickWait,"删除");
		UiDevice.getInstance().waitForWindowUpdate("com.android.soundrecorder", 2000);
		excute(Object_Text,Operation_ClickWait,"确定");
		check(Object_ResourceId,Operation_checkNoExist,"com.android.soundrecorder:id/recode_icon");
		//清场
		
	}
	/**
	 * 有录音文件的时候，且录音机处于非录音状态，长按一个文件，选中文件只有一个时，点击页面上的文件路径
	 */
	public static void test_018() throws UiObjectNotFoundException
	{
		//前提
		
		RecorderCommon.creatRecorder();
		//主体
		UiDevice.getInstance().click(rect_stop.centerX(),rect_stop.centerY());
		excute(Object_ResourceId,Operation_LongClick,"com.android.soundrecorder:id/recode_icon");
		excute(Object_Text,Operation_ClickWait,"文件路径");
		check(Object_Text,Operation_checkExist,"文件名称：");
		//清场
		excute(Object_Device, Operation_PressBack);
		excute(Object_ResourceId,Operation_LongClick,"com.android.soundrecorder:id/recode_icon");
		if((boolean)excute(Object_Text,Operation_checkExist,"全选"))
		{
			excute(Object_Text,Operation_ClickWait,"全选");
		}
		excute(Object_Text,Operation_ClickWait,"删除");
		excute(Object_Text,Operation_ClickWait,"确定");
		
	}
	/**
	 * 有录音文件的时候，且录音机处于非录音状态，长按一个文件，选中文件只有一个时，点击页面上的 重命名
	 */
	public static void test_019()
	{
		//前提
		
		RecorderCommon.creatRecorder();
		//主体
		UiDevice.getInstance().click(rect_stop.centerX(),rect_stop.centerY());
		excute(Object_ResourceId,Operation_LongClick,"com.android.soundrecorder:id/recode_icon");
		excute(Object_Text,Operation_ClickWait,"重命名");
		check(Object_Text,Operation_checkExist,"重命名");
		//清场
		excute(Object_Device, Operation_PressBack);
		excute(Object_ResourceId,Operation_LongClick,"com.android.soundrecorder:id/recode_icon");
		if((boolean)excute(Object_Text,Operation_Exists,"全选"))
		{
			excute(Object_Text,Operation_ClickWait,"全选");
		}
		excute(Object_Text,Operation_ClickWait,"删除");
		excute(Object_Text,Operation_ClickWait,"确定");
		
	}
	/**
	 * 有录音文件的时候，且录音机处于非录音状态，长按一个文件，选中文件只有一个时，.点击页面上的 重命名，在重名名对话框上输入新名字，点击取消
	 */
	public static void test_020()  throws UiObjectNotFoundException
	{
		//前提
		
		RecorderCommon.creatRecorder();
		//主体
		UiDevice.getInstance().click(rect_stop.centerX(),rect_stop.centerY());
		String old_name =(String)excute(Object_ResourceId, Operation_GetText, "com.android.soundrecorder:id/record_displayname");
		excute(Object_ResourceId,Operation_LongClick,"com.android.soundrecorder:id/recode_icon");
		excute(Object_Text,Operation_ClickWait,"重命名");
		Rect rect=new UiObject(new UiSelector().className("android.widget.EditText")).getBounds();
		UiDevice.getInstance().click(rect.centerX()+100, rect.centerY());
		for(int i=0;i<30;i++)
		UiDevice.getInstance().pressDelete();
		excute(Object_ClassName,Operation_SetText,"android.widget.EditText","xin");
		excute(Object_Text,Operation_ClickWait,"取消");
		String new_name =(String)excute(Object_ResourceId, Operation_GetText, "com.android.soundrecorder:id/record_displayname");
		Assert.assertTrue("is ok",old_name.equals(new_name));
		//清场
		excute(Object_ResourceId,Operation_LongClick,"com.android.soundrecorder:id/recode_icon");
		if((boolean)excute(Object_Text,Operation_checkExist,"全选"))
		{
			excute(Object_Text,Operation_ClickWait,"全选");
		}
		excute(Object_Text,Operation_ClickWait,"删除");
		excute(Object_Text,Operation_ClickWait,"确定");
		
	}
	/**
	 * 有录音文件的时候，且录音机处于非录音状态，长按一个文件，选中文件只有一个时，.点击页面上的 重命名，在重名名对话框上输入新名字，点击确定
	 */
	public static void test_021() throws UiObjectNotFoundException
	{
		//前提
		
		String Set_Name="xin.amr";
		RecorderCommon.creatRecorder();
		//主体
		UiDevice.getInstance().click(rect_stop.centerX(),rect_stop.centerY());
		String  Initial_Name = (String)excute(Object_ResourceId,Operation_GetText,"com.android.soundrecorder:id/record_displayname");
		excute(Object_ResourceId,Operation_LongClick,"com.android.soundrecorder:id/recode_icon");
		excute(Object_Text,Operation_ClickWait,"重命名");
		Rect rect=new UiObject(new UiSelector().className("android.widget.EditText")).getBounds();
		UiDevice.getInstance().click(rect.right-10, rect.centerY());
		
		int Name_length=Initial_Name.length();
		for(int i=0;i<Name_length;i++)
			UiDevice.getInstance().pressDelete();
		excute(Object_ClassName,Operation_SetText,"android.widget.EditText","xin");
		excute(Object_Text,Operation_ClickWait,"保存");
		String New_Name =(String)excute(Object_ResourceId, Operation_GetText, "com.android.soundrecorder:id/record_displayname");
		Assert.assertTrue("is ok",Set_Name.equals(New_Name));
		
		//清场
		excute(Object_ResourceId,Operation_LongClick,"com.android.soundrecorder:id/recode_icon");
		if((boolean)excute(Object_Text,Operation_checkExist,"全选"))
		{
			excute(Object_Text,Operation_ClickWait,"全选");
		}
		excute(Object_Text,Operation_ClickWait,"删除");
		excute(Object_Text,Operation_ClickWait,"确定");
		
	}
	/**
	 * 有录音文件的时候，且录音机处于非录音状态，当选中文件多个文件被选中时，点击页面上的 删除 
	 */
	public static void test_022() throws UiObjectNotFoundException
	{
		//前提
		
		RecorderCommon.creatRecorder();
		RecorderCommon.creatRecorder();
		//主体
		UiDevice.getInstance().click(rect_stop.centerX(),rect_stop.centerY());
		excute(Object_ResourceId,Operation_LongClick,"com.android.soundrecorder:id/recode_icon");
		excute(Object_Text,Operation_ClickWait,"全选");
		excute(Object_Text,Operation_ClickWait,"删除");
		check(Object_Text,Operation_checkExist,"注意");
		//清场
		excute(Object_Text,Operation_ClickWait,"确定");
	
		
	}
	/**
	 * 有录音文件的时候，且录音机处于非录音状态，当选中文件多个文件被选中时，点击页面上的 删除 
	 */
	public static void test_023() throws UiObjectNotFoundException
	{
		//前提
		
		RecorderCommon.creatRecorder();
		RecorderCommon.creatRecorder();
		//主体
		UiDevice.getInstance().click(rect_stop.centerX(),rect_stop.centerY());
		excute(Object_ResourceId,Operation_LongClick,"com.android.soundrecorder:id/recode_icon");
		excute(Object_Text,Operation_ClickWait,"全选");
		excute(Object_Text,Operation_ClickWait,"删除");
		excute(Object_Text,Operation_ClickWait,"取消");
		check(Object_ResourceId,Operation_checkExist,"com.android.soundrecorder:id/recode_icon");
		//清场
		excute(Object_ResourceId,Operation_LongClick,"com.android.soundrecorder:id/recode_icon");
		if((boolean)excute(Object_Text,Operation_checkExist,"全选"))
		{
			excute(Object_Text,Operation_ClickWait,"全选");
		}
		excute(Object_Text,Operation_ClickWait,"删除");
		excute(Object_Text,Operation_ClickWait,"确定");
	}
	/**
	 * 有录音文件的时候，且录音机处于非录音状态，当选中文件多个文件被选中时，点击页面上的 删除 ，点击确定
	 */
	public static void test_024() throws UiObjectNotFoundException
	{
		//前提
		
		RecorderCommon.creatRecorder();
		RecorderCommon.creatRecorder();
		//主体
		UiDevice.getInstance().click(rect_stop.centerX(),rect_stop.centerY());
		excute(Object_ResourceId,Operation_LongClick,"com.android.soundrecorder:id/recode_icon");
		excute(Object_Text,Operation_ClickWait,"全选");
		excute(Object_Text,Operation_ClickWait,"删除");
		excute(Object_Text,Operation_ClickWait,"确定");
		check(Object_ResourceId,Operation_checkNoExist,"com.android.soundrecorder:id/recode_icon");
		//清场
		
	}
	/**
	 * 有录音文件的时候，且录音机处于非录音状态，点击列表图标,点击页面上的 更多（菜单）选项
	 */
	public static void test_025() throws UiObjectNotFoundException
	{
		//前提
		
		RecorderCommon.creatRecorder();
		//主体
		UiDevice.getInstance().click(rect_stop.centerX(),rect_stop.centerY());
		excute(Object_Description,Operation_ClickWait,"更多选项");	
		check(Object_Text,Operation_checkExist,"设置保存路径");
		check(Object_Text,Operation_checkExist,"选择多个文件");
		check(Object_Text,Operation_checkExist,"设置文件类型");
		//清场
		excute(Object_Device, Operation_PressBack);
	}
	/**
	 * 有录音文件的时候，且录音机处于非录音状态，点击列表图标,点击页面上的 更多（菜单）选项，点击设置保存路径
	 */
	public static void test_026() throws UiObjectNotFoundException
	{
		//前提
		
		RecorderCommon.creatRecorder();
		//主体
		UiDevice.getInstance().click(rect_stop.centerX(),rect_stop.centerY());
		excute(Object_Description,Operation_ClickWait,"更多选项");	
		excute(Object_Text,Operation_ClickWait,"设置保存路径");
		check(Object_Text,Operation_checkExist,"请选择路径");
		//清场
		excute(Object_Device, Operation_PressBack);
	}
	/**
	 * 有录音文件的时候，且录音机处于非录音状态，点击列表图标,点击页面上的 更多（菜单）选项，点击功能下拉框上的“选择多个文件”
	 */
	public static void test_027() throws UiObjectNotFoundException
	{
		
		//前提
		
		RecorderCommon.creatRecorder();
		//主体
		UiDevice.getInstance().click(rect_stop.centerX(),rect_stop.centerY());
		excute(Object_Description,Operation_ClickWait,"更多选项");	
		excute(Object_Text,Operation_ClickWait,"选择多个文件");
		check(Object_Text,Operation_checkExist,"0");
		//清场
		excute(Object_Text,Operation_ClickWait,"全选");
		excute(Object_Text,Operation_ClickWait,"删除");
		excute(Object_Text,Operation_ClickWait,"确定");

	}
	
	/**
	 * 有录音文件的时候，且录音机处于非录音状态，点击列表图标,点击页面上的 更多（菜单）选项，点击功能下拉框上的“设置文件类型”
	 */
	public static void test_028() throws UiObjectNotFoundException
	{
		
		//前提
		
		RecorderCommon.creatRecorder();
		//主体
		UiDevice.getInstance().click(rect_stop.centerX(),rect_stop.centerY());
		excute(Object_Description,Operation_ClickWait,"更多选项");	
		excute(Object_Text,Operation_ClickWait,"设置文件类型");
		check(Object_Text,Operation_checkExist,"选择录制文件类型");
		//清场
		excute(Object_Text,Operation_ClickWait,"取消");
		excute(Object_ResourceId,Operation_LongClick,"com.android.soundrecorder:id/recode_icon");
		if((boolean)excute(Object_Text,Operation_checkExist,"全选"))
		{
			excute(Object_Text,Operation_ClickWait,"全选");
		}
		excute(Object_Text,Operation_ClickWait,"删除");
		excute(Object_Text,Operation_ClickWait,"确定");

	}
	/**
	 * 有录音文件的时候，且录音机处于非录音状态，点击列表图标,点击页面上的任意一个录音文件
	 */
	public static void test_029() throws UiObjectNotFoundException
	{
		
		//前提
		
		RecorderCommon.creatRecorder();
		//主体
		UiDevice.getInstance().click(rect_stop.centerX(),rect_stop.centerY());
		excute(Object_ResourceId,Operation_ClickWait,"com.android.soundrecorder:id/recode_icon");
		check(Object_Text,Operation_checkExist,"您的录音");
		//清场
		excute(Object_Device, Operation_PressBack);
		excute(Object_ResourceId,Operation_LongClick,"com.android.soundrecorder:id/recode_icon");
		if((boolean)excute(Object_Text,Operation_checkExist,"全选"))
		{
			excute(Object_Text,Operation_ClickWait,"全选");
		}
		excute(Object_Text,Operation_ClickWait,"删除");
		excute(Object_Text,Operation_ClickWait,"确定");

	}
//	public void testdemo()
//	{
//	UiDevice.getInstance().pressHome();
//	UiDevice.getInstance().openNotification();
//	}
	
}