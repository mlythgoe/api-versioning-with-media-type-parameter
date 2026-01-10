package net.mikelythgoe.apiversioningwithmediatypeparameter;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ApiVersionConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private static final String VERSION_HEADER = "version";
    private static final String[] SUPPORTED_VERSIONS = {"1.0", "2.0", "3.5", "9"};
    private static final String DEFAULT_VERSION = "2.0";

    public void configureApiVersioning(ApiVersionConfigurer configurer) {

        configurer
                .addSupportedVersions(SUPPORTED_VERSIONS) // Add Supported Versions defines what versions can be supplied
                .setDefaultVersion(DEFAULT_VERSION) // Used when no version, or an unsupported version, is passed in the request
                .useMediaTypeParameter(MediaType.APPLICATION_XML, VERSION_HEADER) // e.g. Accept = application/xml;version=3.5
                .useMediaTypeParameter(MediaType.APPLICATION_JSON, VERSION_HEADER); //e.g. Accept = application/json;version=3.5


    }
}
