package com.meteorsky.datadesign.Controller;

import com.meteorsky.datadesign.Service.NewspaperClassService;
import com.meteorsky.datadesign.Utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/newspaperClass")
public class NewspaperClassController {

    @Autowired
    private NewspaperClassService newspaperClassService;

    @GetMapping("/getAll")
    public JsonResult getAll() {
        return JsonResult.success(newspaperClassService.getAll());
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public JsonResult add(@RequestParam int id, @RequestParam String name) {
        return JsonResult.success(newspaperClassService.add(id,name));
    }

    @PostMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public JsonResult update(@RequestParam int id,
                             @RequestParam String name) {
        return JsonResult.success(newspaperClassService.update(id, name));
    }

    @PostMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public JsonResult delete(@RequestParam int id) {
        return JsonResult.success(newspaperClassService.delete(id));
    }

}
