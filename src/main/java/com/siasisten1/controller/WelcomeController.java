package com.siasisten1.controller;

import org.springframework.web.bind.annotation.RequestMapping;

public class WelcomeController {

  @RequestMapping("/")
  public String index(){
    return "welcome/index";
  }
}
