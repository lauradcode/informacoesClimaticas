import org.json.JSONObject;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ProjetoSistemaDeInformacoesClimaticasEmTempoReal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);  // definindo o scanner para ler do teclado o local (System.in)
        System.out.print("Digite o nome da cidade: ");  //mensagem para o usuário
        String cidade = scanner.nextLine(); //lê a cidade do teclado escrita pelo us

        try {
            String dadosClimaticos = getDadosClimaticos(cidade); //ainda vai ser definido. retorna um JSON. código 1006 = localização ñ encontrada
            if (dadosClimaticos.contains("\"code\":1006")) { //checagem. "code":1006 tem que estar entres aspas por ser uma string
                System.out.println("Localização não econtrada. Tente novamente.");
            } else {
                imprimirDadosClimaticos(dadosClimaticos);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    
    public static String getDadosClimaticos (String cidade) throws Exception { //desenvolvendo o método mencionado anteriomente

    }



}
