import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MovieExtractor implements ContentExtractor {

    public List<Content> extractContents(String json) {

        // extrair só os dados que interessam (titulo, poster, classificação)
        var parser = new JsonParse();
        List<Map<String, String>> listContent = parser.parse(json);

        List<Content> contents = new ArrayList<>();

        // Popular a lista de conteúdos
        for (Map<String, String> content : listContent) {
            String title = content.get("title");
            String image = content.get("image");

            Content newContent = new Content(title, image);

            contents.add(newContent);
        }

        return contents;
    }
}
