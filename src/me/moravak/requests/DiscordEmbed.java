package me.moravak.requests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.time.LocalDateTime;

public class DiscordEmbed
{
    private HashMap<String, Object> embed = new HashMap<String, Object>();
    private ArrayList<HashMap<String, Object>> fields = new ArrayList<HashMap<String, Object>>();
    private String author = "";
    private String footer = "";
    private String image = "";
    private String thumbnail = "";

    /*
    *
    * Gets whole embed as json
    *
    * */
    public String getEmbed()
    {
        String json = "{"+this.embed.entrySet().stream()
                .map(e -> "\""+ e.getKey() + "\":\"" + String.valueOf(e.getValue()) + "\"")
                .collect(Collectors.joining(", "))+"}";
        json = json.replaceAll(".{1}$", getFields() + getAuthor() + getFooter() + getThumbnail() + getImage() + "}");
        return json;
    }

    /*
    *
    * Gets fields as json
    *
    * */
    public String getFields()
    {
        String fields = "[";
        for (HashMap<String, Object> field : this.fields)
        {
            String field_str = "{"+field.entrySet().stream()
                    .map(e -> "\""+ e.getKey() + "\":\"" + String.valueOf(e.getValue()) + "\"")
                    .collect(Collectors.joining(", "))+"}";
            field_str.replaceAll(".{1}$", ",");
            field_str.replaceAll("^{1}", "");
            fields += field_str + ",";
        }
        fields = fields.replaceAll(".{1}$", "]");;
        return (!fields.equals("]")) ? ", \"fields\":" + fields : "";
    }

    /*
    *
    * Gets author as json
    *
    * */
    public String getAuthor()
    {
        return (!this.author.equals("")) ? ", \"author\":" + this.author : "";
    }

    /*
    *
    * Gets footer as json
    *
    * */
    public String getFooter()
    {
        return (!this.footer.equals("")) ? ", \"footer\":" + this.footer : "";
    }

    /*
    *
    * Gets image as json
    *
    * */
    public String getImage()
    {
        return (!this.image.equals("")) ? ", \"image\": { \"url\":\"" + this.image + "\"}": "";
    }

    /*
    *
    * Gets thumbnail as json
    *
    * */
    public String getThumbnail()
    {
        return (!this.thumbnail.equals("")) ? ", \"thumbnail\": { \"url\":\"" + this.thumbnail + "\"}": "";
    }

    /*
    *
    * Sets embed title
    * @param title Embed title
    *
    * */
    public void setTitle(String title)
    {
        this.embed.put("title", title);
    }

    /*
    *
    * Sets embed description
    * @param description Embed description
    *
    * */
    public void setDescription(String description)
    {
        this.embed.put("description", description);
    }

    /*
    *
    * Sets embed author
    * @param name Author name
    * @param url Author url
    * @param icon_url Icon Url
    *
    * */
    public void setAuthor(String name, String url, String icon_url)
    {
        String author = String.format("{\"name\":\"%s\", \"url\":\"%s\", \"icon_url\": \"%s\"}", name, url, icon_url);
        this.author = author;
    }

    /*
    *
    * Sets embed color
    * @param int color Color of embed
    *
    * */
    public void setColor(int color)
    {
        this.embed.put("color", color);
    }

    /*
    *
    * Sets url of embed
    * @param url Url of embed
    *
    * */
    public void setURL(String url)
    {
        this.embed.put("url", url);
    }

    /*
    *
    * Sets timestamp
    *
    * */
    public void setTimestamp()
    {
        this.embed.put("timestamp", LocalDateTime.now());
    }

    /*
    *
    * Sets footer
    * @param text Footer text
    * @param icon_url Footer icon
    *
    * */
    public void setFooter(String text, String icon_url)
    {
        String footer = String.format("{\"text\":\"%s\", \"icon_url\": \"%s\"}", text, icon_url);
        this.footer = footer;
    }

    /*
    *
    * Sets image
    * @param url Image url
    *
    * */
    public void setImage(String url)
    {
        this.image = url;
    }

    /*
    *
    * Sets thumbnail
    * @param url Thumbnail url
    *
    * */
    public void setThumbnail(String url)
    {
        this.thumbnail = url;
    }
    /*
    *
    * Adds field
    * @param name Field name
    * @param value Field value
    * @param bool inline
    *
    * */
    public void addField(String name, String value, boolean inline)
    {
        HashMap<String, Object> field = new HashMap<String, Object>();
        field.put("name", name);
        field.put("value", value);
        field.put("inline", inline);
        this.fields.add(field);
    }
}
