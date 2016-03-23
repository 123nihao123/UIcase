package testcase;

//import org.junit.AfterClass;  
//import org.junit.BeforeClass;

import static framework.data.ObjectType.*;
import static framework.data.OperationType.*;
import static framework.data.ResIdTextAndDesc.*;
import static framework.excute.Excute.*;
import junit.framework.Assert;
import android.graphics.Rect;
import android.os.RemoteException;
import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import framework.common.CallCommon;
import framework.common.CallLogCommon;
import framework.common.ContactCommon;
import framework.common.DeviceCommon;
import framework.common.MusicPlayerCommon;
import framework.common.SettingCommon;
import framework.common.MusicPlayerCommon;

public class MusicPlayer extends UiAutomatorTestCase
{
	@Override
	protected void setUp() throws UiObjectNotFoundException, RemoteException 
    {	
		System.out.println("Enter the setUp!!!");	
		excute(Object_Device, Operation_WakeUp);
		DeviceCommon.unLock();	
		ClearBackgroundApp();
		Wait(1000);
		DeviceCommon.enterApp(Devices_Desc_Music);
   }
	@Override
	protected void tearDown() throws UiObjectNotFoundException, RemoteException 
    {
    }
	
	/**
	 * 进入音乐界面
	 */
	public static void test_001() 
	{
		//主体
		check(Object_Text,Operation_checkExist,"音乐");
	}
	
	/**
	 * 进入音乐界面 -存在音乐人，专辑，歌曲，播放列表4个tab
	 */
	public static void test_002() 
	{
		//主体
		check(Object_ResIdText,Operation_checkExist,"com.android.music:id/artisttab","音乐人");
		check(Object_ResIdText,Operation_checkExist,"com.android.music:id/albumtab","专辑");
		check(Object_ResIdText,Operation_checkExist,"com.android.music:id/songtab","歌曲");
		check(Object_ResIdText,Operation_checkExist,"com.android.music:id/playlisttab","播放列表");
	}
	
	/**
	 * 进入音乐界面-存在搜索图标
	 */
	public static void test_003()
	{
		//主体
		check(Object_ResIdDesc,Operation_checkExist,"com.android.music:id/search","搜索");
	}
	
	/**
	 * 进入音乐界面--菜单-“派对随机播放”“全部随机播放”“退出”功能
	 */
	public static void test_004() 
	{
		//主体
		excute(Object_Description,Operation_ClickWait,"更多选项");
		check(Object_Text,Operation_checkExist,"派对随机播放");
		check(Object_Text,Operation_checkExist,"全部随机播放");
		check(Object_Text,Operation_checkExist,"退出");
	}
	
	/**
	 * 进入音乐界面 -专辑-按专辑排序
	 */
	public static void test_031() 
	{
		//主体
		excute(Object_ResIdText,Operation_ClickWait,"com.android.music:id/albumtab","专辑");
		check(Object_TextScroll,Operation_checkExist,"Test");
		check(Object_TextScroll,Operation_checkExist,"Test1");
	}
	
	/**
	 * 进入音乐界面 -专辑-点击一个专辑展开
	 */
	public static void test_032() 
	{
		//主体
		excute(Object_ResIdText,Operation_ClickWait,"com.android.music:id/albumtab","专辑");
		String music = (String) excute(Object_ResIdInstance,Operation_GetText,"com.android.music:id/line1","0");
		excute(Object_ResIdInstance,Operation_ClickWait,"com.android.music:id/line1","0");
		check(Object_Text,Operation_checkExist,music);
	}
	
	/**
	 * 进入音乐界面 -专辑-长按一个专辑，出现“播放”“添加到播放列表””删除”等字样
	 */
	public static void test_033() 
	{
		//主体
		excute(Object_ResIdText,Operation_ClickWait,"com.android.music:id/albumtab","专辑");
		excute(Object_ResIdInstance,Operation_LongClick,"com.android.music:id/line1","0");
		check(Object_Text,Operation_checkExist,"播放");
		check(Object_Text,Operation_checkExist,"添加到播放列表");
		check(Object_Text,Operation_checkExist,"删除");
	}
	
	/**
	 * 进入音乐界面 -专辑-长按一个专辑，点击播放跳转到播放界面
	 */
	public static void test_034() 
	{
		//主体
		excute(Object_ResIdText,Operation_ClickWait,"com.android.music:id/albumtab","专辑");
		excute(Object_ResIdInstance,Operation_LongClick,"com.android.music:id/line1","0");
		excute(Object_Text,Operation_ClickWait,"播放");
		check(Object_ResourceId,Operation_checkExist,"com.android.music:id/pause");
		//清场
		excute(Object_ResourceId,Operation_ClickWait,"com.android.music:id/pause");
	}
	
	/**
	 * 进入音乐界面 -专辑-长按一个专辑，点击添加到播放列表
	 */
	public static void test_035()
	{
		//主体
		excute(Object_ResIdText,Operation_ClickWait,"com.android.music:id/albumtab","专辑");
		excute(Object_ResIdInstance,Operation_LongClick,"com.android.music:id/line1","0");
		excute(Object_Text,Operation_ClickWait,"添加到播放列表");
		check(Object_ResIdText,Operation_checkExist,"android:id/alertTitle","添加到播放列表");
	}
	
