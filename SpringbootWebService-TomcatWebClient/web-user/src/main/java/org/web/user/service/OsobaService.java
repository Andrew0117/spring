package org.web.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.web.user.vm.OsobaVM;

import java.util.List;

@Service
@Scope(value = "singleton")
public class OsobaService {

    @Autowired
    private AppServiceI appService;

    public List<OsobaVM> getAll() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<OsobaVM>> responseEntity = restTemplate.exchange(
                appService.getHost() + "/osoba/",
                HttpMethod.GET, null,
                new ParameterizedTypeReference<>() {
                });

        return responseEntity.getBody();
    }

    public OsobaVM save(OsobaVM osobaVM) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<OsobaVM> osobaVMHttpEntity = new HttpEntity<>(osobaVM, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<OsobaVM> response = restTemplate.exchange(
                appService.getHost() + "/osoba/",
                HttpMethod.POST, osobaVMHttpEntity,
                OsobaVM.class);

        return response.getBody();
    }

    public HttpStatus update(OsobaVM osobaVM) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<OsobaVM> osobaVMHttpEntity = new HttpEntity<>(osobaVM, httpHeaders);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Void> response = restTemplate.exchange(
                appService.getHost() + "/osoba/",
                HttpMethod.PUT, osobaVMHttpEntity,
                Void.class);

        return response.getStatusCode();
    }

    public HttpStatus delete(Long id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Void> response = restTemplate.exchange(
                appService.getHost() + "/osoba/{id}",
                HttpMethod.DELETE, httpEntity,
                Void.class, id);

        return response.getStatusCode();
    }

    public List<OsobaVM> findOsobaFilter(OsobaVM osobaVM) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<OsobaVM> osobaVMHttpEntity = new HttpEntity<>(osobaVM, httpHeaders);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<OsobaVM>> responseEntity = restTemplate.exchange(
                appService.getHost() + "/osoba/filter",
                HttpMethod.POST, osobaVMHttpEntity,
                new ParameterizedTypeReference<>() {
                });

        return responseEntity.getBody();
    }
}
