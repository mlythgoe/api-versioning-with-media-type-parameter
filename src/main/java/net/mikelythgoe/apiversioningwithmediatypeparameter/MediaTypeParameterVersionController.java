package net.mikelythgoe.apiversioningwithmediatypeparameter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MediaTypeParameterVersionController {
    // For versioning, version must be major.minor.patch format

    // http://localhost:8080/hello?version=1.0 (you can use 1.0, 1, or 1.0.0, but you can't use 1.1, 1.0.1, etc.')
    @GetMapping(value = "/hello", version = "1", produces = "application/json")
    public String helloV1point0() {
        return "{\"" +
                "message\":\"Hello Version 1\"" +
                "}";
    }

    // http://localhost:8080/hello - Accept=application/json;version=2.0 (you can use 2.0, 2, or 2.0.0, but you can't use 2.1, 2.0.1, etc.')
    @GetMapping(value = "/hello", version = "2", produces = "application/json")
    public String helloV2point0Json() {
        return "{" +
                "\"message\":\"Hello Version 2\"" +
                "}";
    }

    // http://localhost:8080/hello - Accept=application/xml;version=2.0 (you can use 2.0, 2, or 2.0.0, but you can't use 2.1, 2.0.1, etc.')
    // Example: curl --location 'http://localhost:8080/hello' --header 'Accept: application/xml;version=2'
    @GetMapping(value = "/hello", version = "2", produces = "application/xml")
    public String helloV2point0Xml() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                " <root>\n" +
                "     <message>Hello Version 2</message>\n" +
                " </root>";
    }

    // http://localhost:8080/hello & application/json  (you can use 3.5, or 3.5.0, but you can't use 3, 3.6., 3.5.1, etc.)
    @GetMapping(value = "/hello", version = "3.5", produces = "application/json")
    public String helloV3point5() {
        return "{\"" +
                "message\":\"Hello Version 3.5\"" +
                "}";
    }

    // http://localhost:8080/hello?version=9 ( you can use 9, 9.0, or 9.0.0, but you can't use 9.1, 9.0.1, etc.)
    @GetMapping(value = "/hello", version = "9")
    public String helloV9() {
        return "{\"" +
                "message\":\"Hello Version 9\"" +
                "}";
    }

    // http://localhost:8080/hello?version=9 ( you can use 9.9, or 9.9.0, but you can't use 9.9.1, etc.)
    @GetMapping(value = "/hello", version = "9.9")
    public String helloV9point9() {
        return "{\"" +
                "message\":\"Hello Version 9.9\"" +
                "}";
    }
}

