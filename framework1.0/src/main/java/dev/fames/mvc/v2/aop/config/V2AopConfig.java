package dev.fames.mvc.v2.aop.config;

import lombok.Data;

/**
 * Created by   on 2019/4/15.
 */
@Data
public class V2AopConfig {

    private String pointCut;
    private String aspectBefore;
    private String aspectAfter;
    private String aspectClass;
    private String aspectAfterThrow;
    private String aspectAfterThrowingName;

}
