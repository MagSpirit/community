package com.demo.community.controller;

import com.demo.community.dto.AccessTokenDto;
import com.demo.community.dto.GitHubUser;
import com.demo.community.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {

    @Autowired
    private GitHubProvider gitHubProvider;
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state){
        AccessTokenDto accessTokenDto=new AccessTokenDto();
        accessTokenDto.setClient_id("6eb8e7fa2fe36553de24");
        accessTokenDto.setClient_secret("dd030c61a38bb4c5fd7ef4a1920848e9e86a2ef4");
        accessTokenDto.setCode(code);
        accessTokenDto.setRedirect_uri("http://localhost:8080/callback");
        accessTokenDto.setState(state);
        String accessToken = gitHubProvider.getAccessToken(new AccessTokenDto());
        GitHubUser user=gitHubProvider.getUser(accessToken);
        System.out.println(user.getName());
        return "index";
    }
}
