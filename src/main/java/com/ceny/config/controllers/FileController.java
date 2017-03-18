package com.ceny.config.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;


/**
 * Created by chensongkui on 2017/3/17.
 */
// TODO: 2017/3/18  clean it
@RestController
public class FileController {

    private static final Logger LOGGER = LogManager.getLogger(FileController.class);

    @RequestMapping(value = "/file",method = RequestMethod.POST)
    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException, ServletException {
///HttpServletRequest request 作为参数
// Object tmp = request.getPart("file");

        LOGGER.info(file);
        return "done";
    }

    @RequestMapping(value = "/file",method = RequestMethod.GET)
    public String downloadFile(HttpServletResponse response) throws IOException {

        File file = new File("/Users/chensongkui/Desktop/WX20170227-114729@2x.png");
        InputStream in = new FileInputStream(file);
        response.setHeader("Content-Disposition","attachment; filename=\"" + file.getName() + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
        OutputStream out = response.getOutputStream();

        byte[] b = new byte[1024];
        int n;
        while ((n = in.read(b)) != -1){
            out.write(b, 0, n);
        }
        in.close();
        out.flush();
        out.close();
        return "download";
    }
}
