

import ch.qos.logback.classic.Level
import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.util.FileSize
import org.apache.logging.log4j.core.appender.ConsoleAppender
import org.apache.logging.log4j.core.appender.RollingFileAppender
import org.apache.logging.log4j.core.appender.SyslogAppender


appender("Console", ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = "%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n"
    }
}

appender("R", RollingFileAppender) {
    file = "/data/logs/rsyslog/info.log"
    encoder(PatternLayoutEncoder) {
        pattern = "%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n"
    }
    rollingPolicy(SizeAndTimeBasedRollingPolicy) {
        fileNamePattern = "/data/logs/rsyslog/info_%d{yyyy-MM-dd}.%i.log"
        maxFileSize = "10KB"
        maxHistory = 30
        totalSizeCap = FileSize.valueOf("500MB")
    }
}

appender("RSYSLOG", SyslogAppender) {
    syslogHost = "10.0.1.32"
    facility = "local7"
    suffixPattern = "%msg"
}

logger("com.rsyslog.hello.Rsyslog", DEBUG, ["RSYSLOG"])
logger("ch.qos.logback", Level.WARN)

root(Level.valueOf("INFO"), ["Console", "R"])