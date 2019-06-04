package com.rsyslog.hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Auther: liweizhi
 * @Date: 2019/5/28 11:14
 * @Description:
 */
@RestController
public class MyController {
    private static final Logger log = LoggerFactory.getLogger(MyController.class);

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @GetMapping("ping")
    public String ping() {
        return "pong";
    }

    @GetMapping("syslog/{timestamp}/{node}/{content}/{severity}/{type}")
    public String sendSyslog(@PathVariable("timestamp") long timestamp, @PathVariable("node") String node
            , @PathVariable("content") String content, @PathVariable("severity") int severity, @PathVariable("type") int type) {
        Rsyslog.sendSyslog(sdf.format(new Date(timestamp))
                , node, content, severity, type);
        return "OKay";
    }

    public static void main(String[] args) {
        long timestamp = 1559049782000L;
        Date date = new Date(timestamp);
        System.out.println(sdf.format(date));
    }
}
