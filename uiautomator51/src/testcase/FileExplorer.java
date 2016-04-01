package testcase;


//import org.junit.AfterClass;  
//import org.junit.BeforeClass;

import static framework.data.ObjectType.*;
import static framework.data.OperationType.*;
import static framework.data.ResIdTextAndDesc.*;
import static framework.excute.Excute.*;

import java.text.ParseException;
import java.util.Arrays;

import junit.framework.Assert;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.RemoteException;
import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import framework.common.CallCommon;
import framework.common.CallLogCommon;
import framework.common.ContactCommon;
import framework.common.DeviceCommon;
import framework.common.FileExplorerCommon;
import framework.common.MusicPlayerCommon;
import framework.common.SettingCommon;
public class FileExplorer extends UiAutomatorTestCase
{
	@Override
	protected void setUp() throws UiObjectNotFoundException, RemoteException 
    {
		System.out.println("Enter the setUp!!!");	
		excute(Object_Device, Operation_WakeUp);
		DeviceCommon.unLock();	
		ClearBackgroundApp();
		Wait(1000);
		DeviceCommon.enterApp(Devices_Desc_FileManage);
   }
	@Override
	protected void tearDown() throws UiObjectNotFoundException, RemoteException 
    {
    }
	
	/**
	 * 进入文件管理器界面
	 */
	public static void test_001() 
	{
		//主体
		check(Object_Text, Operation_checkExist, "快速查看");
	}
	/**
	 * 点击快速查看
	 */
	public static void test_002() 
	{
		//主体
		excute(Object_Text, Operation_ClickWait, "快速查看");
		check(Object_Text, Operation_checkExist, "快速查看");
		check(Object_Text, Operation_checkExist, "手机");
		check(Object_Text, Operation_checkExist, "存储卡");
	}
	/**
	 * 选择快速查看
	 */
	public static void test_003() 
	{
		//主体
		excute(Object_Text, Operation_ClickWait, "快速查看");
		excute(Object_Text, Operation_ClickWait, "快速查看");
		check(Object_Text, Operation_checkExist, "快速查看");
	}
	/**
	 * 点击手机
	 */
	public static void test_004() 
	{
		//主体
		excute(Object_Text, Operation_ClickWait, "快速查看");
		excute(Object_Text, Operation_ClickWait, "手机");
		check(Object_Text, Operation_checkExist, "手机");
	}
	/**
	 * 点击存储卡
	 */
	public static void test_005() 
	{
		//主体
		excute(Object_Text, Operation_ClickWait, "快速查看");
		excute(Object_Text, Operation_ClickWait, "存储卡");
		check(Object_Text, Operation_checkExist, "存储卡");
	}
	/**
	 * 点击搜索图标
	 */
	public static void test_006() 
	{
		//主体
		excute(Object_ResourceId, Operation_ClickWait, "com.sprd.fileexplorer:id/searchfile");//点击搜索图标
		check(Object_Text, Operation_checkExist, "搜索");
	}
	/**
	 * 点击更多菜单
	 */
	public static void test_007() 
	{
		//主体
		excute(Object_Description, Operation_ClickWait, "更多选项");
		check(Object_Text, Operation_checkExist, "设置");
	}
	/**
	 * 点击更多菜单的设置
	 */
	public static void test_008() 
	{
		//主体
		FileExplorerCommon.menu("设置");
		check(Object_Text, Operation_checkExist, "显示隐藏的文件");
	}
	/**
	 * 快速查看界面显示
	 */
	public static void test_009() 
	{
		//主体
		check(Object_Text, Operation_checkExist, "音乐");
		check(Object_Text, Operation_checkExist, "图片");
		check(Object_Text, Operation_checkExist, "视频");
		check(Object_Text, Operation_checkExist, "文档");
		check(Object_Text, Operation_checkExist, "APK安装文件");
	}
	/**
	 * 进入音乐界面
	 */
	public static void test_010() 
	{
		//主体
		FileExplorerCommon.Enterclass("音乐");
		check(Object_Text, Operation_checkExist, "音乐");
	}
	/**
	 * 长按音乐
	 */
	public static void test_011() 
	{
		//主体
		FileExplorerCommon.Enterclass("音乐");
		excute(Object_ResourceId, Operation_LongClick, "com.sprd.fileexplorer:id/file_item_list_name");
		check(Object_Text, Operation_checkExist, "复制");
		check(Object_Text, Operation_checkExist, "剪切");
		check(Object_Text, Operation_checkExist, "删除");
		check(Object_Text, Operation_checkExist, "重命名");
		check(Object_Text, Operation_checkExist, "分享");
		check(Object_Text, Operation_checkExist, "属性");
	}
	/**
	 * 长按音乐复制
	 */
	public static void test_012() 
	{
		//主体
		FileExplorerCommon.Enterclass("音乐");
		FileExplorerCommon.Longclickmenu("复制");
		check(Object_Text, Operation_checkExist, "选择存储位置");
	}
	/**
	 * 长按音乐剪切
	 */
	public static void test_013() 
	{
		//主体
		FileExplorerCommon.Enterclass("音乐");
		FileExplorerCommon.Longclickmenu("剪切");
		check(Object_Text, Operation_checkExist, "选择存储位置");
	}
	/**
	 * 长按音乐删除
	 */
	public static void test_014() 
	{
		//主体
		FileExplorerCommon.Enterclass("音乐");
		FileExplorerCommon.Longclickmenu("删除");
		check(Object_Text, Operation_checkExist, "要删除所选内容吗？");
	}
	/**
	 * 长按音乐取消删除
	 */
	public static void test_015() 
	{
		//主体
		FileExplorerCommon.Enterclass("音乐");
		String Musicname = (String)excute(Object_ResourceId, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_name");
		FileExplorerCommon.Longclickmenu("删除");
		excute(Object_Text, Operation_ClickWait, "取消");
		check(Object_Text, Operation_checkExist, Musicname);
	}
	/**
	 * 长按音乐确定删除
	 */
//	public static void test_316() 
//	{
//		//主体
//		FileExplorerCommon.Enterclass("音乐");
//		String Musicname = (String)excute(Object_ResourceId, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_name");
//		FileExplorerCommon.Longclickmenu("删除");
//		excute(Object_Text, Operation_ClickWait, "确定");
//		check(Object_Text, Operation_checkNoExist, Musicname);
//	}
	/**
	 * 长按音乐重命名查看
	 */
	public static void test_017() 
	{
		//主体
		FileExplorerCommon.Enterclass("音乐");
		FileExplorerCommon.Longclickmenu("重命名");
		check(Object_Text, Operation_checkExist, "确定");
	}
	/**
	 * 长按音乐重命名
	 */
	public static void test_018() 
	{
		//主体
		FileExplorerCommon.Enterclass("音乐");
		String music = (String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_name", "0");
		FileExplorerCommon.Longclickmenu("重命名");
		String prefix = music.substring(music.lastIndexOf(".")+1);
		System.out.println(prefix);	
		System.out.println("Musicname."+prefix);	
		excute(Object_ResourceId, Operation_SetText, "com.sprd.fileexplorer:id/name_editor", "Musicname");
		excute(Object_Text, Operation_ClickWait, "确定");
		if(!(Boolean) excute(Object_Text, Operation_Exists, "Musicname."+prefix))
			excute(Object_TextScroll, Operation_Exists, "Musicname."+prefix, "vertical");
		check(Object_Text, Operation_checkExist, "Musicname."+prefix);
		//清场
		excute(Object_Text, Operation_LongClick, "Musicname."+prefix);
		excute(Object_Text, Operation_ClickWait, "重命名");
		excute(Object_ResourceId, Operation_SetText, "com.sprd.fileexplorer:id/name_editor", music);
		excute(Object_Text, Operation_ClickWait, "确定");
	}
	/**
	 * 长按音乐分享
	 */
	public static void test_019() 
	{
		//主体
		FileExplorerCommon.Enterclass("音乐");
		FileExplorerCommon.Longclickmenu("分享");
		check(Object_Text, Operation_checkExist, "信息");
		check(Object_Text, Operation_checkExist, "电子邮件");
		check(Object_Text, Operation_checkExist, "蓝牙");
	}
	/**
	 * 长按音乐属性
	 */
	public static void test_020() 
	{
		//主体
		FileExplorerCommon.Enterclass("音乐");
		FileExplorerCommon.Longclickmenu("属性");
		check(Object_ResourceId, Operation_checkExist, "android:id/content");//弹框ID
	}
	/**
	 * 音乐界面菜单键
	 */
	public static void test_021() 
	{
		//主体
		FileExplorerCommon.classmenu("音乐");
		check(Object_Text, Operation_checkExist, "选择多个");
		check(Object_Text, Operation_checkExist, "排序方式");
	}
	/**
	 * 音乐界面选择多个
	 */
	public static void test_022() 
	{
		//主体
		FileExplorerCommon.select("音乐");
		check(Object_Text, Operation_checkExist, "选择全部");
	}
	/**
	 * 音乐界面选择多个任意选择
	 */
	public static void test_023() 
	{
		//主体
		FileExplorerCommon.select("音乐");
		check(Object_ResIdInstance, Operation_CheckedTrue, "com.sprd.fileexplorer:id/select_checkbox", "0");
	}
	/**
	 * 音乐界面选择多个点击删除按钮
	 */
	public static void test_024() 
	{
		//主体
		FileExplorerCommon.select("音乐");
		excute(Object_ResourceId, Operation_ClickWait, "com.sprd.fileexplorer:id/menu_delete");
		check(Object_Text, Operation_checkExist, "要删除所选内容吗？");
	}
	/**
	 * 音乐界面选择多个点击菜单
	 */
	public static void test_025() 
	{
		//主体
		FileExplorerCommon.select("音乐");
		excute(Object_Device, Operation_PressMenu);
		check(Object_Text, Operation_checkExist, "复制");
		check(Object_Text, Operation_checkExist, "剪切");
		check(Object_Text, Operation_checkExist, "分享");
	}
	/**
	 * 音乐界面选择多个点击菜单复制
	 */
	public static void test_026() 
	{
		//主体
		FileExplorerCommon.select("音乐");
		FileExplorerCommon.menu("复制");
		check(Object_Text, Operation_checkExist, "选择存储位置");
	}
	/**
	 * 音乐界面选择多个点击菜单剪切
	 */
	public static void test_027() 
	{
		//主体
		FileExplorerCommon.select("音乐");
		FileExplorerCommon.menu("剪切");
		check(Object_Text, Operation_checkExist, "选择存储位置");
	}
	/**
	 * 音乐界面选择多个点击菜单剪切
	 */
	public static void test_028() 
	{
		//主体
		FileExplorerCommon.select("音乐");
		FileExplorerCommon.menu("分享");
		check(Object_Text, Operation_checkExist, "信息");
		check(Object_Text, Operation_checkExist, "电子邮件");
		check(Object_Text, Operation_checkExist, "蓝牙");
	}
	/**
	* 音乐界面选择多个点击选择全部
	*/
	public static void test_029() 
	{
		//主体
		FileExplorerCommon.select("音乐");
		excute(Object_ResourceId, Operation_ClickWait, "com.sprd.fileexplorer:id/select_all_cb");
		int Num = ((Integer)(excute(Object_ResourceId, Operation_GetChildCount, "com.sprd.fileexplorer:id/list"))).intValue();
		 for(int i=0; i<Num; i++)
		 {
			 check(Object_ResIdInstance, Operation_CheckedTrue, "com.sprd.fileexplorer:id/select_checkbox", String.valueOf(i));
		 }
	}
	/**
	 * 音乐界面选择多个选择全部变成取消全部
	 */
	public static void test_030() 
	{
		//主体
		FileExplorerCommon.select("音乐");
		excute(Object_ResourceId, Operation_ClickWait, "com.sprd.fileexplorer:id/select_all_cb");
		check(Object_Text, Operation_checkExist, "取消全部");
	}
	/**
	 * 音乐界面选择多个点击取消全部
	 */
	public static void test_031() 
	{
		//主体
		FileExplorerCommon.select("音乐");
		excute(Object_ResourceId, Operation_ClickWait, "com.sprd.fileexplorer:id/select_all_cb");
		excute(Object_ResourceId, Operation_ClickWait, "com.sprd.fileexplorer:id/select_all_cb");
		int Num = ((Integer)(excute(Object_ResourceId, Operation_GetChildCount, "com.sprd.fileexplorer:id/list"))).intValue();
		 for(int i=0; i<Num; i++)
		 {
			 check(Object_ResIdInstance, Operation_CheckedFalse, "com.sprd.fileexplorer:id/select_checkbox", String.valueOf(i));
		 }
		check(Object_ResourceId, Operation_EnabledFalse, "com.sprd.fileexplorer:id/menu_delete");
	}
	/**
	 * 音乐界面排列方式
	 */
	public static void test_032() 
	{
		//主体
		FileExplorerCommon.classmenu("音乐");
		excute(Object_Text, Operation_ClickWait, "排序方式");
		check(Object_ResIdText, Operation_checkExist, "android:id/alertTitle", "排序方式");
		check(Object_ResIdText, Operation_checkExist, "android:id/text1", "按名称");
		check(Object_ResIdText, Operation_checkExist, "android:id/text1", "按文件类型");
		check(Object_ResIdText, Operation_checkExist, "android:id/text1", "按时间");
		check(Object_ResIdText, Operation_checkExist, "android:id/text1", "按大小");
	}
	/**
	 * 音乐界面排列方式按名称排序
	 */
	public static void test_033() 
	{
		//主体
		FileExplorerCommon.classmenu("音乐");
		excute(Object_Text, Operation_ClickWait, "排序方式");
		excute(Object_Text, Operation_ClickWait, "按名称");
		excute(Object_Text, Operation_ClickWait, "升序");
		String firname = (String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_name", "0");
		String scename = (String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_name", "1");
		String thrname = (String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_name", "2");
		String bname[] = {firname, scename, thrname};
		Assert.assertTrue(FileExplorerCommon.isSortedByName(bname, false));
		excute(Object_Device, Operation_PressMenu);
		excute(Object_Text, Operation_ClickWait, "排序方式");
		excute(Object_Text, Operation_ClickWait, "按名称");
		excute(Object_Text, Operation_ClickWait, "降序");
		String finame = (String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_name", "0");
		String scname = (String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_name", "1");
		String thname = (String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_name", "2");
		String name[] = {finame, scname, thname};
		Assert.assertTrue(FileExplorerCommon.isSortedByName(name, true));
	}
	/**
	 * 音乐界面排列方式按文件类型排序
	 */
	public static void test_034() 
	{
		//主体
		FileExplorerCommon.classmenu("音乐");
		excute(Object_Text, Operation_ClickWait, "排序方式");
		excute(Object_Text, Operation_ClickWait, "按文件类型");
		excute(Object_Text, Operation_ClickWait, "升序");
		String firname = (String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_name", "0");
		String scename = (String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_name", "1");
		String thrname = (String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_name", "2");
		String bname[] = {firname, scename, thrname};
		Assert.assertTrue(FileExplorerCommon.isSortedByType(bname));
		excute(Object_Device, Operation_PressMenu);
		excute(Object_Text, Operation_ClickWait, "排序方式");
		excute(Object_Text, Operation_ClickWait, "按文件类型");
		excute(Object_Text, Operation_ClickWait, "降序");
		String Tim = (String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_msg", "0");
		String scTime = (String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_msg", "1");
		String thTime = (String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_msg", "2");
		String ti[] = {Tim, scTime, thTime};
		Assert.assertTrue(FileExplorerCommon.isSortedByType(ti, true));
	}
	/**
	 * 音乐界面排列方式按时间排序
	 * @throws ParseException 
	 */
	public static void test_035() throws ParseException 
	{
		//主体
		FileExplorerCommon.classmenu("音乐");
		excute(Object_Text, Operation_ClickWait, "排序方式");
		excute(Object_Text, Operation_ClickWait, "按时间");
		excute(Object_Text, Operation_ClickWait, "升序");
		String Time = (String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_msg", "0");
		String sceTime = (String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_msg", "1");
		String thrTime = (String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_msg", "2");
		String tim[] = {Time, sceTime, thrTime};
		Assert.assertTrue(FileExplorerCommon.isSortedByTime(tim));
		excute(Object_Device, Operation_PressMenu);
		excute(Object_Text, Operation_ClickWait, "排序方式");
		excute(Object_Text, Operation_ClickWait, "按时间");
		excute(Object_Text, Operation_ClickWait, "降序");
		String Tim = (String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_msg", "0");
		String scTime = (String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_msg", "1");
		String thTime = (String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_msg", "2");
		String ti[] = {Tim, scTime, thTime};
		Assert.assertTrue(FileExplorerCommon.isSortedByTime(ti));
	}
	/**
	 * 音乐界面排列方式按大小排序
	 * @throws ParseException 
	 */
	public static void test_036() throws ParseException 
	{
		//主体
		FileExplorerCommon.classmenu("音乐");
		excute(Object_Text, Operation_ClickWait, "排序方式");
		excute(Object_Text, Operation_ClickWait, "按大小");
		excute(Object_Text, Operation_ClickWait, "升序");
		String Size = (String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_msg", "0");
		String sceSize = (String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_msg", "1");
		String thrSize = (String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_msg", "2");
		String num[] = {Size, sceSize, thrSize};
		Assert.assertTrue(FileExplorerCommon.isSortedBySize(num));
		excute(Object_Device, Operation_PressMenu);
		excute(Object_Text, Operation_ClickWait, "排序方式");
		excute(Object_Text, Operation_ClickWait, "按大小");
		excute(Object_Text, Operation_ClickWait, "降序");
		String Siz = (String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_msg", "0");
		String scSize = (String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_msg", "1");
		String thSize = (String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_msg", "2");
		String nu[] = {Siz, scSize, thSize};
		Assert.assertTrue(FileExplorerCommon.isSortedBySize(nu, true));
	}
	/**
	 * 播放音乐
	 */
	public static void test_037() 
	{
		//主体
		FileExplorerCommon.Enterclass("音乐");
		excute(Object_ResourceId, Operation_ClickWait, "com.sprd.fileexplorer:id/file_item_list_name");
		check(Object_ResourceId, Operation_checkExist, "com.android.music:id/playpause");//播放暂停按钮
		
	}
	/**
	 * 进入图片
	 */
	public static void test_038() 
	{
		//主体
		FileExplorerCommon.Enterclass("图片");
		check(Object_Text, Operation_checkExist, "图片");
		
	}
	/**
	 * 进入图片长按图片
	 */
	public static void test_039() 
	{
		//主体
		FileExplorerCommon.Enterclass("图片");
		excute(Object_ResourceId, Operation_LongClick, "com.sprd.fileexplorer:id/file_item_list_name");
		check(Object_Text, Operation_checkExist, "复制");
		check(Object_Text, Operation_checkExist, "剪切");
		check(Object_Text, Operation_checkExist, "删除");
		check(Object_Text, Operation_checkExist, "重命名");
		check(Object_Text, Operation_checkExist, "分享");
		check(Object_Text, Operation_checkExist, "属性");
	}
	/**
	 * 长按图片复制
	 */
	public static void test_040() 
	{
		//主体
		FileExplorerCommon.Enterclass("图片");
		FileExplorerCommon.Longclickmenu("复制");
		check(Object_Text, Operation_checkExist, "选择存储位置");
		}
	/**
	 * 长按图片复制
	 */
	public static void test_041() 
	{
		//主体
		FileExplorerCommon.Enterclass("图片");
		FileExplorerCommon.Longclickmenu("剪切");
		check(Object_Text, Operation_checkExist, "选择存储位置");
	}
	/**
	 * 长按图片删除
	 */
	public static void test_042() 
	{
		//主体
		FileExplorerCommon.Enterclass("图片");
		FileExplorerCommon.Longclickmenu("删除");
		check(Object_Text, Operation_checkExist, "要删除所选内容吗？");
	}
	/**
	 * 长按图片取消删除
	 */
	public static void test_043() 
	{
		//主体
		FileExplorerCommon.Enterclass("图片");
		String Musicname = (String)excute(Object_ResourceId, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_name");
		FileExplorerCommon.Longclickmenu("删除");
		excute(Object_Text, Operation_ClickWait, "取消");
		check(Object_Text, Operation_checkExist, Musicname);
	}
	/**
	 * 长按图片确定删除
	 */
//	public static void test_344() 
//	{
//		//主体
//		FileExplorerCommon.Enterclass("图片");
//		String Picturename = (String)excute(Object_ResourceId, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_name");
//		FileExplorerCommon.Longclickmenu("删除");
//		excute(Object_Text, Operation_ClickWait, "确定");
//		check(Object_Text, Operation_checkNoExist, Picturename);
//	}
	/**
	 * 长按图片重命名查看
	 */
	public static void test_045() 
	{
		//主体
		FileExplorerCommon.Enterclass("图片");
		FileExplorerCommon.Longclickmenu("重命名");
		check(Object_Text, Operation_checkExist, "确定");
	}
	/**
	 * 长按图片重命名
	 */
	public static void test_046() 
	{
		//主体
		FileExplorerCommon.Enterclass("图片");
		String Picture = (String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_name", "0");
		FileExplorerCommon.Longclickmenu("重命名");
		String prefix = Picture.substring(Picture.lastIndexOf(".")+1);
		System.out.println(prefix);	
		System.out.println("Picturename."+prefix);	
		excute(Object_ResourceId, Operation_SetText, "com.sprd.fileexplorer:id/name_editor", "Picturename");
		excute(Object_Text, Operation_ClickWait, "确定");
		if(!(Boolean) excute(Object_Text, Operation_Exists, "Picturename."+prefix))
			excute(Object_TextScroll, Operation_Exists, "Picturename."+prefix, "vertical");
		check(Object_Text, Operation_checkExist, "Picturename."+prefix);
		//清场
		excute(Object_Text, Operation_LongClick, "Picturename."+prefix);
		excute(Object_Text, Operation_ClickWait, "重命名");
		excute(Object_ResourceId, Operation_SetText, "com.sprd.fileexplorer:id/name_editor", Picture);
		excute(Object_Text, Operation_ClickWait, "确定");
	}
	/**
	 * 长按图片分享
	 */
	public static void test_047() 
	{
		//主体
		FileExplorerCommon.Enterclass("图片");
		FileExplorerCommon.Longclickmenu("分享");
		check(Object_Text, Operation_checkExist, "信息");
		check(Object_Text, Operation_checkExist, "电子邮件");
		check(Object_Text, Operation_checkExist, "蓝牙");
	}
	/**
	 * 长按图片设置为
	 */
	public static void test_048() 
	{
		//主体
		FileExplorerCommon.Enterclass("图片");
		FileExplorerCommon.Longclickmenu("设置为");
		check(Object_Text, Operation_checkExist, "联系人照片");
		check(Object_Text, Operation_checkExist, "壁纸");
	}
	/**
	 * 长按图片属性
	 */
	public static void test_049() 
	{
		//主体
		FileExplorerCommon.Enterclass("图片");
		FileExplorerCommon.Longclickmenu("属性");
		check(Object_ResourceId, Operation_checkExist, "android:id/content");//弹框ID
	}
	/**
	 * 图片界面菜单键
	 */
	public static void test_050() 
	{
		//主体
		FileExplorerCommon.classmenu("图片");
		check(Object_Text, Operation_checkExist, "选择多个");
		check(Object_Text, Operation_checkExist, "排序方式");
	}
	/**
	 * 图片界面选择多个
	 */
	public static void test_051() 
	{
		//主体
		FileExplorerCommon.select("图片");
		check(Object_Text, Operation_checkExist, "选择全部");
	}
	/**
	 * 图片界面选择多个任意选择
	 */
	public static void test_052() 
	{
		//主体
		FileExplorerCommon.select("图片");
		check(Object_ResIdInstance, Operation_CheckedTrue, "com.sprd.fileexplorer:id/select_checkbox", "0");
	}
	/**
	 * 图片界面选择多个点击删除按钮
	 */
	public static void test_053() 
	{
		//主体
		FileExplorerCommon.select("图片");
		excute(Object_ResourceId, Operation_ClickWait, "com.sprd.fileexplorer:id/menu_delete");
		check(Object_Text, Operation_checkExist, "要删除所选内容吗？");
	}
	/**
	 * 图片界面选择多个点击菜单
	 */
	public static void test_054() 
	{
		//主体
		FileExplorerCommon.select("图片");
		excute(Object_Device, Operation_PressMenu);
		check(Object_Text, Operation_checkExist, "复制");
		check(Object_Text, Operation_checkExist, "剪切");
		check(Object_Text, Operation_checkExist, "分享");
	}
	/**
	 * 图片界面选择多个点击菜单复制
	 */
	public static void test_055() 
	{
		//主体
		FileExplorerCommon.select("图片");
		FileExplorerCommon.menu("复制");
		check(Object_Text, Operation_checkExist, "选择存储位置");
	}
	/**
	 * 图片界面选择多个点击菜单剪切
	 */
	public static void test_056() 
	{
		//主体
		FileExplorerCommon.select("图片");
		FileExplorerCommon.menu("剪切");
		check(Object_Text, Operation_checkExist, "选择存储位置");
	}
	/**
	 * 图片界面选择多个点击菜单剪切
	 */
	public static void test_057() 
	{
		//主体
		FileExplorerCommon.select("图片");
		FileExplorerCommon.menu("分享");
		check(Object_Text, Operation_checkExist, "信息");
		check(Object_Text, Operation_checkExist, "电子邮件");
		check(Object_Text, Operation_checkExist, "蓝牙");
	}
	/**
	 * 图片界面选择多个点击选择全部
	 */
	public static void test_058() 
	{
		//主体
		FileExplorerCommon.select("图片");
		excute(Object_ResourceId, Operation_ClickWait, "com.sprd.fileexplorer:id/select_all_cb");
		int Num = ((Integer)(excute(Object_ResourceId, Operation_GetChildCount, "com.sprd.fileexplorer:id/list"))).intValue();
		 for(int i=0; i<Num; i++)
		 {
			 check(Object_ResIdInstance, Operation_CheckedTrue, "com.sprd.fileexplorer:id/select_checkbox", String.valueOf(i));
		 }
	}
	/**
	 * 图片界面选择多个选择全部变成取消全部
	 */
	public static void test_059() 
	{
		//主体
		FileExplorerCommon.select("图片");
		excute(Object_ResourceId, Operation_ClickWait, "com.sprd.fileexplorer:id/select_all_cb");
		check(Object_Text, Operation_checkExist, "取消全部");
	}
	/**
	 * 图片界面选择多个点击取消全部
	 */
	public static void test_060() 
	{
		//主体
		FileExplorerCommon.select("图片");
		excute(Object_ResourceId, Operation_ClickWait, "com.sprd.fileexplorer:id/select_all_cb");
		excute(Object_ResourceId, Operation_ClickWait, "com.sprd.fileexplorer:id/select_all_cb");
		int Num = ((Integer)(excute(Object_ResourceId, Operation_GetChildCount, "com.sprd.fileexplorer:id/list"))).intValue();
		 for(int i=0; i<Num; i++)
		 {
			 check(Object_ResIdInstance, Operation_CheckedFalse, "com.sprd.fileexplorer:id/select_checkbox", String.valueOf(i));
		 }
		check(Object_ResourceId, Operation_EnabledFalse, "com.sprd.fileexplorer:id/menu_delete");
	}
	/**
	 * 图片界面排列方式
	 */
	public static void test_061() 
	{
		//主体
		FileExplorerCommon.classmenu("图片");
		excute(Object_Text, Operation_ClickWait, "排序方式");
		check(Object_ResIdText, Operation_checkExist, "android:id/alertTitle", "排序方式");
		check(Object_ResIdText, Operation_checkExist, "android:id/text1", "按名称");
		check(Object_ResIdText, Operation_checkExist, "android:id/text1", "按文件类型");
		check(Object_ResIdText, Operation_checkExist, "android:id/text1", "按时间");
		check(Object_ResIdText, Operation_checkExist, "android:id/text1", "按大小");
	}
	
	/**
	 * 图片界面排列方式按名称排序
	 */
	public static void test_062() 
	{
		//主体
		FileExplorerCommon.classmenu("图片");
		excute(Object_Text, Operation_ClickWait, "排序方式");
		excute(Object_Text, Operation_ClickWait, "按名称");
		excute(Object_Text, Operation_ClickWait, "升序");
		String firname = (String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_name", "0");
		String scename = (String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_name", "1");
		String thrname = (String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_name", "2");
		String bname[] = {firname, scename, thrname};
		Assert.assertTrue(FileExplorerCommon.isSortedByName(bname, false));
		excute(Object_Device, Operation_PressMenu);
		excute(Object_Text, Operation_ClickWait, "排序方式");
		excute(Object_Text, Operation_ClickWait, "按名称");
		excute(Object_Text, Operation_ClickWait, "降序");
		String finame = (String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_name", "0");
		String scname = (String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_name", "1");
		String thname = (String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_name", "2");
		String name[] = {finame, scname, thname};
		Assert.assertTrue(FileExplorerCommon.isSortedByName(name, true));
	}
	/**
	 * 图片界面排列方式按文件类型排序
	 */
	public static void test_063() 
	{
		//主体
		FileExplorerCommon.classmenu("图片");
		excute(Object_Text, Operation_ClickWait, "排序方式");
		excute(Object_Text, Operation_ClickWait, "按文件类型");
		excute(Object_Text, Operation_ClickWait, "升序");
		String firname = (String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_name", "0");
		String scename = (String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_name", "1");
		String thrname = (String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_name", "2");
		String bname[] = {firname, scename, thrname};
		Assert.assertTrue(FileExplorerCommon.isSortedByType(bname));
		excute(Object_Device, Operation_PressMenu);
		excute(Object_Text, Operation_ClickWait, "排序方式");
		excute(Object_Text, Operation_ClickWait, "按文件类型");
		excute(Object_Text, Operation_ClickWait, "降序");
		String Tim = (String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_msg", "0");
		String scTime = (String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_msg", "1");
		String thTime = (String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_msg", "2");
		String ti[] = {Tim, scTime, thTime};
		Assert.assertTrue(FileExplorerCommon.isSortedByType(ti, true));
	}
	/**
	 * 图片界面排列方式按时间排序
	 * @throws ParseException 
	 */
	public static void test_064() throws ParseException 
	{
		//主体
		FileExplorerCommon.classmenu("图片");
		excute(Object_Text, Operation_ClickWait, "排序方式");
		excute(Object_Text, Operation_ClickWait, "按时间");
		excute(Object_Text, Operation_ClickWait, "升序");
		String Time = (String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_msg", "0");
		String sceTime = (String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_msg", "1");
		String thrTime = (String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_msg", "2");
		String tim[] = {Time, sceTime, thrTime};
		Assert.assertTrue(FileExplorerCommon.isSortedByTime(tim));
		excute(Object_Device, Operation_PressMenu);
		excute(Object_Text, Operation_ClickWait, "排序方式");
		excute(Object_Text, Operation_ClickWait, "按时间");
		excute(Object_Text, Operation_ClickWait, "降序");
		String Tim = (String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_msg", "0");
		String scTime = (String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_msg", "1");
		String thTime = (String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_msg", "2");
		String ti[] = {Tim, scTime, thTime};
		Assert.assertTrue(FileExplorerCommon.isSortedByTime(ti, true));
	}
	/**
	 * 图片界面排列方式按大小排序
	 * @throws ParseException 
	 */
	public static void test_065() throws ParseException 
	{
		//主体
		FileExplorerCommon.classmenu("图片");
		excute(Object_Text, Operation_ClickWait, "排序方式");
		excute(Object_Text, Operation_ClickWait, "按大小");
		excute(Object_Text, Operation_ClickWait, "升序");
		String Size = (String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_msg", "0");
		String sceSize = (String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_msg", "1");
		String thrSize = (String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_msg", "2");
		String num[] = {Size, sceSize, thrSize};
		Assert.assertTrue(FileExplorerCommon.isSortedBySize(num));
		excute(Object_Device, Operation_PressMenu);
		excute(Object_Text, Operation_ClickWait, "排序方式");
		excute(Object_Text, Operation_ClickWait, "按大小");
		excute(Object_Text, Operation_ClickWait, "降序");
		String Siz = (String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_msg", "0");
		String scSize = (String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_msg", "1");
		String thSize = (String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_msg", "2");
		String nu[] = {Siz, scSize, thSize};
		Assert.assertTrue(FileExplorerCommon.isSortedBySize(nu, true));
	}
	/**
	 * 浏览图片
	 */
	public static void test_066() 
	{
		//主体
		FileExplorerCommon.Enterclass("图片");
		excute(Object_ResourceId, Operation_ClickWait, "com.sprd.fileexplorer:id/file_item_list_name");
		check(Object_Description, Operation_checkExist, "分享方式");//图片浏览界面分享按钮
	}
	/**
	 * 进入视频
	 */
	public static void test_067() 
	{
		//主体
		FileExplorerCommon.Enterclass("视频");
		check(Object_Text, Operation_checkExist, "视频");
		
	}
	/**
	 * 进入图片长按视频
	 */
	public static void test_068() 
	{
		//主体
		FileExplorerCommon.Enterclass("视频");
		excute(Object_ResourceId, Operation_LongClick, "com.sprd.fileexplorer:id/file_item_list_name");
		check(Object_Text, Operation_checkExist, "复制");
		check(Object_Text, Operation_checkExist, "剪切");
		check(Object_Text, Operation_checkExist, "删除");
		check(Object_Text, Operation_checkExist, "重命名");
		check(Object_Text, Operation_checkExist, "分享");
		check(Object_Text, Operation_checkExist, "属性");
	}
	/**
	 * 长按视频复制
	 */
	public static void test_069() 
	{
		//主体
		FileExplorerCommon.Enterclass("视频");
		FileExplorerCommon.Longclickmenu("复制");
		check(Object_Text, Operation_checkExist, "选择存储位置");
		}
	/**
	 * 长按视频剪切
	 */
	public static void test_070() 
	{
		//主体
		FileExplorerCommon.Enterclass("视频");
		FileExplorerCommon.Longclickmenu("剪切");
		check(Object_Text, Operation_checkExist, "选择存储位置");
	}
	/**
	 * 长按视频删除
	 */
	public static void test_071() 
	{
		//主体
		FileExplorerCommon.Enterclass("视频");
		FileExplorerCommon.Longclickmenu("删除");
		check(Object_Text, Operation_checkExist, "要删除所选内容吗？");
	}
	/**
	 * 长按视频取消删除
	 */
	public static void test_072() 
	{
		//主体
		FileExplorerCommon.Enterclass("视频");
		String Musicname = (String)excute(Object_ResourceId, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_name");
		FileExplorerCommon.Longclickmenu("删除");
		excute(Object_Text, Operation_ClickWait, "取消");
		check(Object_Text, Operation_checkExist, Musicname);
	}
	/**
	 * 长按视频确定删除
	 */
//	public static void test_373() 
//	{
//		//主体
//		FileExplorerCommon.Enterclass("视频");
//		String Picturename = (String)excute(Object_ResourceId, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_name");
//		FileExplorerCommon.Longclickmenu("删除");
//		excute(Object_Text, Operation_ClickWait, "确定");
//		check(Object_Text, Operation_checkNoExist, Picturename);
//	}
	/**
	 * 长按视频重命名查看
	 */
	public static void test_074() 
	{
		//主体
		FileExplorerCommon.Enterclass("视频");
		FileExplorerCommon.Longclickmenu("重命名");
		check(Object_Text, Operation_checkExist, "确定");
	}
	/**
	 * 长按视频重命名
	 */
	public static void test_075() 
	{
		//主体
		FileExplorerCommon.Enterclass("视频");
		String Picture = (String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_name", "0");
		String prefix = Picture.substring(Picture.lastIndexOf(".")+1);
		System.out.println(prefix);	
		System.out.println("Picturename."+prefix);
		FileExplorerCommon.Longclickmenu("重命名");
		excute(Object_ResourceId, Operation_SetText, "com.sprd.fileexplorer:id/name_editor", "Picturename");
		excute(Object_Text, Operation_ClickWait, "确定");
		excute(Object_Text, Operation_WaitForExists, "视频", "10000");
		if(!(Boolean) excute(Object_Text, Operation_Exists, "Picturename."+prefix))
		{
			excute(Object_TextScroll, Operation_Exists, "Picturename."+prefix, "vertical");
			}
		check(Object_Text, Operation_checkExist, "Picturename."+prefix);
		//清场
		excute(Object_Text, Operation_LongClick, "Picturename."+prefix);
		excute(Object_Text, Operation_ClickWait, "重命名");
		excute(Object_ResourceId, Operation_SetText, "com.sprd.fileexplorer:id/name_editor", Picture);
		excute(Object_Text, Operation_ClickWait, "确定");
	}
	/**
	 * 长按视频分享
	 */
	public static void test_076() 
	{
		//主体
		FileExplorerCommon.Enterclass("视频");
		FileExplorerCommon.Longclickmenu("分享");
		check(Object_Text, Operation_checkExist, "信息");
		check(Object_Text, Operation_checkExist, "电子邮件");
		check(Object_Text, Operation_checkExist, "蓝牙");
	}
	/**
	 * 长按视频属性
	 */
	public static void test_077() 
	{
		//主体
		FileExplorerCommon.Enterclass("视频");
		FileExplorerCommon.Longclickmenu("属性");
		check(Object_ResourceId, Operation_checkExist, "android:id/content");//弹框ID
	}
	/**
	 * 视频界面菜单键
	 */
	public static void test_078() 
	{
		//主体
		FileExplorerCommon.classmenu("视频");
		check(Object_Text, Operation_checkExist, "选择多个");
		check(Object_Text, Operation_checkExist, "排序方式");
	}
	/**
	 * 视频界面选择多个
	 */
	public static void test_079() 
	{
		//主体
		FileExplorerCommon.select("视频");
		check(Object_Text, Operation_checkExist, "选择全部");
	}
	/**
	 * 视频界面选择多个任意选择
	 */
	public static void test_080() 
	{
		//主体
		FileExplorerCommon.select("视频");
		check(Object_ResIdInstance, Operation_CheckedTrue, "com.sprd.fileexplorer:id/select_checkbox", "0");
	}
	/**
	 * 视频界面选择多个点击删除按钮
	 */
	public static void test_081() 
	{
		//主体
		FileExplorerCommon.select("视频");
		excute(Object_ResourceId, Operation_ClickWait, "com.sprd.fileexplorer:id/menu_delete");
		check(Object_Text, Operation_checkExist, "要删除所选内容吗？");
	}
	/**
	 * 视频界面选择多个点击菜单
	 */
	public static void test_082() 
	{
		//主体
		FileExplorerCommon.select("视频");
		excute(Object_Device, Operation_PressMenu);
		check(Object_Text, Operation_checkExist, "复制");
		check(Object_Text, Operation_checkExist, "剪切");
		check(Object_Text, Operation_checkExist, "分享");
	}
	/**
	 * 视频界面选择多个点击菜单复制
	 */
	public static void test_083() 
	{
		//主体
		FileExplorerCommon.select("视频");
		FileExplorerCommon.menu("复制");
		check(Object_Text, Operation_checkExist, "选择存储位置");
	}
	/**
	 * 视频界面选择多个点击菜单剪切
	 */
	public static void test_084() 
	{
		//主体
		FileExplorerCommon.select("视频");
		FileExplorerCommon.menu("剪切");
		check(Object_Text, Operation_checkExist, "选择存储位置");
	}
	/**
	 * 视频界面选择多个点击菜单剪切
	 */
	public static void test_085() 
	{
		//主体
		FileExplorerCommon.select("视频");
		FileExplorerCommon.menu("分享");
		check(Object_Text, Operation_checkExist, "信息");
		check(Object_Text, Operation_checkExist, "电子邮件");
		check(Object_Text, Operation_checkExist, "蓝牙");
	}
	/**
	 * 视频界面选择多个点击选择全部
	 */
	public static void test_086() 
	{
		//主体
		FileExplorerCommon.select("视频");
		excute(Object_ResourceId, Operation_ClickWait, "com.sprd.fileexplorer:id/select_all_cb");
		int Num = ((Integer)(excute(Object_ResourceId, Operation_GetChildCount, "com.sprd.fileexplorer:id/list"))).intValue();
		 for(int i=0; i<Num; i++)
		 {
			 check(Object_ResIdInstance, Operation_CheckedTrue, "com.sprd.fileexplorer:id/select_checkbox", String.valueOf(i));
		 }
	}
	/**
	 * 视频界面选择多个选择全部变成取消全部
	 */
	public static void test_087() 
	{
		//主体
		FileExplorerCommon.select("视频");
		excute(Object_ResourceId, Operation_ClickWait, "com.sprd.fileexplorer:id/select_all_cb");
		check(Object_Text, Operation_checkExist, "取消全部");
	}
	/**
	 * 视频界面选择多个点击取消全部
	 */
	public static void test_088() 
	{
		//主体
		FileExplorerCommon.select("视频");
		excute(Object_ResourceId, Operation_ClickWait, "com.sprd.fileexplorer:id/select_all_cb");
		excute(Object_ResourceId, Operation_ClickWait, "com.sprd.fileexplorer:id/select_all_cb");
		int Num = ((Integer)(excute(Object_ResourceId, Operation_GetChildCount, "com.sprd.fileexplorer:id/list"))).intValue();
		 for(int i=0; i<Num; i++)
		 {
			 check(Object_ResIdInstance, Operation_CheckedFalse, "com.sprd.fileexplorer:id/select_checkbox", String.valueOf(i));
		 }
		check(Object_ResourceId, Operation_EnabledFalse, "com.sprd.fileexplorer:id/menu_delete");
	}
	/**
	 * 视频界面排列方式
	 */
	public static void test_089() 
	{
		//主体
		FileExplorerCommon.classmenu("视频");
		excute(Object_Text, Operation_ClickWait, "排序方式");
		check(Object_ResIdText, Operation_checkExist, "android:id/alertTitle", "排序方式");
		check(Object_ResIdText, Operation_checkExist, "android:id/text1", "按名称");
		check(Object_ResIdText, Operation_checkExist, "android:id/text1", "按文件类型");
		check(Object_ResIdText, Operation_checkExist, "android:id/text1", "按时间");
		check(Object_ResIdText, Operation_checkExist, "android:id/text1", "按大小");
	}
	/**
	 * 视频界面排列方式按名称排序
	 */
	public static void test_090() 
	{
		//主体
		FileExplorerCommon.classmenu("视频");
		excute(Object_Text, Operation_ClickWait, "排序方式");
		excute(Object_Text, Operation_ClickWait, "按名称");
		excute(Object_Text, Operation_ClickWait, "升序");
		String firname = (String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_name", "0");
		String scename = (String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_name", "1");
		String thrname = (String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_name", "2");
		String bname[] = {firname, scename, thrname};
		Assert.assertTrue(FileExplorerCommon.isSortedByName(bname, false));
		excute(Object_Device, Operation_PressMenu);
		excute(Object_Text, Operation_ClickWait, "排序方式");
		excute(Object_Text, Operation_ClickWait, "按名称");
		excute(Object_Text, Operation_ClickWait, "降序");
		String finame = (String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_name", "0");
		String scname = (String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_name", "1");
		String thname = (String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_name", "2");
		String name[] = {finame, scname, thname};
		Assert.assertTrue(FileExplorerCommon.isSortedByName(name, true));

	}
	/**
	 * 视频界面排列方式按文件类型排序
	 */
	public static void test_091() 
	{
		//主体
		FileExplorerCommon.classmenu("视频");
		excute(Object_Text, Operation_ClickWait, "排序方式");
		excute(Object_Text, Operation_ClickWait, "按文件类型");
		excute(Object_Text, Operation_ClickWait, "升序");
		String firname = (String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_name", "0");
		String scename = (String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_name", "1");
		String thrname = (String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_name", "2");
		String bname[] = {firname, scename, thrname};
		String name[] = {firname, scename, thrname};
		Assert.assertTrue(FileExplorerCommon.isSortedByType(bname));
		excute(Object_Device, Operation_PressMenu);
		excute(Object_Text, Operation_ClickWait, "排序方式");
		excute(Object_Text, Operation_ClickWait, "按文件类型");
		excute(Object_Text, Operation_ClickWait, "降序");
		String Tim = (String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_msg", "0");
		String scTime = (String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_msg", "1");
		String thTime = (String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_msg", "2");
		String ti[] = {Tim, scTime, thTime};
		Assert.assertTrue(FileExplorerCommon.isSortedByType(ti, true));
	}
	/**
	 * 视频界面排列方式按时间排序
	 * @throws ParseException 
	 */
	public static void test_092() throws ParseException 
	{
		//主体
		FileExplorerCommon.classmenu("视频");
		excute(Object_Text, Operation_ClickWait, "排序方式");
		excute(Object_Text, Operation_ClickWait, "按时间");
		excute(Object_Text, Operation_ClickWait, "升序");
		String Time = (String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_msg", "0");
		String sceTime = (String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_msg", "1");
		String thrTime = (String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_msg", "2");
		String tim[] = {Time, sceTime, thrTime};
		Assert.assertTrue(FileExplorerCommon.isSortedByTime(tim));
		excute(Object_Device, Operation_PressMenu);
		excute(Object_Text, Operation_ClickWait, "排序方式");
		excute(Object_Text, Operation_ClickWait, "按时间");
		excute(Object_Text, Operation_ClickWait, "降序");
		String Tim = (String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_msg", "0");
		String scTime = (String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_msg", "1");
		String thTime = (String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_msg", "2");
		String ti[] = {Tim, scTime, thTime};
		Assert.assertTrue(FileExplorerCommon.isSortedByTime(ti, true));
	}
	/**
	 * 视频界面排列方式按大小排序
	 * @throws ParseException 
	 */
	public static void test_093() throws ParseException 
	{
		//主体
		FileExplorerCommon.classmenu("视频");
		excute(Object_Text, Operation_ClickWait, "排序方式");
		excute(Object_Text, Operation_ClickWait, "按大小");
		excute(Object_Text, Operation_ClickWait, "升序");
		String Size = (String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_msg", "0");
		String sceSize = (String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_msg", "1");
		String thrSize = (String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_msg", "2");
		String num[] = {Size, sceSize, thrSize};
		Assert.assertTrue(FileExplorerCommon.isSortedBySize(num));
		excute(Object_Device, Operation_PressMenu);
		excute(Object_Text, Operation_ClickWait, "排序方式");
		excute(Object_Text, Operation_ClickWait, "按大小");
		excute(Object_Text, Operation_ClickWait, "降序");
		String Siz = (String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_msg", "0");
		String scSize = (String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_msg", "1");
		String thSize = (String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_msg", "2");
		String nu[] = {Siz, scSize, thSize};
		Assert.assertTrue(FileExplorerCommon.isSortedBySize(nu, true));
	}
	/**
	 * 浏览视频
	 */
	public static void test_094() 
	{
		//主体
		FileExplorerCommon.Enterclass("视频");
		excute(Object_ResourceId, Operation_ClickWait, "com.sprd.fileexplorer:id/file_item_list_name");
		check(Object_Description, Operation_checkExist, "分享方式");//图片浏览界面分享按钮
	}

	/**
	 *快速查看-文档
	 */
	public static void test_095()
	{
		//主体
		FileExplorerCommon.Enterclass("文档");
		check(Object_Text,Operation_checkExist,"文档");
		
	}
	
	/**
	 *快速查看-文档-长按一个文档资源，上面有功能项：复制、剪切、删除、重命名、分享
	 */
	public static void test_096()
	{
		//主体
		FileExplorerCommon.Enterclass("文档");
		excute(Object_ResourceId,Operation_LongClick,"com.sprd.fileexplorer:id/file_item_list_name");
		check(Object_Text,Operation_checkExist,"复制");
		check(Object_Text,Operation_checkExist,"剪切");
		check(Object_Text,Operation_checkExist,"删除");
		check(Object_Text,Operation_checkExist,"重命名");
		check(Object_Text,Operation_checkExist,"分享");
		check(Object_Text,Operation_checkExist,"属性");
	}
	
	/**
	 *快速查看-文档-长按一个文档资源-复制
	 */
	public static void test_097()
	{
		//主体
		FileExplorerCommon.Enterclass("文档");
		FileExplorerCommon.Longclickmenu("复制");
		check(Object_Text,Operation_checkExist,"选择存储位置");
	}
	
	/**
	 *快速查看-文档-长按一个文档资源-剪切
	 */
	public static void test_098()
	{
		//主体
		FileExplorerCommon.Enterclass("文档");
		FileExplorerCommon.Longclickmenu("剪切");
		check(Object_Text,Operation_checkExist,"选择存储位置");
	}
	
	/**
	 *快速查看-文档-长按一个文档资源-删除
	 */
	public static void test_099()
	{
		//主体
		FileExplorerCommon.Enterclass("文档");
		FileExplorerCommon.Longclickmenu("删除");
		check(Object_Text,Operation_checkExist,"要删除所选内容吗？");
	}
	
	/**
	 *快速查看-文档-长按一个文档资源-取消删除
	 */
	public static void test_100()
	{
		//主体
		FileExplorerCommon.Enterclass("文档");
		String txt = (String) excute(Object_ResourceId,Operation_GetText,"com.sprd.fileexplorer:id/file_item_list_name");
		FileExplorerCommon.Longclickmenu("删除");
		excute(Object_Text,Operation_ClickWait,"取消");
		check(Object_Text,Operation_checkExist,txt);
	}
	
	/**
	 *快速查看-文档-长按一个文档资源-删除一个文档
	 */
	public static void test_3101()
	{
		//主体
		FileExplorerCommon.Enterclass("文档");
		String txt = (String) excute(Object_ResourceId,Operation_GetText,"com.sprd.fileexplorer:id/file_item_list_name");
		FileExplorerCommon.Longclickmenu("删除");
		excute(Object_Text,Operation_ClickWait,"确定");
		check(Object_Text,Operation_checkNoExist,txt);
	}
	
	/**
	 *快速查看-文档-长按一个文档资源-重命名
	 */
	public static void test_102()
	{
		//主体
		FileExplorerCommon.Enterclass("文档");
		FileExplorerCommon.Longclickmenu("重命名");
		check(Object_Text,Operation_checkExist,"重命名");
	}
	
	/**
	 *快速查看-文档-长按一个文档资源-重命名
	 */
	public static void test_103()
	{
		//主体
		FileExplorerCommon.Enterclass("文档");
		if((Boolean)excute(Object_Text,Operation_Exists,"0atest.txt"))
		{
			excute(Object_Text,Operation_LongClick,"0atest.txt");
			excute(Object_ResourceId,Operation_SetText,"com.sprd.fileexplorer:id/name_editor","test1");
			
		}
		else
		{
			excute(Object_TextScroll,Operation_LongClick,"0atest.txt","vertical");
			excute(Object_Text,Operation_ClickWait,"重命名");
			excute(Object_ResourceId,Operation_SetText,"com.sprd.fileexplorer:id/name_editor","test1");
		} 
		excute(Object_Text,Operation_ClickWait,"确定");
		if((Boolean)excute(Object_Text,Operation_Exists,"test1.txt"))
		{
			check(Object_Text,Operation_checkExist,"test1.txt");
		}
		else
		{
			check(Object_TextScroll,Operation_checkExist,"test1.txt","vertical");
		}
	}
	
	/**
	 *快速查看-文档-长按一个文档资源-分享-页面上有“电子邮件”“信息”“蓝牙”三种分享方式
	 */
	public static void test_104()
	{
		//主体
		FileExplorerCommon.Enterclass("文档");
		FileExplorerCommon.Longclickmenu("分享");
		check(Object_Text,Operation_checkExist,"电子邮件");
		check(Object_Text,Operation_checkExist,"蓝牙");
	}
	
	/**
	 *快速查看-文档-长按一个文档资源-属性-对话框上有text：类型、位置、日期、大小、是否可读、是否可写、是否隐藏 详细信息
	 */
	public static void test_105()
	{
		//主体
		FileExplorerCommon.Enterclass("文档");
		FileExplorerCommon.Longclickmenu("属性");
		check(Object_ClassInstance,Operation_TextContainsTrue,"android.widget.TextView","1","类型");
		check(Object_ClassInstance,Operation_TextContainsTrue,"android.widget.TextView","1","位置");
		check(Object_ClassInstance,Operation_TextContainsTrue,"android.widget.TextView","1","日期");
		check(Object_ClassInstance,Operation_TextContainsTrue,"android.widget.TextView","1","大小");
		check(Object_ClassInstance,Operation_TextContainsTrue,"android.widget.TextView","1","是否可写");
		check(Object_ClassInstance,Operation_TextContainsTrue,"android.widget.TextView","1","是否可读");
		check(Object_ClassInstance,Operation_TextContainsTrue,"android.widget.TextView","1","是否隐藏");
	}
	
	/**
	 *快速查看-文档-菜单-选择多个、排序方式
	 */
	public static void test_106()
	{
		//主体
		FileExplorerCommon.Enterclass("文档");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		check(Object_Text,Operation_checkExist,"选择多个");
		check(Object_Text,Operation_checkExist,"排序方式");
	}
	
	/**
	 *快速查看-文档-菜单-选择多个
	 */
	public static void test_107()
	{
		//主体
		FileExplorerCommon.Enterclass("文档");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"选择多个");
		check(Object_Text,Operation_checkExist,"选择全部");
	}
	
	/**
	 *快速查看-文档-菜单-选择多个-选中1个文档
	 */
	public static void test_108()
	{
		//主体
		FileExplorerCommon.select("文档");
		check(Object_ResIdInstance,Operation_CheckedTrue,"com.sprd.fileexplorer:id/select_checkbox","0");
	}
	
	/**
	 *快速查看-文档-菜单-选择多个-选中1个文档
	 */
	public static void test_109()
	{
		//主体
		FileExplorerCommon.select("文档");
		excute(Object_ResourceId,Operation_ClickWait,"com.sprd.fileexplorer:id/menu_delete");
		check(Object_Text,Operation_checkExist,"要删除所选内容吗？");
	}
	
	/**
	 *快速查看-文档-菜单-选择多个-选中1个文档-菜单-“复制”“剪切”“分享”
	 */
	public static void test_110()
	{
		//主体
		FileExplorerCommon.select("文档");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		check(Object_Text,Operation_checkExist,"复制");
		check(Object_Text,Operation_checkExist,"剪切");
		check(Object_Text,Operation_checkExist,"分享");
	}
	
	/**
	 *快速查看-文档-菜单-选择多个-选中1个文档-菜单-“复制”
	 */
	public static void test_111()
	{
		//主体
		FileExplorerCommon.select("文档");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"复制");
		check(Object_Text,Operation_checkExist,"选择存储位置");
	}
	
	/**
	 *快速查看-文档-菜单-选择多个-选中1个文档-菜单-“剪切”
	 */
	public static void test_112()
	{
		//主体
		FileExplorerCommon.select("文档");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"剪切");
		check(Object_Text,Operation_checkExist,"选择存储位置");
	}
	
	/**
	 *快速查看-文档-菜单-选择多个-选中1个文档-菜单-“分享”
	 */
	public static void test_113()
	{
		//主体
		FileExplorerCommon.select("文档");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"分享");
		check(Object_Text,Operation_checkExist,"电子邮件");
		check(Object_Text,Operation_checkExist,"蓝牙");
	}
	
	/**
	 *快速查看-文档-菜单-选择多个-选择全部
	 */
	public static void test_114()
	{
		//主体
		FileExplorerCommon.Enterclass("文档");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"选择多个");
		excute(Object_ResourceId,Operation_ClickWait,"com.sprd.fileexplorer:id/select_all_cb");
		check(Object_ResourceId,Operation_CheckedTrue,"com.sprd.fileexplorer:id/select_all_cb");
	}
	
	/**
	 *快速查看-文档-菜单-选择多个-选择全部
	 */
	public static void test_115()
	{
		//主体
		FileExplorerCommon.Enterclass("文档");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"选择多个");
		excute(Object_ResourceId,Operation_ClickWait,"com.sprd.fileexplorer:id/select_all_cb");
		check(Object_ResourceId,Operation_CheckedTrue,"com.sprd.fileexplorer:id/select_all_cb");
		check(Object_Text,Operation_checkExist,"取消全部");
	}
	
	/**
	 *快速查看-文档-菜单-选择多个-取消全部，删除图标置灰
	 */
	public static void test_116()
	{
		//主体
		FileExplorerCommon.Enterclass("文档");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"选择多个");
		excute(Object_ResourceId,Operation_ClickWait,"com.sprd.fileexplorer:id/select_all_cb");
		excute(Object_ResourceId,Operation_ClickWait,"com.sprd.fileexplorer:id/select_all_cb");
		check(Object_ResourceId,Operation_CheckedFalse,"com.sprd.fileexplorer:id/menu_delete");
	}
	
	/**
	 *快速查看-文档-菜单-排序方式-对话框上有“按名称”“按文件类型”“按时间”“按大小”四种排序方式
	 */
	public static void test_117()
	{
		//主体
		FileExplorerCommon.Enterclass("文档");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"排序方式");
		check(Object_Text,Operation_checkExist,"按名称");
		check(Object_Text,Operation_checkExist,"按文件类型");
		check(Object_Text,Operation_checkExist,"按时间");
		check(Object_Text,Operation_checkExist,"按大小");
	}
	
	/**
	 *快速查看-文档-菜单-排序方式-对话框上有“按名称”排序方式
	 */
	public static void test_118()
	{
		//主体
		FileExplorerCommon.Enterclass("文档");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"排序方式");
		excute(Object_Text,Operation_ClickWait,"按名称");
		excute(Object_Text,Operation_ClickWait,"升序");
		String txt = (String) excute(Object_ResIdInstance,Operation_GetText,"com.sprd.fileexplorer:id/file_item_list_name","0");
		String txt1 = (String) excute(Object_ResIdInstance,Operation_GetText,"com.sprd.fileexplorer:id/file_item_list_name","1");
		String txt2 = (String) excute(Object_ResIdInstance,Operation_GetText,"com.sprd.fileexplorer:id/file_item_list_name","2");
		String[] Array = new String[]{txt,txt1,txt2};
		boolean result = FileExplorerCommon.isSortedByName(Array);
		Assert.assertEquals(result,true);
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"排序方式");
		excute(Object_Text,Operation_ClickWait,"按名称");
		excute(Object_Text,Operation_ClickWait,"降序");
		String text = (String) excute(Object_ResIdInstance,Operation_GetText,"com.sprd.fileexplorer:id/file_item_list_name","0");
		String text1 = (String) excute(Object_ResIdInstance,Operation_GetText,"com.sprd.fileexplorer:id/file_item_list_name","1");
		String text2 = (String) excute(Object_ResIdInstance,Operation_GetText,"com.sprd.fileexplorer:id/file_item_list_name","2");
		String[] Array1 = new String[]{text,text1,text2};
		boolean result1 = FileExplorerCommon.isSortedByName(Array1,true);
		Assert.assertEquals(result1,true);
	}
	
	/**
	 *快速查看-文档-菜单-排序方式-对话框上有“按文件类型”排序方式
	 */
	public static void test_119()
	{
		//主体
		FileExplorerCommon.Enterclass("文档");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"排序方式");
		excute(Object_Text,Operation_ClickWait,"按文件类型");
		excute(Object_Text,Operation_ClickWait,"升序");
		String txt = (String) excute(Object_ResIdInstance,Operation_GetText,"com.sprd.fileexplorer:id/file_item_list_name","0");
		String txt1 = (String) excute(Object_ResIdInstance,Operation_GetText,"com.sprd.fileexplorer:id/file_item_list_name","1");
		String txt2 = (String) excute(Object_ResIdInstance,Operation_GetText,"com.sprd.fileexplorer:id/file_item_list_name","2");
		String[] Array = new String[]{txt,txt1,txt2};
		boolean result = FileExplorerCommon.isSortedByType(Array);
		Assert.assertEquals(result,true);
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"排序方式");
		excute(Object_Text,Operation_ClickWait,"按文件类型");
		excute(Object_Text,Operation_ClickWait,"降序");
		String text = (String) excute(Object_ResIdInstance,Operation_GetText,"com.sprd.fileexplorer:id/file_item_list_name","0");
		String text1 = (String) excute(Object_ResIdInstance,Operation_GetText,"com.sprd.fileexplorer:id/file_item_list_name","1");
		String text2 = (String) excute(Object_ResIdInstance,Operation_GetText,"com.sprd.fileexplorer:id/file_item_list_name","2");
		String[] Array1 = new String[]{text,text1,text2};
		boolean result1 = FileExplorerCommon.isSortedByType(Array1,true);
		Assert.assertEquals(result1,true);
	}
	
	/**
	 *快速查看-文档-菜单-排序方式-对话框上有“按时间”排序方式
	 * @throws ParseException 
	 */
	public static void test_120() throws ParseException
	{
		//主体
		FileExplorerCommon.Enterclass("文档");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"排序方式");
		excute(Object_Text,Operation_ClickWait,"按时间");
		excute(Object_Text,Operation_ClickWait,"升序");
		String txt = (String) excute(Object_ResIdInstance,Operation_GetText,"com.sprd.fileexplorer:id/file_item_list_msg","0");
		String txt1 = (String) excute(Object_ResIdInstance,Operation_GetText,"com.sprd.fileexplorer:id/file_item_list_msg","1");
		String txt2 = (String) excute(Object_ResIdInstance,Operation_GetText,"com.sprd.fileexplorer:id/file_item_list_msg","2");
		String[] Array = new String[]{txt,txt1,txt2};
		boolean result = FileExplorerCommon.isSortedByTime(Array);
		Assert.assertEquals(result,true);
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"排序方式");
		excute(Object_Text,Operation_ClickWait,"按时间");
		excute(Object_Text,Operation_ClickWait,"降序");
		String text = (String) excute(Object_ResIdInstance,Operation_GetText,"com.sprd.fileexplorer:id/file_item_list_msg","0");
		String text1 = (String) excute(Object_ResIdInstance,Operation_GetText,"com.sprd.fileexplorer:id/file_item_list_msg","1");
		String text2 = (String) excute(Object_ResIdInstance,Operation_GetText,"com.sprd.fileexplorer:id/file_item_list_msg","2");
		String[] Array1 = new String[]{text,text1,text2};
		boolean result1 = FileExplorerCommon.isSortedByTime(Array1,true);
		Assert.assertEquals(result1,true);
	}
	
	/**
	 *快速查看-文档-菜单-排序方式-对话框上有“按大小”排序方式
	 * @throws ParseException 
	 */
	public static void test_121() throws ParseException
	{
		//主体
		FileExplorerCommon.Enterclass("文档");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"排序方式");
		excute(Object_Text,Operation_ClickWait,"按大小");
		excute(Object_Text,Operation_ClickWait,"升序");
		String txt = (String) excute(Object_ResIdInstance,Operation_GetText,"com.sprd.fileexplorer:id/file_item_list_msg","0");
		String txt1 = (String) excute(Object_ResIdInstance,Operation_GetText,"com.sprd.fileexplorer:id/file_item_list_msg","1");
		String txt2 = (String) excute(Object_ResIdInstance,Operation_GetText,"com.sprd.fileexplorer:id/file_item_list_msg","2");
		String[] Array = new String[]{txt,txt1,txt2};
		FileExplorerCommon.isSortedBySize(Array);
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"排序方式");
		excute(Object_Text,Operation_ClickWait,"按大小");
		excute(Object_Text,Operation_ClickWait,"降序");
		String text = (String) excute(Object_ResIdInstance,Operation_GetText,"com.sprd.fileexplorer:id/file_item_list_msg","0");
		String text1 = (String) excute(Object_ResIdInstance,Operation_GetText,"com.sprd.fileexplorer:id/file_item_list_msg","1");
		String text2 = (String) excute(Object_ResIdInstance,Operation_GetText,"com.sprd.fileexplorer:id/file_item_list_msg","2");
		String[] Array1 = new String[]{text,text1,text2};
		FileExplorerCommon.isSortedBySize(Array1,true);
		
	}
	
	/**
	 *快速查看-文档-选择一个文档预览
	 * @throws ParseException 
	 */
	public static void test_122() throws ParseException
	{
		//主体
		FileExplorerCommon.Enterclass("文档");
		String txt = (String)excute(Object_ResIdInstance,Operation_GetText,"com.sprd.fileexplorer:id/file_item_list_name","0");
		excute(Object_ResIdInstance,Operation_ClickWait,"com.sprd.fileexplorer:id/file_item_list_name","0");
		check(Object_Text,Operation_WaitForExists,txt,"10000");
	}
	
	/**
	 *快速查看-APK安装文件
	 */
	public static void test_123()
	{
		//主体
		FileExplorerCommon.Enterclass("APK安装文件");
		check(Object_Text,Operation_checkExist,"APK安装文件");
	}
	
	/**
	 *快速查看-APK安装文件 上面有功能项：复制、剪切、删除、重命名、分享、属性可以通过页面上的text进行判断
	 */
	public static void test_124()
	{
		//主体
		FileExplorerCommon.Enterclass("APK安装文件");
		excute(Object_ResIdInstance,Operation_LongClick,"com.sprd.fileexplorer:id/file_item_list_name","0");
		check(Object_Text,Operation_checkExist,"复制");
		check(Object_Text,Operation_checkExist,"剪切");
		check(Object_Text,Operation_checkExist,"删除");
		check(Object_Text,Operation_checkExist,"重命名");
		check(Object_Text,Operation_checkExist,"分享");
		check(Object_Text,Operation_checkExist,"属性");
	}
	
	/**
	 *快速查看-APK安装文件- 长按一个APK ，复制
	 */
	public static void test_125()
	{
		//主体
		FileExplorerCommon.Enterclass("APK安装文件");
		FileExplorerCommon.Longclickmenu("复制");
		check(Object_Text,Operation_checkExist,"选择存储位置");
	}
	
	/**
	 *快速查看-APK安装文件- 长按一个APK ，剪切
	 */
	public static void test_126()
	{
		//主体
		FileExplorerCommon.Enterclass("APK安装文件");
		FileExplorerCommon.Longclickmenu("剪切");
		check(Object_Text,Operation_checkExist,"选择存储位置");
	}
	
	/**
	 *快速查看-APK安装文件- 长按一个APK ，删除
	 */
	public static void test_127()
	{
		//主体
		FileExplorerCommon.Enterclass("APK安装文件");
		FileExplorerCommon.Longclickmenu("删除");
		check(Object_Text,Operation_checkExist,"要删除所选内容吗？");
	}
	
	/**
	 *快速查看-APK安装文件- 长按一个APK -取消删除
	 */
	public static void test_128()
	{
		//主体
		FileExplorerCommon.Enterclass("APK安装文件");
		String txt = (String) excute(Object_ResourceId,Operation_GetText,"com.sprd.fileexplorer:id/file_item_list_name");
		FileExplorerCommon.Longclickmenu("删除");
		excute(Object_Text,Operation_ClickWait,"取消");
		check(Object_Text,Operation_checkExist,txt);
	}
	
	/**
	 *快速查看-APK安装文件- 长按一个APK -删除一个APK
	 */
	public static void test_3129()
	{
		//主体
		FileExplorerCommon.Enterclass("APK安装文件");
		String txt = (String) excute(Object_ResourceId,Operation_GetText,"com.sprd.fileexplorer:id/file_item_list_name");
		FileExplorerCommon.Longclickmenu("删除");
		excute(Object_Text,Operation_ClickWait,"确定");
		check(Object_Text,Operation_checkNoExist,txt);
	}
	
	/**
	 *快速查看-APK安装文件- 长按一个APK -重命名
	 */
	public static void test_130()
	{
		//主体
		FileExplorerCommon.Enterclass("APK安装文件");
		FileExplorerCommon.Longclickmenu("重命名");
		check(Object_Text,Operation_checkExist,"重命名");
	}

	/**
	 *快速查看-APK安装文件- 长按一个APK-重命名
	 */
	public static void test_131()
	{
		//主体
		FileExplorerCommon.Enterclass("APK安装文件");
		FileExplorerCommon.Longclickmenu("重命名");
		excute(Object_ResourceId,Operation_SetText,"com.sprd.fileexplorer:id/name_editor","APKtest");
		excute(Object_Text,Operation_ClickWait,"确定");
		if((Boolean)excute(Object_Text,Operation_Exists,"APKtest.apk"))
		{
			check(Object_Text,Operation_checkExist,"APKtest.apk");
		}
		else
		{
			check(Object_TextScroll,Operation_checkExist,"APKtest.apk","vertical");
		}
	}
	
	/**
	 *快速查看-APK安装文件- 长按一个APK-分享-页面上有“电子邮件”“蓝牙”三种分享方式
	 */
	public static void test_132()
	{
		//主体
		FileExplorerCommon.Enterclass("APK安装文件");
		FileExplorerCommon.Longclickmenu("分享");
		check(Object_Text,Operation_checkExist,"电子邮件");
		check(Object_Text,Operation_checkExist,"蓝牙");
	}
	
	/**
	 * 快速查看-APK安装文件-长按一个APK-属性
	 */
	public static void test_133()
	{
		//主体
		FileExplorerCommon.Enterclass("APK安装文件");
		FileExplorerCommon.Longclickmenu("属性");
		check(Object_ClassInstance,Operation_TextContainsTrue,"android.widget.TextView","1","类型");
		check(Object_ClassInstance,Operation_TextContainsTrue,"android.widget.TextView","1","位置");
		check(Object_ClassInstance,Operation_TextContainsTrue,"android.widget.TextView","1","日期");
		check(Object_ClassInstance,Operation_TextContainsTrue,"android.widget.TextView","1","大小");
		check(Object_ClassInstance,Operation_TextContainsTrue,"android.widget.TextView","1","是否可写");
		check(Object_ClassInstance,Operation_TextContainsTrue,"android.widget.TextView","1","是否可读");
		check(Object_ClassInstance,Operation_TextContainsTrue,"android.widget.TextView","1","是否隐藏");
	}
	
	/**
	 *快速查看-APK安装文件- 菜单-选择多个、排序方式
	 */
	public static void test_134()
	{
		//主体
		FileExplorerCommon.Enterclass("APK安装文件");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		check(Object_Text,Operation_checkExist,"选择多个");
		check(Object_Text,Operation_checkExist,"排序方式");
	}
	
	/**
	 *快速查看-APK安装文件- 菜单-选择多个
	 */
	public static void test_135()
	{
		//主体
		FileExplorerCommon.Enterclass("APK安装文件");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"选择多个");
		check(Object_Text,Operation_checkExist,"选择全部");
	}
	
	/**
	 *快速查看-APK安装文件-菜单-选择多个-选中1个APK
	 */
	public static void test_136()
	{
		//主体
		FileExplorerCommon.select("APK安装文件");
		check(Object_ResIdInstance,Operation_CheckedTrue,"com.sprd.fileexplorer:id/select_checkbox","0");
	}
	
	/**
	 *快速查看-APK安装文件-菜单-选择多个-选中1个APK-点击删除
	 */
	public static void test_137()
	{
		//主体
		FileExplorerCommon.select("APK安装文件");
		excute(Object_ResourceId,Operation_ClickWait,"com.sprd.fileexplorer:id/menu_delete");
		check(Object_Text,Operation_checkExist,"要删除所选内容吗？");
	}
	
	/**
	 *快速查看-APK安装文件-菜单-选择多个-选中1个文档-菜单-“复制”“剪切”“分享”
	 */
	public static void test_138()
	{
		//主体
		FileExplorerCommon.select("APK安装文件");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		check(Object_Text,Operation_checkExist,"复制");
		check(Object_Text,Operation_checkExist,"剪切");
		check(Object_Text,Operation_checkExist,"分享");
	}
	
	/**
	 *快速查看-APK安装文件-菜单-选择多个-选中1个文档-菜单-“复制”
	 */
	public static void test_139()
	{
		//主体
		FileExplorerCommon.select("APK安装文件");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"复制");
		check(Object_Text,Operation_checkExist,"选择存储位置");
	}
	
	/**
	 *快速查看-APK安装文件-菜单-选择多个-选中1个文档-菜单-“剪切”
	 */
	public static void test_140()
	{
		//主体
		FileExplorerCommon.select("APK安装文件");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"剪切");
		check(Object_Text,Operation_checkExist,"选择存储位置");
	}
	
	/**
	 *快速查看-APK安装文件-菜单-选择多个-选中1个文档-菜单-“分享”
	 */
	public static void test_141()
	{
		//主体
		FileExplorerCommon.select("APK安装文件");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"分享");
		check(Object_Text,Operation_checkExist,"电子邮件");
		check(Object_Text,Operation_checkExist,"蓝牙");
	}
	
	/**
	 *快速查看-APK安装文件-菜单-选择多个-选择全部
	 */
	public static void test_142()
	{
		//主体
		FileExplorerCommon.Enterclass("APK安装文件");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"选择多个");
		excute(Object_ResourceId,Operation_ClickWait,"com.sprd.fileexplorer:id/select_all_cb");
		check(Object_ResourceId,Operation_CheckedTrue,"com.sprd.fileexplorer:id/select_all_cb");
	}
	
	/**
	 *快速查看-APK安装文件-菜单-选择多个-选择全部
	 */
	public static void test_143()
	{
		//主体
		FileExplorerCommon.Enterclass("APK安装文件");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"选择多个");
		excute(Object_ResourceId,Operation_ClickWait,"com.sprd.fileexplorer:id/select_all_cb");
		check(Object_ResourceId,Operation_CheckedTrue,"com.sprd.fileexplorer:id/select_all_cb");
		check(Object_Text,Operation_checkExist,"取消全部");
	}
	
	/**
	 *快速查看-APK安装文件-菜单-选择多个-取消全部，删除图标置灰
	 */
	public static void test_144()
	{
		//主体
		FileExplorerCommon.Enterclass("APK安装文件");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"选择多个");
		excute(Object_ResourceId,Operation_ClickWait,"com.sprd.fileexplorer:id/select_all_cb");
		excute(Object_ResourceId,Operation_ClickWait,"com.sprd.fileexplorer:id/select_all_cb");
		check(Object_ResourceId,Operation_CheckedFalse,"com.sprd.fileexplorer:id/menu_delete");
	}
	
	/**
	 *快速查看-APK安装文件-菜单-排序方式-对话框上有“按名称”“按文件类型”“按时间”“按大小”四种排序方式
	 */
	public static void test_145()
	{
		//主体
		FileExplorerCommon.Enterclass("APK安装文件");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"排序方式");
		check(Object_Text,Operation_checkExist,"按名称");
		check(Object_Text,Operation_checkExist,"按文件类型");
		check(Object_Text,Operation_checkExist,"按时间");
		check(Object_Text,Operation_checkExist,"按大小");
	}
	
	/**
	 *快速查看-APK安装文件-菜单-排序方式-对话框上有“按名称”排序方式
	 */
	public static void test_146()
	{
		//主体
		FileExplorerCommon.Enterclass("APK安装文件");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"排序方式");
		excute(Object_Text,Operation_ClickWait,"按名称");
		excute(Object_Text,Operation_ClickWait,"升序");
		String txt = (String) excute(Object_ResIdInstance,Operation_GetText,"com.sprd.fileexplorer:id/file_item_list_name","0");
		String txt1 = (String) excute(Object_ResIdInstance,Operation_GetText,"com.sprd.fileexplorer:id/file_item_list_name","1");
		String txt2 = (String) excute(Object_ResIdInstance,Operation_GetText,"com.sprd.fileexplorer:id/file_item_list_name","2");
		String[] Array = new String[]{txt,txt1,txt2};
		boolean result = FileExplorerCommon.isSortedByName(Array);
		Assert.assertEquals(result, true);
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"排序方式");
		excute(Object_Text,Operation_ClickWait,"按名称");
		excute(Object_Text,Operation_ClickWait,"降序");
		String text = (String) excute(Object_ResIdInstance,Operation_GetText,"com.sprd.fileexplorer:id/file_item_list_name","0");
		String text1 = (String) excute(Object_ResIdInstance,Operation_GetText,"com.sprd.fileexplorer:id/file_item_list_name","1");
		String text2 = (String) excute(Object_ResIdInstance,Operation_GetText,"com.sprd.fileexplorer:id/file_item_list_name","2");
		String[] Array1 = new String[]{text,text1,text2};
		boolean result1 = FileExplorerCommon.isSortedByName(Array1,true);
		Assert.assertEquals(result1, true);
	}
	
	/**
	 *快速查看-APK安装文件-菜单-排序方式-对话框上有“按文件类型”排序方式
	 */
	public static void test_147()
	{
		//主体
		FileExplorerCommon.Enterclass("APK安装文件");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"排序方式");
		excute(Object_Text,Operation_ClickWait,"按文件类型");
		excute(Object_Text,Operation_ClickWait,"升序");
		String txt = (String) excute(Object_ResIdInstance,Operation_GetText,"com.sprd.fileexplorer:id/file_item_list_name","0");
		String txt1 = (String) excute(Object_ResIdInstance,Operation_GetText,"com.sprd.fileexplorer:id/file_item_list_name","1");
		String txt2 = (String) excute(Object_ResIdInstance,Operation_GetText,"com.sprd.fileexplorer:id/file_item_list_name","2");
		String[] Array = new String[]{txt,txt1,txt2};
		boolean result = FileExplorerCommon.isSortedByType(Array);
		Assert.assertEquals(result, true);
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"排序方式");
		excute(Object_Text,Operation_ClickWait,"按文件类型");
		excute(Object_Text,Operation_ClickWait,"降序");
		String text = (String) excute(Object_ResIdInstance,Operation_GetText,"com.sprd.fileexplorer:id/file_item_list_name","0");
		String text1 = (String) excute(Object_ResIdInstance,Operation_GetText,"com.sprd.fileexplorer:id/file_item_list_name","1");
		String text2 = (String) excute(Object_ResIdInstance,Operation_GetText,"com.sprd.fileexplorer:id/file_item_list_name","2");
		String[] Array1 = new String[]{text,text1,text2};
		boolean result1 = FileExplorerCommon.isSortedByType(Array1,true);
		Assert.assertEquals(result1, true);
	}
	
	/**
	 *快速查看-APK安装文件-菜单-排序方式-对话框上有“按时间”排序方式
	 * @throws ParseException 
	 */
	public static void test_148() throws ParseException
	{
		//主体
		FileExplorerCommon.Enterclass("APK安装文件");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"排序方式");
		excute(Object_Text,Operation_ClickWait,"按时间");
		excute(Object_Text,Operation_ClickWait,"升序");
		String txt = (String) excute(Object_ResIdInstance,Operation_GetText,"com.sprd.fileexplorer:id/file_item_list_msg","0");
		String txt1 = (String) excute(Object_ResIdInstance,Operation_GetText,"com.sprd.fileexplorer:id/file_item_list_msg","1");
		String txt2 = (String) excute(Object_ResIdInstance,Operation_GetText,"com.sprd.fileexplorer:id/file_item_list_msg","2");
		String[] Array = new String[]{txt,txt1,txt2};
		boolean result = FileExplorerCommon.isSortedByTime(Array);
		Assert.assertEquals(result, true);
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"排序方式");
		excute(Object_Text,Operation_ClickWait,"按时间");
		excute(Object_Text,Operation_ClickWait,"降序");
		String text = (String) excute(Object_ResIdInstance,Operation_GetText,"com.sprd.fileexplorer:id/file_item_list_msg","0");
		String text1 = (String) excute(Object_ResIdInstance,Operation_GetText,"com.sprd.fileexplorer:id/file_item_list_msg","1");
		String text2 = (String) excute(Object_ResIdInstance,Operation_GetText,"com.sprd.fileexplorer:id/file_item_list_msg","2");
		String[] Array1 = new String[]{text,text1,text2};
		boolean result1 =FileExplorerCommon.isSortedByTime(Array1,true);
		Assert.assertEquals(result1, true);
	}
	
	/**
	 *快速查看-APK安装文件-菜单-排序方式-对话框上有“按大小”排序方式
	 * @throws ParseException 
	 */
	public static void test_149() throws ParseException
	{
		//主体
		FileExplorerCommon.Enterclass("APK安装文件");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"排序方式");
		excute(Object_Text,Operation_ClickWait,"按大小");
		excute(Object_Text,Operation_ClickWait,"升序");
		String txt = (String) excute(Object_ResIdInstance,Operation_GetText,"com.sprd.fileexplorer:id/file_item_list_msg","0");
		String txt1 = (String) excute(Object_ResIdInstance,Operation_GetText,"com.sprd.fileexplorer:id/file_item_list_msg","1");
		String txt2 = (String) excute(Object_ResIdInstance,Operation_GetText,"com.sprd.fileexplorer:id/file_item_list_msg","2");
		String[] Array = new String[]{txt,txt1,txt2};
		boolean result = FileExplorerCommon.isSortedBySize(Array);
		Assert.assertEquals(result, true);
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"排序方式");
		excute(Object_Text,Operation_ClickWait,"按大小");
		excute(Object_Text,Operation_ClickWait,"降序");
		String text = (String) excute(Object_ResIdInstance,Operation_GetText,"com.sprd.fileexplorer:id/file_item_list_msg","0");
		String text1 = (String) excute(Object_ResIdInstance,Operation_GetText,"com.sprd.fileexplorer:id/file_item_list_msg","1");
		String text2 = (String) excute(Object_ResIdInstance,Operation_GetText,"com.sprd.fileexplorer:id/file_item_list_msg","2");
		String[] Array1 = new String[]{text,text1,text2};
		boolean result1 = FileExplorerCommon.isSortedBySize(Array1,true);
		Assert.assertEquals(result1, true);
	}
	
	/**
	 *快速查看-APK安装文件-菜单-全部安装
	 * @throws UiObjectNotFoundException 
	 */
	public static void test_150() throws UiObjectNotFoundException
	{
		//前提
		DeviceCommon.enterApp(Devices_Desc_Setting);
		excute(Object_TextScroll,Operation_ClickWait,"安全","vertical");
		excute(Object_TextScroll,Operation_ClickWait,"未知来源","vertical");
		excute(Object_Text,Operation_ClickWait,"确定");
		//主体
		DeviceCommon.enterApp(Devices_Desc_FileManage);
		FileExplorerCommon.Enterclass("APK安装文件");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"全部安装");
		for (int i=0;i<=10;i++)
		{
			excute(Object_Text,Operation_ClickWait,"安装");
			check(Object_Text,Operation_WaitForExists,"应用安装完成。","35000");
			excute(Object_Text,Operation_ClickWait,"完成");
			if ((Boolean)excute(Object_Text,Operation_Exists,"APK安装文件"))
			{
				//清场
				DeviceCommon.enterApp(Devices_Desc_Setting);
				excute(Object_TextScroll,Operation_ClickWait,"安全","vertical");
				excute(Object_TextScroll,Operation_ClickWait,"未知来源","vertical");
				return ;
			}
		}
	}
	
	/**
	 *快速查看-APK安装文件-菜单-安装1个APK
	 * @throws UiObjectNotFoundException 
	 */
	public static void test_151() throws UiObjectNotFoundException
	{
		//前提
		DeviceCommon.enterApp(Devices_Desc_Setting);
		excute(Object_TextScroll,Operation_ClickWait,"安全","vertical");
		excute(Object_TextScroll,Operation_ClickWait,"未知来源","vertical");
		excute(Object_Text,Operation_ClickWait,"确定");
		//主体
		DeviceCommon.enterApp(Devices_Desc_FileManage);
		FileExplorerCommon.Enterclass("APK安装文件");
		excute(Object_ResIdInstance,Operation_ClickWait,"com.sprd.fileexplorer:id/file_item_list_name","0");
		excute(Object_Text,Operation_ClickWait,"安装");
		check(Object_Text,Operation_WaitForExists,"应用安装完成。","35000");
		excute(Object_Text,Operation_ClickWait,"完成");
		//清场
		DeviceCommon.enterApp(Devices_Desc_Setting);
		excute(Object_TextScroll,Operation_ClickWait,"安全","vertical");
		excute(Object_TextScroll,Operation_ClickWait,"未知来源","vertical");
	}
	
	
	/**
	 *手机存储-长按一个条目，此菜单上有如下功能项:复制、剪切、删除、清空、重命名、属性
	 */
	public static void test_152() 
	{
		//主体
		FileExplorerCommon.longclickitem("手机");
		check(Object_Text,Operation_checkExist,"复制");
		check(Object_Text,Operation_checkExist,"剪切");
		check(Object_Text,Operation_checkExist,"删除");
		check(Object_Text,Operation_checkExist,"清空");
		check(Object_Text,Operation_checkExist,"重命名");
		check(Object_Text,Operation_checkExist,"属性");
	}
	
	/**
	 *手机存储-长按一个条目-复制
	 */
	public static void test_153() 
	{
		//主体
		FileExplorerCommon.longclickitem("手机");
		excute(Object_Text,Operation_ClickWait,"复制");
		check(Object_Text,Operation_checkExist,"选择存储位置");
	}
	
	/**
	 *手机存储-长按一个条目-剪切
	 */
	public static void test_154() 
	{
		//主体
		FileExplorerCommon.longclickitem("手机");
		excute(Object_Text,Operation_ClickWait,"剪切");
		check(Object_Text,Operation_checkExist,"选择存储位置");
	}
	
	/**
	 *手机存储-长按一个条目-删除
	 */
	public static void test_155() 
	{
		//主体
		FileExplorerCommon.longclickitem("手机");
		excute(Object_Text,Operation_ClickWait,"删除");
		check(Object_Text,Operation_checkExist,"要删除所选内容吗？");
	}
	
	/**
	 *手机存储-长按一个条目-清空
	 */
	public static void test_156() 
	{
		//主体
		FileExplorerCommon.longclickitem("手机");
		excute(Object_Text,Operation_ClickWait,"清空");
		check(Object_Text,Operation_checkExist,"是否删除当前文件夹中所有文件?");
	}
	
	/**
	 *手机存储-长按一个条目-重命名
	 */
	public static void test_157() 
	{
		//主体
		FileExplorerCommon.storagePath("手机");
		if (!(Boolean)excute(Object_TextScrollWithResId, Operation_Exists,"com.sprd.fileexplorer:id/detailed_file_list","ReNameTest","vertical"))
		{
			excute(Object_Device, Operation_PressMenu);
			excute(Object_Text, Operation_ClickWait,"新建文件夹");
			excute(Object_ResourceId,Operation_SetText,"com.sprd.fileexplorer:id/name_editor","ReNameTest");
			excute(Object_Text, Operation_ClickWait,"确定");
		}
		if ((Boolean)excute(Object_TextScrollWithResId, Operation_Exists,"com.sprd.fileexplorer:id/detailed_file_list","NewName","vertical"))
		{
			excute(Object_TextScrollWithResId,Operation_LongClick,"com.sprd.fileexplorer:id/detailed_file_list","NewName","vertical");
			excute(Object_Text,Operation_ClickWait,"删除");
			excute(Object_Text,Operation_ClickWait,"确定");
		}
		//主体
		excute(Object_TextScrollWithResId,Operation_LongClick,"com.sprd.fileexplorer:id/detailed_file_list","ReNameTest","vertical");
		excute(Object_Text,Operation_ClickWait,"重命名");
		excute(Object_ResourceId,Operation_SetText,"com.sprd.fileexplorer:id/name_editor","NewName");
		excute(Object_Text,Operation_ClickWait,"确定");
		check(Object_TextScrollWithResId, Operation_checkExist,"com.sprd.fileexplorer:id/detailed_file_list","NewName","vertical");
		//清场
		excute(Object_TextScrollWithResId,Operation_LongClick,"com.sprd.fileexplorer:id/detailed_file_list","NewName","vertical");
		excute(Object_Text,Operation_ClickWait,"删除");
		excute(Object_Text,Operation_ClickWait,"确定");
	}
	
	/**
	 * 手机存储-长按一个条目-属性
	 */
	public static void test_158() 
	{
		//主体
		FileExplorerCommon.longclickitem("手机");
		excute(Object_Text,Operation_ClickWait,"属性");
		check(Object_ClassInstance,Operation_TextContainsTrue,"android.widget.TextView","1","类型");
		check(Object_ClassInstance,Operation_TextContainsTrue,"android.widget.TextView","1","位置");
		check(Object_ClassInstance,Operation_TextContainsTrue,"android.widget.TextView","1","日期");
		check(Object_ClassInstance,Operation_TextContainsTrue,"android.widget.TextView","1","包含");
		check(Object_ClassInstance,Operation_TextContainsTrue,"android.widget.TextView","1","大小");
		check(Object_ClassInstance,Operation_TextContainsTrue,"android.widget.TextView","1","是否可写");
		check(Object_ClassInstance,Operation_TextContainsTrue,"android.widget.TextView","1","是否可读");
		check(Object_ClassInstance,Operation_TextContainsTrue,"android.widget.TextView","1","是否隐藏");
	}
	
	/**
	 *手机存储-长按一个条目-搜索
	 */
	public static void test_159() 
	{
		//主体
		FileExplorerCommon.storagePath("手机");
		excute(Object_ResourceId,Operation_ClickWait,"com.sprd.fileexplorer:id/searchfile");
		check(Object_Text, Operation_checkExist,"搜索范围:");
	}
	
	/**
	 * 手机存储-点击页面上的更多（菜单）
	 */
	public static void test_160()
	{
		//主体
		FileExplorerCommon.clickmenu("手机");
		check(Object_Text, Operation_checkExist,"新建文件夹");
		check(Object_Text, Operation_checkExist,"新建文件");
		check(Object_Text, Operation_checkExist,"选择多个");
		check(Object_Text, Operation_checkExist,"排序方式");
		check(Object_Text, Operation_checkExist,"存储状态");
		check(Object_Text, Operation_checkExist,"设置");
	}
	
	/**
	 * 手机存储-点击页面上的更多（菜单）-新建文件夹
	 */
	public static void test_162()
	{
		//前提
		FileExplorerCommon.storagePath("手机");
		if ((Boolean)excute(Object_TextScrollWithResId, Operation_Exists,"com.sprd.fileexplorer:id/detailed_file_list","NewFolder","vertical"))
		{
			excute(Object_TextScrollWithResId,Operation_LongClick,"com.sprd.fileexplorer:id/detailed_file_list","NewFolder","vertical");
			excute(Object_Text,Operation_ClickWait,"删除");
			excute(Object_Text,Operation_ClickWait,"确定");
		}
		//主体
		excute(Object_Device, Operation_PressMenu);
		excute(Object_Text, Operation_ClickWait,"新建文件夹");
		excute(Object_ResourceId,Operation_SetText,"com.sprd.fileexplorer:id/name_editor","NewFolder");
		excute(Object_Text, Operation_ClickWait,"确定");
		check(Object_TextScrollWithResId, Operation_checkExist,"com.sprd.fileexplorer:id/detailed_file_list","NewFolder","vertical");
		//清场
		excute(Object_TextScrollWithResId,Operation_LongClick,"com.sprd.fileexplorer:id/detailed_file_list","NewFolder","vertical");
		excute(Object_Text,Operation_ClickWait,"删除");
		excute(Object_Text,Operation_ClickWait,"确定");
	}
	
	/**
	 * 手机存储-点击页面上的更多（菜单）选择多个
	 */
	public static void test_164() 
	{
		//主体
		FileExplorerCommon.clickmenu("手机");
		excute(Object_Text, Operation_ClickWait,"选择多个");
		check(Object_Text,Operation_checkExist,"选择全部");
	}
	
	/**
	 * 手机存储-点击页面上的更多（菜单）选择全部
	 */
	public static void test_165() 
	{
		//主体
		FileExplorerCommon.clickmenu("手机");
		excute(Object_Text, Operation_ClickWait,"选择多个");
		excute(Object_ResourceId, Operation_ClickWait,"com.sprd.fileexplorer:id/select_all_cb");
		check(Object_ResourceId,Operation_CheckedTrue,"com.sprd.fileexplorer:id/select_all_cb");
		excute(Object_Device, Operation_PressMenu);
		check(Object_ClassInstance,Operation_EnabledTrue,"android.widget.LinearLayout","0");
		check(Object_ClassInstance,Operation_EnabledTrue,"android.widget.LinearLayout","1");
	}
	
	
	/**
	 * 手机存储-点击页面上的更多（菜单）-取消选择全部
	 */
	
	public static void test_166() 
	{
		//主体
		FileExplorerCommon.clickmenu("手机");
		excute(Object_Text, Operation_ClickWait,"选择多个");
		excute(Object_ResourceId, Operation_ClickWait,"com.sprd.fileexplorer:id/select_all_cb");
		excute(Object_ResourceId, Operation_ClickWait,"com.sprd.fileexplorer:id/select_all_cb");
		check(Object_ResourceId,Operation_CheckedFalse,"com.sprd.fileexplorer:id/select_all_cb");
		excute(Object_Device, Operation_PressMenu);
		check(Object_ClassInstance,Operation_EnabledFalse,"android.widget.LinearLayout","0");
		check(Object_ClassInstance,Operation_EnabledFalse,"android.widget.LinearLayout","1");
	}
	
	/**
	 * 手机存储-点击页面上的更多（菜单）-选择1个条目，删除
	 */
	
	public static void test_167() 
	{
		//主体
		FileExplorerCommon.clickmenu("手机");
		excute(Object_Text, Operation_ClickWait,"选择多个");
		excute(Object_ResIdInstance, Operation_ClickWait,"com.sprd.fileexplorer:id/file_item_list_name","0");
		excute(Object_ResourceId, Operation_ClickWait,"com.sprd.fileexplorer:id/menu_delete");
		check(Object_Text,Operation_checkExist,"要删除所选内容吗？");
	}
	
	/**
	 * 手机存储-点击页面上的更多（菜单）选择1个条目,复制
	 */
	public static void test_168() 
	{
		//主体
		FileExplorerCommon.clickmenu("手机");
		excute(Object_Text, Operation_ClickWait,"选择多个");
		excute(Object_ResIdInstance, Operation_ClickWait,"com.sprd.fileexplorer:id/file_item_list_name","0");
		excute(Object_Device, Operation_PressMenu);
		excute(Object_Text, Operation_ClickWait,"复制");
		check(Object_Text,Operation_checkExist,"选择存储位置");
	}
	
	/**
	 * 手机存储-点击页面上的更多（菜单）选择1个条目,剪切
	 */
	public static void test_169() 
	{
		//主体
		FileExplorerCommon.clickmenu("手机");
		excute(Object_Text, Operation_ClickWait,"选择多个");
		excute(Object_ResIdInstance, Operation_ClickWait,"com.sprd.fileexplorer:id/file_item_list_name","0");
		excute(Object_Device, Operation_PressMenu);
		excute(Object_Text, Operation_ClickWait,"剪切");
		check(Object_Text,Operation_checkExist,"选择存储位置");
	}
	
	/**
	 * 手机存储-点击页面上的更多-排序方式
	 */
	public static void test_170() 
	{
		//主体
		FileExplorerCommon.clickmenu("手机");
		excute(Object_Text, Operation_ClickWait,"排序方式");
		check(Object_Text,Operation_checkExist,"按名称");
		check(Object_Text,Operation_checkExist,"按文件类型");
		check(Object_Text,Operation_checkExist,"按时间");
		check(Object_Text,Operation_checkExist,"按大小");
	}
	
	/**
	 * 手机存储-点击页面上的更多-排序方式-按名称
	 */
	public static void test_171() 
	{
		//主体
		FileExplorerCommon.storagePath("手机");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"排序方式");
		excute(Object_Text,Operation_ClickWait,"按名称");
		excute(Object_Text,Operation_ClickWait,"升序");
		String txt = (String) excute(Object_ResIdInstance,Operation_GetText,"com.sprd.fileexplorer:id/file_item_list_name","0");
		String txt1 = (String) excute(Object_ResIdInstance,Operation_GetText,"com.sprd.fileexplorer:id/file_item_list_name","1");
		String txt2 = (String) excute(Object_ResIdInstance,Operation_GetText,"com.sprd.fileexplorer:id/file_item_list_name","2");
		String[] Array = new String[]{txt,txt1,txt2};
		boolean result = FileExplorerCommon.isSortedByName(Array);
		Assert.assertEquals(result, true);
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"排序方式");
		excute(Object_Text,Operation_ClickWait,"按名称");
		excute(Object_Text,Operation_ClickWait,"降序");
		String text = (String) excute(Object_ResIdInstance,Operation_GetText,"com.sprd.fileexplorer:id/file_item_list_name","0");
		String text1 = (String) excute(Object_ResIdInstance,Operation_GetText,"com.sprd.fileexplorer:id/file_item_list_name","1");
		String text2 = (String) excute(Object_ResIdInstance,Operation_GetText,"com.sprd.fileexplorer:id/file_item_list_name","2");
		String[] Array1 = new String[]{text,text1,text2};
		boolean result1 = FileExplorerCommon.isSortedByName(Array1,true);
		Assert.assertEquals(result1, true);
	}
	
	/**
	 *快速查看-文档-菜单-排序方式-对话框上有“按文件类型”排序方式
	 */
	public static void test_172()
	{
		//主体
		FileExplorerCommon.storagePath("手机");
		if((Boolean)excute(Object_Text,Operation_Exists,"FileExplorer_Phone"))
		{
			excute(Object_Text,Operation_ClickWait,"FileExplorer_Phone");
		}
		else
		{
			excute(Object_TextScroll,Operation_ClickWait,"FileExplorer_Phone","vertical");
		}	
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"排序方式");
		excute(Object_Text,Operation_ClickWait,"按文件类型");
		excute(Object_Text,Operation_ClickWait,"升序");
		String txt = (String) excute(Object_ResIdInstance,Operation_GetText,"com.sprd.fileexplorer:id/file_item_list_name","0");
		String txt1 = (String) excute(Object_ResIdInstance,Operation_GetText,"com.sprd.fileexplorer:id/file_item_list_name","1");
		String txt2 = (String) excute(Object_ResIdInstance,Operation_GetText,"com.sprd.fileexplorer:id/file_item_list_name","2");
		String[] Array = new String[]{txt,txt1,txt2};
		boolean result = FileExplorerCommon.isSortedByType(Array);
		Assert.assertEquals(result, true);
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"排序方式");
		excute(Object_Text,Operation_ClickWait,"按文件类型");
		excute(Object_Text,Operation_ClickWait,"降序");
		String text = (String) excute(Object_ResIdInstance,Operation_GetText,"com.sprd.fileexplorer:id/file_item_list_name","0");
		String text1 = (String) excute(Object_ResIdInstance,Operation_GetText,"com.sprd.fileexplorer:id/file_item_list_name","1");
		String text2 = (String) excute(Object_ResIdInstance,Operation_GetText,"com.sprd.fileexplorer:id/file_item_list_name","2");
		String[] Array1 = new String[]{text,text1,text2};
		boolean result1 = FileExplorerCommon.isSortedByType(Array1,true);
		Assert.assertEquals(result1, true);
	}
	
	/**
	 * 手机存储-点击页面上的更多-排序方式-按时间
	 * @throws ParseException 
	 */
	public static void test_173() throws ParseException 
	{
		//主体
		FileExplorerCommon.storagePath("手机");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"排序方式");
		excute(Object_Text,Operation_ClickWait,"按时间");
		excute(Object_Text,Operation_ClickWait,"升序");
		String txt = (String) excute(Object_ResIdInstance,Operation_GetText,"com.sprd.fileexplorer:id/file_item_list_msg","0");
		String txt1 = (String) excute(Object_ResIdInstance,Operation_GetText,"com.sprd.fileexplorer:id/file_item_list_msg","1");
		String txt2 = (String) excute(Object_ResIdInstance,Operation_GetText,"com.sprd.fileexplorer:id/file_item_list_msg","2");
		String[] Array = new String[]{txt,txt1,txt2};
		boolean result = FileExplorerCommon.isSortedByTime(Array);
		Assert.assertEquals(result, true);
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"排序方式");
		excute(Object_Text,Operation_ClickWait,"按时间");
		excute(Object_Text,Operation_ClickWait,"降序");
		String text = (String) excute(Object_ResIdInstance,Operation_GetText,"com.sprd.fileexplorer:id/file_item_list_msg","0");
		String text1 = (String) excute(Object_ResIdInstance,Operation_GetText,"com.sprd.fileexplorer:id/file_item_list_msg","1");
		String text2 = (String) excute(Object_ResIdInstance,Operation_GetText,"com.sprd.fileexplorer:id/file_item_list_msg","2");
		String[] Array1 = new String[]{text,text1,text2};
		boolean result1 = FileExplorerCommon.isSortedByTime(Array1,true);
		Assert.assertEquals(result1, true);
	}


	/**
	 *快速查看-文档-菜单-排序方式-对话框上有“按文件大小”排序方式
	 * @throws ParseException 
	 */
	public static void test_174() throws ParseException
	{
		//主体
		FileExplorerCommon.storagePath("手机");
		if((Boolean)excute(Object_Text,Operation_Exists,"FileExplorer_Phone"))
		{
			excute(Object_Text,Operation_ClickWait,"FileExplorer_Phone");
		}
		else
		{
			excute(Object_TextScroll,Operation_ClickWait,"FileExplorer_Phone","vertical");
		}	
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"排序方式");
		excute(Object_Text,Operation_ClickWait,"按大小");
		excute(Object_Text,Operation_ClickWait,"升序");
		String txt = (String) excute(Object_ResIdInstance,Operation_GetText,"com.sprd.fileexplorer:id/file_item_list_msg","0");
		String txt1 = (String) excute(Object_ResIdInstance,Operation_GetText,"com.sprd.fileexplorer:id/file_item_list_msg","1");
		String txt2 = (String) excute(Object_ResIdInstance,Operation_GetText,"com.sprd.fileexplorer:id/file_item_list_msg","2");
		String[] Array = new String[]{txt,txt1,txt2};
		boolean result = FileExplorerCommon.isSortedBySize(Array);
		Assert.assertEquals(result, true);
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"排序方式");
		excute(Object_Text,Operation_ClickWait,"按大小");
		excute(Object_Text,Operation_ClickWait,"降序");
		String text = (String) excute(Object_ResIdInstance,Operation_GetText,"com.sprd.fileexplorer:id/file_item_list_msg","0");
		String text1 = (String) excute(Object_ResIdInstance,Operation_GetText,"com.sprd.fileexplorer:id/file_item_list_msg","1");
		String text2 = (String) excute(Object_ResIdInstance,Operation_GetText,"com.sprd.fileexplorer:id/file_item_list_msg","2");
		String[] Array1 = new String[]{text,text1,text2};
		boolean result1 = FileExplorerCommon.isSortedBySize(Array1,true);
		Assert.assertEquals(result1, true);
	}
		
	/**
	 * 手机存储-点击页面上的更多-存储状态
	 * @throws ParseException 
	 */
	public static void test_175() throws ParseException 
	{
		//主体
		FileExplorerCommon.storagePath("手机");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"存储状态");
		check(Object_Text,Operation_checkExist,"内部存储设备");
	}
	
	/**
	 * 手机存储-点击页面上的更多-设置
	 * @throws ParseException 
	 */
	public static void test_176() throws ParseException 
	{
		//主体
		FileExplorerCommon.storagePath("手机");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"设置");
		check(Object_Text,Operation_checkExist,"显示设置");
	}
	
	/**
	*长按存储卡下条目，弹出功能菜单 
	*/
	public static void test_177() 
	{
		//主体
		FileExplorerCommon.longclickitem("存储卡");
		check(Object_Text,Operation_checkExist,"复制");
		check(Object_Text,Operation_checkExist,"剪切");
		check(Object_Text,Operation_checkExist,"删除");
		check(Object_Text,Operation_checkExist,"清空");
		check(Object_Text,Operation_checkExist,"重命名");
		check(Object_Text,Operation_checkExist,"属性");
	}
	
	/**
	 * 在功能菜单上，点击复制
	 */
	public static void test_178() 
	{
		//主体
		FileExplorerCommon.longclickitem("存储卡");
		excute(Object_Text,Operation_ClickWait,"复制");
		check(Object_Text,Operation_checkExist,"选择存储位置");
	}
	
	/**
	 * 在功能菜单上，点击剪切
	 */
	public static void test_179() 
	{
		//主体
		FileExplorerCommon.longclickitem("存储卡");
		excute(Object_Text,Operation_ClickWait,"剪切");
		check(Object_Text,Operation_checkExist,"选择存储位置");
	}
	
	/**
	 * 在功能菜单上，点击删除
	 */
	public static void test_180() 
	{
		//主体
		FileExplorerCommon.longclickitem("存储卡");
		excute(Object_Text,Operation_ClickWait,"删除");
		check(Object_Text,Operation_checkExist,"要删除所选内容吗？");
	}
	
	/**
	 * 在功能菜单上，点击清空
	 */
	public static void test_181() 
	{
		//主体
		FileExplorerCommon.longclickitem("存储卡");
		excute(Object_Text,Operation_ClickWait,"清空");
		check(Object_Text,Operation_checkExist,"是否删除当前文件夹中所有文件?");
	}
	
	/**
	 * 在功能菜单上，点击重命名
	 */
	public static void test_182() 
	{
		//前提
		FileExplorerCommon.storagePath("存储卡");
		if (!(Boolean)excute(Object_TextScrollWithResId, Operation_Exists,"com.sprd.fileexplorer:id/detailed_file_list","ReNameTest","vertical"))
		{
			excute(Object_Device, Operation_PressMenu);
			excute(Object_Text, Operation_ClickWait,"新建文件夹");
			excute(Object_ResourceId,Operation_SetText,"com.sprd.fileexplorer:id/name_editor","ReNameTest");
			excute(Object_Text, Operation_ClickWait,"确定");
		}
		if ((Boolean)excute(Object_TextScrollWithResId, Operation_Exists,"com.sprd.fileexplorer:id/detailed_file_list","NewName","vertical"))
		{
			excute(Object_TextScrollWithResId,Operation_LongClick,"com.sprd.fileexplorer:id/detailed_file_list","NewName","vertical");
			excute(Object_Text,Operation_ClickWait,"删除");
			excute(Object_Text,Operation_ClickWait,"确定");
		}
		//主体
		excute(Object_TextScrollWithResId,Operation_LongClick,"com.sprd.fileexplorer:id/detailed_file_list","ReNameTest","vertical");
		excute(Object_Text,Operation_ClickWait,"重命名");
		excute(Object_ResourceId,Operation_SetText,"com.sprd.fileexplorer:id/name_editor","NewName");
		excute(Object_Text,Operation_ClickWait,"确定");
		check(Object_TextScrollWithResId, Operation_checkExist,"com.sprd.fileexplorer:id/detailed_file_list","NewName","vertical");
		//清场
		excute(Object_TextScrollWithResId,Operation_LongClick,"com.sprd.fileexplorer:id/detailed_file_list","NewName","vertical");
		excute(Object_Text,Operation_ClickWait,"删除");
		excute(Object_Text,Operation_ClickWait,"确定");
	}
	
	/**
	 * 在功能菜单上，点击属性
	 */
	public static void test_183() 
	{
		//主体
		FileExplorerCommon.longclickitem("存储卡");
		excute(Object_Text,Operation_ClickWait,"属性");
		check(Object_ClassInstance,Operation_TextContainsTrue,"android.widget.TextView","1","类型");
		check(Object_ClassInstance,Operation_TextContainsTrue,"android.widget.TextView","1","位置");
		check(Object_ClassInstance,Operation_TextContainsTrue,"android.widget.TextView","1","日期");
		check(Object_ClassInstance,Operation_TextContainsTrue,"android.widget.TextView","1","包含");
		check(Object_ClassInstance,Operation_TextContainsTrue,"android.widget.TextView","1","大小");
		check(Object_ClassInstance,Operation_TextContainsTrue,"android.widget.TextView","1","是否可写");
		check(Object_ClassInstance,Operation_TextContainsTrue,"android.widget.TextView","1","是否可读");
		check(Object_ClassInstance,Operation_TextContainsTrue,"android.widget.TextView","1","是否隐藏");
	}
	
	/**
	 * 通过页面上的text判断页面跳转到搜索页面
	 */
	public static void test_184() 
	{
		//主体
		excute(Object_ClassName,Operation_ClickWait,"android.widget.Spinner");
		excute(Object_Text,Operation_ClickWait,"存储卡");
		excute(Object_ResourceId,Operation_ClickWait,"com.sprd.fileexplorer:id/searchfile");
		check(Object_Text, Operation_checkExist,"搜索");
	}
	
	/**
	 * 点击页面上的更多（菜单）
	 */
	public static void test_185()
	{
		//主体
		FileExplorerCommon.clickmenu("存储卡");
		check(Object_Text, Operation_checkExist,"新建文件夹");
		check(Object_Text, Operation_checkExist,"新建文件");
		check(Object_Text, Operation_checkExist,"选择多个");
		check(Object_Text, Operation_checkExist,"排序方式");
		check(Object_Text, Operation_checkExist,"存储状态");
		check(Object_Text, Operation_checkExist,"设置");
	}
	
	/**
	 * 新建文件夹
	 */
	public static void test_187()
	{
		//前提
		FileExplorerCommon.storagePath("存储卡");
		if ((Boolean)excute(Object_TextScrollWithResId, Operation_Exists,"com.sprd.fileexplorer:id/detailed_file_list","NewFolderName","vertical"))
		{
			excute(Object_TextScrollWithResId,Operation_LongClick,"com.sprd.fileexplorer:id/detailed_file_list","NewFolderName","vertical");
			excute(Object_Text,Operation_ClickWait,"删除");
			excute(Object_Text,Operation_ClickWait,"确定");
		}
		//主体
		excute(Object_Device, Operation_PressMenu);
		excute(Object_Text, Operation_ClickWait,"新建文件夹");
		excute(Object_ResourceId,Operation_SetText,"com.sprd.fileexplorer:id/name_editor","NewFolderName");
		excute(Object_Text, Operation_ClickWait,"确定");
		check(Object_TextScrollWithResId, Operation_checkExist,"com.sprd.fileexplorer:id/detailed_file_list","NewFolderName","vertical");
		//清场
		excute(Object_TextScrollWithResId,Operation_LongClick,"com.sprd.fileexplorer:id/detailed_file_list","NewFolderName","vertical");
		excute(Object_Text,Operation_ClickWait,"删除");
		excute(Object_Text,Operation_ClickWait,"确定");
	}
	
	/**
	 * 新建文件
	 */
	public static void test_188() 
	{
		//前提
		FileExplorerCommon.storagePath("存储卡");
		if ((Boolean)excute(Object_TextScrollWithResId, Operation_Exists,"com.sprd.fileexplorer:id/detailed_file_list","NewFileName.txt","vertical"))
		{
			excute(Object_TextScrollWithResId,Operation_LongClick,"com.sprd.fileexplorer:id/detailed_file_list","NewFileName.txt","vertical");
			excute(Object_Text,Operation_ClickWait,"删除");
			excute(Object_Text,Operation_ClickWait,"确定");
		}
		//主体
		excute(Object_Device, Operation_PressMenu);
		excute(Object_Text, Operation_ClickWait,"新建文件");
		excute(Object_ResourceId,Operation_SetText,"com.sprd.fileexplorer:id/name_editor","NewFileName");
		excute(Object_Text, Operation_ClickWait,"确定");
		check(Object_TextScrollWithResId, Operation_checkExist,"com.sprd.fileexplorer:id/detailed_file_list","NewFileName.txt","vertical");
		//清场
		excute(Object_TextScrollWithResId,Operation_LongClick,"com.sprd.fileexplorer:id/detailed_file_list","NewFileName.txt","vertical");
		excute(Object_Text,Operation_ClickWait,"删除");
		excute(Object_Text,Operation_ClickWait,"确定");
	}
	
	/**
	 * 选择多个
	 */
	public static void test_189() 
	{
		//主体
		FileExplorerCommon.clickmenu("存储卡");
		excute(Object_Text, Operation_ClickWait,"选择多个");
		check(Object_Text,Operation_checkExist,"选择全部");
	}
		/**
	 * 存储卡模式下，全部选中时，复制、剪切、删除功能可用，页面上的checkbox全部被选中。
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_190() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_ResourceId, Operation_ClickWait, "android:id/text1");//点击选择查看列表
		excute(Object_Text, Operation_ClickWait, "存储卡");
		excute(Object_Description, Operation_ClickWait, "更多选项");
		excute(Object_Text, Operation_ClickWait, "选择多个");
		excute(Object_ResourceId, Operation_ClickWait, "com.sprd.fileexplorer:id/select_all_cb");
		int count=(Integer)excute(Object_ResourceId, Operation_GetChildCount, "com.sprd.fileexplorer:id/list");
		for (int i = 0; i < count; i++) {
			check(Object_ResIdInstance, Operation_CheckedTrue, "com.sprd.fileexplorer:id/select_checkbox",String.valueOf(i));
		}
		check(Object_Description, Operation_EnabledTrue, "删除");
		excute(Object_Description, Operation_ClickWait, "更多选项");
		check(Object_ClassInstance, Operation_EnabledTrue, "android.widget.LinearLayout","0");
		check(Object_ClassInstance, Operation_EnabledTrue, "android.widget.LinearLayout","1");
	}
	
	/**
	 * 存储卡模式下，取消全部选中时，复制、剪切、删除功能不可用，页面上的checkbox未被选中。
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_191() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_ResourceId, Operation_ClickWait, "android:id/text1");//点击选择查看列表
		excute(Object_Text, Operation_ClickWait, "存储卡");
		excute(Object_Description, Operation_ClickWait, "更多选项");
		excute(Object_Text, Operation_ClickWait, "选择多个");
		excute(Object_ResourceId, Operation_ClickWait, "com.sprd.fileexplorer:id/select_all_cb");
		excute(Object_ResourceId, Operation_ClickWait, "com.sprd.fileexplorer:id/select_all_cb");
		int count=(Integer)excute(Object_ResourceId, Operation_GetChildCount, "com.sprd.fileexplorer:id/list");
		for (int i = 0; i < count; i++) {
			check(Object_ResIdInstance, Operation_CheckedFalse, "com.sprd.fileexplorer:id/select_checkbox",String.valueOf(i));
		}
		check(Object_Description, Operation_EnabledFalse, "删除");
		excute(Object_Description, Operation_ClickWait, "更多选项");
		check(Object_ClassInstance, Operation_EnabledFalse, "android.widget.LinearLayout","0");
		check(Object_ClassInstance, Operation_EnabledFalse, "android.widget.LinearLayout","1");
	}
	/**
	 * 选择存储卡，点击菜单进入选择多个，选择一个点击删除
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_192() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_ResourceId, Operation_ClickWait, "android:id/text1");//点击选择查看列表
		excute(Object_Text, Operation_ClickWait, "存储卡");
		excute(Object_Description, Operation_ClickWait, "更多选项");
		excute(Object_Text, Operation_ClickWait, "选择多个");
		excute(Object_ResIdInstance, Operation_ClickWait, "com.sprd.fileexplorer:id/select_checkbox","0");
		excute(Object_Description, Operation_ClickWait, "删除");
		check(Object_Text, Operation_checkExist, "要删除所选内容吗？");
		
	}
	
	
	/**
	 * 选择存储卡，点击菜单进入选择多个，选择一个文件复制
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_193() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_ResourceId, Operation_ClickWait, "android:id/text1");//点击选择查看列表
		excute(Object_Text, Operation_ClickWait, "存储卡");
		excute(Object_Description, Operation_ClickWait, "更多选项");
		excute(Object_Text, Operation_ClickWait, "选择多个");
		excute(Object_ResIdInstance, Operation_ClickWait, "com.sprd.fileexplorer:id/select_checkbox","0");
		excute(Object_Description, Operation_ClickWait, "更多选项");
		excute(Object_Text, Operation_ClickWait, "复制");
		check(Object_Text, Operation_checkExist, "选择存储位置");
		
	}
	
	/**
	 * 选择存储卡，点击菜单进入选择多个，选择一个文件剪切
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_194() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_ResourceId, Operation_ClickWait, "android:id/text1");//点击选择查看列表
		excute(Object_Text, Operation_ClickWait, "存储卡");
		excute(Object_Description, Operation_ClickWait, "更多选项");
		excute(Object_Text, Operation_ClickWait, "选择多个");
		excute(Object_ResIdInstance, Operation_ClickWait, "com.sprd.fileexplorer:id/select_checkbox","0");
		excute(Object_Description, Operation_ClickWait, "更多选项");
		excute(Object_Text, Operation_ClickWait, "剪切");
		check(Object_Text, Operation_checkExist, "选择存储位置");
		
	}
	/**
	 * 选择存储卡，点击菜单上的排序方式
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_195() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_ResourceId, Operation_ClickWait, "android:id/text1");//点击选择查看列表
		excute(Object_Text, Operation_ClickWait, "存储卡");
		excute(Object_Description, Operation_ClickWait, "更多选项");
		excute(Object_Text, Operation_ClickWait, "排序方式");
		check(Object_Text, Operation_checkExist, "按名称");
		check(Object_Text, Operation_checkExist, "按文件类型");
		check(Object_Text, Operation_checkExist, "按时间");
		check(Object_Text, Operation_checkExist, "按大小");
	}
	/**
	 * 存储卡模式下，菜单点击排序方式，按名称升序、降序
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_196() throws UiObjectNotFoundException, RemoteException,IndexOutOfBoundsException
	{
		//主体
		excute(Object_ResourceId, Operation_ClickWait, "android:id/text1");//点击选择查看列表
		excute(Object_Text, Operation_ClickWait, "存储卡");
		excute(Object_Text, Operation_ClickWait, "FileExplorer_SD");
		excute(Object_Description, Operation_ClickWait, "更多选项");
		excute(Object_Text, Operation_ClickWait, "排序方式");
		excute(Object_Text, Operation_ClickWait, "按名称");
		excute(Object_Text, Operation_ClickWait, "升序");
		String [] name1 =new String[3];
		for (int i = 0; i < 3; i++) {
			String str=(String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_name", String.valueOf(i));
			name1[i]=str;
		}
		Assert.assertTrue(FileExplorerCommon.isSortedByName(name1));
		excute(Object_Description, Operation_ClickWait, "更多选项");
		excute(Object_Text, Operation_ClickWait, "排序方式");
		excute(Object_Text, Operation_ClickWait, "按名称");
		excute(Object_Text, Operation_ClickWait, "降序");
		String [] name2 =new String[3];
		for (int i = 0; i < 3; i++) {
			String str=(String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_name", String.valueOf(i));
			name2[i]=str;
		}
		Assert.assertTrue(FileExplorerCommon.isSortedByName(name2,true));
	}
	/**
	 * 存储卡模式下，菜单点击排序方式，按文件类型升序、降序
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_197() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_ResourceId, Operation_ClickWait, "android:id/text1");//点击选择查看列表
		excute(Object_Text, Operation_ClickWait, "存储卡");
		excute(Object_Text, Operation_ClickWait, "FileExplorer_SD");
		excute(Object_Description, Operation_ClickWait, "更多选项");
		excute(Object_Text, Operation_ClickWait, "排序方式");
		excute(Object_Text, Operation_ClickWait, "按文件类型");
		excute(Object_Text, Operation_ClickWait, "升序");
		String [] type1 =new String[3];
		for (int i = 0; i < 3; i++) {
			String str = (String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_name", String.valueOf(i));
			type1[i]=str;
		}
		Assert.assertTrue(FileExplorerCommon.isSortedByType(type1));
		excute(Object_Description, Operation_ClickWait, "更多选项");
		excute(Object_Text, Operation_ClickWait, "排序方式");
		excute(Object_Text, Operation_ClickWait, "按文件类型");
		excute(Object_Text, Operation_ClickWait, "降序");
		String [] type2 =new String[3];
		for (int i = 0; i < 3; i++) {
			String str = (String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_name", String.valueOf(i));
			type2[i]=str;
		}
		Assert.assertTrue(FileExplorerCommon.isSortedByType(type2,true));
	}
	/**
	 * 存储卡模式下，菜单点击排序方式，按时间升序、降序
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_198() throws UiObjectNotFoundException, RemoteException , ParseException
	{
		//主体
		excute(Object_ResourceId, Operation_ClickWait, "android:id/text1");//点击选择查看列表
		excute(Object_Text, Operation_ClickWait, "存储卡");
		excute(Object_Text, Operation_ClickWait, "FileExplorer_SD");
		excute(Object_Description, Operation_ClickWait, "更多选项");
		excute(Object_Text, Operation_ClickWait, "排序方式");
		excute(Object_Text, Operation_ClickWait, "按时间");
		excute(Object_Text, Operation_ClickWait, "升序");
		String [] time1=new String[3];
		for (int i = 0; i < 3; i++) {
			String str=(String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_msg",String.valueOf(i));
			time1[i]=str;
		}
		Assert.assertTrue(FileExplorerCommon.isSortedByTime(time1));
		excute(Object_Description, Operation_ClickWait, "更多选项");
		excute(Object_Text, Operation_ClickWait, "排序方式");
		excute(Object_Text, Operation_ClickWait, "按时间");
		excute(Object_Text, Operation_ClickWait, "降序");
		String [] time2=new String[3];
		for (int i = 0; i < 3; i++) {
			String str=(String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_msg",String.valueOf(i));
			time2[i]=str;
		}
		Assert.assertTrue(FileExplorerCommon.isSortedByTime(time2,true));
	}
	
	/**
	 * 存储卡模式下，菜单点击排序方式，按大小升序、降序
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_199() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_ResourceId, Operation_ClickWait, "android:id/text1");//点击选择查看列表
		excute(Object_Text, Operation_ClickWait, "存储卡");
		excute(Object_Text, Operation_ClickWait, "FileExplorer_SD");
		excute(Object_Description, Operation_ClickWait, "更多选项");
		excute(Object_Text, Operation_ClickWait, "排序方式");
		excute(Object_Text, Operation_ClickWait, "按大小");
		excute(Object_Text, Operation_ClickWait, "升序");
		String [] size1=new String[3];
		for (int i = 0; i < 3; i++) {
			String str=(String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_msg",String.valueOf(i));
			size1[i]=str;
		}
		Assert.assertTrue(FileExplorerCommon.isSortedBySize(size1));
		excute(Object_Description, Operation_ClickWait, "更多选项");
		excute(Object_Text, Operation_ClickWait, "排序方式");
		excute(Object_Text, Operation_ClickWait, "按大小");
		excute(Object_Text, Operation_ClickWait, "降序");
		String [] size2=new String[3];
		for (int i = 0; i < 3; i++) {
			String str=(String)excute(Object_ResIdInstance, Operation_GetText, "com.sprd.fileexplorer:id/file_item_list_msg",String.valueOf(i));
			size2[i]=str;
		}
		Assert.assertTrue(FileExplorerCommon.isSortedBySize(size2,true));
	}
	/**
	 * 选择存储卡点击进入储存状态
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_200() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_ResourceId, Operation_ClickWait, "android:id/text1");//点击选择查看列表
		excute(Object_Text, Operation_ClickWait, "存储卡");
		excute(Object_Description, Operation_ClickWait, "更多选项");
		excute(Object_Text, Operation_ClickWait, "存储状态");
		check(Object_Text, Operation_checkExist, "存储设置");
		
	}
	/**
	 * 选择存储卡进入设置页面
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_201() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_ResourceId, Operation_ClickWait, "android:id/text1");//点击选择查看列表
		excute(Object_Text, Operation_ClickWait, "存储卡");
		excute(Object_Description, Operation_ClickWait, "更多选项");
		excute(Object_Text, Operation_ClickWait, "设置");
		check(Object_Text, Operation_checkExist, "设置");
		
	}
	/**
	 * 进入设置页面,查看显示隐藏文件默认状态
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_202() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_Description, Operation_ClickWait, "更多选项");
		excute(Object_Text, Operation_ClickWait, "设置");
		check(Object_ResourceId, Operation_CheckedFalse, "android:id/checkbox");
		
	}
	/**
	 * 进入设置页面，勾选显示隐藏文件
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_203() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_Description, Operation_ClickWait, "更多选项");
		excute(Object_Text, Operation_ClickWait, "设置");
		if (!(Boolean)excute(Object_ResourceId, Operation_IsChecked, "android:id/checkbox")) {
			excute(Object_ResourceId, Operation_ClickWait, "android:id/checkbox");
		}
		check(Object_ResourceId, Operation_CheckedTrue, "android:id/checkbox");
		excute(Object_Device, Operation_PressBack);
		excute(Object_ResourceId, Operation_ClickWait, "android:id/text1");//点击选择查看列表
		excute(Object_Text, Operation_ClickWait, "存储卡");
		excute(Object_Text, Operation_ClickWait, "FileExplorer_SD");
		check(Object_Text, Operation_checkExist, ".hiddenfiletest");
		//清场
		excute(Object_Description, Operation_ClickWait, "更多选项");
		excute(Object_Text, Operation_ClickWait, "设置");
		excute(Object_ResourceId, Operation_ClickWait, "android:id/checkbox");
	}
	/**
	 * 打开文件管理器，输入关键字test，搜索范围为全部，点击搜索
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_204() throws UiObjectNotFoundException, RemoteException 
	{
		//前提
		FileExplorerCommon.ClearSearchType();
		//主体
		excute(Object_Description, Operation_ClickWait, "搜索");
		excute(Object_ResourceId, Operation_SetText, "com.sprd.fileexplorer:id/search_view","test");
		excute(Object_Text, Operation_ClickWait, "搜索范围:");
		excute(Object_Text, Operation_ClickWait, "全部");
		excute(Object_ResourceId, Operation_ClickWait, "com.sprd.fileexplorer:id/fragment_search_type_image");
		excute(Object_Device, Operation_PressEnter);
		excute(Object_Text, Operation_WaitForExists, "搜索结果", "10000");
		check(Object_Text, Operation_checkExist, "testpicture.jpg");
		check(Object_Text, Operation_checkExist, "testpicture-sdcard.jpg");
		excute(Object_Device, Operation_PressBack);
		excute(Object_ResourceId, Operation_ClickWait, "com.sprd.fileexplorer:id/fragment_search_type_vedio");
		excute(Object_ResourceId, Operation_ClickWait, "com.sprd.fileexplorer:id/fragment_search_type_audio");
		excute(Object_Text, Operation_ClickWait, "test");
		excute(Object_Device, Operation_PressEnter);
		excute(Object_Text, Operation_WaitForExists, "搜索结果", "10000");
		String [] str2=new String[]{"testpicture.jpg","testvideo.3gp","testmusic.mp3","testpicture-sdcard.jpg","testvideo-sdcard.3gp","testmusic-sdcard.mp3"};
		for (int i = 0; i < str2.length; i++) {
			check(Object_Text, Operation_checkExist, str2[i]);
		}
		excute(Object_Device, Operation_PressBack);
		excute(Object_ResourceId, Operation_ClickWait, "com.sprd.fileexplorer:id/fragment_search_type_document");
		excute(Object_ResourceId, Operation_ClickWait, "com.sprd.fileexplorer:id/fragment_search_type_apks");
		excute(Object_ResourceId, Operation_ClickWait, "com.sprd.fileexplorer:id/fragment_search_type_other");
		excute(Object_Text, Operation_ClickWait, "test");
		excute(Object_Device, Operation_PressEnter);
		excute(Object_Text, Operation_WaitForExists, "搜索结果", "10000");
		String [] str1=new String[]{"testpicture.jpg","testmusic.mp3","testapk.apk","testvideo.3gp","testdocument.txt","testfile.rar","testpicture-sdcard.jpg",
				                    "testmusic-sdcard.mp3","testapk-sdcard.apk","testvideo-sdcard.3gp","testdocument-sdcard.txt","testfile-sdcard.rar"};
		for (int i = 0; i < str1.length; i++) {
			check(Object_TextScroll, Operation_checkExist, str1[i],"vertical");
		}
	}
	/**
	 * 打开文件管理器，输入关键字test，搜索范围为手机，点击搜索
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_205() throws UiObjectNotFoundException, RemoteException 
	{	
		//前提
		FileExplorerCommon.ClearSearchType();
		//主体
		excute(Object_Description, Operation_ClickWait, "搜索");
		excute(Object_ResourceId, Operation_SetText, "com.sprd.fileexplorer:id/search_view","test");
		excute(Object_Text, Operation_ClickWait, "搜索范围:");
		excute(Object_Text, Operation_ClickWait, "手机");
		excute(Object_ResourceId, Operation_ClickWait, "com.sprd.fileexplorer:id/fragment_search_type_image");
		excute(Object_Device, Operation_PressEnter);
		excute(Object_Text, Operation_WaitForExists, "搜索结果", "10000");
		check(Object_Text, Operation_checkExist, "testpicture.jpg");
		excute(Object_Device, Operation_PressBack);
		excute(Object_ResourceId, Operation_ClickWait, "com.sprd.fileexplorer:id/fragment_search_type_vedio");
		excute(Object_ResourceId, Operation_ClickWait, "com.sprd.fileexplorer:id/fragment_search_type_audio");
		excute(Object_Text, Operation_ClickWait, "test");
		excute(Object_Device, Operation_PressEnter);
		excute(Object_Text, Operation_WaitForExists, "搜索结果", "10000");
		check(Object_Text, Operation_checkExist, "testpicture.jpg");
		check(Object_Text, Operation_checkExist, "testvideo.3gp");
		check(Object_Text, Operation_checkExist, "testmusic.mp3");
		excute(Object_Device, Operation_PressBack);
		excute(Object_ResourceId, Operation_ClickWait, "com.sprd.fileexplorer:id/fragment_search_type_document");
		excute(Object_ResourceId, Operation_ClickWait, "com.sprd.fileexplorer:id/fragment_search_type_apks");
		excute(Object_ResourceId, Operation_ClickWait, "com.sprd.fileexplorer:id/fragment_search_type_other");
		excute(Object_Text, Operation_ClickWait, "test");
		excute(Object_Device, Operation_PressEnter);
		excute(Object_Text, Operation_WaitForExists, "搜索结果", "10000");
		String [] str1=new String[]{"testpicture.jpg","testmusic.mp3","testapk.apk","testvideo.3gp","testdocument.txt","testfile.rar"};
		for (int i = 0; i < str1.length; i++) {
			check(Object_Text, Operation_checkExist, str1[i]);
		}
	}
	/**
	 * 打开文件管理器，输入关键字test，搜索范围为存储卡，点击搜索
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_206() throws UiObjectNotFoundException, RemoteException 
	{	
		//前提
		FileExplorerCommon.ClearSearchType();
		//主体
		excute(Object_Description, Operation_ClickWait, "搜索");
		excute(Object_ResourceId, Operation_SetText, "com.sprd.fileexplorer:id/search_view","test");
		excute(Object_Text, Operation_ClickWait, "搜索范围:");
		excute(Object_Text, Operation_ClickWait, "存储卡");
		excute(Object_ResourceId, Operation_ClickWait, "com.sprd.fileexplorer:id/fragment_search_type_image");
		excute(Object_Device, Operation_PressEnter);
		excute(Object_Text, Operation_WaitForExists, "搜索结果", "10000");
		check(Object_Text, Operation_checkExist, "testpicture-sdcard.jpg");
		excute(Object_Device, Operation_PressBack);
		excute(Object_ResourceId, Operation_ClickWait, "com.sprd.fileexplorer:id/fragment_search_type_vedio");
		excute(Object_ResourceId, Operation_ClickWait, "com.sprd.fileexplorer:id/fragment_search_type_audio");
		excute(Object_Text, Operation_ClickWait, "test");
		excute(Object_Device, Operation_PressEnter);
		excute(Object_Text, Operation_WaitForExists, "搜索结果", "10000");
		check(Object_Text, Operation_checkExist, "testpicture-sdcard.jpg");
		check(Object_Text, Operation_checkExist, "testvideo-sdcard.3gp");
		check(Object_Text, Operation_checkExist, "testmusic-sdcard.mp3");
		excute(Object_Device, Operation_PressBack);
		excute(Object_ResourceId, Operation_ClickWait, "com.sprd.fileexplorer:id/fragment_search_type_document");
		excute(Object_ResourceId, Operation_ClickWait, "com.sprd.fileexplorer:id/fragment_search_type_apks");
		excute(Object_ResourceId, Operation_ClickWait, "com.sprd.fileexplorer:id/fragment_search_type_other");
		excute(Object_Text, Operation_ClickWait, "test");
		excute(Object_Device, Operation_PressEnter);
		excute(Object_Text, Operation_WaitForExists, "搜索结果", "10000");
		String [] str1=new String[]{"testpicture-sdcard.jpg","testmusic-sdcard.mp3","testapk-sdcard.apk","testvideo-sdcard.3gp","testdocument-sdcard.txt","testfile-sdcard.rar"};
		for (int i = 0; i < str1.length; i++) {
			check(Object_Text, Operation_checkExist, str1[i]);
		}
		
	
	}
	/**
	 * 清除搜索框里的“test”并检查
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_207() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_Description, Operation_ClickWait, "搜索");
		excute(Object_ResourceId, Operation_SetText, "com.sprd.fileexplorer:id/search_view","test");
		excute(Object_ResourceId, Operation_ClickWait, "com.sprd.fileexplorer:id/clear_all_img");
		check(Object_ResIdText, Operation_checkNoExist, "com.sprd.fileexplorer:id/search_view","test");
	}
	/**
	 * 打开文件管理器，输入关键字test，搜索范围为存储卡，点击搜索，
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_208() throws UiObjectNotFoundException, RemoteException 
	{	
		//前提
		FileExplorerCommon.ClearSearchType();
		//主体
		excute(Object_Description, Operation_ClickWait, "搜索");
		excute(Object_ResourceId, Operation_SetText, "com.sprd.fileexplorer:id/search_view","test");
		excute(Object_Text, Operation_ClickWait, "搜索范围:");
		excute(Object_Text, Operation_ClickWait, "存储卡");
		excute(Object_ResourceId, Operation_ClickWait, "com.sprd.fileexplorer:id/fragment_search_type_image");
		excute(Object_ResourceId, Operation_ClickWait, "com.sprd.fileexplorer:id/fragment_search_type_vedio");
		excute(Object_ResourceId, Operation_ClickWait, "com.sprd.fileexplorer:id/fragment_search_type_audio");
		excute(Object_ResourceId, Operation_ClickWait, "com.sprd.fileexplorer:id/fragment_search_type_document");
		excute(Object_ResourceId, Operation_ClickWait, "com.sprd.fileexplorer:id/fragment_search_type_apks");
		excute(Object_ResourceId, Operation_ClickWait, "com.sprd.fileexplorer:id/fragment_search_type_other");
		excute(Object_Device, Operation_PressEnter);
		excute(Object_Text, Operation_WaitForExists, "搜索结果", "10000");
		excute(Object_Text, Operation_ClickWait, "testpicture-sdcard.jpg");
		excute(Object_Description, Operation_ClickWait, "更多选项");
		check(Object_Text, Operation_checkExist, "播放幻灯片");
		excute(Object_Device, Operation_PressBack);
		excute(Object_Device, Operation_PressBack);
		excute(Object_Text, Operation_ClickWait, "testmusic-sdcard.mp3");
		check(Object_ResourceId, Operation_checkExist, "com.android.music:id/progress");
		excute(Object_Device, Operation_PressBack);
		excute(Object_Text, Operation_ClickWait, "testapk-sdcard.apk");
		check(Object_Text, Operation_checkExist, "要安装此应用吗？它将获得以下权限：");
		excute(Object_Device, Operation_PressBack);
		excute(Object_Text, Operation_ClickWait, "testvideo-sdcard.3gp");
		excute(Object_Description, Operation_ClickWait, "更多选项");
		check(Object_Text, Operation_checkExist, "声道设置");
		excute(Object_Device, Operation_PressBack);
		excute(Object_Device, Operation_PressBack);
		excute(Object_Text, Operation_ClickWait, "testdocument-sdcard.txt");
		check(Object_Text, Operation_checkExist, "testdocument-sdcard.txt");
	}	
}
