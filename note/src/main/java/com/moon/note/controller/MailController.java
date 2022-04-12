//package com.moon.note.controller;
//
//import com.moon.note.entity.Response;
//import com.moon.note.entity.Result;
//import com.moon.note.service.MailService;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.annotation.Resource;
//
///**
// * @author MoonLight
// */
//
//@RestController
//@RequestMapping("/mail")
//public class MailController {
//
//    @Resource
//    MailService mailService;
//
//    // TODO 分类型发邮件
//
//    @PostMapping("/{username}/{type}/{content}")
//    public Result<String> send(@PathVariable String username,String type ,@PathVariable String content) throws Exception {
//        mailService.sendMail(username, type,content);
//        return new Result<>(Response.SUCCESS);
//    }
//}
