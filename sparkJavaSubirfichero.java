package com.firstfewlines;
import static spark.Spark.*;

import spark.utils.IOUtils;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.Part;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class SparkJavaSubirfichero {

    public static void main(String [] argv){
        staticFiles.location("/public");

        post("/api/upload", (req, res) -> {
            req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("Establecer ruta aqui. ejm. /tmp"));
            Part filePart = req.raw().getPart("mifichero");

            try (InputStream inputStream = filePart.getInputStream()) {
                OutputStream outputStream = new FileOutputStream("Establecer ruta aqui. ejm. /tmp" + filePart.getSubmittedFileName());
                IOUtils.copy(inputStream, outputStream);
                outputStream.close();
            }

            return "Fichero subido y guardado";
        });
    }
}
