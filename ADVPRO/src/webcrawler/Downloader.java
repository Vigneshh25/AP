package webcrawler;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

interface Downloader {
    String download(String url) throws IOException;
}

class HttpDownloader implements Downloader {
    @Override
    public String download(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        Scanner scanner = new Scanner(conn.getInputStream());
        scanner.useDelimiter("\\A");
        return scanner.hasNext() ? scanner.next() : "";
    }
}

class DownloaderFactory {
    public static Downloader getDownloader() {
        return new HttpDownloader();
    }
}
