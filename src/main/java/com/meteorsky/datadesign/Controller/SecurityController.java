package com.meteorsky.datadesign.Controller;

import com.meteorsky.datadesign.Service.SecurityService;
import com.meteorsky.datadesign.Utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/security")
public class SecurityController {

    @Autowired
    private SecurityService securityService;

    @PostMapping("/backup")
    @PreAuthorize("hasRole('ADMIN')")
    public JsonResult backup(@RequestParam String filename) {
        if(filename.isEmpty()){
            return JsonResult.fail("请输入文件名");
        }
        return JsonResult.success(securityService.backup(filename));
    }

    @GetMapping("/getBackupList")
    @PreAuthorize("hasRole('ADMIN')")
    public JsonResult getBackupList() {
        return JsonResult.success(securityService.getBackupList());
    }

    @PostMapping("/restore")
    @PreAuthorize("hasRole('ADMIN')")
    public JsonResult restore(@RequestParam String filename) {
        if(filename.isEmpty()){
            return JsonResult.fail("请输入文件名");
        }
        return JsonResult.success(securityService.restore(filename));
    }

}
