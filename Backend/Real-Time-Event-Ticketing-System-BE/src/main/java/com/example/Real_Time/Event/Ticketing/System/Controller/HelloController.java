package com.example.Real_Time.Event.Ticketing.System.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HelloController {

//    @GetMapping("/secured")
//    public String secured(@AuthenticationPrincipal userPrincipal principal) {
//        return "If you see this then you're logged in as user"+principal.getEmail()
//                +"User ID" + principal.getUserId();
//    }


}
