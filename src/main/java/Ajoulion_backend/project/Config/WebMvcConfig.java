package Ajoulion_backend.project.Config;

import Ajoulion_backend.project.ImageUpload.ImageUpload;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String dir = System.getProperty("user.dir");
        String file = (dir.indexOf('\\') > 0) ? "file:///" : "file:";
        registry.addResourceHandler("/image/**")
                .addResourceLocations(file + ImageUpload.getImageDir());
    }
}