package br.com.javanei.retrocenter.datafile.service;

import br.com.javanei.retrocenter.datafile.entity.DatafileEntity;
import br.com.javanei.retrocenter.datafile.persistence.DatafileArtifactDAO;
import br.com.javanei.retrocenter.datafile.persistence.DatafileArtifactFileDAO;
import br.com.javanei.retrocenter.datafile.persistence.DatafileDAO;
import br.com.javanei.retrocenter.datafile.persistence.DatafileReleaseDAO;
import org.mockito.AdditionalAnswers;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatafileServiceConfiguration {
    @Bean
    public RetrocenterDatafileService retrocenterDatafileService() {
        return new RetrocenterDatafileService();
    }

    @Bean
    public DatafileDAO datafileDAO() {
        DatafileDAO datafileDAO = Mockito.mock(DatafileDAO.class);
        //Mockito.when(datafileDAO.saveAndFlush(Mockito.any())).then(AdditionalAnswers.returnsFirstArg());
        Mockito.when(datafileDAO.saveAndFlush(Mockito.any())).thenAnswer(new Answer<DatafileEntity>() {
            @Override
            public DatafileEntity answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                DatafileEntity r = (DatafileEntity) args[0];
                r.setId(1l);
                return r;
            }
        });
        return datafileDAO;
    }

    @Bean
    public DatafileArtifactDAO artifactDAO() {
        DatafileArtifactDAO artifactDAO = Mockito.mock(DatafileArtifactDAO.class);
        Mockito.when(artifactDAO.saveAndFlush(Mockito.any())).then(AdditionalAnswers.returnsFirstArg());
        return artifactDAO;
    }

    @Bean
    public DatafileArtifactFileDAO artifactFileDAO() {
        DatafileArtifactFileDAO artifactFileDAO = Mockito.mock(DatafileArtifactFileDAO.class);
        Mockito.when(artifactFileDAO.saveAndFlush(Mockito.any())).then(AdditionalAnswers.returnsFirstArg());
        return artifactFileDAO;
    }

    @Bean
    public DatafileReleaseDAO releaseDAO() {
        DatafileReleaseDAO releaseDAO = Mockito.mock(DatafileReleaseDAO.class);
        Mockito.when(releaseDAO.saveAndFlush(Mockito.any())).then(AdditionalAnswers.returnsFirstArg());
        return releaseDAO;
    }
}
