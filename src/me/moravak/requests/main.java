package me.moravak.requests;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class main {
    public static void main(String[] args) throws Exception
    {
        DiscordWebhook hook = new DiscordWebhook("your supercool url");
        hook.setAuthor("April");

        DiscordEmbed embed = new DiscordEmbed();
        embed.setTitle("Nice webhook");
        embed.setDescription("Its really nice");
        embed.addField("Dev", "Majkl", false);
        hook.addEmbed(embed);
        hook.send();

    }
}
