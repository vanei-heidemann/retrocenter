package br.com.javanei.retrocenter.datafile.clrmamepro.service;

import br.com.javanei.retrocenter.datafile.clrmamepro.persistence.CMProDatafileDAO;
import br.com.javanei.retrocenter.datafile.clrmamepro.persistence.CMProDiskDAO;
import br.com.javanei.retrocenter.datafile.clrmamepro.persistence.CMProGameDAO;
import br.com.javanei.retrocenter.datafile.clrmamepro.persistence.CMProGameRomDAO;
import br.com.javanei.retrocenter.datafile.clrmamepro.persistence.CMProResourceDAO;
import br.com.javanei.retrocenter.datafile.clrmamepro.persistence.CMProResourceRomDAO;
import br.com.javanei.retrocenter.datafile.clrmamepro.persistence.CMProSampleDAO;
import br.com.javanei.retrocenter.datafile.clrmamepro.persistence.CMProSampleofDAO;
import org.mockito.AdditionalAnswers;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;

public class CMProServiceConfiguration {
    @Bean
    public CMProService cmProService() {
        return new CMProService();
    }

    @Bean
    public CMProDatafileDAO datafileDAO() {
        CMProDatafileDAO dao = Mockito.mock(CMProDatafileDAO.class);
        Mockito.when(dao.saveAndFlush(Mockito.any())).then(AdditionalAnswers.returnsFirstArg());
        return dao;
    }

    @Bean
    public CMProDiskDAO diskDAO() {
        CMProDiskDAO dao = Mockito.mock(CMProDiskDAO.class);
        Mockito.when(dao.saveAndFlush(Mockito.any())).then(AdditionalAnswers.returnsFirstArg());
        return dao;
    }

    @Bean
    public CMProGameDAO gameDAO() {
        CMProGameDAO dao = Mockito.mock(CMProGameDAO.class);
        Mockito.when(dao.saveAndFlush(Mockito.any())).then(AdditionalAnswers.returnsFirstArg());
        return dao;
    }

    @Bean
    public CMProGameRomDAO gameRomDAO() {
        CMProGameRomDAO dao = Mockito.mock(CMProGameRomDAO.class);
        Mockito.when(dao.saveAndFlush(Mockito.any())).then(AdditionalAnswers.returnsFirstArg());
        return dao;
    }

    @Bean
    public CMProResourceDAO resourceDAO() {
        CMProResourceDAO dao = Mockito.mock(CMProResourceDAO.class);
        Mockito.when(dao.saveAndFlush(Mockito.any())).then(AdditionalAnswers.returnsFirstArg());
        return dao;
    }

    @Bean
    public CMProResourceRomDAO resourceRomDAO() {
        CMProResourceRomDAO dao = Mockito.mock(CMProResourceRomDAO.class);
        Mockito.when(dao.saveAndFlush(Mockito.any())).then(AdditionalAnswers.returnsFirstArg());
        return dao;
    }

    @Bean
    public CMProSampleDAO sampleDAO() {
        CMProSampleDAO dao = Mockito.mock(CMProSampleDAO.class);
        Mockito.when(dao.saveAndFlush(Mockito.any())).then(AdditionalAnswers.returnsFirstArg());
        return dao;
    }

    @Bean
    public CMProSampleofDAO sampleofDAO() {
        CMProSampleofDAO dao = Mockito.mock(CMProSampleofDAO.class);
        Mockito.when(dao.saveAndFlush(Mockito.any())).then(AdditionalAnswers.returnsFirstArg());
        return dao;
    }
}
