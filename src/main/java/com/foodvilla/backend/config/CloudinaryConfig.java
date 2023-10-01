package com.foodvilla.backend.config;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {


    @Value("${CLOUDINARY_SECRET}")
    private String secretValueCloudinary;

    @Bean
    public Cloudinary cloudinaryConfiguration() {
        Cloudinary cloudinary = null;
        Map config = new HashMap();
        config.put("cloud_name", "dlg7e8dwa");
        config.put("api_key", "515544832494222");
        config.put("api_secret", secretValueCloudinary);
        cloudinary = new Cloudinary(config);
        return cloudinary;
    }

}
