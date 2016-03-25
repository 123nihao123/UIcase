package testcase;


import junit.framework.Assert;

import android.os.RemoteException;



import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;

import com.android.uiautomator.testrunner.UiAutomatorTestCase;

import static framework.data.ObjectType.*;
import static framework.data.OperationType.*;
import static framework.data.ResIdTextAndDesc.*;
import static framework.excute.Excute.*;

import framework.common.DeviceCommon;

//插耳机，FM电台无任何收藏的频道,Fm允许访问音乐，录音等媒体
public class Fm extends UiAutomatorTestCase
{
	
	@Override
	protected void setUp() throws UiObjectNotFoundException, RemoteException 
    {   	
		System.out.println("Enter the setUp!!!");	
		excute(Object_Device, Operation_WakeUp);
		DeviceCommon.unLock();	
		ClearBackgroundApp();
		Wait(1000);
		DeviceCommon.enterApp(Devices_Text_Fm);	
		check(Object_Description,Operation_WaitForExists,"停止播放 FM 电台","30000");
		
   }
	       
	
	protected void tearDown() throws UiObjectNotFoundException, RemoteException 
    {
    }//	在 ResIdTextAndDesc下加public static final String Devices_Text_Fm = "FM 电台";
	/**
	 * 检查页面播放按钮
	 */
	public static void test_001() 
	{
		check(Object_Description,Operation_WaitForExists,"停止播放 FM 电台","10000");
	}
	/**
	 * 查看数字的变化
	 */
	public static void test_002() //Operation_GetText 
	{
		String  figure1 = (String) excute(Object_ResourceId,Operation_GetText,"com.android.fmradio:id/station_value");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.fmradio:id/button_prevstation");
		String  figure2 = (String) excute(Object_ResourceId,Operation_GetText,"com.android.fmradio:id/station_value");
		Assert.assertNotSame(figure1, figure2);
		//查看2次数字变化
	}
	/**
	 * 查看数字的变化
	 */
	public static void test_003()
	{
		String  figure1 = (String) excute(Object_ResourceId,Operation_GetText,"com.android.fmradio:id/station_value");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.fmradio:id/button_decrease");
		String  figure2 = (String)  excute(Object_ResourceId,Operation_GetText,"com.android.fmradio:id/station_value");
		float f1 = Float.parseFloat(figure1);
		float f2 = (float) ( Float.parseFloat(figure2)+0.1);
		Assert.assertEquals(f1, f2);
        //比之前小了0.1
	}
	/**
	 * 收藏频道
	 */
	
