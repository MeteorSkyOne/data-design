package com.meteorsky.datadesign.Controller;

import com.meteorsky.datadesign.Service.DepartmentService;
import com.meteorsky.datadesign.Utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/getAll")
    public JsonResult getAll() {
        return JsonResult.success(departmentService.getAll());
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public JsonResult add(@RequestParam int id,@RequestParam String name) {
        return JsonResult.success(departmentService.add(id,name));
    }

    @PostMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public JsonResult update(@RequestParam int id,
                             @RequestParam String name) {
        return JsonResult.success(departmentService.update(id, name));
    }

    @PostMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public JsonResult delete(@RequestParam int id) {
        return JsonResult.success(departmentService.delete(id));
    }

}
