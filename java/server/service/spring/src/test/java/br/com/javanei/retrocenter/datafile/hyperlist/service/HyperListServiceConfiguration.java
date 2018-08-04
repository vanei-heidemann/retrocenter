package br.com.javanei.retrocenter.datafile.hyperlist.service;

import br.com.javanei.retrocenter.datafile.hyperlist.persistence.HyperListDAO;
import br.com.javanei.retrocenter.datafile.hyperlist.persistence.HyperListGameDAO;
import org.mockito.AdditionalAnswers;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;

public class HyperListServiceConfiguration {
    @Bean
    public HyperListService hyperListService() {
        return new HyperListService();
    }

    @Bean
    public HyperListDAO datafileDAO() {
        HyperListDAO dao = Mockito.mock(HyperListDAO.class);
        Mockito.when(dao.saveAndFlush(Mockito.any())).then(AdditionalAnswers.returnsFirstArg());
        return dao;
    }

    @Bean
    public HyperListGameDAO gameDAO() {
        HyperListGameDAO dao = Mockito.mock(HyperListGameDAO.class);
        Mockito.when(dao.saveAndFlush(Mockito.any())).then(AdditionalAnswers.returnsFirstArg());
        return dao;
    }
}
