package me.moravak.requests;


import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public class DiscordWebhook
{
    private String url;
    private HashMap<String, Object> content = new HashMap<String, Object>();
    private ArrayList<String> embeds = new ArrayList<String>();
    /*
    *
    * Creates webhook
    * @param url The webhook url
    *
    * */
    public DiscordWebhook(String url)
    {
        this.url = url;
    }

    /*
    *
    * Adds content as plain text
    * @param content Webhook content
    *
    * */
    public void setContent(String content)
    {
        this.content.put("content", content);
    }

    /*
    *
    * Sets author name
    * @param author Author name
    *
    * */
    public void setAuthor(String author)
    {
        this.content.put("username", author);
    }

    /*
    *
    * Sets webhook avatar
    * @param avatar Webhook avatar
    *
    * */
    public void setAvatar(String avatar)
    {
        this.content.put("avatar_url", avatar);
    }

    /*
    *
    * Adds embed to webhook
    * @param embed DiscordEmbed
    *
    * */
    public void addEmbed(DiscordEmbed embed)
    {
        this.embeds.add(embed.getEmbed());
    }

    /*
    *
    * Enables TTS
    *
    * */
    public void enableTTS()
    {
        this.content.put("tts", true);
    }

    /*
    *
    * Sends webhook
    *
    * */
    public void send() throws IOException
    {
        URL url = new URL(this.url);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("POST");
        conn.addRequestProperty("User-Agent", "Java-DiscordWebhook-Moravak");
        conn.addRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        String json = "{"+this.content.entrySet().stream()
                .map(e -> "\""+ e.getKey() + "\":\"" + String.valueOf(e.getValue()) + "\"")
                .collect(Collectors.joining(", "))+"}";
        json = json.replaceAll(".{1}$", ", \"embeds\":" + this.embeds + "}");
        byte[] out = json.getBytes(StandardCharsets.UTF_8);
        OutputStream stream = conn.getOutputStream();
        stream.write(out);
        System.out.println(json);
        System.out.println(conn.getResponseCode() + " " + conn.getResponseMessage());
        conn.disconnect();
    }
}