	public static void test_004()
	{
		excute(Object_Description,Operation_ClickWait,"添加到收藏");
		check(Object_ResourceId,Operation_checkExist,"com.android.fmradio:id/favorite_text");
		//清场
		excute(Object_ResourceId,Operation_ClickWait,"com.android.fmradio:id/station_more");
		excute(Object_ResIdText,Operation_ClickWait,"android:id/title","取消收藏");
	}
	/**
	 * 查看数字的变化
	 */
	public static void test_008()
	{
		String  figure1 = (String) excute(Object_ResourceId,Operation_GetText,"com.android.fmradio:id/station_value");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.fmradio:id/button_increase");
		String  figure2 = (String)  excute(Object_ResourceId,Operation_GetText,"com.android.fmradio:id/station_value");
		float f1 = Float.parseFloat(figure1);
		float f2 = (float) ( Float.parseFloat(figure2)-0.1);
		Assert.assertEquals(f1, f2);
		//比之前大了0.1
	}
	/**
	 * 查看数字的变化
	 */
	public static void test_005() //Operation_GetText 
	{
		String  figure1 = (String) excute(Object_ResourceId,Operation_GetText,"com.android.fmradio:id/station_value");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.fmradio:id/button_nextstation");
		String  figure2 = (String) excute(Object_ResourceId,Operation_GetText,"com.android.fmradio:id/station_value");
		Assert.assertNotSame(figure1, figure2);
		//查看2次数字变化
	}
	/**
	 * 查看频道列表
	 */
	public static void test_007() 
	{
		excute(Object_ResourceId,Operation_ClickWait,"com.android.fmradio:id/fm_station_list");
		if((Boolean) excute(Object_Text,Operation_Exists,"正在搜索电台"))
		{	
			check(Object_ResIdInstance,Operation_WaitForExists,"com.android.fmradio:id/lv_station_type","50000");
		} //如果是第一次，等待搜索完毕
		check(Object_ResIdInstance,Operation_checkExist,"com.android.fmradio:id/lv_station_type","0");
	}
	/**
	 * 查看频道列表
	 */
	public static void test_009() 
	{
		excute(Object_ResourceId,Operation_ClickWait,"com.android.fmradio:id/fm_station_list");
		check(Object_ResourceId,Operation_checkExist,"com.android.fmradio:id/lv_station_type");
		//查看搜索图标的ResourceId
	}
	/**
	 * 取消收藏
	 */
	public static void test_010_1() 
	{
		excute(Object_ResourceId,Operation_ClickWait,"com.android.fmradio:id/fm_station_list");
		excute(Object_ResIdInstance,Operation_ClickWait,"com.android.fmradio:id/lv_station_type","0");
		excute(Object_Device,Operation_PressBack);
		check(Object_ResourceId,Operation_checkExist,"com.android.fmradio:id/favorite_text");
		//清场
		excute(Object_ResourceId,Operation_ClickWait,"com.android.fmradio:id/station_more");
		excute(Object_ResIdText,Operation_ClickWait,"android:id/title","取消收藏");
		//查看搜索图标的ResourceId
	}
	/**
	 * 收藏频道
	 */
	public static void test_010_2() 
	{
		//前提
		excute(Object_ResourceId,Operation_ClickWait,"com.android.fmradio:id/fm_station_list");
		excute(Object_ResIdInstance,Operation_ClickWait,"com.android.fmradio:id/lv_station_type","0");
		//主体
		excute(Object_ResIdInstance,Operation_ClickWait,"com.android.fmradio:id/lv_station_type","0");
		excute(Object_Device,Operation_PressBack);
		check(Object_ResourceId,Operation_checkNoExist,"com.android.fmradio:id/favorite_text");
	}
	/**
	 * 播放频道
	 */
	public static void test_011() 
	{
		excute(Object_ResourceId,Operation_ClickWait,"com.android.fmradio:id/fm_station_list");
		String  figure1 = (String) excute(Object_ResIdInstance,Operation_GetText,"com.android.fmradio:id/lv_station_freq","0");
		excute(Object_ResIdInstance,Operation_ClickWait,"com.android.fmradio:id/list_item_left","0");
		String  figure2 = (String)  excute(Object_ResourceId,Operation_GetText,"com.android.fmradio:id/station_value");
		Assert.assertEquals(figure1, figure2);
		//通过数字进行判断
	}
	/**
	 * 频道菜单
	 */
	public static void test_012() 
	{
		excute(Object_ResourceId,Operation_ClickWait,"com.android.fmradio:id/fm_station_list");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		check(Object_Text,Operation_checkExist,"刷新");
		check(Object_Text,Operation_checkExist,"从当前频道开始搜台");
	}
	/**
	 * 刷新
	 */
	public static void test_013_1() 
	{
		excute(Object_ResourceId,Operation_ClickWait,"com.android.fmradio:id/fm_station_list");
		excute(Object_Description,Operation_ClickWait,"更多选项");
	    excute(Object_Text,Operation_ClickWait,"刷新");
		check(Object_Text,Operation_checkExist,"正在搜索电台");
	}
	/**
	 * 收听频道
	 */
	public static void test_013_2() 
	{
		excute(Object_ResourceId,Operation_ClickWait,"com.android.fmradio:id/fm_station_list");
		String  figure1 = (String) excute(Object_ResIdInstance,Operation_GetText,"com.android.fmradio:id/lv_station_freq","0");
		excute(Object_ResIdInstance,Operation_ClickWait,"com.android.fmradio:id/list_item_left","0");
		String  figure2 = (String)  excute(Object_ResourceId,Operation_GetText,"com.android.fmradio:id/station_value");
		Assert.assertEquals(figure1, figure2);
	    //通过频道号相等判断
	}
	/**
	 * 刷新搜台
	 */
	public static void test_014() 
	{
		excute(Object_ResourceId,Operation_ClickWait,"com.android.fmradio:id/fm_station_list");
		excute(Object_Description,Operation_ClickWait,"更多选项");
	    excute(Object_Text,Operation_ClickWait,"从当前频道开始搜台");
		check(Object_Text,Operation_checkExist,"正在搜索电台");
	}
	/**
	 * 耳机/外放切换
	 */
	public static void test_015()
	{
		excute(Object_ResourceId,Operation_ClickWait,"com.android.fmradio:id/fm_headset");
		check(Object_Text,Operation_checkExist,"耳机");
		check(Object_Text,Operation_checkExist,"扬声器");
	}
	/**
	 * 耳机/外放切换
	 */
	public static void test_016()
	{
		excute(Object_ResourceId,Operation_ClickWait,"com.android.fmradio:id/fm_headset");
		check(Object_Text,Operation_ClickWait,"耳机");
	}
	/**
	 * 耳机/外放切换
	 */
	public static void test_017()
	{
		excute(Object_ResourceId,Operation_ClickWait,"com.android.fmradio:id/fm_headset");
		check(Object_Text,Operation_ClickWait,"扬声器");
	}
	/**
	 * 菜单
	 */
	public static void test_018()
	{
		excute(Object_Description,Operation_ClickWait,"更多选项");
		check(Object_Text,Operation_checkExist,"开始录音");
		check(Object_Text,Operation_checkExist,"已保存的录音");
		check(Object_Text,Operation_checkExist,"录音格式");
		check(Object_Text,Operation_checkExist,"设置保存路径");
	}
	/**
	 * 录音
	 */
	public static void test_019()
	{
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"开始录音");
		check(Object_Text,Operation_checkExist,"录音");
	}
	/**
	 * 播放录音
	 */
	public static void test_020()
	{
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"已保存的录音");
		check(Object_Text,Operation_checkExist,"音乐");
	}
	/**
	 * 录音格式
	 */
	public static void test_021()
	{
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"录音格式");
		check(Object_Text,Operation_checkExist,"amr");
		check(Object_Text,Operation_checkExist,"3gpp");
	}
	/**
	 * 检查录音格式
	 */
	public static void test_022()
	{
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"录音格式");
		excute(Object_Text,Operation_ClickWait,"amr");
		//assert
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"录音格式");
		check(Object_Text,Operation_IsChecked,"amr");
	}
	/**
	 * 检查录音格式
	 */
	public static void test_023()
	{
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"录音格式");
		excute(Object_Text,Operation_ClickWait,"3gpp");
		//assert
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"录音格式");
		check(Object_Text,Operation_IsChecked,"3gpp");
	}
	/**
	 * 保存路径
	 */
	public static void test_024()
	{
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"设置保存路径");
		check(Object_ResourceId,Operation_checkExist,"android:id/select_dialog_listview");	
	}
	/**
	 * 保存路径
	 */
	public static void test_025()
	{
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"设置保存路径");
		excute(Object_Text,Operation_ClickWait,"内置存储");
	}
	/**
	 * 保存路径
	 */
	public static void test_026()
	{
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"设置保存路径");
		excute(Object_Text,Operation_ClickWait,"外置存储");
	}
	/**
	 * 暂停播放
	 */
	public static void test_027()
	{
		excute(Object_ResourceId,Operation_ClickWait,"com.android.fmradio:id/play_button");
		//检查没有电台，所以是关闭的
		excute(Object_ResourceId,Operation_ClickWait,"com.android.fmradio:id/fm_station_list");
		check(Object_Text,Operation_checkNoExist,"电台");
	}
	/**
	 * 继续播放
	 */
	public static void test_028()
	{
		//前提
		excute(Object_ResourceId,Operation_ClickWait,"com.android.fmradio:id/play_button");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.fmradio:id/fm_station_list");
		check(Object_Text,Operation_checkNoExist,"电台");
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"com.android.fmradio:id/play_button");
		//可以进入电台
		excute(Object_ResourceId,Operation_ClickWait,"com.android.fmradio:id/fm_station_list");
		check(Object_Text,Operation_checkExist,"电台");
	}
	/**
	 * 收藏频道
	 */
	public static void test_029()
	{
		excute(Object_Description,Operation_ClickWait,"添加到收藏");
		check(Object_ResourceId,Operation_checkExist,"com.android.fmradio:id/favorite_text");
		//清场
		excute(Object_ResourceId,Operation_ClickWait,"com.android.fmradio:id/station_more");
		excute(Object_ResIdText,Operation_ClickWait,"android:id/title","取消收藏");
	}
	/**
	 * 收藏频道内容
	 */
	public static void test_030()
	{
		
		excute(Object_Description,Operation_ClickWait,"添加到收藏");
		check(Object_ResourceId,Operation_checkExist,"com.android.fmradio:id/favorite_text");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.fmradio:id/station_more");
		check(Object_Text,Operation_checkExist,"取消收藏");
		check(Object_Text,Operation_checkExist,"重命名");
		//清场
		excute(Object_ResIdText,Operation_ClickWait,"android:id/title","取消收藏");
	}
	/**
	 * 收藏频道内容
	 */
	public static void test_031()
	{
		excute(Object_Description,Operation_ClickWait,"添加到收藏");
		check(Object_ResourceId,Operation_checkExist,"com.android.fmradio:id/favorite_text");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.fmradio:id/station_more");
		check(Object_Text,Operation_checkExist,"取消收藏");
		check(Object_Text,Operation_checkExist,"重命名");
		excute(Object_ResIdText,Operation_ClickWait,"android:id/title","取消收藏");
	}
	/**
	 * 重命名
	 */
	public static void test_032()
	{
		
		excute(Object_Description,Operation_ClickWait,"添加到收藏");
		check(Object_ResourceId,Operation_checkExist,"com.android.fmradio:id/favorite_text");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.fmradio:id/station_more");
		excute(Object_Text,Operation_ClickWait,"重命名");
		excute(Object_ResourceId, Operation_SetText, "com.android.fmradio:id/dlg_edit_station_name_text", "zhanxun");
		excute(Object_Text,Operation_ClickWait,"保存");
		Assert.assertTrue("zhanxun".equals(excute(Object_ResourceId,Operation_GetText,"com.android.fmradio:id/station_name")));
	    //清场
		excute(Object_ResourceId,Operation_ClickWait,"com.android.fmradio:id/station_more");
		excute(Object_ResIdText,Operation_ClickWait,"android:id/title","取消收藏");
	}
	/**
	 * 更改频道号
	 */
	public static void test_033() throws UiObjectNotFoundException
	{
		excute(Object_Description,Operation_ClickWait,"添加到收藏");
		
		excute(Object_ResourceId,Operation_ClickWait,"com.android.fmradio:id/station_value");
	
		excute(Object_ResourceId,Operation_SetText,"com.android.fmradio:id/edit_freq","100.0");
		excute(Object_Text,Operation_ClickWait,"确定");
		//检查
		Assert.assertTrue("100.0".equals(excute(Object_ResourceId,Operation_GetText,"com.android.fmradio:id/station_value")));
		//清场
		
		excute(Object_ResourceId,Operation_ClickWait,"com.android.fmradio:id/station_more");
		excute(Object_ResIdText,Operation_ClickWait,"android:id/title","取消收藏");
		
	}
	/**
	 * 换收藏的频道
	 */
	public static void test_034()
	{
		excute(Object_Description,Operation_ClickWait,"添加到收藏");
		check(Object_ResourceId,Operation_checkExist,"com.android.fmradio:id/favorite_text");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.fmradio:id/card_view");
		check(Object_Description,Operation_checkExist,"停止播放 FM 电台");
		//清场
		excute(Object_ResourceId,Operation_ClickWait,"com.android.fmradio:id/station_more");
		excute(Object_ResIdText,Operation_ClickWait,"android:id/title","取消收藏");
		
	}
	
	
}
	
	
	
	
	
	
	
	

	