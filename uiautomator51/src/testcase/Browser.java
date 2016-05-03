package testcase;

import static framework.data.ObjectType.*;
import static framework.data.OperationType.*;
import static framework.data.ResIdTextAndDesc.*;
import static framework.excute.Excute.*;
import junit.framework.Assert;
import android.graphics.Rect;
import android.os.RemoteException;
import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import framework.common.BrowserCommon;
import framework.common.CameraCommon;
import framework.common.DeviceCommon;

public class Browser extends UiAutomatorTestCase
{
	@Override
	protected void setUp() throws UiObjectNotFoundException, RemoteException 
    {
		System.out.println("Enter the setUp!!!");	
		excute(Object_Device, Operation_WakeUp);
		DeviceCommon.unLock();	
		ClearBackgroundApp();
		Wait(1000);
		DeviceCommon.enterApp(Devices_Desc_Browser);
   }
	@Override
	protected void tearDown() throws UiObjectNotFoundException, RemoteException 
    {
    }
	
	/**
	 * 进入浏览器
	 */
	public static void test_000()
	{
		//主体
		  excute(Object_Description, Operation_WaitForExists, "更多选项", "10000");
		  excute(Object_Description, Operation_ClickWait, "更多选项");
		  excute(Object_TextScroll, Operation_ClickWait, "设置", "vertical");
		  excute(Object_Text, Operation_ClickWait,"常规");
		  excute(Object_Text, Operation_ClickWait,"设置主页");
		  excute(Object_Text, Operation_ClickWait,"空白页");
		  excute(Object_Device, Operation_PressBack);
		  excute(Object_Device, Operation_PressBack);
		  excute(Object_ResourceId,Operation_ClickWait,"com.android.browser:id/tabs");
		  excute(Object_ResourceId,Operation_ClickWait,"com.android.browser:id/closetab");  
	}
	/**
	 * 进入浏览器
	 */
	public static void test_001()
	{
		//主体
		check(Object_ResourceId,Operation_checkExist,"com.android.browser:id/url");
	}
	
