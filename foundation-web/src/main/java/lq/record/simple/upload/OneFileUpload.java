package lq.record.simple.upload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * springmvc 单文件上传
 * 
 * @author 刘泉
 * @date 2016年8月10日 下午3:27:36
 */
@Controller
public class OneFileUpload {
    @RequestMapping("/oneFileUpload")
    public void oneFileUpload(MultipartHttpServletRequest request,
        HttpServletResponse response) {
        try {
            System.out.println("username=" + request.getParameter("username"));
            MultipartFile file = request.getFile("uploadFile");
            String uploadFileName = file.getOriginalFilename();
            InputStream isRef = file.getInputStream();
            String targetDir = request.getSession().getServletContext()
                .getRealPath("uploadTest");
            File targetFile = new File(targetDir, uploadFileName);
            FileOutputStream fosRef = new FileOutputStream(targetFile);
            IOUtils.copy(isRef, fosRef);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
