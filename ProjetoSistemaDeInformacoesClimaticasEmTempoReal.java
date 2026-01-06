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
             e.printStackTrace();
        }

    }
    
    public static String getDadosClimaticos (String cidade) throws Exception { //desenvolvendo o método mencionado anteriomente
        String apiKey = Files.readString( Paths.get("api-key.txt")).trim(); /* colocando a api coletada. trim -> remove espaço errado */
        String formataNomeCidade = URLEncoder.encode(cidade, StandardCharsets.UTF_8);
        String apiUrl = "https://api.weatherapi.com/v1/current.json?key=" + apiKey + "&q=" + formataNomeCidade; //escrevendo a url, isso é uma string
        HttpRequest request = HttpRequest.newBuilder() //classe java que vai sair do sistema e fazer a solicitação 
            .uri(URI.create(apiUrl)) //define o URI da solicitação http
            .build(); //finaliza a construção da solicitação HTTP
        
        //Criando objeto para enviar a solicitação HTTP e receber a resposta HTTP para acessar o site weather api
        HttpClient client = HttpClient.newHttpClient(); //objeto com poder de enviar solicitação e receber resposta
        //Criando objeto para enviar a requisição HTTP e receber a resposta HTTP, para se comunicar com o site 
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body(); //retorna os dados obtidos 
    }

    /*fazendo o método da impressão para não ficar confuso para o usuário. TODAS as Strings usadas são de acordo com a DOCUMENTAÇÃO DO WEATHER API*/
    public static void imprimirDadosClimaticos(String dados){
        //System.out.println("DEBUG: " + dados);
        JSONObject dadosJson = new JSONObject(dados); //aqui esta od dados da localização
        JSONObject informacoesMeteorologicas = dadosJson.getJSONObject("current"); //significa que quero os dados em tempo real da weather, por isso 'current'

        //extrai os dados da localização
        String cidade = dadosJson.getJSONObject("location").getString ("name");
        String pais = dadosJson.getJSONObject("location").getString ("country");

        //extrai dados adicionais 
        String condicaoTempo = informacoesMeteorologicas.getJSONObject("condition").getString("text"); //condição e descrição
        int umidade = informacoesMeteorologicas.getInt("humidity");
        float velocidadeVento = informacoesMeteorologicas.getFloat("wind_kph");
        float pressaoAtmosferica = informacoesMeteorologicas.getFloat("pressure_mb");
        float sensacaoTermica = informacoesMeteorologicas.getFloat("feelslike_c");
        float temperaturaAtual = informacoesMeteorologicas.getFloat("temp_c");

        // Extrai a data e hora da string retornada pela API
        String dataHoraString = informacoesMeteorologicas.getString("last_updated");

        // Imprime as informações atuais
        System.out.println("Informações Meteorológicas para " + cidade + ", " + pais);
        System.out.println("Data e Hora: " + dataHoraString);
        System.out.println("Temperatura Atual: " + temperaturaAtual + "°C");
        System.out.println("Sensação Térmica: " + sensacaoTermica + "°C");
        System.out.println("Condição do Tempo: " + condicaoTempo);
        System.out.println("Umidade: " + umidade + "%");
        System.out.println("Velocidade do Vento: " + velocidadeVento + " km/h");
        System.out.println("Pressão Atmosférica: " + pressaoAtmosferica + " mb");
    }
}
