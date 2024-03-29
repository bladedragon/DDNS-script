package team.ict.ddnstask.pojo;

import lombok.Data;

@Data
public class Aliyun {
    /** 序列号 */
    private static final long serialVersionUID = 1L;
    /** IPV4地址，当前电脑在公网的ip地址 */
    private String ipV4;
    /** 主机记录，如果要解析@.exmaple.com，主机记录要填写"@”，而不是空 */
    private String rr;
    /** 解析记录类型，参见解析记录类型格式 */
    private String type;
    /** 解析记录的ID，此参数在添加解析时会返回，在获取域名解析列表时会返回 */
    private String recordId;
    /** 生效时间，默认为600秒（10分钟），参见TTL定义说明。购买VIP可以升级为1秒生效。没必要1秒 */
    private Long ttl;
}
