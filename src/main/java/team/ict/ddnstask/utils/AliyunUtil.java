package team.ict.ddnstask.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.alidns.model.v20150109.DescribeDomainRecordsRequest;
import com.aliyuncs.alidns.model.v20150109.DescribeDomainsRequest;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.FormatType;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.http.ProtocolType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class AliyunUtil {

    private static IAcsClient client = null;
    @Value("${AccessKeyId}")
    String accessKeyId;
    @Value("${AccessKeySecret}")
    String accessKeySecret;
    String regionId = "cn-hangzhou";


    /**
     * 获取一次inter连接，修改DNS
     */
    public static DescribeDomainsRequest getRequest(String actionName) {
        DescribeDomainsRequest request = new DescribeDomainsRequest();
        request.setProtocol(ProtocolType.HTTPS); // 指定访问协议
        request.setAcceptFormat(FormatType.JSON); // 指定api返回格式
        request.setMethod(MethodType.POST); // 指定请求方法
        // 设置请求action
        request.setActionName(actionName);
        return request;
    }


    /**
     * 获取一次inter连接,查询dns
     */
    public static DescribeDomainRecordsRequest getRequestQuery(String actionName) {
        DescribeDomainRecordsRequest request = new DescribeDomainRecordsRequest();
        request.setProtocol(ProtocolType.HTTPS); // 指定访问协议
        request.setAcceptFormat(FormatType.JSON); // 指定api返回格式
        request.setMethod(MethodType.POST); // 指定请求方法
        // 设置请求action
        request.setActionName(actionName);
        return request;
    }

    /**
     * 获取客户端数据
     *
     * @return
     */
    public  IAcsClient getClient() {
        IClientProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        client = new DefaultAcsClient(profile);
        return client;
    }


}
