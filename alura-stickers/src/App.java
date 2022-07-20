import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) throws Exception {

        // fazer uma conexão HTTP e buscar os top 250 filmes
        String url = "https://api.mocki.io/v2/549a5d8b/Top250Movies";
        URI address = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(address).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // extrair só os dados que interessam (titulo, poster, classificação)
        var parser = new JsonParse();
        List<Map<String, String>> listMovies = parser.parse(body);

        // exibir e manipular os dados 
        for (Map<String,String> movie : listMovies) {
            System.out.println(movie.get("title"));
            String imageUrl = movie.get("image");
            String title = movie.get("title");

            InputStream inputStream = new URL(imageUrl).openStream();
            String fileName = title + ".png";

            StickerGenerator generator = new StickerGenerator();
            generator.create(inputStream, fileName);
        }
    }
}