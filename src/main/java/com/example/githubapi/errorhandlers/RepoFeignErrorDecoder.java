package com.example.githubapi.errorhandlers;

import com.example.githubapi.exceptions.RepoNotFoundException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.stereotype.Component;

@Component
public class RepoFeignErrorDecoder implements ErrorDecoder {
    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() >= 400 && response.status() <= 499) {
            throw new RepoNotFoundException("Cannot find repository.");
        }

        return defaultErrorDecoder.decode(methodKey, response);
    }
}
