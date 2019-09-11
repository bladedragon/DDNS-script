package team.ict.ddnstask.ddnsService;


import com.aliyuncs.alidns.model.v20150109.DescribeDomainRecordsRequest;
import com.aliyuncs.alidns.model.v20150109.DescribeDomainRecordsResponse;
import com.aliyuncs.exceptions.ClientException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import team.ict.ddnstask.pojo.Aliyun;
import team.ict.ddnstask.utils.AliyunUtil;
import com.aliyuncs.alidns.model.v20150109.DescribeDomainRecordsResponse.Record;
import java.util.List;

@Slf4j
@Service
public class UpdateRecord {
    @Value("${DomainName}")
    private String DomainName;

    @Autowired
    AliyunUtil aliyunUtil;
    @Autowired
    DomainList domainList;
    public void analysisDns() {

        LocalPublicIpv4 ip  = new LocalPublicIpv4();
        String ipv4 = ip.getPublicIp();
        String actionName = "DescribeDomainRecords";
        DescribeDomainRecordsResponse response;
        DescribeDomainRecordsRequest request = AliyunUtil.getRequestQuery(actionName);

        try {
            request.setDomainName(DomainName);
            response = aliyunUtil.getClient().getAcsResponse(request);

            List<Record> records = response.getDomainRecords();
            if(records == null || records.isEmpty()){
                log.error(">>>>>>>>>找不到Records<<<<<<</n");
                return;
            }
            //获取解析记录，注意查看阿里云官网，选择合适顺序
            Record record = records.get(0);
            Aliyun aliyun = new Aliyun();
            if(record.getValue().equals(ipv4)){
                log.info(">>>>>>>>>>>>>>>>>>>>外网IP"+record.getValue()+"无变化，不需要更新<<<<<<<<<<<<<<<<<");
            }else{
                log.info(">>>>>>>>>>>>>>>>>>>>外网IP为"+ipv4+" ;DNS解析"+record.getValue()+"，需要更新<<<<<<<<<<<<<<<<<<<<<");
                aliyun.setIpV4(ipv4);
                aliyun.setRecordId(record.getRecordId());
                aliyun.setRr(record.getRR());
                aliyun.setTtl(record.getTTL());
                aliyun.setType(record.getType());
                log.info(">>>>>>>>>>>>>>>>>>>>>开始更换IP<<<<<<<<<<<<<<<<<<<<<<<<<<");
                domainList.analysisDns(aliyun);
                log.info(">>>>>>>>>>>>>>>>>>>>>更换IP结束<<<<<<<<<<<<<<<<<<<<<<<<<<");
            }

        } catch (ClientException e) {
            e.printStackTrace();
            log.error("域名更换异常");
        }
    }

}
