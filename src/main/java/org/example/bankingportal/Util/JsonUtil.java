package org.example.bankingportal.Util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonUtil {

    private static final ObjectMapper objectMapper =
            new ObjectMapper()
                    .setSerializationInclusion(JsonInclude.Include.NON_NULL);


    private static String toJsonString(Object obj) {

        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            log.warn(e.getMessage());
        }
        return null;
    }

}
