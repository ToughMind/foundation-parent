package lq.record.simple.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 利用springmvc下载文件
 * 
 * @author 刘泉
 * @date 2016年8月10日 下午3:27:24
 */
@Controller
public class DownloadFile {
    @RequestMapping("/downloadFile")
    public void login(HttpServletRequest request,
        HttpServletResponse response) {
        try {
            String fileName = "a.png";
            String fileNameEncode = new String(fileName.getBytes(),
                "ISO8859-1");
            response.setContentType("application/x-msdownload");
            FileInputStream FileInputStreamRef = new FileInputStream(new File(
                request.getSession().getServletContext().getRealPath("")
                    + "uploadTest/" + fileName));
            response.setHeader("Content-Disposition",
                "attachment;filename=" + fileNameEncode);
            OutputStream osRef = response.getOutputStream();
            IOUtils.copy(FileInputStreamRef, osRef);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
