#!/bin/bash
#创建打包测试代码的Build脚本
#set -x
cd /home/likewise-open/SPREADTRUM/zhengzheng.song/auto/adt-bundle-linux-x86_64-20140702/sdk/tools
./android create uitest-project -n Android60 -t 2 -p /home/likewise-open/SPREADTRUM/zhengzheng.song/workspace/nanjing/UIcase/uiautomator51
#打包
cd /home/likewise-open/SPREADTRUM/zhengzheng.song/workspace/nanjing/UIcase/uiautomator51
ant build 
#上传文件到手机
adb push /home/likewise-open/SPREADTRUM/zhengzheng.song/workspace/nanjing/UIcase/uiautomator51/bin/Android60.jar data/local/tmp
#adb push /home/likewise-open/SPREADTRUM/zhengzheng.song/workspace/Android6.0Setting/wifiConfig.txt data/local/tmp
#运行测试程序
adb root
adb shell uiautomator runtest Android60.jar -c testcase.CallLog
#adb shell uiautomator runtest Android60.jar -c testcase.Settings
		
