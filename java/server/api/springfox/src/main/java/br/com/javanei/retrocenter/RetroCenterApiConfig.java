package br.com.javanei.retrocenter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RetroCenterApiConfig {
    @Value("${retrocenter.repository.basedir}")
    private String repositoryBaseDir;

    public String getRepositoryBaseDir() {
        return repositoryBaseDir;
    }
}
