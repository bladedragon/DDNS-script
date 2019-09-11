package team.ict.ddnstask.ddnsService;


import com.aliyuncs.IAcsClient;
import com.aliyuncs.alidns.model.v20150109.DescribeDomainsRequest;
import com.aliyuncs.alidns.model.v20150109.DescribeDomainsResponse;
import com.aliyuncs.alidns.model.v20150109.DescribeDomainsResponse.Domain;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import team.ict.ddnstask.pojo.Aliyun;
import team.ict.ddnstask.utils.AliyunUtil;

import java.util.List;

@Slf4j
@Service
public class DomainList {

    private static IAcsClient client = null;
    @Autowired
    AliyunUtil aliyunUtil;


    public void setParam(DescribeDomainsRequest request,Aliyun yun){
        request.putQueryParameter("RecordId", yun.getRecordId());
        request.putQueryParameter("RR", yun.getRr());
        request.putQueryParameter("Type", yun.getType());
        request.putQueryParameter("Value", yun.getIpV4());
        request.putQueryParameter("TTL", yun.getTtl());
    }

    public void analysisDns(Aliyun yun) {
        String actionName = "UpdateDomainRecord";
        DescribeDomainsRequest request = AliyunUtil.getRequest(actionName);
        DescribeDomainsResponse response;
        setParam(request, yun);

        try {
            response = aliyunUtil.getClient().getAcsResponse(request);
            List<Domain> list = response.getDomains();
            for (Domain domain : list) {
                log.info(">>>>>>>>>>>>>>>>>>>>>>更新域名为"+domain.getDomainName()+"的IP地址<<<<<<<<<<<<<<<<<<<<<<<<<<");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
