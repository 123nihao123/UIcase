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
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_001() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		check(Object_Text,Operation_checkExist,"音乐");
	}
	
	/**
	 * 进入音乐界面 -存在音乐人，专辑，歌曲，播放列表4个tab
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_002() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		check(Object_ResIdText,Operation_checkExist,"com.android.music:id/artisttab","音乐人");
		check(Object_ResIdText,Operation_checkExist,"com.android.music:id/albumtab","专辑");
		check(Object_ResIdText,Operation_checkExist,"com.android.music:id/songtab","歌曲");
		check(Object_ResIdText,Operation_checkExist,"com.android.music:id/playlisttab","播放列表");
	}
	
	/**
	 * 进入音乐界面-存在搜索图标
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_003() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		check(Object_ResIdDesc,Operation_checkExist,"com.android.music:id/search","搜索");
	}
	
	/**
	 * 进入音乐界面--菜单-“派对随机播放”“全部随机播放”“退出”功能
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_004() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_Description,Operation_ClickWait,"更多选项");
		check(Object_Text,Operation_checkExist,"派对随机播放");
		check(Object_Text,Operation_checkExist,"全部随机播放");
		check(Object_Text,Operation_checkExist,"退出");
	}
	
	/**
	 * 进入音乐界面 -专辑-点击一个专辑展开
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_032() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_ResIdText,Operation_ClickWait,"com.android.music:id/albumtab","专辑");
		String music = (String) excute(Object_ResIdInstance,Operation_GetText,"com.android.music:id/line1","0");
		excute(Object_ResIdInstance,Operation_ClickWait,"com.android.music:id/line1","0");
		check(Object_Text,Operation_checkExist,music);
	}
	
	/**
	 * 进入音乐界面 -专辑-长按一个专辑，出现“播放”“添加到播放列表””删除”等字样
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_033() throws UiObjectNotFoundException, RemoteException 
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
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_034() throws UiObjectNotFoundException, RemoteException 
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
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_035() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_ResIdText,Operation_ClickWait,"com.android.music:id/albumtab","专辑");
		excute(Object_ResIdInstance,Operation_LongClick,"com.android.music:id/line1","0");
		excute(Object_Text,Operation_ClickWait,"添加到播放列表");
		check(Object_ResIdText,Operation_checkExist,"android:id/alertTitle","添加到播放列表");
	}
	
	/**
	 * 进入音乐界面 -专辑-长按一个专辑，点击删除，取消删除
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_036() throws UiObjectNotFoundException, RemoteException 
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
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_037() throws UiObjectNotFoundException, RemoteException 
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
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_039() throws UiObjectNotFoundException, RemoteException 
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
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_040() throws UiObjectNotFoundException, RemoteException 
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
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_041() throws UiObjectNotFoundException, RemoteException 
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
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_042() throws UiObjectNotFoundException, RemoteException 
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
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_043() throws UiObjectNotFoundException, RemoteException 
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
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_045() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_ResIdText,Operation_ClickWait,"com.android.music:id/albumtab","专辑");
		String music = (String) excute(Object_ResIdInstance,Operation_GetText,"com.android.music:id/line1","0");
		excute(Object_ResIdInstance,Operation_ClickWait,"com.android.music:id/line1","0");
		excute(Object_ResIdInstance,Operation_LongClick,"com.android.music:id/line1","0");
		excute(Object_Text,Operation_ClickWait,"删除");
		excute(Object_Text,Operation_ClickWait,"去掉");
		check(Object_Text,Operation_checkNoExist,music);
	}
	
	/**
	 * 进入音乐界面 -专辑-点击一个专辑，长按一首歌，点击搜索
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_046() throws UiObjectNotFoundException, RemoteException 
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
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_047() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_ResIdText,Operation_ClickWait,"com.android.music:id/albumtab","专辑");
		excute(Object_ResIdInstance,Operation_ClickWait,"com.android.music:id/line1","0");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		 if((Boolean)excute(Object_Text, Operation_Exists, "派对随机播放"))
		  {
		   excute(Object_Text, Operation_ClickWait, "派对随机播放");
		   excute(Object_Description,Operation_ClickWait,"更多选项");
		  }
		  check(Object_Text, Operation_checkExist, "关闭派对随机播放");
		  //清场
		   excute(Object_Text, Operation_ClickWait, "关闭派对随机播放");
		   excute(Object_ResourceId, Operation_ClickWait, "com.android.music:id/nowplaying");
		   excute(Object_ResourceId, Operation_ClickWait, "com.android.music:id/pause");
	}
	
	/**
	 * 进入音乐界面 -专辑-点击一个专辑-菜单-全部随机播放
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_048() throws UiObjectNotFoundException, RemoteException 
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
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_049() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_ResIdText,Operation_ClickWait,"com.android.music:id/albumtab","专辑");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"退出");
		check(Object_Text,Operation_checkNoExist,"专辑");
	}
	
	/**
	 * 进入音乐界面 -歌曲-选择一首歌曲播放
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_051() throws UiObjectNotFoundException, RemoteException 
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
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_052() throws UiObjectNotFoundException, RemoteException 
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
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_053() throws UiObjectNotFoundException, RemoteException 
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
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_054() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_ResIdText,Operation_ClickWait,"com.android.music:id/songtab","歌曲");
		excute(Object_ResIdInstance,Operation_LongClick,"com.android.music:id/line1","0");
		excute(Object_Text, Operation_ClickWait, "添加到播放列表");
		check(Object_ResIdText,Operation_checkExist,"android:id/alertTitle","添加到播放列表");
		
	}
	
	/**
	 * 进入音乐界面 -歌曲-选择长按一首歌曲，点击删除
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_055() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_ResIdText,Operation_ClickWait,"com.android.music:id/songtab","歌曲");
		excute(Object_ResIdInstance,Operation_LongClick,"com.android.music:id/line1","0");
		excute(Object_Text, Operation_ClickWait, "删除");
		check(Object_ResourceId,Operation_checkExist,"com.android.music:id/prompt");
	}
	
	/**
	 * 进入音乐界面 -歌曲-选择长按一首歌曲，点击删除后取消
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_056() throws UiObjectNotFoundException, RemoteException 
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
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_058() throws UiObjectNotFoundException, RemoteException 
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
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_059() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_ResIdText,Operation_ClickWait,"com.android.music:id/songtab","歌曲");
		excute(Object_ResIdInstance,Operation_LongClick,"com.android.music:id/line1","0");
		excute(Object_Text,Operation_ClickWait,"搜索");
		check(Object_ResourceId,Operation_TextContainsTrue,"android:id/title","使用以下工具");
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
		check(Object_ResourceId, Operation_ClickWait, "com.android.music:id/next");//上一首
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
	public  void test_087() 
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
	 * 关闭随机播放
	 */
	public  void test_088() 
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
	public  void test_089() 
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
	public  void test_091() 
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
	public  void test_092() 
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
	public  void test_093() 
	{
		//主体
		excute(Object_Text, Operation_ClickWait, "歌曲");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.music:id/line1");
		excute(Object_Description, Operation_ClickWait, "更多选项");
		excute(Object_Description, Operation_ClickWait, "音乐库");
		check(Object_Text, Operation_checkNoExist, "com.android.music:id/pause");
		//清场
		excute(Object_ResourceId, Operation_ClickWait, "com.android.music:id/nowplaying");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.music:id/pause");
	}

}
