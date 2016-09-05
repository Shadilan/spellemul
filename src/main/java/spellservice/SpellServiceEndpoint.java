package spellservice;

import net.yandex.speller.services.spellservice.CheckTextRequest;
import net.yandex.speller.services.spellservice.CheckTextResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
/**
 * Created by Shadilan on 06.09.2016.
 */
public class SpellServiceEndpoint {

    @Endpoint
    public class CountryEndpoint {
        private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

        private ErrorGenerator errorGenerator;

        @Autowired
        public CountryEndpoint(ErrorGenerator errorGenerator) {
            this.errorGenerator = errorGenerator;
        }

        @PayloadRoot(namespace = NAMESPACE_URI, localPart = "CheckTextRequest")
        @ResponsePayload
        public CheckTextResponse checkText(@RequestPayload CheckTextRequest request) {
            CheckTextResponse response = new CheckTextResponse();
            response.setSpellResult(errorGenerator.getError(request.getText()));
            return response;
        }

        @PayloadRoot(namespace = NAMESPACE_URI, localPart = "CheckTextsRequest")
        @ResponsePayload
        public CheckTextResponse checkTexts(@RequestPayload CheckTextsRequest request) {
            CheckTextResponse response = new CheckTextResponse();
            response.setSpellResult(errorGenerator.getError(request.getText()));
            return response;
        }
    }
}
