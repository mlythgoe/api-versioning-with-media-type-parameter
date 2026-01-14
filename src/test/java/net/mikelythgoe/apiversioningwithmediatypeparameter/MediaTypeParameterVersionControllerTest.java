package net.mikelythgoe.apiversioningwithmediatypeparameter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MediaTypeParameterVersionController.class)
public class MediaTypeParameterVersionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void helloV1_ShouldReturnVersion1() throws Exception {
        mockMvc.perform(get("/hello")
                .accept(MediaType.APPLICATION_JSON + ";" + "version=1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Hello Version 1"));
    }

    @Test
    void helloV2_ShouldReturnVersion2() throws Exception {
        mockMvc.perform(get("/hello")
                .accept(MediaType.APPLICATION_JSON + ";" + "version=2.0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Hello Version 2 - JSON"));
    }

    @Test
    void helloV3point5Json_ShouldReturnVersion3point5Json() throws Exception {
        mockMvc.perform(get("/hello")
                .accept(MediaType.APPLICATION_JSON + ";" + "version=3.5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Hello Version 3.5 - JSON"));
    }

    @Test
    void helloV2Xml_ShouldReturnVersion2Xml() throws Exception {
        mockMvc.perform(get("/hello")
                        .accept(MediaType.APPLICATION_XML + ";" + "version=2.0"))
                .andExpect(status().isOk())
                .andExpect(xpath("/Message/message").string("Hello Version 2 - XML"));
    }

    @Test
    void helloV3point5Xml_ShouldReturnVersion3point5Xml() throws Exception {
        mockMvc.perform(get("/hello")
                        .accept(MediaType.APPLICATION_XML + ";" + "version=3.5"))
                .andExpect(status().isOk())
                .andExpect(xpath("/Message/message").string("Hello Version 3.5 - XML"));
    }

    @Test
    void helloV9_ShouldReturnVersion9() throws Exception {
        mockMvc.perform(get("/hello")
                .accept(MediaType.APPLICATION_JSON + ";" + "version=9"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Hello Version 9 - JSON"));
    }

    @Test
    void helloV9point9_ShouldReturnVersion9point9() throws Exception {
        mockMvc.perform(get("/hello")
                .accept(MediaType.APPLICATION_JSON + ";" + "version=9.9"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Hello Version 9.9 - JSON"));
    }

    @Test
    void helloXml_WithoutVersion_ShouldReturnDefaultXmlResponse() throws Exception {
        mockMvc.perform(get("/hello")
                        .accept(MediaType.APPLICATION_XML))
                .andExpect(status().isOk())
                .andExpect(xpath("/Message/message").string("Hello Version 2 - XML"));
    }

    @Test
    void hello_WithoutHeader_ShouldReturnDefaultResponse() throws Exception {
        // No Version should return the default response
        // unless we have set "Version Required" in the config.
        // So this should return the DEFAULT response
        mockMvc.perform(get("/hello")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Hello Version 2 - JSON"));
    }

    @Test
    void hello_WithInvalidVersion_ShouldReturnDefaultResponse() throws Exception {
        // Invalid Version should return the default response,
        // unless we have set "Version Required" in the config.
        // So this should return the DEFAULT response
        mockMvc.perform(get("/hello")
                .accept(MediaType.APPLICATION_JSON+ ";" + "version=9.0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Hello Version 9 - JSON"));
    }

}
