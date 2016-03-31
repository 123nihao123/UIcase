package testcase;

import static framework.data.ObjectType.*;
import static framework.data.OperationType.*;
import static framework.data.ResIdTextAndDesc.*;
import static framework.excute.Excute.*;
import android.os.RemoteException;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

import framework.common.DeviceCommon;
import framework.common.NoteCommon;

public class Note extends UiAutomatorTestCase
{
	@Override
	protected void setUp() throws UiObjectNotFoundException, RemoteException 
    {
		System.out.println("Enter the setUp!!!");	
		excute(Object_Device, Operation_WakeUp);
		DeviceCommon.unLock();	
		ClearBackgroundApp();
		Wait(1000);
		DeviceCommon.enterApp(Devices_Desc_Note);
		NoteCommon.deleteAll();
   }
	@Override
	protected void tearDown() throws UiObjectNotFoundException, RemoteException 
    {
    }
	/**
	 * 进入笔记本
	 */
	public static void test_001() 
	{
		//主体
		check(Object_Text, Operation_checkExist, "记事本");
	}
	/**
	 * 无笔记时查看页面显示
	 */
	public static void test_002() 
	{
		//主体
		check(Object_Text, Operation_checkExist, "设置新的便签，请点击创建便签");
	}
	/**
	 * 点击“+”进入添加界面
	 */
	public static void test_003() 
	{
		//主体
		excute(Object_Description,Operation_ClickWait,"新建便签");
		check(Object_Text, Operation_checkExist, "编辑标题");
	}
	/**
	 * 没有笔记时点击菜单按钮
	 */
	public static void test_004() 
	{
		//主体
		excute(Object_Device,Operation_PressMenu);
		check(Object_Text,Operation_checkExist,"新建文件夹");
	}
	/**
	 * 没有笔记时，新建文件夹
	 */
	public static void test_005() 
	{
		//主体
		excute(Object_Device,Operation_PressMenu);
		excute(Object_Text,Operation_ClickWait,"新建文件夹");
		excute(Object_ResourceId,Operation_SetText,"com.sprd.note:id/et_dialog_new_folder","FirstFolder");
		excute(Object_Text,Operation_ClickWait,"确定");
		check(Object_Text, Operation_checkExist, "FirstFolder(0)");
	}
	/**
	 * 有一条笔记的前提下点击菜单
	 */
	public static void test_006() 
	{
		//前提
		NoteCommon.createNote("Hi","Hello");
		//主体
		excute(Object_Device,Operation_PressMenu);
		check(Object_Text, Operation_checkExist, "新建文件夹");
		check(Object_Text, Operation_checkExist, "删除");
		check(Object_Text, Operation_checkExist, "删除全部");		
	}
	/**
	 * 有一条笔记的前提下点击菜单中的删除
	 */
	public static void test_007() 
	{
		//前提
		NoteCommon.createNote("Hi","Hello");
		//主体
		excute(Object_Device,Operation_PressMenu);
		excute(Object_Text, Operation_ClickWait, "删除");
		check(Object_Text, Operation_checkExist, "完成");		
	}
	/**
	 * 有一条笔记的前提下点击菜单中的删除全部
	 */
	public static void test_008() 
	{
		//前提
		NoteCommon.createNote("Hi","Hello");
		//主体
		excute(Object_Device,Operation_PressMenu);
		excute(Object_Text, Operation_ClickWait, "删除全部");
		check(Object_Text, Operation_checkExist, "确定删除全部条目?");		
	}
	/**
	 * 只有一个文件夹点击菜单选项
	 */
	public static void test_009() 
	{
		//前提
		NoteCommon.createFolder("Wenjian");
		//主体
		excute(Object_Device,Operation_PressMenu);
		check(Object_Text, Operation_checkExist, "新建文件夹");
		check(Object_Text, Operation_checkExist, "删除");
		check(Object_Text, Operation_checkExist, "删除全部");
	}
	/**
	 * 有一个笔记一个文件夹点击菜单按钮
	 */
	public static void test_010() 
	{
		//前提
		NoteCommon.createNote("Hi","Hello");
		NoteCommon.createFolder("Wenjian");
		//主体
		excute(Object_Device,Operation_PressMenu);
		check(Object_Text, Operation_checkExist, "新建文件夹");
		check(Object_Text, Operation_checkExist, "删除");
		check(Object_Text, Operation_checkExist, "删除全部");	
		check(Object_Text, Operation_checkExist, "移入文件夹");
	}
	/**
	 * 有一个笔记和一个文件夹时进行移动文件夹
	 */
	public static void test_011() 
	{
		//前提
		NoteCommon.createNote("Hi","Hello");
		NoteCommon.createFolder("Wenjianjia");
		//主体
		excute(Object_Device,Operation_PressMenu);
		excute(Object_Text, Operation_ClickWait, "移入文件夹");
		excute(Object_ResourceId, Operation_ClickWait, "com.sprd.note:id/check");
		excute(Object_Text, Operation_ClickWait, "完成");
		excute(Object_Text, Operation_ClickWait, "Wenjianjia");
		check(Object_Text, Operation_checkNoExist, "Hi");
		check(Object_Text, Operation_checkExist, "Wenjianjia(1)");
	}
	/**
	 * 有一个笔记和一个文件夹时长按笔记
	 */
	public static void test_012() 
	{
		//前提
		NoteCommon.createNote("Hi","Hello");
		NoteCommon.createFolder("Wenjianjia");
		//主体
		excute(Object_Text, Operation_LongClick, "Hi");
		check(Object_Text, Operation_checkExist, "删除");
		check(Object_Text, Operation_checkExist, "移入文件夹");
		check(Object_Text, Operation_checkExist, "分享");
	}
	/**
	 * 有一个笔记和一个文件夹时长按笔记，点击删除
	 */
	public static void test_013() 
	{
		//前提
		NoteCommon.createNote("Hi","Hello");
		NoteCommon.createFolder("Wenjianjia");
		//主体
		excute(Object_Text, Operation_LongClick, "Hi");
		excute(Object_Text, Operation_ClickWait, "删除");
		check(Object_Text, Operation_checkExist, "确定删除所选条目?");
	}
	/**
	 * 有一个笔记和一个文件夹时长按笔记，点击移入文件夹
	 */
	public static void test_014() 
	{
		//前提
		NoteCommon.createNote("Hi","Hello");
		NoteCommon.createFolder("Wenjianjia");
		//主体
		excute(Object_Text, Operation_LongClick, "Hi");
		excute(Object_Text, Operation_ClickWait, "移入文件夹");
		excute(Object_ResourceId, Operation_ClickWait, "android:id/text1");
		check(Object_Text, Operation_checkNoExist, "Hi");
		check(Object_Text, Operation_checkExist, "Wenjianjia(1)");
	}
	/**
	 * 有一个笔记和一个文件夹时长按笔记，点击分享
	 */
	public static void test_015() 
	{
		//前提
		NoteCommon.createNote("Hi","Hello");
		NoteCommon.createFolder("Wenjianjia");
		//主体
		excute(Object_Text, Operation_LongClick, "Hi");
		excute(Object_Text, Operation_ClickWait, "分享");
		check(Object_Text, Operation_checkExist, "电子邮件");
		check(Object_Text, Operation_checkExist, "信息");
		check(Object_Text, Operation_checkExist, "蓝牙");	
	}
	/**
	 * 有一个笔记和一个文件夹时长按文件夹
	 */
	public static void test_016() 
	{
		//前提
		NoteCommon.createNote("Hi","Hello");
		NoteCommon.createFolder("Wenjianjia");
		//主体
		excute(Object_Text, Operation_LongClick, "Wenjianjia(0)");
		check(Object_Text, Operation_checkExist, "删除");
		check(Object_Text, Operation_checkExist, "修改文件夹名称");
	}
	/**
	 * 有一个笔记和一个文件夹时长按文件夹，选择删除
	 */
	public static void test_017() 
	{
		//前提
		NoteCommon.createNote("Hi","Hello");
		NoteCommon.createFolder("Wenjianjia");
		//主体
		excute(Object_Text, Operation_LongClick, "Wenjianjia(0)");
		excute(Object_Text, Operation_ClickWait, "删除");
		check(Object_Text, Operation_checkExist, "确定删除所选条目?");
	}
	/**
	 * 有一个笔记和一个文件夹时长按文件夹，选择修改文件夹名称
	 */
	public static void test_018() 
	{
		//前提
		NoteCommon.createNote("Hi","Hello");
		NoteCommon.createFolder("Wenjianjia");
		//主体
		excute(Object_Text, Operation_LongClick, "Wenjianjia(0)");
		excute(Object_Text, Operation_ClickWait, "修改文件夹名称");
		excute(Object_ResourceId, Operation_SetText,"com.sprd.note:id/et_dialog_new_folder","NewFolderName");
		excute(Object_Text, Operation_ClickWait, "确定");
		check(Object_Text, Operation_checkExist, "NewFolderName(0)");
	}
	/**
	 * 添加笔记本
	 */
	public static void test_019() 
	{
		//主体
		NoteCommon.createNote("1", "1234");
		check(Object_ResIdText,Operation_checkExist,"com.sprd.note:id/note_title","1");
	}
	/**
	 * 在新建note界面点击更多——取消
	 */
	public static void test_020() 
	{
		//主体
		excute(Object_Description,Operation_ClickWait,"新建便签");
		excute(Object_Device,Operation_PressMenu);
		excute(Object_Text,Operation_ClickWait,"取消");
		check(Object_Text,Operation_checkExist,"确认要取消吗？");
		excute(Object_Text,Operation_ClickWait,"确定");
		check(Object_Text,Operation_checkExist,"记事本");
		excute(Object_Description,Operation_ClickWait,"新建便签");
		excute(Object_Device,Operation_PressMenu);
		excute(Object_Text,Operation_ClickWait,"取消");
		excute(Object_Text,Operation_ClickWait,"取消");
		check(Object_Text,Operation_checkExist,"编辑标题");
	}
	/**
	 * 选择一个已存在的条目跳转到“编辑页面”
	 */
	public static void test_021() 
	{
		//前提
		NoteCommon.createNote("Hi","Hello");
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"com.sprd.note:id/note_title");
		excute(Object_Device,Operation_PressMenu);
		check(Object_Text,Operation_checkExist,"编辑标题");
		check(Object_Text,Operation_checkExist,"保存");
		check(Object_Text,Operation_checkExist,"分享");
		check(Object_Text,Operation_checkExist,"删除");
	}
	/**
	 * 点击功能下拉框上的“编辑标题”
	 */
	public static void test_022() 
	{
		//前提
		NoteCommon.createNote("Hi","Hello");
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"com.sprd.note:id/note_title");
		excute(Object_Device,Operation_PressMenu);
		excute(Object_Text,Operation_ClickWait,"编辑标题");
		check(Object_Text,Operation_checkExist,"确定");	
	}
	/**
	 * 点击功能下拉框上的“保存”
	 */
	public static void test_023() 
	{
		//前提
		NoteCommon.createNote("Hi","Hello");
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"com.sprd.note:id/note_title");
		excute(Object_Device,Operation_PressMenu);
		excute(Object_Text,Operation_ClickWait,"保存");
		check(Object_Text,Operation_checkExist,"记事本");	
	}
	/**
	 * 点击功能下拉框上的“分享”
	 */
	public static void test_024() 
	{
		//前提
		NoteCommon.createNote("Hi","Hello");
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"com.sprd.note:id/note_title");
		excute(Object_Device,Operation_PressMenu);
		excute(Object_Text,Operation_ClickWait,"分享");
		check(Object_Text, Operation_checkExist, "电子邮件");
		check(Object_Text, Operation_checkExist, "信息");
		check(Object_Text, Operation_checkExist, "蓝牙");	
	}
	/**
	 * 点击功能下拉框上的“删除”
	 */
	public static void test_025() 
	{
		//前提
		NoteCommon.createNote("Hi","Hello");
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"com.sprd.note:id/note_title");
		excute(Object_Device,Operation_PressMenu);
		excute(Object_Text,Operation_ClickWait,"删除");
		check(Object_Text,Operation_checkExist,"确定删除该便签?");	
	}
	/**
	 * 进入文件夹界面点击新建笔记
	 */
	public static void test_026() 
	{
		//前提
		NoteCommon.createFolder("Wenjianjia");
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"com.sprd.note:id/note_title");
		excute(Object_Description,Operation_ClickWait,"新建便签");
		check(Object_Text,Operation_checkExist,"编辑标题");	
	}
	/**
	 * 有一个已建立的文件夹，且文件夹中有记事本,点击菜单按钮
	 */
	public static void test_027() 
	{
		//前提
		NoteCommon.createFolder("Wenjianjia");
		excute(Object_ResourceId,Operation_ClickWait,"com.sprd.note:id/note_title");
		NoteCommon.createNote("Hi","Hello");
		//主体
		excute(Object_Device,Operation_PressMenu);
		check(Object_Text,Operation_checkExist,"修改文件夹名称");	
		check(Object_Text,Operation_checkExist,"删除");	
		check(Object_Text,Operation_checkExist,"删除全部");	
		check(Object_Text,Operation_checkExist,"移出文件夹");	
	}
	/**
	 * 有一个已建立的文件夹，且文件夹中有记事本,点击菜单按钮，修改文件夹名称
	 */
	public static void test_028() 
	{
		//前提
		NoteCommon.createFolder("Wenjianjia");
		excute(Object_ResourceId,Operation_ClickWait,"com.sprd.note:id/note_title");
		NoteCommon.createNote("Hi","Hello");
		//主体
		excute(Object_Device,Operation_PressMenu);
		excute(Object_Text,Operation_ClickWait,"修改文件夹名称");	
		check(Object_ResIdText,Operation_checkExist,"com.sprd.note:id/note_editor_title","修改文件夹名称");	
		excute(Object_ResourceId,Operation_SetText,"com.sprd.note:id/et_dialog_new_folder","NewFolderName");
		excute(Object_Text,Operation_ClickWait,"确定");	
		check(Object_Text,Operation_checkExist,"NewFolderName");	
	}
	/**
	 * 有一个已建立的文件夹，且文件夹中有记事本,点击菜单按钮，删除
	 */
	public static void test_029() 
	{
		//前提
		NoteCommon.createFolder("Wenjianjia");
		excute(Object_ResourceId,Operation_ClickWait,"com.sprd.note:id/note_title");
		NoteCommon.createNote("Hi","Hello");
		//主体
		excute(Object_Device,Operation_PressMenu);
		excute(Object_Text,Operation_ClickWait,"删除");	
		check(Object_Text, Operation_checkExist, "完成");	
	}
	/**
	 * 有一个已建立的文件夹，且文件夹中有记事本,点击菜单按钮，删除全部
	 */
	public static void test_030() 
	{
		//前提
		NoteCommon.createFolder("Wenjianjia");
		excute(Object_ResourceId,Operation_ClickWait,"com.sprd.note:id/note_title");
		NoteCommon.createNote("Hi","Hello");
		//主体
		excute(Object_Device,Operation_PressMenu);
		excute(Object_Text,Operation_ClickWait,"删除全部");	
		check(Object_Text, Operation_checkExist, "确定删除全部条目?");	
	}
	/**
	 * 有一个已建立的文件夹，且文件夹中有记事本,点击菜单按钮，移出文件夹
	 */
	public static void test_031() 
	{
		//前提
		NoteCommon.createFolder("Wenjianjia");
		excute(Object_ResourceId,Operation_ClickWait,"com.sprd.note:id/note_title");
		NoteCommon.createNote("Hi","Hello");
		//主体
		excute(Object_Device,Operation_PressMenu);
		excute(Object_Text,Operation_ClickWait,"移出文件夹");	
		excute(Object_ResourceId,Operation_ClickWait,"com.sprd.note:id/check");
		excute(Object_Text,Operation_ClickWait,"完成");
		check(Object_Text, Operation_checkExist, "设置新的便签，请点击创建便签");	
	}
	/**
	 * 有一个已建立的文件夹，且文件夹中没有记事本，菜单项有修改文件夹名称
	 */
	public static void test_032() 
	{
		//前提
		NoteCommon.createFolder("Wenjianjia");
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"com.sprd.note:id/note_title");
		excute(Object_Device,Operation_PressMenu);
		check(Object_Text, Operation_checkExist, "修改文件夹名称");	
		excute(Object_Text,Operation_ClickWait,"修改文件夹名称");
		check(Object_ResIdText, Operation_checkExist,"com.sprd.note:id/note_editor_title", "修改文件夹名称");	
	}
}
