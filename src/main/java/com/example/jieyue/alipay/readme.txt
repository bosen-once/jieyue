�����л�����

Eclipse+JDK1.6������+Tomcat6.0������


��ʹ�÷�����

SDK���ص�ַ��
https://doc.open.alipay.com/docs/doc.htm?treeId=193&articleId=103419&docType=1

��һ���������ء�JAVA����Դ����SDK��

�ڶ�����������Ϻ󣬰�ѹ������ѹ��

��������ѹ����alipay-sdk-javaXXX.jar��commons-logging-1.1.1.jar�ܰ��ļ����빤����Ŀalipay.trade.page.pay-PHP-UTF-8\WebContent\WEB-INF\lib�С�

���Ĳ�����AlipayConfig.java�ļ����������������Ϣ�����棬���������Ϣ�У�app_id��merchant_private_key��alipay_public_key��return_url��notify_url��
bizContent�ĸ�����������÷���ƴ�ӷ�ʽ�����ĵ���https://doc.open.alipay.com/docs/doc.htm?treeId=270&articleId=105899&docType=1

���岽������index.jsp�ļ�

������������֧�����̣���Ч���ȡ�


��ҵ����ע�������

������notify_url�ļ���return_url�ļ������У�notify_url�ļ���Ҫ��д��ҵ�����߼����룬�����������������д��

�����֤�첽֪ͨ���ݣ�

1���̻���Ҫ��֤��֪ͨ�����е�out_trade_no�Ƿ�Ϊ�̻�ϵͳ�д����Ķ�����

2���ж�total_amount�Ƿ�ȷʵΪ�ö�����ʵ�ʽ����̻���������ʱ�Ľ�

3��У��֪ͨ�е�seller_id������seller_email) �Ƿ�Ϊ�ñʽ��׶�Ӧ�Ĳ�������һ���̻������ж��seller_id/seller_email��

4����֤�ӿڵ��÷���app_id


��˵����

��demo����Ϊѧϰ�ο�ʹ�ã������ʵ��������п������ѹ���Ƕ��������Ŀ��ƽ̨�С