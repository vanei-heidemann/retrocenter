package br.com.javanei.retrocenter.datafile.logiqx.service;

import br.com.javanei.retrocenter.datafile.logiqx.persistence.LogiqxDatafileDAO;
import br.com.javanei.retrocenter.datafile.logiqx.persistence.LogiqxGameDAO;
import org.mockito.AdditionalAnswers;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;

public class LogiqxServiceConfiguration {
    @Bean
    public LogiqxService logiqxService() {
        return new LogiqxService();
    }

    @Bean
    public LogiqxDatafileDAO datafileDAO() {
        LogiqxDatafileDAO dao = Mockito.mock(LogiqxDatafileDAO.class);
        Mockito.when(dao.saveAndFlush(Mockito.any())).then(AdditionalAnswers.returnsFirstArg());
        return dao;
    }

    @Bean
    public LogiqxGameDAO gameDAO() {
        LogiqxGameDAO dao = Mockito.mock(LogiqxGameDAO.class);
        Mockito.when(dao.saveAndFlush(Mockito.any())).then(AdditionalAnswers.returnsFirstArg());
        return dao;
    }
}
