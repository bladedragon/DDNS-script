package team.ict.ddnstask;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import team.ict.ddnstask.ddnsService.UpdateRecord;
import team.ict.ddnstask.utils.SpringbootUtil;

@Slf4j
@SpringBootApplication
public class DdnstaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(DdnstaskApplication.class, args);
        log.info("<<<<<<<<<<<<<<<<<<<<<DDNS 服务开启>>>>>>>>>>>>>>>>");

        ApplicationContext context = SpringbootUtil.getApplicationContext();
        UpdateRecord updateRecord = context.getBean(UpdateRecord.class);
        updateRecord.analysisDns();
        log.info("<<<<<<<<<<<<<<<<<<<<<DDNS 服务结束>>>>>>>>>>>>>>>>");
    }

}
