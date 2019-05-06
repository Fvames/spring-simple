package dev.fames.mvc.v2.demo.controller;

import dev.fames.mvc.annotation.Autowired;
import dev.fames.mvc.annotation.Controller;
import dev.fames.mvc.annotation.RequestMapping;
import dev.fames.mvc.annotation.RequestParam;
import dev.fames.mvc.v2.demo.service.IQueryService;
import dev.fames.mvc.v2.webmvc.V2ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/web")
public class MyAction {
    @Autowired
    private IQueryService queryService;

    @RequestMapping("/query.json")
    public V2ModelAndView query(HttpServletRequest request, HttpServletResponse response, @RequestParam("name") String name) {
        String result = queryService.query(name);

        return out(response, result);
    }

    private V2ModelAndView out(HttpServletResponse response, String result) {
        try {
            response.getWriter().write(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
