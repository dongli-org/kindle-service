package cn.wanli.kindle.service;

/**
 * @author wanli
 * @date 2018-11-27 10:30
 */
public interface MailService {

    /**
     * 发送简单文本文件邮件
     *
     * @param to      收件地址
     * @param subject 邮件主题
     * @param content 邮件内容
     */
    void sendSimpleMail(String to, String subject, String content);

    /**
     * 发送HTML邮件
     *
     * @param to      收件地址
     * @param subject 邮件主题
     * @param content 邮件内容
     */
    void sendHtmlMail(String to, String subject, String content);

    /**
     * 发送携带附件的邮件
     *
     * @param to       收件地址
     * @param subject  邮件主题
     * @param content  邮件内容
     * @param filePath 附件路径
     */
    void sendAttachmentsMail(String to, String subject, String content, String filePath);

    void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId);


}
