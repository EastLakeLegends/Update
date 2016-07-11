# Update
Use system app update

调用系统更新app包，实现断点续传，后台自动下载，下拉栏显示进度，下载直接完毕自动跳转到安装页面,记录跳过更新版本等功能。由于下载页面是dialog自己定义，可根据需求调整。

  
为了使代码结构清晰，降低耦合，主要实现代码在独立模块systemupdate中实现，MainActivity只实现传值的初始化工作。  

[id]http://android-doc.com/reference/android/app/DownloadManager.html

![update](https://github.com/EastLakeLegends/Update/blob/master/update.png) 