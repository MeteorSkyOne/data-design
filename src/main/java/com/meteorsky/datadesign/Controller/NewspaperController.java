package com.meteorsky.datadesign.Controller;

import com.meteorsky.datadesign.Model.Newspaper;
import com.meteorsky.datadesign.Service.NewspaperService;
import com.meteorsky.datadesign.Service.OrderService;
import com.meteorsky.datadesign.Utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RestController
@RequestMapping("/newspaper")
public class NewspaperController {

    @Autowired
    private NewspaperService newspaperService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/getAll")
    public JsonResult getAll() {
        return JsonResult.success(newspaperService.getAll());
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public JsonResult add(@RequestParam String id,
                          @RequestParam String name,
                          @RequestParam String publisher,
                          @RequestParam String publishBetween,
                          @RequestParam float price,
                          @RequestParam String content,
                          @RequestParam int newspaperClass){
        return JsonResult.success(newspaperService.add(id,name,publisher,publishBetween,price,content,newspaperClass));
    }

    @PostMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public JsonResult update(@RequestParam String id,
                             @RequestParam String name,
                             @RequestParam String publisher,
                             @RequestParam String publishBetween,
                             @RequestParam float price,
                             @RequestParam String content,
                             @RequestParam int newspaperClass){
        return JsonResult.success(newspaperService.update(id,name,publisher,publishBetween,price,content,newspaperClass));
    }

    @PostMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public JsonResult delete(@RequestParam String id){
        return JsonResult.success(newspaperService.delete(id));
    }

    @PostMapping("subscribe")
    public JsonResult subscribe(@RequestParam String id, @RequestParam int counts, @RequestParam int months, HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
        return JsonResult.success(orderService.subscribeNewspaper(username,id,counts,months));
    }

}
