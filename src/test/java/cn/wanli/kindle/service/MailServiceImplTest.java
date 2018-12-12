/*
 * kindle electronic book push service
 *
 * Copyright (C) 2018 - wanli <wanlinus@qq.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package cn.wanli.kindle.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author wanli
 * @date 2018-11-27 10:37
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceImplTest {

    @Autowired
    private MailService mailService;

    @Test
    public void sendSimpleMail() {
        mailService.sendSimpleMail("wanlinus@qq.com", "test", "content");

    }

    @Test
    public void sendHtmlMail() {
        String content = "<html>\n" +
                "<body>\n" +
                "    <h3>Hello World</h3>\n" +
                "</body>\n" +
                "</html>";
        mailService.sendHtmlMail("wanlinus@qq.com", "Sorry", content);
//        mails.forEach(mail -> mailService.sendHtmlMail(mail, "Sorry", content));
    }

    @Test
    public void sendAttachmentsMail() {
        String filePath = "d:\\aaa.txt";
        mailService.sendAttachmentsMail("wanlinus@qq.com", "主题：带附件的邮件", "有附件，请查收！", filePath);
    }

    @Test
    public void sendInlineResourceMail() {
        String rscId = "neo006";
        String content = "<html><body>这是有图片的邮件：<img src=\'cid:" + rscId + "\' ></body></html>";
        String imgPath = "d://wanli/Pictures/微信图片_20181105154040.png";

        mailService.sendInlineResourceMail("wanlinus@qq.com", "主题：这是有图片的邮件", content, imgPath, rscId);
    }
}