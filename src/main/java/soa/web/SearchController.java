package soa.web;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class SearchController {

  private final ProducerTemplate producerTemplate;

  @Autowired
  public SearchController(ProducerTemplate producerTemplate) {
    this.producerTemplate = producerTemplate;
  }

  @RequestMapping("/")
  public String index() {
    return "index";
  }


  @RequestMapping(value = "/search")
  @ResponseBody
  public Object search(@RequestParam(value="q", required=true) String q,
                       @RequestParam(value="n", required=false, defaultValue="10") int max,
                       @RequestParam(value="l", required=false, defaultValue="en") int np,
                       @RequestParam(value="p", required=false, defaultValue="1") String lang ) {

    Map<String,Object> headers = new HashMap<String,Object>();
    
    headers.put("CamelTwitterKeywords", q);
    headers.put("CamelTwitterCount", max);
    headers.put("CamelTwitterNumberOfPages", np);
    headers.put("CamelTwitterSearchLanguage", lang);

    return producerTemplate.requestBodyAndHeaders("direct:search", "", headers);
  }
}