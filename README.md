# Update
Use system app update

����ϵͳ����app����ʵ�ֶϵ���������̨�Զ����أ���������ʾ���ȣ�����ֱ������Զ���ת����װҳ��,��¼�������°汾�ȹ��ܡ���������ҳ����dialog�Լ����壬�ɸ������������
  
Ϊ��ʹ����ṹ������������ϣ���Ҫʵ�ִ����ڶ���ģ��systemupdate��ʵ�֣�MainActivityֻʵ�ִ�ֵ�ĳ�ʼ�������� 

#Maven������ 
compile 'com.cnhubei.systemupdate:systemupdate:1.0.0'

dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    testCompile 'junit:junit:4.12'
    compile 'com.cnhubei.systemupdate:systemupdate:1.0.0'
} 


![update](https://github.com/EastLakeLegends/Update/blob/master/update1.png)   ![update](https://github.com/EastLakeLegends/Update/blob/master/update2.png) 
![update](https://github.com/EastLakeLegends/Update/blob/master/update3.png) 

#MainActivity�г�ʼ������

VersionBean bean = new VersionBean("8.9.9","http://10.99.101.4/1616v3.0.2xin.apk ", "app����2", 0, 90000000);
bean.setCheckUpdater(false);
VersionManager.VersionCheck(MainActivity.this, bean);

#��Ҫʹ����DownloadManager

DownloadManager API:http://android-doc.com/reference/android/app/DownloadManager.html


DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        //������ʲô��������½�������
        request.setAllowedNetworkTypes(request.NETWORK_WIFI);
        //����֪ͨ������
        request.setNotificationVisibility(request.VISIBILITY_VISIBLE);
        request.setTitle("�������ź��������");
        request.setMimeType("application/vnd.android.package-archive");
        request.setAllowedOverRoaming(false);
        saveSimpleCacheObject(url);
        String path = Environment.DIRECTORY_DOWNLOADS;
        String app = url.substring(url.lastIndexOf("/") + 1);

        File file = new File(path, app);
        try{
            //�޸�data/dataĿ¼Ȩ��
            if(file.getAbsolutePath().indexOf("data/data/")>-1){
                String[] command = {"chmod", "755", file.getAbsolutePath()};
                ProcessBuilder builder = new ProcessBuilder(command);
                builder.start();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        //�����ļ����Ŀ¼
        request.setDestinationInExternalPublicDir(path, app);
        downManager = (DownloadManager)this.getSystemService(Context.DOWNLOAD_SERVICE);
        downManager.enqueue(request);

�в���ĵط������ָ�������ۣ