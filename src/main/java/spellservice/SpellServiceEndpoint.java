package spellservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 * Created by zhitnikov.bronislav on 05.09.2016.
 */
@Endpoint
public class SpellServiceEndpoint {
    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";
    SpellService spellService;
    @Autowired
    public SpellServiceEndpoint(SpellService spellService) {
        this.spellService = spellService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "CheckTextRequest")
    @ResponsePayload
    public CheckTextResponse checkTextRequest(@RequestPayload CheckTextRequest request) {
        CheckTextResponse response = new CheckTextResponse();
        response.setSpellResult(spellService.checkText(request.getText()));

        return response;
    }
}
