package com.meteorsky.datadesign.Service;

import com.meteorsky.datadesign.Utils.STATUS;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class SecurityService {

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${mysqlpath}")
    private String mysqlpath;

    private final String path = (Thread.currentThread().getContextClassLoader().getResource("").getPath()+"backup/").substring(1);

    public int backup(String filename){
        File file = new File(path);
        if(!file.exists()){
            try {
                file.mkdir();
            } catch (Exception e) {
                return STATUS.FAIL;
            }
        }
        String cmd = mysqlpath.substring(0,2) + " && cd " + mysqlpath + " && mysqldump -u" +username+" -p"+password+" newspaper > "+path+filename+".sql";
        try {
            Runtime.getRuntime().exec(new String[]{"cmd","/c",cmd});
            return STATUS.SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return STATUS.FAIL;
        }
    }

    public int restore(String filename){
        String cmd = mysqlpath.substring(0,2) + " && cd " + mysqlpath + " && mysql -u"+username+" -p"+password+" newspaper < "+path+filename;
        try {
            Runtime.getRuntime().exec(new String[]{"cmd","/c",cmd});
            return STATUS.SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return STATUS.FAIL;
        }
    }

    public String[] getBackupList(){
        File file = new File(path);
        if(file.exists()){
            return file.list();
        }
        return new String[0];
    }

}