	/**
	 * 进入www.baidu.com
	 */
	public static void test_002()
	{
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"com.android.browser:id/url");
		excute(Object_ResourceId,Operation_SetText,"com.android.browser:id/url","www.baidu.com");
		excute(Object_Device, Operation_PressEnter);
		excute(Object_Description,Operation_WaitForExists,"百度一下,你就知道","50000");
		check(Object_Description,Operation_checkExist,"百度一下,你就知道");
	}
	
	/**
	 * 进入www.baidu.com,清空地址栏
	 */
	public static void test_003()
	{
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"com.android.browser:id/url");
		excute(Object_ResourceId,Operation_SetText,"com.android.browser:id/url","www.baidu.com");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.browser:id/clear");
		check(Object_ResIdText,Operation_checkExist,"com.android.browser:id/url","搜索或输入网址");
	}
	
	/**
	 * 进入www.baidu.com,刷新
	 */
	public static void test_004()
	{
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"com.android.browser:id/url");
		excute(Object_ResourceId,Operation_SetText,"com.android.browser:id/url","www.baidu.com");
		excute(Object_Device, Operation_PressEnter);
		excute(Object_Description,Operation_WaitForExists,"百度一下,你就知道","50000");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.browser:id/url");
		excute(Object_Description,Operation_ClickWait,"刷新网页");
		excute(Object_Description,Operation_WaitForExists,"百度一下,你就知道","50000");
		check(Object_Description,Operation_checkExist,"百度一下,你就知道");
	}
	
	/**
	 * 进入www.baidu.com,返回前一页
	 * @throws UiObjectNotFoundException 
	 */
	public static void test_005() throws UiObjectNotFoundException
	{
		//前提
		excute(Object_ResourceId,Operation_ClickWait,"com.android.browser:id/tabs");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.browser:id/closetab");
		//主体
		DeviceCommon.enterApp(Devices_Desc_Browser);
		excute(Object_ResourceId,Operation_ClickWait,"com.android.browser:id/url");
		excute(Object_ResourceId,Operation_SetText,"com.android.browser:id/url","www.baidu.com");
		excute(Object_Device, Operation_PressEnter);
		excute(Object_Description,Operation_WaitForExists,"百度一下,你就知道","50000");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.browser:id/back");
		excute(Object_Text,Operation_WaitForExists,"about:blank","50000");
		check(Object_Description,Operation_checkNoExist,"百度一下,你就知道");
	}
	
	/**
	 * 进入www.baidu.com,跳转下一页
	 * @throws UiObjectNotFoundException 
	 */
	public static void test_006() throws UiObjectNotFoundException
	{
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"com.android.browser:id/url");
		excute(Object_ResourceId,Operation_SetText,"com.android.browser:id/url","www.baidu.com");
		excute(Object_Device, Operation_PressEnter);
		excute(Object_Description,Operation_WaitForExists,"百度一下,你就知道","50000");
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"com.android.browser:id/url");
		excute(Object_ResourceId,Operation_SetText,"com.android.browser:id/url","www.meituan.com");
		excute(Object_Device, Operation_PressEnter);
		excute(Object_ResourceId,Operation_WaitForExists,"com.android.browser:id/favicon","50000");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.browser:id/back");
		excute(Object_Description,Operation_WaitForExists,"百度一下,你就知道","50000");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.browser:id/forward");
		excute(Object_ResourceId,Operation_WaitForExists,"com.android.browser:id/favicon","50000");
		check(Object_Description,Operation_checkNoExist,"百度一下,你就知道");
	}
	
	/**
	 * 进入添加tab页面
	 */
	public static void test_007()
	{
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"com.android.browser:id/tabs");
		check(Object_ResourceId,Operation_checkExist,"com.android.browser:id/newtab");
	}
	
	/**
	 * 添加tab页面
	 */
	public static void test_008()
	{
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"com.android.browser:id/tabs");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.browser:id/newtab");
		excute(Object_Text,Operation_WaitForExists,"about:blank","50000");
		check(Object_ResIdText,Operation_checkExist,"com.android.browser:id/tabcount","2");
		//清场
		excute(Object_ResourceId,Operation_ClickWait,"com.android.browser:id/tabs");
		if ((Boolean)excute(Object_ResourceId,Operation_Exists,"com.android.browser:id/closetab"))
		{
			excute(Object_ResIdInstance,Operation_ClickWait,"com.android.browser:id/closetab","1");
		}
	}
	
	/**
	 * 添加tab页面,删除一个窗口
	 */
	public static void test_009()
	{
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"com.android.browser:id/tabs");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.browser:id/newtab");
		excute(Object_Text,Operation_WaitForExists,"about:blank","50000");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.browser:id/tabs");
		excute(Object_ResIdInstance,Operation_ClickWait,"com.android.browser:id/closetab","1");
		excute(Object_Device, Operation_PressBack);
		check(Object_ResIdText,Operation_checkExist,"com.android.browser:id/tabcount","1");
	}
	
	/**
	 * 进入书签
	 */
	public static void test_010()
	{
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"com.android.browser:id/tabs");
		excute(Object_Description,Operation_ClickWait,"书签");
		check(Object_Text,Operation_checkExist,"书签和历史");
	}
	
	/**
	 *  添加tab页面，弹出功能下拉菜单，菜单上有 功能项：退出、主页、历史记录、设置
	 */
	public static void test_011()
	{
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"com.android.browser:id/tabs");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		check(Object_Text,Operation_checkExist,"退出");
		check(Object_Text,Operation_checkExist,"主页");
		check(Object_Text,Operation_checkExist,"历史记录");
		check(Object_Text,Operation_checkExist,"设置");
	}
	
	/**
	 *  添加tab页面，弹出功能下拉菜单：退出
	 */
	public static void test_012()
	{
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"com.android.browser:id/tabs");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"退出");
		check(Object_ResourceId,Operation_checkNoExist,"com.android.browser:id/newtab");
	}
	
	/**
	 *  添加tab页面，弹出功能下拉菜单：主页
	 */
	public static void test_013()
	{
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"com.android.browser:id/tabs");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"主页");
		check(Object_ResIdText,Operation_checkExist,"com.android.browser:id/url","about:blank");
	}
	
	/**
	 *  添加tab页面，弹出功能下拉菜单：历史记录
	 */
	public static void test_014()
	{
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"com.android.browser:id/tabs");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"历史记录");
		check(Object_Text,Operation_checkExist,"今天");
	}
	
	/**
	 *  添加tab页面，弹出功能下拉菜单：设置
	 */
	public static void test_015()
	{
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"com.android.browser:id/tabs");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"设置");
		check(Object_Text,Operation_checkExist,"常规");
	}
	/**
	 * 点击收藏图标
	 */
	public static void test_016()
	{
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"com.android.browser:id/bookmarks");
		check(Object_Text,Operation_checkExist,"书签和历史");
	}
	/**
	 * 进入书签页
	 */
	public static void test_017()
	{
		//主体
		BrowserCommon.enterCollectionTab("书签");
		check(Object_Text,Operation_SelectedTrue,"书签");
	}
	/**
	 * 书签页更多（菜单）图标
	 */
	public static void test_018()
	{
		//主体
		BrowserCommon.enterCollectionTab("书签");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		check(Object_Text,Operation_checkExist,"添加书签");
		check(Object_Text,Operation_checkExist,"新建文件夹");
	}
	/**
	 * 点击添加书签
	 */
	public static void test_019()
	{
		//主体
		BrowserCommon.enterCollectionTab("书签");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"添加书签");
		check(Object_Text,Operation_checkExist,"将此页加为书签");
	}
	/**
	 * 添加书签，输入网址、名称，点击保存
	 */
	public static void test_020()
	{
		//主体
		BrowserCommon.enterCollectionTab("书签");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"添加书签");
		excute(Object_ResourceId,Operation_SetText,"com.android.browser:id/title","Baidu");
		excute(Object_ResourceId,Operation_SetText,"com.android.browser:id/address","www.baidu.com");
		excute(Object_Text,Operation_ClickWait,"确定");
		check(Object_ResIdText,Operation_checkExist,"com.android.browser:id/label","Baidu");
		//清场
		excute(Object_Text,Operation_LongClick,"Baidu");
		excute(Object_Text,Operation_ClickWait,"删除书签");
		excute(Object_Text,Operation_ClickWait,"确定");
	}
	/**
	 * 点击新建文件夹
	 */
	public static void test_021()
	{
		//主体
		BrowserCommon.enterCollectionTab("书签");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"新建文件夹");
		check(Object_ResIdText,Operation_checkExist,"com.android.browser:id/fake_title","新建文件夹");
	}
	/**
	 * 新建一个文件夹
	 */
	public static void test_022()
	{
		//主体
		BrowserCommon.enterCollectionTab("书签");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"新建文件夹");
		for(int i=0;i<=20;i++)
		{
			excute(Object_Device, Operation_PressDelete);
		}
		excute(Object_ResourceId,Operation_SetText,"com.android.browser:id/title","NewFolder");
		excute(Object_Text,Operation_ClickWait,"确定");
		check(Object_ResIdText,Operation_checkExist,"com.android.browser:id/label","NewFolder");
		//清场
		excute(Object_Text,Operation_LongClick,"NewFolder");
		excute(Object_Text,Operation_ClickWait,"删除文件夹");
		excute(Object_Text,Operation_ClickWait,"确定");
	}
	/**
	 * 长按书签
	 */
	public static void test_023()
	{
		//主体
		BrowserCommon.enterCollectionTab("书签");
		excute(Object_ResourceId,Operation_LongClick,"com.android.browser:id/label");
		String[] str={"打开","在新标签页中打开","编辑书签","向主屏幕添加快捷方式","分享链接","复制链接网址","删除书签","设置为主页"};
		CameraCommon.checkForExist(str);
	}
	/**
	 * 长按书签 打开
	 */
	public static void test_024()
	{
		//主体
		BrowserCommon.enterCollectionTab("书签");
		excute(Object_ResourceId,Operation_LongClick,"com.android.browser:id/label");
		excute(Object_Text,Operation_ClickWait,"打开");
		check(Object_ResourceId,Operation_checkExist,"com.android.browser:id/url");
	}
	/**
	 * 长按书签 在新标签页中打开
	 */
	public static void test_025()
	{
		//主体
		String tabCount1=(String)excute(Object_ResourceId,Operation_GetText,"com.android.browser:id/tabcount");
		BrowserCommon.enterCollectionTab("书签");
		excute(Object_ResourceId,Operation_LongClick,"com.android.browser:id/label");
		excute(Object_Text,Operation_ClickWait,"在新标签页中打开");
		String tabCount2=(String)excute(Object_ResourceId,Operation_GetText,"com.android.browser:id/tabcount");
		Assert.assertFalse(tabCount1.equals(tabCount2));
	}
	/**
	 * 长按书签 编辑书签
	 */
	public static void test_026()
	{
		//主体
		BrowserCommon.enterCollectionTab("书签");
		excute(Object_ResourceId,Operation_LongClick,"com.android.browser:id/label");
		excute(Object_Text,Operation_ClickWait,"编辑书签");
		check(Object_ResIdText,Operation_checkExist,"com.android.browser:id/fake_title","编辑书签");
	}
	/**
	 * 长按书签 向主屏幕添加快捷方式
	 */
	public static void test_027()
	{
		//主体
		BrowserCommon.enterCollectionTab("书签");
		String tabName=(String)excute(Object_ResourceId,Operation_GetText,"com.android.browser:id/label");
		excute(Object_ResourceId,Operation_LongClick,"com.android.browser:id/label");
		excute(Object_Text,Operation_ClickWait,"向主屏幕添加快捷方式");
		excute(Object_Device,Operation_PressHome);
		excute(Object_Text,Operation_WaitForExists,tabName,"3000");
		check(Object_Text,Operation_checkExist,tabName);
		//清场
		Rect bounds = (Rect) excute(Object_Text, Operation_GetBounds, tabName);
	    String x = Integer.toString(bounds.centerX());
		String y = Integer.toString(bounds.centerY());
		int width=UiDevice.getInstance().getDisplayWidth();
		excute(Object_Device, Operation_DiviceDrag, x, y,String.valueOf(width/2),"0", "10");
	}
	/**
	 * 长按书签 分享链接
	 */
	public static void test_028()
	{
		//主体
		BrowserCommon.enterCollectionTab("书签");
		excute(Object_ResourceId,Operation_LongClick,"com.android.browser:id/label");
		excute(Object_Text,Operation_ClickWait,"分享链接");
		check(Object_Text,Operation_checkExist,"分享方式");
	}
	/**
	 * 长按书签 复制链接网址
	 */
	public static void test_029()
	{
		//主体
		BrowserCommon.enterCollectionTab("书签");
		excute(Object_ResourceId,Operation_LongClick,"com.android.browser:id/label");
		String str=(String)excute(Object_ResourceId,Operation_GetText,"com.android.browser:id/url");
		excute(Object_Text,Operation_ClickWait,"复制链接网址");
		excute(Object_Device,Operation_PressBack);
	}
	/**
	 * 长按书签 删除书签
	 */
	public static void test_030()
	{
		//前提 添加一个标签
		BrowserCommon.enterCollectionTab("书签");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"添加书签");
		excute(Object_ResourceId,Operation_SetText,"com.android.browser:id/title","Baidu");
		excute(Object_ResourceId,Operation_SetText,"com.android.browser:id/address","www.baidu.com");
		excute(Object_Text,Operation_ClickWait,"确定");
		if((Boolean)excute(Object_Text,Operation_Exists,"目标目录存在一个相同名称或网址的书签，是否覆盖？"))
			excute(Object_Text,Operation_ClickWait,"确定");
		//主体
		excute(Object_Text,Operation_LongClick,"Baidu");
		excute(Object_Text,Operation_ClickWait,"删除书签");
		excute(Object_Text,Operation_ClickWait,"取消");
		check(Object_Text,Operation_checkExist,"Baidu");
		excute(Object_Text,Operation_LongClick,"Baidu");
		excute(Object_Text,Operation_ClickWait,"删除书签");
		excute(Object_Text,Operation_ClickWait,"确定");
		check(Object_Text,Operation_checkNoExist,"Baidu");
	}
	/**
	 * 长按书签 设置为主页
	 */
	public static void test_031()
	{
		//主体
		BrowserCommon.enterCollectionTab("书签");
		excute(Object_ResourceId,Operation_LongClick,"com.android.browser:id/label");
		String urlName=(String)excute(Object_ResourceId,Operation_GetText,"com.android.browser:id/url");
		excute(Object_Text,Operation_ClickWait,"设置为主页");
		excute(Object_Device,Operation_PressBack);
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_TextScroll,Operation_ClickWait,"设置","vertical");
		excute(Object_Text,Operation_ClickWait,"常规");	
		check(Object_ResIdContainsText,Operation_checkExist,"android:id/summary",urlName);	
	}
	/**
	 * 查看页面上的历史记录tab
	 */
	public static void test_032()
	{
		//主体
		BrowserCommon.enterCollectionTab("历史记录");
		check(Object_Text,Operation_SelectedTrue,"历史记录");	
	}
	/**
	 * 选择一条历史记录
	 */
	public static void test_033()
	{
		//主体
		BrowserCommon.enterCollectionTab("历史记录");
		excute(Object_Text,Operation_ClickWait,"今天");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.browser:id/url");
		check(Object_ResourceId,Operation_checkExist,"com.android.browser:id/tabs");	
	}
	/**
	 * 收藏一条记录
	 */
	public static void test_034()
	{
		//主体
		BrowserCommon.enterCollectionTab("历史记录");
		excute(Object_Text,Operation_ClickWait,"今天");
		String name=(String)excute(Object_ResIdInstance,Operation_GetText,"com.android.browser:id/title","0");
		if((Boolean)excute(Object_ResIdInstance,Operation_IsChecked,"com.android.browser:id/star","0"))
		{
			excute(Object_ResIdInstance,Operation_ClickWait,"com.android.browser:id/star","0");
		    excute(Object_ResIdInstance,Operation_ClickWait,"com.android.browser:id/star","0");
		    excute(Object_Text,Operation_ClickWait,"确定");
		    excute(Object_Text,Operation_ClickWait,"书签");
		    check(Object_Text,Operation_checkExist,name);
		}		
		else
		{
			excute(Object_ResIdInstance,Operation_ClickWait,"com.android.browser:id/star","0");
			excute(Object_Text,Operation_ClickWait,"确定");
			excute(Object_Text,Operation_ClickWait,"书签");
			check(Object_Text,Operation_checkExist,name);	
			excute(Object_Text,Operation_ClickWait,"历史记录");
			excute(Object_ResIdInstance,Operation_ClickWait,"com.android.browser:id/star","0");
		}
	}
	/**
	 * 长按一条记录
	 */
	public static void test_035()
	{
		//主体
		BrowserCommon.enterCollectionTab("历史记录");
		excute(Object_Text,Operation_ClickWait,"今天");
		Boolean isStar=(Boolean)excute(Object_ResIdInstance,Operation_IsChecked,"com.android.browser:id/star","0");
		excute(Object_ResIdInstance,Operation_LongClick,"com.android.browser:id/url","0");
		if (isStar)
		{
			String[] str={"打开","在新标签页中打开","从书签中删除","分享链接","复制链接网址","从历史记录中删除","设置为主页"};
			CameraCommon.checkForExist(str);
		}
		else
		{
			String[] str={"打开","在新标签页中打开","保存到书签","分享链接","复制链接网址","从历史记录中删除","设置为主页"};
			CameraCommon.checkForExist(str);
		}	
	}
	/**
	 * 长按一个历史条目 打开
	 */
	public static void test_036()
	{
		//主体
		BrowserCommon.enterCollectionTab("历史记录");
		excute(Object_Text,Operation_ClickWait,"今天");
		excute(Object_ResourceId,Operation_LongClick,"com.android.browser:id/url");
		excute(Object_Text,Operation_ClickWait,"打开");
		check(Object_ResourceId,Operation_checkExist,"com.android.browser:id/tabs");
	}
	/**
	 * 长按一个历史条目 在新标签中打开
	 */
	public static void test_037()
	{
		//主体
		String tabCount1=(String)excute(Object_ResourceId,Operation_GetText,"com.android.browser:id/tabcount");
		BrowserCommon.enterCollectionTab("历史记录");
		excute(Object_Text,Operation_ClickWait,"今天");
		excute(Object_ResourceId,Operation_LongClick,"com.android.browser:id/url");
		excute(Object_Text,Operation_ClickWait,"在新标签页中打开");
		String tabCount2=(String)excute(Object_ResourceId,Operation_GetText,"com.android.browser:id/tabcount");
		Assert.assertFalse(tabCount1.equals(tabCount2));
	}
	/**
	 * 长按一个历史条目  保存到书签
	 */
	public static void test_038()
	{
		//主体
		BrowserCommon.enterCollectionTab("历史记录");
		excute(Object_Text,Operation_ClickWait,"今天");
		String name=(String)excute(Object_ResIdInstance,Operation_GetText,"com.android.browser:id/title","0");
		if((Boolean)excute(Object_ResIdInstance,Operation_IsChecked,"com.android.browser:id/star","0"))
			excute(Object_ResIdInstance,Operation_ClickWait,"com.android.browser:id/star","0");
		excute(Object_ResIdInstance,Operation_LongClick,"com.android.browser:id/url","0");
		excute(Object_Text,Operation_ClickWait,"保存到书签");
		check(Object_Text,Operation_checkExist,"将此页加为书签");
		excute(Object_Text,Operation_ClickWait,"确定");
		excute(Object_Text,Operation_ClickWait,"书签");
		check(Object_Text,Operation_checkExist,name);
	}
	/**
	 * 长按一个历史条目 分享链接
	 */
	public static void test_039()
	{
		//主体
		BrowserCommon.enterCollectionTab("历史记录");
		excute(Object_Text,Operation_ClickWait,"今天");
		excute(Object_ResourceId,Operation_LongClick,"com.android.browser:id/url");
		excute(Object_Text,Operation_ClickWait,"分享链接");
		check(Object_Text,Operation_checkExist,"分享方式");
	}
	/**
	 * 长按一个历史条目 复制链接网址
	 */
	public static void test_040()
	{
		//主体
		BrowserCommon.enterCollectionTab("历史记录");
		excute(Object_Text,Operation_ClickWait,"今天");
		excute(Object_ResourceId,Operation_LongClick,"com.android.browser:id/url");
		excute(Object_Text,Operation_ClickWait,"复制链接网址");	
	}
	/**
	 * 长按一个历史条目 删除
	 */
	public static void test_041()
	{
		//主体
		BrowserCommon.enterCollectionTab("历史记录");
		excute(Object_Text,Operation_ClickWait,"今天");
		String urlName=(String)excute(Object_ResourceId,Operation_GetText,"com.android.browser:id/url");
		excute(Object_ResourceId,Operation_LongClick,"com.android.browser:id/url");
		excute(Object_Text,Operation_ClickWait,"从历史记录中删除");
		check(Object_Text,Operation_checkNoExist,urlName);	
	}
	/**
	 * 长按一个历史条目 设置为主页
	 */
	public static void test_042()
	{
		//主体
		BrowserCommon.enterCollectionTab("历史记录");
		excute(Object_Text,Operation_ClickWait,"今天");
		String urlName=(String)excute(Object_ResourceId,Operation_GetText,"com.android.browser:id/url");
		excute(Object_ResourceId,Operation_LongClick,"com.android.browser:id/url");
		excute(Object_Text,Operation_ClickWait,"设置为主页");
		excute(Object_Device,Operation_PressBack);
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_TextScroll,Operation_ClickWait,"设置","vertical");
		excute(Object_Text,Operation_ClickWait,"常规");	
		check(Object_ResIdContainsText,Operation_checkExist,"android:id/summary",urlName);	
	}
	/**
	 * 保存的网页
	 */
	public static void test_043()
	{
		//主体
		BrowserCommon.enterCollectionTab("保存的网页");
		check(Object_Text,Operation_checkNoExist,"没有保存的网页。");
	}
	/**
	 * 长按一个保存的网页
	 */
	public static void test_044()
	{
		//主体
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_TextScroll,Operation_ClickWait,"保存以供离线阅读","vertical");
		excute(Object_ResourceId,Operation_LongClick,"com.android.browser:id/title");
		check(Object_Text,Operation_checkExist,"删除已保存的网页");
		//清场
		excute(Object_Text,Operation_ClickWait,"删除已保存的网页");
		excute(Object_Text,Operation_ClickWait,"确定");
	}
	/**
	 * 点击页面右上角的 更多（菜单）按钮
	 */
	public static void test_045()
	{
		//主体
		excute(Object_Description,Operation_ClickWait,"更多选项");
		String[] str={"停止","刷新","前进","退出","主页","历史记录","书签","保存到书签","分享网页","在网页上查找","请求桌面版网站","保存以供离线阅读","设置"};
		for (int i=0;i<str.length;i++)
			check(Object_TextScroll,Operation_checkExist,str[i],"vertical");
	}
	/**
	 * 点击页面右上角的更多按钮 停止
	 */
	public static void test_046()
	{
		//主体
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"停止");
		check(Object_ResourceId,Operation_checkNoExist,"com.android.browser:id/stop");
	}
	
	/**
	 * 点击页面右上角的 更多按钮 刷新
	 */
	public static void test_047()
	{
		//主体
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"刷新");
		check(Object_ResourceId,Operation_checkExist,"com.android.browser:id/stop");	
	}
	
	/**
	 * 点击页面右上角的更多按钮 前进
	 */
	public static void test_048()
	{
		//前提
		excute(Object_ResourceId,Operation_SetText,"com.android.browser:id/url","www.baidu.com");
		excute(Object_Device,Operation_PressEnter);
		excute(Object_Description,Operation_WaitForExists,"百度一下,你就知道","20000");
		excute(Object_Description,Operation_ClickWait," 新闻");
		excute(Object_Description,Operation_WaitForExists,"百度新闻","20000");
		excute(Object_Device,Operation_PressBack);
		//主体
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"前进");
		excute(Object_Description,Operation_WaitForExists,"百度新闻","20000");
		check(Object_Description,Operation_checkExist,"百度新闻");
	}
	/**
	 * 点击页面右上角的更多按钮 退出
	 */
	public static void test_049()
	{
		//主体
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"退出");
		check(Object_ResourceId,Operation_checkNoExist,"com.android.browser:id/url");
	}
	/**
	 * 点击页面右上角的更多按钮 主页
	 */
	public static void test_050()
	{
		//主体
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"主页");
		excute(Object_ResourceId,Operation_LongClick,"com.android.browser:id/url");
		String urlName1=(String)excute(Object_ResourceId,Operation_GetText,"com.android.browser:id/url");
		excute(Object_Device,Operation_PressBack);
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_TextScroll,Operation_ClickWait,"设置","vertical");
		excute(Object_Text,Operation_ClickWait,"常规");	
		String urlName2=(String)excute(Object_ResourceId,Operation_GetText,"android:id/summary");
		Assert.assertEquals(urlName1, urlName2);	
	}
	/**
	 * 点击页面右上角的更多按钮 历史记录
	 */
	public static void test_051()
	{
		//主体
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"历史记录");
		check(Object_Text,Operation_checkExist,"书签和历史");	
	}
	/**
	 * 点击页面右上角的更多按钮 分享网页
	 */
	public static void test_052()
	{
		//主体
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"分享网页");
		check(Object_Text,Operation_checkExist,"分享方式");	
	}
	/**
	 * 点击页面右上角的更多按钮  在网页上查找
	 */
	public static void test_053()
	{
		//主体
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"在网页上查找");
		check(Object_ResourceId,Operation_checkExist,"android:id/edit");
		excute(Object_ResourceId,Operation_SetText,"android:id/edit","abc");
		check(Object_ResourceId,Operation_checkExist,"android:id/matches");
	}
	/**
	 * 点击页面右上角的更多按钮  请求桌面版网站
	 */
	public static void test_054()
	{
		//主体
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_TextScroll,Operation_ClickWait,"请求桌面版网站","vertical");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_TextScroll,Operation_Exists,"请求桌面版网站","vertical");
		check(Object_ResourceId,Operation_CheckedTrue,"android:id/checkbox");
		//清场
		excute(Object_Text,Operation_ClickWait,"请求桌面版网站");
	}
	/**
	 * 点击页面右上角的更多按钮 保存以离线阅读
	 */
	public static void test_055()
	{
		//主体
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_TextScroll,Operation_ClickWait,"保存以供离线阅读","vertical");
		check(Object_Text,Operation_checkExist,"书签和历史");
		//清场
		excute(Object_ResourceId,Operation_LongClick,"com.android.browser:id/title");
		excute(Object_Text,Operation_ClickWait,"删除已保存的网页");
		excute(Object_Text,Operation_ClickWait,"确定");
	}
	/**
	 * 点击页面右上角的更多按钮 设置
	 */
	public static void test_056()
	{
		//主体
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_TextScroll,Operation_ClickWait,"设置","vertical");
		check(Object_Text,Operation_checkExist,"常规");	
	}
	/**
	 * 主页设置
	 */
	public static void test_057()
	{
		//主体
		BrowserCommon.settingMenu("常规");
		excute(Object_Text, Operation_ClickWait, "设置主页");
		check(Object_Text, Operation_checkExist, "当前页");
		check(Object_Text, Operation_checkExist, "空白页");
		check(Object_Text, Operation_checkExist, "默认页");
		check(Object_Text, Operation_checkExist, "页面导航");
		check(Object_Text, Operation_checkExist, "其他");
	}
	/**
	 * 当前页
	 */
	public static void test_058()
	{
		//主体
		BrowserCommon.settingMenu("常规");
		String hmoepage = (String) excute(Object_ResourceId, Operation_GetText, "android:id/summary");
		excute(Object_Text, Operation_ClickWait, "设置主页");
		excute(Object_Text, Operation_ClickWait, "当前页");
		excute(Object_Text, Operation_WaitForExists, hmoepage, "10000");
		check(Object_Text, Operation_checkExist, hmoepage);
	}
	/**
	 * 空白页
	 */
	public static void test_059()
	{
		//主体
		BrowserCommon.settingMenu("常规");
		excute(Object_Text, Operation_ClickWait, "设置主页");
		excute(Object_Text, Operation_ClickWait, "空白页");
		check(Object_Text, Operation_checkExist, "空白页");
	}
	/**
	 * 默认页
	 */
	public static void test_060()
	{
		//主体
		BrowserCommon.settingMenu("常规");
		excute(Object_Text, Operation_ClickWait, "设置主页");
		excute(Object_Text, Operation_ClickWait, "默认页");
		excute(Object_Text, Operation_WaitForExists, "http://www.wo.com.cn", "10000");
		check(Object_Text, Operation_checkExist, "http://www.wo.com.cn");
	}
	/**
	 * 页面导航
	 */
	public static void test_061()
	{
		//主体
		BrowserCommon.settingMenu("常规");
		excute(Object_Text, Operation_ClickWait, "设置主页");
		excute(Object_Text, Operation_ClickWait, "页面导航");
		excute(Object_Text, Operation_WaitForExists, "页面导航", "10000");
		check(Object_Text, Operation_checkExist, "页面导航");
	}
	/**
	 * 其它主页
	 */
	public static void test_062()
	{
		//主体
		BrowserCommon.settingMenu("常规");
		excute(Object_Text, Operation_ClickWait, "设置主页");
		excute(Object_Text, Operation_ClickWait, "其他");
		excute(Object_ResourceId, Operation_SetText, "android:id/custom", "www.baidu.com");
		excute(Object_Text, Operation_ClickWait, "确定");
		excute(Object_Text, Operation_WaitForExists, "http://www.baidu.com/", "10000");
		check(Object_Text, Operation_checkExist, "http://www.baidu.com/");
	}
	/**
	 * 清除缓存
	 */
	public static void test_063()
	{
		//主体
		BrowserCommon.PrivacySecurity("清除缓存");
	}
	/**
	 * 清除缓存
	 */
	public static void test_064()
	{
		//主体
		BrowserCommon.PrivacySecurity("清除历史记录");
	}
	/**
	 * 显示安全警告
	 */
	public static void test_065()
	{
		//主体
		BrowserCommon.settingMenu("隐私和安全");
		if(!(boolean) excute(Object_ResIdInstance, Operation_IsChecked, "android:id/checkbox", "0"))
			excute(Object_ResIdInstance, Operation_ClickWait, "android:id/checkbox", "0");
		excute(Object_ResIdInstance, Operation_ClickWait, "android:id/checkbox", "0");
		check(Object_ResIdInstance, Operation_CheckedFalse, "android:id/checkbox", "0");
		excute(Object_ResIdInstance, Operation_ClickWait, "android:id/checkbox", "0");
		check(Object_ResIdInstance, Operation_CheckedTrue, "android:id/checkbox", "0");
	}
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
