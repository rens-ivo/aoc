import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.stream.Stream;

public class PuzzleInputFetcher {

    static String BASE_URL = "https://adventofcode.com/2022/day/%s/input";

    private int day;

    private String cookie;

    public PuzzleInputFetcher(int day, String cookie) {
        this.day = day;
        this.cookie = cookie;
    }

    public String getFullUrl() {
        return String.format(BASE_URL, day);
    }

    public Stream<String> getStream() {
        var uri = URI.create(getFullUrl());
        var request = HttpRequest.newBuilder(uri)
                .setHeader("Cookie" , this.cookie)
                .build();
        var client = HttpClient.newHttpClient();

        HttpResponse<Stream<String>> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofLines());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return response.body();
    }
}

