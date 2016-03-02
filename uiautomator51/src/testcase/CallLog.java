package testcase;

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
import framework.common.SettingCommon;

public class CallLog extends UiAutomatorTestCase
{
	@Override
	protected void setUp() throws UiObjectNotFoundException, RemoteException 
    {			
		System.out.println("Enter the setUp!!!");	
		excute(Object_Device, Operation_WakeUp);
		DeviceCommon.unLock();	
		ClearBackgroundApp();
		Wait(1000);
		DeviceCommon.enterApp(Devices_Desc_Call);
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"通话记录");
		excute(Object_Text,Operation_ClickWait,"全部");
   }
	     
	@Override
	protected void tearDown() throws UiObjectNotFoundException, RemoteException 
    {
    }
	/**
	 * 通话记录-从卡2打出电话
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_046() throws UiObjectNotFoundException, RemoteException 
	{	
		//前提
		ClearBackgroundApp();
		DeviceCommon.enterApp(Devices_Desc_Call);
		Rect ModArea = (Rect) excute(Object_ResourceId,Operation_GetBounds,"com.android.dialer:id/floating_action_button");
		int WideArea = ModArea.width();
		int x = ModArea.centerX();
		int y = ModArea.centerY();
		//主体
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"通话记录");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.dialer:id/primary_action_button");
		excute(Object_ResourceId, Operation_WaitForExists, "android:id/select_dialog_listview","10000");
		excute(Object_ResIdInstance,Operation_ClickWait,"com.android.dialer:id/label","1");
		Wait(10000);
		UiDevice.getInstance().click(x, y);
		
	}
	
	/**
	 * 通话记录-展开
	 * @throws UiObjectNotFoundException
	 */
	public static void test_047() throws UiObjectNotFoundException 
	{	
		//主体
		excute(Object_ResourceId, Operation_LongClick, "com.android.dialer:id/primary_action_view");
		check(Object_Text, Operation_checkExist, "复制到剪贴板");
		check(Object_Text, Operation_checkExist, "呼叫之前编辑号码");
				
	}
	
	
	/**
	 * 通话记录查看全部通话记录按钮
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException 
	 */
	public static void test_048() throws UiObjectNotFoundException, RemoteException 
	{	
		ClearBackgroundApp();
		DeviceCommon.enterApp(Devices_Desc_Call);
		excute(Object_Description,Operation_ClickWait,"最近");
		check(Object_TextScroll, Operation_checkExist, "查看全部通话记录","vertical");
		
	}
	
 	/**
 	 * 查看全部通话记录
 	 * @throws UiObjectNotFoundException
 	 * @throws RemoteException
 	 */
	public static void test_049() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		check(Object_Text,Operation_checkExist,"通话记录");
	}
	/**
	 * 进入通话详情
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_050() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/call_log_row");
		excute(Object_Text,Operation_ClickWait,"通话详情");
		check(Object_Text,Operation_checkExist,"通话详情");
	}
	/**
	 * 查看通话记录详情界面内容
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_051() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/call_log_row");
		excute(Object_Text,Operation_ClickWait,"通话详情");
		check(Object_Text,Operation_checkExist,"通话详情");
		check(Object_ResourceId,Operation_checkExist,"com.android.dialer:id/caller_name");
		check(Object_ResourceId,Operation_checkExist,"com.android.dialer:id/date");
		check(Object_ResourceId,Operation_checkExist,"com.android.dialer:id/duration");
	}
	
	/**
	 * 查看通话记录详情拨打电话
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_052() throws UiObjectNotFoundException, RemoteException 
	{
		//前提
		ClearBackgroundApp();
		Wait(1000);
		DeviceCommon.enterApp(Devices_Desc_Call);
		Rect ModArea = (Rect) excute(Object_ResourceId,Operation_GetBounds,"com.android.dialer:id/floating_action_button");
		int WideArea = ModArea.width();
		int x = ModArea.centerX();
		int y = ModArea.centerY();
		//主体
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"通话记录");
		excute(Object_Text,Operation_ClickWait,"全部");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/call_log_row");
		excute(Object_Text,Operation_ClickWait,"通话详情");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/call_back_button");
		excute(Object_ResourceId,Operation_WaitForExists,"com.android.dialer:id/label","10000");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/label");
		Wait(10000);
		UiDevice.getInstance().click(x, y);
	}
	/**
	 * 查看通话记录详情拨打IP电话
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_053() throws UiObjectNotFoundException, RemoteException 
	{
		//前提
		DeviceCommon.enterApp(Devices_Desc_Call);
		Rect ModArea = (Rect) excute(Object_ResourceId,Operation_GetBounds,"com.android.dialer:id/floating_action_button");
		int WideArea = ModArea.width();
		int x = ModArea.centerX();
		int y = ModArea.centerY();
		CallLogCommon.addIPCall();
		ClearBackgroundApp();
		DeviceCommon.enterApp(Devices_Desc_Call);
		//主体
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"通话记录");
		excute(Object_Text,Operation_ClickWait,"全部");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/call_log_row");
		excute(Object_Text,Operation_ClickWait,"通话详情");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/call_back_button");
		excute(Object_ResourceId,Operation_WaitForExists,"com.android.dialer:id/label","10000");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/label");
		Wait(10000);
		UiDevice.getInstance().click(x, y);
		//清场
		ClearBackgroundApp();
		DeviceCommon.enterApp(Devices_Desc_Call);
		CallLogCommon.removeIPCall();
	}
	/**
	 * 查看通话记录详情界面发送信息
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_054() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/call_log_row");
		excute(Object_Text,Operation_ClickWait,"通话详情");
		String Number=(String) excute(Object_ResourceId,Operation_GetText,"com.android.dialer:id/caller_name");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/message_icon");
		excute(Object_ResourceId,Operation_WaitForExists,"com.android.messaging:id/conversation_title","10000");
		check(Object_Text,Operation_checkExist,Number);
	}
	/**
	 * 查看通话记录详情界面添加联系人
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_055() throws UiObjectNotFoundException, RemoteException 
	{
		//前提
		ClearBackgroundApp();
		DeviceCommon.enterApp(Devices_Desc_PhoneBook);
		ContactCommon.BatchDelete("所有联系人");
		ClearBackgroundApp();
		DeviceCommon.enterApp(Devices_Desc_Call);
		//主体
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"通话记录");
		excute(Object_Text,Operation_ClickWait,"全部");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/call_log_row");
		excute(Object_Text,Operation_ClickWait,"通话详情");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/add_contact_icon");
		excute(Object_Text,Operation_WaitForExists,"选择联系人","10000");
		check(Object_Text,Operation_checkExist,"选择联系人");
	}
	/**
	 * 查看通话记录详情界面查看不存在添加联系人选项
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_056() throws UiObjectNotFoundException, RemoteException 
	{
		//前提
		ClearBackgroundApp();
		DeviceCommon.enterApp(Devices_Desc_PhoneBook);
		ContactCommon.BatchDelete("所有联系人");
		ClearBackgroundApp();
		DeviceCommon.enterApp(Devices_Desc_Call);
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"通话记录");
		excute(Object_Text,Operation_ClickWait,"全部");
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/call_log_row");
		excute(Object_Text,Operation_ClickWait,"通话详情");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/add_contact_icon");
		excute(Object_Text,Operation_WaitForExists,"选择联系人","10000");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"创建新联系人");
		excute(Object_Text,Operation_ClickWait,"本机");
		excute(Object_Text,Operation_SetText,"姓名","addname");
		excute(Object_ResIdDesc,Operation_ClickWait,"com.android.contacts:id/menu_save", "保存");
		check(Object_ResourceId,Operation_checkNoExist,"com.android.dialer:id/add_contact_icon");
		//清场
		ClearBackgroundApp();
		DeviceCommon.enterApp(Devices_Desc_PhoneBook);
		ContactCommon.BatchDelete("所有联系人");
	}
	/**
	 * 半屏查看联系人详情
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_057() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/quick_contact_photo");
		check(Object_ResourceId,Operation_checkExist,"com.android.contacts:id/third_icon");
	}
	/**
	 * 删除单条联系人
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_058() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/call_log_row");
		excute(Object_Text,Operation_ClickWait,"通话详情");
		String Number=(String) excute(Object_ResourceId,Operation_GetText,"com.android.dialer:id/caller_name");
		System.out.println(Number);
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/menu_remove_from_call_log");
		check(Object_Text,Operation_checkNoExist,Number);
	}
	/**
	 * 呼叫之前编辑，包含第60条
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_059() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/call_log_row");
		excute(Object_Text,Operation_ClickWait,"通话详情");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"呼叫之前编辑号码");
		excute(Object_ResourceId,Operation_WaitForExists,"com.android.dialer:id/dialpad_floating_action_button","10000");
		check(Object_ResourceId,Operation_checkExist,"com.android.dialer:id/dialpad_floating_action_button");
	}
	/**
	 * 未接电话
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_061() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"未接电话");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/call_log_row");
		excute(Object_Text,Operation_ClickWait,"通话详情");
		check(Object_Text,Operation_checkExist,"通话详情");
		check(Object_Text,Operation_checkExist,"未接电话");		
	}
	/**
	 * 已拨电话
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_062() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"已拨电话");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/call_log_row");
		excute(Object_Text,Operation_ClickWait,"通话详情");
		check(Object_Text,Operation_checkExist,"通话详情");
		check(Object_Text,Operation_checkExist,"外拨电话");
	}
	/**
	 * 已接电话
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_063() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"已接电话");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/call_log_row");
		excute(Object_Text,Operation_ClickWait,"通话详情");
		check(Object_Text,Operation_checkExist,"通话详情");
		check(Object_Text,Operation_checkExist,"来电");
	}
	/**
	 * 进入清除通话记录
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_064() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"清除通话记录");
		check(Object_Text,Operation_checkExist,"清除通话记录");
	}
	/**
	 * 查看全选清除通话记录
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_065() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"清除通话记录");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		check(Object_Text,Operation_checkExist,"全选");
	}
	/**
	 * 确认全选通话记录
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_066() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"清除通话记录");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"全选");
		int Num = ((Integer)(excute(Object_ResourceId, Operation_GetChildCount, "android:id/list"))).intValue();
		System.out.println(Num);
		for(int i=0; i<Num; i++)
		{
			check(Object_ResIdInstance,Operation_CheckedTrue,"com.android.dialer:id/call_icon",String.valueOf(i));
		}
	}
	/**
	 * 删除全部通话记录
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_167() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		CallLogCommon.deleteAllLog("全部");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"通话记录");
		excute(Object_Text,Operation_ClickWait,"全部");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		check(Object_Text,Operation_checkNoExist,"清除通话记录");
	}
	/**
	 * 确认单选删除通话记录
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_068() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"清除通话记录");
		excute(Object_ResIdInstance,Operation_ClickWait,"com.android.dialer:id/call_icon","0");
		int Num = ((Integer)(excute(Object_ResourceId, Operation_GetChildCount, "android:id/list"))).intValue();
		System.out.println(Num);
		for(int i=0; i<Num; i++)
		{
			check(Object_ResIdInstance,Operation_CheckedTrue,"com.android.dialer:id/call_icon","0");
			if(i>0){
				check(Object_ResIdInstance,Operation_CheckedFalse,"com.android.dialer:id/call_icon",String.valueOf(i));
			}
		}
	}
	/**
	 * 选择全部通话记录
	 * @throws UiObjectNotFoundException
	 */
	public static void test_069() throws UiObjectNotFoundException 
	{	
		//主体
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"清除通话记录");
		excute(Object_ResIdInstance,Operation_ClickWait,"com.android.dialer:id/call_icon","2");
		check(Object_ResIdInstance,Operation_CheckedTrue,"com.android.dialer:id/call_icon","2");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"全选");
		int Num = ((Integer)(excute(Object_ResourceId, Operation_GetChildCount, "android:id/list"))).intValue();
	    System.out.println(Num);
	    for(int i=0; i<Num; i++)
	    {
	     check(Object_ResIdInstance,Operation_CheckedTrue,"com.android.dialer:id/call_icon",String.valueOf(i));
	    }
	}
	/**
	 * 取消删除单条联系人
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_070() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"清除通话记录");
		String Number=(String) excute(Object_ResourceId,Operation_GetText,"com.android.dialer:id/line1");
		excute(Object_Text,Operation_ClickWait,Number);
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"删除全部选中的通话记录");
		excute(Object_Text,Operation_ClickWait,"取消");
		check(Object_Text,Operation_checkExist,Number);
	}
 	/**
 	 * 清除一条所选的通话记录
 	 * @throws UiObjectNotFoundException
 	 */
	public static void test_071() throws UiObjectNotFoundException 
	{	
		//主体
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"清除通话记录");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.dialer:id/call_icon");
		String Number= (String)excute(Object_ResourceId, Operation_GetText, "com.android.dialer:id/line1");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"删除全部选中的通话记录");
		excute(Object_Text,Operation_ClickWait,"确定");
		check(Object_Text, Operation_checkExist, Number);
	}	
}
