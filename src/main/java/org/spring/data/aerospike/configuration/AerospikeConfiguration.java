package org.spring.data.aerospike.configuration;

import com.aerospike.client.Host;
import com.aerospike.client.policy.ClientPolicy;
import com.aerospike.client.policy.RecordExistsAction;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.aerospike.config.AbstractAerospikeDataConfiguration;
import org.springframework.data.aerospike.repository.config.EnableAerospikeRepositories;

import java.util.Arrays;
import java.util.Collection;

@EnableAerospikeRepositories("org.spring.data.aerospike.repository")
@Configuration
public class AerospikeConfiguration extends AbstractAerospikeDataConfiguration {

    private static final int DEFAULT_PORT = 3000;

    @Bean
    @ConfigurationProperties("spring-data-aerospike.aerospike")
    public AerospikeConfigurationProperties aerospikeConfigurationProperties() {
        return new AerospikeConfigurationProperties();
    }

    @Override
    protected Collection<Host> getHosts() {
        final var hostsString = aerospikeConfigurationProperties().getHosts();
        final var hosts = Host.parseHosts(hostsString, DEFAULT_PORT);
        return Arrays.asList(hosts);
    }

    @Override
    protected String nameSpace() {
        return aerospikeConfigurationProperties().getNamespace();
    }

    @Override
    protected ClientPolicy getClientPolicy() {
        final var connectConfiguration = aerospikeConfigurationProperties();

        final var clientPolicy = new ClientPolicy();

        clientPolicy.user = connectConfiguration.getUser();
        clientPolicy.password = connectConfiguration.getPassword();
        clientPolicy.failIfNotConnected = true;
        clientPolicy.timeout = connectConfiguration.getConnectTimeoutMs();
        clientPolicy.writePolicyDefault.totalTimeout = connectConfiguration.getOperationTimeoutMs();
        clientPolicy.writePolicyDefault.socketTimeout = connectConfiguration.getOperationTimeoutMs();
        clientPolicy.writePolicyDefault.recordExistsAction = RecordExistsAction.UPDATE_ONLY;
        clientPolicy.writePolicyDefault.maxRetries = 0;
        clientPolicy.readPolicyDefault.socketTimeout = connectConfiguration.getOperationTimeoutMs();
        clientPolicy.readPolicyDefault.totalTimeout = connectConfiguration.getOperationTimeoutMs();
        clientPolicy.readPolicyDefault.maxRetries = 0;
        clientPolicy.readPolicyDefault.sleepBetweenRetries = 0;
        return clientPolicy;
    }
}