	/**
	 * 进入音乐界面 -专辑-长按一个专辑，点击删除，取消删除
	 */
	public static void test_036() 
	{
		//主体
		excute(Object_ResIdText,Operation_ClickWait,"com.android.music:id/albumtab","专辑");
		String music = (String) excute(Object_ResIdInstance,Operation_GetText,"com.android.music:id/line1","0");
		excute(Object_ResIdInstance,Operation_LongClick,"com.android.music:id/line1","0");
		excute(Object_Text,Operation_ClickWait,"删除");
		excute(Object_Text,Operation_ClickWait,"取消");
		check(Object_Text,Operation_checkExist,music);
	}
	
	/**
	 * 进入音乐界面 -专辑-长按一个专辑，点击删除该专辑
	 */
	public static void test_037() 
	{
		//主体
		excute(Object_ResIdText,Operation_ClickWait,"com.android.music:id/albumtab","专辑");
		String music = (String) excute(Object_ResIdInstance,Operation_GetText,"com.android.music:id/line1","0");
		excute(Object_ResIdInstance,Operation_LongClick,"com.android.music:id/line1","0");
		excute(Object_Text,Operation_ClickWait,"删除");
		excute(Object_Text,Operation_ClickWait,"确定");
		check(Object_Text,Operation_checkNoExist,music);
	}
		
	/**
	 * 进入音乐界面 -专辑-点击一个专辑，长按一首歌，出现此功能对话框上有“播放”“添加播放列表”“删除”“搜索”
	 */
	public static void test_039() 
	{
		//主体
		excute(Object_ResIdText,Operation_ClickWait,"com.android.music:id/albumtab","专辑");
		excute(Object_ResIdInstance,Operation_ClickWait,"com.android.music:id/line1","0");
		excute(Object_ResIdInstance,Operation_LongClick,"com.android.music:id/line1","0");
		check(Object_Text,Operation_checkExist,"播放");
		check(Object_Text,Operation_checkExist,"添加到播放列表");
		check(Object_Text,Operation_checkExist,"删除");
	}
	
	/**
	 * 进入音乐界面 -专辑-点击一个专辑，长按一首歌，点击播放
	 */
	public static void test_040()
	{
		//主体
		excute(Object_ResIdText,Operation_ClickWait,"com.android.music:id/albumtab","专辑");
		excute(Object_ResIdInstance,Operation_ClickWait,"com.android.music:id/line1","0");
		excute(Object_ResIdInstance,Operation_LongClick,"com.android.music:id/line1","0");
		excute(Object_Text,Operation_ClickWait,"播放");
		check(Object_ResourceId,Operation_checkExist,"com.android.music:id/pause");
		//清场
		excute(Object_ResourceId,Operation_ClickWait,"com.android.music:id/pause");
	}
	
	/**
	 * 进入音乐界面 -专辑-点击一个专辑，长按一首歌，点击添加到播放列表
	 */
	public static void test_041() 
	{
		//主体
		excute(Object_ResIdText,Operation_ClickWait,"com.android.music:id/albumtab","专辑");
		excute(Object_ResIdInstance,Operation_ClickWait,"com.android.music:id/line1","0");
		excute(Object_ResIdInstance,Operation_LongClick,"com.android.music:id/line1","0");
		excute(Object_Text,Operation_ClickWait,"添加到播放列表");
		check(Object_ResIdText,Operation_checkExist,"android:id/alertTitle","添加到播放列表");
	}
	
	/**
	 * 进入音乐界面 -专辑-点击一个专辑，长按一首歌，点击删除
	 */
	public static void test_042()  
	{
		//主体
		excute(Object_ResIdText,Operation_ClickWait,"com.android.music:id/albumtab","专辑");
		excute(Object_ResIdInstance,Operation_ClickWait,"com.android.music:id/line1","0");
		excute(Object_ResIdInstance,Operation_LongClick,"com.android.music:id/line1","0");
		excute(Object_Text,Operation_ClickWait,"删除");
		check(Object_ResourceId,Operation_checkExist,"com.android.music:id/prompt");
	}
	
	/**
	 * 进入音乐界面 -专辑-点击一个专辑，长按一首歌，点击删除，取消删除
	 */
	public static void test_043()
	{
		//主体
		excute(Object_ResIdText,Operation_ClickWait,"com.android.music:id/albumtab","专辑");
		String music = (String) excute(Object_ResIdInstance,Operation_GetText,"com.android.music:id/line1","0");
		excute(Object_ResIdInstance,Operation_ClickWait,"com.android.music:id/line1","0");
		excute(Object_ResIdInstance,Operation_LongClick,"com.android.music:id/line1","0");
		excute(Object_Text,Operation_ClickWait,"删除");
		excute(Object_Text,Operation_ClickWait,"取消");
		check(Object_Text,Operation_checkExist,music);
	}	
	
