package com.bxrbasov.app.servlet;

import com.bxrbasov.app.service.ImageService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import java.io.IOException;
import java.io.InputStream;

@WebServlet("/images/*")
public class ImageServlet extends HttpServlet {

    private static ImageService imageService = ImageService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String imagePath = req.getRequestURI().replace("/nebank", "");
        imageService.getImage(imagePath)
                .ifPresentOrElse(image -> {
                    resp.setContentType("application/octet-stream");
                    writeImage(image, resp);
                }, () -> resp.setStatus(404));
    }

    @SneakyThrows
    private void writeImage(InputStream image, HttpServletResponse resp) {
        try(image; var outputStream = resp.getOutputStream()) {
            int currentByte;
            while ((currentByte = image.read()) != -1) {
                outputStream.write(currentByte);
            }
        }
    }

}
