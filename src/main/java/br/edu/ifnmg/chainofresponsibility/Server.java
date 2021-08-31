package br.edu.ifnmg.chainofresponsibility;

/**
 *
 * @author USER
 */
import java.util.HashMap;
import java.util.Map;

public class Server {
    private Map<String, String> users = new HashMap<>();
    private Middleware middleware;

    /**
     * O cliente passa uma cadeia de objetos para o servidor. Isso melhora a flexibilidade e
     * torna o teste da classe de servidor mais fácil.
     */
    public void setMiddleware(Middleware middleware) {
        this.middleware = middleware;
    }

    /**
     * O servidor obtém e-mail e senha do cliente e envia a autorização
     * solicitação à rede.
     */
    public boolean logIn(String email, String password) {
        if (middleware.check(email, password)) {
            System.out.println("Authorization have been successful!");
            return true;
        }
        return false;
    }

    public void register(String email, String password) {
        users.put(email, password);
    }

    public boolean hasEmail(String email) {
        return users.containsKey(email);
    }

    public boolean isValidPassword(String email, String password) {
        return users.get(email).equals(password);
    }
}