	/**
	 * 进入音乐界面 -专辑-点击一个专辑，长按一首歌，点击删除该歌曲
	 */
	public static void test_045()
	{
		//主体
		excute(Object_ResIdText,Operation_ClickWait,"com.android.music:id/albumtab","专辑");
		String music = (String) excute(Object_ResIdInstance,Operation_GetText,"com.android.music:id/line1","0");
		excute(Object_ResIdInstance,Operation_ClickWait,"com.android.music:id/line1","0");
		excute(Object_ResIdInstance,Operation_LongClick,"com.android.music:id/line1","0");
		excute(Object_Text,Operation_ClickWait,"删除");
		excute(Object_Text,Operation_ClickWait,"确定");
		check(Object_Text,Operation_checkNoExist,music);
	}
	
	/**
	 * 进入音乐界面 -专辑-点击一个专辑，长按一首歌，点击搜索
	 */
	public static void test_046() 
	{
		//主体
		excute(Object_ResIdText,Operation_ClickWait,"com.android.music:id/albumtab","专辑");
		excute(Object_ResIdInstance,Operation_ClickWait,"com.android.music:id/line1","0");
		excute(Object_ResIdInstance,Operation_LongClick,"com.android.music:id/line1","0");
		excute(Object_Text,Operation_ClickWait,"搜索");
		check(Object_ResourceId,Operation_TextContainsTrue,"android:id/title","使用以下工具");
	}
	
	/**
	 * 进入音乐界面 -专辑-点击一个专辑-菜单-派对随机播放
	 */
	public static void test_047()
	{
		//主体
		excute(Object_ResIdText,Operation_ClickWait,"com.android.music:id/albumtab","专辑");
		excute(Object_ResIdInstance,Operation_ClickWait,"com.android.music:id/line1","0");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		if((Boolean)excute(Object_Text, Operation_Exists, "派对随机播放"))
		{
			excute(Object_Text, Operation_ClickWait, "派对随机播放");
			excute(Object_Device, Operation_PressMenu);
			check(Object_Text, Operation_checkExist, "关闭派对随机播放");
			//清场
			excute(Object_Text, Operation_ClickWait, "关闭派对随机播放");
			excute(Object_ResourceId, Operation_ClickWait, "com.android.music:id/nowplaying");
			excute(Object_ResourceId, Operation_ClickWait, "com.android.music:id/pause");
		}else{
			check(Object_Text, Operation_checkExist, "关闭派对随机播放");
			excute(Object_Text, Operation_ClickWait, "关闭派对随机播放");
		}
	}
	
