# Update
Use system app update

调用系统更新app包，实现断点续传，后台自动下载，下拉栏显示进度，下载直接完毕自动跳转到安装页面,记录跳过更新版本等功能。由于下载页面是dialog自己定义，可根据需求调整。
  
为了使代码结构清晰，降低耦合，主要实现代码在独立模块systemupdate中实现，MainActivity只实现传值的初始化工作。 

#Maven库引用 
compile 'com.cnhubei.systemupdate:systemupdate:1.0.0'

dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    compile 'com.cnhubei.systemupdate:systemupdate:1.0.0'
} 


![update](https://github.com/EastLakeLegends/Update/blob/master/update1.png)   ![update](https://github.com/EastLakeLegends/Update/blob/master/update2.png) 
![update](https://github.com/EastLakeLegends/Update/blob/master/update3.png) 

#MainActivity中初始化代码

VersionBean bean = new VersionBean("8.9.9","http://10.99.101.4/1616v3.0.2xin.apk ", "app测试2", 0, 90000000);
bean.setCheckUpdater(false);
VersionManager.VersionCheck(MainActivity.this, bean);

#主要使用了DownloadManager

DownloadManager API:http://android-doc.com/reference/android/app/DownloadManager.html


DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        //设置在什么网络情况下进行下载
        request.setAllowedNetworkTypes(request.NETWORK_WIFI);
        //设置通知栏标题
        request.setNotificationVisibility(request.VISIBILITY_VISIBLE);
        request.setTitle("动向新闻海外版下载");
        request.setMimeType("application/vnd.android.package-archive");
        request.setAllowedOverRoaming(false);
        saveSimpleCacheObject(url);
        String path = Environment.DIRECTORY_DOWNLOADS;
        String app = url.substring(url.lastIndexOf("/") + 1);

        File file = new File(path, app);
        try{
            //修改data/data目录权限
            if(file.getAbsolutePath().indexOf("data/data/")>-1){
                String[] command = {"chmod", "755", file.getAbsolutePath()};
                ProcessBuilder builder = new ProcessBuilder(command);
                builder.start();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        //设置文件存放目录
        request.setDestinationInExternalPublicDir(path, app);
        downManager = (DownloadManager)this.getSystemService(Context.DOWNLOAD_SERVICE);
        downManager.enqueue(request);

有不足的地方，请多指教与讨论！