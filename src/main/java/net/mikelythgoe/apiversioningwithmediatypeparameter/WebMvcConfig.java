package net.mikelythgoe.apiversioningwithmediatypeparameter;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ApiVersionConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    public void configureApiVersioning(ApiVersionConfigurer configurer) {

        configurer
                .addSupportedVersions("1.0", "2.0", "3.5", "9") // Add Supported Versions defines what versions can be supplied
                .setDefaultVersion("1.0") // Used when no version, or an unsupported version, is passed in the request
                .useMediaTypeParameter(MediaType.APPLICATION_XML, "version") // e.g. Accept = application/xml;version=3.5
                .useMediaTypeParameter(MediaType.APPLICATION_JSON, "version"); //e.g. Accept = application/json;version=3.5


    }
}
