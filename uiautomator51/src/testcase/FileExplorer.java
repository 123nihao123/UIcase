package testcase;


//import org.junit.AfterClass;  
//import org.junit.BeforeClass;

import static framework.data.ObjectType.*;
import static framework.data.OperationType.*;
import static framework.data.ResIdTextAndDesc.*;
import static framework.excute.Excute.*;
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
		FileExplorerCommon.Longclickmenu("重命名");
		excute(Object_ResourceId, Operation_SetText, "com.sprd.fileexplorer:id/name_editor", "Musicname");
		excute(Object_Text, Operation_ClickWait, "确定");
		check(Object_Text, Operation_checkExist, "Musicname");
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
	*长按存储卡下条目，弹出功能菜单 
	*/
	public static void test_177() 
	{
		//主体
		FileExplorerCommon.longclickiteminSD();
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
		FileExplorerCommon.longclickiteminSD();
		excute(Object_Text,Operation_ClickWait,"复制");
		check(Object_Text,Operation_checkExist,"选择存储位置");
	}
	
	/**
	 * 在功能菜单上，点击剪切
	 */
	public static void test_179() 
	{
		//主体
		FileExplorerCommon.longclickiteminSD();
		excute(Object_Text,Operation_ClickWait,"剪切");
		check(Object_Text,Operation_checkExist,"选择存储位置");
	}
	
	/**
	 * 在功能菜单上，点击删除
	 */
	public static void test_180() 
	{
		//主体
		FileExplorerCommon.longclickiteminSD();
		excute(Object_Text,Operation_ClickWait,"删除");
		check(Object_Text,Operation_checkExist,"要删除所选内容吗？");
	}
	
	/**
	 * 在功能菜单上，点击清空
	 */
	public static void test_181() 
	{
		//主体
		FileExplorerCommon.longclickiteminSD();
		excute(Object_Text,Operation_ClickWait,"清空");
		check(Object_Text,Operation_checkExist,"是否删除当前文件夹中所有文件?");
	}
	
	/**
	 * 在功能菜单上，点击重命名
	 */
	public static void test_182() 
	{
		//前提
		FileExplorerCommon.cometoSD();
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
		FileExplorerCommon.longclickiteminSD();
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
		FileExplorerCommon.clickmenuinSD();
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
		FileExplorerCommon.cometoSD();
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
		FileExplorerCommon.cometoSD();
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
		FileExplorerCommon.clickmenuinSD();
		excute(Object_Text, Operation_ClickWait,"选择多个");
		check(Object_Text,Operation_checkExist,"选择全部");
	}
}
