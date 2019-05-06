package dev.fames.mvc.v2.demo.service.impl;

import dev.fames.mvc.annotation.Service;
import dev.fames.mvc.v2.demo.service.IQueryService;

@Service
public class QueryService implements IQueryService {
    @Override
    public String query(String name) {

        String json = "{name: \"" + name + "\",time: \"2019-04-11\"";

        return json;
    }
}
