package padroesestruturaisteste_exercicios.proxy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import padroesestruturais_exercicios.proxy.Paciente;
import padroesestruturais_exercicios.proxy.PacienteProxy;
import padroesestruturais_exercicios.proxy.BD;
import padroesestruturais_exercicios.proxy.Secretario;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class PacienteProxyTest {
    @BeforeEach
    void setUp() {
        BD.addPaciente(new Paciente(1111111, "João", "Juiz de Fora", 10/05f, 11/03f));
        BD.addPaciente(new Paciente(2222222, "Maria", "Juiz de Fora", 15/04f, 06/01f));
    }

    @Test
    void deveRetornarDadosPessoaisPaciente() {
        PacienteProxy paciente = new PacienteProxy(1111111);

        assertEquals(Arrays.asList("João", "Juiz de Fora"), paciente.obterDadosPessoais());
    }

    @Test
    void deveRetonarConsultasPaciente() {
        Secretario secretario = new Secretario("Ana", true);
        PacienteProxy paciente = new PacienteProxy(2222222);

        assertEquals(Arrays.asList(15/04f, 06/01f), paciente.obterUltimasConsultas(secretario));
    }

    @Test
    void deveRetonarExcecaoUsuarioNaoAutorizadoConsultarNotasAluno() {
        try {
            Secretario secretario = new Secretario("Joana", false);
            PacienteProxy paciente = new PacienteProxy(2222222);

            paciente.obterUltimasConsultas(secretario);
            fail();
        }
        catch (IllegalArgumentException e) {
            assertEquals("Acesso não autorizado", e.getMessage());
        }
    }
}