	/**
	 * 进入音乐界面 -专辑-点击一个专辑-菜单-全部随机播放
	 */
	public static void test_048() 
	{
		//主体
		excute(Object_ResIdText,Operation_ClickWait,"com.android.music:id/albumtab","专辑");
		excute(Object_ResIdInstance,Operation_ClickWait,"com.android.music:id/line1","0");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text, Operation_ClickWait, "全部随机播放");
		check(Object_ResourceId, Operation_checkExist, "com.android.music:id/pause");
	    //清场
		excute(Object_ResourceId, Operation_ClickWait, "com.android.music:id/pause");
	}
	
	
	/**
	 * 进入音乐界面 -专辑-退出音乐播放器
	 */
	public static void test_049() 
	{
		//主体
		excute(Object_ResIdText,Operation_ClickWait,"com.android.music:id/albumtab","专辑");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"退出");
		check(Object_Text,Operation_checkNoExist,"专辑");
	}
	
	/**
	 * 进入音乐界面 -歌曲
	 */
	public static void test_050()
	{
		//主体
		excute(Object_ResIdText,Operation_ClickWait,"com.android.music:id/songtab","歌曲");
		excute(Object_TextScroll,Operation_checkExist,"test","vertical");
		excute(Object_TextScroll,Operation_checkExist,"test1","vertical");
	}
	/**
	 * 进入音乐界面 -歌曲-选择一首歌曲播放
	 */
	public static void test_051() 
	{
		//主体
		excute(Object_ResIdText,Operation_ClickWait,"com.android.music:id/songtab","歌曲");
		excute(Object_ResIdInstance,Operation_ClickWait,"com.android.music:id/line1","0");
		check(Object_ResourceId, Operation_checkExist, "com.android.music:id/pause");
	    //清场
		excute(Object_ResourceId, Operation_ClickWait, "com.android.music:id/pause");
	}
	
	/**
	 * 进入音乐界面 -歌曲-选择长按一首歌曲，对话框上有“播放”“添加到播放列表”“用作手机铃声”“删除”“搜索”等功能项
	 */
	public static void test_052() 
	{
		//主体
		excute(Object_ResIdText,Operation_ClickWait,"com.android.music:id/songtab","歌曲");
		excute(Object_ResIdInstance,Operation_LongClick,"com.android.music:id/line1","0");
		check(Object_Text, Operation_checkExist, "播放");
		check(Object_Text, Operation_checkExist, "添加到播放列表");
		check(Object_Text, Operation_checkExist, "删除");
	}
	
	/**
	 * 进入音乐界面 -歌曲-选择长按一首歌曲，点击“播放”
	 */
	public static void test_053()
	{
		//主体
		excute(Object_ResIdText,Operation_ClickWait,"com.android.music:id/songtab","歌曲");
		excute(Object_ResIdInstance,Operation_LongClick,"com.android.music:id/line1","0");
		excute(Object_Text, Operation_ClickWait, "播放");
		check(Object_ResourceId, Operation_checkExist, "com.android.music:id/pause");
	    //清场
		excute(Object_ResourceId, Operation_ClickWait, "com.android.music:id/pause");
		
	}
	
	/**
	 * 进入音乐界面 -歌曲-选择长按一首歌曲，点击“添加到播放列表”
	 */
	public static void test_054() 
	{
		//主体
		excute(Object_ResIdText,Operation_ClickWait,"com.android.music:id/songtab","歌曲");
		excute(Object_ResIdInstance,Operation_LongClick,"com.android.music:id/line1","0");
		excute(Object_Text, Operation_ClickWait, "添加到播放列表");
		check(Object_ResIdText,Operation_checkExist,"android:id/alertTitle","添加到播放列表");
		
	}
	
	/**
	 * 进入音乐界面 -歌曲-选择长按一首歌曲，点击删除
	 */
	public static void test_055() 
	{
		//主体
		excute(Object_ResIdText,Operation_ClickWait,"com.android.music:id/songtab","歌曲");
		excute(Object_ResIdInstance,Operation_LongClick,"com.android.music:id/line1","0");
		excute(Object_Text, Operation_ClickWait, "删除");
		check(Object_ResourceId,Operation_checkExist,"com.android.music:id/prompt");
	}
	
	/**
	 * 进入音乐界面 -歌曲-选择长按一首歌曲，点击删除后取消
	 */
	public static void test_056() 
	{
		//主体
		excute(Object_ResIdText,Operation_ClickWait,"com.android.music:id/songtab","歌曲");
		String music = (String) excute(Object_ResIdInstance,Operation_GetText,"com.android.music:id/line1","0");
		excute(Object_ResIdInstance,Operation_LongClick,"com.android.music:id/line1","0");
		excute(Object_Text,Operation_ClickWait,"删除");
		excute(Object_Text,Operation_ClickWait,"取消");
		check(Object_Text,Operation_checkExist,music);
		
	}
	
	/**
	 * 进入音乐界面 -歌曲-长按一首歌，点击删除该歌曲
	 */
	public static void test_058() 
	{
		//主体
		excute(Object_ResIdText,Operation_ClickWait,"com.android.music:id/songtab","歌曲");
		String music = (String) excute(Object_ResIdInstance,Operation_GetText,"com.android.music:id/line1","0");
		excute(Object_ResIdInstance,Operation_LongClick,"com.android.music:id/line1","0");
		excute(Object_Text,Operation_ClickWait,"删除");
		excute(Object_Text,Operation_ClickWait,"确定");
		check(Object_Text,Operation_checkNoExist,music);
	}
	
	/**
	 * 进入音乐界面 -歌曲-长按一首歌，点击搜索
	 */
	public static void test_059() 
	{
		//主体
		excute(Object_ResIdText,Operation_ClickWait,"com.android.music:id/songtab","歌曲");
		excute(Object_ResIdInstance,Operation_LongClick,"com.android.music:id/line1","0");
		excute(Object_Text,Operation_ClickWait,"搜索");
		check(Object_ResourceId,Operation_TextContainsTrue,"android:id/title","使用以下工具");
	}
	
	/**
	 * 进入音乐界面 -歌曲-长按一首歌，用作手机铃声
	 */
	public static void test_060()
	{
		//主体
		excute(Object_ResIdText,Operation_ClickWait,"com.android.music:id/songtab","歌曲");
		excute(Object_ResIdInstance,Operation_LongClick,"com.android.music:id/line1","0");
		excute(Object_Text,Operation_ClickWait,"用作手机铃声");
		check(Object_ResIdText,Operation_checkExist,"android:id/alertTitle","用作铃声");
	}
	
	/**
	 * 进入音乐界面 -歌曲-随机派对播放
	 */
	public static void test_062() 
	{
		//主体
		excute(Object_ResIdText,Operation_ClickWait,"com.android.music:id/songtab","歌曲");
		excute(Object_Device, Operation_PressMenu);
		if((Boolean)excute(Object_Text, Operation_Exists, "派对随机播放"))
		{
			excute(Object_Text, Operation_ClickWait, "派对随机播放");
			excute(Object_Device, Operation_PressMenu);
			check(Object_Text, Operation_checkExist, "关闭派对随机播放");
			//清场
			excute(Object_Text, Operation_ClickWait, "关闭派对随机播放");
			excute(Object_ResourceId, Operation_ClickWait, "com.android.music:id/nowplaying");
			excute(Object_ResourceId, Operation_ClickWait, "com.android.music:id/pause");
		}else{
			check(Object_Text, Operation_checkExist, "关闭派对随机播放");
			excute(Object_Text, Operation_ClickWait, "关闭派对随机播放");
		}
	}
	
	/**
	 * 进入音乐界面 -歌曲-菜单-全部随机播放
	 */
	public static void test_064() 
	{
		//主体
		excute(Object_ResIdText,Operation_ClickWait,"com.android.music:id/songtab","歌曲");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text, Operation_ClickWait, "全部随机播放");
		check(Object_ResourceId, Operation_checkExist, "com.android.music:id/pause");
	    //清场
		excute(Object_ResourceId, Operation_ClickWait, "com.android.music:id/pause");
	}
	
	/**
	 * 进入音乐界面 -歌曲-退出音乐播放器
	 */
	public static void test_065()
	{
		//主体
		excute(Object_ResIdText,Operation_ClickWait,"com.android.music:id/songtab","歌曲");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"退出");
		check(Object_Text,Operation_checkNoExist,"专辑");
	}
	
	/**
	 * 播放列表
	 */
	public static void test_066() 
	{
		//主体
		excute(Object_Text, Operation_ClickWait, "播放列表");
		check(Object_Text, Operation_checkExist, "最近添加的歌曲");
	}	
	/**
	 * 新增歌曲
	 */
	public static void test_067() 
	{
		//主体
		excute(Object_Text, Operation_ClickWait, "播放列表");
		excute(Object_Text, Operation_ClickWait, "最近添加的歌曲");
		check(Object_Text, Operation_checkExist, "新增歌曲");
	}
	/**
	 * 长按最近添加的歌曲
	 */
	public static void test_069() 
	{
		//主体
		excute(Object_Text, Operation_ClickWait, "播放列表");
		excute(Object_Text, Operation_LongClick, "最近添加的歌曲");
		check(Object_Text, Operation_checkExist, "播放");
		check(Object_Text, Operation_checkExist, "编辑");
	}
	/**
	 * 长按播放歌曲
	 */
	public static void test_070() 
	{
		//主体
		excute(Object_Text, Operation_ClickWait, "播放列表");
		excute(Object_Text, Operation_LongClick, "最近添加的歌曲");
		excute(Object_Text, Operation_ClickWait, "播放");
		check(Object_ResourceId, Operation_checkExist, "com.android.music:id/pause");
		//清场
		excute(Object_ResourceId, Operation_ClickWait, "com.android.music:id/pause");

	}
	/**
	 * 编辑最近添加的歌曲
	 */
	public static void test_071() 
	{
		//主体
		excute(Object_Text, Operation_ClickWait, "播放列表");
		excute(Object_Text, Operation_LongClick, "最近添加的歌曲");
		excute(Object_Text, Operation_ClickWait, "编辑");
		check(Object_Text, Operation_checkExist, "设置时间");
	}
	/**
	 * 长按新建播放列表
	 */
	public static void test_072() 
	{
		//前提
		MusicPlayerCommon.addandlongclicknewplaylist();
		//主体
		check(Object_Text, Operation_checkExist, "播放");
		check(Object_Text, Operation_checkExist, "重命名");
		check(Object_Text, Operation_checkExist, "删除");
		//清场
		excute(Object_Text, Operation_ClickWait, "删除");
		excute(Object_Text, Operation_ClickWait, "确定");
	}
	/**
	 * 播放新建播放列表
	 */
	public static void test_073() 
	{
		//前提
		MusicPlayerCommon.addandlongclicknewplaylist();
		//主体
		excute(Object_Text, Operation_ClickWait, "播放");
		check(Object_ResourceId, Operation_checkExist, "com.android.music:id/pause");
		//清场
		excute(Object_ResourceId, Operation_ClickWait, "com.android.music:id/pause");
		excute(Object_Device, Operation_PressBack);
		MusicPlayerCommon.deleteallplaylist();
	}
	/**
	 * 删除新建播放列表
	 */
	public static void test_074() 
	{
		//主体
		MusicPlayerCommon.addandlongclicknewplaylist();
		excute(Object_Text, Operation_ClickWait, "删除");
		check(Object_Text, Operation_checkExist, "确定");
		//清场
		excute(Object_Text, Operation_ClickWait, "确定");
	}
	/**
	 * 取消删除新建播放列表
	 */
	public static void test_075() 
	{
		//前提
		excute(Object_Text, Operation_ClickWait, "歌曲");
		excute(Object_ResourceId, Operation_LongClick, "com.android.music:id/line1");
		excute(Object_Text, Operation_ClickWait, "添加到播放列表");
		excute(Object_Text, Operation_ClickWait, "新建播放列表");
		String playlistName = (String)excute(Object_ResourceId, Operation_GetText, "com.android.music:id/playlist");
		excute(Object_Text, Operation_ClickWait, "保存");
		//主体
		excute(Object_Text, Operation_ClickWait, "播放列表");
		excute(Object_Text, Operation_LongClick, playlistName);
		excute(Object_Text, Operation_ClickWait, "删除");
		excute(Object_Text, Operation_ClickWait, "取消");
		check(Object_Text, Operation_checkExist, playlistName);
		//清场
		MusicPlayerCommon.deleteallplaylist();
	}
	/**
	 * 确定删除新建播放列表
	 */
	public static void test_076() 
	{
		//前提
		excute(Object_Text, Operation_ClickWait, "歌曲");
		excute(Object_ResourceId, Operation_LongClick, "com.android.music:id/line1");
		excute(Object_Text, Operation_ClickWait, "添加到播放列表");
		excute(Object_Text, Operation_ClickWait, "新建播放列表");
		String playlistName = (String)excute(Object_ResourceId, Operation_GetText, "com.android.music:id/playlist");
		excute(Object_Text, Operation_ClickWait, "保存");
		//主体
		excute(Object_Text, Operation_ClickWait, "播放列表");
		excute(Object_Text, Operation_LongClick, playlistName);
		excute(Object_Text, Operation_ClickWait, "删除");
		excute(Object_Text, Operation_ClickWait, "确定");
		check(Object_Text, Operation_checkNoExist, playlistName);
	}
	/**
	 * 重命名新建播放列表
	 */
	public static void test_077() 
	{
		//前提
		excute(Object_Text, Operation_ClickWait, "歌曲");
		excute(Object_ResourceId, Operation_LongClick, "com.android.music:id/line1");
		excute(Object_Text, Operation_ClickWait, "添加到播放列表");
		excute(Object_Text, Operation_ClickWait, "新建播放列表");
		String playlistName = (String)excute(Object_ResourceId, Operation_GetText, "com.android.music:id/playlist");
		excute(Object_Text, Operation_ClickWait, "保存");
		//主体
		excute(Object_Text, Operation_ClickWait, "播放列表");
		excute(Object_Text, Operation_LongClick, playlistName);
		excute(Object_Text, Operation_ClickWait, "重命名");
		excute(Object_ResourceId, Operation_SetText, "com.android.music:id/playlist", "playlistName");
		excute(Object_Text, Operation_ClickWait, "保存");
		check(Object_ResIdContainsText, Operation_checkExist, "com.android.music:id/line1", "playlistName");
		//清场
		MusicPlayerCommon.deleteallplaylist();
	}
	/**
	 * 派对播放
	 */
	public static void test_078() 
	{
		//主体
		excute(Object_Text, Operation_ClickWait, "播放列表");
		excute(Object_Text, Operation_ClickWait, "最近添加的歌曲");
		excute(Object_Device, Operation_PressMenu);
		if((Boolean)excute(Object_Text, Operation_Exists, "派对随机播放"))
		{
			excute(Object_Text, Operation_ClickWait, "派对随机播放");
			excute(Object_Device, Operation_PressMenu);
			check(Object_Text, Operation_checkExist, "关闭派对随机播放");
			//清场
			excute(Object_Text, Operation_ClickWait, "关闭派对随机播放");
			excute(Object_ResourceId, Operation_ClickWait, "com.android.music:id/nowplaying");
			excute(Object_ResourceId, Operation_ClickWait, "com.android.music:id/pause");
		}else{
			check(Object_Text, Operation_checkExist, "关闭派对随机播放");
			excute(Object_Text, Operation_ClickWait, "关闭派对随机播放");
		}
	}
	/**
	 * 新建播放列表
	 */
	public static void test_079() 
	{
		//主体
		excute(Object_Text, Operation_ClickWait, "播放列表");
		excute(Object_Device, Operation_PressMenu);
		excute(Object_Text, Operation_ClickWait, "新建播放列表");
		String playlistName = (String)excute(Object_ResourceId, Operation_GetText, "com.android.music:id/playlist");
		excute(Object_Text, Operation_ClickWait, "保存");
		check(Object_Text, Operation_checkExist, playlistName);
		//清场
		MusicPlayerCommon.deleteallplaylist();
	}
	/**
	 * 退出
	 */
	public static void test_080() 
	{
		//主体
		excute(Object_Text, Operation_ClickWait, "歌曲");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.music:id/line1");
		excute(Object_Device, Operation_PressMenu);
		excute(Object_Text, Operation_ClickWait, "退出");
		check(Object_ResourceId, Operation_checkNoExist, "com.android.music:id/pause");//播放按钮
	}
	/**
	 * 播放界面查看
	 */
	public static void test_081() 
	{
		//主体
		excute(Object_Text, Operation_ClickWait, "歌曲");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.music:id/line1");
		check(Object_ResourceId, Operation_checkExist, "com.android.music:id/pause");//播放按钮
		check(Object_ResourceId, Operation_checkExist, "com.android.music:id/next");//下一首
		check(Object_ResourceId, Operation_checkExist, "com.android.music:id/prev");//上一首
		check(Object_ResourceId, Operation_checkExist, "com.android.music:id/curplaylist");//播放列表
		check(Object_ResourceId, Operation_checkExist, "com.android.music:id/shuffle");//随机播放
		check(Object_ResourceId, Operation_checkExist, "com.android.music:id/repeat");//重复播放
		//清场
		excute(Object_ResourceId, Operation_ClickWait, "com.android.music:id/pause");
	}
	/**
	 * 上一首
	 */
	public static void test_082() 
	{
		//主体
		excute(Object_Text, Operation_ClickWait, "歌曲");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.music:id/line1");
		String trackname = (String)excute(Object_ResourceId, Operation_GetText, "com.android.music:id/trackname");
		check(Object_ResourceId, Operation_ClickWait, "com.android.music:id/prev");//上一首
		check(Object_Text, Operation_checkNoExist, trackname);
		//清场
		excute(Object_ResourceId, Operation_ClickWait, "com.android.music:id/pause");
	}
	/**
	 * 暂停播放按钮
	 */
	public static void test_083() 
	{
		//主体
		excute(Object_Text, Operation_ClickWait, "歌曲");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.music:id/line1");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.music:id/pause");
//		String starttime = (String)excute(Object_ResourceId, Operation_GetText, "com.android.music:id/currenttime");
//		String stoptime = (String)excute(Object_ResourceId, Operation_GetText, "com.android.music:id/currenttime");
//		Assert.assertEquals(starttime, stoptime);
		
	}
	/**
	 * 播放按钮
	 */
	public static void test_084() 
	{
		//主体
		excute(Object_Text, Operation_ClickWait, "歌曲");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.music:id/line1");
		String starttime = (String)excute(Object_ResourceId, Operation_GetText, "com.android.music:id/currenttime");
		String stoptime = (String)excute(Object_ResourceId, Operation_GetText, "com.android.music:id/currenttime");
		Assert.assertNotSame(starttime, stoptime);
		//清场
		excute(Object_ResourceId, Operation_ClickWait, "com.android.music:id/pause");
	}
	/**
	 * 下一首
	 */
	public static void test_085() 
	{
		//主体
		excute(Object_Text, Operation_ClickWait, "歌曲");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.music:id/line1");
		String trackname = (String)excute(Object_ResourceId, Operation_GetText, "com.android.music:id/trackname");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.music:id/next");//上一首
		check(Object_Text, Operation_checkNoExist, trackname);
		//清场
		excute(Object_ResourceId, Operation_ClickWait, "com.android.music:id/pause");
	}
	/**
	 * 正在播放列表
	 */
	public static void test_086() 
	{
		//主体
		excute(Object_Text, Operation_ClickWait, "歌曲");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.music:id/line1");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.music:id/curplaylist");
		check(Object_Text, Operation_checkExist, "正在播放");
		//清场
		excute(Object_Device, Operation_PressBack);
		excute(Object_ResourceId, Operation_ClickWait, "com.android.music:id/pause");
	}
	/**
	 * 随机播放
	 */
	public void test_087() 
	{
		//主体
		excute(Object_Text, Operation_ClickWait, "歌曲");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.music:id/line1");
//		SettingCommon.take_temp_pic(getUiDevice(), "1");
//		MusicPlayerCommon.screenshort("1");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.music:id/shuffle");
//		SettingCommon.take_temp_pic(getUiDevice(), "2");
//		MusicPlayerCommon.screenshort("2");
//		SettingCommon.check_pic("1", "2", 0.8);
		//check(Object_ResourceId, Operation_CheckedTrue, "com.android.music:id/shuffle");
		//清场
		excute(Object_ResourceId, Operation_ClickWait, "com.android.music:id/pause");
	}
	/**
	 * 关闭随机播放
	 */
	public void test_088() 
	{
		//主体
		excute(Object_Text, Operation_ClickWait, "歌曲");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.music:id/line1");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.music:id/shuffle");
		//check(Object_ResourceId, Operation_CheckedTrue, "com.android.music:id/shuffle");
		//清场
		excute(Object_ResourceId, Operation_ClickWait, "com.android.music:id/pause");
	}
	/**
	 * 重复全部播放
	 */
	public void test_089() 
	{
		//主体
		excute(Object_Text, Operation_ClickWait, "歌曲");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.music:id/line1");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.music:id/repeat");
		//check(Object_ResourceId, Operation_CheckedTrue, "com.android.music:id/shuffle");
		//清场
		excute(Object_ResourceId, Operation_ClickWait, "com.android.music:id/pause");
	}
	/**
	 * 搜索
	 */
	public void test_091() 
	{
		//主体
		excute(Object_Text, Operation_ClickWait, "歌曲");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.music:id/line1");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.music:id/search");
		check(Object_ResourceId, Operation_checkExist, "android:id/search_src_text");//搜索按钮text id
		//清场
		excute(Object_Device, Operation_PressBack);
		excute(Object_ResourceId, Operation_ClickWait, "com.android.music:id/pause");
	}
	/**
	 * 播放界面点击菜单
	 */
	public void test_092() 
	{
		//主体
		excute(Object_Text, Operation_ClickWait, "歌曲");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.music:id/line1");
		excute(Object_Description, Operation_ClickWait, "更多选项");
		check(Object_Text, Operation_checkExist, "音乐库");
		check(Object_Text, Operation_checkExist, "添加到播放列表");
		check(Object_Text, Operation_checkExist, "删除");
		check(Object_Text, Operation_checkExist, "均衡器");
		check(Object_Text, Operation_checkExist, "用作铃声");
		check(Object_Text, Operation_checkExist, "退出");
		//清场
		excute(Object_Device, Operation_PressBack);
		excute(Object_ResourceId, Operation_ClickWait, "com.android.music:id/pause");
	}
	/**
	 * 播放界面点击音乐库
	 */
	public void test_093() 
	{
		//主体
		excute(Object_Text, Operation_ClickWait, "歌曲");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.music:id/line1");
		excute(Object_Description, Operation_ClickWait, "更多选项");
		excute(Object_Text, Operation_ClickWait, "音乐库");
		check(Object_ResourceId, Operation_checkNoExist, "com.android.music:id/pause");
		//清场
		excute(Object_ResourceId, Operation_ClickWait, "com.android.music:id/nowplaying");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.music:id/pause");
	}
	/**
	 * 播放界面点击派对随机播放
	 */
	public void test_094() 
	{
		//主体
		excute(Object_Text, Operation_ClickWait, "歌曲");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.music:id/line1");
		excute(Object_Description, Operation_ClickWait, "更多选项");
		excute(Object_Text, Operation_ClickWait, "派对随机播放");
		excute(Object_Description, Operation_ClickWait, "更多选项");
		check(Object_Text, Operation_checkExist, "关闭派对随机播放");
		//清场
		excute(Object_Text, Operation_ClickWait, "关闭派对随机播放");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.music:id/pause");
	}
	/**
	 * 播放界面点击添加到播放列表
	 */
	public void test_095() 
	{
		//主体
		excute(Object_Text, Operation_ClickWait, "歌曲");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.music:id/line1");
		excute(Object_Description, Operation_ClickWait, "更多选项");
		excute(Object_Text, Operation_ClickWait, "添加到播放列表");
		check(Object_Text, Operation_checkExist, "新建播放列表");
		//清场
		excute(Object_Device, Operation_PressBack);
		excute(Object_ResourceId, Operation_ClickWait, "com.android.music:id/pause");
	}
	/**
	 * 播放界面点击删除
	 */
	public void test_096() 
	{
		//主体
		excute(Object_Text, Operation_ClickWait, "歌曲");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.music:id/line1");
		String trackname = (String)excute(Object_ResourceId, Operation_GetText, "com.android.music:id/trackname");
		excute(Object_Description, Operation_ClickWait, "更多选项");
		excute(Object_Text, Operation_ClickWait, "删除");
		check(Object_ResIdContainsText, Operation_checkExist, "android:id/message", "要从设备中永久删除");
		//清场
		excute(Object_Device, Operation_PressBack);
		excute(Object_ResourceId, Operation_ClickWait, "com.android.music:id/pause");
	}
	/**
	 * 播放界面点击取消删除
	 */
	public void test_097() 
	{
		//主体
		excute(Object_Text, Operation_ClickWait, "歌曲");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.music:id/line1");
		String trackname = (String)excute(Object_ResourceId, Operation_GetText, "com.android.music:id/trackname");
		excute(Object_Description, Operation_ClickWait, "更多选项");
		excute(Object_Text, Operation_ClickWait, "删除");
		excute(Object_Text, Operation_ClickWait, "取消");
		check(Object_Text, Operation_checkExist, trackname);
		//清场
		excute(Object_ResourceId, Operation_ClickWait, "com.android.music:id/pause");
	}
	/**
	 * 播放界面点击确定删除
	 */
	public void test_198() 
	{
		//主体
		excute(Object_Text, Operation_ClickWait, "歌曲");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.music:id/line1");
		String trackname = (String)excute(Object_ResourceId, Operation_GetText, "com.android.music:id/trackname");
		excute(Object_Description, Operation_ClickWait, "更多选项");
		excute(Object_Text, Operation_ClickWait, "删除");
		excute(Object_Text, Operation_ClickWait, "确定");
		check(Object_Text, Operation_checkNoExist, trackname);
		//清场
		excute(Object_ResourceId, Operation_ClickWait, "com.android.music:id/pause");
	}
	/**
	 * 播放界面点击均衡器
	 */
	public void test_099() 
	{
		//主体
		excute(Object_Text, Operation_ClickWait, "歌曲");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.music:id/line1");
		String trackname = (String)excute(Object_ResourceId, Operation_GetText, "com.android.music:id/trackname");
		excute(Object_Description, Operation_ClickWait, "更多选项");
		excute(Object_Text, Operation_ClickWait, "均衡器");
		check(Object_Text, Operation_checkExist, "平调");
		//清场
		excute(Object_Device, Operation_PressBack);
		excute(Object_ResourceId, Operation_ClickWait, "com.android.music:id/pause");
	}
	/**
	 * 播放界面点击用作铃声
	 */
	public void test_100() 
	{
		//主体
		excute(Object_Text, Operation_ClickWait, "歌曲");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.music:id/line1");
		String trackname = (String)excute(Object_ResourceId, Operation_GetText, "com.android.music:id/trackname");
		excute(Object_Description, Operation_ClickWait, "更多选项");
		excute(Object_Text, Operation_ClickWait, "用作铃声");
		check(Object_ResIdContainsText, Operation_checkExist, "android:id/text1", "手机铃声");
		//清场
		excute(Object_Device, Operation_PressBack);
		excute(Object_ResourceId, Operation_ClickWait, "com.android.music:id/pause");
	}
	/**
	 * 播放界面点击退出
	 */
	public void test_101() 
	{
		//主体
		excute(Object_Text, Operation_ClickWait, "歌曲");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.music:id/line1");
		String trackname = (String)excute(Object_ResourceId, Operation_GetText, "com.android.music:id/trackname");
		excute(Object_Description, Operation_ClickWait, "更多选项");
		excute(Object_Text, Operation_ClickWait, "退出");
		check(Object_ResourceId, Operation_checkNoExist, "com.android.music:id/pause");
	}
	

}
