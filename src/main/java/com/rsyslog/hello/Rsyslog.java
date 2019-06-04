package com.rsyslog.hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Auther: liweizhi
 * @Date: 2019/5/28 10:49
 * @Description:
 */
public class Rsyslog {

    private static final Logger log = LoggerFactory.getLogger(Rsyslog.class);

    private static final String PARAM_5 = "应用";
    private static final String PARAM_6 = "对内服务";
    private static final String PARAM_7 = "服务不可用";

    public static void sendSyslog(String time, String node, String content, int severity, int type) {
        log.info("##{}|{}|{}|{}|{}|{}|{}|{}|{}$$", time, node, node, content, PARAM_5, PARAM_6, PARAM_7, severity, type);
    }

}
