import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {

    public static void main(String[] args) throws Exception {

        // fazer uma conexão HTTP e buscar os top 250 filmes
        
        // String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java/api/TopMovies.json";
        // ContentExtractor extractor = new MovieExtractor();

        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java/api/NASA-APOD.json";
        ContentExtractor extractor = new NasaExtractor();

        //Cria um cliente HTTP para fazer requisições
        Http http = new Http();
        String json = http.getData(url);


        List<Content> contents = extractor.extractContents(json);

        // exibir e manipular os dados 
        StickerGenerator generator = new StickerGenerator();
        for (int i = 0; i < 3; i++) {

            Content content = contents.get(i);

            InputStream inputStream = new URL(content.getImage()).openStream();
            String fileName = "./images/" + content.getTitle() + ".png";

            generator.create(inputStream, fileName);

            System.out.println(content.getTitle());
            System.out.println();
        }
    }
}