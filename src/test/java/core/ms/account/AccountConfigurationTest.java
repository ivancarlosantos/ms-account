package core.ms.account;

import core.ms.account.cross.utils.TokenRequest;
import core.ms.account.infra.domain.Account;
import core.ms.account.infra.domain.Token;
import core.ms.account.infra.repository.AccountRepository;
import core.ms.account.status.AccountStatus;
import io.restassured.RestAssured;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AccountConfigurationTest {

    @LocalServerPort
    private Integer port;

    @Autowired
    private AccountRepository accountRepository;

    @Container
    static PostgreSQLContainer<?> container = new PostgreSQLContainer<>(
            DockerImageName.parse("postgres:11"));

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.driver-class-name", () -> "org.postgresql.Driver");
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);

        log.info("url {}", container.getJdbcUrl());
        log.info("username {}", container.getUsername());
        log.info("password {}", container.getPassword());
        log.info("spring.datasource.driver-class-name {}", container.getJdbcDriverInstance());
    }

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost:" + port;
        accountRepository.deleteAll();
    }

    @BeforeAll
    static void beforeAll() {
        container.start();
    }

    @AfterAll
    static void afterAll() {
        container.stop();
    }

    @Test
    void testAccount() {
        Long id = 1L;
        String nome = "Account Name";
        String conta = "00000000-00";
        String agencia = "00000000-00";
        String limite = "1000000";
        String nodeID = UUID.randomUUID().toString();
        String status = AccountStatus.ATIVO.toString();

        Account account = new Account(id, nome, conta, agencia, limite, nodeID, status);

        accountRepository.save(account);

        assertNotNull(account);
        assertEquals(id, account.getId());
        assertEquals(nome, account.getNome());
        assertEquals(agencia, account.getAgencia());
        assertEquals(conta, account.getConta());
        assertEquals(limite, account.getLimite());
        assertEquals(nodeID, account.getNodeID());
        assertEquals(status, account.getStatus());
    }

    @Test
    void rateAccount() {

        Long id = 1L;
        String nome = "Account Name";
        String conta = "00000000-00";
        String agencia = "00000000-00";
        String limite = "1000000";
        String nodeID = UUID.randomUUID().toString();
        String status = AccountStatus.ATIVO.toString();

        Account account = new Account(id, nome, conta, agencia, limite, nodeID, status);

        accountRepository.save(account);

        List<Account> accounts = accountRepository.findAll();
        assertEquals(1, accounts.size());
        assertEquals("Account Name", accounts.get(0).getNome());
    }

    @Test
    void testAccountSuccess() {
        Account p = new Account();
        p.setConta("0000 1111 2222 3333");
        Assertions.assertEquals("0000 1111 2222 3333", p.getConta());
    }

    @Test
    void testAccountError() {
        Account p = new Account();
        p.setConta("0000 1111 2222 3333");
        Assertions.assertNotEquals("3333 2222 1111 0000", p.getConta());
    }

    @Test
    void testAccountNull() {
        Account p = new Account();
        p.setConta(null);
        Assertions.assertNull(p.getConta());
    }
}
