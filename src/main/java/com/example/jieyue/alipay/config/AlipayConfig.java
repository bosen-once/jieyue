package com.example.jieyue.alipay.config;

/* *
 *类名：AlipayConfig1
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

import java.io.FileWriter;
import java.io.IOException;

public class AlipayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2021002114695818";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQC/0lWCv81WoVbU\n" +
            "yOTJ7jM9AV6YRmIOUhLqWHGA1wSXAG57NSkK7SifBcit61hdfZaYzDatmQQn5t4D\n" +
            "S+gQ4rQwnerKzoTmWY6EKXV3BAawdLG30SRJhX0739l7Ul+X3giTIUG5R4fG9gTN\n" +
            "XnmhGX4yTa2yir/IOeWJ0lqXoKRyxRclaMFsF8gW4EBk5S7PVzMhdjh7EV1D+2QB\n" +
            "5baNIFaZvfByMr7V+q2+awlBH7ZoDbviyV+vHmZCC/dOLPaWLTkW+t+ih/z9rqaN\n" +
            "fZIOTgaLB7g7hca7dnzCM/G1i2UNHWzU2mRfALK/ppvJKe7J7GTOSu92iVF5Yryr\n" +
            "CUv6TcjDAgMBAAECggEAQtqStOwWK8A1XBncK0iakQN+LYIyQJ14PDaw8F+AGQSs\n" +
            "fMJtWIKJassAQyVH+eWAbCaYU+6FlUaQUAPPkLsCZANVxnMtvH6v/f3ZE67SigNR\n" +
            "wSfiBsHAgPKmgKWX7X0T7ftkRafZeY1a5RytNWmGIxARnq1e4/DRwDQRINF7HwhZ\n" +
            "Crp/JXZYOYv/0dSRyV5l/S3J13m7NgimG7nejFc7rRhMS9gXQohD9pXcL8N1F+Af\n" +
            "zpSf1AFyCvFA41cQO+SNPbpjXHv2k2WuBOFMt08Rxfn4DKiDhhK9eHN0nB9RVeWl\n" +
            "DOY/6RiMketWvMqx5TCltE5WtGchEqYHVwNK6Tfe+QKBgQDt50tdoxisr+kj2JAA\n" +
            "BQXZmz5isQpeUt3JruNOnw2hVSbMO60iftLTeB+GHX6hGvD5ViFEDgZotgjYL/p+\n" +
            "AW7Dm0+D+ecWvVUhySrab/sMso/B33OCqYfPNdj780+fmqVRrRjnK/J4V0n5AoaC\n" +
            "Rx0vfXfJUXonPZ+Jn8RSiW8CNwKBgQDOaa9ljnO6mcSr+k/I7Biq0e4ZFvl10M4G\n" +
            "aMDE9AyiGUWn0JIERpLCZ0VIgOqtDXuJo5JwPfIHVppobBqaWdlKBuA2rIwynjls\n" +
            "+jfplnVlAQKxkdRUM+8nXW24q0qsNFro+KsCE5y6tevxsHyXihmElv9AhRcsXY2g\n" +
            "LM7BDzIX1QKBgQDTM2P38+Pi4qRB+28TZWIlJioDKZK/3Ccjdkw7rv5FvLed1ljE\n" +
            "5/w4Wkr6IFMbqTRG1kzgAudzrC875ZQmqxTvOIEl9ARQbrMWNGYD2CfyxM7U2Y/N\n" +
            "br2vmelQ+1czpPGhCd+y0pQGe8+i7bgGDiX91BA0vE2Kaq2oWxrJ/1J6jwKBgQCh\n" +
            "RffRzHRllmEQ0dlmrfkz9iQnHEk6oTNn1I7RE7GPMVm4HYEIsC9YjyGV3Yv+O+uL\n" +
            "Be+TnQKucx7feZ9re8oHScoDTduYbQGHQRVQf8snaJu3K66PP6iaxCfHQqZsV9ez\n" +
            "8R6D19j8FMnNo8wlBrSbYPL8cUBC3LGcNYYxV3MXJQKBgQDbh5oML1AJ1FYvkMew\n" +
            "v/xCNMvrsJDNPty4oZD3lXAqq2/rbHKxDqsB9PkVcd4xdHrYuHU+MbYeb96hHAyv\n" +
            "seYiUCWF/XryBGI/7AcHTKpgBA9TXqudCyPUhz0ewC5vKnYdcqXXTjjL1WqILegj\n" +
            "cSLF6WzY1X1kw7j9IuP6wyZQZg==";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAuaRqJqDOBRNsIa1MkgyiR6kRUk+M8cbNmGFN0RDy1NJmxyaR/iqIi5k8vQZ+qnVelXTVVEEYKiYaIxj6co8ERKPXlrnk/GiRxn410jX35iLnm/BgwXaiVP1Fx6kusiXiCGWgrvEsbAMFvbZqyqT/z8m+ogQ7JPTkoauzzu16JJxpcSPEf7SPnbuiBjHbUqRGP1pAa1yVun0eVFHaFeqi+cRfGDhwym2xEAMokJh+aFlU3AnCg/f7fBek4UNi6anAadoX3dKcB8cNeIMzg5FaplZP2V0ztLg2Qvw4ezKSWUFB4hFD0d6De61zwpGjgl+ouobyL4prsUA3qlT48E8d8wIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://工程公网访问地址/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://工程公网访问地址/alipay.trade.page.pay-JAVA-UTF-8/return_url.jsp";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipay.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
