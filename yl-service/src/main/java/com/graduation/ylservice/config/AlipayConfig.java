package com.graduation.ylservice.config;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.graduation.ylservice.entity.query.PayVo;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 支付宝配置类
 */
@Component
@Data
public class AlipayConfig {
    //在支付宝创建的应用的id
    private String app_id = "2021000117624167";

    // 商户私钥，您的PKCS8格式RSA2私钥
    private String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDFLYXcpKk7MLzv08SstN3yC3s1yRAmP/um2bi8sdppcZgmOG4Z4niULEX4Pb9qbu0g5tdEnBP1Z67Kjez7rn+AxyTD/QUrR1bao6zaR1MdAbF3nf+HlRpddSnfTFN//mC5gf4hkX+FsejYRouW5tUnwWAr1ybQzYnGTdRr9lLtJCQcpktP6BWkpIxceegxVNOuaaojmxjbtRrt4K4jqW4wmhnyJFa3mkV1Xm4sew0or3r76acfb7aVP5v+ftUUSPfXHvbtLTbx9UqUrgXwAsepJ41crY5bWYTKNegay7FzPNRTHUuyMHvg+1AyIil/UjopnhtJjxit7Ka4GmtZatKPAgMBAAECggEATwTnWH1EtMvba+FaODxjldS0E+H37txFZaBrQHOmLAuyBd8ngIgZ+oejotBcHbNGpKDeNEi4VuiuS86E4ngT8O/d9A06PRXh4M0nQHLFBCLhlCZleVhvsHR7y1kzPWAcMzu9NIayrEmYHtRmrJrlYYFFoUwf6h300Nvv/TRCF3ZnSjAx4OqVGFHElvtsTrtj+kOGIS10A0tqS9suvAWh3I+R9fI7mJ1SEOY8yWLNepV70XCfa6J2biAKOwj6NlJvE1u3ZiDN6mGbtr4JG+afSpA+iKGquV0FnOzekbB9d3QzRa5rTE+E1aPXGaoOP/uC7ptPVX/ShCiFmOKXCmsWAQKBgQD52nfa/mmAxf/aQgnxquTmk9lOS7pTUsnW1Rn77rrDr2Qli/407FINZU30JVF7eZF26Rgx84K67VU8qftdjBXi4FVmR3ypk4i/YVSx6Rsb6biqFZacU4aE4O+eSa3RGb9EjeYqY5IroLl2vTkoni5Gans1UmYcEH6SnmGjClecbwKBgQDKB1A9xzJqu8xqlzN/lqjSxS72CW/PRlTy7enCvujqlLDwhjwbS0/pooDJaabj0d3HLwC0Isi0ER881YjzwceTiv7L+FgCJeUdj5RH1/cUTdMUAUw/0pFuOviCwSP3gmmSox9qWGqGeRihkjfq3rS6VaZjbF5SvcLSqeZ6XE974QKBgQCiRl9T76sMRQ0Ab6XPvSPAEkSwEoUouvt2uZ+PviS1FcQgksT/EIHWS3aO3HIIlROCPLBHORYuXBjhNrGYt3Q7j2JH9KQl6zmEuy1sA69kuK8EYNMaIzGaOhHHJ6lpxqQwXU0hONxl05TBZvKpy9rYRy5QmnzRO3FJvu5BG2V9pwKBgDVncYKjIWStd//FMEvN25q7nQpUvEWiZdpBocVlCdTYsV0jXsgoRFuSJVEYqJEYbPO2LQ1xFHTlAcjm+WjWFJef6nKolzsaIVKLHbtQD3RCjkV/glRsqRzmYcLOo19ODfJBRJCsBpy9bhFnwXhInqWS9oTZpKVinBlr6Az4UdqBAoGBAPaRq9CPfKCYwTwzN5snrmzJcO3NjIi0LcH0T9p+uZ5btOe1hKbSmT0ihBbYtlIBjE76OmaHBSKDuM2XkqMXM4W1QQ7ziFx637jtJ+ve0d1gMvVshwxbVyIUvSy59xrjFOpOybJtWj6lRTRyUm3saD2Rf2WQR3BfpLXg2bGj0TcM";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    private String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgtpRO9WNsBDthUN3v7VuwJZKpKJ1oAjHlzLdKsgcWOk1eKld0o+ZELTRvzgBPA/mfMPruaAa6fO4KuiRhQrXsulHzMH7S0vVVU8whp7rtUKtG9E8OpblkGwbshyygXt3iUBAlOzwsmAKGewiLu4v6SGrTW4/MFZxZQAqYwGjvTNgcA7DPbptJZMS95N4+NYZ6gFlZT77aR1qdCnzN9ABKbZ0c+fMymJuGRghhtKDONw3fRLVPNOPM7DxmArSZE3k49IyklXg0d9WwThzP08/ufiP5siRnvt7qjTvcukrBLGv9/QVFyEmMBaCVkYnMZHpnG9Ou07E2QpWvw8L/el4qwIDAQAB";
    // 服务器[异步通知]页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    // 支付宝会悄悄的给我们发送一个请求，告诉我们支付成功的信息
    private String notify_url = "http://3r832150c9.wicp.vip/ylservice/payConfig/payRecord";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    //同步通知，支付成功，一般跳转到成功页
    //private String return_url = "http://3r832150c9.wicp.vip/memberOrder.html";
    private String return_url = "http://127.0.0.1:3000/indent";

    // 签名方式
    private String sign_type = "RSA2";

    // 字符编码格式
    private String charset = "utf-8";

    // 自动关单时间
    private String timeout = "15m";

    // 支付宝网关； https://openapi.alipaydev.com/gateway.do
    private String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    public String pay(PayVo vo) throws AlipayApiException {

        //AlipayClient alipayClient = new DefaultAlipayClient(AlipayTemplate.gatewayUrl, AlipayTemplate.app_id, AlipayTemplate.merchant_private_key, "json", AlipayTemplate.charset, AlipayTemplate.alipay_public_key, AlipayTemplate.sign_type);
        //1、根据支付宝的配置生成一个支付客户端
        AlipayClient alipayClient = new DefaultAlipayClient(gatewayUrl,
                app_id, merchant_private_key, "json",
                charset, alipay_public_key, sign_type);

        //2、创建一个支付请求 //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(return_url);
        alipayRequest.setNotifyUrl(notify_url);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = vo.getOut_trade_no();
        //付款金额，必填
        String total_amount = vo.getTotal_amount();
        //订单名称，必填
        String subject = vo.getSubject();
        //商品描述，可空
        String body = vo.getBody();

        // 30分钟内不付款就会自动关单
        alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
                + "\"total_amount\":\"" + total_amount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"body\":\"" + body + "\","
                + "\"timeout_express\":\"" + timeout + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        String result = alipayClient.pageExecute(alipayRequest).getBody();

        //会收到支付宝的响应，响应的是一个页面，只要浏览器显示这个页面，就会自动来到支付宝的收银台页面
        return result;
    }
}
