package br.edu.ifnmg.chainofresponsibility;

/**
 * @author USER
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Demo {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Server server;

    private static void init() {
        server = new Server();
        server.register("teste@example.com", "teste_pass");
        server.register("user@example.com", "user_pass");

        //Todas as verificações estão vinculadas. O cliente pode construir várias cadeias usando o mesmo
        // componente.
        Middleware middleware = new ThrottlingMiddleware(2);
        middleware.linkWith(new UserExistsMiddleware(server))
                .linkWith(new RoleCheckMiddleware());

         //O servidor obtém uma cadeia do código do cliente.
        server.setMiddleware(middleware);
    }

    public static void main(String[] args) throws IOException {
        init();

        boolean success;
        do {
            System.out.print("Enter email: \"user@example.com\" ");
            String email = "user@example.com";
            System.out.print("Input password: \"user_pass\"");
            String password = "user_pass";
            success = server.logIn(email, password);
         
        } while (!success);
        
    }
}